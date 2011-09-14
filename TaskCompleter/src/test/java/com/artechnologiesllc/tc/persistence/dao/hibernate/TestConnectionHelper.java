/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author dan
 */
public class TestConnectionHelper {
    private static final Logger logger = Logger.getLogger(TestConnectionHelper.class);
    
    private static boolean generated = false;
    
    private static final String FILENAME = "import.sql";
    
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        if(connection == null)
            connection = ((DataSource)TestPersistenceContext.getInstance().getBean("localDataSource")).getConnection();
        
        return connection;
    }
    
    public static void generateTestData() throws Exception {
        logger.debug("Generating test data and committing to database.");
        if(!generated) {
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
                for(String stmt : getStatements()) {
                    logger.debug("Statement: " + stmt);
                    statement.addBatch(stmt);
                }
                
                logger.debug("Executing batch.");
                statement.executeBatch();
                generated = true;
            } finally {
                if(statement != null)
                    statement.close();
            }
        }
    }
    
    public static String[] getStatements() throws Exception {
        logger.debug("Parsing statements from file: " + FILENAME);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(FILENAME)));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = in.readLine()) != null)
            builder.append(line);
        in.close();
        
        return builder.toString().split(";\\s*");
    }
}
