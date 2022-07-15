import java.util.ArrayList;
import java.util.Scanner;

public class Bus_Headquarters
{
    public int totalAccumulativePassenger;
    private ArrayList<Bus_Station> listStation = new ArrayList<>();
    private int totalBus = 10;
    private double totalProfit, totalExpenses, totalSales; 

    Scanner in = new Scanner(System.in);
    DataCollector db = new DataCollector();
    Tools t = new Tools();

    // Constructors
    public Bus_Headquarters()
    {
        totalAccumulativePassenger = 0;
        listStation = db.getStationFromDB();
        /*
            If laptop's ram is full and want to crash the program, 
            java will run finalize() method to save all the stations before halted
            ( Java finalize works as destructor at java1.0 - java9
              this program programmed at java17 )
        */  
        System.runFinalization();  
    }

    // Setters / Getters
    public int getTotalBus()
    {
        return totalBus;
    }
    public double getTotalProfit()
    {
        return totalProfit;
    }
    public void setbus(int a)
    {
        totalBus = a;
    }
    
    // Methods (private)
    private void calculateProfit()
    {
        totalProfit = totalSales - totalExpenses;
    }
    private void createBusStation()
    {
        System.out.print("Station Name: ");
        String name = in.nextLine();
        System.out.print("Station Place: ");
        String place = in.nextLine();
        System.out.println("Station price: RM ");
        double price = in.nextDouble();
        System.out.println("Bus assigned: ");
        int busses = in.nextInt();
        in.nextLine();
        totalBus += busses;
        listStation.add(new Bus_Station(name, place, busses, price));
        db.updateDatabaseBeforeEnds(listStation);
        t.clearConsole();
        printAllSation();
    }
    private void removeBusStation()
    {
        t.clearConsole();
        t.printAllStation(listStation);
        System.out.print("\nStation Name to be remove: ");
        String name = in.nextLine();
        for(Bus_Station a : listStation)
            if(a.getName().equalsIgnoreCase(name))
            {
                listStation.remove(a);
                break;
            }
        db.updateDatabaseBeforeEnds(listStation);
        t.clearConsole();
        printAllSation();

    }

    // Methods(API) - Gives data instead of print it
    public ArrayList<Bus_Station> getAllStation()
    {
        return listStation;
    }

    // Methods (public)
    public void collectPassengerAmount()
    {
        calculateProfit();
    }
    public void printAllSation()
    {
        t.printAllStation(listStation);
        System.out.println("\nPress Enter to continue...");
        in.nextLine();
    }
    public void printAllBus()
    {
        t.clearConsole();
        for(Bus_Station a : listStation)
        {
            try {
                System.out.println("Station Name: " +a.getName());
                System.out.println("Bus Quantity: " +a.getBusAmount() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("");
        }
        System.out.print("Press Enter to continue...");
        in.nextLine();
    }
    public void admin()
    {
        final String menu[] = {"Add Station", "Delete Station", "Exit"};
        int input = 0;
        t.clearConsole();
        System.out.print("Enter password: ");
        String pass = in.nextLine();
        if(!pass.equalsIgnoreCase("ami nik muq az"))
            return ; // return to main
        for(;;)
        {
            for(int i = 1 ; i <= menu.length ; i++)
                System.out.println(i + ". "+ menu[i-1]);
            System.out.println();
            input = in.nextInt();
            in.nextLine();
            switch(input)
            {
                case 1:
                    createBusStation();
                    break;
                case 2:
                    removeBusStation();
                    break;
                default:
                    return; // security
            }
        }
    }
    public void finalize() // act as destructor (~ in c++)
    {
        db.updateDatabaseBeforeEnds(listStation);
    }
}