/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.taskcompleter.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author dan
 */
@Entity
public class CompleteInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(optional=false)
    @JoinColumn(name="TASK_ID",nullable=false,updatable=false)
    private Task task;
    
    @Id
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date scheduledDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false)
    private Date completeTime;
    
    @Column(nullable=false)
    private String info;
    
    @Column(nullable=false)
    private String username;

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((task != null) ? task.hashCode() : 0);
        hash += ((scheduledDate != null) ? scheduledDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try {
            CompleteInfo other = (CompleteInfo)object;
            return other.task.equals(task) &&
                    other.scheduledDate.equals(scheduledDate) &&
                    other.completeTime.equals(completeTime) &&
                    other.info.equals(info) &&
                    other.username.equals(username);
        } catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "com.artechnologiesllc.taskcompleter.domain.CompleteInfo[ task="
                + task.toString() + ", scheduledDate=" + scheduledDate.toString() + " ]";
    }
    
}
