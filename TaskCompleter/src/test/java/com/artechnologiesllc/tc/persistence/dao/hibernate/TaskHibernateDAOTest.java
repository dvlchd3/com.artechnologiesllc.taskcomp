/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.artechnologiesllc.tc.persistence.dao.TaskDAO;
import java.util.Date;
import java.sql.Statement;
import javax.sql.DataSource;
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
        connection = ((DataSource)TestPersistenceContext.getInstance().getBean("localDataSource")).getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            
            statement.addBatch("INSERT INTO Task(task_id, title, details, documentationFile, inputType, startDate, days, weeks, months) " +
                    "VALUES('1', 'Delete Test Task', 'Delete Test Details', 'Delete Doc File', '1', '2011-08-29', '1', '1', '1');");
            
            statement.addBatch("INSERT INTO Task(task_id, title, details, documentationFile, inputType, startDate, days, weeks, months) " +
                    "VALUES('2', 'Get Test Task', 'Get Test Details', 'Get Doc File', '1', '2011-08-29', '1', '1', '1');");
            
            statement.executeBatch();
        } finally {
            statement.close();
        }
    }

    /**
     * Test of persist method, of class TaskHibernateDAO.
     */
    @Test
    public void testPersist() throws Exception {
        String title = "Persisted Task 1";
        String details = "Persisted Details 1";
        String documentationFile = "Persisted Doc 1";
        byte inputType = 1;
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-08-29");
        Date endDate = null;
        short days = 1;
        byte weeks = 1;
        short months = 1;
        Task task = new Task();
        task.setTitle(title);
        task.setDetails(details);
        task.setDocumentationFile(documentationFile);
        task.setInputType(inputType);
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setDays(days);
        task.setWeeks(weeks);
        task.setMonths(months);
        
        TaskDAO dao = (TaskDAO)TestPersistenceContext.getInstance().getBean("taskDAO");
        
        Task persistedTask = dao.persist(task);
        assertNotNull("Id", persistedTask.getId());
        assertEquals("Title", title, persistedTask.getTitle());
        assertEquals("Details", details, persistedTask.getDetails());
        assertEquals("Documentation File", documentationFile, persistedTask.getDocumentationFile());
        assertEquals("Input Type", inputType, persistedTask.getInputType());
        assertNull("Problem", persistedTask.getProblem());
        assertEquals("Start Date", startDate, persistedTask.getStartDate());
        assertEquals("End Date", endDate, persistedTask.getEndDate());
        assertEquals("Days", days, persistedTask.getDays());
        assertEquals("Weeks", weeks, persistedTask.getWeeks());
        assertEquals("Months", months, persistedTask.getMonths());
        
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Task WHERE task_id=?");
            statement.setLong(1, persistedTask.getId());
            results = statement.executeQuery();
            results.next();
            
            assertEquals("Database title", title, results.getString("title"));
            assertEquals("Database details", details, results.getString("details"));
            assertEquals("Database Documentation File", documentationFile, results.getString("documentationFile"));
            assertEquals("Database Input Type", inputType, results.getByte("inputType"));
            assertEquals("Database Start Date", startDate, results.getDate("startDate"));
            assertEquals("Database End Date", endDate, results.getDate("endDate"));
            assertEquals("Database Days", days, results.getShort("days"));
            assertEquals("Database Weeks", weeks, results.getByte("weeks"));
            assertEquals("Database Months", months, results.getShort("months"));
        } finally {
            if(statement != null)
                statement.close();
            
            if(results != null)
                results.close();
        }
    }

    /**
     * Test of delete method, of class TaskHibernateDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Delete Test Task");
        task.setDetails("Delete Test Details");
        task.setDocumentationFile("Delete Doc File");
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
    public void testGetTask() throws Exception {        
        TaskDAO dao = (TaskDAO)TestPersistenceContext.getInstance().getBean("taskDAO");
        
        Task result = dao.getTask(2L);
        assertNotNull(result);
        
        assertEquals("Title", "Get Test Task", result.getTitle());
        assertEquals("Details", "Get Test Details", result.getDetails());
        assertEquals("Doc File", "Get Doc File", result.getDocumentationFile());
        assertEquals("Input Type", (byte)1, result.getInputType());
        assertEquals("Start Date", new SimpleDateFormat("yyyy-MM-dd").parse("2011-08-29"), result.getStartDate());
        assertEquals("Days", (short)1, result.getDays());
        assertEquals("Weeks", (byte)1, result.getWeeks());
        assertEquals("Months", (byte)1, result.getMonths());
    }
}
