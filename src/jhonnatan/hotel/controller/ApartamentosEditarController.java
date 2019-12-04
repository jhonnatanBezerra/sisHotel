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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.ApartamentoDao;
import jhonnatan.hotel.model.Apartamento;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class ApartamentosEditarController implements Initializable {
    
    @FXML
    private TextField txtNumeroAP;
    @FXML
    private TextField txtAndar;
    
    @FXML
    private Button btCancela;
    private static Apartamento editAP;
    @FXML
    private Label lblIdAP;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabelaApUpdate();
    }    

    @FXML
    private void btCancelaEdicao(ActionEvent event) {
        ApartamentosController pane = new ApartamentosController();
        pane.abreApListar(event);
    }

    @FXML
    private void btSalvarEdicao(ActionEvent event) {
        ApartamentoDao apdao = new ApartamentoDao();
        Apartamento ap = new Apartamento();
        ap.setId(editAP.getId());
        ap.setNumeroAP(txtNumeroAP.getText());
        ap.setAndar(txtAndar.getText());
        try{
            apdao.atualizar(ap);
            JOptionPane.showMessageDialog(null, "Atualização Efetuada");
            ApartamentosController pane = new ApartamentosController();
            pane.abreApListar(event);
        }catch(SQLException ex){
            Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Apartamento getEditAP() {
        return editAP;
    }

    public static void setEditAP(Apartamento editAP) {
        ApartamentosEditarController.editAP = editAP;
    }
    
    public void initTabelaApUpdate(){
        lblIdAP.setText("ID do Apartamento: "+editAP.getId());
        txtNumeroAP.setText(editAP.getNumeroAP());
        txtAndar.setText(editAP.getAndar());
    }
}
