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
public class MotorbikeFactory extends VehicleAbstractFactory{
    
    public static final int SPORT = 1;
    public static final int CRUISE = 2;
    
    
    @Override
    public Vehiculo getVehiculo(int type) {
        switch(type){
            case SPORT:
                return new SportMotorbike();
            case CRUISE:
                return new CruiseMotorbike();
        }
        return null;
    }
    
}
