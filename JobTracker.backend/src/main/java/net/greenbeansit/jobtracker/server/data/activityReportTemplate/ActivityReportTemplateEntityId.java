package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

/**
 * Created by Philipp Minges on 24.05.16.
 */
public class ActivityReportTemplateEntityId implements Serializable
{
    private static final long	serialVersionUID	= 1L;
    private Integer author;
    private String name;
    public Integer getAuthor() {
        return author;
    }
    public void setAuthor(Integer author) {
        this.author = author;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
