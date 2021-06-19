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
        //En caso de que el usuario quiera ver una lista, y no existan listas, no podria salir ya que se pedira que guarde una lista, que no existe
        //, entonces solo en caso de que listas tenga algo, se permitira determinadas opciones
        if(listas == null)
        {
            mensaje = ("No existen listas agregadas\n Digite 1, si desea crear una nueva lista, digite 2 si desea cargar una lista desde el disco duro, Digite 3 si desa Salir");
            opcionElegidaLista=entrada.pedirNumeroRango(mensaje,3 ,1);
            switch(opcionElegidaLista){
                case 1: 
                creeLista();
                break;
                case 2:
                cargueLista();
                break;
                case 3:
                salgaPrograma();
                break;
            }

        }
        else
        {
            mensaje =("¿Qué desea hacer?\n Digite 1  si desea crear una nueva lista, Digite 2 Mostrar listas\nDigite 3 si desea aministrar una lista, Digite 4 si desea borrar una lista\nDigite 5 si desea salir del gestor");

            opcionElegidaLista=entrada.pedirNumeroRango(mensaje,5,1);

            switch(opcionElegidaLista){
                case 1: 
                creeLista();
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
    //R: Se tiene la situacion de como adminstrar tareas, en teoria, podria pedirse la lista a modficar y luego tareas

    public void muestreTareasListas()
    {
        boolean continuarAgregandoCategorias;
        boolean agregarMasListas = true;
        //R: ArrayList no soporta valores si no objetos, por eso mejor pedi tipo lista. 
        ArrayList<Lista> listasSeleccionadas = new ArrayList<Lista>();
        continuarAgregandoCategorias = pregunteSiImportaCategoria();

        while(continuarAgregandoCategorias||listasSeleccionadas.size()<listas.size())
        {
            if(continuarAgregandoCategorias==true)
            {
                if(listasSeleccionadas.size()<listas.size())
                {
    
                }
                else
                {
                    System.out.println("Se han seleccionado todas las listas");
                    agregarMasListas = false;
                }
            }
            while(agregarMasListas)
            {
                int seleccionLista = seleccioneLista(continuarAgregandoCategorias,"cat");
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
                    //R: creo que se deberia mostrar un poco mas de info
                    informe+="("+i+") "+listas.get(i).nombreLista+"\n\n";
                    for(int t=0;t<listas.get(i).tareas.size();i++){
                        informe+= "\t"+t+". "+listas.get(i).tareas.get(t).nombre+"\n";
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
        seleccionUsuario = entrada.pedirNumeroRango(mensaje,2,1);
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
        
        if(importaCategoria == true)
        {
            
        }
        int posListaElegida = 1;
        listas.get(posListaElegida).administreLista();
    }

    // public Lista preguntePorCategoria()
    // {
        
    // }

    public boolean pregunteSiImportaCategoria()
    {
        boolean importaCategoria = false;
        int seleccionUsuario = 0;
        String mensaje = ("Digite 1 si importa la categoria de la lista, digite 2 si no importa la categoria de la lista");
        seleccionUsuario = entrada.pedirNumeroRango(mensaje,2,1);
        if(seleccionUsuario ==1)
        {
            importaCategoria = true;
        }
        return importaCategoria;

    }
    //R: se modifico lista, recordar que se hablo de codigo de lista variable
    public void creeLista(){
        Lista nuevaLista = new Lista(listas.size());
        listas.add(nuevaLista);
        //Notar que cada vez que listas se agrega, se debe guardar en un archivo
        File nuevoFile = new File(nuevaLista.deNombreLista()+".txt");//<-- el archivo se llama igual que la lista. Se le adiciona el .txt
        listasArchivos.add(nuevoFile);
    }

    public void borreLista()
    {
        //Tomar en cuenta que se debe cambiar todos los codigos de lista.
        //Se debe eliminar lista de archivos.
        //listas.remove(); //este método no espeficica cuál lista
    }

    public void modificarCodigosLista()
    {

    }

    public int seleccioneLista(boolean impCat, String cat)
    {
        String categoria = cat;
        boolean importaCategoria = impCat;

        int posListaSeleccionada = 0;
        String mensaje = "";
        muestreLista(importaCategoria, categoria);
        System.out.println("Seleccione una lista");
        mensaje =  ("Digite un numero entre 1 y "+listas.size());
        entrada.pedirNumeroRango(mensaje,listas.size(),1);
        return posListaSeleccionada;
    }

    public void muestreLista(boolean importaCategoria, String categoria){}
    //Agregado metodo cargueLista
    public void hacerNuevaLista()
    {}

    public void cargueLista()
    {

    }
    public void categorizeListas()
    {
        String categoria = "";
        boolean existeCategoria = false;
        for (int i = 0;i<listas.size();i++)
        {
            categoria = listas.get(i).deCategoria();
            existeCategoria = verifiqueExistenciaCategoria(categoria);
        }
    }
    public boolean verifiqueExistenciaCategoria(String cat)
    {
        boolean categoriaGuardada = false;
        if(categoriasListas != null)
        {
            for(int i=0;i<categoriasListas.size();i++)
            {
                if(cat.equals(categoriasListas)==true)
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
