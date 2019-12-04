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
import jhonnatan.hotel.dao.UsuarioDao;
import jhonnatan.hotel.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UsuarioCadastroController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
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
        Stage stage = (Stage)btCancela.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btNovoUsuario(ActionEvent event) {
        UsuarioDao userDao = new UsuarioDao();
        Usuario user = new Usuario();
        user.setNome(txtNome.getText());
        user.setEmail(txtEmail.getText());
        user.setSenha(txtSenha.getText());
        
        try {
            userDao.salvar(user);
            Stage stage = (Stage)btCadastro.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
