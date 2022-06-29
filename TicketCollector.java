import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketCollector {
    File folder = new File("Tickets/");
    File[] files = folder.listFiles();
    ArrayList<Tickets> TicketData = new ArrayList<>();

    public TicketCollector()
    {
        String line, attr, name, ic, stationArrival, stationDepart;
        String[] array;
        double price = 0;
        int age = 0;
        for(File a : files)
        {
            line = attr = name = ic = stationArrival = stationDepart = "";
            try {
                Scanner data = new Scanner(a);
                while(data.hasNextLine())
                {
                    line = data.nextLine();
                    array = line.split(" ");
                    attr = array[0];
                    if(attr.equals("name")) 
                    {
                        name = "";
                        for(int i = 2 ; i < array.length ; i++)
                            name += array[i] + " ";
                        continue;
                    }
                    else if(attr.equals("ic")) 
                    {
                        ic = array[2];
                        continue;
                    }
                    else if(attr.equals("age")) 
                    {
                        age = Integer.parseInt(array[2]);
                        continue;
                    }
                    else if(attr.equals("price")) 
                    {
                        price = Double.parseDouble(array[2]);
                        continue;
                    }
                    else if(attr.equals("stationDepart")) 
                    {
                        stationDepart = "";
                        for(int i = 2 ; i < array.length ; i++)
                            stationDepart += array[i] + " ";
                        continue;
                    }
                    else if(attr.equals("stationArrival")) 
                    {
                        stationArrival = "";
                        for(int i = 2 ; i < array.length ; i++)
                            stationArrival += array[i] + " ";
                        TicketData.add(new Tickets(name, ic, age, stationDepart, stationArrival, price));
                        continue;
                    }
                }
                data.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Tickets> getTicketsFromDB()
    {
        return TicketData;
    }

    public void storeTickets(ArrayList<Tickets> newData)
    {
        int counter = 1;
        for(Tickets data : newData)
        {
            try {
                FileWriter file = new FileWriter("Tickets/"+Integer.toString(counter++)+".txt");
                PrintWriter writeFile = new PrintWriter(file);
                writeFile.println("name = " + data.getName());
                writeFile.println("ic = " + data.getIc());
                writeFile.println("age = " + data.getAge());
                writeFile.println("price = " + data.getPrice());
                writeFile.println("stationDepart = " + data.getStationDepart());
                writeFile.println("stationArrival = " + data.getStationArrival());
                writeFile.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
