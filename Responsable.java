import java.util.ArrayList;
import java.io.Serializable;
/**
 * 
 *@author Randy Agüero B90082 y Andrés Serrano C07483
 */
public class Responsable implements Serializable
{
    int cantidadHorasDisponibles;
    int cantidadHorasDedicadas;
    ArrayList<Tarea> tareasAsignadas;
    int codigoResponsable;
    String nombre;
    String identificacion;//Nom +cod
    EntradaDatos entrada;
    public Responsable(int codResp)//Lo mismo, se deberia cambiar, es poco importante ya que el usuario va a seleccionar un responsable
    {
        codigoResponsable = codResp;
        nombre = " ";
        identificacion = "";
        cantidadHorasDedicadas = 0;
        cantidadHorasDisponibles = 0;
        entrada = new EntradaDatos();
    }

    public void genereNombre()
    {
        String mensaje = ("Digite el nombre de la persona responsable");
        nombre = entrada.pidaTexto(mensaje);
    }

    public void genereCantidadHorasDedicadas()
    {
        String mensaje = ("Digite la cantidad de horas que el responsable usa semanalmente para hacer tareas\nEl responsable debe dedicarse al menos 1 hora y como maximo 40");
        cantidadHorasDedicadas = entrada.pidaNumeroRango(mensaje,40, 1);
        cantidadHorasDisponibles = cantidadHorasDedicadas;
    }

    public void genereIdentificacion()
    {
        identificacion = codigoResponsable+nombre;//Para mostrar seria util mostrar solo una cantidad x de caracteres solo por visualizacion
    }

    public void asigneTarea(Tarea tarAsig)
    {
        Tarea tareaAsignada = tarAsig;
        tareasAsignadas.add(tareaAsignada);
    }
    
    //A
    public void desasigneTarea(Tarea tarAsig)
    {
        tareasAsignadas.remove(tarAsig);
    }
    
    public void editeNombre()
    {
        genereIdentificacion();
    }
    public void editeHorasDedicadas()
    {
    }
    public void editeCodigo(int numCodigo)
    {
     codigoResponsable = numCodigo;
     genereIdentificacion();
    }

    public boolean verifiqueResponsableDesocupado(int cantNuevHoras)
    {
        boolean responsableHorasOcupado = false;
        int cantidadNuevaHorasDedicadas = 0;
        int cantidadHorasOcupadas = cantidadHorasDedicadas-cantidadHorasDisponibles;//Revisar si esto funciona
        String mensaje = ("Digite la cantidad de horas que el responsable usa semanalmente para hacer tareas\nEl responsable debe dedicarse al menos 1 hora y como maximo 40");
        //Si se tienen horas asignadas, el trabajador no puede dejar tareas sin responsable.
        //Estaria la posibilidad de pida solo un numero mayor que cantidad horas ocupadas
        //Sin embargo lo mejor es informar al usuario y que pregunte si desea eliminar tareas en caso de reducir el numero de horas de Dedicadas y esten ocupadas.
        //Esto tambien daria el caso de tareas que no pueden asignarse.
        cantidadNuevaHorasDedicadas = entrada.pidaNumeroRango(mensaje, 40,1);
        if(cantidadNuevaHorasDedicadas<cantidadHorasDedicadas)
        {
            //En caso de tener tareas, no se pueden dejar sin responsable, al menos que el usuario lo indique.
            //El programa debe ser capaz de resolverle al usuario la situacion de bajar la cantidad de horas a la semana,
            //Sin quedarse sin espacio, excepto a eleccion del usuario.
            //Otra cosa que se puede analizar es que se podria obligar al usuario a desasignarTareas hasta que tenga horas asignadas menores o iguales a las nuevas horas 
            if(cantidadNuevaHorasDedicadas<cantidadHorasOcupadas)
            {
                // System.out.println("El responsable "+nombre+" T
            }
        }
        return responsableHorasOcupado;
    }

    public void muestreInformacion()
    {
        String informacionAmostrar = ("Nombre responsable: "+nombre+" Codigo"+codigoResponsable+"\n");
        informacionAmostrar += ("\tTareas Asignadas:\n");//Se agrega \t apenas para dar un espacio, apenas por estetica
        informacionAmostrar += deNombreTareasAsignadas();
        informacionAmostrar += ("\tCantidad de horas por semana \n");
        informacionAmostrar += ("\t\tTotales "+cantidadHorasDedicadas);
        informacionAmostrar += ("\t\tCantidad de horas disponibles "+cantidadHorasDisponibles);
        informacionAmostrar += ("\t\tCantidad de horasOcupadas "+(cantidadHorasDedicadas-cantidadHorasDisponibles));
        
        System.out.println(informacionAmostrar);
    }

    public String deNombreTareasAsignadas()
    {
        String infoTareasAsignadas = "";
        for( int i=0;i<tareasAsignadas.size();i++)
        {
            infoTareasAsignadas += ("\t\tNombre lista: "+tareasAsignadas.get(i).deListaPerteneciente()+" Nombre tarea: "+tareasAsignadas.get(i).deNombre()+"\n");
        }
        return infoTareasAsignadas;
    }
    public int deCodigoResponsable()
    {
        return codigoResponsable;
    }

    public void cambieCantidadHorasDedicadas(int nuevHoras)
    {
        
    }
    public String deNombre()
    {
        return nombre;
    }
    public String deIdentificacion()
    {
        return identificacion;
    }

    public static void main (String args[])
    {
        EntradaDatos entrada = new EntradaDatos();
        int num = entrada.pidaNumero("num",1);
        Responsable responsable1 = new Responsable(num);

    }
}
