/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.logic.logic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jll30
 */
public class test2
{
    private static logic test=new logic();
    public static void main(String[] args) throws SQLException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now=new Date();
        System.out.println(dateFormat.format(now));
                
                Statement statement;
                Connection conn= DriverManager.getConnection("jdbc:sqlite:data.db");
                statement = conn.createStatement();
                statement.setQueryTimeout(10);
                statement.execute("PRAGMA foreign_keys = ON");
                statement.execute("DELETE FROM ACCOUNT WHERE ID=5");
    }
            
}
