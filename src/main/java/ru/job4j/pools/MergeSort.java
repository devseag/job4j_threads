package ru.job4j.pools;

public class MergeSort {

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        // pri sledujushhem uslovii, massiv iz odnogo jelementa
        // delit' nechego, vozvrashhaem jelement
        if (from == to) {
            return new int[] { array[from] };
        }
        // popali sjuda, znachit v massive bolee odnogo jelementa
        // nahodim seredinu
        int mid = (from + to) / 2;
        // ob#edinjaem otsortirovannye chasti
        return merge(
                // sortiruem levuju chast'
                sort(array, from, mid),
                // sortiruem pravuju chast'
                sort(array, mid + 1, to)
        );
    }

    // Metod ob#edinenija dvuh otsortirovannyh massivov
    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] <= right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }

}