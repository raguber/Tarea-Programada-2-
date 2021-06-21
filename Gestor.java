import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
/**
 * Estudiantes:
 *  Randy Jossue Aguero Bermudez Carne B90082
 *  Andrés Esteban Serrano Robles Carne C07483
 */
public class Gestor
{
    ArrayList<File> listasArchivos;//<-- Aquí van las listas
    FileInputStream fis;
    ObjectInputStream lector; //<-- Lee(carga) la lista x
    FileOutputStream fos;
    ObjectOutputStream escritor;//<-- Escribe(guarda) en la lista x 
    FileReader fire;
    FileWriter fiwr;
    BufferedReader bure;
    BufferedWriter buwr;

    JFileChooser chooser;
    ArrayList<String> categoriasListas;
    ArrayList<Lista> listas;

    EntradaDatos entrada;

    GestorFecha gestorFechas = new GestorFecha();
    //goback
    //El Maravilloso Gestor de Listas
        //void administreGestor()
            //void creeLista()
                //boolean pedirOpcionAgregarCategoria()
                    //muestreCategoriasListas()
                //agregarCategoria()
                    //verifiqueExistenciaCategoria()
                //seleccioneCategoria()
                    //muestreCategoriasListas()
                //categorizeListas()
                    //verifiqueExistenciaCategoria()
    public Gestor()
    {
        listas = new ArrayList<Lista>();
        listasArchivos = new ArrayList<File>();
        entrada = new EntradaDatos();
        categoriasListas = new ArrayList<String>();

    }

    public void administreGestor()throws IOException,ClassNotFoundException
    {
        //Hay que dejarle a la lista que modifique Tareas.
        int opcionElegidaLista = 0;
        String mensaje = "";
        //En caso de que el usuario quiera ver una lista, y no existan listas, no podria salir ya que se pidaa que guarde una lista, que no existe
        //, entonces solo en caso de que listas tenga algo, se permitira determinadas opciones
        if(listas.size()==0)
        {
            mensaje = ("No existen listas agregadas\n Digite 1, si desea crear una nueva lista, Digite 2 si desa Salir");
            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,3 ,1);
            switch(opcionElegidaLista){
                case 1: 
                creeLista();//listo
                break;
                case 2:
                //R: Cargu Lista Ya no se necesita 
                //cargueLista();
                break;
                case 3:
                salgaPrograma();//Listo
                break;
            }

        }
        else
        {
            mensaje =("¿Qué desea hacer?\nDigite 1  si desea crear una nueva lista, Digite 2 si desea Mostrar o modificar tareas ya creadas \nDigite 3 si desea modificar una lista o agregar tareas a una lista, Digite 4 si desea borrar una lista\nDigite 5 si desea modificar categorias, Digite 6 si desea salir del gestor");

            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,5,1);

