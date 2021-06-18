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

    String nombreLista, identificacion, descripcion;
    ArrayList<Tarea> tareas;
    EntradaDatos entrada;
    int numeroLista;
    String directorio;
    public Lista(int numLista)
    {
        listaResponsables = new ArrayList<Responsable>();
        String directorio = "";
        nombreLista = " ";
        numeroLista = numLista;
        numeroLista = 0;
        identificacion =" "; 
        descripcion = " ";
        tareas = new ArrayList<Tarea>();
        entrada = new EntradaDatos();
    }

    public void administreLista()
    {}

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
        opcionElegida = entrada.pedirNumeroRango(mensaje,3,1);

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
        eleccionEdicion = entrada.pedirNumeroRango(mensaje,4,1);  
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

    public void genereNombre()
    {
        String mensaje = ""; //esta variable se le mostrara al usuario cuando se pida al usuario un dato
        mensaje = ("Digite el nombre de la tarea");
        nombreLista = entrada.pedirTexto(mensaje);
    }

    public void genereIdentificacion()
    {
        //se usara el nombre y numero de lista, para generar el identificador
        //Se deberia acortar el tamaño de la identificacion, pero depende puede ser la identificacion el codifo de lista
        identificacion = (numeroLista+nombreLista);

    }

    public void genereDescripcion()
    {
        String mensaje = ""; //esta variable se le mostrara al usuario cuando se pida al usuario un dato
        mensaje = ("Digite la descripcion de la tarea");
        descripcion = entrada.pedirTexto(mensaje);
    }

    public void agregueTarea()
    {
        //ver si se agrega desde gestor o desde Lista.
        //Por el momento se agregara desde lista, en caso de ser desde gestor,
        //entonces el gestor mandara una tarea al llamar el metodo, en ese caso solo se agregaria a la lista
        //Ahora si se desea hacer el gestor lo mas comprimido posible lo mejor es hacerlo desde aqui.

        //Se deja como comentario porque se necesesita reparar tarea;
        //Tarea tarea ;// new Tarea();
        //tareas.add(tarea);

        //A: constructor actual: Tarea x = new Tarea(ID,nombre,lista)
        //Nombre debe hacerse en el propio contructor de el no tiene sentido pedirle datos que solo le sirven para guardarse en tareas
        ///Tampoco necesita ni ,identificador, la idea de guardarlo como el tamaño del array es mucho mejor, en caso de agregar, eliminar entre otros.

        //Tarea b = new Tarea(002,"Practicar piano","Música");
        //tareas.add(b);
        //El constructor tiene los atributos que consideré obligatorios para que una tarea exista. Los otros atributos son adicionales

    }
    public int deCodigoLista()
    {
        return numeroLista;
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
        Lista lista1 = new Lista(0);
        lista1.genereNombre();
        lista1.genereIdentificacion();
        lista1.genereDescripcion();

    }
}
