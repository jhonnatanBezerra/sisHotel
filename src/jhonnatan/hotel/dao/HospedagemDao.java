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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Hospedagem;
import jhonnatan.hotel.model.Hospede;
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
        
        String sql = "INSERT INTO hospedagem (idHospede, idUsuario, numeroAP, acompanhantes,  status) VALUES (?, ?, ?, ?, ?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, h.getHospede().getID());
        ps.setInt(2, usuarioLogado.getId());
        ps.setString(3, h.getNumeroAP());
        ps.setString(4, h.getQtdAcompanhante());
        ps.setBoolean(5, true);
        
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
        String sql = "SELECT * FROM hospedagem WHERE status = '1'";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Hospedagem h = new Hospedagem();
            h.setHospede(buscar(rs.getInt("idHospede")));
            h.setUsuario(usuarioLogado);
            h.setNumeroAP(rs.getString("numeroAP"));
            h.setQtdAcompanhante(rs.getString("acompanhantes"));
            h.setIdReserva(rs.getInt("idReserva"));
            h.setStatus(rs.getBoolean("status"));
        }
        return lHospedagem;
    }
    
    public Hospede  buscar(Integer id) throws SQLException{
        Hospede h = null;
        String sql = "SELECT * FROM hospede WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HospedagemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            h = new Hospede();
            h.setID(rs.getInt("id"));
            h.setNome(rs.getString("nome"));
            h.setRG(rs.getString("rg"));
            h.setCPF(rs.getString("cpf"));
            h.setTelefone(rs.getString("telefone"));
            h.setEmail(rs.getString("email"));
            h.setDataNascimento(rs.getDate("dataNascimento"));
            h.setLogradouro(rs.getString("logradouro"));
            h.setNumero(rs.getString("numero"));
            h.setBairro(rs.getString("bairro"));
            h.setCidade(rs.getString("cidade"));
            h.setCEP(rs.getInt("cep"));
            h.setEstado(rs.getString("estado"));
            h.setStatus(rs.getString("status"));
        }
        return h;
    }
    
}
