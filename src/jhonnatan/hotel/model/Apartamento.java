/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.model;

/**
 *
 * @author Admin
 */
public class Apartamento {
    private Integer id;
    private String numeroAP;
    private String andar;
   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numeroAP;
    }

    public void setNumero(String numero) {
        this.numeroAP = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    
}
