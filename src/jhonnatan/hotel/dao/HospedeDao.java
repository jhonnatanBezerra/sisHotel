/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
        JOptionPane.showMessageDialog(null, "Hospede cadastrado com sucesso!!!");
    }

    @Override
    public void atualizar(Hospede obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Hospede obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hospede> listar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
