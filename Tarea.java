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
    String listaPerteneciente;
    int id,dinero,horas;
    boolean completada,estimoDinero,estimoHoras;
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
      
<<<<<<< HEAD
    public Tarea(int ID){
        nombre = "";
        completada = false;
        descripcion = "";
        listaPerteneciente = "";
        id=ID;//id debería actuar como un contador de tareas. Si se crean 5 tareas, id=001,002,003,004,005 por ejemplo.
        //Si esto se aprueba, quedaría por discutir si es un contador de tareas totales o de tareas de cada lista o de cada categoría
        //También qué paso con el id cuando se borra y/o termina una tarea
=======
    public Tarea(int ID,String pertenencia){
        id=ID;
        dinero=0; horas=0;
        listaPerteneciente=pertenencia;
        completada = false; estimoDinero=false; estimoHoras=false;
        responsable=null;
        descripcion="";
        fechaInicio="";
        fechaFin="";//estimación de horas semanales de esta tarea
>>>>>>> 19a56958df937952898d46cb7bd78c5bfcb7444b
        recursos = new ArrayList<Recurso>();
        dependencias = new ArrayList<Tarea>();
        entrada = new EntradaDatos();
    }
    //Agregar metodo para que asigne responsable
    
    public void modifiqueNombre(){
        String mensaje="Escribe el nuevo nombre";
        nombre=entrada.pedirTexto(mensaje);
        System.out.println("Se modificó el nombre de esta actividad: "+nombre);
    }
    
    public void modifiqueDescripcion(){
        String mensaje="Escriba la nueva descripción: ";
        descripcion=entrada.pedirTexto(mensaje);
        System.out.println("Se modificó la descripción de esta actividad: "+nombre);
    }
    
    public void genereResponsable(Responsable resp){
        String mensaje="Inserte el nuevo responsable: ";
        responsable = resp;
    }
    
    /**
       Solicita un responsable. Sirve para crear o para modificarlo.
       INCONCLUSO: No sé si hace falta un método en EntradaDatos para modificar responsable.
       */
      //Responsable debe ser un objeto, no un nombre. Se le debe pidar al usuario al usuario la seleccion de un responsable. mas no un nombre
    public void modifiqueResponsable(Responsable resp){
        String mensaje="Inserte el nuevo responsable: ";
        responsable = resp;
        System.out.println("Se modificó el responsable de esta actividad: "+nombre);
    }
    
    public void elimineResponsable(){
        responsable=null;
    }
    
    public void modifiqueHoras(){
        String mensaje="Inserte la nueva cantidad de horas";
        horas=entrada.pedirNumero(mensaje,0);
        System.out.println("Se modificó el tiempo estimado de esta actividad: "+nombre);
    }
    
    public void modifiqueDinero(){
        String mensaje="Inserte la nueva cantidad de dinero";
        dinero=entrada.pedirNumero(mensaje,0);
        System.out.println("Se modificó el dinero estimado de esta actividad: "+nombre);
    }
    
    public void switchEstimeEnHoras(){
        if(estimoHoras==true){
            estimoHoras=false;
        }else if(estimoHoras==false){
            estimoHoras=true;
        }
    }
    
    public void switchEstimeEnDinero(){
        if(estimoDinero==true){
            estimoDinero=false;
        }else if(estimoDinero==false){
            estimoDinero=true;
        }
    }
    
    public void modifiqueFechaInicio(){
        String mensaje="Inserte la nueva fecha de inicio";
        fechaInicio=entrada.pedirTexto(mensaje);
        System.out.println("Se modificó la fecha de inicio de esta actividad: "+nombre);
    }
    
    public void modifiqueFechaFin(){
        String mensaje="Inserte la nueva fecha final";
        fechaFin=entrada.pedirTexto(mensaje);
        System.out.println("Se modificó la fecha final de esta actividad: "+nombre);
    }
    
    /**
       Agrega un nuevo recurso para esta tarea.
       INCONCLUSO: mismo pŕoblema del método modifiqueResponsable
       */
    public void agregueRecurso(Recurso rec){
        recursos.add(rec);
        System.out.println("La actividad "+nombre+" ahora necesita el recurso "+rec);
    }
    
    /**
       Elimina un recurso para esta tarea.
       INCONCLUSO: mismo problema del método modifiqueResponsable
       */
    public void elimineRecurso(Recurso rec){
        recursos.remove(rec);
        System.out.println("La actividad "+nombre+" ya no necesita el recurso "+rec);
    }
    
    public void dependaDe(Tarea gen){
        dependencias.add(gen);
        System.out.println("La actividad "+nombre+" ahora depende de "+gen);
    }
    
    public void yaNoDependaDe(Tarea gen){
        dependencias.remove(gen);
        System.out.println("La actividad "+nombre+" ya no depende de "+gen);
    }
    
    /**
       Revisa si la tarea puede completarse. Toma en cuenta dependencias, 
       */
    public void complete(){
        boolean check=true;//Este booleano debe ser true para que esta tarea pueda completarse. Se asume inicialmente que sí puede completarse.
        String estoFalta="La tarea no puede completarse porque:\n";
        for(int i=0;i<dependencias.size();i++){
            //revisar en un "for" todas las tareas que están en el ArrayList dependencias.
            //Si alguna de las tareas no está completa, check se vuelve falso y se termina el "for"
            if(dependencias.get(i).completada==false){
                check=false; 
                estoFalta+="\tDepende de tareas que están incompletas\n";
                break;
            }
        }
        if(responsable==null){
            check=false;
            estoFalta+="\tNo tiene responsable asignado\n";
        }
        if(estimoDinero==true && dinero!=0){
            check=false;
            estoFalta+="\tAún no cumple con la estimación de dinero\n";
        }
        if(estimoHoras==true && horas!=0){
            check=false;
            estoFalta+="\tAún no cumple con la estimación de Horas\n";
        }
        
        if(check==true){
            //si completa todas las pruebas, completar esta tarea.
            completada=true;
        }else{
            //caso contrario, aún hay alguna dependencia incompleta :(
            completada=false;
            System.out.println(estoFalta);
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
    public static void main(String args[])
    {
        Tarea tarea1 = new Tarea(0);
         
    }

}
