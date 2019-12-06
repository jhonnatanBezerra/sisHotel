/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.net.URL;
import java.sql.Date;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jhonnatan.hotel.dao.ReservaDao;
import jhonnatan.hotel.model.Reserva;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class ReservaController implements Initializable {
   
     @FXML
    private TextField txtBusca;

    @FXML
    private TableView<Reserva> tabelaReservas;

    @FXML
    private TableColumn<Reserva, String> clmNome;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Reserva, Integer> clmAcompanhantes;

    @FXML
    private TableColumn<Reserva, Integer> clmID;
    
    @FXML
    private Button btCancela;

    @FXML
    private TableColumn<Reserva, Date> clmData;
    
    private Reserva reservaSelecionada;
    
    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTabela();
        
        tabelaReservas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                reservaSelecionada = (Reserva) newValue;
            }
        });
    }
    
    @FXML
    void btVoltaPrincipal(ActionEvent event) {
        Stage stage = (Stage)btCancela.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editaRes(ActionEvent event) {
        
    }
    
    private ObservableList<Reserva> buscar(){
        ObservableList<Reserva> resPesq = FXCollections.observableArrayList();
        for(int x = 0; x < listaReservas.size(); x++){
            if(listaReservas.get(x).getNome().toLowerCase().contains(txtBusca.getText().toLowerCase()));
            resPesq.add(listaReservas.get(x));
        }
        return resPesq;
    } 

    @FXML
    void btBuscar(ActionEvent event) {
        tabelaReservas.setItems(buscar());
    }

    @FXML
    void btNovaReserva(ActionEvent event) {

    }

    @FXML
    void btExcluirRes(ActionEvent event) {

    }

    private void initTabela(){
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmID.setCellValueFactory(new PropertyValueFactory("dataChegada"));
        clmAcompanhantes.setCellValueFactory(new PropertyValueFactory("qtdPessoas"));
        tabelaReservas.setItems(atualizarTabela());
    }
    
    private ObservableList<Reserva> atualizarTabela(){
         try {
             ReservaDao rDao = new ReservaDao();
             listaReservas = FXCollections.observableArrayList(rDao.listar());
             return listaReservas;
         } catch (SQLException ex) {
             Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
         }
        return null;
    }
}
