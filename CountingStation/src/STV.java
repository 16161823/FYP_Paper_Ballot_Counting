import java.util.ArrayList;
/*
Steps:
1. Put ballots in candidates arraylists
2. See who wins
3. Check if vote is over
4. Put ballots being reused into an seperate arraylist to be redistributed
 */
public class STV {

    //Ballots come in as String of comma separated characters 0-X, with the order they come in being the preference of the ballot paper. so 4,3,1 means the 4th candidate is their 1st preference, 3th candidate is 2nd preference and so on.
    public STV(int candidates, int places, ArrayList<String>allBallots) {

        int ballots = allBallots.size();

        //Quote is the nymber of votes a candidate needs to be elected.
        int hareQuota = (ballots * places);
        int droopQuota = ((ballots * (places + 1)) + 1);

        boolean countingOver = false;
        boolean someoneWonThisRound = false;
        int[]placesTakenArray = new int[candidates];


        //String regExpression = "[[^[0-9||,]][1][,]]";

        //Arraylist to hold Arraylists of individual candidates
        ArrayList<ArrayList<String>> candidatesArray = new ArrayList<ArrayList<String>>();

        //creates the candidate Arraylists
        for (int i = 0; i < candidates; i++) {
            candidatesArray.add(new ArrayList<String>());
        }
    }
    //Sorts the Arraylist
    public void firstOrganiseBallots(ArrayList<String>ballots){
        for (int i = 0; i < ballots.size(); i++) {

            String currentBallot = ballots.get(i).split(",")[0];
            int a = Integer.parseInt(currentBallot);

            for (int z = 1; z < (candidates + 1); z++) {
                if (z == i) {
                    candidatesArray.get(z - 1).add(currentBallot);
                    break;
                    }
                }
            }
    }

    //Checks to see if anyone won this round.
    public void whoWon(ArrayList<ArrayList<String>> candidatesArray){

        int previousWinners = placeTakenArray.length;
        someWonThisRound = false;

        for(int i = 0; i < candidates; i++){
            if (candidatesArray.get(i).size() >= hareQuota){
                placeTakenArray.add(i);
            }
        }

        if(placeTakenArray.length > previousWinners){
            someoneWonThisRound = true;
        }
    }

    //Checks if counting can stop.
    public void isCountingOver(){
        if(placeTakenArray.length = places){
            countingOver = true;
        }
    }

    public void redistributingWinnerBallots(ArrayList<ArrayList<String>> candidatesArray){
        for (int i = 0;i < placesTakenArray.length;i++){
            int a = placesTakenArray[i];
            if(candidatesArray.get(a).size() > hareQuote){
                for (int x = 0;candidatesArray.get(a).size() > hareQuote;x++){
                    String [] temp = candidatesArray.get(a).get(x).split(",");
                    String
                }
            }
        }
    }
    //If voting not over and someone won redistribute their extra ballots and count again
    //If voting not over and no winner this round, redistribute their ballots and check for winner again.
}
