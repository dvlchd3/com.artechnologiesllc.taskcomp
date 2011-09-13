package com.artechnologiesllc.tc.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * <p>Represents a <code>Task</code> that needs to be completed. This
 * class also contains the repeat information that indicates when
 * the task should repeat.</p>
 * 
 * <h3>Input Type</h3>
 * <p>There are currently 4 input types: CheckBox, Time Entry,
 * Multi-Time Entry, and Text Entry (catch-all).  The
 * <code>inputType</code> property contains a numeric value
 * representing the type of input required to complete this
 * <code>Task</code>.
 * <table>
 *  <tr>
 *      <th><code>inputType</code></th>
 *      <th>Type</th>
 *  </tr>
 *  <tr>
 *      <td>1</td>
 *      <td>CheckBox</td>
 *  </tr>
 *  <tr>
 *      <td>2</td>
 *      <td>Time Entry</td>
 *  </tr>
 *  <tr>
 *      <td>3</td>
 *      <td>Multi-Time Entry</td>
 *  </tr>
 *  <tr>
 *      <td>4</td>
 *      <td>Text Entry</td>
 *  </tr>
 * </table>
 * </p>
 * 
 * <h3>Repeat Information</h3>
 * <p>The repeat information consists of 5 properties:
 *  <ol>
 *      <li><code>startDate</code></li>
 *      <li><code>endDate</code></li>
 *      <li><code>days</code></li>
 *      <li><code>weeks</code></li>
 *      <li><code>months</code></li>
 *  </ol>
 * The <code>startDate</code> and <code>endDate</code> dictates
 * the range of dates the <code>Task</code> should be displayed
 * for completion.</p>
 * 
 * <h4>Days, Weeks, Months</h4>
 * <p>The <code>days</code>, <code>weeks</code>, and <code>months</code>
 * properties refer to when the <code>Task</code> should repeat throughout
 * the date range. The <code>days</code> property dictates which day
 * of the week the <code>Task</code> should be displayed for completion.
 * The <code>week</code> and <code>months</code> property dictates which
 * week of the month and month of the year (respectively) the
 * <code>Task</code> should be displayed for completion.</p>
 * <p>The strategy for the 3 properties is as follows: Each bit of
 * the binary representation of the number corresponds to a single day,
 * week, or month. The least significant bit is ignored, then the next
 * significant bit starts the mapping.</p>
 * <p><strong><code>days</code> Mapping</strong>
 * <table>
 *  <tr>
 *      <th>Bit</th>
 *      <td>1-8</td>
 *      <td>9</td>
 *      <td>10</td>
 *      <td>11</td>
 *      <td>12</td>
 *      <td>13</td>
 *      <td>14</td>
 *      <td>15</td>
 *      <td>16</td>
 *  </tr>
 *  <tr>
 *      <th>Value</th>
 *      <td>N/A</td>
 *      <td>Sat.</td>
 *      <td>Fri.</td>
 *      <td>Thur.</td>
 *      <td>Wed.</td>
 *      <td>Tue.</td>
 *      <td>Mon.</td>
 *      <td>Sun.</td>
 *      <td>N/A</td>
 *  </tr>
 * </table></p>
 * <p><strong><code>weeks</code> Mapping</strong>
 * <table>
 *  <tr>
 *      <th>Bit</th>
 *      <td>1-2</td>
 *      <td>3</td>
 *      <td>4</td>
 *      <td>5</td>
 *      <td>6</td>
 *      <td>7</td>
 *      <td>8</td>
 *  </tr>
 *  <tr>
 *      <th>Value</th>
 *      <td>N/A</td>
 *      <td>Week 5</td>
 *      <td>Week 4</td>
 *      <td>Week 3</td>
 *      <td>Week 2</td>
 *      <td>Week 1</td>
 *      <td>N/A</td>
 *  </tr>
 * </table></p>
 * <p><strong><code>months</code> Mapping</strong>
 * <table>
 *  <tr>
 *      <th>Bit</th>
 *      <td>1-3</td>
 *      <td>4</td>
 *      <td>5</td>
 *      <td>6</td>
 *      <td>7</td>
 *      <td>8</td>
 *      <td>9</td>
 *      <td>10</td>
 *      <td>11</td>
 *      <td>12</td>
 *      <td>13</td>
 *      <td>14</td>
 *      <td>15</td>
 *      <td>16</td>
 *  </tr>
 *  <tr>
 *      <th>Value</th>
 *      <td>N/A</td>
 *      <td>Dec.</td>
 *      <td>Nov.</td>
 *      <td>Oct.</td>
 *      <td>Sep.</td>
 *      <td>Aug.</td>
 *      <td>Jul.</td>
 *      <td>Jun.</td>
 *      <td>May</td>
 *      <td>Apr.</td>
 *      <td>Mar.</td>
 *      <td>Feb.</td>
 *      <td>Jan.</td>
 *      <td>N/A</td>
 *  </tr>
 * </table></p>
 * 
 * @author Dan Buhrman
 */
@Entity
@Table(name="Task")
public class Task implements Serializable {
    
