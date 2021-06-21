import java.io.Serializable;
/**
 * Objetos que representan recursos
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11/6/2021
 */
public class Recurso implements Serializable{
    String nombre,descripcion;
    int cantidad;
    boolean reusable;
    String identificacion;
    EntradaDatos entrada; 
    int codigoRecurso;
    public Recurso(int codR){
        entrada = new EntradaDatos();
        nombre = genereNombre();
        descripcion = genereDescripcion();
        cantidad= genereCantidadRecurso();
        reusable=false;
        codigoRecurso = codR;
        String indentificacion = genereIdentificacion();
    }
    
    public String genereNombre()
    {
        String nom ="";
        String mensaje =("Digite el nombre del recurso");
        nom = entrada.pidaTexto(mensaje);
        
        return nom;
    }
    public String genereDescripcion(){
        String mensaje ="Digite la descripción de este recurso";
        String desc = entrada.pidaTexto(mensaje);
        return desc;
    }
    public void modifiqueNombre(){
        String mensaje="Inserte el nuevo nombre de este recurso: ";
        nombre=entrada.pidaTexto(mensaje);
    }
    public void modifiqueDescripcion(){
        String mensaje="Inserte la nueva descripción de este recurso";
        descripcion=entrada.pidaTexto(mensaje);
    }
    public int genereCantidadRecurso()
    {
        int cantRecurso= 0;
        String mensaje =("Digite la cantidad de elementos que existen de "+nombre);
        cantRecurso = entrada.pidaNumero(mensaje,1);
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
        String mensaje="Inserte un número para aumentar la cantidad del recurso "+nombre;
        cantidad+=entrada.pidaNumero(mensaje,1);
    }
    public String deIdentificacion()
    {
        return identificacion;
    }
    public String deNombre()
    {
        return nombre;
    }
    public int deCantidad()
    {
        return cantidad;
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
