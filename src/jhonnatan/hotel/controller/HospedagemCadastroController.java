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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jhonnatan.hotel.model.Hospedagem;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class HospedagemCadastroController implements Initializable {

    @FXML
    private ComboBox<Hospedagem> comboAP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


public void abreCadastroHosp(ActionEvent event) throws IOException{
    BorderPane cadastroHosp = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemCadastroFXML.fxml"));
    Scene cena = new Scene(cadastroHosp);
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(cena);
    stage.show();
}    

    @FXML
    private void pegaAP(ActionEvent event) {
       
    }

    @FXML
    private void cancelaHospedagem(ActionEvent event) {
        HospedagemController hospedagem = new HospedagemController();
        try {
            hospedagem.abreHospedagemController(event);
        } catch (IOException ex) {
            Logger.getLogger(HospedagemCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

 
