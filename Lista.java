import java.util.ArrayList;
import java.io.Serializable;
/**
 * Esta clase se definen tareas de las listas.
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 */
public class Lista implements Serializable
{
    //Cada lista tendra responsables taras y recursos.
    ArrayList<Responsable> listaResponsables;
    ArrayList<Recurso> listaRecursos;
    String nombreLista, identificacion, descripcion;
    ArrayList<Tarea> listaTareas;
    EntradaDatos entrada;
    String categoriaLista;
    int codigoLista;
    String directorio;
    public Lista(int codLista,String catLista)
    {
        entrada = new EntradaDatos();
        listaResponsables = new ArrayList<Responsable>();
        listaRecursos = new ArrayList<Recurso>();
        String directorio = " ";
        nombreLista = "";
        identificacion = " ";
        codigoLista = codLista;
        categoriaLista = catLista;

        genereNombreLista();
        genereDescripcion();

        genereIdentificacion(); 

        listaTareas = new ArrayList<Tarea>();
    }
    public void administreLista()
    {

    }

    public void administreResponsables()
    {

        int opcionElegida = 0;
        String mensaje = "";
        //Se deberia mostrar algo similar a
        //Modificar Responsables
        //Mostrar Responsables **Esto se podria hacer de una vez; sin necesidad de mostrar dos veces, 
        //Para elegir un responsables**Por el momento mostrar dos veces parece ser lo mejor

        //muestreInformacionResponsables();
        //Ver no deberia ser una opcion porque ya se mostro informacion directa;
        //No deberia porque agregar la opcion "ver responsable" pero no debe ser necesario
        mensaje = ("Digite 1 si desea editar un responsable, Digite 2 si desea eliminar un responsable, Digite 3 si desea agregar un responsable");
        opcionElegida = entrada.pidaNumeroRango(mensaje,3,1);

        switch (opcionElegida)
        {
            case 1 :
            editarResponsable();
            break;
            case 2 :
            // eliminarResponsable();
            break;
            case 3 :
            // agregarResponsable();

        }
    }
    //Esto lo voy a enviar nuevamente en Responsables;
    public void editarResponsable()
    {
        //Se va a maneja el editado de los responsables mediante el codigo asignado, seria buena idea asignarle un codigo
        //Podria ser su posicion en la lista, mas esto significaria que es un codigo no permanente
        //Entonces un codigo aleatorio podria ser una buena idea, sin embargo se tendria que verificar que los codigos de los resposnables no se repitan
        String mensaje = "";
        int eleccionEdicion;
        //  int posicionResponsable = pidaEleccionResponsable();//No se ha modificado el metodo, entonces devuelve un codigo en vez de posicion en la lisa,
        mensaje = ("Digite 1 si desea modificar el nombre, digite 2 si desea modificar el codigo,Digite 3 si desea modificar la cantidad de horas Dedicadas\nDigite 4 si desea modificar la tareas asignadas");
        //Hay que analizar como se puede nombrar a todas las horas que se dedica una persona.
        eleccionEdicion = entrada.pidaNumeroRango(mensaje,4,1);  
        switch (eleccionEdicion)
        {
            case 1:
            //listaResponsables.get(posicionResponsable).editeNombre();
            break;
            case 2:
            //editeCodigoResponsable(posicionResponsable);
            break;
            case 3:
            //listaResponsables.get(posicionResponsable).editeHorasDedicadas();
            break;
            case 4:
            //modifiqueTareasAsignadas(posicionResponsable);
            break;

            default:
        }

    }

    public void genereNombreLista()
    {
        String mensaje = ""; //esta variable se le mostrara al usuario cuando se pida al usuario un dato
        mensaje = ("Digite el nombre de la lista");

        nombreLista = entrada.pidaTexto(mensaje);
    }
    public void modifiqueNombreLista()
    {
        genereIdentificacion();
    }

    public void modifiqueCodigoLista(int cod)
    {
        codigoLista = cod;
    }

