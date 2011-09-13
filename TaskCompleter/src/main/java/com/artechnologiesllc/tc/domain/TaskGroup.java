package com.artechnologiesllc.tc.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 * <p>Represents a group of <code>Task</code>s to complete. This class
 * serves as a container for a set of <code>Task</code>s.</p>
 * 
 * <p>This is an entity class that maps to the <code>TaskGroup</code>
 * table in the database. It also specifies the join table
 * <code>Task_TaskGroup</code> used as the unidirectional many-to-many
 * relationship with the <code>Task</code> table.</p>
 * 
 * @author Dan Buhrman
 * 
 * @see Task
 */
@Entity
@Table(name="TaskGroup")
public class TaskGroup implements Serializable {
    
    /**
     * The unique name of the group.
     */
    private String name;
    /**
     * The displayable name of the group.
     */
    private String displayName;
    /**
     * The list of <code>Task<code>s contained in this group.
     */
    private List<Task> tasks;

    /**
     * Gets the unique name of this group.
     * @return Unique name.
     */
    @Id
    @Column(name="group_name", length=20)
    public String getName() {
        return name;
    }
    /**
     * Registers the unique name of this group.
     * @param name Unique name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the displayable name of this group.
     * @return Display name.
     */
    @Column(name="display_name", length=20)
    public String getDisplayName() {
        return displayName;
    }
    /**
     * Registers the displayable name of this group.
     * @param displayName Display name.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Gets the list of <code>Task</code>s contained in
     * this group.
     * @return List of <code>Task</code>s.
     */
    @ManyToMany
    @OrderColumn(name="order_num", nullable=false)
    @JoinTable(name="Task_TaskGroup",
            joinColumns={@JoinColumn(name="group_name", nullable=false)},
            inverseJoinColumns={@JoinColumn(name="task_id", nullable=false)})
    public List<Task> getTasks() {
        return tasks;
    }
    /**
     * Registers the list of <code>Task</code>s contained in
     * this group.
     * @param tasks List of <code>Task</code>s.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            TaskGroup other = (TaskGroup)obj;
            return other.getName().equals(getName());
        } catch(ClassCastException e) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += getName().hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        return "TaskGroup [" + getName() + "] " + getDisplayName();
    }
    
}
