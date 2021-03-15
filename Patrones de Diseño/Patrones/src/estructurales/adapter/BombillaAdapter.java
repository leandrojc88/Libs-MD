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
public class BombillaAdapter extends Bombilla {

    // objeto que se adaptara
    Vela vela = new Vela();

    @Override
    public String encender() {
        return vela.prender();
    }

    @Override
    public String apagar() {
        return vela.apagar();
    }

    @Override
    public String aumentarIntencidad() {
        return vela.prender() + " meterle gasolina";
    }

}
