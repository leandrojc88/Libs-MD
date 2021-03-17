/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.methodFactory;

/**
 *
 * @author LEO
 */

// CREADOR y CREADOR CONCRETO
public class DBFactory {
    public enum DBType{ MySQL, Oracle, Postgres }
    
    public static DBAdapter getDBAdapter(DBType type){
        switch(type){
            case MySQL:
                return new MySQLAdapter();                
            case Oracle:
                return new OracleAdapter();
            case Postgres:
                return new PostgresAdapter();
            default:
                throw new IllegalArgumentException("No soportado");
        }
    }
}
