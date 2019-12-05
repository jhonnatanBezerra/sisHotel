/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import jhonnatan.hotel.model.Hospede;

/**
 * FXML Controller class
 *
 * @author Jhonnatan
 */
public class HospedagemEditarController implements Initializable {

    public static Hospede hospSelecionado;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public static Hospede getHospSelecionado() {
        return hospSelecionado;
    }

    public static void setHospSelecionado(Hospede hospSelecionado) {
        HospedagemEditarController.hospSelecionado = hospSelecionado;
    }
    
} 
