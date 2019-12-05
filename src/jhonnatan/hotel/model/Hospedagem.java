/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Hospedagem {
    private Integer id;
    private Hospede hospede;
    private Usuario usuario;
    private Apartamento numeroAP;
    private String qtdAcompanhante;
    private Integer idReserva;
    private Boolean status;
    private Date dataEntrada;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Apartamento getNumeroAP() {
        return numeroAP;
    }

    public void setNumeroAP(Apartamento numeroAP) {
        this.numeroAP = numeroAP;
    }
    
    

    public String getQtdAcompanhante() {
        return qtdAcompanhante;
    }

    public void setQtdAcompanhante(String qtdAcompanhante) {
        this.qtdAcompanhante = qtdAcompanhante;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    
    
    
    
}
