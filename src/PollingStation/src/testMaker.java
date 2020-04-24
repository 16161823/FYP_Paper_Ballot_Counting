import java.util.ArrayList;
import java.util.Random;


public class testMaker {

    ArrayList<ArrayList<Integer>> ballots = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> ballot = new ArrayList<Integer>();
    int candidates = 8;
    Random random = new Random();
    static int testMakerSize = 100;
    Integer a = null;
    Integer b = null;
    Integer c = null;

    public ArrayList<ArrayList<Integer>> testMakeArray(){

        for(int i = 0;i < testMakerSize;i++){

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
            b = random.nextInt(candidates+1);
            c = random.nextInt(candidates+1);


            this.ballot = new ArrayList<Integer>();

            this.ballot.add(a);
            this.ballot.add(b);
            this.ballot.add(c);

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

    public static int getTestMakerSize() {
        return testMakerSize;
    }
}
