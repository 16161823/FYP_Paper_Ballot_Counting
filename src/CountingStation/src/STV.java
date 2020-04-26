

import java.util.ArrayList;
/*
Steps:
1. Put ballots in candidates arraylists
2. See who wins
3. Check if vote is over
4. Put ballots being reused into an seperate arraylist to be redistributed
4b.Ballots have to be checked if they have any further preferences before being redistributed.
 */
 //Arraylist.ensureCapacity operation**


public class STV {

    private boolean countingOver = false;
    private boolean someoneWonThisRound = false;
    private Integer hareQuota, droopQuota;
    private Integer places = 2;
    private Integer candidates = 8;
    private Integer lowestCandidateCount = 0;
    private Integer locationOfLowestCandidate = 0;

    private ArrayList<Integer> placesTakenArray = new ArrayList<Integer>();
    private ArrayList<Integer> loserTable = new ArrayList<Integer>();

    private ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray = new ArrayList<ArrayList<ArrayList<Integer>>>();

    //Ballots come in as String of comma separated characters 0-X, with the order they come in being the preference of the ballot paper. so 4,3,1 means the 4th candidate is their 1st preference, 3th candidate is 2nd preference and so on.
    //public STV(int candidates, int places, ArrayList<ArrayList<Integer>> allBallots) {
    public STV() {
    }

    public Integer getHareQuota() {
        return hareQuota;
    }
    public Integer getDroopQuota(){ return droopQuota;}

    public void electionRun(ArrayList<ArrayList<Integer>> ballots){
        setQuota(ballots.size(),places);
        System.out.println("setQuota:" + getHareQuota());
        System.out.println("ballot size:" + ballots.size());
        System.out.println("places:" + places);
        System.out.println();

        createCandidatesArrays();


        firstOrganiseBallots(ballots);


        isNewWinner(candidatesArray);
        isCountingOver();

        while(!countingOver){
            isNewWinner(candidatesArray);
            isCountingOver();
            candidatesArray.get(0).trimToSize();
            candidatesArray.get(1).trimToSize();
            candidatesArray.get(2).trimToSize();
            candidatesArray.get(3).trimToSize();
            candidatesArray.get(4).trimToSize();
            candidatesArray.get(5).trimToSize();
            candidatesArray.get(6).trimToSize();
            candidatesArray.get(7).trimToSize();

            System.out.println("Candidate:" + 0 + "ArraySize = "+ candidatesArray.get(0).size());
            System.out.println("Candidate:" + 1 + "ArraySize = "+ candidatesArray.get(1).size());
            System.out.println("Candidate:" + 2 + "ArraySize = "+ candidatesArray.get(2).size());
            System.out.println("Candidate:" + 3 + "ArraySize = "+ candidatesArray.get(3).size());
            System.out.println("Candidate:" + 4 + "ArraySize = "+ candidatesArray.get(4).size());
            System.out.println("Candidate:" + 5 + "ArraySize = "+ candidatesArray.get(5).size());
            System.out.println("Candidate:" + 6 + "ArraySize = "+ candidatesArray.get(6).size());
            System.out.println("Candidate:" + 7 + "ArraySize = "+ candidatesArray.get(7).size());

            if (someoneWonThisRound){
                redistributingWinnerBallots(candidatesArray);
                someoneWonThisRound = false;
            }else{
                isNewLoser(candidatesArray);
                redistributingLoserBallots(candidatesArray);
            }
        }
        System.out.println("Winner Position is :" + placesTakenArray);
    }


    public void setQuota(Integer ballots, Integer places) {
        //Quote is the number of votes a candidate needs to be elected.
        hareQuota = (ballots/places);

        droopQuota = ((ballots / (places + 1)) + 1);
    }

    //creates the candidate Arraylists
    public void createCandidatesArrays() {
        for (int i = 0; i < candidates; i++) {
            candidatesArray.add(new ArrayList<ArrayList<Integer>>());
        }
        System.out.println("CandidatesArrayCreated");
    }

