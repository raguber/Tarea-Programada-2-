import java.util.ArrayList;
/**
 * Write a description of class Tareas here.
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11-6-2021
 */
public class Tarea{
    //cada tarea, deberia tener un atibuto con el nombre de la lista a la cual pertenece, mas que todo es util cuando se muestren todas las tareas
        //R/Ok
    //O tareas asignadas a determinado responsable (cuando se muestra la informacion de un responsable)
        //R/mejor en clase Responsable
    //Indirectamente, si el nombre de las listas, u otras cosas se modifican (deberia de poderse) se deberia actualizar la informacion
    //Esto indica que hay que hacer un metodo que cambie el nombre de listaPerteneciente;
        //R/Ok
    
    //My to do:
    //Agregar un atributo con el nombre de la lista a la que pertenece esta tarea
    //Crear métodos para cambiar varios atributos de las listas
    //Cambiar el constructor como está en Lista. Usar EntradaDatos como en Lista para aligerar Gestor
    
    String listaPerteneciente;
    int id,dinero,esfuerzo,horas;
    boolean completada;
    String nombre,descripcion,fechaInicio,fechaFin;
    Responsable responsable;
    EntradaDatos entrada;
    ArrayList <Recurso> recursos;//recursos que tiene la tarea
    ArrayList <Tarea> dependencias;//contiene las tareas de las que esta tarea depende. Para completar esta tarea, todas las tareas que
    //están en el ArrayList deben estar completadas
    /**
     * MODIFICADO: El constructor solo pide un nombre, lista de pertenencia, responsable y un id.
       */
      // nombre, e identificador, lista ***, responsable no lo veo 
      ///Ver que quiza sea buena idea asignar valores desde esta clase y no desde el gestor
      //Quiza el gestor sea mas conveniente tenerlo para que solo indique que se haga una tarea,
      //Se podria hacer un metodo main, que llame a los metodos para que llenen los datos.
      //Esto seria importante para reducir el codigo en el gestor,
      
      //Esto tambien ayudaria a simplificar la Clase Lista
      //Ya que las listas tienen tareas, No solo el gesto
      //Por supuesto solo es una idea.
      
    public Tarea(int ID,String nom,String lista){
        nombre = nom;
        completada = false;
        descripcion = "";
        listaPerteneciente=lista;
        id=ID;//id debería actuar como un contador de tareas. Si se crean 5 tareas, id=001,002,003,004,005 por ejemplo.
        //Si esto se aprueba, quedaría por discutir si es un contador de tareas totales o de tareas de cada lista o de cada categoría
        //También qué paso con el id cuando se borra y/o termina una tarea
        recursos = new ArrayList<Recurso>();
        dependencias = new ArrayList<Tarea>();
        entrada = new EntradaDatos();
    }
    //Agregar metodo para que asigne responsable
    
    public String modifiqueNombre(){
        String mensaje="Escribe el nuevo nombre";
        nombre=entrada.pedirTexto(mensaje);
        return"Se modificó el nombre de esta actividad: "+nombre;
    }
    
    public String modifiqueDescripcion(){
        String mensaje="Escriba la nueva descripción: ";
        descripcion=entrada.pedirTexto(mensaje);
        return"Se modificó la descripción de esta actividad: "+nombre;
    }
    
    /**
       Solicita un responsable. Sirve para crear o para modificarlo.
       INCONCLUSO: No sé si hace falta un método en EntradaDatos para modificar responsable.
       */
      
      //Responsable debe ser un objeto, no un nombre. Se le debe pidar al usuario al usuario la seleccion de un responsable. mas no un nombre
    public String modifiqueResponsable(Responsable resp){
        String mensaje="Inserte el nuevo responsable: ";
        return"Se modificó el responsable de esta actividad: "+nombre;

    }
    
    public void elimineResponsable(){
        responsable=null;
    }
    
    public String modifiqueHoras(){
        String mensaje="Inserte la nueva cantidad de horas";
        horas=entrada.pedirNumero(mensaje,1);
        return"Se modificó el tiempo estimado de esta actividad: "+nombre;
    }
    
    public String modifiqueDinero(){
        String mensaje="Inserte la nueva cantidad de dinero";
        dinero=entrada.pedirNumero(mensaje,1);
        return"Se modificó el dinero estimado de esta actividad: "+nombre;
    }
    
    public String modifiqueEsfuerzo(){
        String mensaje="Inserte la nueva cantidad de esfuerzo";
        esfuerzo=entrada.pedirNumero(mensaje,1);
        return"Se modificó el esfuerzo estimado de esta actividad: "+nombre;
    }
    
    public String modifiqueFechaInicio(){
        String mensaje="Inserte la nueva fecha de inicio";
        fechaInicio=entrada.pedirTexto(mensaje);
        return"Se modificó la fecha de inicio de esta actividad: "+nombre;
    }
    
    public String modifiqueFechaFin(){
        String mensaje="Inserte la nueva fecha final";
        fechaFin=entrada.pedirTexto(mensaje);
        return"Se modificó la fecha final de esta actividad: "+nombre;
    }
    
    /**
       Agrega un nuevo recurso para esta tarea.
       INCONCLUSO: mismo pŕoblema del método modifiqueResponsable
       */
    public String agregueRecurso(Recurso rec){
        recursos.add(rec);
        return"La actividad "+nombre+" ahora necesita el recurso "+rec;
    }
    
    /**
       Elimina un recurso para esta tarea.
       INCONCLUSO: mismo problema del método modifiqueResponsable
       */
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
    
    /**
       Revisa si la tarea puede completarse.
       */
    public String complete(){
        boolean check=true;//Este booleano debe ser true para que esta tarea pueda completarse. Se asume inicialmente que sí puede completarse.
        for(int i=0;i<dependencias.size();i++){
            //revisar en un "for" todas las tareas que están en el ArrayList dependencias.
            //Si alguna de las tareas no está completa, check se vuelve falso y se termina el "for"
            if(dependencias.get(i).completada==false){
                check=false;
                break;
            }
        }
        if(check==true){
            //si todas las depenencias están completas, completar esta tarea.
            completada=true;
            return"La actividad "+nombre+" completada!";
        }else{
            //caso contrario, aún hay alguna dependencia incompleta :(
            completada=false;
            return"La actividad "+nombre+" aún no puede completarse";
        }
    }
    //********
    //Agregue unos metodos
    public String deNombre(){
        return nombre;
    }
    public String deListaPerteneciente(){
        return listaPerteneciente;
    }
    //Agregar main, que pida nombre.
}
