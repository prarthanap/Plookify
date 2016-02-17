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
        JOptionPane.showMessageDialog(null,"This application works!");
        logic testing=new logic();
        System.out.println(testing.authCheck("jil30","password"));
    }
    
}
