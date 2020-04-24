import java.io.IOException;
import java.util.ArrayList;

public class localCommand {

    ArrayList<ArrayList<Integer>> ballots = new ArrayList<ArrayList<Integer>>();

    public  localCommand() throws IOException {
        client client = new client();
        testMaker testMaker = new testMaker();
        ballots = testMaker.testMakeArray();

        for (int i = 0; i < testMaker.getTestMakerSize();i++) {
            client.sendObject(ballots.get(i));
        }
        /*
        for (int i = 0; i < 20; i++)
        {
            client.out.println(ballots.get(i));
            System.out.println(ballots.get(i));
        }
        */

        //client.out.print(testMaker.getBallots());

        client.closeClient();
    }

    public static void main(String[]args) throws IOException {
        localCommand localCommand = new localCommand();
    }
}
