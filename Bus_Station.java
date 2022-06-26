import java.util.Random;

public class Bus_Station {
    String name, place;
    int amountBusProvided, ticketCount;
    double price ;
    private double Sales = 0, expenses = new Random().nextInt(6) + 10;

    // Constructors
    public Bus_Station()
    {
        place = name = "";
        price = Sales = ticketCount = amountBusProvided = 0;
    }
    public Bus_Station(String StationName, String StationLocation, double stationPrice)
    {
        name = StationName;
        place = StationLocation;
    }
    public Bus_Station(String StationName, String StationLocation, int providedBus, double stationPrice)
    {
        name = StationName;
        place = StationLocation;
        amountBusProvided = providedBus;
    }
    public Bus_Station(String StationName, String StationLocation, int providedBus, double stationPrice, int total )
    {
        name = StationName;
        place = StationLocation;
        amountBusProvided = providedBus;
        ticketCount = total;
    }
    public Bus_Station(Bus_Station otherStation)
    {
        name = otherStation.name;
        place = otherStation.place;
        amountBusProvided = otherStation.amountBusProvided;
        ticketCount = otherStation.ticketCount;
        // sales always different for each station
    }

    // Setters / Getters
    public String getName() {return name;};
    public String getPlace() {return place;};
    public double getSales(){return Sales;}
    public double getExpenses(){return expenses;}
    public double getPrice(){return price;}
    public int getBusAmount(){return amountBusProvided;}
    public int getTicket(){return ticketCount;}

    // Methods
    public void changeBusAmount(int amount, byte target)
    {
        if(target > 0)
        {
            amountBusProvided += amount;
            return;
        }
        amountBusProvided -= amount;
    }
    public String toString()
    {
        return String.format("Station Name: %s\nStation Location: %s\n",name, place );
    }

}
