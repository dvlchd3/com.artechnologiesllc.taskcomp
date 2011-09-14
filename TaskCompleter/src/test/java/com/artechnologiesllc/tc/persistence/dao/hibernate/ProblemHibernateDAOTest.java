/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

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
        TestConnectionHelper.generateTestData();
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
        List<String> procedures = new ArrayList<String>(2);
        procedures.add("Create Procedure 1");
        procedures.add("Create Procedure 2");
        
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setContact(contact);
        problem.setDoc(doc);
        problem.setPriority(priority);
        problem.setProcedures(procedures);
        
        ProblemDAO dao = (ProblemDAO) TestPersistenceContext.getInstance().getBean("problemDAO");
        Problem perProblem = dao.persist(problem);
        
        assertNotNull("Id", perProblem.getId());
        assertEquals("Title", title, perProblem.getTitle());
        assertEquals("Contact", contact, perProblem.getContact());
        assertEquals("Doc", doc, perProblem.getDoc());
        assertEquals("Priority", priority, perProblem.getPriority());
        assertEquals("Procedures", procedures, perProblem.getProcedures());
        
        PreparedStatement stmt = connection.prepareStatement("SELECT title, contact, documentation, priority FROM Problem WHERE problem_id=?");
        stmt.setInt(1, perProblem.getId());
        ResultSet results = stmt.executeQuery();
        assertTrue(results.next());
        
        assertEquals("DB Title", title, results.getString(1));
        assertEquals("DB Contact", contact, results.getString(2));
        assertEquals("DB Doc", doc, results.getString(3));
        assertEquals("DB Priority", priority, results.getByte(4));
        
        PreparedStatement stmt2 = connection.prepareStatement("SELECT procedure_value FROM ProblemProcedures WHERE problem_id=? ORDER BY procedure_num");
        stmt2.setInt(1, perProblem.getId());
        ResultSet results2 = stmt2.executeQuery();
        int i;
        for(i=0; results2.next(); i++) {
            assertEquals("Procedure " + i, procedures.get(i), results2.getString(1));
        }
        assertEquals("Procedure length", procedures.size(), i);
            
    }

    /**
     * Test of delete method, of class ProblemHibernateDAO.
     */
    @Test
    public void testDelete() throws Exception {
        Problem problem = new Problem();
        problem.setId(1);
        problem.setTitle("Delete Problem");
        problem.setContact("Delete Contact");
        problem.setPriority((byte)1);
        List<String> procedures = new ArrayList<String>(2);
        procedures.add("Delete Procedure 1");
        procedures.add("Delete Procedure 2");
        problem.setProcedures(procedures);
        
        ProblemDAO dao = (ProblemDAO) TestPersistenceContext.getInstance().getBean("problemDAO");
        dao.delete(problem);
        
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Problem WHERE problem_id=?");
        stmt.setInt(1, 1);
        ResultSet results = stmt.executeQuery();
        assertFalse(results.next());
        
        PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM ProblemProcedures WHERE problem_id=?");
        stmt2.setInt(1, 1);
        ResultSet results2 = stmt2.executeQuery();
        assertFalse(results.next());
    }

    /**
     * Test of getProblem method, of class ProblemHibernateDAO.
     */
    @Test
    public void testGetProblem() {
        Problem expProblem = new Problem();
        expProblem.setId(2);
        expProblem.setTitle("Get Problem");
        expProblem.setContact("Get Contact");
        expProblem.setDoc("Get Doc");
        expProblem.setPriority((byte)4);
        List<String> expProcedures = new ArrayList<String>(2);
        expProcedures.add("Get Procedure 1");
        expProcedures.add("Get Procedure 2");
        expProblem.setProcedures(expProcedures);
        
        ProblemDAO dao = (ProblemDAO) TestPersistenceContext.getInstance().getBean("problemDAO");
        Problem problem = dao.getProblem(2);
        assertEquals(expProblem, problem);
    }
}
