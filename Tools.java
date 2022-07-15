import java.io.IOException;
import java.util.ArrayList;

public class Tools {
    public void clearConsole()
    {
        // Clearing consoles
        // Only support Windows
        // Recommended to have ANSI ESC supported (Powershell, cmd, VSCode's terminal)
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printC(String text, boolean nextline, String color)
    {
        /*
         * - make colored text here
         * - but it is dangerous
         * - Console that is not support ANSI ESC,
         *   will look VERY ugly
         */
        // String fulltext = "";
        
    }

    private int[] getLenght(ArrayList<Bus_Station> data)
    {
        int[] maxes = {0, 0};
        for(Bus_Station a : data)
        {
            if(a.getName().length() > maxes[0])
                maxes[0] = a.getName().length();
            if(a.getPlace().length() > maxes[1])
                maxes[1] = a.getPlace().length();
        }
        return maxes;
    }
    public void skip(int i)
    {
        for(int a = 0 ; a < i ; a++)
            System.out.print(" ");
    }
    public void skip(int i, char a)
    {
        for(int b = 0 ; b < i; b++)
            System.out.print(a);
    }
    public void printAllStation(ArrayList<Bus_Station> data)
    {
        int [] max = getLenght(data);
        skip(max[1] + max[0] + 5, '+');
        System.out.print("\n| Station Name");
        skip(max[0] - 12);
        System.out.print("| Location");
        skip(max[1] - 8);
        System.out.println("|");
        skip(max[1] + max[0] + 5, '+');
        System.out.println();
        for(Bus_Station bus : data)
        {
            System.out.print("| " + bus.getName());
            skip(max[0] - bus.getName().length());
            System.out.print("| " + bus.getPlace());
            skip(max[1] - bus.getPlace().length());
            System.out.println("| ");
        }
        skip(max[1] + max[0] + 5, '+');
    }
}
