/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jhonnatan.hotel.dao.HospedagemDao;
import jhonnatan.hotel.dao.HospedeDao;
import jhonnatan.hotel.dao.UsuarioDao;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Hospede;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PrincipalController implements Initializable {

    private Usuario usuarioLogado;
    
    @FXML
    private Label lblUsuario;
    @FXML
    private MenuBar menuBar;
    @FXML
    private BorderPane panePrincipal;
    @FXML
    private Menu btRelatiorios;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usuarioLogado = Session.getUsuario();
        lblUsuario.setText("Bem-Vindo: "+usuarioLogado.getNome());
        LocalDate now = LocalDate.now();
        
        
    }    

    @FXML
    private void abreHospede(ActionEvent event) {
        
        try {
            BorderPane principal;
            principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedeFXML.fxml"));
            Scene cena = new Scene(principal);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void sairDaAplicacao(ActionEvent event) throws JRException {
        

         VBox root;
        try {
            root = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/LoginFXML.fxml"));
            Scene cena = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    private void btHospedagem(ActionEvent event) {
        BorderPane hospedagem;
        try {
            hospedagem = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/HospedagemFXML.fxml"));
            Scene cena = new Scene(hospedagem);
            Stage stage =  new Stage();//(Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrePrincipal(ActionEvent event){
        BorderPane principal; 
        try {
            principal = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/PrincipalFXML.fxml"));
            Scene cena = new Scene(principal);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void abreUsuario(ActionEvent event) {
        AnchorPane paneUsuario = new AnchorPane();
        try {
            paneUsuario = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioFXML.fxml"));
            Scene cena = new Scene(paneUsuario);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void abreApartamento(ActionEvent event) {
        AnchorPane paneAP = new AnchorPane();
        try{
            paneAP = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ApartamentosFXML.fxml"));
            Scene cena = new Scene(paneAP);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch(IOException ex){
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void fechaSistema(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void relatorioHospedes(ActionEvent event) {
        try {
            
            HospedeDao hdao = new HospedeDao();
            
            InputStream stream = getClass().getResourceAsStream("/jhonnatan/hotel/view/RelatorioHospede.jrxml");
            JasperReport report = JasperCompileManager.compileReport(stream);
            JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(hdao.listar()));
            JasperViewer.viewReport(print,false);
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void relatorioHospedagens(ActionEvent event) {
        try {
            
            HospedagemDao hdao = new HospedagemDao();
            
            InputStream stream = getClass().getResourceAsStream("/jhonnatan/hotel/view/RelatorioHospedagem.jrxml");
            JasperReport report = JasperCompileManager.compileReport(stream);
            JasperPrint print = JasperFillManager.fillReport(report,null, new JRBeanCollectionDataSource(hdao.listar()));
            JasperViewer.viewReport(print,false);
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      @FXML
    void btAbreReserva(ActionEvent event) {
        AnchorPane pane = new AnchorPane();
        try {
            pane = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ReservaFXML.fxml"));
            Scene cena = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
