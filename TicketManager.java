import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class TicketManager {
    Bus_Headquarters HQ;
    ArrayList<Tickets> ticketList = new ArrayList<>();
    TicketCollector ticketDB = new TicketCollector();
    Scanner in = new Scanner(System.in);
    Tools t = new Tools();

    int maxStation = 0, maxPlace = 0;
    
    // Constructor
    public TicketManager(Bus_Headquarters HQ)
    {
        this.HQ = HQ;
    }

    // Private method
    private void getLenght()
    {
        for(Bus_Station a : HQ.getAllStation())
        {
            if(a.getName().length() > maxStation)
                maxStation = a.getName().length();
            if(a.getPlace().length() > maxPlace)
                maxPlace = a.getPlace().length();
        }

    }
    private void printAllStation()
    {
        getLenght();
        for(int i = 0 ; i < maxPlace + maxStation + 4 ; i++ )
            System.out.print("+");
        System.out.println();
        
    }

    // Public Method
    public void generateTicket()
    {
        t.clearConsole();
        System.out.print("Name: ");
    }
    public void finalize()
    {
        ticketDB.storeTickets(ticketList);
    }
}
