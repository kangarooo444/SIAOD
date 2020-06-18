import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FibbSearch {
    public static List<Integer> array;
    public static int[] indexes = new int[100];
    public static void main(String[] args) throws IOException {
        //create and sort list
        array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<100; i++){
            array.add(random.ints(0,(100+1)).findFirst().getAsInt());
        }
        System.out.println(array);
        Collections.sort(array);
        System.out.println(array);
        //enter num to find
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        //find with Fibb
        FibbNums();
        System.out.println(FibbSearch(0,n));

    }
    public static void FibbNums()
    {
        indexes[0]=1;
        indexes[1]=1;
        for (int i=2; i<indexes.length; i++)
        {
            indexes[i] = indexes[i-1]+indexes[i-2];
            //System.out.print(indexes[i]+" ");
        }
    }
    public static int FibbSearch(int firstint, int n){
        int index=-1;

        if (array.get(firstint)==n) return firstint;

        for(int i=1;i<indexes.length;i++){

            if (firstint+indexes[i]>array.size())
                if (array.get(array.size()-1)<n) return -1;
                else {
                    return FibbSearch(firstint+indexes[i-1],n);
                }

            if (array.get(firstint+indexes[i])==n) return firstint+indexes[i];
            if (array.get(firstint+indexes[i])>n) {
                if (i==1) return -1;
                return FibbSearch(firstint+indexes[i-1],n);
            }
        }
        return index;
    }

}
