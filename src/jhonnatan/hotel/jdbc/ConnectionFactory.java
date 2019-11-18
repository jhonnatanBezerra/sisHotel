/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jhonnatan
 */
public class ConnectionFactory {
    public static Connection getConnection(){
        try{
            //return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Max-Aula7", "postgres", "root");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sis_hotel", "root", "");
            
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
