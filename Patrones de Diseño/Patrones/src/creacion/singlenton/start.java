/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion.singlenton;

/**
 *
 * @author LEO
 */
public class start {
    
    public static void main(String[] args) {
        MySinglenton a = MySinglenton.getMySinglenton("a");
        MySinglenton b = MySinglenton.getMySinglenton("b");
        
        System.out.println(a.getX());
        System.out.println(b.getX());
    }    
}
