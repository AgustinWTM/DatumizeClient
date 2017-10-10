import datumizeBussines.consoleManager.ConsoleManager;
import datumizeBussines.interf.WorkOnClient;
import datumizerServer.accesResouces.ServerCaller;

import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.initConsoleDialog();
        System.out.println("Fin del proceso");
    }
}