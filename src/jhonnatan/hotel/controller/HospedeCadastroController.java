/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import jhonnatan.hotel.dao.HospedeDao;
import jhonnatan.hotel.model.Hospede;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HospedeCadastroController implements Initializable {

    private Usuario usuarioLogado;
    
    
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtRG;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker txtDataNascimento;
    @FXML
    private TextField txtLogradouro;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtCEP;
    @FXML
    private TextField txtEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usuarioLogado = Session.getUsuario();
        System.out.println(Session.getUsuario().getId());
        System.out.println(usuarioLogado);
        System.out.println("O usuario logado no sistema possui a id: "+usuarioLogado.getId());
    }    

    @FXML
    private void cancelarCadastro(ActionEvent event) {
       HospedeController root = new HospedeController();
       root.abreHospedeController(event);   
    }

    @FXML
    private void cadastrarHospede(ActionEvent event)  {
        try {
            
            
            Hospede hospede = new Hospede();
            hospede.setNome(txtNome.getText());
            hospede.setRG(txtRG.getText());
            hospede.setCPF(txtCPF.getText());
            hospede.setTelefone(txtTelefone.getText());
            hospede.setEmail(txtEmail.getText());
            hospede.setDataNascimento(java.sql.Date.valueOf(txtDataNascimento.getValue()));
            hospede.setLogradouro(txtLogradouro.getText());
            hospede.setNumero(txtNumero.getText());
            hospede.setBairro(txtBairro.getText());
            hospede.setCidade(txtCidade.getText());
            hospede.setCEP(Integer.parseInt(txtCEP.getText()));
            hospede.setEstado(txtEstado.getText());
            hospede.setStatus("1");
           

            
            HospedeDao hosDao = new HospedeDao();
            hosDao.salvar(hospede); 
            HospedeController root = new HospedeController();
            root.abreHospedeController(event);
              
               
        } catch (SQLException ex) {
            Logger.getLogger(HospedeCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        
    }
    
    
    
    
}
