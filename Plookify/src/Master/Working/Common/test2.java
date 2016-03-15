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
        f = new File("../account/resources/logo.png");
        System.out.println(f.exists());
    }
            
}
