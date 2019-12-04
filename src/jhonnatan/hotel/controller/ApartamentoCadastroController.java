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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.ApartamentoDao;
import jhonnatan.hotel.model.Apartamento;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class ApartamentoCadastroController implements Initializable {
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtAndar;
    @FXML
    private Button btCancela;
    @FXML
    private Button btCadastro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btCancelaCadastro(ActionEvent event) {
        ApartamentosController pane = new ApartamentosController();
        pane.abreApListar(event);
    }

    @FXML
    private void btNovoUsuario(ActionEvent event) {
        ApartamentoDao apdap = new ApartamentoDao();
        Apartamento ap = new Apartamento();
        ap.setNumeroAP(txtNumero.getText());
        ap.setAndar(txtAndar.getText());
        
        try {
            apdap.salvar(ap);
            ApartamentosController apPane = new ApartamentosController();
            apPane.abreApListar(event);
        } catch (SQLException ex) {
            Logger.getLogger(ApartamentoCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Apartamento Cadastrado com Sucesso");
            
           
            
        
    }
    
}
