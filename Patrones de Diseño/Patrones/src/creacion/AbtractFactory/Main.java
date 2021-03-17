/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.AbtractFactory;

import java.util.Scanner;

/**
 *
 * @author LEO
 */
public class Main {
    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        //seleccion de la fabrica
        System.out.print("Escriba la fabrica");
        int typeAbstrac = t.nextInt();
        VehicleAbstractFactory abstractFactory = VehicleAbstractFactory.getFactory(typeAbstrac);
        // seleccion del vehiculo de la fabrica
        System.out.print("Escriba el tipo de Vehiculo");
        int typeVehicle = t.nextInt();
        Vehiculo vehiculo = abstractFactory.getVehiculo(typeVehicle);
        System.out.println("Llantas "+vehiculo.getWheels());
        System.out.println("Asientos "+vehiculo.getSeats());
    }
}
