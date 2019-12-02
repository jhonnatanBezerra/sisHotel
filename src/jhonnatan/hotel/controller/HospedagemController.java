/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Hospede;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class HospedagemController implements Initializable {

    @FXML
    private TableView<Hospedagem> tabelaHospedagem;
    @FXML
    private TableColumn<Hospedagem, Integer> clmID;
    @FXML
    private TableColumn<Hospedagem, Hospede> clmHospede;
    @FXML
    private TableColumn<Hospedagem, String> clmAP;
    @FXML
    private TableColumn<Hospedagem, Integer> clmAcompanhantes;
    @FXML
    private TableColumn<Hospedagem, Date> clmData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void abreHospedagemController(ActionEvent event) throws IOException{
        BorderPane hospedagem = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemFXML.fxml"));
        Scene cena = new Scene(hospedagem);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
        
    }

    @FXML
    private void btVoltaPrincipal(ActionEvent event) {
        PrincipalController root = new PrincipalController();
        root.abrePrincipal(event);
    }

    @FXML
    private void btNovaHospedagem(ActionEvent event) {
        HospedagemCadastroController cadastro = new HospedagemCadastroController();
        try {
            cadastro.abreCadastroHosp(event);
        } catch (IOException ex) {
            Logger.getLogger(HospedagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
