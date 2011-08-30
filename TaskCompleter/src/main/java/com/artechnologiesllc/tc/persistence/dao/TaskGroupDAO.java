/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao;

import com.artechnologiesllc.tc.domain.TaskGroup;

/**
 * Provides persistence operations for the <code>TaskGroup</code> object.
 *
 * @author Dan Buhrman
 * 
 * @see com.artechnologiesllc.tc.domain.TaskGroup
 */
public interface TaskGroupDAO {
    
    /**
     * Persists a <code>TaskGroup</code> instance
     * (create or update) depending on the value of
     * the id;
     * @param group <code>TaskGroup</code> to persist.
     * @return Persisted instance.
     */
    public TaskGroup persist(TaskGroup group);
    
    /**
     * Removes a <code>TaskGroup</code> form the database.
     * @param group <code>TaskGroup</code> to remove.
     */
    public void delete(TaskGroup group);
    
    /**
     * Retrieves a <code>TaskGroup</code> object from the
     * database based on its unique id.
     * @param id Unique Id.
     * @return <code>TaskGroup</code> instance from the database
     *          or <code>null</code> if none exists.
     */
    public TaskGroup getTaskGroup(Long id);
    
    /**
     * Retrieves a <code>TaskGroup</code> object from the
     * database based on its unique name.
     * @param name Unique name.
     * @return <code>TaskGroup</code> instance from the database
     *          or <code>null</code> if none exists.
     */
    public TaskGroup getTaskGroup(String name);
}
