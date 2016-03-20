/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args) throws SQLException
    {//remember to use try catch block
        database data=new database();
        ResultSet pubID=data.makeQuery("SELECT USERID FROM SUBSCRIPTION WHERE PREMIUM=1 and PUBLICITY='0.0'");
        ArrayList<Integer> pID=new ArrayList<>();
        while (pubID.next())
        {
            pID.add(pubID.getInt(1));
        }
        ArrayList<String> namesList=new ArrayList<>();
        for(int i=0;i<pID.size();i++)
        {
            namesList.add(data.makeQuery("SELECT USERNAME FROM ACCOUNT WHERE ID='"+pID.get(i)+"'").getString(1));
        }
        for (String a : namesList)
        {System.out.println(a);}
        
        
    }
            
}
