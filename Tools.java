import java.io.IOException;

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
        // String fulltext = "";
        
    }
}
