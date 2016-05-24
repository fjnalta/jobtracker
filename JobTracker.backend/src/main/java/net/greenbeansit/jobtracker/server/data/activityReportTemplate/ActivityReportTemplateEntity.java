package net.greenbeansit.jobtracker.server.data.activityReportTemplate;

import java.io.Serializable;

import javax.persistence.*;

@Entity @IdClass(ActivityReportTemplateEntityId.class)
@Table(name = "activity_report_template")
public class ActivityReportTemplateEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -804679538322787505L;

    @Id @Column(name = "author", nullable = false)
    private Integer author;
    @Id @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "taskId")
    private Integer taskId;


    public ActivityReportTemplateEntity() {

    }

    /**
     * Constructor for a new template
     */
    public ActivityReportTemplateEntity(String name, String text, Integer taskId, Integer author) {
        this.name = name;
        this.text = text;
        this.taskId = taskId;
        this.author = author;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "task_id")
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    @Column(name = "author")
    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
}
