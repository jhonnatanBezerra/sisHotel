/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jhonnatan.hotel.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jhonnatan
 * @param <T>
 */
public interface DaoGenerico<T> {
    void salvar(T obj) throws SQLException;
    void atualizar (T obj) throws SQLException;
    void deletar (T obj) throws SQLException;
    List<T> listar() throws SQLException;
     
}
