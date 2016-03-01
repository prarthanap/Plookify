/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui;

import java.io.IOException;
import java.util.*;
import javafx.scene.image.*;
import javax.imageio.*;

/**
 *
 * @author jll30
 */
public class imageLib2       
{
    Image logo=new Image("/Master/Working/account/gui/logo.png");
    Image logo1=new Image("/Master/Working/account/gui/logo(small).png");
    
    Map<String,Image> imageMap;
    
    public imageLib2()
    {
        imageMap=new HashMap<>();
        imageMap.put("logo_small",logo1);
        imageMap.put("logo",logo);
    }
    public Image getImage(String name)
    {
        return imageMap.get(name);
    }
}
