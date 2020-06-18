import java.util.Set;
import java.util.TreeSet;

public class main {
    static int maxValue = 100;
    static int countSize = 100;
    static TreeSet treeSet = new TreeSet<>();
    static int[] array = new int[countSize];
    static long timer=0;


    public static void main(String[] args) {

        for (int i=0;i<countSize;i++) {
            array[i] = ((int) (Math.random() * maxValue));
            treeSet.add(array[i]);
        }
        timeSet();
        System.out.println(standardSearch(array,7));
        timeSet();
        timeSet();
        System.out.println(treeSet.contains(7));
        timeSet();
    }

    public static int standardSearch(int[] array, int search){
        for (int i=0;i<countSize;i++)
            if (array[i]==search)
                return i;
            return -1;
    }

    public static void printArr(int[] array){
        for (int i=0;i<countSize;i++)
            System.out.print(array[i]+" ");
        System.out.println();
    }

    public static void timeSet(){
        if (timer==0) timer=System.nanoTime();
        else {
            double val = (double)(System.nanoTime()-timer);
            System.out.println(val/1000000);
            timer=0;
        }
    }
}