    public void genereIdentificacion()
    {
        //se usara el nombre y numero de lista, para generar el identificador
        //Se deberia acortar el tamaño de la identificacion, pero depende puede ser la identificacion el codifo de lista
        identificacion = (codigoLista+nombreLista);

    }

    public void genereDescripcion()
    {
        String mensaje = ""; //esta variable se le mostrara al usuario cuando se pida al usuario un dato
        mensaje = ("Digite la descripcion de la lista");
        descripcion = entrada.pidaTexto(mensaje);

    }
    //responsable recurso estimación
    public void agregueTarea()
    {
        //A:esto lo hice yo
        String mensaje;
        Tarea nuevaTarea = new Tarea(listaTareas.size(),nombreLista);
        listaTareas.add(nuevaTarea);
    }

    public void mostrarLista()
    {
        String mostrarInfo ="";
        mostrarInfo += ("Numero Lista "+codigoLista+"\n");
        mostrarInfo += ("Categoria: "+categoriaLista+" Nombre de la lista "+nombreLista+" Identificacion: "+identificacion+"\n");
        mostrarInfo += (deInfoMinResponsables());
        mostrarInfo += (deInfoMinRecursos());
        mostrarInfo += (deInfoMinTareas());

        System.out.println(mostrarInfo);
    }

    public String deInfoMinResponsables()
    {
        String infoResponsables ="";
        for(int i=0;i<listaResponsables.size();i++)
        {
            infoResponsables += ("\t Nombre Responsable"+listaResponsables.get(i).deNombre()+"Identificacion "+listaResponsables.get(i).deIdentificacion()+"\n");
        }
        return infoResponsables;
    }

    public String deInfoMinRecursos()
    {
        String infoRecursos = "";
        for(int i=0;i<listaRecursos.size();i++)
        {
            infoRecursos += ("\t Nombre Recurso : "+listaRecursos.get(i).deNombre()+" Identificacion Recurso "+listaRecursos.get(i).deIdentificacion()+"\n");
            infoRecursos += ("\t Cantidad disponibles de "+listaRecursos.get(i).deNombre()+"\n");
        }
        return infoRecursos;
    }

    public String deInfoMinTareas()
    {
        String infoTareas ="";
        for(int i=0;i<listaTareas.size();i++)
        {
            infoTareas += ("\t Nombre Tarea : "+listaTareas.get(i).deNombre()+" Identificacion Tarea "+listaTareas.get(i).deIdentificacion()+"\n"); 
            infoTareas += ("\t Responsables  : "+listaTareas.get(i).deResponsablesNombre()+" Estado Tarea : "+listaTareas.get(i).deEstadoTarea()+"\n");
        }
        return infoTareas;
    }
    public void modifiqueTarea(int tarSel)
    {
        //Metodo eliminar, modificarReponsables, modificarRecursosAqui,etc aqui
        //Metodo modNombre, descripcion, aqui.
        //Si se llama a eliminarTaea; entonces hay que generar de nuevo codigos de tarea
    }
    

    public int deCantidadTareas()
    {
        return listaTareas.size();
    }

    public String deCategoria()
    {
        return categoriaLista;
    }

    public int deCodigoLista()
    {
        return codigoLista;
    }

    public String deNombreLista()
    {
        return nombreLista;
    }

    public String deIndentifiacion()
    {
        return identificacion;
    }

    public String deDescripcion()
    {
        return descripcion;
    }

    public String deDirectorioGuardado()
    {
        return directorio;
    }

    public boolean directorioGuardado()
    {
        boolean directorioGuardado = true;
        if(directorio.equals(null)==true)
        {
            directorioGuardado = false;
        }
        return directorioGuardado;
    }

    public static void main (String Args[])
    {
        Lista lista1 = new Lista(0,"VAcias");
        lista1.genereNombreLista();
        lista1.genereIdentificacion();
        lista1.genereDescripcion();

    }
}
