/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructurales.adapter;

import java.io.Console;

/**
 *
 * @author LEO
 */
public class main {

    public static void main(String[] args) {
        Bombilla[] lista_bombillas = new Bombilla[3];
        lista_bombillas[0] = new BombillaModerna();
        lista_bombillas[1] = new BombillaClasica();
        lista_bombillas[2] = new BombillaAdapter();

        for (int i = 0; i < lista_bombillas.length; i++) {
            System.out.println(lista_bombillas[i].encender());
        }
    }

}
