
import java.util.Scanner;
import java.util.InputMismatchException;

public class GestorFecha
{
    EntradaDatos entrada;
    int diaInicial ;
    int mesInicial;
    int anoInicial;

    int diaNuevo;
    int mesNuevo;
    int anoNuevo;

    int cantidadDias; 
    public GestorFecha()
    {
        entrada = new EntradaDatos();
        diaInicial = 0;
        mesInicial = 0;
        anoInicial = 0;

        diaNuevo = 0;
        mesNuevo = 0;
        anoNuevo= 0;

        cantidadDias = 0; 

        int anosde365 = 0;
        int anosde366 = 0;

    }

    public String pidaFecha()
    {

        String mensaje = ("Digite un nuevo año");
        int anoTemp = -1;

        while(anoTemp<anoInicial)
        {
            anoTemp = entrada.pidaNumero(mensaje,1900);
            if(anoTemp<anoInicial)
            {
                System.out.println("El año ingresado es menor que el año guardado, no se puede volver al pasado");

            }
        }
        anoInicial = anoTemp;
        int mesActualTemp = (mesInicial-1);
        mensaje = ("Digite el numero del mes \n1: enero , 2: febrero, 3: marzo, 4: abril, 5: mayo, 6 junion\n7: julio, 8: Agosto, 9: Septiembre, 10: Octubre , 11: Noviembre, 12, Diciembre");
        while(mesActualTemp<mesInicial)
        {

            mesActualTemp = entrada.pidaNumeroRango(mensaje,12,1);
            if(mesActualTemp<mesInicial)
            {
                System.out.println("El mes ingresado es menor al guardadado, no se puede volver al pasado");
            }
        }
        int diaMaximo = 0;

        switch(mesActualTemp)
        {
            case 1:
            diaMaximo = 31;
            break;
            case 2:
            boolean anoBisiesto = verifiqueAnoBisiesto();
            if(anoBisiesto== true)
            {
                diaMaximo = 29;
            }
            else
            {
                diaMaximo = 28;
            }
            break;
            case 3:
            diaMaximo = 31;
            break;
            case 4:
            diaMaximo = 30;
            break;
            case 5:
            diaMaximo = 31;
            break;
            case 6:
            diaMaximo = 30;
            break;
            case 7:
            diaMaximo = 31;
            break;
            case 8:
            diaMaximo = 31;
            break;
            case 9:
            diaMaximo = 30;
            break;
            case 10:
            diaMaximo = 31;
            break;
            case 11:
            diaMaximo = 30;
            break;
            case 12:
            diaMaximo = 31;
            break;
        }

        int diaActualTemp = (diaInicial-1);
        mensaje = ("Digite el dia, digite un numero entre 1 y "+diaMaximo);
        if(mesActualTemp==mesInicial)

        {
            while(diaActualTemp<=diaInicial)
            {
                diaActualTemp = entrada.pidaNumeroRango(mensaje,diaMaximo,1);

                if(diaActualTemp<=diaInicial)
                {
                    System.out.println("No se puede elegir un dia anterior, si se esta en el mismo mes, no se puede regresar al pasado");
                }
            }
           
        }
        else
        {
            diaActualTemp = entrada.pidaNumeroRango(mensaje,diaMaximo,1);
        }
        diaInicial = diaActualTemp;
        mesInicial = mesActualTemp;
        
        String fechaActual = (diaInicial+"/"+mesInicial+"/"+anoInicial);
        return fechaActual;
    }

    public boolean verifiqueAnoBisiesto()
    {
        boolean anoBisiesto = false;
        if ((anoInicial % 4 ==0)) //Calcula si el año actual es biciesto
        {
            if ((anoInicial % 400 ==0)||(anoInicial % 100 !=0))
            {
                anoBisiesto = true;

            }
            else
            {
                anoBisiesto = false;
            }

        }
        else
        {
            anoBisiesto = false;
        }
        return anoBisiesto;
    }

    public static void main (String args[])
    {

        int anoInicial = 0;
        int diaInicial = 0;
        int mesIncio = 0;

        int diaActual=0;
        int mesActual=0;
        int anoActual=0;

        int diasTranscurridos=0;

        boolean anoBisiesto = false;//Se usara posteriormente para saber si el año actual es bisiesto o no (importante para calcular dias de febrero)
        boolean nacimientoBisiesto = false;//Se usara posteriormente para saber si el año de inicio es bisiesto o no (importante para calcular dias de febrero)
        int mesInicio = 0;
    }
    // while((diaActual<1)||(diaActual>31))
    // {
    // boolean fallo = true;
    // while (fallo)
    // {

    // try
    // {
    // fallo= false;
    // System.out.println("Digite el dia actual en numeros");
    // Scanner entradaDiaActual = new Scanner(System.in);
    // diaActual = entradaDiaActual.nextInt();
    // }
    // catch(java.util.InputMismatchException ioe)
    // {
    // System.out.println("Error caracter no valido, o el dia de actual es menor que 1 o mayor que 31");
    // fallo = true;

    // }

