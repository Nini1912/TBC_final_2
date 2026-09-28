package ge.tbc.testautomation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse {

    private String key;
    private String slug;
    private Seo seo;
    private List<SectionComponent> sectionComponents;

    public String getKey() {
        return key;
    }

    public String getSlug() {
        return slug;
    }

    public Seo getSeo() {
        return seo;
    }

    public List<SectionComponent> getSectionComponents() {
        return sectionComponents;
    }
}