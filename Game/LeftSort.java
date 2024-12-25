package Game;
import java.util.*;

public class LeftSort implements Comparator<int[]> {

    public int compare(int[] o1,int[] o2) {
        if (o1[1] == o2[1]) {return o2[0]-o1[0];}
        else {return o1[1]-o2[1];}
    }
    
}
