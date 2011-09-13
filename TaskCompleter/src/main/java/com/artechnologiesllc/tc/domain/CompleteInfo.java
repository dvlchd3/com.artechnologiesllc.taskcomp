/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Represents the completion information for a <code>Task</code>.
 * This is a database entity mapped to the <code>CompleteInfo</code>
 * database table.
 * 
 * @author Dan Buhrman
 * 
 * @see Task
 */
@Entity
@Table(name="CompleteInfo")
public class CompleteInfo implements Serializable {
    
    /**
     * The <code>Task</code> that was completed.
     */
    private Task task;
    /**
     * The date the task was scheduled for completion.
     */
    private Date schDate;
    /**
     * The time the task was completed.
     */
    private Date compTime;
    /**
     * The information used to complete the task.
     */
    private String info;
    /**
     * The user who completed the task.
     */
    private String user;

    /**
     * <p>Gets the <code>Task</code> that was completed.</p>
     * 
     * <p>This is a Many-To-One relationship with the 
     * <code>Task</code> table and serves as part of the
     * composite primary key.</p>
     * 
     * @return <code>Task</code> that was completed.
     */
    @Id
    @ManyToOne
    @JoinColumn(name="task_id", nullable=false, updatable=false, insertable=false)
    public Task getTask() {
        return task;
    }
    /**
     * Registers the <code>Task</code> that was completed.
     * @param task Completed <code>Task</code>.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * <p>Gets the date the <code>Task</code> was scheduled
     * for completion.</p>
     * 
     * <p>This serves as part of the composite primary key</p>
     * @return Scheduled date for the <code>Task</code>
     */
    @Id
    @Column(name="scheduled_date")
    @Temporal(TemporalType.DATE)
    public Date getSchDate() {
        return schDate;
    }
    /**
     * Registers the date the <code>Task</code> was scheduled
     * for completion.
     * 
     * @param schDate Scheduled date for the <code>Task</code>.
     */
    public void setSchDate(Date schDate) {
        this.schDate = schDate;
    }

    /**
     * Gets the timestamp the <code>Task</code> was completed.
     * @return Time the <code>Task</code> was completed.
     */
    @Column(name="completed_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCompTime() {
        return compTime;
    }
    /**
     * Registers the time the <code>Task</code> was completed.
     * @param compTime Time <code>Task</code> was completed.
     */
    public void setCompTime(Date compTime) {
        this.compTime = compTime;
    }

    /**
     * <p>Gets the information used to complete the <code>Task</code>.</p>
     * 
     * <p>This property correlates to the <code>Task</code>s
     * <code>inputType</code>:
     * <table>
     *  <tr>
     *      <th>Input Type</th>
     *      <th>Input Name</th>
     *      <th>Expected form</th>
     *  </tr>
     *  <tr>
     *      <td>1</td>
     *      <td>CheckBox</td>
     *      <td>True|False</th>
     *  </tr>
     *  <tr>
     *      <td>2</td>
     *      <td>Time</td>
     *      <td>HH:mm</td>
     *  </tr>
     *  <tr>
     *      <td>3</td>
     *      <td>Multi-Time</td>
     *      <td>HH:mm HH:mm</td>
     *  </tr>
     *  <tr>
     *      <td>4</td>
     *      <td>Text</td>
     *      <td>[value]</td>
     *  </tr>
     *  <tr>
     *      <td>N/A</td>
     *      <td>Problem</td>
     *      <td>Case ######</td>
     *  </tr>
     * </table>
     * </p>
     * @return Completion information.
     */
    @Column(name="complete_info", length=15)
    public String getInfo() {
        return info;
    }
    /**
     * Registers the completion information for
     * the <code>Task</code>.
     * @param info Completion information.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Gets the username of the user who completed
     * the <code>Task</code>.
     * @return Completing username.
     */
    @Column(name="complete_user", length=15)
    public String getUser() {
        return user;
    }
    /**
     * Registers the username of the user who completed
     * the <code>Task</code>.
     * @param user Completing username.
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            CompleteInfo other = (CompleteInfo)obj;
            return  other.getTask().equals(getTask()) &&
                    other.getSchDate().equals(getSchDate());
        } catch(ClassCastException e) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getTask() != null) ? 0 : getTask().hashCode();
        hash += (getSchDate() != null) ? 0 : getSchDate().hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("CompleteInfo[");
        builder.append(getTask().toString());
        builder.append(" Scheduled: ");
        builder.append(new java.text.SimpleDateFormat("dd/MM/yyyy").format(getSchDate()));
        builder.append("] ");
        builder.append(getInfo());
        return builder.toString();
    }
}
