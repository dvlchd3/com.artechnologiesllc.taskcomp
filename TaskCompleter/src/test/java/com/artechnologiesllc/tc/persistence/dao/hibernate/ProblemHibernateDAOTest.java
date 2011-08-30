/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import org.junit.BeforeClass;
import com.artechnologiesllc.tc.persistence.dao.ProblemDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.sql.DataSource;
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
    public static void generateProblemTestData() throws Exception {
        connection = ((DataSource)TestPersistenceContext.getInstance().getBean("localDataSource")).getConnection();
        Statement statement = null;
        try {            
            statement = connection.createStatement();
                    
            statement.addBatch("INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
                                "VALUES('1', 'Test1', 'Contact1', 'Doc1');");

            statement.addBatch("INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
                                "VALUES('2', 'Test2', 'Contact2', 'Doc2');");

            statement.addBatch("INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
                                "VALUES('3', 'Test3', 'Contact3', 'Doc3');");

            statement.addBatch("INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
                                "VALUES('4', 'Test4', 'Contact4', 'Doc4');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('1', 'Procedure 1_1', '0');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('2', 'Procedure 2_1', '0');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('2', 'Procedure 2_2', '1');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('3', 'Procedure 3_1', '0');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('3', 'Procedure 3_2', '1');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('3', 'Procedure 3_3', '2');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('4', 'Procedure 4_1', '0');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('4', 'Procedure 4_2', '1');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('4', 'Procedure 4_3', '2');");

            statement.addBatch("INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
                                "VALUES('4', 'Procedure 4_4', '3');");
            
            statement.executeBatch();
        } finally {
            statement.close();
        }
    }

    /**
     * Test of persist method, of class ProblemHibernateDAO.
     */
    @Test
    public void testCreate() throws Exception {
        ProblemDAO dao = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        
        String testTitle = "Title5";
        String testContactName = "Contact5";
        String testDocumentationFile = "Doc5";
        List<String> testProcedures = new ArrayList<String>(5);
        testProcedures.add("Procedure 5_1");
        testProcedures.add("Procedure 5_2");
        testProcedures.add("Procedure 5_3");
        testProcedures.add("Procedure 5_4");
        testProcedures.add("Procedure 5_5");
        
        Problem newProblem = new Problem();
        newProblem.setTitle(testTitle);
        newProblem.setContactName(testContactName);
        newProblem.setDocumentationFile(testDocumentationFile);
        newProblem.setProcedures(testProcedures);
        
        Problem persistProblem = dao.persist(newProblem);
        assertNotNull("Id exists", persistProblem.getId());
        assertEquals("Title equals", testTitle, persistProblem.getTitle());
        assertEquals("Contact Name equals", testContactName, persistProblem.getContactName());
        assertEquals("Documentation File equals", testDocumentationFile, persistProblem.getDocumentationFile());
        assertEquals("Procedures equals", testProcedures, persistProblem.getProcedures());
        
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet results = null;
        ResultSet results2 = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Problem WHERE problem_id=?");
            statement.setLong(1, persistProblem.getId());
            results = statement.executeQuery();
            results.next();
            
            assertEquals("Database title", testTitle, results.getString("title"));
            assertEquals("Database contact name", testContactName, results.getString("contactName"));
            assertEquals("Database documentation name", testDocumentationFile, results.getString("documentationFile"));
            
            statement2 = connection.prepareStatement("SELECT procedures FROM ProblemProcedures WHERE problem_id=? ORDER BY procedure_num");
            statement2.setLong(1, persistProblem.getId());
            results2 = statement2.executeQuery();
            
            for(int counter=0; results.next(); counter++) {
                assertEquals("Database procedures " + counter, testProcedures.get(counter), results2.getString(1));
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
    
    @Test
    public void testUpdate() throws Exception {
        Problem problem = new Problem();
        problem.setId(1L);
        problem.setTitle("UpdatedTitle1");
        problem.setContactName("UpdateContact1");
        problem.setDocumentationFile("UpdatedDocumentationFile1");
        List<String> procedures = new ArrayList<String>(1);
        procedures.add("UpdatedProcedures 1_1");
        problem.setProcedures(procedures);
        
        ProblemDAO dao = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        Problem persistProblem = dao.persist(problem);
        assertEquals("Id", problem.getId(), persistProblem.getId());
        assertEquals("Title", problem.getTitle(), persistProblem.getTitle());
        assertEquals("ContactName", problem.getContactName(), persistProblem.getContactName());
        assertEquals("DocumentationFile", problem.getDocumentationFile(), persistProblem.getDocumentationFile());
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
            assertEquals("Database ContactName", problem.getContactName(), results.getString("contactName"));
            assertEquals("Database DocumentationFile", problem.getDocumentationFile(), results.getString("documentationFile"));
            
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
    public void testDelete() throws Exception {
        Problem problem = new Problem();
        problem.setId(2L);
        problem.setTitle("Test2");
        problem.setContactName("Contact2");
        problem.setDocumentationFile("Doc2");
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
    public void testGetProblem() {
        ProblemDAO dao = (ProblemDAO)TestPersistenceContext.getInstance().getBean("problemDAO");
        
        Problem expProblem = new Problem();
        expProblem.setId(3L);
        expProblem.setTitle("Test3");
        expProblem.setContactName("Contact3");
        expProblem.setDocumentationFile("Doc3");
        List<String> procedures = new ArrayList<String>(3);
        procedures.add("Procedure 3_1");
        procedures.add("Procedure 3_2");
        procedures.add("Procedure 3_3");
        expProblem.setProcedures(procedures);
        
        Problem actualProblem = dao.getProblem(3L);
        
        assertEquals(expProblem, actualProblem);
    }
}
