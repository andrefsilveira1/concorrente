package concorrente;
import leitor.Reader;
import java.util.*;
public class MergeSortConcorrente extends Thread {
    private int[] array;
    private int start;
    private int end;

    public MergeSortConcorrente(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    public static void main(String[] args) {
        String file = args[0];
        System.out.println("O arquivo escolhido foi:" + file);
        Reader leitor = new Reader();
        List<Integer> entradas = leitor.read(file);

        int[] array = entradas.stream().mapToInt(i->i).toArray();
        int size = array.length - 1;
        MergeSortConcorrente sort = new MergeSortConcorrente(array,0,size);

        long start = System.nanoTime();
        sort.start();
        try {
            sort.join();
            long limit = System.nanoTime();
            long duration = (limit - start);
//            System.out.println(Arrays.toString(array));
            System.out.println("Tempo de execução: " + duration + " nano seconds");

        } catch (InterruptedException err) {
            err.printStackTrace();
        }

    }

    @Override
    public void run() {
        if (array != null && start < end) {
            int half = (end + start) / 2;
            MergeSortConcorrente leftSide = new MergeSortConcorrente(array, start, half);
            MergeSortConcorrente rightSide = new MergeSortConcorrente(array, half+1, end);
            leftSide.start();
            rightSide.start();
            try {
                leftSide.join();
                rightSide.join();
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
            Merge(array, start, half, end);
        }
    }

    void Merge(int array[], int start, int half, int end) {
        int aux1 = half - start + 1;
        int aux2 = end - half;

        int Left[] = new int [aux1];
        int Right[] = new int [aux2];

        for (int i = 0; i < aux1; i++) {
            Left[i] = array[start + i];
        }
        for (int j = 0; j < aux2; j++) {
            Right[j] = array[half + 1 + j];
        }
        int init1 = 0, init2 = 0;
        int aux3 = start;

        while (init1 < aux1 && init2 < aux2) {
            if (Left[init1] <= Right[init2]) {
                array[aux3] = Left[init1];
                init1++;
            } else {
                array[aux3] = Right[init2];
                init2++;
            }
            aux3++;
        }
        while (init1 < aux1) {
            array[aux3] = Left[init1];
            init1++;
            aux3++;
        }

        while (init2 < aux2) {
            array[aux3] = Right[init2];
            init2++;
            aux3++;
        }
    }
}