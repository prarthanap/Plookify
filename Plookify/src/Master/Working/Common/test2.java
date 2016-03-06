/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.logic.logic;
import java.sql.SQLException;

/**
 *
 * @author jll30
 */
public class test2
{
    private static logic test=new logic();
    public static void main(String[] args) throws SQLException
    {
        test.newSubscribe(4,3);
    }
            
}
