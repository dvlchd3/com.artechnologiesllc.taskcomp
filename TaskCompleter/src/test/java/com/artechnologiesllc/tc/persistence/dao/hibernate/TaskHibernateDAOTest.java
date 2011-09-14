/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import org.junit.Ignore;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.artechnologiesllc.tc.persistence.dao.TaskDAO;
import java.util.Date;
import java.sql.Connection;
import com.artechnologiesllc.tc.domain.Task;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dan
 */
public class TaskHibernateDAOTest {
    
    private static Connection connection;

    @BeforeClass
    public static void generateTaskTestData() throws Exception {
        connection = TestConnectionHelper.getConnection();
        TestConnectionHelper.generateTestData();
    }

    /**
     * Test of persist method, of class TaskHibernateDAO.
     */
    @Test
    public void testPersist() throws Exception {
        String title = "Create Task";
        String details = "Create Details";
        Date schTime = new SimpleDateFormat("HH:mm").parse("05:00");
        String doc = "Create Doc";
        Task task = new Task();
        task.setTitle(title);
        task.setDetails(details);
        task.setSchTime(schTime);
        task.setDoc(doc);
        
        TaskDAO dao = (TaskDAO) TestPersistenceContext.getInstance().getBean("taskDAO");
        Task perTask = dao.persist(task);
        
        assertNotNull("Id", perTask.getId());
        assertEquals("Title", title, perTask.getTitle());
        assertEquals("Details", details, perTask.getDetails());
        assertEquals("SchTime", schTime, perTask.getSchTime());
        assertEquals("Doc", doc, perTask.getDoc());
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Task WHERE task_id=?");
        stmt.setInt(1, perTask.getId());
        ResultSet results = stmt.executeQuery();
        
        assertTrue("Exists in DB", results.next());
        
        assertEquals("DB Title", title, results.getString("title"));
        assertEquals("DB Details", details, results.getString("details"));
        assertEquals("DB SchTime", schTime.getTime(), results.getTime("schTime").getTime());
        assertEquals("DB Doc", doc, results.getString("documentation"));
    }

    /**
     * Test of delete method, of class TaskHibernateDAO.
     */
    @Test
    @Ignore
    public void testDelete() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setTitle("Delete Test Task");
        task.setDetails("Delete Test Details");
        task.setDoc("Delete Doc File");
        task.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse("2011-08-29"));
        task.setDays((short)1);
        task.setWeeks((byte)1);
        task.setMonths((short)1);
        
        TaskDAO dao = (TaskDAO)TestPersistenceContext.getInstance().getBean("taskDAO");
        dao.delete(task);
        
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Task WHERE task_id=?");
            statement.setLong(1, 1L);
            results = statement.executeQuery();
            
            assertFalse(results.next());
        } finally {
            if(statement != null)
                statement.close();
            
            if(results != null)
                results.close();
        }
    }

    /**
     * Test of getTask method, of class TaskHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetTask() throws Exception {        
        TaskDAO dao = (TaskDAO)TestPersistenceContext.getInstance().getBean("taskDAO");
        
        Task result = dao.getTask(2);
        assertNotNull(result);
        
        assertEquals("Title", "Get Test Task", result.getTitle());
        assertEquals("Details", "Get Test Details", result.getDetails());
        assertEquals("Doc File", "Get Doc File", result.getDoc());
        assertEquals("Input Type", (byte)1, result.getInputType());
        assertEquals("Start Date", new SimpleDateFormat("yyyy-MM-dd").parse("2011-08-29"), result.getStartDate());
        assertEquals("Days", 1, (short)result.getDays());
        assertEquals("Weeks", 1, (byte)result.getWeeks());
        assertEquals("Months", 1, (short)result.getMonths());
    }
}
