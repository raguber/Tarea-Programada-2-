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
    public Responsable(int codResp)
    {
        entrada = new EntradaDatos();
        codigoResponsable = codResp;
        nombre = " ";
        identificacion = "";
        cantidadHorasDedicadas = 0;
        cantidadHorasDisponibles = 0;
        genereNombre();
        genereIdentificacion();
        genereCantidadHorasDedicadas();
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
        identificacion = codigoResponsable+nombre;
    }

    public void asigneTarea(Tarea tarAsig)
    {
        Tarea tareaAsignada = tarAsig;
        tareasAsignadas.add(tareaAsignada);
    }
    
    public void desasigneTarea()
    {
        
    }
    public void elimineTarea(Tarea tarAsig)
    {
        tareasAsignadas.remove(tarAsig);
    }
    
    public void editeNombre()
    {
        String mensaje = ("Digite el nuevo nombre del responsable "+nombre);
        nombre = entrada.pidaTexto(mensaje);
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
        
        cantidadNuevaHorasDedicadas = entrada.pidaNumeroRango(mensaje, 40,1);
        if(cantidadNuevaHorasDedicadas<cantidadHorasDedicadas)
        {
            
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
