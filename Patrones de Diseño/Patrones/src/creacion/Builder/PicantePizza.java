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

//ConcreteBuilder
public class PicantePizza extends PizzaBuilder{

    @Override
    public void buildMasa() {
        pizza.setMasa("suave");
    }

    @Override
    public void buildSalsa() {
        pizza.setSalsa("dulce");
    }

    @Override
    public void buildRelleno() {
      pizza.setRelleno("chirizo");
    }
}
