import java.io.Serializable;
/**
 * Objetos que representan recursos
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11/6/2021
 */
public class Recurso implements Serializable{
    String nombre;
    int cantidad;
    boolean reusable;
    String identificacion;
    EntradaDatos entrada; 
    int codigoRecurso;
    public Recurso(int codR){
        nombre = genereNombre();
        cantidad= genereCantidadRecurso();
        reusable=false;
        codigoRecurso = codR;
        String indentificacion = genereIdentificacion();
    }
    
    public void modifiqueNombre(){
        String mensaje="Inserte el nuevo nombre de este recurso: ";
        nombre=entrada.pedirTexto(mensaje);
    }
    public String genereNombre()
    {
        String nom ="";
        String mensaje =("Digite el nombre del recurso");
        nom = entrada.pedirTexto(mensaje);
        
        return nom;
    }
    public int genereCantidadRecurso()
    {
        int cantRecurso= 0;
        String mensaje =("Digite la cantidad de elementos que existen de "+nombre);
        cantRecurso = entrada.pedirNumero(mensaje,1);
        return cantRecurso;
    }
    public String genereIdentificacion()
    {
        String id = nombre+codigoRecurso;
        return id;
    }
    public void modifiqueCodigoRecurso(int cod)
    {
        codigoRecurso = cod;
        genereIdentificacion();
        
    }
    
    public void hagaReusable(){
        reusable=true;
    }
    
    public void hagaAgotable(){
        reusable=false;
    }
    
    /**
     * Aumenta la cantidad de este recurso
     */
    public void mas(){
        String mensaje="Inserte un número para aumentar la cantidad";
        cantidad+=entrada.pedirNumero(mensaje,1);
    }
    public String deIdentificacion()
    {
        return identificacion;
    }
    public String deNombre()
    {
    
        return nombre;
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
