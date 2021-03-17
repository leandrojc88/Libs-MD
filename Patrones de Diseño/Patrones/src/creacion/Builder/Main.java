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
public class Main {
    public static void main(String[] args) {
        /** Un cliente pidiendo una pizza. */
        Cocina cocina = new Cocina();
        PizzaBuilder hawai = new HawaiPizza();
        PizzaBuilder picante = new PicantePizza();
        
        cocina.setPizzaBuilder(hawai);
        cocina.construirPizza();//ejecutando el flujo del builder
        
        Pizza pizza = cocina.getPizza();
    }
}
