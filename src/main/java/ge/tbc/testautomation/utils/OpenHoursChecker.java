package ge.tbc.testautomation.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenHoursChecker {

    private static final ZoneId TBILISI = ZoneId.of("Asia/Tbilisi");

    private static final Map<String, DayOfWeek> DAY_MAP = Map.of(
            "ორშაბათი", DayOfWeek.MONDAY,
            "სამშაბათი", DayOfWeek.TUESDAY,
            "ოთხშაბათი", DayOfWeek.WEDNESDAY,
            "ხუთშაბათი", DayOfWeek.THURSDAY,
            "პარასკევი", DayOfWeek.FRIDAY,
            "შაბათი", DayOfWeek.SATURDAY,
            "კვირა", DayOfWeek.SUNDAY
    );

    private static final Pattern SCHEDULE_ENTRY = Pattern.compile(
            "([ა-ჰ]+)(?:-([ა-ჰ]+))?:\\s*(\\d{2}:\\d{2})-(\\d{2}:\\d{2})"
    );

    public boolean isCurrentlyOpen(String scheduleText) {
        if (scheduleText.contains("24/7")) {
            return true;
        }

        LocalDateTime now = LocalDateTime.now(TBILISI);
        DayOfWeek currentDay = now.getDayOfWeek();
        LocalTime currentTime = now.toLocalTime();

        Matcher matcher = SCHEDULE_ENTRY.matcher(scheduleText);

        while (matcher.find()) {
            DayOfWeek startDay = DAY_MAP.get(matcher.group(1));
            DayOfWeek endDay = matcher.group(2) != null ? DAY_MAP.get(matcher.group(2)) : startDay;

            if (startDay == null || endDay == null || !isDayInRange(currentDay, startDay, endDay)) {
                continue;
            }

            LocalTime open = LocalTime.parse(matcher.group(3));
            LocalTime close = LocalTime.parse(matcher.group(4));
            if (close.equals(LocalTime.MIDNIGHT)) {
                close = LocalTime.MAX;   // "until 00:00" means until the end of the day
            }

            if (!currentTime.isBefore(open) && !currentTime.isAfter(close)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDayInRange(DayOfWeek current, DayOfWeek start, DayOfWeek end) {
        int cur = current.getValue();
        int s = start.getValue();
        int e = end.getValue();
        return s <= e ? (cur >= s && cur <= e) : (cur >= s || cur <= e);
    }
}