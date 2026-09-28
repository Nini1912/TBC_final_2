package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String type;
    private String title;
    private int status;
    private String detail;
    private String code;
    private String traceId;
    private String endpoint;

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public String getCode() {
        return code;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getEndpoint() {
        return endpoint;
    }
}