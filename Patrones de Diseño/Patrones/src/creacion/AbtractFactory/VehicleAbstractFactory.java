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
public abstract class VehicleAbstractFactory {
    
    public static final int CarFactory = 1;
    public static final int MotorbikeFactory = 2;
    
    public abstract Vehiculo getVehiculo(int type);
    // retorna unas instancia de si misma
    public static VehicleAbstractFactory getFactory(int type){
        switch(type){
            case CarFactory:
                return new CarFactory();
            case MotorbikeFactory:
                return new MotorbikeFactory();
        }
        return null;
    }
    
}
