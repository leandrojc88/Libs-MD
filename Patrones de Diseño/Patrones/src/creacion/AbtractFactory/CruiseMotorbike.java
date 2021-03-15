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
//Producto Concreto
public class CruiseMotorbike implements Motorbike{

    @Override
    public String getType() {
        return "Cruse!";
    }

    @Override
    public int getWheels() {
        return 2;
    }

    @Override
    public int getSeats() {
        return 2;
    }
    
}
