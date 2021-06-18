import java.util.ArrayList;
/**
 * Estudiantes:
 *  Randy Jossue Aguero Bermudez Carne B90082
 *  Andrés Esteban Serrano Robles Carne C07483
 */
public class Gestor
{

    ArrayList<Lista> listas;

    EntradaDatos entrada;

    GestorFecha gestorFechas = new GestorFecha();
    public Gestor()
    {

        listas = new ArrayList<Lista>();
        entrada = new EntradaDatos();
    }
    //********************************************************
    // Metodos sobre Responsables.
    //********************************************************
    //Esto apareceria si el usuario decide modificar Horas de los Responsables, esto mediante menu;
    //Este metodo podria pedir quien es el responsable, o que se ingrese un parametro de tipo responsable
    //Temporalmente se usara la segunda opcion
    public void administreGestor()
    {
        int opcionElegidaUsuario = 0;
        String mensaje = ("Seleccione una opcion\n");//se debe agregar mas
        //A: creo que la opción de ver tareas debería estar en dentro de administrar listas
        mensaje += ("Digite 1 si desea ver las tareas, Digite 2 si desea administrar las listas, Digite 3 si desea administrar los responsables\nDigite 4 si desea administar los recursos");
        //A:agregué dígitos 5 y 6. Podemos discutirlos en la próxima reunión
        mensaje += "Digite 5 si desea administrar sus categorías, Digite 6 para crear un filtro de tareas\n ";
        opcionElegidaUsuario = entrada.pedirNumeroRango(mensaje,4,1);
        switch (opcionElegidaUsuario)
        {
            case 1:
            muestreTareas();
            break;
            case 2:
            administreListas();
            break;
            case 3:
            administreResponsables();
            break;
            case 4:
            administeRecursos();
            break;
            default:
        }

    }

    //R: Se deberia analizar muestreTareas como mostrarInformacionTareas para X cantidad de tareas
    //R: Se tiene la situacion de como adminstrar tareas, en teoria, podria pedirse la lista a modficar y luego tareas

    //R: Creo que esto esta incompleto, lo anterior y  lo dejo como comentar
    public void muestreTareas()
    {
        boolean agregarMasListas = true;
        //R: ArrayList no soporta valores si no objetos. 
        ArrayList<Lista> listasSeleccionadas = new ArrayList<Lista>();
        while(agregarMasListas)
        {
            int seleccionLista = seleccioneLista();
            listasSeleccionadas.add(listas.get(seleccionLista));
            agregarMasListas = pidaOpcionContinuarAgregarListas();

        }

        //R: Se deberia pedir N cantidad de listas para mostrar, hay que filtrarlas. El enunciado de la tarea no dice que como filtrarlas, entonces preguntarle al usuario una seleccion
        //R: por eso se usa ArrayLista, notar que podria seguir, pidiendo tareas, entonces es prudente preguntar. Si quiere agregar mas o no.
        String informe="";
        for(int j=0;j<listasSeleccionadas.size();j++)
        {
            for(int i=0;i<listas.size();i++)//No necesariamente se sabe cual es la posicion ni el numero de codigo, se sabe que el numero de lista es la posicion en el array de listas.
            {
                if(listasSeleccionadas.get(j).deCodigoLista() == listas.get(i).deCodigoLista())
                {
                    informe+="("+i+") "+listas.get(i).nombreLista+"\n\n";
                    for(int t=0;t<listas.get(i).tareas.size();i++){
                        informe+= "\t"+t+". "+listas.get(i).tareas.get(t).nombre+"\n";
                    }
                    informe+="\n\n";
                }
            }
        }
        //R: se deberia mostrar con un print desde aqui informe,

    }

    public boolean pidaOpcionContinuarAgregarListas()
    {
        boolean continuarAgregandoListas = true;
        int seleccionUsuario = 0;
        String mensaje = ("Digite 1 si desea continuar agregando Listas, Digite 2 si no desea continuar agregando Listas");
        seleccionUsuario = entrada.pedirNumeroRango(mensaje,2,1);
        if(seleccionUsuario == 2)
        {
            continuarAgregandoListas = false;
        }
        return continuarAgregandoListas;
    }

    public void administreResponsables()
    {

    }

    //Creo que este sí debería estar aquí para facilitar la comunicación con las tareas
    public void modifiqueTareasAsignadas(int posResp)
    {}

    public int pidaOpcionResponsable()
    {
        String mensaje= "";
        int opcionElegida = 0;

        return opcionElegida;
    }

