package datumizeBussines.consoleManager;

import datumizeBussines.interf.WorkOnClient;
import datumizeBussines.workOn.WorkOnClientAsyncImplementation;
import datumizeBussines.workOn.WorkOnClientSyncImplementation;

import java.util.Scanner;

public class ConsoleManager {

    Scanner scanner = new Scanner(System.in);
    WorkOnClient workOnClient = null;

    public void initConsoleDialog(){
        System.out.println("Hola, este es el Main");
        this.bucleTypeconfig();
    }

    private void bucleTypeconfig(){
        String valZero = this.readerStandart("El tipo de configuracion, B para salir, S Sync o A Async: ");
        if(valZero.equalsIgnoreCase("S")){
            workOnClient = new WorkOnClientSyncImplementation();
            this.bucleRead();
        }else if (valZero.equalsIgnoreCase("A")){
            workOnClient = new WorkOnClientAsyncImplementation();
            this.bucleRead();
        }else if (valZero.equalsIgnoreCase("B")){
            scanner.close();
            System.out.println("Fin de Uso de Main");
        }else{
            this.bucleTypeconfig();
        }
    }

    private void bucleRead(){
        String valOption = this.readerStandart("Pulsa R para leer el valor en servidor, B para ir al menu anterior o introduce un numero para actualizarlo: ");
        if(valOption.equalsIgnoreCase("R")){
            this.serverResponseShow(workOnClient.getVal());
            this.bucleRead();
        }else if (valOption.matches("\\d+")){
            this.serverResponseShow(workOnClient.addVal(Long.valueOf(valOption)));
            this.bucleRead();
        }else if (valOption.equalsIgnoreCase("B")){
            this.bucleTypeconfig();
        }else{
            this.bucleRead();
        }
    }


    private String readerStandart(String textQuestion){
        System.out.println(textQuestion);
        String valResponse = scanner.next();
        System.out.println("valor recivido por consola: "+valResponse);
        return valResponse;
    }

    public void serverResponseShow(String resp){
        System.out.println("valor actual: "+ resp);
    }
}
