import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 59898)) {
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

           //while (scanner.hasNextLine()) {
            out.println("Well");
            //System.out.println(in.nextLine());
            //}
        }
    }
}
