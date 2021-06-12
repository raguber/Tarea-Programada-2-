/**
 * Objetos que representan recursos
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11/6/2021
 */
public class Recurso{
    String nombre;
    int cantidad;
    boolean reusable;
    /**
     *El constructor acepta un nombre, una cantidad inicial y si es reusable 
       */
    public Recurso(String nom, int cant,boolean re){
        nombre=nom;
        cantidad=cant;
        reusable=re;
    }
    
    /**
     * Aumenta la cantidad de este recurso
     */
    public void mas(int cant){
        cantidad+=cant;
    }
    
    /**
     * Restar la cantidad de recursos. Si se resta más de lo que se tiene actualmente, la cantidad se iguala a 0.
     * Si el recurso es reusable, el método termina sin hacer cambios.
       */
    public void menos(int cant){
        if(reusable==false){
            if(cant<=cantidad){
                cantidad-=cant;
            }else if(cant>cantidad){
                cantidad=0;
            }
        }else{
            return;
        }
    }
}