    /**
     * Unique ID for the <code>Task</code>.
     */
    private Integer id;
    /**
     * Title of the <code>Task</code>.
     */
    private String title;
    /**
     * Additional details.
     */
    private String details;
    /**
     * Scheduled time.
     */
    private Date schTime;
    /**
     * Path to documentation file.
     */
    private String doc;
    /**
     * Input type required to complete this <code>Task</code>.
     */
    private byte inputType;
    /**
     * Problem instance to follow if there is a problem.
     */
    private Problem problem;
    /**
     * Starting date.
     */
    private Date startDate;
    /**
     * Ending date.
     */
    private Date endDate;
    /**
     * Days to complete on.
     */
    private Short days;
    /**
     * Weeks to complete on.
     */
    private Byte weeks;
    /**
     * Months to complete on.
     */
    private Short months;

    /**
     * Gets the unique ID for the <code>Task</code>.
     * @return Unique ID.
     */
    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    /**
     * Registers the unique ID for the 
     * <code>Task</code>.&nbsp;This is meant to be set
     * by the persistence provider.
     * 
     * @param id Unique ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Gets the title for the <code>Task</code>.
     * @return <code>Task</code> title.
     */
    @Column(name="title", nullable=false, length=50)
    public String getTitle() {
        return title;
    }
    /**
     * Registers the title for the <code>Task</code>.
     * @param title <code>Task</code> title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the additional details for the <code>Task</code>.
     * @return Additional details.
     */
    @Column(name="details", length=150)
    public String getDetails() {
        return details;
    }
    /**
     * Registers the additional details for the <code>Task</code>.
     * @param details Additional details.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the scheduled time for the <code>Task</code>.
     * @return Scheduled time.
     */
    @Column(name="scheduled_time")
    @Temporal(TemporalType.TIME)
    public Date getSchTime() {
        return schTime;
    }
    /**
     * Registers the scheduled time for the <code>Task</code>.
     * @param schTime Scheduled time.
     */
    public void setSchTime(Date schTime) {
        this.schTime = schTime;
    }

    /**
     * Gets the path to the documentation file.
     * @return Path to documentation file.
     */
    @Column(name="documentation")
    public String getDoc() {
        return doc;
    }
    /**
     * Registers the path to the documentation file.
     * @param doc Path to documentation file.
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * Gets the type of input required to complete the
     * <code>Task</code>.
     * @return Input type.
     */
    @Column(name="input_type", nullable=false)
    public byte getInputType() {
        return inputType;
    }
    /**
     * Registers the type of input required to complete
     * the <code>Task</code>.
     * @param inputType Input type.
     */
    public void setInputType(byte inputType) {
        this.inputType = inputType;
    }

    /**
     * Gets the <code>Problem</code> instance to follow if
     * there is a problem completing the <code>Task</code>.
     * @return <code>Problem</code> to follow.
     */
    @ManyToOne
    @JoinColumn(name="problem_id", updatable=false)
    public Problem getProblem() {
        return problem;
    }
    /**
     * Registers the <code>Problem</code> instance to follow if
     * there is a problem completing the <code>Task</code>.
     * @param problem <code>Problem</code> to follow.
     */
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    /**
     * Gets the starting date for the <code>Task</code>.
     * @return Starting date.
     */
    @Column(name="start_date")
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Registers a starting date for the <code>Task</code>.
     * @param startDate Starting date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the ending date for the <code>Task</code>.
     * @return Ending date.
     */
    @Column(name="end_date")
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }
    /**
     * Registers a ending date for the <code>Task</code>.
     * @param endDate Ending date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Gets the days the <code>Task</code> should be displayed
     * for completion.
     * @return Days.
     */
    @Column(name="days")
    public Short getDays() {
        return days;
    }
    /**
     * Registers the days the <code>Task</code> should be displayed
     * for completion.
     * @param days Days.
     */
    public void setDays(Short days) {
        this.days = days;
    }

    /**
     * Gets the weeks the <code>Task</code> should be displayed
     * for completion.
     * @return Weeks.
     */
    @Column(name="weeks")
    public Byte getWeeks() {
        return weeks;
    }
    /**
     * Registers the weeks the <code>Task</code> should be displayed
     * for completion.
     * @param weeks Weeks.
     */
    public void setWeeks(Byte weeks) {
        this.weeks = weeks;
    }
    
    /**
     * Gets the months the <code>Task</code> should be displayed
     * for completion.
     * @return Months.
     */
    @Column(name="months")
    public Short getMonths() {
        return months;
    }
    /**
     * Registers the months the <code>Task</code> should be displayed
     * for completion.
     * @param months Months.
     */
    public void setMonths(Short months) {
        this.months = months;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            Task other = (Task)obj;
            if(other.getId() != null && getId() != null)
                return other.getId().equals(getId());
            else
                return  other.getTitle().equals(getTitle()) &&
                        other.getDetails().equals(getDetails()) &&
                        other.getSchTime().equals(getSchTime()) &&
                        other.getDoc().equals(getDoc()) &&
                        other.getInputType() == getInputType() &&
                        other.getProblem().equals(getProblem()) &&
                        other.getStartDate().equals(getStartDate()) &&
                        other.getEndDate().equals(getEndDate()) &&
                        other.getDays().equals(getDays()) &&
                        other.getWeeks().equals(getWeeks()) &&
                        other.getMonths().equals(getMonths());
        } catch(ClassCastException e) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() == null) ? 0 : getId();
        return hash;
    }
    
    @Override
    public String toString() {
        return "Task [" + getId() + "] " + getTitle();
    }
}
