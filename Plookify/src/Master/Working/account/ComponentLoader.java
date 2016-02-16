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
        JOptionPane.showMessageDialog(null,"This application works correctly!");
        logic testing=new logic();
        testing.addAccount("bacon", "", "");
    }
    
}
