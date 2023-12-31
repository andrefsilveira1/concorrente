package concorrente;

import leitor.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MergeSortConcorrente extends RecursiveAction {
    private final int[] array;
    private final int start;
    private final int end;

    public MergeSortConcorrente(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        String file = args[0];
        System.out.println("O arquivo escolhido foi: " + file);
        Reader leitor = new Reader();
        List<Integer> entradas = leitor.read(file);

        int[] array = entradas.stream().mapToInt(Integer::intValue).toArray();
        int size = array.length - 1;

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        MergeSortConcorrente sort = new MergeSortConcorrente(array, 0, size);

        long start = System.nanoTime();
        forkJoinPool.invoke(sort);
        long limit = System.nanoTime();
        long duration = (limit - start);

        System.out.println("Tempo de execução: " + duration + " nano seconds");
//        System.out.println(Arrays.toString(array));
    }

    @Override
    protected void compute() {
        if (array != null && start < end) {
            int half = (end + start) / 2;
            MergeSortConcorrente leftSide = new MergeSortConcorrente(array, start, half);
            MergeSortConcorrente rightSide = new MergeSortConcorrente(array, half + 1, end);

            invokeAll(leftSide, rightSide);

            Merge(array, start, half, end);
        }
    }

    public static void Merge(int[] array, int start, int half, int end) {
        int aux1 = half - start + 1;
        int aux2 = end - half;

        int[] Left = new int[aux1];
        int[] Right = new int[aux2];

        System.arraycopy(array, start, Left, 0, aux1);
        System.arraycopy(array, half + 1, Right, 0, aux2);

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
