import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.WeakHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;

public class Bus_Headquarters
{
    public int totalAccumulativePassenger;
    private ArrayList<Bus_Station> listStation = new ArrayList<>();
    private int totalBus = 10;
    private double totalProfit, totalExpenses, totalSales; 

    Scanner in = new Scanner(System.in);
    DataCollector db = new DataCollector();
    Tools t = new Tools();
    JFrame frame;
    JPanel window;

    // Constructors
    public Bus_Headquarters(JFrame frameFromMain, JPanel jpanelFromMain)
    {
        frame = frameFromMain;
        window = jpanelFromMain;
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
            if(a.getName().equalsIgnoreCase(name + " "))
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
        JPanel bus = new JPanel();
        JLabel label = new JLabel("LIST ALL BUS AVAILABLE");
        JTable table  =new JTable();
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            frame.remove(bus);
            t.frameOri();
        });
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addColumn("Station Name");
        model.addColumn("AMOUNT OF BUS AVAILABLE");
        for(Bus_Station a : listStation)
            model.addRow(new Object[]{a.getName(), Integer.toString(a.getBusAmount())});
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table);
        bus.add(label);
        bus.add(scroll);
        bus.add(exit);
        frame.remove(window);
        frame.add(bus);
        frame.setSize(frame.getSize().width - 100, frame.getSize().height + 200);
        t.frameUpdate();
    }
    public void admin()
    {
        String password = JOptionPane.showInputDialog(frame,"Enter Password: " );
        if(!password.equalsIgnoreCase("ami nik muq az"))
            return ;
        frame.remove(window);
        JPanel admin = new JPanel(new GridLayout(2, 3));
        admin.add(new JLabel());
        JLabel label = new JLabel("ADMINISTRATION SECTION");
        admin.add(label);
        admin.add(new JLabel());
        JButton b1 = new JButton("Add Station");
        b1.addActionListener(e -> createBusStation());
        b1.setSize(20, 30);
        admin.add(b1);
        JButton b2 = new JButton("Remove Station");
        b2.addActionListener(e -> removeBusStation());
        admin.add(b2);
        JButton b3 = new JButton("Exit");
        b3.addActionListener(e -> {
            frame.remove(admin);
            t.frameOri();
        });

        admin.add(b3);
        frame.add(admin);
        t.frameUpdate();
    }
    public void finalize() // act as destructor (~ in c++)
    {
        db.updateDatabaseBeforeEnds(listStation);
    }
}