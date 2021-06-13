import java.util.ArrayList;
/**
 * 
 *@author Randy Agüero B90082 y Andrés Serrano C07483
 */
public class Responsable
{
    int cantidadHorasDisponibles;
    int cantidadHorasTrabajo;
    ArrayList<Tarea> tareasAsignadas;
    int codigoResponsable;
    String nombre;
    String identificacion;//Nom +cod
    EntradaDatos entrada;
    public Responsable(int codResp)
    {
       codigoResponsable = codResp;
       nombre = " ";
       identificacion = "";
       cantidadHorasTrabajo = 0;
       cantidadHorasDisponibles = 0;
       
       entrada = new EntradaDatos();
    }
    public void genereNombre()
    {
        String mensaje = ("Digite el nombre de la persona responsable");
        nombre = entrada.pedirTexto(mensaje);
    }
    public void genereCantidadHorasTrabajo()
    {
        String mensaje = ("Digite la cantidad de horas que el responsable usa semanalmente para hacer tareas\nEl responsable debe dedicarse al menos 1 hora y como maximo 40");
        cantidadHorasTrabajo = entrada.pedirNumeroRango(mensaje,40, 1);
        cantidadHorasDisponibles = cantidadHorasTrabajo;
    }
    public void genereIdentificacion()
    {
        identificacion = codigoResponsable+nombre;//Para mostrar seria util mostrar solo una cantidad x de caracteres solo por visualizacion
    }
    public void asigneTarea(Tarea tarAsig)
    {
        //Tarea tareaAsignada = tarAsig;
        //tarea.add(tareaAsignada);
    }

    public static void main (String args[])
    {
        Responsable responsable1 = new Responsable(0);
        responsable1.genereNombre();
        responsable1.genereIdentificacion();
        responsable1.genereCantidadHorasTrabajo();
        
    }
}
