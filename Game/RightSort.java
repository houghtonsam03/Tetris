package Game;
import java.util.*;

public class RightSort implements Comparator<int[]> {

    public int compare(int[] o1,int[] o2) {
        if (o1[1] == o2[1]) {return o1[0]-o2[0];}
        else {return o1[1]-o2[1];}
    }
    
}
