package Utils;

public class ConsolePrinter {
   public static void userManagementMenu(){
       System.out.println(" +--------------------------------------+");
       System.out.println(" |        User Account Management       |");
       System.out.println(" +--------------------------------------+");
       System.out.println(" |  1. Create Account                   |");
       System.out.println(" |  2. Modify Account                   |");
       System.out.println(" |  3. Delete Account                   |");
       System.out.println(" |  4. Display All Accounts             |");
       System.out.println(" | 5. Exit                              |");
       System.out.println(" +--------------------------------------+");
       System.out.print("  ==> Enter your choice: ");
   }

    public static void carbonMenu(){
        System.out.println(" +--------------------------------------+");
        System.out.println(" |     Carbon FingerPrint Management    |");
        System.out.println(" +--------------------------------------+");
        System.out.println(" |  1. Add Carbon Consumption           |");
        System.out.println(" |  2. Get Total Carbon Consumption     |");
        System.out.println(" |  3. Get All Carbon Consumption       |");
        System.out.println(" |  4. Get Carbon Consumption Report    |");
        System.out.println(" |  5. Exit                             |");
        System.out.println(" +--------------------------------------+");
        System.out.print("  ==> Enter your choice: ");
    }

    public static void reportMenu(){
        System.out.println(" +--------------------------------------+");
        System.out.println(" |               Carbon Report          |");
        System.out.println(" +--------------------------------------+");
        System.out.println(" |  1. Daily                            |");
        System.out.println(" |  2. Weekly                           |");
        System.out.println(" |  3. Monthly                          |");
        System.out.println(" |  4. Exit                             |");
        System.out.println(" +--------------------------------------+");
        System.out.print("  ==> Enter your choice: ");
    }

    public static void ConsumptionTypeMenu(){
        System.out.println(" +--------------------------------------+");
        System.out.println(" |            Consumption Type          |");
        System.out.println(" +--------------------------------------+");
        System.out.println(" |  1. TRANSPORT                        |");
        System.out.println(" |  2. HOUSING                          |");
        System.out.println(" |  3. FOOD                             |");
        System.out.println(" |  4. Exit                             |");
        System.out.println(" +--------------------------------------+");
        System.out.print("  ==> Enter your choice: ");
    }

    public static void mainMenu(){
        System.out.println(" +--------------------------------------+");
        System.out.println(" |        Main Menu                     |");
        System.out.println(" +--------------------------------------+");
        System.out.println(" |  1. User Account Management          |");
        System.out.println(" |  2. Carbon Fingerprint               |");
        System.out.println(" |  3. Exit                             |");
        System.out.println(" |  4. Test Functions                   |");
        System.out.println(" +--------------------------------------+");
        System.out.print("  ==> Enter your choice: ");
    }

    public static void printError(String message){
        String redText = "\033[0;31m";
        String resetText = "\033[0m";

        System.out.println("\n" + redText + "--------------------------------------");
        System.out.println( message );
        System.out.println("--------------------------------------" + resetText + "\n" );
    }

    public static void printInfo(String message){
        String yellowText = "\033[0;33m";
        String resetText = "\033[0m";

        System.out.println("\n" + yellowText + "--------------------------------------");
        System.out.println(  message );
        System.out.println("--------------------------------------" + resetText + "\n" );
    }

    public static void printSuccess(String message){
        String greenText = "\033[0;32m";
        String resetText = "\033[0m";
        System.out.println("\n" + greenText + "--------------------------------------");
        System.out.println(" " +  message );
        System.out.println("--------------------------------------" + resetText + "\n" );

    }
}
