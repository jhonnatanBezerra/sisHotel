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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jhonnatan.hotel.dao.ApartamentoDao;
import jhonnatan.hotel.model.Apartamento;
import jhonnatan.hotel.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class ApartamentosController implements Initializable {
    @FXML
    private TableView<Apartamento> tabelaAP;
   
    @FXML
    private TableColumn<Apartamento, Integer> clmID;
    @FXML
    private TableColumn<Apartamento, String> clmNumero;
    @FXML
    private TableColumn<Apartamento, String> clmAndar;
    @FXML
    private Button btCancela;
    @FXML
    private TextField txtBusca;
    @FXML
    private Button btExcluir;
    
    private Apartamento apSelecionado;
    private ObservableList<Apartamento> listaAP = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTabelaAP();
        
        tabelaAP.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                apSelecionado = (Apartamento) newValue;
                System.out.println("Apartamento selecionado numero: "+apSelecionado.getNumeroAP());
                
            }
        });
    }    

    @FXML
    private void btVoltaPrincipal(ActionEvent event) {
        Stage stage = (Stage)btCancela.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void editaAP(ActionEvent event) {
        ApartamentosEditarController.setEditAP(apSelecionado);
        
        if(apSelecionado != null){
           try {
                VBox editaAP;
                editaAP = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ApartamentosEditarFXML.fxml"));
                Scene cena = new Scene(editaAP);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(cena);
                stage.show();
                        
            } catch (IOException ex) {
                Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        }


    @FXML
    private void btBuscar(ActionEvent event) {
        tabelaAP.setItems(buscar());
    }

    
    
    @FXML
    private void btNovoAP(ActionEvent event) {
        VBox cadastroAP = new VBox();
        try {
            cadastroAP = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ApartamentoCadastroFXML.fxml"));
            Scene cena = new Scene(cadastroAP);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTabelaAP(){
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        clmNumero.setCellValueFactory(new PropertyValueFactory("numeroAP"));
        clmAndar.setCellValueFactory(new PropertyValueFactory("andar"));
        tabelaAP.setItems(attTabelaAP());
    }
    
    private ObservableList<Apartamento> attTabelaAP(){
        ApartamentoDao apdao = new ApartamentoDao();
        try {
            listaAP = FXCollections.observableArrayList(apdao.listar());
            return listaAP;
        } catch (SQLException ex) {
            Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private ObservableList<Apartamento> buscar(){
        ObservableList<Apartamento> apPesquisa = FXCollections.observableArrayList();
        
        for(int x = 0; x < listaAP.size(); x++){
            if(listaAP.get(x).getNumeroAP().contains(txtBusca.getText())){
            apPesquisa.add(listaAP.get(x));
            }
        }
        return apPesquisa;
    }
    
    public void abreApListar(ActionEvent event){
        try {
            AnchorPane paneApLista = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/ApartamentosFXML.fxml"));
            Scene cena = new Scene(paneApLista);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btExcluirAP(ActionEvent event) {
        if(apSelecionado != null){
            try{
                ApartamentoDao apdao = new ApartamentoDao();
                int res = JOptionPane.showConfirmDialog(null, "Deseja excluir o Apartamento: "+apSelecionado.getNumeroAP(),"Excluir",JOptionPane.YES_NO_OPTION);
                if(res == 0){
                    apdao.deletar(apSelecionado);
                    initTabelaAP();
                }else{
                    initTabelaAP();
                }
            }catch(SQLException ex){
                Logger.getLogger(ApartamentosController.class.getName()).log(Level.SEVERE, null, ex);    
            }
        }else{    
            JOptionPane.showMessageDialog(null, "Selecione um Apartamento para deletar");
        }
    }
    
    
    
}

    
    

