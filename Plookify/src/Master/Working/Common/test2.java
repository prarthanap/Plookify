/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.Common;

import java.io.File;

/**
 *
 * @author jll30
 */
public class test2
{
    public static void main(String[] args)
    {
        File f;
        String path=System.getProperty("user.dir")+"\\build\\classes\\Master\\Working\\player\\logic\\Tracks\\Mad.mp3";
        f=new File(path);
        System.out.println(path);
        System.out.println(f.exists());
    }
            
}
