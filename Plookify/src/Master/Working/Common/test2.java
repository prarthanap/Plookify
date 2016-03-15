/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import Master.Working.account.logic.logic;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jll30
 */
public class test2
{
    private static logic test=new logic();
    private static int count=0;
    public static void main(String[] args) throws SQLException
    {
        Timer timer1=new Timer();
        timer1.schedule(new TimerTask()
        {
            @Override
            public void run() 
            {
             if(count<5)
             {
                System.out.println("Rawr");
                count++;
             }
             else{this.cancel();}
            }
        }, 1000,500);

    }
            
}
