import java.util.Random;
import java.util.Arrays;

public class main {
    static int hMatr=5;
    static int wMatr=5;
    static int mValue=99;
    static int[][] matr;
    static long timer=0;

    public static void main(String[] args) {
        int[][]copMatr;
        matr = new int[hMatr][wMatr];
        Random random = new Random();
        for (int i=0; i<hMatr; i++)
            for (int j=0; j<wMatr; j++)
                matr[i][j] = random.ints(0,(mValue+1)).findFirst().getAsInt();

        //printMatr(matr);
        System.out.println("----------");

        System.out.println("===STANDART_SORT===");
        copMatr=cloneMatr(matr);
        timeSet();
        standartSort(copMatr);
        timeSet();

        System.out.println("===PASTE_SORT===");
        copMatr=cloneMatr(matr);
        timeSet();
        pasteSort(copMatr);
        timeSet();

        System.out.println("===QUICK_SORT===");
        copMatr=cloneMatr(matr);
        timeSet();
        quickSort(copMatr);
        timeSet();

        System.out.println("----------");
        //printMatr(copMatr);
    }

    public static void printMatr(int[][] matr){
        for (int i=0; i<hMatr; i++){
            for (int j=0; j<wMatr; j++){
                if (j==wMatr-1)
                    System.out.println(matr[i][j]);
                else
                    System.out.print(matr[i][j]+"\t");
            }
        }
    }

    public static int[][] pasteSort(int[][] matrSource){
        int[][] matr = matrSource.clone();
        for (int i=0; i<hMatr; i++){
            for (int j=1; j<wMatr; j++) {
                int counter=0;
                for(int k=j; k>0 && matr[i][k-1]>matr[i][k];k--){
                    counter++;
                    int tmp=matr[i][k-1];
                    matr[i][k-1]=matr[i][k];
                    matr[i][k]=tmp;
                }
            }
        }
        return matr;
    }

    public static int[][] quickSort(int[][] matrSource){
        int[][] matr = matrSource.clone();
        for (int i=0;i<hMatr;i++){
            quickSortLine(matr[i],0,wMatr-1);
        }
        return matr;
    }

    public static int[][] standartSort(int[][] matrSource){
        int[][] matr = matrSource.clone();
        for (int i=0;i<hMatr;i++){
            Arrays.sort(matr[i]);
        }
        return matr;
    }

    public static void quickSortLine(int[] matr, int low, int high){
        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = matr[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (matr[i] < opora) {
                i++;
            }

            while (matr[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = matr[i];
                matr[i] = matr[j];
                matr[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSortLine(matr, low, j);

        if (high > i)
            quickSortLine(matr, i, high);
    }

    public static int[][] cloneMatr(int[][] matr){
        int[][] newMatr = new int[hMatr][wMatr];
        for (int i=0; i<hMatr; i++) {
            for (int j = 0; j < wMatr; j++) {
                newMatr[i][j]=matr[i][j];
            }
        }
        return newMatr;
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
