package PollingStation;
import java.util.ArrayList;
import java.util.Random;


public class testMaker {

    ArrayList<ArrayList<Integer>> ballots = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> ballot = new ArrayList<Integer>();
    int candidates = 8;
    Random random = new Random();
    private int testMakerSize = 100000; //Has to be manually inputted in CountingStation Server.
    Integer a = null;
    Integer b = null;
    Integer c = null;

    public ArrayList<ArrayList<Integer>> testMakeArray(){

        for(int i = 0;i < testMakerSize;i++){

            //a = random.nextInt(candidates);
            b = random.nextInt(candidates);
            c = random.nextInt(candidates);
            Integer x = random.nextInt(100);
            if (x >= 50){
                a = 2;

            }
            else if (x < 50 && x >= 30)
            {
                a = 2;
            }
            else if (x < 30 && x > 10){
                a = 3;
            }
            else
            {
                a = 5;
            }

            //a = random.nextInt(candidates+1);
            //b = random.nextInt(candidates+1);
            //c = random.nextInt(candidates+1);


            this.ballot = new ArrayList<Integer>();

            this.ballot.add(c);
            this.ballot.add(b);
            this.ballot.add(a);

            ballots.add(this.ballot);



        }
        return ballots;
    }

    public ArrayList<ArrayList<Integer>> getBallots(){
        return this.ballots;
    }


    public int getCandidates() {
        return candidates;
    }

    public int getTestMakerSize() {
        return testMakerSize;
    }
}
