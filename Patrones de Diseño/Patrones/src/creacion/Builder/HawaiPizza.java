/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.Builder;

/**
 *
 * @author LEO
 */
public class HawaiPizza extends PizzaBuilder{

    @Override
    public void buildMasa() {
        pizza.setMasa("dura");
    }
    @Override
    public void buildSalsa() {
        pizza.setSalsa("Hawai");
    }

    @Override
    public void buildRelleno() {
        pizza.setRelleno("jamo");
    }
    
}
