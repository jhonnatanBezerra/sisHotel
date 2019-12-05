/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.ApartamentoDao;
import jhonnatan.hotel.dao.HospedagemDao;
import jhonnatan.hotel.dao.HospedeDao;
import jhonnatan.hotel.model.Apartamento;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Hospede;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class HospedagemCadastroController implements Initializable {

    @FXML
    private ComboBox<Apartamento> comboAP;
    @FXML
    private TextField txtAcompanhante;
    private Apartamento idApSelecionado = null;
    
    
    
    
    @FXML
    private TextField txtBusca;
    @FXML
    private DatePicker dateData ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate now = LocalDate.now();
        HospedagemEditarController ed = new HospedagemEditarController();
        dateData.setValue((now));
        dateData.setDisable(true);
        
        if(ed.getHospSelecionado() != null){
            txtBusca.setText(ed.getHospSelecionado().getNome());
            
        }
        
        initComboBox();
        comboAP.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                idApSelecionado = (Apartamento) newValue; 
            }
            
        });
    }


public void abreCadastroHosp(ActionEvent event) throws IOException{
    BorderPane cadastroHosp = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemCadastroFXML.fxml"));
    Scene cena = new Scene(cadastroHosp);
    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    stage.setScene(cena);
    stage.show();
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

    private void initComboBox() {
        ApartamentosController ap = new ApartamentosController();
        comboAP.setItems(ap.attTabelaAP());
    }

    @FXML
    private void cadastroHospedagem(ActionEvent event) {
        HospedagemEditarController ed = new HospedagemEditarController();
        if(ed.getHospSelecionado() != null){
            try {
            HospedagemDao hospedagemDao = new HospedagemDao();
            Hospedagem h = new Hospedagem();
            HospedeDao hosp = new HospedeDao();
            ApartamentoDao apdao = new ApartamentoDao();
            h.setHospede(hosp.buscar(ed.getHospSelecionado().getID()));
            h.setNumeroAP(apdao.buscar(idApSelecionado.getId()));
            h.setQtdAcompanhante(txtAcompanhante.getText());
            
                hospedagemDao.salvar(h);
                JOptionPane.showMessageDialog(null, "Hospedagem Realizada para:"+ed.getHospSelecionado().getNome());
                HospedagemController hospAbre = new HospedagemController();
                hospAbre.abreHospedagemController(event);
                
            } catch (SQLException ex) {
                Logger.getLogger(HospedagemCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HospedagemCadastroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um hospede");
        }
    }

    @FXML
    private void btPesquisaHospede(ActionEvent event) {
        
        PesquisaHospedeController pesquisa = new PesquisaHospedeController();
        try {
            pesquisa.abrePesquisaHospede(event);
        } catch (IOException ex) {
            Logger.getLogger(HospedagemCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void abreHospedagemControler(ActionEvent event) throws IOException{
        BorderPane paneHospedagemController = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemCadastroFXML.fxml"));
        Scene cena = new Scene(paneHospedagemController);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(cena);
        stage.show();
    }
    
    
    
    
}

 
