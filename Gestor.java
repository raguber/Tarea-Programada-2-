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
    
    
    // R: Se deberia mostrar mas informacion

    public void muestreTareas()
    {
        String informe="";
        //mostrar lista, luego tareas
        for(int i=0;i<listas.size();i++){
            informe+="("+i+") "+listas.get(i).nombre+"\n\n";
            for(int t=0;t<listas.get(i).tareas.size();i++){
                informe+= "\t"+t+". "+listas.get(i).tareas.get(t).nombre+"\n";
            }
            informe+="\n\n";
        }
        //Ejemplo:
        //(1) Lista de compras

        //1. comprar queso
        //2. comprar jabón
        //3. comprar té

        //(2) Semana 11

        //1. Entregar TP2
        //2. Leer el capítulo 11
    }
    //Borrar esto
    public void administreResponsables()
    {
        int posListaElegida = seleccioneLista();
        int opcionElegida = 0;
        String mensaje = "";
        //Se deberia mostrar algo similar a
        //Modificar Responsables
        //Mostrar Responsables **Esto se podria hacer de una vez; sin necesidad de mostrar dos veces, 
        //Para elegir un responsables**Por el momento mostrar dos veces parece ser lo mejor

        muestreInformacionResponsables();
        //Ver no deberia ser una opcion porque ya se mostro informacion directa;
        //No deberia porque agregar la opcion "ver responsable" pero no debe ser necesario
        mensaje = ("Digite 1 si desea editar un responsable, Digite 2 si desea eliminar un responsable, Digite 3 si desea agregar un responsable");
        opcionElegida = entrada.pedirNumeroRango(mensaje,3,1);

        switch (opcionElegida)
        {
            case 1 :
            // editarResponsable();
            break;
            case 2 :
            eliminarResponsable();
            break;
            case 3 :
            agregarResponsable();

        }
    }

    // public void editarResponsable()
    // {
    // //Se va a maneja el editado de los responsables mediante el codigo asignado, seria buena idea asignarle un codigo
    // //Podria ser su posicion en la lista, mas esto significaria que es un codigo no permanente
    // //Entonces un codigo aleatorio podria ser una buena idea, sin embargo se tendria que verificar que los codigos de los resposnables no se repitan
    // String mensaje = "";
    // int eleccionEdicion;
    // int posicionResponsable = pidaEleccionResponsable();//No se ha modificado el metodo, entonces devuelve un codigo en vez de posicion en la lisa,
    // mensaje = ("Digite 1 si desea modificar el nombre, digite 2 si desea modificar el codigo,Digite 3 si desea modificar la cantidad de horas Dedicadas\nDigite 4 si desea modificar la tareas asignadas");
    // //Hay que analizar como se puede nombrar a todas las horas que se dedica una persona.
    // eleccionEdicion = entrada.pedirNumeroRango(mensaje,4,1);  
    // switch (eleccionEdicion)
    // {
    // case 1:
    // //listaResponsables.get(posicionResponsable).editeNombre();
    // break;
    // case 2:
    // editeCodigoResponsable(posicionResponsable);
    // break;
    // case 3:
    // //listaResponsables.get(posicionResponsable).editeHorasDedicadas();
    // break;
    // case 4:
    // modifiqueTareasAsignadas(posicionResponsable);
    // break;

    // default:
    // }

    // }

    //Porqué aquí y no en su clase
    public void editeCodigoResponsable(int posResp)
    {}

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
