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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.HospedeDao;
import jhonnatan.hotel.model.Hospede;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class EditarHospedeController implements Initializable {

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
    private Label lblID;
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
    
    
    private static Hospede hospEdit;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableUpdate();
    }    

    @FXML
    private void cancelarAtualizacao(ActionEvent event) {
        HospedeController root = new HospedeController();
        root.abreHospedeController(event);
    }

    @FXML
    private void editarHospede(ActionEvent event) {
        HospedeDao hDao = new HospedeDao();
        Hospede h = new Hospede();
        h.setID(Integer.parseInt(lblID.getText()));
        h.setNome(txtNome.getText());
        h.setRG(txtRG.getText());
        h.setCPF(txtCPF.getText());
        h.setTelefone(txtTelefone.getText());
        h.setEmail(txtEmail.getText());
        h.setDataNascimento(java.sql.Date.valueOf(txtDataNascimento.getValue()));
        h.setLogradouro(txtLogradouro.getText());
        h.setNumero(txtNumero.getText());
        h.setBairro(txtBairro.getText());
        h.setCidade(txtCidade.getText());
        h.setCEP(Integer.parseInt(txtCEP.getText()));
        h.setEstado(txtEstado.getText());
        h.setStatus("1");
        
        try {
            hDao.atualizar(h);
            JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso!!!");
            HospedeController root = new HospedeController();
            root.abreHospedeController(event);
        } catch (SQLException ex) {
            Logger.getLogger(EditarHospedeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void initTableUpdate(){
        

        lblID.setText(hospEdit.getID().toString());
        txtNome.setText(hospEdit.getNome());
        txtRG.setText(hospEdit.getRG());
        txtCPF.setText(hospEdit.getCPF());
        txtTelefone.setText(hospEdit.getTelefone());
        txtEmail.setText(hospEdit.getEmail());
        txtDataNascimento.setValue(hospEdit.getDataNascimento().toLocalDate());
        txtLogradouro.setText(hospEdit.getLogradouro());
        txtNumero.setText(hospEdit.getNumero());
        txtBairro.setText(hospEdit.getBairro());
        txtCidade.setText(hospEdit.getCidade());
        txtCEP.setText(Integer.toString(hospEdit.getCEP()));
        txtEstado.setText(hospEdit.getEstado());
    }
    
    public static Hospede getHospEdit() {
        return hospEdit;
    }

    public static void setHospEdit(Hospede hospEdit) {
        EditarHospedeController.hospEdit = hospEdit;
    }
    
    
    
}
