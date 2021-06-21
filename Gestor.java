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

    GestorFecha gestorFechas;

    int diaActual;
    int mesActual;
    int anoActual;
    String fechaActual;
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

    //void administreGestor()
    //void creeLista()
    //boolean pedirOpcionAgregarCategoria()
    //muestreCategoriasListas()
    //agregarCategoria()
    //verifiqueExistenciaCategoria()
    //seleccioneCategoria()
    //muestreCategoriasListas()
    //categorizeListas()

    public Gestor()
    {
        listas = new ArrayList<Lista>();
        listasArchivos = new ArrayList<File>();
        entrada = new EntradaDatos();
        categoriasListas = new ArrayList<String>();
        gestorFechas = new GestorFecha();
        diaActual = 0;
        mesActual = 0;
        anoActual = 0;

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
            System.out.println("No existe guardada una fecha en el gestor, por favor ingrese los datos");
            seleccioneFechaActual();

            mensaje = ("No existen listas agregadas\n Digite 1, si desea crear una nueva lista, Digite 2 si desa Salir");
            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,3 ,1);
            switch(opcionElegidaLista){
                case 1: 
                creeLista();//listo
                break;
                case 2:
                salgaPrograma();//Listo
                break;
            }

        }
        else
        {
            mensaje =("¿Qué desea hacer?\nDigite 1  si desea crear una nueva lista, Digite 2 si desea Mostrar, modificar o progresar tareas ya creadas \nDigite 3 si desea modificar una lista o agregar tareas a una lista,\nDigite 4 si desea modificar categorias, Digite 5 si desea salir del gestor");

            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,5,1);

            switch(opcionElegidaLista){
                case 1: 
                creeLista(); 
                break;
                case 2:
                administreTareas();
                break;
                case 3:
                administreListaEspecifica();               
                break;       
                case 4:
                modifiqueCategoriasGuardadas();
                break;
                case 5:
                salgaDelGestor();
                break;
            }
        }
    }

    public void seleccioneFechaActual()
    {
        fechaActual = gestorFechas.pidaFecha();
        diaActual = gestorFechas.deDiaInicial();
        mesActual = gestorFechas.deMesInicial();
        anoActual = gestorFechas.deAnoInicial();
    }

    public void administreListaEspecifica()
    {
        boolean importaCategoria = pregunteSiImportaCategoria();
        String categoria;
        int posListaElegida = 0;
        int opcionUsuario = 0;
        String mensaje = (" ");
        if(importaCategoria == true)
        {
            categoria = seleccioneCategoria();
            posListaElegida = seleccioneListaCategorias(categoria);

        }
        else
        {
            posListaElegida = seleccioneLista();

        }
        mensaje = ("Digite 1 si desea modificar o agregar una tarea a la lista, Digite 2 si desea eliminar la lista");
        opcionUsuario = entrada.pidaNumeroRango(mensaje,2,1);
        switch(opcionUsuario)
        {
            case 1:
            listas.get(posListaElegida).administreLista();
            break;
            case 2:
            listas.remove(posListaElegida);
            modificarCodigosLista();
            categorizeListas();
            break;
        }

    }

    public ArrayList<Lista> seleccioneListasTareas()
    {

        String categoria = "";
        //R: ArrayList no soporta valores si no objetos, por eso mejor pedi tipo lista. 
        ArrayList<Lista> listasSeleccionadas = new ArrayList<Lista>();
        boolean continuarAgregandoCategorias = pregunteSiImportaCategoria();
        boolean agregarMasListas = true;
        int listaSeleccionada = 0;
        boolean listaAgregada = true;
        while(agregarMasListas)
        {
            if(continuarAgregandoCategorias == true)
            {
                categoria = seleccioneCategoria();
                listaSeleccionada = seleccioneListaCategorias(categoria);
            }
            else
            {
                listaSeleccionada = seleccioneLista();
            }

            int seleccionLista = listaSeleccionada;
            if(verifiqueSiTareaSeleccionada(listas.get(seleccionLista),listasSeleccionadas )== false)
            {
                listasSeleccionadas.add(listas.get(seleccionLista));
            }
            else
            {
                System.out.println("Error, La tarea elegida ya ha sido seleccionada");

            }

            if(listasSeleccionadas.size()!=listas.size())
            {
                agregarMasListas = pidaOpcionContinuarAgregarListas();//Retorn un boolean, en caso de falso se sale del while
                if(agregarMasListas == true)
                {
                    continuarAgregandoCategorias = pregunteSiContinuarFiltrandoCategorias();
                }
            }
            else
            {
                System.out.println("No se pueden seleccionar mas listas, ya se han seleccionado todas");
                agregarMasListas = false;
                continuarAgregandoCategorias = false;
            }

        }
        //R:Deberia ir eso en un metodo aparte
        //R: Se deberia pida N cantidad de listas para mostrar, hay que filtrarlas. El enunciado de la tarea no dice que como filtrarlas, entonces preguntarle al usuario una seleccion
        //R: por eso se usa ArrayLista, notar que podria seguir, pidiendo tareas, entonces es prudente preguntar. Si quiere agregar mas o no.
        listasSeleccionadas.size();
        return listasSeleccionadas;
    }

    public void administreTareas()
    {
        ArrayList<Lista> seleccionLista = seleccioneListasTareas();
        muestreTareas(seleccionLista);
        seleccionLista = elimineListasVacias(seleccionLista);
        System.out.println(seleccionLista.size()+" asd");
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
                seleccioneFechaActual();
                actualizeFechas();
            }
            //Se va a usar la identificacion de la lista y la identificacion de la tarea para editar eliminar o algo en las tareas-
        }
        else
        {
            System.out.println("Todas las listas seleccionadas estan vacias, debe seleccionar otras listas, o agregar tareas para poder gestionar las tareas");
        }
    }

    public void actualizeFechas()
    {
        for(int i=0;i<listas.size();i++)
        {
            listas.get(i).actualizeFechas(fechaActual, diaActual,mesActual,anoActual);
        }
    }

    public ArrayList<Lista> elimineListasVacias(ArrayList<Lista> listaSel)
    {
        ArrayList<Lista> lista = new ArrayList<Lista>();

        for(int i=0;i<listaSel.size();i++)
        {
            System.out.println("i"+i);
            if(listaSel.get(i).deCantidadTareas()>0)
            {
                
                lista.add(listaSel.get(i));
                System.out.println(lista.get(i).deCantidadTareas()+"cant");
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
        }else if(seleccionUsuario==2){
            filtrarCategorias = false;
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
        System.out.println(categoriaSeleccionada);
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
        int opcionAgregarCategoria = pedirOpcionAgregarCategoria();
        String categoriaSeleccionada ="";
        switch(opcionAgregarCategoria)
        {
            case 1://Agregar una categoria nueva
            categoriaSeleccionada = agregarCategoria();
            break;
            case 2://Seleccionar una lista existente
            categoriaSeleccionada = seleccioneCategoria();
            break;
            case 3://dejar lista vacia;
            System.out.println("Se ha seleccionado dejar la lista sin categoria");
            categoriaSeleccionada = "Sin categoria";
            break;
        }

        Lista nuevaLista = new Lista(listas.size()+1,categoriaSeleccionada);
        nuevaLista.fijeFecha(fechaActual,diaActual,mesActual,anoActual);
        listas.add(nuevaLista);
        //Notar que cada vez que listas se agrega, se debe guardar en un archivo
        File nuevoFile = new File(nuevaLista.deNombreLista()+".txt");//<-- el archivo se llama igual que la lista. Se le adiciona el .txt
        listasArchivos.add(nuevoFile);
        categorizeListas();
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
        System.out.println("Digite el codigo de la lista");
        mensaje =  ("Digite un numero entre 1 y "+listas.size());
        posListaSeleccionada = entrada.pidaNumeroRango(mensaje,listas.size(),1);
        posListaSeleccionada--;//se sabe que el codigo es la posicion de la lista, entonces si se selecciona 1 de codigo la posicion es 0;
        return posListaSeleccionada;
    }

    public int seleccioneListaCategorias(String cat)
    {
        int listaSeleccionada = 0;

        boolean listaSeleccionadaIncorrecta = true;
        while(listaSeleccionadaIncorrecta)
        { 
            muestreListasCategoria(cat);
            String mensaje = ("Digite el codigo de la lista que desea seleccionar");
            listaSeleccionada = entrada.pidaNumeroRango(mensaje,listas.size(),1);
            listaSeleccionada--;
            for(int i= 0;i<listas.size();i++)
            {
                if(listas.get(listaSeleccionada).deCategoria().equals(cat)==true)
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
    public int pedirOpcionAgregarCategoria()
    {
        muestreCategoriasListas();

        boolean entradaIncorrecta = true;
        String mensaje =("Digite 1 si desea agregar una nueva categoria, Digite 2 si desea usar una categoria existente, Digite 3 si desea agregar una lista sin categoria");
        int opcionCategoria = entrada.pidaNumeroRango(mensaje,3,1);

        if((opcionCategoria == 2)&(categoriasListas.size()==0))
        {
            System.out.println("No han existido listas agregadas por lo tanto no hay categorias");

            mensaje = ("Digite 1 si desea agregar una categoria nueva, Digite 2 si desea agregar una lista sin categoria");
            int opcionSegunda = entrada.pidaNumeroRango(mensaje,2,1);
            if(opcionSegunda==1)
            {
                opcionCategoria = 1;

            }
            else
            {
                opcionCategoria = 3;
            }
        }

        return opcionCategoria;
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
        categoriasListas.clear();//Se limpia para volver a encontrar todas las categorias usadas
        String categoria = "";
        boolean existeCategoria = false;
        boolean categoriaUsada = false;
        for (int i = 0;i<listas.size();i++)
        {

            categoria = listas.get(i).deCategoria();

            existeCategoria = verifiqueExistenciaCategoria(categoria);

            if(existeCategoria==false)
            {

                categoriasListas.add(categoria);

            }
        }

    }

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

    public void modifiqueCategoriasGuardadas()
    {
        muestreCategoriasListas();
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

    }

    public void modifiqueCategoria(String nomAct, String nomNuev)
    {
        for(int i=0;i<listas.size();i++)
        {
            if (listas.get(i).deCategoria().equals(nomAct) == true)
            {
                listas.get(i).modifiqueCategoria(nomNuev);
                System.out.println(listas.get(i).deCategoria());
            }
        }
        categorizeListas();
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
