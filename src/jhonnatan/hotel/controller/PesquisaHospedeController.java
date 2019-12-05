/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jhonnatan.hotel.dao.HospedeDao;
import jhonnatan.hotel.model.Hospede;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class PesquisaHospedeController implements Initializable {
    
    @FXML
    private TableView<Hospede> tabelaHospede;
    @FXML
    private TableColumn<Hospede, Integer> clmID;
    @FXML
    private TableColumn<Hospede, String> clmNome;
    @FXML
    private TableColumn<Hospede, String> clmCPF;
    @FXML
    private TableColumn<Hospede, String> clmTelefone;
    @FXML
    private TableColumn<Hospede, String> clmData;
    @FXML
    private TableColumn<Hospede, String> clmEmail;
    
    private ObservableList<Hospede> listaDeHospedes = FXCollections.observableArrayList();
    
    private Hospede hospSelecionado;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabela();
        tabelaHospede.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                hospSelecionado = (Hospede)newValue;
            }
        });
    }    

    @FXML
    private void btSelecionar(ActionEvent event) {
        HospedagemCadastroController hospedagem = new HospedagemCadastroController();
        HospedagemEditarController ed = new HospedagemEditarController();
        if(hospSelecionado != null){
            ed.setHospSelecionado(hospSelecionado);
            try {
                hospedagem.abreCadastroHosp(event);
            } catch (IOException ex) {
                Logger.getLogger(PesquisaHospedeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void initTabela() {
        
        clmID.setCellValueFactory(new PropertyValueFactory("ID")); // Nome igual ao modelo
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmCPF.setCellValueFactory(new PropertyValueFactory("CPF"));
        clmData.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        clmTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));
        
        tabelaHospede.setItems(atualizarTabela());
        
    }

    private ObservableList<Hospede> atualizarTabela() {
        try {
            HospedeDao hDao = new HospedeDao();
            listaDeHospedes = FXCollections.observableArrayList(hDao.listar());
            return listaDeHospedes ;
            
        } catch (SQLException ex) {
            Logger.getLogger(HospedeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public void abrePesquisaHospede(ActionEvent event) throws IOException{
        VBox panePesquisaHosp = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/PesquisaHospedeFXML.fxml"));
        Scene cena = new Scene(panePesquisaHosp);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }
    
}
