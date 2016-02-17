/**
 * @author jlleow
 * testing code here
 */
package Master.Working.account;

import Master.Working.account.logic.*;
import javax.swing.*;

public class ComponentLoader
{
    public static void main(String[] args)
    {
        logic testing=new logic();
        System.out.println(testing.authCheck("ji30","password"));
        JOptionPane.showMessageDialog(null,"This application works!");
    }
    
}
