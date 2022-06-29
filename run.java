import java.io.IOException;
import java.util.Scanner;

public class run
{
    public static void main(String[] args) throws IOException, InterruptedException {
        // objects declaration
        Scanner in = new Scanner(System.in);
        Bus_Headquarters main = new Bus_Headquarters();
        TicketManager customerTicket = new TicketManager(main);
        Tools t = new Tools();

        int menuChoose;

        final String[] menu = {"Admin", "View Stations", "View Bus", "View Tickets", "Generate Tickes", "Exit"}; 
        for(char user = 'y';user != 'n';)
        {   
            t.clearConsole();
            for(int i = 0 ; i < menu.length ; i++)
                System.out.println((i+1) + ". " + menu[i]);
            System.out.println();    
            menuChoose = in.nextInt();
            in.nextLine();
            if(menuChoose == 1) main.admin();
            if(menuChoose == 2) main.printAllSation();
            if(menuChoose == 3) main.printAllBus();
            if(menuChoose == 4) customerTicket.printTicket();
            if(menuChoose == 5) customerTicket.generateTicket();
            if(menuChoose == menu.length )
                break;
        }
        System.out.println("\n\nTHANK YOU");
        main.finalize();
        customerTicket.finalize();
        in.close();
    }
}