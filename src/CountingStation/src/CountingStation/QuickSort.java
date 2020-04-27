package CountingStation;

import java.util.ArrayList;

// Java program for implementation of QuickSort  https://www.geeksforgeeks.org/quick-sort/
public class QuickSort {

    public static int partition(ArrayList<String> ballots, int first, int last)
    {

        int left, right, temp, loc, flag, i , j;
        loc = left = first;
        right = last;
        flag = 0;

        while(flag != 1)
        {
            while((i = Integer.parseInt(ballots.get(loc).split(",")[0])) <= (j = Integer.parseInt(ballots.get(right).split(",")[0])) && (loc!=right))
                right--;
            if(loc==right)
                flag =1;
            else if(i>j)
            {
                temp = i;
                i = j;
                j = temp;
                loc = right;
            }
            if(flag!=1)
            {
                while((i = Integer.parseInt(ballots.get(loc).split(",")[0])) <= (j = Integer.parseInt(ballots.get(left).split(",")[0])) && (loc!=left))
                    left++;
                if(loc==left)
                    flag =1;
                else if(i < j)
                {
                    temp = i;
                    i = j;
                    j = temp;
                    loc = left;
                }
            }
        }
        return loc;
    }
    static void quickSort(ArrayList<String> ballots, int first, int last)
    {

        int loc;
        if(first < last)
        {
            loc = partition(ballots,first,last);
            quickSort(ballots, first, loc-1);
            quickSort(ballots, loc+1, last);
        }
    }
}
