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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Hospede;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;


/**
 *
 * @author Admin
 */
public class HospedeDao implements DaoGenerico<Hospede>{
    private Usuario usuarioLogado = Session.getUsuario();
    private Connection conn;
    private PreparedStatement ps = null;
    
    
    @Override
    public void salvar(Hospede hosp) throws SQLException {
        String sql = "INSERT INTO hospede (nome, rg, cpf, telefone, email, dataNascimento, logradouro, numero, bairro, cidade, cep, estado, status, usuario) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, hosp.getNome());
        ps.setString(2, hosp.getRG());
        ps.setString(3, hosp.getCPF());
        ps.setString(4, hosp.getTelefone());
        ps.setString(5, hosp.getEmail());
        ps.setDate(6, hosp.getDataNascimento());
        ps.setString(7, hosp.getLogradouro());
        ps.setString(8, hosp.getNumero());
        ps.setString(9, hosp.getBairro());
        ps.setString(10, hosp.getCidade());
        ps.setInt(11, hosp.getCEP());
        ps.setString(12, hosp.getEstado());
        ps.setBoolean(13, true);
        ps.setInt(14, usuarioLogado.getId());
        ps.executeUpdate();
        conn.close();
        ps.close();
        
    }

    @Override
    public void atualizar(Hospede hosp) throws SQLException {
    
        String sql = "UPDATE hospede SET nome = ?, rg = ?, cpf = ?, telefone = ?, email = ?, dataNascimento = ?, logradouro = ?, numero = ?, bairro = ?, cidade = ?, cep = ?, estado = ?, status = ?, usuario = ? WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, hosp.getNome());
        ps.setString(2, hosp.getRG());
        ps.setString(3, hosp.getCPF());
        ps.setString(4, hosp.getTelefone());
        ps.setString(5, hosp.getEmail());
        ps.setDate(6, hosp.getDataNascimento());
        ps.setString(7, hosp.getLogradouro());
        ps.setString(8, hosp.getNumero());
        ps.setString(9, hosp.getBairro());
        ps.setString(10, hosp.getCidade());
        ps.setInt(11, hosp.getCEP());
        ps.setString(12, hosp.getEstado());
        ps.setBoolean(13, true);
        ps.setInt(14, usuarioLogado.getId());
        ps.setInt(15, hosp.getID());
        ps.executeUpdate();
        conn.close();
        ps.close();
        
    }

    @Override
    public void deletar(Hospede h) throws SQLException {
        String sql = "DELETE FROM hospede WHERE id=?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, h.getID());
        ps.execute();
        ps.close();
        conn.close();
    }

    @Override
    public List<Hospede> listar() throws SQLException {
        List<Hospede> lHospede = new ArrayList<>();
        String sql = "SELECT * FROM hospede WHERE status = '1'";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Hospede h = new Hospede();
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
            lHospede.add(h);
            
        }

        rs.close();
        conn.close();
        ps.close();
        return lHospede;
        
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
        rs.close();
        conn.close();
        ps.close();
        return h;
    }
    
}
