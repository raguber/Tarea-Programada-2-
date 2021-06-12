import java.util.ArrayList;
/**
 * Write a description of class Tareas here.
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11-6-2021
 */
public class Tarea{
    int id,dinero,esfuerzo,horas;
    boolean completada;
    String nombre,descripcion,fechaInicio,fechaFin;
    Responsable responsable;
    ArrayList <Recurso> recursos;//recursos que tiene la tarea
    ArrayList <Tarea> dependencias;//contiene las tareas de las que esta tarea depende. Para completar esta tarea, todas las tareas que
    //están en el ArrayList deben estar completadas
    /**
     * El constructor no solicita todos los valores. Solicita el nombre, dinero(0 si no es necesario), esfuerzo, y horas
       */
    public Tarea(String nom,int din,int esf,int hor,Responsable resp){
        nombre = nom;
        dinero = din;
        esfuerzo = esf;
        horas = hor;
        responsable = resp;
        completada = false;
        descripcion = "";
        recursos = new ArrayList<Recurso>();
        dependencias = new ArrayList<Tarea>();
    }
    
    public String modifiqueDescripcion(String desc){
        descripcion=desc;
        return"Se modificó la descripción de esta actividad: "+nombre;
    }
    
    public String modifiqueResponsable(Responsable resp){
        responsable=resp;
        return"Se modificó el responsable de esta actividad: "+nombre;
    }
    
    public void modifiqueHoras(int hor){
        horas-=hor;
    }
    
    public String agregueRecurso(Recurso rec){
        recursos.add(rec);
        return"La actividad "+nombre+" ahora necesita el recurso "+rec;
    }
    
    public String elimineRecurso(Recurso rec){
        recursos.remove(rec);
        return"La actividad "+nombre+" ya no necesita el recurso "+rec;
    }
    
    public String dependaDe(Tarea gen){
        dependencias.add(gen);
        return"La actividad "+nombre+" ahora depende de "+gen;
    }
    
    public String yaNoDependaDe(Tarea gen){
        dependencias.remove(gen);
        return"La actividad "+nombre+" ya no depende de "+gen;
    }
    
    public String complete(){
        boolean check=true;
        for(int i=0;i<dependencias.size();i++){
            if(dependencias.get(i).completada==false){
                check=false;
                break;
            }
        }
        if(check==true){
            completada=true;
            return"La actividad "+nombre+" completada!";
        }else{
            completada=false;
            return"La actividad "+nombre+" aún no puede completarse";
        }
    }
    
    //Si se completa una tarea de la que esta depende, cómo lo puede saber el Array de tareas que está aquí.
}
