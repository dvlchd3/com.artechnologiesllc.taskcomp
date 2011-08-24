/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.taskcompleter.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author dan
 */
@Entity
public class TaskGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="GROUP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String name;
    
    @ManyToMany
    @IndexColumn(name="ORDER_NUM")
    @JoinTable(name="TASKGROUP_TO_TASK",
            joinColumns=@JoinColumn(name="TASK_ID"),
            inverseJoinColumns=@JoinColumn(name="GROUP_ID"))
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
            TaskGroup other = (TaskGroup)object;
            if(other.id != null && id != null)
                return other.id.equals(id);
            else
                return other.name.equals(name) &&
                        other.tasks.equals(tasks);
        } catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "com.artechnologiesllc.taskcompleter.domain.TaskGroup[ id=" + id + " ]";
    }
    
}
