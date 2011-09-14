/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import com.artechnologiesllc.tc.domain.Problem;
import com.artechnologiesllc.tc.persistence.dao.ProblemDAO;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author dan
 */
public class ProblemHibernateDAO extends HibernateDaoSupport implements ProblemDAO {
    
    private static final Logger logger = Logger.getLogger(ProblemHibernateDAO.class);

    @Override
    public Problem persist(Problem problem) {
        logger.debug("Persisting " + problem.toString());
        
        return (Problem) getHibernateTemplate().merge(problem);
    }

    public void delete(Problem problem) {
        logger.debug("Deleting " + problem.toString());
        getHibernateTemplate().delete(problem);
    }

    public Problem getProblem(int problemid) {
        logger.debug("Finding problem by id: " + (long)problemid);
        
        List result = getHibernateTemplate().findByNamedQuery("com.artechnologiesllc.tc.problemById", problemid);
        if(result.size() <= 0) {
            logger.debug("No match found.");
            return null;
        } else {
            logger.debug("Match found.");
            return (Problem)result.get(0);
        }
    }
    
}
