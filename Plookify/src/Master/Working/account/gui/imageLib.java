/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui;

import java.util.*;
import javax.swing.ImageIcon;

/**
 *
 * @author jll30
 */
public class imageLib       
{
    ImageIcon logo= new javax.swing.ImageIcon(getClass().getResource("/Master/Working/account/resources/logo.png"));
    ImageIcon logo1=new javax.swing.ImageIcon(getClass().getResource("/Master/Working/account/resources/logo(small).png"));
    Map<String,ImageIcon> imageMap;
    
    public imageLib()
    {
        imageMap=new HashMap<>();
        imageMap.put("logo_small",logo1);
        imageMap.put("logo1",logo);
    }
    public ImageIcon getImage(String name)
    {
        return imageMap.get(name);
    }
}
