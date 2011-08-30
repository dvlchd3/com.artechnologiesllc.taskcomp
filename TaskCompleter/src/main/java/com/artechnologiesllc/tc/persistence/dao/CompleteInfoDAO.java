/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao;

import com.artechnologiesllc.tc.domain.CompleteInfo;
import com.artechnologiesllc.tc.domain.Task;
import java.util.Date;
import java.util.List;

/**
 * Provides persistence operations for the <code>CompleteInfo</code> object.
 * 
 * @author dan
 * 
 * @see com.artechnologiesllc.tc.domain.CompleteInfo
 */
public interface CompleteInfoDAO {
    
    /**
     * Persists a <code>CompleteInfo</code> instance
     * (create or update) depending on the value of
     * the id.
     * @param compInfo <code>CompleteInfo</code> to persist.
     * @return Persisted instance.
     */
    public CompleteInfo persist(CompleteInfo compInfo);
    
    /**
     * Removes a <code>CompleteInfo</code> from the database.
     * @param compInfo <code>CompleteInfo</code> to remove.
     */
    public void delete(CompleteInfo compInfo);
    
    /**
     * Retrieves a <code>CompleteInfo</code> object from the database
     * based on its <code>Task</code> and scheduled date.
     * @param task <code>Task</code> of the object to retrieve.
     * @param schDate Scheduled date of the object to retrieve.
     * @return <code>CompleteInfo</code> instance from the database
     *          or <code>null</code> if none exists in the database.
     * 
     */
    public CompleteInfo getCompleteInfo(Task task, Date schDate);
    
    /**
     * Retrieves a list of <code>CompleteInfo</code> objects from the
     * database based on the <code>Task</code>.
     * @param task <code>Task</code> of the objects to retrieve.
     * @return List of <code>CompleteInfo</code> instances from the
     *          database.
     */
    public List<CompleteInfo> getCompleteInfo(Task task);
    
    /**
     * Retrieves a list of <code>CompleteInfo</code> objects from the
     * database based on the scheduled date.
     * @param schDate Scheduled date of the objects to retrieve.
     * @return List of <code>CompleteInfo</code> instances from the
     *          database.
     */
    public List<CompleteInfo> getCompleteInfo(Date schDate);
    
    /**
     * Retrieves a list of <code>CompleteInfo</code> objects from the
     * database based on the <code>Task</code> and range of scheduled
     * dates.
     * @param task <code>Task</code> of objects to retrieve.
     * @param startSchDate Starting bound of the scheduled date range.
     * @param endSchDate Ending bound of the scheduled date range.
     * @return List of <code>CompleteInfo</code> instances from the
     *          database.
     */
    public List<CompleteInfo> getCompleteInfo(Task task, Date startSchDate, Date endSchDate);
}
