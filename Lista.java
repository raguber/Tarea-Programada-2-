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
    int codigoLista,diaActual,mesActual,anoActual;
    String fechaActual;
    String directorio;
    public Lista(int codLista,String catLista)
    {
        entrada = new EntradaDatos();
        listaResponsables = new ArrayList<Responsable>();
        listaRecursos = new ArrayList<Recurso>();
        String directorio = " ";
        nombreLista = "";
        identificacion = " ";
        fechaActual = "";
        diaActual = 0;
        mesActual = 0;
        anoActual = 0;
        
        codigoLista = codLista;
        categoriaLista = catLista;

        genereNombreLista();
        genereDescripcion();

        genereIdentificacion(); 

        listaTareas = new ArrayList<Tarea>();
    }
    public String deInfoTareas()
    {
        String info = "";
        for(int i= 0;i<listaTareas.size();i++)
        {
            info = listaTareas.get(i).muestreInformacion();
            System.out.println(info);
        }
        return info;
    }

    public void administreLista()
    {
        int opcionUsuario = 0;
        int seleccionTarea = 0;
        String mensaje = ("Digite 1 si desea agregar una tarea, Digite 2 si desea eliminar o modificar una tarea\n Digite 3 si desea modificar o agregar responsables, Digite 4 si desea modificar o agregar los recursos\n Digite 5 si desea eliminar una tarea, Digite 6 si desea modificar el nombre de la lista, \nDigite 7 si desea modificar la descripcion de la lista,Digite 8 si desea modificar la categoria de la lista ");
        opcionUsuario = entrada.pidaNumeroRango(mensaje,5,1);
        switch(opcionUsuario)
        {
            case 1:
            agregueTarea();

            break;

            case 2:
            seleccionTarea = pidaSeleccionTarea();
            modifiqueTarea(seleccionTarea);

            break;
            case 3:
            modifiqueAdmResponsables();

            break;
            case 4:
            modifiqueAdmRecursos();
            break;
            case 5:
            seleccionTarea = pidaSeleccionTarea();
            elimineTarea(seleccionTarea);
            break;
            case 6:
            modifiqueNombreLista();
            break;
            case 7: 
            modifiqueDescripcionLista();
            break;
            case 8:
            modifiqueCategoriaLista();

            break;

        }
    }

    public void modifiqueCategoriaLista()
    {

    }

    public int seleccioneRecurso()
    {

        int recSel = 0;
        String mensaje = "";
        for(int i = 0; i<listaRecursos.size();i++)
        {
            listaRecursos.get(i).muestreInformacion();
        }
        mensaje += ("Digite el codigo del recurso");
        recSel = entrada.pidaNumeroRango(mensaje,listaRecursos.size(),1);
        recSel--;
        return recSel;
    }

    public void modifiqueAdmResponsables()
    {
        int opcionModificarResponsables = pidaOpcionModResponsables();
        int respSel = 0;
        switch(opcionModificarResponsables)
        {
            case 1:
            respSel = seleccioneResponsables();
            listaResponsables.get(respSel).desasigneTarea();

            break;
            case 2:
            respSel = seleccioneResponsables();
            listaResponsables.get(respSel).editeNombre();
            break;
            case 3:
            respSel = seleccioneResponsables();
            listaResponsables.get(respSel).editeHorasDedicadas();
            break;
            case 4:
            Responsable resp = new Responsable((listaResponsables.size()+1));
            listaResponsables.add(resp);
        }
    }

    public int pidaOpcionModResponsables()
    {
        int opcionSel = 0;
        String mensaje = ("Digite 1 si desea eliminar tareas de un responsable, Digte 2 si desea modificar el nombre de un responsable, Digite 3 si desea modificar las horas asignadas de un responsable, Digite 4 si desea agregar un responsable");
        opcionSel = entrada.pidaNumeroRango(mensaje,4,1);
        return opcionSel;
    }

    public int seleccioneResponsables()
    {
        int respSel = 0;
        String mensaje = "";
        for(int i = 0; i<listaResponsables.size();i++)
        {
            listaResponsables.get(i).muestreInformacion();
        }
        mensaje = ("Digite el codigo de responsable");
        respSel = entrada.pidaNumeroRango(mensaje,listaResponsables.size(),1);
        respSel--;
        return respSel;
    }

    public int pidaSeleccionTarea()
    {
        int tareaSel = 0;
        String mensaje = "";

        for(int i = 0;i<listaTareas.size();i++)
        {
            mensaje += ("Codigo tarea: "+listaTareas.get(i).deCodigoTarea()+" nombre"+listaTareas.get(i).deNombre());
            mensaje += "\n";
        }
        mensaje = ("Digite el codigo de la tarea");
        tareaSel = entrada.pidaNumeroRango(mensaje,listaTareas.size(),1);
        tareaSel--;
        return tareaSel;
    }

    public int pidaOpcionAdministreLista()
    {
        int opcionSeleccionada = 0;
        String mensaje = ("Digite 1 si desea agregar una tarea, Digite 2 si desea eliminar o modificar una tarea\n Digite 3 si desea modificar o agregar responsables, Digite 4 si desea modificar o agregar los recursos\n Digite 5 si desea eliminar una tarea ");
        opcionSeleccionada = entrada.pidaNumeroRango(mensaje,5,1);
        return opcionSeleccionada;
    }

    public void administreResponsables()
    {

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
        opcionElegida = entrada.pidaNumeroRango(mensaje,3,1);

        switch (opcionElegida)
        {
            case 1 :
            editarResponsable();
            break;
            case 2 :
            // eliminarResponsable();
            // actualizeTareas();
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

    public void muestreInformacionResponsables()
    {
        System.out.println("Se mostrarán las categorías de listas");
        for(int i=0;i<listaResponsables.size();i++)
        {
            System.out.println("Responsable: "+(i+1)+" "+listaResponsables.get(i).nombre);
        }
    }

    //A:pidaEleccionResponsable
    public int pidaEleccionResponsable()
    {
        String mensaje = "Digite el número del responsable que desea seleccionar, debe ser un número entre 1 y"+listaResponsables.size();
        int posicionResponsable = entrada.pidaNumeroRango(mensaje,listaResponsables.size(),1);

        return posicionResponsable;
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
        Tarea nuevaTarea;
        Responsable resp;
        Recurso rec;
        if(listaResponsables.size()==0)
        {
            System.out.println("No existen responsables guardados se debe crear uno");
            resp = new Responsable((listaResponsables.size()+1));



        }
        else
        {
            resp = pidaResponsable();


        }
        if(listaRecursos.size()==0)
        {
            System.out.println("No existen recursos guardados se debe crear uno");
            rec = new Recurso((listaRecursos.size()+1));


        }
        else
        {
            rec = pidaRecurso();

        }
        nuevaTarea=new Tarea(listaTareas.size()+1,nombreLista,resp,rec);


        

        nuevaTarea.responsable=resp;
        nuevaTarea.recursos.add(rec);
        if(listaTareas.size()>0){
            pregunteSobreDependencia();
        }
        nuevaTarea.fijeFecha(fechaActual, diaActual,mesActual,anoActual);
        listaTareas.add(nuevaTarea);
    }
    public void fijeFecha(String fechaA, int diaA,int mesA,int anoA)
    {
        fechaActual = fechaA;
        diaActual = diaA;
        mesActual = mesA;
        anoActual = anoA;
    }
    public void pregunteSobreDependencia(){
        String mensaje = "¿Este tarea depende de otra?\nDigite 1 = Sí, Digite 2 = No";
        int respuesta = entrada.pidaNumeroRango(mensaje,2,1);
        if(respuesta==1){
            agregueDependencias(pidaSeleccionTarea());
        }
    }
    
    public void agregueDependencias(int tarSel){
        boolean noMas=false;
        Tarea tengoDependiente;
        int seleccion;
        String mensaje ="¿De quién depende esta tarea?\nDigite el código o -1 para salir";
        while(noMas==false){
            seleccion = entrada.pidaNumeroRango(mensaje,listaTareas.size(),1);
            if(seleccion==-1){
                noMas=true;
            }else{
                tengoDependiente = listaTareas.get(seleccion);
                listaTareas.get(tarSel).dependaDe(tengoDependiente);
            }
        }
    }
    
    public Responsable pidaResponsable()
    {
        Responsable respSel = listaResponsables.get(0);

        String mensaje = ("Digite 1 si desea seleccionar un responsable existente, Digite 2 si desea seleccionar un responsable nuevo");
        int opcion = entrada.pidaNumeroRango(mensaje,2,1);
        if((opcion == 1)&&(listaResponsables.size()>0))
        {
            int posRes;
            posRes = seleccioneResponsables();
            respSel = listaResponsables.get(posRes);
        }
        else
        {
            if(listaResponsables.size()==0&&(opcion ==1))
            {
                System.out.println("No existen responsables guardados, debe hacer un nuevo");
            }
            respSel = new Responsable(listaResponsables.size());
        }

        return respSel;
    }

    public Recurso pidaRecurso()
    {
        Recurso recSel = listaRecursos.get(0);
        String mensaje = ("Digite 1 si desea seleccionar un recurso existente, Digite 2 si desea seleccionar un recurso nuevo");
        int opcion = entrada.pidaNumeroRango(mensaje,2,1);
        if((opcion == 1)&&(listaRecursos.size()>0))
        {
            int posRec;
            posRec = seleccioneRecurso();
            recSel = listaRecursos.get(posRec);
        }
        else
        {
            if(listaRecursos.size()==0&&(opcion ==1))
            {
                System.out.println("No existen responsables guardados, debe hacer un nuevo");
            }
            recSel = new Recurso(listaResponsables.size()+1);
        }

        return recSel;
    }

    public void mostrarLista()
    {
        String mostrarInfo ="";
        mostrarInfo += (" Codigo Lista "+codigoLista+"\n");
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
            infoTareas += ("\t Responsables  : "+listaTareas.get(i).deResponsablesNombre()+" Estado Tarea : "+listaTareas.get(i).estadoTarea+"\n");
        }
        return infoTareas;
    }
    

    public void modifiqueTarea(int tarSel)
    {
        int opcionModificacionTareas = pidaOpcionModTareas();
        switch(opcionModificacionTareas)
        {
            case 1:
            elimineTarea(tarSel);
            break;
            case 2:
            modifiqueResponsablesTarea(tarSel);
            break;
            case 3:
            modifiqueRecursos(tarSel);
            break;
            case 4:
            expandaFechaFinal(tarSel);
            break;
            case 5:
            agregueDineroTarea(tarSel);
            break;
            case 6:
            listaTareas.get(tarSel).modifiqueNombre();
            case 7:
            listaTareas.get(tarSel).modifiqueDescripcion();
        }
        //Metodo eliminar, modificarReponsables, modificarRecursosAqui,etc aqui
        //Metodo modNombre, descripcion, aqui.
        //Si se llama a eliminarTarea; entonces hay que generar de nuevo codigos de tarea
    }

    public int pidaOpcionModTareas()
    {
        int eleccion = 0;
        String mensaje = ("Digite 1 si desea eliminar la tarea, Digite 2 si desea modificar los responsables de la tarea\nDigite 3 si desea modificar los recursos de la tarea, Digite 4 si desea expandir la fecha de finalizacion\nDigite 5 para agregar dinero a la tarea, Digite 6 para modificar el nombre de la tarea\nDigite 7 para modificar la descripcion de la tarea");
        eleccion = entrada.pidaNumeroRango(mensaje,5,1);
        return eleccion;
    }
    public void actualizeFechas(String fecha,int diaA,int mes,int anoA)
    {
        fechaActual = fecha;
        diaActual = diaA;
        mesActual = mes;
        anoActual = anoA;
        progreseTareas();
    }
    public void progreseTareas()
    {
        for(int i=0;i<listaTareas.size();i++)
        {
            listaTareas.get(i).progrese(fechaActual,diaActual,mesActual,anoActual);
        }
    }
    

    public void modifiqueResponsablesTarea(int tarSel)
    {
        


    }

    public void modifiqueRecursos(int tarSel)
    {

    }

    public void expandaFechaFinal(int tarSel)
    {

    }

    public void agregueDineroTarea(int tarSel)
    {
        listaTareas.get(tarSel).progreseDinero();
    }

    public void modifiqueCategoria(String nuvCat)
    {
        categoriaLista = nuvCat;
    }

    public void elimineTarea(int tarSel)
    {
        listaTareas.remove(tarSel);
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

    public void modifiqueResponsable()
    {

    }

    public void modifiqueAdmRecursos()
    {
        String mensaje = ("Digite 1 si desea modificar el nombre de un recurso, digite 2 si desea modificar la descripcion de un recurso, digite 3 si desea eliminar un recurso");
        int opcionSel = entrada.pidaNumeroRango(mensaje,3,1);
        int recSel = 0;
        switch(opcionSel)
        {
            case 1:
            recSel = seleccioneRecurso();
            listaRecursos.get(recSel).modifiqueNombre();
            break;
            case 2:
            recSel = seleccioneRecurso();
            //listaRecursos.get(recSel).modifiqueDescripcion();
            break;
            case 3:
            recSel = seleccioneRecurso();
            // if(listaRecursos.get(recSel).estaAsignado()==true)
            // {
            // System.out.println("El recurso esta asignado, hasta que no se termine la tarea con el recurso asignado, no se puede eliminar el recurso");
            // }
            // else
            // {
            // listaRecursos.remove(recSel);
            // }
            break;
        }

    }

    public void modiqueNombreLista()
    {
        String mensaje = ("Digite el nuevo nombre de la lista "+nombreLista);
        nombreLista = entrada.pidaTexto(mensaje);
    }

    public void modifiqueDescripcionLista()
    {
        String mensaje = ("Digite la nueva descripcion de la lista "+nombreLista);
        nombreLista = entrada.pidaTexto(mensaje);
    }

    public static void main (String Args[])
    {
        Lista lista1 = new Lista(0,"VAcias");
        lista1.genereNombreLista();
        lista1.genereIdentificacion();
        lista1.genereDescripcion();

    }
}
