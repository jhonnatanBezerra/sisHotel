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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.UsuarioDao;
import jhonnatan.hotel.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UsuarioEditarController implements Initializable {

    @FXML
    private Label lblIdUsuario;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    
    private static Usuario editUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabelaUserUpdate();
    }    

    @FXML
    private void btCancelaEdicao(ActionEvent event) {
        
        UsuarioController pane = new UsuarioController();
        pane.abreUsuarioListar(event);
        
    }

    @FXML
    private void btSalvarEdicao(ActionEvent event) {
        UsuarioDao userDao = new UsuarioDao();
        Usuario user = new Usuario();
        user.setId(editUser.getId());
        user.setNome(txtNome.getText());
        user.setEmail(txtEmail.getText());
        user.setSenha(txtSenha.getText());
        try {
            userDao.atualizar(user);
            JOptionPane.showMessageDialog(null, "Atualização Efetuada");
            UsuarioController pane = new UsuarioController();
            pane.abreUsuarioListar(event);
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioEditarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static Usuario getEditUser() {
        return editUser;
    }

    public static void setEditUser(Usuario editUser) {
        UsuarioEditarController.editUser = editUser;
    }
    
    public void initTabelaUserUpdate(){
        lblIdUsuario.setText("ID do Usuario: "+editUser.getId().toString());
        txtNome.setText(editUser.getNome());
        txtEmail.setText(editUser.getEmail());
        txtSenha.setText(editUser.getSenha());
    }
}
