/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dan
 */
@Entity
@NamedQueries({
    @NamedQuery(name="com.artechnologiesllc.tc.taskById",
        query="from Task as task where task.id = ?")
})
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String title;
    
    private String details;
    
    private String documentationFile;
    
    @Column(nullable = false)
    private byte inputType;
    
    @ManyToOne
    @JoinColumn(name="problem_id")
    private Problem problem;
    
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(nullable=false)
    private short days;
    
    @Column(nullable=false)
    private byte weeks;
    
    @Column(nullable=false)
    private short months;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDocumentationFile() {
        return documentationFile;
    }

    public void setDocumentationFile(String documentationFile) {
        this.documentationFile = documentationFile;
    }

    public byte getInputType() {
        return inputType;
    }

    public void setInputType(byte inputType) {
        this.inputType = inputType;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public short getDays() {
        return days;
    }

    public void setDays(short days) {
        this.days = days;
    }

    public byte getWeeks() {
        return weeks;
    }

    public void setWeeks(byte weeks) {
        this.weeks = weeks;
    }

    public short getMonths() {
        return months;
    }

    public void setMonths(short months) {
        this.months = months;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try {
            Task other = (Task)object;
            if(other.id != null && id != null) {
                return other.id.equals(id);
            }else {
                try {
                    return other.title.equals(title) &&
                            (other.details == null) ? details == null : other.details.equals(details) &&
                            other.inputType == inputType &&
                            (other.problem == null) ? problem == null : other.problem.equals(problem) &&
                            other.startDate.equals(startDate) &&
                            (other.endDate == null) ? endDate == null : other.endDate.equals(endDate) &&
                            other.days == days &&
                            other.weeks == weeks &&
                            other.months == months;
                } catch(NullPointerException e) {
                    return false;
                }
            }
        } catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "com.artechnologiesllc.taskcompleter.domain.Task[ id=" + id + " ]";
    }
    
}
