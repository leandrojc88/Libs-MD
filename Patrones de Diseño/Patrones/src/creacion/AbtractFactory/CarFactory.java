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
public class CarFactory extends VehicleAbstractFactory{
    public static final int FAMILY = 1;
    public static final int LUXURY = 2;

    @Override
    public Vehiculo getVehiculo(int type) {
        switch(type){
        case FAMILY:
            return new FamilyCar();
        case LUXURY:
            return new LuxuryCar();
        }
        return null;
    }
}
