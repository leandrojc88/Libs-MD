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
public class main {
    public static void main(String[] args) {
        DBAdapter dBAdapter = DBFactory.getDBAdapter(DBFactory.DBType.MySQL);
        Connection connection = dBAdapter.getConnection();
    }
}
