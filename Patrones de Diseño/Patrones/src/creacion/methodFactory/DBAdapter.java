/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.methodFactory;

import java.sql.Connection;

/**
 *
 * @author LEO
 */

// PRODUCTO
public interface DBAdapter {
    Connection getConnection();
}
