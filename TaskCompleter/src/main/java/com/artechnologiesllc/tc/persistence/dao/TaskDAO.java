/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao;

import com.artechnologiesllc.tc.domain.Task;

/**
 * Provides persistence operations for the <code>Task</code> object.
 * 
 * @author dan
 * 
 * @see com.artechnologiesllc.taskcompleter.domain.Task
 */
public interface TaskDAO {
    
    /**
     * Persists a <code>Task</code> instance
     * (create or update) depending on the value
     * of the id.
     * @param task <code>Task</code> to persist.
     * @return Persisted instance.
     */
    public Task persist(Task task);
    
    /**
     * Removes a <code>Task</code> from the database.
     * @param task <code>Task</code> to remove.
     */
    public void delete(Task task);
    
    /**
     * Retrieves a <code>Task</code> from the database
     * by its unique id.
     * @param taskid Unique id of the <code>Task</code>
     *                  to retrieve.
     * @return <code>Task</code> instance from the database or
     *          <code>null</code> if id cannot be found.
     */
    public Task getTask(Long taskid);
}
