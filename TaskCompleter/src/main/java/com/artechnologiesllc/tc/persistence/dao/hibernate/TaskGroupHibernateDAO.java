/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import com.artechnologiesllc.tc.domain.TaskGroup;
import com.artechnologiesllc.tc.persistence.dao.TaskGroupDAO;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author dan
 */
public class TaskGroupHibernateDAO extends HibernateDaoSupport implements TaskGroupDAO {

    private static final Logger logger = Logger.getLogger(TaskGroupHibernateDAO.class);
    
    public TaskGroup persist(TaskGroup group) {
        logger.debug("Persisting " + group.toString());
        
        return (TaskGroup) getHibernateTemplate().merge(group);
    }

    public void delete(TaskGroup group) {
        logger.debug("Deleting " + group.toString());
        
        getHibernateTemplate().delete(group);
    }

    public TaskGroup getTaskGroup(Long id) {
        logger.debug("Finding TaskGroup with id " + (long)id);
        
        List<TaskGroup> result = (List<TaskGroup>) getHibernateTemplate().findByNamedQuery(
                                                    "com.artechnologiesllc.tc.taskGroupById",
                                                    id);
        if(result.isEmpty())
            return null;
        else
            return result.get(0);
        
    }

    public TaskGroup getTaskGroup(String name) {
        logger.debug("Finding TaskGroup with name " + name);
        
        List<TaskGroup> result = (List<TaskGroup>) getHibernateTemplate().findByNamedQuery(
                                                    "com.artechnologiesllc.tc.taskGroupByName",
                                                    name);
        if(result.isEmpty())
            return null;
        else
            return result.get(0);
    }
    
}
