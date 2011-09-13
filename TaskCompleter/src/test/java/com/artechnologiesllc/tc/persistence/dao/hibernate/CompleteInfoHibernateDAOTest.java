/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import org.junit.Ignore;
import java.sql.Statement;
import javax.sql.DataSource;
import java.sql.Connection;
import com.artechnologiesllc.tc.domain.CompleteInfo;
import com.artechnologiesllc.tc.domain.Task;
import java.util.Date;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dan
 */
public class CompleteInfoHibernateDAOTest {
    
    private static Connection connection;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        connection = TestConnectionHelper.getConnection();
        TestConnectionHelper.generateTestData();
    }

    /**
     * Test of persist method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testPersist() {
        
    }

    /**
     * Test of delete method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testDelete() {
        System.out.println("delete");
        CompleteInfo compInfo = null;
        CompleteInfoHibernateDAO instance = new CompleteInfoHibernateDAO();
        instance.delete(compInfo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteInfo method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetCompleteInfo_Task_Date() {
        System.out.println("getCompleteInfo");
        Task task = null;
        Date schDate = null;
        CompleteInfoHibernateDAO instance = new CompleteInfoHibernateDAO();
        CompleteInfo expResult = null;
        CompleteInfo result = instance.getCompleteInfo(task, schDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteInfo method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetCompleteInfo_Task() {
        System.out.println("getCompleteInfo");
        Task task = null;
        CompleteInfoHibernateDAO instance = new CompleteInfoHibernateDAO();
        List expResult = null;
        List result = instance.getCompleteInfo(task);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteInfo method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetCompleteInfo_Date() {
        System.out.println("getCompleteInfo");
        Date schDate = null;
        CompleteInfoHibernateDAO instance = new CompleteInfoHibernateDAO();
        List expResult = null;
        List result = instance.getCompleteInfo(schDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompleteInfo method, of class CompleteInfoHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetCompleteInfo_3args() {
        System.out.println("getCompleteInfo");
        Task task = null;
        Date startSchDate = null;
        Date endSchDate = null;
        CompleteInfoHibernateDAO instance = new CompleteInfoHibernateDAO();
        List expResult = null;
        List result = instance.getCompleteInfo(task, startSchDate, endSchDate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
