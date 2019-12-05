/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
    @FXML
    private MenuBar menuBar;
    @FXML
    private BorderPane panePrincipal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usuarioLogado = Session.getUsuario();
        lblUsuario.setText("Usuario: "+usuarioLogado.getId());
        LocalDate now = LocalDate.now();
        
        
    }    

    @FXML
    private void abreHospede(ActionEvent event) {
        
        try {
            BorderPane principal;
            principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedeFXML.fxml"));
            Scene cena = new Scene(principal);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void sairDaAplicacao(ActionEvent event) {
         VBox root;
        try {
            root = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/LoginFXML.fxml"));
            Scene cena = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void btHospedagem(ActionEvent event) {
        BorderPane hospedagem;
        try {
            hospedagem = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemFXML.fxml"));
            Scene cena = new Scene(hospedagem);
            Stage stage =  new Stage();//(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrePrincipal(ActionEvent event){
        BorderPane principal; 
        try {
            principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/PrincipalFXML.fxml"));
            Scene cena = new Scene(principal);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void abreUsuario(ActionEvent event) {
        AnchorPane paneUsuario = new AnchorPane();
        try {
            paneUsuario = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioFXML.fxml"));
            Scene cena = new Scene(paneUsuario);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abreApartamento(ActionEvent event) {
        AnchorPane paneAP = new AnchorPane();
        try{
            paneAP = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ApartamentosFXML.fxml"));
            Scene cena = new Scene(paneAP);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fechaSistema(ActionEvent event) {
        System.exit(0);
    }
    
    
}
