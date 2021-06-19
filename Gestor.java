import java.io.File;
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
    File file;
    FileInputStream fis;
    ObjectInputStream lector;
    FileOutputStream fos;
    ObjectOutputStream escritor;
    JFileChooser chooser;
    ArrayList<String> categoriasListas;
    ArrayList<Lista> listas;

    EntradaDatos entrada;

    GestorFecha gestorFechas = new GestorFecha();
    public Gestor()
    {
        listas = new ArrayList<Lista>();
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

    }

    public void borreLista()
    {
        //Tomar en cuenta que se debe cambiar todos los codigos de lista.
        //Se debe eliminar lista de archivos.
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

    //********Métodos de escritura y lectura*************

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
