import java.util.ArrayList;
import java.util.Scanner;

public class TicketManager {
    /*
        - must use this (Bus_Headquarters HQ) at other class
        - Class as parameter will pass by reference
        - Any changes made to the data will be recognize to all classes
    */ 
    Bus_Headquarters HQ; 
    ArrayList<Tickets> ticketList = new ArrayList<>();
    TicketCollector ticketDB = new TicketCollector();
    Scanner in = new Scanner(System.in);
    Tools t = new Tools();

    /*
     * Station Name = 12 
     * Location = 8
    */
    int maxStation = 12, maxPlace = 8;
    
    // Constructor
    public TicketManager(Bus_Headquarters HQ)
    {
        this.HQ = HQ;
        ticketList = ticketDB.getTicketsFromDB();
    }

    // Public Method
    public void generateTicket()
    {
        String name, ic, stationDepart, stationArrival;
        double priceA = 0, priceB = 0;
        stationArrival = stationDepart = "";
        t.clearConsole();
        boolean enable = true;
        t.printAllStation(HQ.getAllStation());
        System.out.print("\n\nName: ");
        name = in.nextLine();
        System.out.print("IC: ");
        ic = in.nextLine();
        System.out.println("Enter your age: ");
        int age = in.nextInt();
        in.nextLine();
        for(;enable;)
        {
            System.out.print("Choose station Depart: ");
            stationDepart = in.nextLine();
            stationDepart += " ";
            for(Bus_Station a : HQ.getAllStation())
                if(a.getName().equalsIgnoreCase(stationDepart))
                {
                    enable = false;
                    priceA = a.getPrice();
                }
            if(enable)
                System.out.println("Station not found\n");
        }
        enable = true;
        for(;enable;)
        {
            System.out.print("Choose station Arrival: ");
            stationArrival = in.nextLine();
            stationArrival += " ";
            for(Bus_Station a : HQ.getAllStation())
            {
                if(a.getName().equalsIgnoreCase(stationArrival))
                {
                    enable = false;
                    priceB = a.getPrice();
                }
            }
            if(enable)
                System.out.println("Station not found");
        }
        ticketList.add(new Tickets(name, ic, age, stationDepart, stationArrival, (priceA + priceB)));
    }
    public void printTicket()
    {
        for(Tickets a : ticketList)
            System.out.println(a +"");
        System.out.println("\nPress Enter to continue...");
        in.nextLine();
    }
    public void finalize()
    {
        ticketDB.storeTickets(ticketList);
    }
}