    public void firstOrganiseBallots(ArrayList<ArrayList<Integer>> ballots) {
        for (int i = 0; i < ballots.size(); i++) {

            Integer preference = ballots.get(i).get(0);

            for (int z = 1; z < (candidates + 1); z++) {
                if (z == preference) {
                    candidatesArray.get(z - 1).add(ballots.get(i));
                    break;
                }
            }
        }
        System.out.println("First Pass");
    }

    //Checks if counting can stop.
    public void isCountingOver() {
        //System.out.println("isCountingOverCheck");
        if (placesTakenArray.size() == places) {
            countingOver = true;
        }
    }
/*
    public void redistributingWinnerBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        //System.out.println(placesTakenArray.size());
        for (int i = 0; i < placesTakenArray.size(); i++) {
            Integer a  = placesTakenArray.get(i) ;
            System.out.println(a);
            if (candidatesArray.get(a).size() >= (Integer)(this.getDroopQuota())) {
                for (int x = 1; candidatesArray.get(a).size() > this.getDroopQuota(); x++) {
                    //Should remove the first preference from ballot allowing to sort based on second and so on.
                    //if(!(candidatesArray.get(a).get((candidatesArray.get(a).size())-x).get(0).isEmpty())){
                    if (!candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).get(1).isEmpty()) {
                        candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).remove(0);
                        //Gets Preference inside ballot array first slot.
                        if (!candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).isEmpty()) {
                            Integer preference = candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).get(0);
                            for (int xyz = 0; xyz < loserTable.size() + 1; xyz++) {
                                if (!loserTable.contains(preference)) {
                                    candidatesArray.get(preference-1).add(0, candidatesArray.get(a).get((candidatesArray.get(a).size()) - x));
                                    candidatesArray.get(a).remove((candidatesArray.get(a).size()) - x); //P
                                    break;
                                } else {
                                    if (!(candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).isEmpty())) {
                                        candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).remove(0);
                                        //Gets Preference inside ballot array first slot.
                                        preference = candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).get(0);
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        System.out.println("Winner Distributing");
    }
*/
    public void redistributingWinnerBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        //System.out.println(placesTakenArray.size());
        System.out.println("Winner Distributing");
        Integer x = 1;
        Integer pref = null;
        Integer candidateWhoWon = null;

        for (int i = 0; i < placesTakenArray.size(); i++) {

            candidateWhoWon = placesTakenArray.get(i);

            System.out.println("Candidate who's ballots are being redistributed:" + (candidateWhoWon + 1));
            x = candidatesArray.get(candidateWhoWon).size();
            System.out.println(x);
            while (candidatesArray.get(candidateWhoWon).size() >= getDroopQuota() && x != 0) {

                if (candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).size() >= 2)
                  for(int c = 0; candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).size() >= c; c++) {

                    if (!loserTable.contains(candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).get(1))
                    && !placesTakenArray.contains(candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).get(1))) {

                            pref = candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).get(1);

                            candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x).remove(0);

                            candidatesArray.get(pref).add(candidatesArray.get(candidateWhoWon).get(candidatesArray.get(candidateWhoWon).size() - x));
                            candidatesArray.get(candidateWhoWon).remove(candidatesArray.get(candidateWhoWon).size() - x);
                            candidatesArray.get(candidateWhoWon).trimToSize();
                        }
                    }
                    x--;
                }

            }
    }

