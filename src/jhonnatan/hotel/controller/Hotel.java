/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

    defeitos causam erros que PODEM GERAR falhas...

*/

package jhonnatan.hotel.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void start(Stage primaryStage) {
        
        try {
            
            VBox root;
            root = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/LoginFXML.fxml"));
            Scene cena = new Scene(root);
            primaryStage.setScene(cena);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public static void main(String[] args) throws SQLException {
        launch(args);
       
        
    }

   
}
