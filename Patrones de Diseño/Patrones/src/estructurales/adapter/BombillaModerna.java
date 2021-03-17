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
public class BombillaModerna extends Bombilla {

    @Override
    public String encender() {
        return "encendido moderno";
    }

    @Override
    public String apagar() {
        return "apagado moderno";
    }

    @Override
    public String aumentarIntencidad() {
        return "aumentado moderno";
    }

}
