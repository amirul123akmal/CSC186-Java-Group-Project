public class Tickets extends Customer 
{
    String stationDepart, stationArrival;
    double price;

    public Tickets(String name, String ic, int age)
    {
        // For new tickets
        super(name, ic, age);
    }    
    public Tickets(String name, String ic, int age, String stationDepart, String stationArrival, double price)
    {
        // For tickets from DB
        super(name, ic, age);
        this.stationArrival = stationArrival;
        this.stationDepart = stationDepart;
        this.price = price;
    }
    public Tickets(Tickets copy)
    {
        // For tickets from DB
        super(copy.getName(), copy.getIc(), copy.getAge());
        this.stationArrival = copy.getStationArrival();
        this.stationDepart = copy.getStationDepart();
        this.price = copy.getPrice();
    }
    
    // Setters Getters
    public String getStationDepart() {
        return stationDepart;
    }
    public void setStationDepart(String stationDepart) {
        this.stationDepart = stationDepart;
    }
    public String getStationArrival() {
        return stationArrival;
    }
    public void setStationArrival(String stationArrival) {
        this.stationArrival = stationArrival;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Tickets [price=" + price + ", stationArrival=" + stationArrival + ", stationDepart=" + stationDepart
                + "]";
    }
}
