import java.util.ArrayList;
import java.io.Serializable;
/**
 * Write a description of class Tareas here.
 *
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 * @version 11-6-2021
 */
public class Tarea implements Serializable{

    String listaPerteneciente;
    int codigoTarea,dinero,horas;
    boolean completada,estimoDinero,estimoHoras,esProxy;
    String nombre,descripcion,fechaInicio,fechaFin;
    GestorFecha gestorFechas;
    Responsable responsable;
    String identificacion;
    String estadoTarea;

    //Solo para Tarea Proxy
    ArrayList<Responsable> listaResponsables;
    EntradaDatos entrada;
    ArrayList <Recurso> recursos;//recursos que tiene la tarea
    ArrayList <Tarea> dependencias;

    /**
     * MODIFICADO: El constructor solo pide un nombre, lista de pertenencia, responsable y un id.
     */
    public Tarea(int ID, String listPert){
        codigoTarea=ID;
        listaPerteneciente = listPert;
        dinero=0; horas=0;
        dependencias = new ArrayList<Tarea>();
        modifiqueDescripcion();
        modifiqueNombre();
        completada = false; estimoDinero=false; estimoHoras=false;esProxy= false;
        listaResponsables = new ArrayList<Responsable>();
        gestorFechas = new GestorFecha();
        fechaInicio = genereFechaInicio();
        fechaFin="";//estimación de horas semanales de esta tarea
        estadoTarea = "pendiente";
    }
    //Agregar metodo para que asigne responsable

    public void modifiqueNombre(){
        String mensaje="Escribe el nuevo nombre";
        nombre=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó el nombre de esta actividad: "+nombre);
        modifiqueIdentificacion();
    }

    public void modifiqueDescripcion(){
        String mensaje="Escriba la nueva descripción: ";
        descripcion=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la descripción de esta actividad: "+nombre);
    }

    public void modifiqueIdentificacion()
    {
        identificacion = nombre+codigoTarea;
    }

    public void modifiqueCodigoTarea(int cod)
    {
        codigoTarea = cod;
    }

    public String genereFechaInicio ()
    {
        String fecha = "";
        return fecha;
    }

    public void genereResponsable(Responsable resp){

        responsable = resp;

    }

    /**
    Solicita un responsable. Sirve para crear o para modificarlo.
    INCONCLUSO: No sé si hace falta un método en EntradaDatos para modificar responsable.
     */
    //Responsable debe ser un objeto, no un nombre. Se le debe pidar al usuario al usuario la seleccion de un responsable. mas no un nombre
    //Esto es util si se desea modificar o visualizar desde aqui.
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
        horas=entrada.pidaNumero(mensaje,0);
        System.out.println("Se modificó el tiempo estimado de esta actividad: "+nombre);
    }

    public void modifiqueDinero(){
        String mensaje="Inserte la nueva cantidad de dinero";
        dinero=entrada.pidaNumero(mensaje,0);
        System.out.println("Se modificó el dinero estimado de esta actividad: "+nombre);
    }
    public void verifiqueEstado()
    {
        
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
        fechaInicio=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la fecha de inicio de esta actividad: "+nombre);
    }

    public void modifiqueFechaFin(){
        String mensaje="Inserte la nueva fecha final";
        fechaFin=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la fecha final de esta actividad: "+nombre);
    }

    public void agregueRecurso(Recurso rec){
        recursos.add(rec);
        System.out.println("La actividad "+nombre+" ahora necesita el recurso "+rec);
    }

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
    
    
    public String deIdentificacion()
    {
        return identificacion;
    }
    public int deCodigoTarea()
    {
        return codigoTarea;
    }

    public void cambieValorProxy()
    {
        if(esProxy==true)
        {
            esProxy = false;
        }else if(esProxy==false)
        {
            esProxy = true;
        }
    }

    public String deResponsablesNombre()
    {
        String responsables = "";
        if(esProxy == false)
        {
            responsables = listaResponsables.get(1).deNombre();
        }
        else
        {
            for(int i=0;i<listaResponsables.size();i++)
            {
                responsables += (listaResponsables.get(i).deNombre()+"\t");
            }
        }
        return responsables;

    }

    public boolean esTareaProxy()
    {
        return esProxy;
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
        Tarea tarea1 = new Tarea(0, "List1");

    }

}
