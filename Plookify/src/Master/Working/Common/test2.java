/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args) throws SQLException{
    database test=new database();
    ResultSet result=test.makeQuery("SELECT * FROM ACCOUNT");
    while(result.next()){
            String name = result.getString(4);
            String name2=result.getString(5);
             System.out.println(name+" "+name2);}
    }
}
