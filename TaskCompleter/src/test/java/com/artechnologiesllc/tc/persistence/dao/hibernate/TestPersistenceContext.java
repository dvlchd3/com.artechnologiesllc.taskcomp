/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author dan
 */
public class TestPersistenceContext extends ClassPathXmlApplicationContext {
    
    private static final String FILE_NAME = "persistenceTestContext.xml";
    private static ApplicationContext instance;
    
    protected TestPersistenceContext(){
        super(FILE_NAME);
    }
    
    public static ApplicationContext getInstance() {
        if(instance == null)
            instance = new TestPersistenceContext();
        
        return instance;
    }
}