/*
    //Method Removes ballots from lost candidates arrays, either transfering them to other preferences or deleting them if no other preference.
    public void redistributingLoserBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        //System.out.println(loserTable.size());
        for (int i = 0; i < loserTable.size(); i++) {
            System.out.println("redistributingLoserBallots:" + i);
            int a = loserTable.get(i);
            if (candidatesArray.get(a).size() > 0) {
                for (; candidatesArray.get(a).size() > 0; ) {
                    candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).remove(0);
                    if (!(candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).isEmpty())) {

                        Integer preference = candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).get(0);

                        for (int xyz = 0; xyz < loserTable.size() + 1; xyz++) {
                            if (!loserTable.contains(preference)) {
                                candidatesArray.get(preference - 1).add(0, candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1));
                                candidatesArray.get(a).remove((candidatesArray.get(a).size()) - 1);
                                break;
                            } else {
                                candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).remove(0);
                                //Gets Preference inside ballot array first slot.
                                if (!(candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).isEmpty())) {
                                    preference = candidatesArray.get(a).get((candidatesArray.get(a).size()) - 1).get(0);
                                } else {
                                    candidatesArray.get(a).remove((candidatesArray.get(a).size()) - 1);
                                    break;
                                }
                            }
                        }

                    } else {
                        candidatesArray.get(a).remove(candidatesArray.get(a).size() - 1);
                    }
                }
            }
        }
        //System.out.println("LoserDistributing");
    }
*/
    public void redistributingLoserBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        //System.out.println(loserTable.size());
        for (int i = 0; i < loserTable.size(); i++) {

            Integer aLoser = loserTable.get(i);
            Integer ballotTracking = candidatesArray.get(aLoser).size() - 1;
            Integer ballotPref = null;
            Integer passing = null;
            Boolean moved = null;
            while (ballotTracking > -1) {
                if (!(candidatesArray.get(aLoser).size() == 0)) {
                    System.out.println("1");
                    while (candidatesArray.get(aLoser).size() > 0 && ballotTracking > -1) {
                        System.out.println("2");
                        if (candidatesArray.get(aLoser).get(ballotTracking).size() > 1) {
                            System.out.println("3");
                            while (candidatesArray.get(aLoser).get(ballotTracking).size() > 1) {
                                candidatesArray.get(aLoser).get(ballotTracking).remove(0);
                                passing = candidatesArray.get(aLoser).get(ballotTracking).get(0);
                                System.out.println("4");
                                if (!(placesTakenArray.contains(passing) && !(loserTable.contains(passing)))) {
                                    candidatesArray.get(passing).add(candidatesArray.get(aLoser).get(ballotTracking));
                                    candidatesArray.get(aLoser).remove(ballotTracking);
                                    candidatesArray.get(aLoser).trimToSize();
                                    System.out.println("5");
                                    ballotTracking--;
                                    break;
                                } else if (candidatesArray.get(aLoser).get(ballotTracking).size() == 1) {
                                    candidatesArray.get(aLoser).remove(ballotTracking);
                                    candidatesArray.get(aLoser).trimToSize();
                                    System.out.println("6");
                                    ballotTracking--;
                                    break;
                                }
                            }
                        } else {
                            candidatesArray.get(aLoser).remove(ballotTracking);
                            candidatesArray.get(aLoser).trimToSize();
                            System.out.println("7");
                            ballotTracking--;
                            break;
                        }
                    }
                }
            }
        }
    }

    //Checks to see if specific candidate won. ArrayList passed should be candidateArray.get(i) where i is the candidate. The placesTakenArray holds the position of the candidates ballots in CandidatesArraylist
    public void isNewWinner(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        for (int i = 0; i < candidatesArray.size(); i++) {
            if (candidatesArray.get(i).size() >= getDroopQuota()) {

                if (!(placesTakenArray.contains(i))) {
                    placesTakenArray.add(i);
                    someoneWonThisRound = true;
                    System.out.println(("Candidate: "+ (i+1) +" Won this round"));
                }
                else
                {
                    //System.out.println("Candidate has won already.");
                }
            }
        }
    }

    public void isNewLoser(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        lowestCandidateCount = 1000000; //Could be set to size of all ballots together to begin
        locationOfLowestCandidate = -1;
        for (int i = 0; i < candidatesArray.size(); i++) {
            if (candidatesArray.get(i).size() < lowestCandidateCount && !loserTable.contains(i) ) {

                lowestCandidateCount = candidatesArray.get(i).size();
                locationOfLowestCandidate = i;
            }
        }
    if(locationOfLowestCandidate == -1)
    {
        System.out.println("isNewLoser -1 Error");
    }
    else {

        loserTable.add(locationOfLowestCandidate);
        System.out.println("LoserLocation: " + locationOfLowestCandidate);
    }
    }


    public void displayWinners(){
        for (int i = 1;i < placesTakenArray.size()+1;i++){
            System.out.println(placesTakenArray.get(placesTakenArray.size()-(i))+1);
        }
    }
}