            switch(opcionElegidaLista){
                case 1: 
                creeLista(); //Listo 
                break;
                case 2:
                administreTareas();
                break;
                case 3:
                administreListaEspecifica();
                //Incompleto
                break;
                case 4:
                borreLista();
                case 5:
                
                //Listo
                cargueListas();
                modificarCategorias();
                
                case 6:
                salgaDelGestor();
                break;
            }
        }
    }

    //R: Se deberia analizar muestreTareas como mostrarInformacionTareas para X cantidad de tareas
    //R: Se tiene la situacion de como adminstrar tareas, en teoria, podria pidase la lista a modficar y luego tareas

    public ArrayList<Lista> seleccioneListasTareas()
    {

        String categoria = "";
        //R: ArrayList no soporta valores si no objetos, por eso mejor pedi tipo lista. 
        ArrayList<Lista> listasSeleccionadas = new ArrayList<Lista>();
        boolean continuarAgregandoCategorias = pregunteSiImportaCategoria();
        boolean agregarMasListas = true;
        while(continuarAgregandoCategorias||listasSeleccionadas.size()<listas.size())
        {
            if(continuarAgregandoCategorias==true)
            {

                categoria = seleccioneCategoria();

            }
            else
            {

                agregarMasListas = false;
                continuarAgregandoCategorias = false;
            }

            while(agregarMasListas)
            {
                if(continuarAgregandoCategorias == true)
                {
                    int listaSeleccionada = seleccioneListaCategorias(categoria);
                }
                else
                {
                    int listaSeleccionada = seleccioneLista();
                }

                int seleccionLista = 0;
                if(verifiqueSiTareaSeleccionada(listas.get(seleccionLista),listasSeleccionadas )== false)
                {
                    listasSeleccionadas.add(listas.get(seleccionLista));

                }
                else
                {
                    System.out.println("Error, La tarea elegida ya ha sido seleccionada");
                }
                if(listasSeleccionadas.size()!=listasSeleccionadas.size())
                {
                    agregarMasListas = pidaOpcionContinuarAgregarListas();
                }
                else
                {
                    agregarMasListas = false;
                    continuarAgregandoCategorias = false;
                }
            }
            if(agregarMasListas ==false)
            {
                continuarAgregandoCategorias =  false;

            }
            if(listasSeleccionadas.size()==listas.size())
            {
                System.out.println("No se pueden seleccionar mas listas, ya se han seleccionado todas");
                agregarMasListas=false;
                continuarAgregandoCategorias=false;

            }

            if(continuarAgregandoCategorias == true)
            {
                continuarAgregandoCategorias = pregunteSiContinuarFiltrandoCategorias();
            }

            //R:Deberia ir eso en un metodo aparte
            //R: Se deberia pida N cantidad de listas para mostrar, hay que filtrarlas. El enunciado de la tarea no dice que como filtrarlas, entonces preguntarle al usuario una seleccion
            //R: por eso se usa ArrayLista, notar que podria seguir, pidiendo tareas, entonces es prudente preguntar. Si quiere agregar mas o no.

        }
        return listasSeleccionadas;
    }

    public void administreTareas()
    {
        ArrayList<Lista> seleccionLista = seleccioneListasTareas();
        muestreTareas(seleccionLista);
        seleccionLista = elimineListasVacias(seleccionLista);

        if(seleccionLista.size() != 0)
        {
            System.out.println("Se eliminaron las listas sin tareas");
            int opcionUsuario = opcionUsuarioAdmTareas();
            if(opcionUsuario ==1)
            {

                int listaSel = pidaCodListaTarea(seleccionLista);//Se pide un codigo de lista, que a su vez es  posicion-1 en el arrayList listas 
                listaSel--;
                int tareaSel = pidaTarea(listaSel);
                listas.get(listaSel).modifiqueTarea(tareaSel);
            }
            else
            {
                //modificarTiempo
            }
            //Se va a usar la identificacion de la lista y la identificacion de la tarea para editar eliminar o algo en las tareas-
        }
        else
        {
            System.out.println("Todas las listas seleccionadas estan vacias, debe seleccionar otras listas, o agregar tareas para poder gestionar las tareas");
        }
    }

    public ArrayList<Lista> elimineListasVacias(ArrayList<Lista> listSel)
    {
        ArrayList<Lista> lista = listSel;
        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i).deCantidadTareas()==0)
            {

                lista.remove(i);

            }
        }
        return lista;
    }

    public int seleccioneTareaEditar(int numLista)
    {
        int tareaSeleccionada = 0;
        int cantTareas = listas.get(numLista).deCantidadTareas();
        for(int i = 0;i<cantTareas;i++)
        {
            System.out.println("Codigo tarea :"+listas.get(numLista).listaTareas.get(i).deCodigoTarea()+" Nombre :"+listas.get(numLista).listaTareas.get(i).deNombre());
        }
        String mensaje =("Digite el codigo de la tarea que desea seleccionar");
        tareaSeleccionada = entrada.pidaNumeroRango(mensaje,cantTareas,1);
        return tareaSeleccionada;
    }

    public int pidaCodListaTarea(ArrayList<Lista> listasFil)
    {
        int listaSel = 0;
        boolean codListaIncorrecto = true;
        for(int i= 0; i<listasFil.size();i++)
        {
            System.out.println("Codigo Lista: "+listasFil.get(i).deCodigoLista()+" Nombre  de la Lista"+listasFil.get(i).deNombreLista());
        }
        System.out.println("Se eliminaron listas vacias seleccionadas");
        String  mensaje = ("Digite el codigo de la lista de la tarea que desea editar");

        while(codListaIncorrecto)
        {
            listaSel = entrada.pidaNumero(mensaje,1); 
            boolean listaEncontrada = false;
            for(int i= 0;i<listasFil.size();i++)
            {
                if(listasFil.get(i).deCodigoLista() == listaSel)
                {
                    listaEncontrada = true;
                }
            }
            if(listaEncontrada == true)
            {
                codListaIncorrecto = false;
            }
            else
            {
                System.out.println("El codigo de lista ingresado no corresponde a una lista filtradada previamente segun su eleccion de listas");
            }
        }
        return listaSel;  
    }
    //Como se muestran todas las tareas
    public int pidaTarea(int numLista)
    {
        int numTarea = 0;

        return numTarea;

    }

    public int opcionUsuarioAdmTareas()
    {
        int opcionSeleccionada = 0;
        String mensaje = ("Digite 1 si desea modificar o eliminar una tarea, Digite 2 si desea progresar el tiempo");
        opcionSeleccionada = entrada.pidaNumeroRango(mensaje,2,1);
        return opcionSeleccionada;
    }

    public void muestreTareas(ArrayList<Lista> selListas)
    {
        String informe="";
        for(int j=0;j<selListas.size();j++)
        {
            for(int i=0;i<listas.size();i++)//No necesariamente se sabe cual es la posicion ni el numero de codigo, se sabe que el numero de lista es la posicion en el array de listas.
            {
                if(selListas.get(j).deCodigoLista() == listas.get(i).deCodigoLista())
                {

                    //R: creo que se deberia mostrar un poco mas de info
                    //En realidad aqui se deberian mostar ya las listas
                    informe+="("+i+") "+listas.get(i).nombreLista+"\n\n";
                    for(int t=0;t<listas.get(i).listaTareas.size();i++){
                        informe+= "\t"+t+". "+listas.get(i).listaTareas.get(t).nombre+"\n";
                    }
                    informe+="\n\n";
                }
            }
        }
        //R: se deberia mostrar con un print desde aqui informe,
    }

    public boolean pregunteSiContinuarFiltrandoCategorias()
    {
        boolean filtrarCategorias = false;

        String  mensaje =("Digite 1 si desea seguir filtrando la seleccion de listas por categorias, Digite 2 si no desea seguir filtrando la seleccion de listas por categorias");
        int seleccionUsuario = entrada.pidaNumeroRango(mensaje,2,1);
        if(seleccionUsuario == 1)
        {
            filtrarCategorias = true;
        }

        return filtrarCategorias;
    }
    //A: está dentro de creeLista, que está dentro de administreGestor()
    public String seleccioneCategoria()
    {
        String categoriaSeleccionada = "";
        String mensaje = "";
        int posicionCategoriaSeleccionada = 0;

        muestreCategoriasListas();
        mensaje = ("Digite el numero de la categoria que desea seleccionar, debe ser un numero entre 1 y "+categoriasListas.size());
        posicionCategoriaSeleccionada = entrada.pidaNumeroRango(mensaje,categoriasListas.size(),1);

        categoriaSeleccionada = categoriasListas.get(posicionCategoriaSeleccionada-1);
        return categoriaSeleccionada;
    }
    
    //A: está dentro de pedirOpcionAgregarCategoria(), que a su vez está dentro de creeLista(), que a su vez está dentro de administreGestor()
    public void muestreCategoriasListas()
    {
        System.out.println("Se mostrarán las categorías de listas");
        for(int i=0;i<categoriasListas.size();i++)
        {
            System.out.println("Categoria: "+(i+1)+" "+categoriasListas.get(i));
        }
    }

    public boolean verifiqueSiTareaSeleccionada(Lista list, ArrayList<Lista> listSel)
    {
        boolean tareaSeleccionada = false;
        //Se va a comparar con el codigo
        for(int i= 0;i<listSel.size();i++)
        {
            if(list.deCodigoLista() == listSel.get(i).deCodigoLista())
            {
                tareaSeleccionada = true;
            }
        }
        return tareaSeleccionada;
    }

    public boolean pidaOpcionContinuarAgregarListas()
    {
        boolean continuarAgregandoListas = true;
        int seleccionUsuario = 0;
        String mensaje = ("Digite 1 si desea continuar agregando Listas, Digite 2 si no desea continuar agregando Listas");
        seleccionUsuario = entrada.pidaNumeroRango(mensaje,2,1);
        if(seleccionUsuario == 2)
        {
            continuarAgregandoListas = false;
        }
        return continuarAgregandoListas;
    }

    public void administreListaEspecifica()
    {
        boolean importaCategoria = pregunteSiImportaCategoria();
        String categoria;
        int posListaElegida = 0;
        if(importaCategoria == true)
        {
            categoria = seleccioneCategoria();
            posListaElegida = seleccioneListaCategorias(categoria);
            listas.get(posListaElegida-1).administreLista();
        }
        else
        {
            posListaElegida = seleccioneLista();
            listas.get(posListaElegida-1).administreLista();
        }

    }

    public boolean pregunteSiImportaCategoria()
    {
        boolean importaCategoria = false;
        int seleccionUsuario = 0;
        String mensaje = ("Digite 1 si desea filtrar la lista a seleccionar por categoria, digite 2 si no desa filtrar la lista a seleccionar  por categoria");
        seleccionUsuario = entrada.pidaNumeroRango(mensaje,2,1);
        if(seleccionUsuario ==1)
        {
            importaCategoria = true;
        }
        return importaCategoria;

    }

    public String agregarCategoria()
    {
        String categoria = "";
        String mensaje = ("Digite el nombre de la categoria");
        categoria = entrada.pidaTexto(mensaje);
        boolean existeCategoria = verifiqueExistenciaCategoria(categoria);
        if(categoriasListas.size()==0)
        {
            categoriasListas.add(categoria);
        }
        else
        {
            if(existeCategoria == false)
            {
                categoriasListas.add(categoria);
            }
        }
        return categoria;
    }
    //A: está dentro de administreGestor
    public void creeLista(){
        boolean agregarCategoria = pedirOpcionAgregarCategoria();
        String categoriaSeleccionada ="";
        if(agregarCategoria == true)
        {
            categoriaSeleccionada = agregarCategoria();
        }
        else
        {
            categoriaSeleccionada = seleccioneCategoria();
        }
        Lista nuevaLista = new Lista(listas.size()+1,categoriaSeleccionada);
        listas.add(nuevaLista);
        //Notar que cada vez que listas se agrega, se debe guardar en un archivo
        File nuevoFile = new File(nuevaLista.deNombreLista()+".txt");//<-- el archivo se llama igual que la lista. Se le adiciona el .txt
        listasArchivos.add(nuevoFile);
        categorizeListas();
    }

    public void borreLista()
    {
        //Tomar en cuenta que se debe cambiar todos los codigos de lista.
        //Se debe eliminar lista de archivos.
        //listas.remove(); //este método no espeficica cuál lista
    }

    public void modificarCodigosLista()
    {
        for(int i=0;i<listas.size();i++)
        {
            listas.get(i).modifiqueCodigoLista(i+1);
        }
    }

    public int seleccioneLista()
    {

        int posListaSeleccionada = 0;
        String mensaje = "";
        muestreListas();
        System.out.println("Seleccione una lista");
        mensaje =  ("Digite un numero entre 1 y "+listas.size());
        posListaSeleccionada = entrada.pidaNumeroRango(mensaje,listas.size(),1);
        return posListaSeleccionada;
    }

    public int seleccioneListaCategorias(String cat)
    {
        int listaSeleccionada = 0;

        boolean listaSeleccionadaIncorrecta = true;
        while(listaSeleccionadaIncorrecta)
        { 
            muestreListasCategoria(cat);
            String mensaje = ("Digite el numero de la lista que desea seleccionar");
            listaSeleccionada = entrada.pidaNumeroRango(mensaje,listas.size(),1);

            for(int i= 0;i<listas.size();i++)
            {
                if(listas.get(i).deCategoria()==listas.get(listaSeleccionada-1).deCategoria())
                {
                    listaSeleccionadaIncorrecta = false;
                }
            }
            if(listaSeleccionadaIncorrecta==true)
            {
                System.out.println("La lista seleccionada no es valida, intente de nuevo");
            }
        }
        return listaSeleccionada;
    }
    
    //A: está dentro de agregarLista(), que está dentro de administreGestor()
    public boolean pedirOpcionAgregarCategoria()
    {
        muestreCategoriasListas();
        boolean agregarCategoria = false;
        String mensaje =("Digite 1 si desea agregar una nueva categoria, Digite 2 si desea usar una categoria existente");
        int opcionCategoria = entrada.pidaNumeroRango(mensaje,2,1);
        if(opcionCategoria==1)
        {
            agregarCategoria = true;
        }
        else
        {
            if(categoriasListas.size()==0)
            {
                System.out.println("No han existido listas agregadas por lo tanto no hay categorias, se tendra que agregar una primer categoria");
                agregarCategoria = true;
            }
        }
        return agregarCategoria;

    }

    public void muestreListas()
    {
        for(int i=0;i<listas.size();i++)
        {
            System.out.println("Lista :"+(i+1));
            listas.get(i).mostrarLista();
        }
    }

    public void muestreListasCategoria(String cat)
    {
        for(int i=0;i<listas.size();i++)
        {
            if(listas.get(i).deCategoria().equals(cat)==true)
            {
                listas.get(i).mostrarLista();
            }
        }
    }
    //Agregado metodo cargueLista

    public void categorizeListas()
    {
        String categoria = "";
        boolean existeCategoria = false;
        for (int i = 0;i<listas.size();i++)
        {
            categoria = listas.get(i).deCategoria();
            existeCategoria = verifiqueExistenciaCategoria(categoria);
            if(existeCategoria=false)
            {
                categoriasListas.add(categoria);
            }
        }
    }

    //A: está dentro de agregarCategoria()
    public boolean verifiqueExistenciaCategoria(String cat)
    {
        boolean categoriaGuardada = false;
        if(categoriasListas.size() > 0)
        {
            for(int i=0;i<categoriasListas.size();i++)
            {
                if(categoriasListas.get(i).equals(cat)==true)
                {
                    categoriaGuardada = true;
                }
            }
        }
        return categoriaGuardada;
    }
    public void modificarCategorias()
    {
        String mensaje = ("Digite 1 si desea eliminar una categoria, Digite 2 si desea modificar el nombre de una categoria");
        int opcionUsuario = entrada.pidaNumeroRango(mensaje,2,1);
        if(opcionUsuario==1)
        {
            String nombreCategoria = seleccioneCategoria();
            modifiqueCategoria(nombreCategoria,"Sin categoria");
        }
        else
        {
            String nombreCategoria = seleccioneCategoria();
            mensaje = ("Escriba el nuevo nombre de la categoria "+nombreCategoria);
            String nuevNombre = entrada.pidaTexto(mensaje);
            modifiqueCategoria(nombreCategoria,nuevNombre);
        }
        categorizeListas();
    }
    public void modifiqueCategoria(String nomAct, String nomNuev)
    {
        for(int i=0;i<listas.size();i++)
        {
            if (listas.get(i).deCategoria().equals(nomAct) == true)
            {
                listas.get(i).modifiqueCategoria(nomNuev);
            }
        }
        
    }
    public void salgaDelGestor()
    {
        //preguntar si el usuario quiere guardar
    }

    public void salgaPrograma() throws IOException
    {
        cierreTodo();
        System.exit(0);
    }
    

    public void cierreTodo() throws IOException{
        fis.close(); lector.close();
        fos.close(); escritor.close();
        fire.close(); bure.close();
        fiwr.close(); buwr.close();
    }

    //********Métodos de escritura y lectura*************

    /**
    Método que guarda todas las listas que se tienen actualmente.
     */
    public void guardeListas()throws IOException,ClassNotFoundException,FileNotFoundException{
        for(int i=0;i<listas.size();i++){
            //1. Se saca archivo del arraylist de archivos(de listas)
            File guardeEsto = listasArchivos.get(i);
            //Si este archivo(lista) aún no está en el directorio, créelo
            if(!guardeEsto.exists()){
                guardeEsto.createNewFile();
            }

            //2. creación de objetos necesaria para guardar objetos
            fos = new FileOutputStream(guardeEsto);
            escritor = new ObjectOutputStream(fos);

            //3. Se guarda un objeto lista en este archivo
            escritor.writeObject(listas.get(i));
        }
    }

    /**
    Guarda en un archivo txt el nombre de todas las listas. Este método es necesario para que el gestor conozca el nombre de los archivos
    que debe llamar.
     */
    public void guardeNombresDeListas()throws IOException{
        //1. Se abre el archivo con el nombre "Nombres de listas"
        File nombresDeListas = new File("Nombres de listas.txt");

        //2. Si el archivo no existe, créelo
        if(!nombresDeListas.exists()){
            nombresDeListas.createNewFile();
        }

        //3. Se crean los objetos necesarios para escribir nombres
        fiwr = new FileWriter(nombresDeListas);
        buwr = new BufferedWriter(fiwr);

        //4. Se escribe la cantidad de listas para que pueda leerse cuando el programa se abra de nuevo
        buwr.write(listas.size());

        //5. Escriba el nombre de todas las listas en este archivo.
        //Recordar que el nombre de la lista y del archivo es el mismo
        for(int i=0;i<listas.size();i++){
            buwr.write(listas.get(i).nombreLista);
            buwr.newLine();
        }
    }

    /**
    Método para abrir las listas
     */
    public void cargueListas()throws IOException,ClassNotFoundException,FileNotFoundException{
        //1. Se abre el archivo con el nombre "Nombres de listas" el que contiene los nombres de todas las listas
        File nombresDeListas = new File("Nombres de listas.txt");

        //2. Se crean los objetos necesarios para leer este archivo
        fire = new FileReader(nombresDeListas);
        bure = new BufferedReader(fire);

        //3. Se lee tamaño, indica la cantidad de listas que había la última vez que se cerró el programa
        int tamaño = Integer.parseInt(bure.readLine());
        String unNombre="";
        //4. Se leen todos los nombres, se abren todos los archivos y se crean todos los objetos Lista
        for(int i=0;i<tamaño;i++){
            //4.1. Lea este nombre de lista
            unNombre = bure.readLine();
            //4.2. Carge en un objeto File, el archivo con este nombre leído
            File cargueEsto = new File(unNombre+".txt");
            //4.3. Agregue este objeto file al arraylist de files/archivos
            listasArchivos.add(cargueEsto);

            //4.4. Creción de los objetos necesarios para leer este archivo
            fis = new FileInputStream(cargueEsto);
            lector = new ObjectInputStream(fis);
            //4.5. Lea la lista y cree un objeto lista
            Lista unaLista = (Lista) lector.readObject();
            //4.6 Agregue esta lista al arraylist de listas
            listas.add(unaLista);
        }
        categorizeListas();
        System.out.println("Se cargaron las listas sin problemas");
    }

    public static void main (String args[])throws IOException, ClassNotFoundException
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
