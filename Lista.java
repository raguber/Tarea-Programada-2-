import java.util.ArrayList;
/**
 * Esta clase se definen tareas de las listas.
 * @author Randy Agüero B90082 y Andrés Serrano C07483
 */
public class Lista
{
    String nombre, identificacion, descripcion;
    ArrayList<Tarea> tareas;
    EntradaDatos entrada;
    int numeroLista;
    public Lista(int numLista)
    {
        nombre = " ";
        numeroLista = numLista;//Se usara un contador Momentaneamente desde el gestor, sin embargo podria ser aleatorio o dado por el usario
        //el ultimo seria problematico por razones de limite de digitos de los numeros, sin embargo util seria el tamaño del array como codigo de lista;
        //Revisar si arrayList deja espacios vacios o acomodo automaticamente los espacios vacios
        identificacion =" "; //Identificador podria ser el 3 letras del nombre mas un numero o un contador;
        descripcion = " ";
        tareas = new ArrayList<Tarea>();
        entrada = new EntradaDatos();
    }

    public void genereNombre()
    {
        String mensaje = ""; //esta variable se le mostrara al usuario cuando se pida al usuario un dato
        mensaje = ("Digite el nombre de la tarea");
        nombre = entrada.pedirTexto(mensaje);
    }

    public void genereIdentificacion()
    {
        //se usara el nombre y numero de lista, para generar el identificador
        identificacion = (numeroLista+nombre);
     
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
        
    }

    public static void main (String Args[])
    {
        Lista lista1 = new Lista(0);//ver si esto funciona, cuando se pida otro numero de lista-
        lista1.genereNombre();
        lista1.genereIdentificacion();
        lista1.genereDescripcion();

    }
}
