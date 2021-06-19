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
    //A: agregué un método para cerrar todo esto, se llama cierreTodo(). Iba a meterlo inicialmente en cierrePrograma pero decídí dejarlo
    //fuera, dígame usted si aprueba meterlo en cierrePrograma

    //R: Metalo ahi, porque de eso se trata el metodo

    JFileChooser chooser;
    ArrayList<String> categoriasListas;
    ArrayList<Lista> listas;

    EntradaDatos entrada;

    GestorFecha gestorFechas = new GestorFecha();
    public Gestor()
    {
        listas = new ArrayList<Lista>();
        listasArchivos = new ArrayList<File>();
        entrada = new EntradaDatos();
        categoriasListas = new ArrayList<String>();

    }

    public void administreGestor()
    {
        //Hay que dejarle a la lista que modifique Tareas.
        int opcionElegidaLista = 0;
        String mensaje = "";
        //En caso de que el usuario quiera ver una lista, y no existan listas, no podria salir ya que se pidaa que guarde una lista, que no existe
        //, entonces solo en caso de que listas tenga algo, se permitira determinadas opciones
        if(listas.size()==0)
        {
            mensaje = ("No existen listas agregadas\n Digite 1, si desea crear una nueva lista, digite 2 si desea cargar una lista desde el disco duro, Digite 3 si desa Salir");
            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,3 ,1);
            switch(opcionElegidaLista){
                case 1: 
                creeLista();//listo
                break;
                case 2:
                cargueLista();
                break;
                case 3:
                salgaPrograma();//Listo
                break;
            }

        }
        else
        {
            mensaje =("¿Qué desea hacer?\nDigite 1  si desea crear una nueva lista, Digite 2 Mostrar tareas de las listas\nDigite 3 si desea admnistrar una lista, Digite 4 si desea borrar una lista\nDigite 5 si desea salir del gestor");

            opcionElegidaLista=entrada.pidaNumeroRango(mensaje,5,1);

            switch(opcionElegidaLista){
                case 1: 
                creeLista();//Listo
                break;
                case 2:
                llameMuestreLista();
                break;
                case 3:
                administreListaEspecifica();
                break;
                case 4:
                borreLista();
                case 5:
                cargueLista();
                case 6:
                salgaDelGestor();
                break;
            }
        }
    }

    //R: Se deberia analizar muestreTareas como mostrarInformacionTareas para X cantidad de tareas
    //R: Se tiene la situacion de como adminstrar tareas, en teoria, podria pidase la lista a modficar y luego tareas

    public void muestreTareasListas()
    {

        boolean agregarMasListas = true;
        String categoria = "";
        //R: ArrayList no soporta valores si no objetos, por eso mejor pedi tipo lista. 
        ArrayList<Lista> listasSeleccionadas = new ArrayList<Lista>();
        boolean continuarAgregandoCategorias = pregunteSiImportaCategoria();

        while(continuarAgregandoCategorias||listasSeleccionadas.size()<listas.size())
        {
            if(continuarAgregandoCategorias==true)
            {
                if(listasSeleccionadas.size()<listas.size())
                {
                    categoria = seleccioneCategoria();
                }
                else
                {
                    System.out.println("Se han seleccionado todas las listas");
                    agregarMasListas = false;
                }
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
                agregarMasListas = pidaOpcionContinuarAgregarListas();
                if(agregarMasListas ==false)
                {
                    continuarAgregandoCategorias =  false;
                }
            }
            if(continuarAgregandoCategorias == true)
            {
                continuarAgregandoCategorias = pregunteSiContinuarFiltrandoCategorias();
            }
        }
        //R: Se deberia pida N cantidad de listas para mostrar, hay que filtrarlas. El enunciado de la tarea no dice que como filtrarlas, entonces preguntarle al usuario una seleccion
        //R: por eso se usa ArrayLista, notar que podria seguir, pidiendo tareas, entonces es prudente preguntar. Si quiere agregar mas o no.
        String informe="";
        for(int j=0;j<listasSeleccionadas.size();j++)
        {
            for(int i=0;i<listas.size();i++)//No necesariamente se sabe cual es la posicion ni el numero de codigo, se sabe que el numero de lista es la posicion en el array de listas.
            {
                if(listasSeleccionadas.get(j).deCodigoLista() == listas.get(i).deCodigoLista())
                {
                    if(listasSeleccionadas.get(j).listaTareas.size()==0)
                    {
                        System.out.println("Se ignoro la lista "+listasSeleccionadas.get(j).deNombreLista());
                    }
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
        listasSeleccionadas = null;
    }

    public void llameMuestreLista()
    {
        muestreTareasListas();
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

    public void muestreCategoriasListas()
    {
        System.out.println("Se mostraran las categorias de listas");
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
        String mensaje = ("Digite 1 si importa la categoria de la lista, digite 2 si no importa la categoria de la lista");
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
            System.out.println(listaSeleccionada);
            for(int i= 0;i<listas.size();i++)
            {
                if(listas.get(i).deCategoria()==listas.get(listaSeleccionada-1).deCategoria())
                {
                    listaSeleccionadaIncorrecta = false;
                }
            }
            if(listaSeleccionadaIncorrecta==false)
            {
                System.out.println("La lista seleccionada no es valida, intente de nuevo");
            }
        }
        return listaSeleccionada;
    }

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

    public void cargueLista()
    {
        categorizeListas();
    }

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

    public void salgaDelGestor()
    {}

    public void salgaPrograma()
    {
        System.exit(0);
    }

    public void cierreTodo()throws IOException{
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
    public void cargeListas()throws IOException,ClassNotFoundException,FileNotFoundException{
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
    }

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
