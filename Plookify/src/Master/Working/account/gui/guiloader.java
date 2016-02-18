
package Master.Working.account.gui;

import java.io.File;
import javax.swing.*;

/**
 *
 * @author jll30
 */
public class guiloader 
{
    public static void main(String[] args)
    {
        File imageCheck = new File("Master/Working/account/gui/logo.psd");

    if(imageCheck.exists()) 
        System.out.println("Image file found!");
    else 
        System.out.println("Image file not found!");
        
        /*JFrame frame1=new JFrame();
        loginscreen logon=new loginscreen();
        frame1.getContentPane().add(logon);
        frame1.pack();
        frame1.setSize(100,100);
        frame1.setVisible(true);*/
    }
}
