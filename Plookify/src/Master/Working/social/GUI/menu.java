/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master.Working.social.GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Hamza
 */
public class menu extends Application{

    Stage window;
    private Connection connection = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Plookify");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Search Label - constrains use (child, column, row)
//        Label searchLabel = new Label("Search");
//        searchLabel.setId("bold-label");
//        GridPane.setConstraints(searchLabel, 20, 20);

        //Search Input
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search");
        searchInput.getStyleClass().add("searchField");
        
        GridPane.setConstraints(searchInput, 82, 2);
        

        //Add Friend
        Button addFriend = new Button("+");
        addFriend.getStyleClass().add("addFriend");
        GridPane.setConstraints(addFriend, 21, 22);
        
        //Upgrade
        Button upgrade = new Button("  Premium  ");
        upgrade.getStyleClass().add("premium");
        GridPane.setConstraints(upgrade, 61, 2);

        //Add everything to grid
        grid.getChildren().addAll(/*searchLabel,*/ searchInput, addFriend, upgrade);

        Scene scene = new Scene(grid, 1100, 600);
        scene.getStylesheets().add("test.css");
        window.setScene(scene);
        window.show();
    }

    public void input(String search){
    String query = "SELECT USERNAME from ACCOUNT";
    database db = new database();
    Connection conn = db.getConnection();
    ResultSet rs =  db.makeQuery(query);    
    }
    
    public ResultSet makeQuery(String query)//method to take a string as a query for database, returns resultset
        {
		try {connection = DriverManager.getConnection("jdbc:sqlite:data.db");}
                catch (SQLException ex)
                {throw new RuntimeException("Database connection failed!", ex);}
                
		Statement statement;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(10);
                        return statement.executeQuery(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		finally {
			if (connection != null){
				try{connection.close();}
				catch(SQLException ex){System.err.println(ex.getMessage());}
			}
		}
            return null;
	}

    public void makeUpdate(String query)//method running only one statement(not worth creating an arraylist for 1 update using the prev method
        {
            try {connection = DriverManager.getConnection("jdbc:sqlite:data.db");}
                catch (SQLException ex)
                {throw new RuntimeException("Database connection failed!", ex);}
                
		Statement statement;
		try {
                            statement = connection.createStatement();
                            statement.setQueryTimeout(10);
                            statement.executeUpdate(query);
                    }
		catch (SQLException ex) {System.err.println(ex.getMessage());}
		finally {
			if (connection != null){
				try{connection.close();}
				catch(SQLException ex){System.err.println(ex.getMessage());}
			}
		}
        }
}
