package concorrente;

import leitor.Reader;
import merge.merge;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MergeSortConcorrente implements Runnable {
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

        MergeSortConcorrente sort = new MergeSortConcorrente(array, 0, size);

        ExecutorService executor = Executors.newFixedThreadPool(5);
        long start = System.nanoTime();

        executor.execute(sort);

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            long limit = System.nanoTime();
            long duration = (limit - start);
            System.out.println("Tempo de execução: " + duration + " nano seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(Arrays.toString(array));
    }

    @Override
    public void run() {
        if (array != null && start < end) {
            int half = (end + start) / 2;
            MergeSortConcorrente leftSide = new MergeSortConcorrente(array, start, half);
            MergeSortConcorrente rightSide = new MergeSortConcorrente(array, half + 1, end);

            leftSide.run();
            rightSide.run();
            merge.Merge(array, start, half, end);
        }
    }
}
