/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jll30
 */
public class dbc {
    Connection conn = null;
    Statement stmt = null;

    public static Connection ConnectDB()
    {
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        conn.setAutoCommit(true);
        return conn;
    } catch (SQLException ex) {System.err.println(ex.getMessage());}
     return null;
    }
    
}