/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.AbtractFactory;

/**
 *
 * @author LEO
 */
// Producto Concreto
public class LuxuryCar implements Car{

    @Override
    public int getDoors() {
        return 2;
    }

    @Override
    public int getWheels() {
        return 4;
    }

    @Override
    public int getSeats() {
        return 2;
    }
    
}
