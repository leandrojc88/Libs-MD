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
public class MySinglenton {
    private String x;
     // crear intancia `static` de la propia clase para que sea unica 
    private static MySinglenton mySinglenton;
    
     //crear constructor privado para que no pueda ser referenciado desde otro paquete
    private MySinglenton(String x){
        this.x = x;
    }
    
    //metodo publico que crea la instancia y si ya excite la retorna
    public static MySinglenton getMySinglenton(String x){
        if(mySinglenton == null)
            mySinglenton = new MySinglenton(x);
        return mySinglenton;
    }

    /**
     * @return the x
     */
    public String getX() {
        return x;
    }
    
}
