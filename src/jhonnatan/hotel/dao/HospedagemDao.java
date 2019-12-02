/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;


/**
 *
 * @author Admin
 */
public class HospedagemDao implements DaoGenerico<Hospedagem>{
    
    private Connection conn;
    private PreparedStatement ps = null;
    private Usuario usuarioLogado = Session.getUsuario();
    
    @Override
    public void salvar(Hospedagem h) throws SQLException{
        
        ApartamentoDao apDao = new ApartamentoDao();
        String sql = "INSERT INTO hospedagem (idHospede, idUsuario, numeroAP, acompanhantes, dataEntrada,  status) VALUES (?, ?, ?, ?, ?, ?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, h.getHospede().getID());
        ps.setInt(2, usuarioLogado.getId());
        ps.setString(3, h.getNumeroAP().getNumero());
        ps.setString(4, h.getQtdAcompanhante());
        LocalDate now = LocalDate.now();
        ps.setDate(5, java.sql.Date.valueOf(now));
        ps.setBoolean(6, true);
        ps.executeUpdate();
        
    }
                        

    @Override
    public void atualizar(Hospedagem obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Hospedagem obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hospedagem> listar() throws SQLException {
        List<Hospedagem> lHospedagem = new ArrayList<>();
         Hospedagem h = null;
         HospedeDao hDao = null;
         UsuarioDao userDao = null;
         ApartamentoDao apDao = null;
        String sql = "SELECT * FROM hospedagem WHERE status = '1'";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            h = new Hospedagem();
            hDao = new HospedeDao();
            h.setHospede(hDao.buscar(rs.getInt("idHospede")));
            h.setUsuario(userDao.buscar(rs.getInt("idUsuario")));
            h.setNumeroAP(apDao.buscar(rs.getInt("apartamento")));
            h.setQtdAcompanhante(rs.getString("acompanhantes"));
            h.setIdReserva(rs.getInt("idReserva"));
            h.setStatus(rs.getBoolean("status"));
        }
        return lHospedagem;
    }
    
    
    public Hospedagem buscar(Integer id)throws SQLException{
        Hospedagem h = null;
        HospedeDao hospede = null;
        UsuarioDao user = null;
        ApartamentoDao apDao = null;
        String sql = "SELECT * FROM hospedagem WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            h = new Hospedagem();
            hospede = new HospedeDao();
            apDao = new ApartamentoDao();
            h.setId(rs.getInt("id"));
            h.setHospede(hospede.buscar(rs.getInt("idHospede")));
            h.setUsuario(user.buscar(rs.getInt("idUsuario")));
            h.setNumeroAP(apDao.buscar(rs.getInt("numeroAP")));
            h.setQtdAcompanhante(rs.getString("acompanhantes"));
            h.setIdReserva(rs.getInt("idReserva"));
            h.setStatus(rs.getBoolean("status"));
            h.setDataEntrada(rs.getDate("dataEntrada"));
        }
        return h;
    }
  
}
