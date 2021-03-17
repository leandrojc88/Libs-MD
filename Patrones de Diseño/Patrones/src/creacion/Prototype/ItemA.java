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

//Concrete Prototype
public class ItemA extends PrototypeItems{

    public ItemA(String x, String y, String z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public PrototypeItems clone() {
        return new ItemA(this.x, this.y, this.z);
    }
    
}
