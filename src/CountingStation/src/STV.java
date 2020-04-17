import org.jetbrains.annotations.NotNull;

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
    private Integer hareQuota, droopQuota, candidates, places;

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
        createCandidatesArrays();
        firstOrganiseBallots(ballots);
        while(!countingOver){
            isNewWinner(candidatesArray);
            isCountingOver();
            if (someoneWonThisRound){
                redistributingWinnerBallots(candidatesArray);
            }else{
                redistributingLoserBallots(candidatesArray);
            }
        }
        System.out.println(placesTakenArray);
    }


    public void setQuota(Integer ballots, Integer places) {
        //Quote is the number of votes a candidate needs to be elected.
        hareQuota = (ballots / places);
        //droopQuota = ((ballots / (places + 1)) + 1);
    }

    //creates the candidate Arraylists
    public void createCandidatesArrays() {
        for (int i = 0; i < candidates; i++) {
            candidatesArray.add(new ArrayList<ArrayList<Integer>>());
        }
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
    }

    //Checks if counting can stop.
    public void isCountingOver() {
        if (placesTakenArray.size() == places) {
            countingOver = true;
        }
    }

    public void redistributingWinnerBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        for (int i = 0; i < placesTakenArray.size(); i++) {

            int a = placesTakenArray.get(i);
            if (candidatesArray.get(a).size() > this.getHareQuota()) {
                for (int x = 1; candidatesArray.get(a).size() > this.getHareQuota(); x++) {
                    //Should remove the first preference from ballot allowing to sort based on second and so on.
                    //if(!(candidatesArray.get(a).get((candidatesArray.get(a).size())-x).get(0).isEmpty())){
                    if (!candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).isEmpty()) {
                        candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).remove(0);
                        //Gets Preference inside ballot array first slot.
                        if (!candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).isEmpty()) {
                            Integer preference = candidatesArray.get(a).get((candidatesArray.get(a).size()) - x).get(0);
                            for (int xyz = 0; xyz < loserTable.size() + 1; xyz++) {
                                if (!loserTable.contains(preference)) {
                                    candidatesArray.get(preference - 1).add(0, candidatesArray.get(a).get((candidatesArray.get(a).size()) - x));
                                    candidatesArray.get(a).remove((candidatesArray.get(a).size()) - x);
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
    }

    //Method Removes ballots from lost candidates arrays, either transfering them to other preferences or deleting them if no other preference.
    public void redistributingLoserBallots(ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        for (int i = 0; i < loserTable.size(); i++) {

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
    }


    //Checks to see if specific candidate won. ArrayList passed should be candidateArray.get(i) where i is the candidate.
    public void isNewWinner(@NotNull ArrayList<ArrayList<ArrayList<Integer>>> candidatesArray) {
        for (int i = 0; i < candidatesArray.size(); i++) {
            if (candidatesArray.get(i).size() >= hareQuota) {
                if (!placesTakenArray.contains(i)) {
                    placesTakenArray.add(i);
                    someoneWonThisRound = true;
                    System.out.println(("Candidate:"+ i +" Won this round"));
                }
            }
        }
    }
}