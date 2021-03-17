/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.Prototype;

/**
 *
 * @author LEO
 */
public class Main {
    public static void main(String[] args) {
        // CLiente
        ItemA uno = new ItemA("x-a", "y-a", "z-a");
        
        PrototypeItems clonado = uno.clone();
        
        System.err.println("uno - " + uno.getX());
        System.err.println("clonado - " + clonado.getX());
    }
}
