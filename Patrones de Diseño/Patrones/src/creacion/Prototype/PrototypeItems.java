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
// Prototype
public abstract class PrototypeItems {
    
    String x;
    String y;
    String z;

    public String getX() {return x;}

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }
    
    public abstract PrototypeItems clone();
}
