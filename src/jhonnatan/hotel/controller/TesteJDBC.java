/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.controller;

import java.sql.Connection;
import java.sql.SQLException;
import jhonnatan.hotel.jdbc.ConnectionFactory;

/**
 *
 * @author Jhonnatan
 */
public class TesteJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        System.out.println("Sucesso");
        conn.close();
        System.out.println("Conexão fechada");
    }
    
}
