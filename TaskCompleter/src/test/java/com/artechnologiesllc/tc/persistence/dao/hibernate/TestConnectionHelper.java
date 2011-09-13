/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.persistence.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author dan
 */
public class TestConnectionHelper {
    
    private static boolean generated = false;
    
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        if(connection == null)
            connection = ((DataSource)PersistenceContext.getInstance().getBean("localDataSource")).getConnection();
        
        return connection;
    }
    
    public static void generateTestData() throws SQLException {
        if(!generated) {
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
                for(String stmt : statements)
                    statement.addBatch(stmt);
                
                statement.executeBatch();
                generated = true;
            } finally {
                if(statement != null)
                    statement.close();
            }
        }
    }
    
    private static final String[] statements = new String[]{
        "INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
            "VALUES('1', 'Update Problem', 'Update Contact', 'Update Doc');",
        
        "INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
            "VALUES('1', 'Update Procedure', '0');",
        
        "INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
            "VALUES('2', 'Delete Problem', 'Delete Contact', 'Delete Doc');",
        
        "INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
            "VALUES('2', 'Delete Procedure', '0');",
        
        "INSERT INTO Problem(problem_id, title, contactName, documentationFile) " +
            "VALUES('3', 'Get Problem', 'Get Contact', 'Get Doc');",
        
        "INSERT INTO ProblemProcedures(problem_id, procedures, procedure_num) " +
            "VALUES('3', 'Get Procedure', '0');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('1', 'Update Task', 'Update Details', 'Update Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('2', 'Delete Task', 'Delete Details', 'Delete Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('3', 'Get Task', 'Get Details', 'Get Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('4', 'Create Complete Task', 'Create Complete Details', 'Create Complete Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('5', 'Update Complete Task', 'Update Complete Details', 'Update Complete Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('6', 'Delete Complete Task', 'Delete Complete Details', 'Delete Complete Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('7', 'Get Task Date Task', 'Get Task Date Details', 'Get Task Date Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('8', 'Get Task Task', 'Get Task Details', 'Get Task Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('9', 'Get Date Task', 'Get Date Details', 'Get Date Doc', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('10', 'Get Task Date Range Task', 'Get Task Date Range Details', 'Get Task Date Range Doc', '1', " +
            "'2011-08-28', '2011-09-03', '1', '1', '1');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('5', '2011-08-30', '2011-08-30 13:48:00', 'Update', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('6', '2011-08-30', '2011-08-30 14:00:00', 'Delete', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('7', '2011-08-30', '2011-08-30 14:00:00', 'Get Task Date', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('8', '2011-08-30', '2011-08-30 14:00:00', 'Get Task', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('9', '2011-08-30', '2011-08-30 14:00:00', 'Get Date', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('10', '2011-08-30', '2011-08-30 14:00:00', 'Get Task Date Range', 'buhrmande');",
        
        "INSERT INTO CompleteInfo(task_id, scheduledDate, completeTime, info, username) " +
            "VALUES('10', '2011-09-01', '2011-09-01 14:00:00', 'Get Task Date Range', 'buhrmande')",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('11', 'TaskGroup Task1', 'TaskGroup Details1', 'TaskGroup Doc1', '1', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO Task(task_id, title, details, documentationFile, inputType, " +
            "startDate, endDate, days, weeks, months) " +
            "VALUES('12', 'TaskGroup Task2', 'TaskGroup Details2', 'TaskGroup Doc2', '2', " +
            "'2011-08-30', '2011-09-01', '1', '1', '1');",
        
        "INSERT INTO TaskGroup(group_id, name) " +
            "VALUES('1', 'Update TaskGroup');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('11', '1', '0');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('12', '1', '1');",
        
        "INSERT INTO TaskGroup(group_id, name) " +
            "VALUES('2', 'Delete TaskGroup');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('11', '2', '0');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('12', '2', '1');",
        
        "INSERT INTO TaskGroup(group_id, name) " +
            "VALUES('3', 'Get TaskGroup Id');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('11', '3', '0');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('12', '3', '1');",
        
        "INSERT INTO TaskGroup(group_id, name) " +
            "VALUES('4', 'Get TaskGroup Name');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('11', '4', '0');",
        
        "INSERT INTO TaskgroupToTask(task_id, group_id, order_num) " +
            "VALUES('12', '4', '1');"
    };
}
