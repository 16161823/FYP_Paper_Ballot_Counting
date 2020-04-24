

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class client {

    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private Scanner scanner,in = null;
    private ObjectOutputStream objectOutputStream = null;


    client() throws IOException {
        try {
            this.socket = new Socket("localhost", 59898);
            System.out.println("Client On");

            //Ins
            this.scanner = new Scanner(System.in);
            dataInputStream = new DataInputStream(System.in);

            this.in = new Scanner(socket.getInputStream());

            //Outs
            //this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        String line = "";

    }

    public void sendObject(Object object){
        try {
            objectOutputStream.writeObject(object);
        }
        catch (IOException i){
            System.out.println(i);
        }
    }


    public void closeClient() throws IOException {
    this.objectOutputStream.close();
    this.in.close();
    this.dataInputStream.close();
    this.scanner.close();
    this.socket.close();
    }
}

//while (scanner.hasNextLine()) {
//out.println("Well");
//System.out.println(in.nextLine());