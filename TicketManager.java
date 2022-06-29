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

    // Constructor
    public TicketManager(Bus_Headquarters HQ)
    {
        this.HQ = HQ;
        ticketList = ticketDB.getTicketsFromDB();
    }

    // Private Method
    private void beautyTicket(Tickets data)
    {
        final int ticketWidth = 40;
        t.skip(ticketWidth, '+');
        System.out.print("\n| Name: " + data.getName());
        t.skip(ticketWidth - data.getName().length() - 9);
        System.out.print("|\n| IC : " + data.getIc());
        t.skip(ticketWidth - data.getIc().length() - 8);
        System.out.print("|\n| Station Depart : " + data.getStationDepart());
        t.skip(ticketWidth - data.getStationDepart().length() - 20);
        System.out.print("|\n| Station Arrival : " + data.getStationArrival());
        t.skip(ticketWidth - data.getStationArrival().length() - 21);
        System.out.print("|\n| Price: RM " + String.format("%.2f",data.getPrice()));
        t.skip(ticketWidth - String.format("%.2f", data.getPrice()).length() - 13);
        System.out.println("|");
        t.skip(ticketWidth, '+');
        System.out.println("\n");
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
        ticketDB.storeTickets(ticketList);
    }
    public void printTicket()
    {
        t.clearConsole();
        System.out.print("Enter your IC: ");
        String ic = in.nextLine();
        for(Tickets a : ticketList)
            if(a.getIc().equals(ic)) // 1 Person may have multiple tickets
                beautyTicket(a);
        System.out.println("\n\n\nPress Enter to continue...");
        in.nextLine();
    }
    public void finalize()
    {
        ticketDB.storeTickets(ticketList);
    }
}
