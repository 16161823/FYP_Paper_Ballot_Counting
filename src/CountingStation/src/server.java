import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class server {

    private Socket socket = null;
    private ServerSocket serverSocket = null;

    private DataInputStream in = null;

    private ObjectInputStream objectInputStream = null;
    private int port = 59898;

    Object object = null;

    public server() {
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("The Counting Station server is running...");

            System.out.println("Waiting for Client");

            socket = serverSocket.accept();
            System.out.println("Client accepted");

            //in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            String line = "";
        }
        catch (IOException i)
        {
            System.out.println(i);
        }
        try {
            for(int i = 0;i < 100; i++) {

                object = objectInputStream.readObject();
                //command.ballot = (ArrayList<Integer>) object;
                command.ballots.add(((ArrayList<Integer>) object));
            }
        }
        catch (ClassNotFoundException e){
            System.out.println("ClassNotFound");
            e.printStackTrace();
        }
        catch (IOException i){
            System.out.println("trouble Reading Object from Socket.");
            i.printStackTrace();
        }
        //System.out.println(command.ballot);


    }



    private static class Capitalizer implements Runnable {
        private Socket socket;

        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                while (in.hasNextLine()) {

                    System.out.println(in.nextLine());
                    String[] temp = in.nextLine().split(",");

                    command.ballot.add(Integer.parseInt(temp[2]));
                    command.ballot.add(Integer.parseInt(temp[1]));
                    command.ballot.add(Integer.parseInt(temp[0]));

                    command.ballots.add(command.ballot);

                    command.ballot.remove(0);
                    command.ballot.remove(0);
                    command.ballot.remove(0);



                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }

    public void closeServer() throws IOException {
        objectInputStream.close();
        serverSocket.close();
        System.out.println("Server closed");
    }

/*
    private static class Capitalizer implements Runnable {
        private Socket socket;

        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connected: " + socket);
            try {
                //To read Data
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //To send data
                PrintStream printStream = new PrintStream(socket.getOutputStream(), true);
                while (bufferedReader.hasNextLine()) {
                    System.out.println(in.nextLine().toUpperCase());
                }
            } catch (Exception e) {
                System.out.println("Error:" + socket);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }

 */
}
