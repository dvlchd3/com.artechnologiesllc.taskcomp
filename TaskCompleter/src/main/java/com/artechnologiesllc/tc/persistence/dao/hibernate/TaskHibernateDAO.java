/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import com.artechnologiesllc.tc.domain.Task;
import com.artechnologiesllc.tc.persistence.dao.TaskDAO;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author dan
 */
public class TaskHibernateDAO extends HibernateDaoSupport implements TaskDAO {

    private static final Logger logger = Logger.getLogger(TaskHibernateDAO.class);
    
    public Task persist(Task task) {
        logger.debug("Persisting " + task.toString());
        
        return (Task) getHibernateTemplate().merge(task);
    }

    public void delete(Task task) {
        logger.debug("Deleting " + task.toString());
        
        getHibernateTemplate().delete(task);
    }

    public Task getTask(int taskid) {
        logger.debug("Finding task by id: " + taskid);
        
        List result = getHibernateTemplate().findByNamedQuery("com.artechnologiesllc.tc.taskById", taskid);
        if(result.size() <= 0) {
            logger.debug("No match found.");
            return null;
        } else {
            logger.debug("Match found.");
            return (Task)result.get(0);
        }
    }
    
}