    public void eliminarResponsable()
    {
        //listaResponsables.remove();  ?
    }

    // public int pidaEleccionResponsable ()
    // {
    // int eleccionUsuario = 0;//ver si el nombre de esta variable es lo mas correcto.
    // String mensaje = "";
    // boolean opcionIncorrecta = true;
    // boolean codExiste = false;
    // muestreInformacionResponsables();
    // mensaje = ("Digite el codigo del responsable que desea editar");//puede que sea una buena idea el codigo del responsable sea la posicion en la lista

    // while (opcionIncorrecta)
    // {

    // eleccionUsuario = entrada.pedirNumero(mensaje,1);
    // codExiste = verifiqueExistenciaResponsable(eleccionUsuario);
    // if(codExiste == true)
    // {
    // opcionIncorrecta = false;
    // }
    // else
    // {
    // System.out.println("Error, el responsable elegido no existe");
    // muestreInformacionResponsables();//Se llamara a este metodo para que el usuario pueda ver nuevamente los datos de los responsables
    // }
    // }

    // return eleccionUsuario;
    // }

    //public boolean verifiqueExistenciaResponsable(int codResp)
    //{
    //boolean existeResponsable = false;
    // for(int i=0;i<listaResponsables.size();i++)
    // {
    //     if(listaResponsables.get(i).deCodigoResponsable()==codResp)
    //    {
    //   existeResponsable = true;
    //    }

    //  }
    //return existeResponsable;
    //}

    public void agregarResponsable()
    {

    }

    public void muestreInformacionResponsables()
    {

        // System.out.println("Se mostrara los responsables");

        // //Para mostrar la lista, quiza por asuntos visuales sea preferiblemente mostrar solo x cantidad de digitos en el nombre
        // //Ver metodos clase string
        // //Ahora, es prudente solo mostrar informacion minima y despues mostrar informacion detallada si se necesita
        // //Mostrandolo en lista es mejor por visualizacion. Se podria mostrar toda la informacion
        // for(int i=0;i<listaResponsables.size();i++)
        // {
        // listaResponsables.get(i).muestreInformacion();
        // }
        // //A: sería bueno crear dos métodos: uno muestra info detallada y otro la muestra resumida
    }

    public void modiqueHorasResponsable(Responsable respElegido)
    {

    }

    public void administeRecursos()
    {

    }

    /**
    OJO: a esto le falta mucho, terminar lo antes posible
     */
    public void administreListas(){
        //Hay que dejarle a la lista que modifique Tareas.
        int opcionElegidaLista = 0;
        String mensaje =("¿Qué desea hacer?\n Digite 1  si desea crear una nueva lista, Digite 2 Mostrar listas\nDigite 3 si desea aministrar una lista, Digite 4 si desea borrar una lista");

        opcionElegidaLista=entrada.pedirNumeroRango(mensaje,4,1);
        switch(opcionElegidaLista){
            case 1: 
            creeLista();
            break;
            case 2:
            muestreLista();
            break;
            case 3:
            administreListaEspecifica();
            break;
            case 4:
            borreLista();
            break;
        }
    }

    public void administreListaEspecifica()
    {
        int posListaElegida = seleccioneLista();
        listas.get(posListaElegida).administreLista();
    }
    //R: se modifico lista, recordar que se hablo de codigo de lista variable
    public void creeLista(){
        Lista nuevaLista = new Lista(listas.size());
        listas.add(nuevaLista);
    }

    public void borreLista()
    {
        //Tomar en cuenta que se debe cambiar todos los codigos de lista.
        //Se debe eliminar lista de archivos.
    }

    public int seleccioneLista()
    {
        int posListaSeleccionada = 0;
        String mensaje = "";
        muestreLista();
        System.out.println("Seleccione una lista");
        mensaje =  ("Digite un numero entre 1 y "+listas.size());
        entrada.pedirNumeroRango(mensaje,listas.size(),1);
        return posListaSeleccionada;
    }

    public void muestreLista(){}

    public static void main (String args[])
    {
        Gestor gestorListas = new Gestor();
        boolean continuarPrograma = true;

        //Lo que deberia mostrar en las interacciones con el usuario son menus;
        //En gestor se deberia mostrar un menu muy corto y simple, y llamar metodos dependiendo de la seleccion
        while (continuarPrograma)
        {
            //Se deberia mostrar menu
            gestorListas.administreGestor();

        }
        System.exit(0);
    }
}
