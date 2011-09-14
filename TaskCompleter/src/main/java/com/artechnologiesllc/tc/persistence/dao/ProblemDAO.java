/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao;

import com.artechnologiesllc.tc.domain.Problem;

/**
 * Provides persistence operations for the <code>Problem</code> object.
 * 
 * @author Dan Buhrman
 * 
 * @see com.artechnologiesllc.tc.domain.Problem
 */
public interface ProblemDAO {
    
    /**
     * Persists a <code>Problem</code> instance
     * (create or update) depending on the value
     * of the id.
     * @param problem <code>Problem</code> to persist.
     * @return Persisted instance.
     */
    public Problem persist(Problem problem);
    
    /**
     * Removes a <code>Problem</code> from the database.
     * @param problem <code>Problem</code> to remove.
     */
    public void delete(Problem problem);
    
    /**
     * Retrieves a <code>Problem</code> from the database
     * by its unique id.
     * @param problemid Unique id of the <code>Problem</code>
     *                  to retrieve.
     * @return <code>Problem</code> instance from the database or
     *          <code>null</code> if id cannot be found.
     */
    public Problem getProblem(int problemid);
}
