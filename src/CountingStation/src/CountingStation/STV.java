package CountingStation;

import javax.imageio.ImageReader;
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
    private boolean winnerThisRound = false;
    private Integer hareQuota, droopQuota, preference;
    private Integer places = 3, previousLoser = null, iterator = 0;
    private Integer candidates = 8, candidatesRemaining = 8;
    private Integer lowestCandidateCount, locationOfLowestCandidate = 0;

    private ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray = new ArrayList<ArrayList<ArrayList<Integer>>>();
    private ArrayList<ArrayList<Integer>> ballotArray = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> winnerTable = new ArrayList<Integer>(), loserTable = new ArrayList<Integer>();

    public STV() {
    }

    public Integer getHareQuota() {
        return hareQuota;
    }

    public Integer getDroopQuota() {
        return droopQuota;
    } //Quota used in Ireland.

    public void electionRun(ArrayList<ArrayList<Integer>> ballots) {

        ballotArray = ballots;
        setQuota(ballots.size(), places);
        System.out.println("Ballot pool size:" + ballots.size());
        System.out.println("Candidates in the race " + candidates);
        System.out.println("Places up for grab: " + places);
        System.out.println("Quota: " + getDroopQuota());
        System.out.println();
        createCandidatesArrays();
        System.out.println("Candidates Array created");

        organiseBallots();

        while (isCountingOver() == false) {
            checkNewWinner();
            if (isWinnerThisRound()) {
                winnerRemoveExcess();
                System.out.println("ballotArraySize Is Winner: " + ballotArray.size());
            } else {
                newLoser();
                loserRemoveExcess();
                System.out.println("ballotArraySize is Loser: " + ballotArray.size());
            }

            organiseBallots();
            winnerThisRound = false;

            checkIfOver();
            for (int i = 0; i < 8; i++) {
                System.out.println("" + i + " Array :" + candidatesArray.get(i).size());
            }
            System.out.println();
            System.out.println("Winners Table: " + winnerTable);
            System.out.println("Losers Table: " + loserTable);
            System.out.println("Quota: " + getDroopQuota());
            System.out.println();
        }
        closeResults();
    }


    public void setQuota(Integer ballots, Integer places) { //Quote is the number of votes a candidate needs to be elected.

        hareQuota = (ballots / places);
        droopQuota = ((ballots / (places + 1)) + 1); //Irish Used Quota
    }

    //creates the candidate Arraylists
    public void createCandidatesArrays() {
        for (int i = 0; i < candidates; i++) {
            candidatesArray.add(new ArrayList<ArrayList<Integer>>());
        }
        System.out.println("CandidatesArrayCreated");
    }

    public void organiseBallots() { //This should sort ballots that are put into ballotArray. Either moving to a new candidate else deleting if no more pref.
        while (ballotArray.size() > 0) {

            preference = ballotArray.get(ballotArray.size() - 1).get(0);
            System.out.println("organiseBallotsPref: " + preference);
            if (!(winnerTable.contains(preference)) && !(loserTable.contains(preference))) {
                candidatesArray.get(preference).add(ballotArray.get(ballotArray.size() - 1));
                System.out.println("Ballot has been added to candidate: " + preference);
                ballotArray.remove(ballotArray.size() - 1);
                ballotArray.trimToSize();
            } else if (ballotArray.get(ballotArray.size() - 1).size() > 1) {
                ballotArray.get(ballotArray.size() - 1).remove(0);
                ballotArray.get(ballotArray.size() - 1).trimToSize();
            } else {
                ballotArray.remove(ballotArray.size() - 1);
                ballotArray.trimToSize();
            }


        }
        System.out.println("Organise Pass: ");
        System.out.println("");
    }

    public void checkNewWinner() {
        System.out.println("New Winner Check");
        for (Integer i = 0; i < candidates; i++) {
            if (candidatesArray.get(i).size() >= getDroopQuota() && (!winnerTable.contains(i)) && (!loserTable.contains(i))) {
                winnerTable.add(i);
                System.out.println("New Winner is candidate " + i);
                candidatesRemaining--;
                winnerThisRound = true;

            }
        }
    }

    public void newLoser() {
        System.out.println("New Loser");
        lowestCandidateCount = 10000;
        for (Integer i = 0; i < candidates; i++) {
            if (candidatesArray.get(i).size() < lowestCandidateCount && (!winnerTable.contains(i)) && (!loserTable.contains(i))) {
                lowestCandidateCount = candidatesArray.get(i).size();
                locationOfLowestCandidate = i;
            }
        }
        loserTable.add(loserTable.size(), locationOfLowestCandidate);
        previousLoser = locationOfLowestCandidate;
        System.out.println("New Loser is candidate " + locationOfLowestCandidate);
        candidatesRemaining--;
    }

    public void checkIfOver() {
        if (places == winnerTable.size() || places - winnerTable.size() == candidatesRemaining) {
            countingOver = true;
            System.out.println("The election is over.");
        }
    }


    public void closeResults() {
        while (places > winnerTable.size()) {
            for (Integer i = 0; i < candidates; i++) {
                if ((!winnerTable.contains(i)) && (!loserTable.contains(i))) {
                    winnerTable.add(i);
                    System.out.println("Candidate " + i + " Won after the ballot.");
                }
            }
        }
        System.out.println("");
        System.out.println("The winning candidates are " + winnerTable);
    }

    public void winnerRemoveExcess() {
        preference = winnerTable.get(0);
        System.out.println("winnerRemoveExcess Preference: " + preference);
        iterator = candidatesArray.get(preference).size() - 1;
        System.out.println(iterator);
        for (; candidatesArray.get(preference).size() > getDroopQuota(); ) {
            System.out.println(iterator);
            while (iterator > 0) {
                if (candidatesArray.get(preference).get(iterator).size() >= 2) {//If Ballot has another preference still.
                    candidatesArray.get(preference).get(iterator).remove(0);//Removes the current preference
                    candidatesArray.get(preference).get(iterator).trimToSize();
                    ballotArray.add(candidatesArray.get(preference).get(iterator));//Puts into ballotArray for later sorting.
                    candidatesArray.get(preference).remove(iterator);
                    candidatesArray.get(preference).trimToSize();
                    System.out.println("winnerRemoveExcess 1");
                } else {
                    candidatesArray.get(preference).remove(iterator);
                    candidatesArray.get(preference).trimToSize();
                    System.out.println("winnerRemoveExcess 2");
                }
                iterator--;
            }
        }
    }

        public void loserRemoveExcess () {
            preference = previousLoser;
            System.out.println("loserRemoveExcess Preference: " + preference);

            iterator = candidatesArray.get(preference).size() - 1;
            System.out.println(iterator);
            for (; candidatesArray.get(preference).size() > 0; ) {
                System.out.println(iterator);
                while (iterator > 0) {
                    if (candidatesArray.get(preference).get(iterator).size() >= 2) {//If Ballot has another preference still.
                        candidatesArray.get(preference).get(iterator).remove(0);//Removes the current preference
                        candidatesArray.get(preference).get(iterator).trimToSize();
                        ballotArray.add(candidatesArray.get(preference).get(iterator));//Puts into ballotArray for later sorting.
                        candidatesArray.get(preference).remove(iterator);
                        candidatesArray.get(preference).trimToSize();
                        System.out.println("loserRemoveExcess 1");
                    } else {
                        candidatesArray.get(preference).remove(iterator);
                        candidatesArray.get(preference).trimToSize();
                        System.out.println("loserRemoveExcess 2");
                    }
                    iterator--;
                }
                candidatesArray.get(preference).remove(0);//Removes last element
                candidatesArray.get(preference).trimToSize();
                System.out.println("loserRemoveExcess 3");
            }
            System.out.println("loserRemoveExcess 4");
        }


        public boolean isCountingOver () {
            return countingOver;
        }

        public boolean isWinnerThisRound () {
            return winnerThisRound;
        }

}