    // }
    // }

    // while((mesActual<1)||(mesActual>12))
    // {
    // boolean fallo =false;
    // try
    // {
    // fallo= false;
    // System.out.println("Digite el mes actual en numeros");
    // Scanner entradaMesActual = new Scanner(System.in);
    // mesActual = entradaMesActual.nextInt();
    // }
    // catch(java.util.InputMismatchException ioe)
    // {
    // System.out.println("Error caracter no valido, o el mes actual es menor que 1 o mayor que 12");
    // fallo = true;

    // }

    // }

    // while((anoActual<anoInicial))
    // {
    // boolean fallo =false;
    // try
    // {
    // fallo= false;
    // System.out.println("Digite el año actual en numeros");
    // Scanner entradaAnoActual = new Scanner(System.in);
    // anoActual = entradaAnoActual.nextInt();
    // }
    // catch(java.util.InputMismatchException ioe)
    // {
    // System.out.println("Error caracter no valido, o el año actual es menor que el año de nacimiento");
    // fallo = true;

    // }

    // }

    // //Aqui se determina si el año de nacimiento es bisiesto o no
    // //Para eso se toma el año y si es multiplo de 4 y a su vez de 400 o de 100, entonces el año de nacimiento de la persona fue bisieseto (en caso de que se cumpliera);
    // if ((anoInicial % 4 ==0)) //Calcula si el año de nacimiento es biciesto
    // {
    // if ((anoInicial % 400 ==0)||(anoInicial % 100 !=0))
    // {
    // nacimientoBisiesto = true;

    // }
    // else
    // {
    // nacimientoBisiesto = false;
    // }

    // }
    // else
    // {
    // nacimientoBisiesto = false;        
    // }

    // //Aqui se determina si el año actual es bisiesto o no
    // //Para eso se toma el año y si es multiplo de 4 y a su vez de 400 o de 100, entonces el año de nacimiento de la persona fue bisieseto (en caso de que se cumpliera);
    // if ((anoActual % 4 ==0)) //Calcula si el año actual es biciesto
    // {
    // if ((anoActual % 400 ==0)||(anoActual % 100 !=0))
    // {
    // anoBisiesto = true;

    // }
    // else
    // {
    // anoBisiesto = false;
    // }

    // }
    // else
    // {
    // anoBisiesto = false;
    // }

    // //Calculo dias hasta primer terminar mes
    // //Aqui se empiezan a calcular los dias
    // //El calculo de dias,Se separa en dos casos, Si el año de nacimiento es igual al año actual (por ejemplo un bebe) o si el año es diferente

    // if ((anoActual!=anoInicial))//En caso de si el año es diferente, se va a hacer lo siguiente:
    // {
    // //Para la suma con el siguiente if, se compara si el mes es de 31 o de 30 dias, o en el caso particular de febrero si tiene 29 o 28 dias (Segun si el año de nacimiento fue bisiesto.
    // //Para calcular eso se hace mediante una simple resta: (la cantidad de dias del mes) - (el dia de nacimiento)

    // //Lo que se calcularia, serian los dias que vivio la persona en el mes en el que nacio, ya que no necesariamente la persona vivio todos los dias del mes en el que nacio.,+
    // //Por lo cual, se calculan esos dias, y despues se van sumando los dias de los meses en forma completa.

    // //La resta anterior se va guardando en una variable, que en el trascurso del desarollo del programa va sumando dias.

    // if (((mesNacimiento %2 !=0)&&(mesNacimiento<9))||(mesNacimiento==8))//Calculo 31 dias exepto agosto (8%2=0) pero tiene 31 dias
    // {

    // diasVividos =31-diaInicial;

    // }
    // else
    // {
    // if ((mesNacimiento==2))
    // {
    // if (anoBisiesto == true)
    // {

    // diasVividos =  29-diaInicial;
    // }
    // else
    // {
    // diasVividos = 28-diaInicial;
    // }
    // }
    // else//Si se cumple que el mes es bisiesto
    // {
    // diasVividos = 30-diaInicial;

    // }
    // }

    // //Para el siguiente for se calculan los meses que la persona vivio completos, hasta finalizar el año  
    // for (int i = mesNacimiento+1; i <= 12; i++)
    // {
    // //Aqui se calcula si el mes tiene 30 o 31 dias, o en el caso particular de febrero si tiene 28 o 29 dias, en caso de ser bisiesto
    // if((i %2 !=0)&&(i<9)||(i==8))// Si los mese son impares menores que nueve o es agos los dias tienen 31 dias (Enero, marzo,mayo....)
    // {
    // diasVividos =diasVividos+31;
    // }
    // else
    // {
    // if (i==2)//Si el mes es febrero analiza si el año tiene 29 o 28 dias
    // {

    // if (nacimientoBisiesto == true)
    // {

    // diasVividos = diasVividos+29;
    // }
    // else
    // {
    // diasVividos = diasVividos +28;

    // }
    // }
    // else
    // {
    // if ( i%2!=0)//Si el mes es impar igual o mayor que 9 los dias van a tener 30 dias (Setiembre, noviembre);
    // {

