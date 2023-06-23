package merge;

public class merge {
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
