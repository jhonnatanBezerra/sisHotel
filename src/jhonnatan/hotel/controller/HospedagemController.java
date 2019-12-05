/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jhonnatan.hotel.dao.HospedagemDao;
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
    private TableColumn<Hospedagem, String> clmHospede;
    @FXML
    private TableColumn<Hospedagem, String> clmAP;
    @FXML
    private TableColumn<Hospedagem, Integer> clmAcompanhantes;
    @FXML
    private TableColumn<Hospedagem, Date> clmData;
    
     @FXML
    private Button btvolta;
    
    private Hospedagem hospeSelecionada;
    
    private ObservableList<Hospedagem> listaHospedagem = FXCollections.observableArrayList();
    @FXML
    private TextField txtBusca;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabelaHospedagem();
        
        tabelaHospedagem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               hospeSelecionada = (Hospedagem) newValue; 
            }
            
        });
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
        //capturar id
       Stage stage = (Stage)btvolta.getScene().getWindow();
       stage.close();
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
    
    @FXML
    private void btBuscar(ActionEvent event){
        tabelaHospedagem.setItems(buscar());
    }
    
    private ObservableList<Hospedagem> buscar(){
        ObservableList<Hospedagem> hospedagemPesq = FXCollections.observableArrayList();
        for(int x = 0; x < listaHospedagem.size(); x++){
            if(listaHospedagem.get(x).getUsuario().getNome().toLowerCase().contains(txtBusca.getText().toLowerCase())); // pesquisando por nome
                hospedagemPesq.add(listaHospedagem.get(x));
        }
        return hospedagemPesq;
    }
    
    private void initTabelaHospedagem(){
        HospedagemDao hdao = new HospedagemDao();
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        //clmHospede.setCellValueFactory(new PropertyValueFactory("hospede"));
        clmHospede.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHospede().getNome()));
        clmAP.setCellValueFactory(new PropertyValueFactory("numeroAP"));
        clmAcompanhantes.setCellValueFactory(new PropertyValueFactory("qtdAcompanhante"));
        clmData.setCellValueFactory(new PropertyValueFactory("dataEntrada"));
        
        
        tabelaHospedagem.setItems(attTabela());
    }
    
    private ObservableList<Hospedagem> attTabela(){
        HospedagemDao hosDao = new HospedagemDao();
        try {
            listaHospedagem = FXCollections.observableArrayList(hosDao.listar());
            return listaHospedagem;
        } catch (SQLException ex) {
            Logger.getLogger(HospedagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
