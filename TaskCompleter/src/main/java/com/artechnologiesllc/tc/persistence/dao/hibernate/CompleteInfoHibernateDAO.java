/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import com.artechnologiesllc.tc.domain.CompleteInfo;
import com.artechnologiesllc.tc.domain.Task;
import com.artechnologiesllc.tc.persistence.dao.CompleteInfoDAO;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author dan
 */
public class CompleteInfoHibernateDAO extends HibernateDaoSupport implements CompleteInfoDAO {

    private static final Logger logger = Logger.getLogger(CompleteInfoHibernateDAO.class);
    
    public CompleteInfo persist(CompleteInfo compInfo) {
        logger.debug("Persisting " + compInfo.toString());
        
        return (CompleteInfo) getHibernateTemplate().merge(compInfo);
    }

    public void delete(CompleteInfo compInfo) {
        logger.debug("Deleting " + compInfo.toString());
        
        getHibernateTemplate().delete(compInfo);
    }

    public CompleteInfo getCompleteInfo(Task task, Date schDate) {
        logger.debug("Finding CompleteInfo by task: " + task.toString() + " and Scheduled Date " + schDate.toString());
        
        List result = getHibernateTemplate().findByNamedQuery(
                "com.artechnologiesllc.tc.completeInfoByTaskSchDate",
                new Object[]{task, schDate});
        if(result.size() <= 0) {
            logger.debug("No match found.");
            return null;
        } else {
            logger.debug("Match found.");
            return (CompleteInfo)result.get(0);
        }
    }

    public List<CompleteInfo> getCompleteInfo(Task task) {
        logger.debug("Finding CompleteInfo by task: " + task.toString());
        
        return (List<CompleteInfo>) getHibernateTemplate().findByNamedQuery(
                                        "com.artechnologiesllc.tc.completeInfoByTask",
                                        task);
    }

    public List<CompleteInfo> getCompleteInfo(Date schDate) {
        logger.debug("Finding CompleteInfo by scheduled date: " + schDate.toString());
        
        return (List<CompleteInfo>) getHibernateTemplate().findByNamedQuery(
                                        "com.artechnologiesllc.tc.completeInfoByScheduledDate",
                                        schDate);
    }

    public List<CompleteInfo> getCompleteInfo(Task task, Date startSchDate, Date endSchDate) {
        logger.debug("Finding CompleteInfo by task: " 
                + task.toString() + " and scheduled date range start: "
                + startSchDate.toString() + " end: " + endSchDate.toString());
        
        return (List<CompleteInfo>) getHibernateTemplate().findByNamedQuery(
                                        "com.artechnologiesllc.tc.completeInfoByTaskScheduledDateRange",
                                        new Object[]{task, startSchDate, endSchDate});
    }
    
}
