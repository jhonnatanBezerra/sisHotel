/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateStringConverter;
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
        
        String sql = "INSERT INTO hospedagem (idHospede, idUsuario, numeroAP, acompanhantes, dataEntrada,  status) VALUES (?, ?, ?, ?, ?, ?)";
        conn = ConnectionFactory.getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, h.getHospede().getID());
        ps.setInt(2, usuarioLogado.getId());
        ps.setString(3, h.getNumeroAP());
        ps.setString(4, h.getQtdAcompanhante());
        LocalDate now = LocalDate.now();
        ps.setDate(5, java.sql.Date.valueOf(now));
        ps.setBoolean(6, true);
        
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
            HospedeDao hDao = new HospedeDao();
            h.setHospede(hDao.buscar(rs.getInt("idHospede")));
            h.setUsuario(usuarioLogado);
            h.setNumeroAP(rs.getString("numeroAP"));
            h.setQtdAcompanhante(rs.getString("acompanhantes"));
            h.setIdReserva(rs.getInt("idReserva"));
            h.setStatus(rs.getBoolean("status"));
        }
        return lHospedagem;
    }

   
   
    
}
