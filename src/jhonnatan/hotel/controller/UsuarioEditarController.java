/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        VBox usuarioRoot;
        try {
            usuarioRoot = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioFXML.fxml"));
            Scene cena = new Scene(usuarioRoot);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioEditarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btSalvarEdicao(ActionEvent event) {
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
