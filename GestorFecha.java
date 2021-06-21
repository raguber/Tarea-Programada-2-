
import java.util.Scanner;
import java.util.InputMismatchException;

public class GestorFecha
{

    public static void main (String args[])
    {

        int anoNacimiento = 0;//Se definen las variables para guardar la informacion de la fecha de nacimiento
        int diaNacimiento = 0;
        int mesNacimiento = 0;

        int diaActual=0;//Se definen las variables para guardar la informacion de la fecha actual
        int mesActual=0;
        int anoActual=0;

        int diasVividos=0;//Los dias vividos se iran sumando y guardando en esta variable
        int anosde365 = 0;//Se guardara aqui la cantidad de años que son bisiestos
        int anosde366 = 0;// y los años normales.

        boolean anoBisiesto = false;//Se usara posteriormente para saber si el año actual es bisiesto o no (importante para calcular dias de febrero)
        boolean nacimientoBisiesto = false;//Se usara posteriormente para saber si el año de nacimiento es bisiesto o no (importante para calcular dias de febrero)
        int mesInicio = 0;

        //Aqui se empezara a pedir evitando errores del usuario, la fecha de nacimiento y la fecha actual (dias/meses/años.
        while((diaNacimiento<1)||(diaNacimiento>31))
        {
            boolean fallo = true;
            while (fallo)
            {

                try
                {
                    fallo= false;
                    System.out.println("Digite el dia de nacimiento en numeros");
                    Scanner entradaDiaNacimiento = new Scanner(System.in);
                    diaNacimiento = entradaDiaNacimiento.nextInt();
                }
                catch(java.util.InputMismatchException ioe)
                {
                    System.out.println("Error caracter no valido, o el dia de nacimiento es menor que 1 o mayor que 31");
                    fallo = true;

                }

            }
        }   

        while((mesNacimiento<1)||(mesNacimiento>12))
        {
            boolean fallo = true;
            while (fallo)
            {

                try
                {
                    fallo= false;
                    System.out.println("Digite el mes de nacimiento en numeros");
                    Scanner entradaMesNacimiento = new Scanner(System.in);
                    mesNacimiento = entradaMesNacimiento.nextInt();
                }
                catch(java.util.InputMismatchException ioe)
                {
                    System.out.println("Error caracter no valido, o el mes de nacimiento es menor que 1 o mayor que 12");
                    fallo = true;

                }

            }
        }   

        boolean falloNacimiento = true;
        while (falloNacimiento)
        {

            try
            {
                falloNacimiento= false;
                System.out.println("Digite el año de nacimiento en numeros");
                Scanner entradaAnoNacimiento = new Scanner(System.in);
                anoNacimiento = entradaAnoNacimiento.nextInt();
            }
            catch(java.util.InputMismatchException ioe)
            {
                System.out.println("Error caracter no valido, se necesita un numero entero");
                falloNacimiento = true;

            }

        }

        while((diaActual<1)||(diaActual>31))
        {
            boolean fallo = true;
            while (fallo)
            {

                try
                {
                    fallo= false;
                    System.out.println("Digite el dia actual en numeros");
                    Scanner entradaDiaActual = new Scanner(System.in);
                    diaActual = entradaDiaActual.nextInt();
                }
                catch(java.util.InputMismatchException ioe)
                {
                    System.out.println("Error caracter no valido, o el dia de actual es menor que 1 o mayor que 31");
                    fallo = true;

                }

            }
        }

        while((mesActual<1)||(mesActual>12))
        {
            boolean fallo =false;
            try
            {
                fallo= false;
                System.out.println("Digite el mes actual en numeros");
                Scanner entradaMesActual = new Scanner(System.in);
                mesActual = entradaMesActual.nextInt();
            }
            catch(java.util.InputMismatchException ioe)
            {
                System.out.println("Error caracter no valido, o el mes actual es menor que 1 o mayor que 12");
                fallo = true;

            }

        }

        while((anoActual<anoNacimiento))
        {
            boolean fallo =false;
            try
            {
                fallo= false;
                System.out.println("Digite el año actual en numeros");
                Scanner entradaAnoActual = new Scanner(System.in);
                anoActual = entradaAnoActual.nextInt();
            }
            catch(java.util.InputMismatchException ioe)
            {
                System.out.println("Error caracter no valido, o el año actual es menor que el año de nacimiento");
                fallo = true;

            }

        }

        //Aqui se determina si el año de nacimiento es bisiesto o no
        //Para eso se toma el año y si es multiplo de 4 y a su vez de 400 o de 100, entonces el año de nacimiento de la persona fue bisieseto (en caso de que se cumpliera);
        if ((anoNacimiento % 4 ==0)) //Calcula si el año de nacimiento es biciesto
        {
            if ((anoNacimiento % 400 ==0)||(anoNacimiento % 100 !=0))
            {
                nacimientoBisiesto = true;

            }
            else
            {
                nacimientoBisiesto = false;
            }

        }
        else
        {
            nacimientoBisiesto = false;        
        }

        //Aqui se determina si el año actual es bisiesto o no
        //Para eso se toma el año y si es multiplo de 4 y a su vez de 400 o de 100, entonces el año de nacimiento de la persona fue bisieseto (en caso de que se cumpliera);
        if ((anoActual % 4 ==0)) //Calcula si el año actual es biciesto
        {
            if ((anoActual % 400 ==0)||(anoActual % 100 !=0))
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

        //Calculo dias hasta primer terminar mes
        //Aqui se empiezan a calcular los dias
        //El calculo de dias,Se separa en dos casos, Si el año de nacimiento es igual al año actual (por ejemplo un bebe) o si el año es diferente

        if ((anoActual!=anoNacimiento))//En caso de si el año es diferente, se va a hacer lo siguiente:
        {
            //Para la suma con el siguiente if, se compara si el mes es de 31 o de 30 dias, o en el caso particular de febrero si tiene 29 o 28 dias (Segun si el año de nacimiento fue bisiesto.
            //Para calcular eso se hace mediante una simple resta: (la cantidad de dias del mes) - (el dia de nacimiento)

            //Lo que se calcularia, serian los dias que vivio la persona en el mes en el que nacio, ya que no necesariamente la persona vivio todos los dias del mes en el que nacio.,+
            //Por lo cual, se calculan esos dias, y despues se van sumando los dias de los meses en forma completa.

            //La resta anterior se va guardando en una variable, que en el trascurso del desarollo del programa va sumando dias.

            if (((mesNacimiento %2 !=0)&&(mesNacimiento<9))||(mesNacimiento==8))//Calculo 31 dias exepto agosto (8%2=0) pero tiene 31 dias
            {

                diasVividos =31-diaNacimiento;

            }
            else
            {
                if ((mesNacimiento==2))
                {
                    if (anoBisiesto == true)
                    {

                        diasVividos =  29-diaNacimiento;
                    }
                    else
                    {
                        diasVividos = 28-diaNacimiento;
                    }
                }
                else//Si se cumple que el mes es bisiesto
                {
                    diasVividos = 30-diaNacimiento;

                }
            }

            //Para el siguiente for se calculan los meses que la persona vivio completos, hasta finalizar el año  
            for (int i = mesNacimiento+1; i <= 12; i++)
            {
                //Aqui se calcula si el mes tiene 30 o 31 dias, o en el caso particular de febrero si tiene 28 o 29 dias, en caso de ser bisiesto
                if((i %2 !=0)&&(i<9)||(i==8))// Si los mese son impares menores que nueve o es agos los dias tienen 31 dias (Enero, marzo,mayo....)
                {
                    diasVividos =diasVividos+31;
                }
                else
                {
                    if (i==2)//Si el mes es febrero analiza si el año tiene 29 o 28 dias
                    {

                        if (nacimientoBisiesto == true)
                        {

                            diasVividos = diasVividos+29;
                        }
                        else
                        {
                            diasVividos = diasVividos +28;

                        }
                    }
                    else
                    {
                        if ( i%2!=0)//Si el mes es impar igual o mayor que 9 los dias van a tener 30 dias (Setiembre, noviembre);
                        {

                            diasVividos =diasVividos+30;
                        }
                        else
                        {
                            diasVividos = diasVividos+31;//Para octubre y Diciembre

                        }
                    }
                }
            } 

            //Ahora se calculan los años que la persona ha vivido completamente,
            //Se calcula si el año es bisiesto o no, entonces por cada año vivido se va sumando en una unidad si el año es bisiesto
            //por ejemplo si una persona nacio en el año 1999, en el año 2000 vivio 366
            //en el programa se va calcular la cantidad de años bisiestoa y la cantidad de años no bisiesto para despues multiplicar por la cantidad de dias

            for (int i=anoNacimiento+1;i<anoActual;i++)
            {

                if ( i % 4==0)
                {
                    if((i % 400 ==0)||( i % 100 !=0 ))
                    {
                        anosde366 = anosde366+1;                 
                    }
                }
                else
                {
                    anosde365 =anosde365+1;
                }
            }

            //PAra el calculo de los dias del año actual se van calculando los meses completos, despues los dias vividos del mes actual.

            for (int i=1; i<mesActual ;i++)//Si la personaha vivido hasta  cierto mes se calcula hasta cierto mes se calcula desde enero, hasta llegar al mes anterior al mes actual

            {
                //Aqui se ve si los meses vividos del año actual tieen 31 o 30 dias, o en el casode febrero 28 o 29
                if (((i %2 !=0)&&(i<9))||(i==8)) //Para Meses de 31 dias hasta agosto"
                {

                    diasVividos = diasVividos+31;

                }
                else
                {
                    if ((i==2))//Para febrero
                    {

                        if (anoBisiesto == true)
                        {

                            diasVividos =  diasVividos+29;

                        }
                        else
                        {
                            diasVividos = diasVividos+28;

                        }
                    }
                    else//Para los meses dsde septiembre hasta Diciembre, se analiza si es mes impar o par
                    {
                        if ( i % 2 != 0)
                        {
                            diasVividos = diasVividos+31;

                        }
                        else
                        {
                            diasVividos=diasVividos+30;

                        }
                    }
                }

            }
            //Aqui se calculan los dias vividos que faltan
            //Ademas para sumar los dias vividos del mes actual, simplemente se suma tambien el dia actual.
            //La explicacion de la suma desde el nacimiento hasta la gecha actual es la siguiente
            //diasVividos = diasVividos del mes de nacimiento * dias vividos hasta el 31 de diciembre del año de nacimiento, los años completamente vividos*cantidad de dias de cada año)
            //+los meses enteros vividos del año actual, y los dias que del mes actual.
            diasVividos=diasVividos+diaActual+((anosde365*365)+(anosde366*366));
        }
        else//La persona tiene menos de 1 año
        {
            //Si la persona nacio en el mismo año y nacio en el mes actual (tiene menos de 1 mes), con solo calcular el dia actual -  dia nacimiento, se dan los dias vividos
            if (mesActual==mesNacimiento)
            {

                diasVividos = diaActual-diaNacimiento;

            }
            else
            {//Si la persona tiene mas de 1 mes pero menos de un año
                //El mes de inicio sera el mes de nacimiento, pues una persona pudo haber nacido en un mes distinto de enero, por lo cual puede tener 1 mes y 10 dias
                mesInicio = mesNacimiento;
                //Por ejemplo mes nacimiento = 3 y mes actual = 5, se calcularan el mes 3 y 4 completos, y se sumara el dia actual
                for (int i=mesInicio; i<mesActual ;i++)
                {

                    if (((i %2 !=0)&&(i<9))||(i==8))//Calculo 31 dias hasta agosto,
                    {
                        diasVividos = diasVividos+31;
                    }

                    else
                    {
                        if ((i==2)) //So el mes es febrero
                        {
                            if (anoBisiesto == true)
                            {
                                diasVividos =  diasVividos+29;
                            } 
                            else
                            {
                                diasVividos = diasVividos+28;

                            }
                        }
                        else
                        {
                            if ( i % 2 != 0) //Se determina la cantidad de dias de septiembre hasta diciembre, si es impar o par 
                            {
                                diasVividos = diasVividos+30;

                            }
                            else
                            {
                                diasVividos=diasVividos+31;

                            }
                        }
                    }

                }
                
                //Aqui se calculan los meses completos + el dia actual- dia de nacimiento.
                diasVividos = diasVividos+(diaActual-diaNacimiento);

            }

        }
        //Se va a mostrar cantidad de alos bisiestos y cantidad de alos normales
        System.out.println("Usted ha vivido "+anosde365+" años no bisiestos");
        System.out.println("Usted ha vivido "+anosde366+" años bisiestos");
        //Se muesta la suma de los dias vividos
        System.out.println("Los dias usted vivido hasta el "+diaActual+"/"+mesActual+"/"+anoActual+" "+diasVividos+" dias.");
    }
}