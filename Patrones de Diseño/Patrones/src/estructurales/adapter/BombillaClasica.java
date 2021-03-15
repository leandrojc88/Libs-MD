/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurales.adapter;

/**
 *
 * @author LEO
 */
public class BombillaClasica extends Bombilla{

    @Override
    public String encender() {
        return "encendido clasico";
    }

    @Override
    public String apagar() {
        return "apagado clasico";
    }

    @Override
    public String aumentarIntencidad() {
        return "aumento clasico";
    }
    
}
