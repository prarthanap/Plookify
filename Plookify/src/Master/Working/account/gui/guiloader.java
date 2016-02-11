
package Master.Working.account.gui;

import javax.swing.*;

/**
 *
 * @author jll30
 */
public class guiloader 
{
    public static void main(String[] args)
    {
        JFrame frame1=new JFrame();
        loginscreen logon=new loginscreen();
        frame1.getContentPane().add(logon);
        frame1.pack();
        frame1.setVisible(true);
    }
}
