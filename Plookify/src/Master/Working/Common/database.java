package Master.Working.Common;

/**
 *
 * @author jlleow AKA ec14002
 *
 * Code for making queries or updates to database
 *
 * 16/02/2016
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {

    public Connection conn = null;

    public void Database() {

    }

    public void conClose() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println("none to close");
        }
    }
    /*public static void main(String[] args) throws SQLException//just testing code (DO NOT RUN)
     {
     System.out.println("starto!");
     database data1=new database();
     ResultSet result=data1.makeQuery("SELECT * FROM ACCOUNT");
     while(result.next())
     {
     String name = result.getString(4);
     System.out.println(name);
     name=result.getString(10);
     System.out.println(name);
     }
          
     //System.out.println();
     }*/

    public ResultSet makeQuery(String query)//method to take a string as a query for database, returns resultset
    {
        ResultSet res = null;
        try {
            Statement statement;

            conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db");
            
          //  conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            res = statement.executeQuery(query);
            System.out.println("Query made");
            return res;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("exception at MQ");
        }

        return null;
    }

    public void makeUpdate(String query)//method running only one statement
    {
        try {
            Statement statementU;
            
            conn = DriverManager.getConnection("jdbc:sqlite::resource:Master/Working/Common/data.db");
            
            //conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            statementU = conn.createStatement();
            statementU.setQueryTimeout(10);
            statementU.execute("PRAGMA foreign_keys = ON");
            statementU.executeUpdate(query);
            System.out.println("Update made");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage() + "@mU");
        } finally {
            try {
                conn.close();
                System.out.println("connection closed at MU");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public boolean dupCheck(String search, String column, String table)//checks if there is a row match using what u searching for, the table and which column
    {
        String Query1 = "SELECT " + column + " FROM " + table + " WHERE " + column + "='" + search + "'";
        try {
            int count = 0;
            ResultSet check = makeQuery(Query1);
            while (check.next()) {
                count++;
            }
            try {
                conn.close();
                System.out.println("connection closed at DC");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            return count != 0;
        } catch (SQLException ex) {
            System.out.println("dupcheck error!");
        }
        return false;
    }

    public boolean dupCheck2(String query)//checks if there is a row match using custom query
    {
        try {
            int count;
            try (ResultSet check = makeQuery(query)) {
                count = 0;
                while (check.next()) {
                    count++;
                }
            }
            return count != 0;
        } catch (SQLException ex) {
            System.out.println("dupcheck error!");
        }
        return false;
    }

    public int resCount(String query)//returns number of records
    {
        try {
            ResultSet check = makeQuery(query);
            int count = 0;
            while (check.next()) {
                count++;
            }
            return count;
        } catch (SQLException ex) {
            System.out.println("resCount error!");
        } finally {
            try {
                conn.close();
                System.out.println("connection closed at rescount");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return 0;
    }

    public int authCheckD(String uname, String pass1) {
        String authtest = "SELECT ID FROM ACCOUNT WHERE USERNAME='" + uname + "' and PASSWORD='" + pass1 + "'";
        ResultSet idRes;
        try {
            idRes = makeQuery(authtest);
            if (idRes.next()) {
                return idRes.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                conn.close();
                System.out.println("conn and idres closed");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return 9999;//no one should have this

    }

}