    // diasVividos =diasVividos+30;
    // }
    // else
    // {
    // diasVividos = diasVividos+31;//Para octubre y Diciembre

    // }
    // }
    // }
    // } 

    // //Ahora se calculan los años que la persona ha vivido completamente,
    // //Se calcula si el año es bisiesto o no, entonces por cada año vivido se va sumando en una unidad si el año es bisiesto
    // //por ejemplo si una persona nacio en el año 1999, en el año 2000 vivio 366
    // //en el programa se va calcular la cantidad de años bisiestoa y la cantidad de años no bisiesto para despues multiplicar por la cantidad de dias

    // for (int i=anoInicial+1;i<anoActual;i++)
    // {

    // if ( i % 4==0)
    // {
    // if((i % 400 ==0)||( i % 100 !=0 ))
    // {
    // anosde366 = anosde366+1;                 
    // }
    // }
    // else
    // {
    // anosde365 =anosde365+1;
    // }
    // }

    // //PAra el calculo de los dias del año actual se van calculando los meses completos, despues los dias vividos del mes actual.

    // for (int i=1; i<mesActual ;i++)//Si la personaha vivido hasta  cierto mes se calcula hasta cierto mes se calcula desde enero, hasta llegar al mes anterior al mes actual

    // {
    // //Aqui se ve si los meses vividos del año actual tieen 31 o 30 dias, o en el casode febrero 28 o 29
    // if (((i %2 !=0)&&(i<9))||(i==8)) //Para Meses de 31 dias hasta agosto"
    // {

    // diasVividos = diasVividos+31;

    // }
    // else
    // {
    // if ((i==2))//Para febrero
    // {

    // if (anoBisiesto == true)
    // {

    // diasVividos =  diasVividos+29;

    // }
    // else
    // {
    // diasVividos = diasVividos+28;

    // }
    // }
    // else//Para los meses dsde septiembre hasta Diciembre, se analiza si es mes impar o par
    // {
    // if ( i % 2 != 0)
    // {
    // diasVividos = diasVividos+31;

    // }
    // else
    // {
    // diasVividos=diasVividos+30;

    // }
    // }
    // }

    // }
    // //Aqui se calculan los dias vividos que faltan
    // //Ademas para sumar los dias vividos del mes actual, simplemente se suma tambien el dia actual.
    // //La explicacion de la suma desde el nacimiento hasta la gecha actual es la siguiente
    // //diasVividos = diasVividos del mes de nacimiento * dias vividos hasta el 31 de diciembre del año de nacimiento, los años completamente vividos*cantidad de dias de cada año)
    // //+los meses enteros vividos del año actual, y los dias que del mes actual.
    // diasVividos=diasVividos+diaActual+((anosde365*365)+(anosde366*366));
    // }
    // else//La persona tiene menos de 1 año
    // {
    // //Si la persona nacio en el mismo año y nacio en el mes actual (tiene menos de 1 mes), con solo calcular el dia actual -  dia nacimiento, se dan los dias vividos
    // if (mesActual==mesNacimiento)
    // {

    // diasVividos = diaActual-diaInicial;

    // }
    // else
    // {//Si la persona tiene mas de 1 mes pero menos de un año
    // //El mes de inicio sera el mes de nacimiento, pues una persona pudo haber nacido en un mes distinto de enero, por lo cual puede tener 1 mes y 10 dias
    // mesInicio = mesNacimiento;
    // //Por ejemplo mes nacimiento = 3 y mes actual = 5, se calcularan el mes 3 y 4 completos, y se sumara el dia actual
    // for (int i=mesInicio; i<mesActual ;i++)
    // {

    // if (((i %2 !=0)&&(i<9))||(i==8))//Calculo 31 dias hasta agosto,
    // {
    // diasVividos = diasVividos+31;
    // }

    // else
    // {
    // if ((i==2)) //So el mes es febrero
    // {
    // if (anoBisiesto == true)
    // {
    // diasVividos =  diasVividos+29;
    // } 
    // else
    // {
    // diasVividos = diasVividos+28;

    // }
    // }
    // else
    // {
    // if ( i % 2 != 0) //Se determina la cantidad de dias de septiembre hasta diciembre, si es impar o par 
    // {
    // diasVividos = diasVividos+30;

    // }
    // else
    // {
    // diasVividos=diasVividos+31;

    // }
    // }
    // }

    // }

    // //Aqui se calculan los meses completos + el dia actual- dia de nacimiento.
    // diasVividos = diasVividos+(diaActual-diaInicial);

    // }

    // }
    // //Se va a mostrar cantidad de alos bisiestos y cantidad de alos normales
    // System.out.println("Usted ha vivido "+anosde365+" años no bisiestos");
    // System.out.println("Usted ha vivido "+anosde366+" años bisiestos");
    // //Se muesta la suma de los dias vividos
    // System.out.println("Los dias usted vivido hasta el "+diaActual+"/"+mesActual+"/"+anoActual+" "+diasVividos+" dias.");
}
