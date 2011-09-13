/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import com.artechnologiesllc.tc.domain.Problem;
import org.junit.Ignore;
import org.junit.BeforeClass;
import com.artechnologiesllc.tc.persistence.dao.ProblemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import com.artechnologiesllc.tc.domain.Problem;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dan
 */
public class ProblemHibernateDAOTest {
    
    private static Connection connection;

    @BeforeClass
    public static void setUpBefore() throws Exception {
        connection = TestConnectionHelper.getConnection();
        //TestConnectionHelper.generateTestData();
    }

    /**
     * Test of persist method, of class ProblemHibernateDAO.
     */
    @Test
    public void testCreate() throws Exception {
        String title = "Create Problem";
        String contact = "Create Contact";
        String doc = "Create Doc";
        byte priority = 3;
        
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setContact(contact);
        problem.setDoc(doc);
        problem.setPriority(priority);
        
        ProblemDAO dao = (ProblemDAO) TestPersistenceContext.getInstance().getBean("problemDAO");
        Problem perProblem = dao.persist(problem);
        
        assertNotNull("Id", perProblem.getId());
        assertEquals("Title", title, perProblem.getTitle());
        assertEquals("Contact", contact, perProblem.getContact());
        assertEquals("Doc", doc, perProblem.getDoc());
        assertEquals("Priority", priority, perProblem.getPriority());
        
        PreparedStatement stmt = connection.prepareStatement("SELECT title, contact, documentation, priority FROM Problem WHERE problem_id=?");
        stmt.setLong(1, perProblem.getId());
        ResultSet results = stmt.executeQuery();
        assertTrue(results.next());
        
        assertEquals("DB Title", title, results.getString(1));
        assertEquals("DB Contact", contact, results.getString(2));
        assertEquals("DB Doc", doc, results.getString(3));
        assertEquals("DB Priority", priority, results.getByte(4));
    }
    
    @Test
    @Ignore
    public void testUpdate() throws Exception {
        Problem problem = new Problem();
        problem.setId(1L);
        problem.setTitle("UpdatedTitle1");
        problem.setContact("UpdateContact1");
        problem.setDoc("UpdatedDocumentationFile1");
        List<String> procedures = new ArrayList<String>(1);
        procedures.add("UpdatedProcedures 1_1");
        problem.setProcedures(procedures);
        
        ProblemDAO dao = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        Problem persistProblem = dao.persist(problem);
        assertEquals("Id", problem.getId(), persistProblem.getId());
        assertEquals("Title", problem.getTitle(), persistProblem.getTitle());
        assertEquals("ContactName", problem.getContact(), persistProblem.getContact());
        assertEquals("DocumentationFile", problem.getDoc(), persistProblem.getDoc());
        assertEquals("Procedures", problem.getProcedures(), persistProblem.getProcedures());
        
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet results = null;
        ResultSet results2 = null;
        try {
            statement = connection.prepareStatement("SELECT title, contactName, documentationFile FROM Problem WHERE problem_id=?");
            statement.setLong(1, 1L);
            results = statement.executeQuery();
            results.next();
            
            assertEquals("Database Title", problem.getTitle(), results.getString("title"));
            assertEquals("Database ContactName", problem.getContact(), results.getString("contactName"));
            assertEquals("Database DocumentationFile", problem.getDoc(), results.getString("documentationFile"));
            
            statement2 = connection.prepareStatement("SELECT procedures FROM ProblemProcedures WHERE problem_id=? ORDER BY procedure_num");
            statement2.setLong(1, 1L);
            results2 = statement2.executeQuery();
            
            for(int count=0; results2.next(); count++) {
                assertEquals("Database procedures " + count, problem.getProcedures().get(count), results2.getString(1));
            }
        } finally {
            if(statement != null)
                statement.close();
            
            if(statement2 != null)
                statement2.close();
            
            if(results != null)
                results.close();
            
            if(results2 != null)
                results2.close();
        }
    }

    /**
     * Test of delete method, of class ProblemHibernateDAO.
     */
    @Test
    @Ignore
    public void testDelete() throws Exception {
        Problem problem = new Problem();
        problem.setId(2L);
        problem.setTitle("Test2");
        problem.setContact("Contact2");
        problem.setDoc("Doc2");
        List<String> procedures = new ArrayList<String>(2);
        procedures.add("Procedure 2_1");
        procedures.add("Procedure 2_2");
        problem.setProcedures(procedures);
        
        ProblemDAO instance = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        instance.delete(problem);
        
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet results = null;
        ResultSet results2 = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Problem WHERE problem_id=?");
            statement.setLong(1, 2L);
            results = statement.executeQuery();
            assertFalse("Database problem", results.next());
            
            statement2 = connection.prepareStatement("SELECT * FROM ProblemProcedures WHERE problem_id=?");
            statement2.setLong(1, 2L);
            results2 = statement.executeQuery();
            assertFalse("Database ProblemProcedures", results2.next());
        } finally {
            if(statement != null)
                statement.close();
            
            if(statement2 != null)
                statement2.close();
            
            if(results != null)
                results.close();
            
            if(results2 != null)
                results2.close();
        }
    }

    /**
     * Test of getProblem method, of class ProblemHibernateDAO.
     */
    @Test
    @Ignore
    public void testGetProblem() {
        ProblemDAO dao = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        
        Problem expProblem = new Problem();
        expProblem.setId(3L);
        expProblem.setTitle("Test3");
        expProblem.setContact("Contact3");
        expProblem.setDoc("Doc3");
        List<String> procedures = new ArrayList<String>(3);
        procedures.add("Procedure 3_1");
        procedures.add("Procedure 3_2");
        procedures.add("Procedure 3_3");
        expProblem.setProcedures(procedures);
        
        Problem actualProblem = dao.getProblem(3L);
        
        assertEquals(expProblem, actualProblem);
    }
}
