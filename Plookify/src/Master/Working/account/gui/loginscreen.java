package Master.Working.account.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * @author jlleow
 */
public class loginscreen extends JPanel
{
    private BufferedImage logo1;
    public loginscreen()
    {
        try 
        {                
          logo1 = ImageIO.read(new File("./logo.psd"));
        } catch (IOException ex)
        {
            System.out.println("image not found");
        }
    }
    
}
