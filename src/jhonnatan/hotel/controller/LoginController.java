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
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.UsuarioDao;
import jhonnatan.hotel.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class LoginController implements Initializable {

    @FXML
    private VBox rootPane;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btLogin;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void logarSistema(ActionEvent event) {

        try {
            Usuario user = new Usuario();
            user.setNome(txtUsuario.getText());
            user.setSenha(txtSenha.getText());
        
            UsuarioDao userDao = new UsuarioDao();
            boolean teste = userDao.verifica(user);
            
            if(teste == true){
                try {
                    BorderPane principal; 
                    principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/PrincipalFXML.fxml"));
                    Scene cena = new Scene(principal);
                    //erro nao converte vbox para borderpane
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(cena);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario ou senha invalido");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
