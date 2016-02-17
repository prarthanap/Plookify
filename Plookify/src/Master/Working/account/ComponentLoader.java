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
        String[] address1=new String[]{"22","street","city","county","postcode","number"};
        testing.addAccount("test1", "a", "b","pass",address1);
    }
    
}
