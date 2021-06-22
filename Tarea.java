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
    int codigoTarea,dinero,horas,horasProgreso,dineroProgreso;
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
    String fechaActual;

    int progreso;

    int diaActual,mesActual, anoActual,diaInicio,mesInicio,anoInicio;

    /**
     * El constructor de tareas pide en su argumento, una indentificación, lista en la que pertenece, un responsable, y un recurso.
     * 
     */
    public Tarea(int ID, String listPert,Responsable resp,Recurso rec){
        entrada = new EntradaDatos();
        gestorFechas = new GestorFecha();
        dependencias = new ArrayList<Tarea>();
        listaResponsables = new ArrayList<Responsable>();
        recursos = new ArrayList <Recurso>();

        
        completada=false; esProxy=false;
        estimoDinero=false; estimoHoras=false;
        dinero=0; horas=0;
        estadoTarea ="Pendiente";
        
        codigoTarea = ID;
        listaPerteneciente = listPert;

        responsable =resp;
        listaResponsables.add(resp);
        //fecha.pidaFechaTarea();

        recursos.add(rec);
        fechaActual = "";
        diaActual = 0;
        mesActual = 0;
        anoActual = 0;
        fechaInicio = "";
        diaInicio = 0;
        mesInicio = 0;
        anoInicio = 0;
        genereFechaInicio();
        progreso = 0;
        modifiqueNombre();
        modifiqueDescripcion();
        genereEstimacion();
    }
    public void fijeFecha(String fechaA, int diaA,int mesA,int anoA)
    {
        fechaActual = fechaA;
        diaActual = diaA;
        mesActual = mesA;
        anoActual = anoA;
    }
    public String muestreInformacion(){
        String info="Nombre: "+nombre+"\nCódigo: "+codigoTarea+"\nResponsable: "+deResponsablesNombre()+"\nRecursos: "+recursos;
        info+="\nFecha de inicio: "+fechaInicio+"\nFecha de Fin: "+fechaFin+"\nProgreso: "+progreso+"\n";
        return info;
    }
    public void modifiqueNombre(){
        String mensaje="Escribe el nuevo nombre de la tarea";
        nombre=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó el nombre de esta tarea: "+nombre);
        modifiqueIdentificacion();
    }

    public void modifiqueDescripcion(){
        String mensaje="Escriba la nueva descripción: ";
        descripcion=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la descripción de esta tarea: "+nombre);
    }

    public void modifiqueIdentificacion()
    {
        identificacion = nombre+codigoTarea;
    }

    public void modifiqueCodigoTarea(int cod)
    {
        codigoTarea = cod;
    }

    public void genereFechaInicio ()
    {
        fechaInicio = gestorFechas.pidaFechaTarea(diaActual, mesActual,anoActual);
        diaInicio = gestorFechas.diaInicial;
        mesInicio = gestorFechas.mesInicial;
        anoInicio =  gestorFechas.anoInicial;
        fechaFin = gestorFechas.calculeFechaFin(responsable.cantidadHorasDedicadas,horas,diaActual,mesActual,anoActual, diaInicio,mesInicio,anoInicio);
    }
    
    public void modifiqueFechaInicio(){
        String mensaje="Inserte la nueva fecha de inicio";
        fechaInicio=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la fecha de inicio de esta tarea: "+nombre);
    }

    public void modifiqueFechaFin(){
        String mensaje="Inserte la nueva fecha final";
        fechaFin=entrada.pidaTexto(mensaje);
        System.out.println("Se modificó la fecha final de esta tarea: "+nombre);
    }

    public void genereResponsable(Responsable resp){

        responsable = resp;

    }

    /**
    Solicita un responsable. Sirve para crear o para modificarlo.
    INCONCLUSO: No sé si hace falta un método en EntradaDatos para modificar responsable.
     */
    public void modifiqueResponsable(Responsable resp){
        String mensaje="Inserte el nuevo responsable: ";
        responsable = resp;
        System.out.println("Se modificó el responsable de esta tarea: "+nombre);
    }

    public void elimineResponsable(){
        responsable=null;
    }

    public void modifiqueHoras(){
        String mensaje="Inserte la nueva cantidad de horas";
        horas=entrada.pidaNumero(mensaje,0);
        System.out.println("Se modificó el tiempo estimado de esta tarea: "+nombre);
    }

    public void modifiqueDinero(){
        String mensaje="Inserte la nueva cantidad de dinero";
        dinero=entrada.pidaNumero(mensaje,0);
        System.out.println("Se modificó el dinero estimado de esta tarea: "+nombre);
    }
    
    public void progreseDinero(){
        if(estimoDinero==true){
            String mensaje="Deposite una cantidad de dinero";
            System.out.println("Cantidad de dinero actual: "+dineroProgreso+" ---> Cantidad de dinero necesario: "+dinero+"\nFalta "+(dinero-dineroProgreso));
            dinero-=entrada.pidaNumeroRango(mensaje,dinero,1);
        }
    }
    
    public void verifiqueEstado()
    {
        
    }
    
    public void genereEstimacion(){
        String mensaje="Digite 1 si la tarea estima horas, 2 si estima dinero, 3 si estima ambas";
        int decision = entrada.pidaNumeroRango(mensaje,3,1);
        switch(decision){
            case 1:estimeEnHoras();break;
            case 2:estimeEnDinero();break;
            case 3:estimeEnHoras();estimeEnDinero();break;
        }
    }
    
    public void estimeEnHoras(){
        String mensaje="Inserte la cantidad de horas semanales que se nesesitan para realizar la tarea"+nombre;
        horas = entrada.pidaNumero(mensaje,1);
        estimoHoras=true;
    }
    
    public void estimeEnDinero(){
        String mensaje="Inserte la cantidad de dinero que estima la tarea "+nombre;
        dinero = entrada.pidaNumero(mensaje,1);
        estimoDinero=true;
    }
    public void progrese(String fechaA,int diaA,int mesA,int anoA)
    {
        if(anoA<anoInicio)
        {
            if(mesA<mesInicio)
            {
                if(diaA>diaInicio)//Solo si la fecha actual es mayor que el inicio se progresa
                {
                    progreseTarea(fechaA,diaA,mesA,anoA);
                }
            }
        }
        fechaActual = fechaA;
        diaActual = diaA;
        mesActual = mesA;
        anoActual = anoA;
        
        
    }
    public void progreseTarea(String fechaA, int diaA,int mesA,int anoA)
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
    
    public void pregunteSobreDependencias(){}
    
    public void agregueRecurso(Recurso rec){
        recursos.add(rec);
        System.out.println("La tarea "+nombre+" ahora necesita el recurso "+rec);
    }

    public void elimineRecurso(Recurso rec){
        recursos.remove(rec);
        System.out.println("La tarea "+nombre+" ya no necesita el recurso "+rec);
    }

    public void dependaDe(Tarea gen){
        dependencias.add(gen);
        System.out.println("La tarea "+nombre+" ahora depende de "+gen);
    }

    public void yaNoDependaDe(Tarea gen){
        dependencias.remove(gen);
        System.out.println("La tarea "+nombre+" ya no depende de "+gen);
    }
    
    public void progrese(){
        if(esProxy==true){
            
        }else if(esProxy==false){
            
        }
    }
    
    /**
    Revisa si la tarea puede completarse. Toma en cuenta dependencias, estimación, y si tiene un responsable asignado.
     */
    
    public void complete(){
        boolean check=true;
        String estoFalta="La tarea no puede completarse porque:\n";
        for(int i=0;i<dependencias.size();i++){
            //revisar en un "for" todas las tareas que están en el ArrayList dependencias.
            //Si alguna de las tareas no está completa, check se vuelve falso y se termina el "for"
            if(dependencias.get(i).completada==false || dependencias.get(i).esProxy==false){
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
            completada=false;
        }
        else if(esProxy==false)
        {
            esProxy = true;
            completada=true;
        }
    }

    public String deResponsablesNombre()
    {
        String responsables = "";
        if(esProxy == false)
        {

            responsables = responsable.deNombre();

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
}
