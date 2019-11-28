/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PrincipalController implements Initializable {

    private Usuario usuarioLogado;
    
    @FXML
    private Label lblUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioLogado = Session.getUsuario();
        lblUsuario.setText("Usuario: "+usuarioLogado.getId());
        
    }    

    @FXML
    private void abreHospede(ActionEvent event) {
        
        try {
            BorderPane principal;
            principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedeFXML.fxml"));
            Scene cena = new Scene(principal);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
}
