package PollingStation;

import PollingStation.*;

import java.io.IOException;
import java.util.ArrayList;

public class localCommand {

    ArrayList<ArrayList<Integer>> ballots = new ArrayList<ArrayList<Integer>>();

    public  localCommand() throws IOException {

        //readerTess4J readerTess4J = new readerTess4J();


        client client = new client();
        testMaker testMaker = new testMaker();
        ballots = testMaker.testMakeArray();
        System.out.println("Starting data transfer: ");
        for (int i = 0; i < testMaker.getTestMakerSize();i++) {
            client.sendObject(ballots.get(i));

        }
        System.out.println("TestMaker Test Size is : " + testMaker.getTestMakerSize());
        System.out.println("Ballot Data has been sent");



        //client.out.print(testMaker.getBallots());

        client.closeClient();


    }

    public static void main(String[]args) throws IOException {
        localCommand localCommand = new localCommand();
    }
}
