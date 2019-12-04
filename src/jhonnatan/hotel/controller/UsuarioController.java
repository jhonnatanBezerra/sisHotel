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
import jhonnatan.hotel.dao.UsuarioDao;
import jhonnatan.hotel.model.Usuario;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tabelausuarios;
    @FXML
    private TableColumn<Usuario, Integer> clmID;
    @FXML
    private TableColumn<Usuario, String> clmNome;
    @FXML
    private TableColumn<Usuario, String> clmEmail;
    @FXML
    private TextField txtBusca;

    private Usuario usuarioSelecionado;
    
    private ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
    @FXML
    private Button btCancela;
    @FXML
    private Button btExcluir;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTabelaUsuarios();
        
        tabelausuarios.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                usuarioSelecionado = (Usuario) newValue;
            }
        });
    }    

    @FXML
    private void btVoltaPrincipal(ActionEvent event) {
        Stage stage = (Stage)btCancela.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editaUser(ActionEvent event) {
        UsuarioEditarController.setEditUser(usuarioSelecionado);
        if(usuarioSelecionado != null){
            try {
                VBox editaUsuario;
                editaUsuario = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioEditarFXML.fxml"));
                Scene cena = new Scene(editaUsuario);
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(cena);
                stage.show();
                        
            } catch (IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btBuscar(ActionEvent event) {
        tabelausuarios.setItems(buscar());
    }

    @FXML
    private void btNovoUser(ActionEvent event) {
        VBox cadastroUser = new VBox();
        try {
            cadastroUser = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioCadastroFXML.fxml"));
            Scene cena = new Scene(cadastroUser);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initTabelaUsuarios(){
        clmID.setCellValueFactory(new PropertyValueFactory("id"));
        clmNome.setCellValueFactory(new PropertyValueFactory("nome"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));
        tabelausuarios.setItems(attTabela());
    }
    
    private ObservableList<Usuario> attTabela(){
        UsuarioDao uDao = new UsuarioDao();
        try {
            listaUsuario = FXCollections.observableArrayList(uDao.listar());
            return listaUsuario;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private ObservableList<Usuario> buscar(){
        ObservableList<Usuario> usuarioPesquisa = FXCollections.observableArrayList();
        for(int x = 0; x < listaUsuario.size() ; x++){
            if(listaUsuario.get(x).getNome().toLowerCase().contains(txtBusca.getText().toLowerCase())){
                usuarioPesquisa.add(listaUsuario.get(x));
            }
        }
        return usuarioPesquisa;
    }
    
    public void abreUsuarioListar(ActionEvent event){
        
        AnchorPane paneUsuarioLista = new AnchorPane();
        try {
            paneUsuarioLista = FXMLLoader.load(getClass().getResource("/jhonnatan/hotel/view/UsuarioFXML.fxml"));
            Scene cena = new Scene(paneUsuarioLista);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(cena);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(UsuarioEditarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void btExcluirUser(ActionEvent event) {
        if(usuarioSelecionado != null){
            try {
                UsuarioDao udao = new UsuarioDao();
                int res = JOptionPane.showConfirmDialog(null, "Deseja excluir o Usuario: "+usuarioSelecionado.getNome(),"Excluir",JOptionPane.YES_NO_OPTION);
                if(res == 0){
                    udao.deletar(usuarioSelecionado);
                    initTabelaUsuarios();
                }else{
                    System.out.println("Nao deleta");
                    initTabelaUsuarios();
                }

            } catch (SQLException ex) {
                Logger.getLogger(HospedeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um Hospede para deletar");
        }
    }
    
}
