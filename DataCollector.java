import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataCollector {

    private ArrayList<Bus_Station> list = new ArrayList<>();

    public DataCollector()
    {
        try {
            File file = new File("Database/stationList.txt");
            Scanner data = new Scanner(file);
            
            String line, name, place, a[], attr;
            double price;
            int bus, passengers;

            line = name = place = attr = "";
            price = bus = passengers = 0;

            while(data.hasNextLine())
            {
                line = data.nextLine();
                a = line.split(" ");
                attr = a[0];
                // for(String i : a)
                //     System.out.print(i);
                if(attr.equals("name")) 
                {
                    name = "";
                    for(int i = 2 ; i < a.length ; i++)
                        name += a[i] + (i != a.length-1 ? " " :  "");
                    continue;
                }
                else if(attr.equals("place")) 
                {
                    place = "";
                    for(int i = 2 ; i < a.length ; i++)
                        place += a[i] + (i != a.length-1 ? " " :  "");
                    continue;
                }
                else if(attr.equals("price")) 
                {
                    price = Double.parseDouble(a[2]);
                    continue;
                }
                else if(attr.equals("bus")) 
                {
                    bus = Integer.parseInt(a[2]);
                    continue;
                }
                else if(attr.equals("passenger")) 
                {
                    passengers = Integer.parseInt(a[2]);
                    list.add(new Bus_Station(name, place, bus, price, passengers));
                    continue;
                }
            }
            data.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Bus_Station> getStationFromDB()
    {
        return list;
    }
    public void updateDatabaseBeforeEnds(ArrayList<Bus_Station> newData)
    {
        FileWriter file;
        try {
            file = new FileWriter("Database/stationList.txt");
            PrintWriter data = new PrintWriter(file);
            
            for(Bus_Station a : newData)
            {
                data.println("name = " + a.getName());
                data.println("place = " + a.getPlace());
                data.println("price = " + a.getPrice());
                data.println("bus = " + a.getBusAmount());
                data.println("passenger = " + a.getTicket()+'\n');
            }

            data.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
