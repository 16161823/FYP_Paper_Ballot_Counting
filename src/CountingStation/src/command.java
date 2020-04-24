import java.io.IOException;
import java.util.ArrayList;

public class command {

    static ArrayList<ArrayList<Integer>> ballots = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> ballot = new ArrayList<Integer>();
    static Integer numberOfVoters = 20;
    static Integer numberOfCandidates = 8;

    public static void main(String[] args) throws IOException {


        STV stv = new STV();
        server server = new server();
        server.closeServer();




        stv.electionRun(ballots);



        //Client connects to server and asks for settings.
        //Server then waits while Client intakes ballots.
        //Once this is done Client will transfer data to the server.
        //
    }
}
