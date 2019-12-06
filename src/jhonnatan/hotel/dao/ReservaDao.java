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
import jhonnatan.hotel.jdbc.ConnectionFactory;
import jhonnatan.hotel.model.Reserva;
import jhonnatan.hotel.model.Usuario;
import jhonnatan.hotel.util.Session;

/**
 *
 * @author Jhonnatan
 */
public class ReservaDao implements DaoGenerico<Reserva> {

    private Connection conn;
    private PreparedStatement ps = null;
    private Usuario usuarioLogado = Session.getUsuario();
    
    @Override
    public void salvar(Reserva res) throws SQLException {
        String sql = "INSERT INTO reserva(nome, dataChegada, qtdPessoas, diarias, status, usuario) VALUES (?,?,?,?,?,?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, res.getNome());
        ps.setDate(2, res.getDataChegada());
        ps.setInt(3, res.getQtdPessoas());
        ps.setInt(4, res.getDiarias());
        ps.setBoolean(5, res.getStatus());
        ps.setInt(6, usuarioLogado.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
        
    }

    @Override
    public void atualizar(Reserva res) throws SQLException {
        String sql = "UPDATE reserva SET nome = ?, dataChegada = ?, qtdPessoas = ?, diarias = ? WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, res.getNome());
        ps.setDate(2, res.getDataChegada());
        ps.setInt(3, res.getQtdPessoas());
        ps.setInt(4, res.getDiarias());
        ps.setInt(5, res.getId());
        ps.executeUpdate();
        conn.close();
        ps.close();
    }   

    @Override
    public void deletar(Reserva res) throws SQLException {
        String sql = "DELETE FROM reserva WHERE id = ?"; 
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, res.getId());
        ps.execute();
        conn.close();
        ps.close();
    }

    @Override
    public List<Reserva> listar() throws SQLException {
        List<Reserva> lReserva = new ArrayList<>();
        Reserva r;
        String sql = "SELECT * FROM reserva";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            r = new Reserva();
            r.setId(rs.getInt("id"));
            r.setNome(rs.getString("nome"));
            r.setDataChegada(rs.getDate("dataChegada"));
            r.setQtdPessoas(rs.getInt("qtdPessoas"));
            r.setDiarias(rs.getInt("diarias"));
            r.setStatus(rs.getBoolean("status"));
            lReserva.add(r);
        }
        return lReserva;
        
    }
    public Reserva buscar (Integer id)throws SQLException{
        Reserva r = null;
        String sql = "SELECT * FROM reserva WHERE id = ?";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            r = new Reserva();
            UsuarioDao udao = new UsuarioDao();
            r.setId(rs.getInt("id"));
            r.setNome(rs.getString("nome"));
            r.setDataChegada(rs.getDate("dataChegada"));
            r.setQtdPessoas(rs.getInt("qtdPessoas"));
            r.setDiarias(rs.getInt("diarias"));
            r.setStatus(rs.getBoolean("status"));
            r.setUsuario(udao.buscar(rs.getInt("usuario")));
        }
        return r;
    }
}
