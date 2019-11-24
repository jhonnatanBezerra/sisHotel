/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;


import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Jhonnatan
 */
public class Hotel extends Application{

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/LoginFXML.fxml"));
        Scene cena = new Scene(root);
        primaryStage.setScene(cena);
        primaryStage.show();
    }
    
    
    
    public static void main(String[] args) throws SQLException {
        launch(args);
    }

   
}
