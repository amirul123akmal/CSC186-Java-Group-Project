import java.util.ArrayList;
import java.util.Scanner;

public class Bus_Headquarters
{
    public int totalAccumulativePassenger;
    private ArrayList<Bus_Station> listStation = new ArrayList<>();
    private int totalBus = 10;
    private double totalProfit, totalExpenses, totalSales; 

    static Scanner in = new Scanner(System.in);
    static DataCollector db = new DataCollector();
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
        for (Bus_Station a : listStation) 
            System.out.println(a+"");
    }
    public void createBusStation()
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
    }
    public void printAllBus()
    {
        for(Bus_Station a : listStation)
        {
            try {
                t.clearConsole();
                System.out.println("Station Name: " +a.getName());
                System.out.println("Bus Quantity: " +a.getBusAmount());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.print("");
        }
    }
    public void finalize() // act as destructor (~ in c++)
    {
        db.updateDatabaseBeforeEnds(listStation);
    }
}