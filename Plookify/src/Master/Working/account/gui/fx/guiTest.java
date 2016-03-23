/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.account.gui.fx;

import Master.Working.account.logic.logic;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author jll30
 */
public class guiTest extends Application
{
    @Override
    public void start(Stage stage) throws Exception 
    {
        logic logicB=new logic();
        logicB.extendPrem(3);
    }

    public static void main(String[] args)
    {
        launch(args); 
    }
}