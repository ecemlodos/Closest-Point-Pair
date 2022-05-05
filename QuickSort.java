

import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        if (orderBy.equals("compareX")) {
            if (startIndex < lastIndex) {
                // pi is partitioning index, arr[p]
                // is now at right place
                int pi = partitionX(arr, startIndex, lastIndex);

                // Separately sort elements before
                // partition and after partition
                sort(arr, startIndex, pi - 1, "compareX");
                sort(arr, pi + 1, lastIndex, "compareX");
            }
        } else if (orderBy.equals("compareY")) {
            if (startIndex < lastIndex) {
                // pi is partitioning index, arr[p]
                // is now at right place
                int pi = partitionY(arr, startIndex, lastIndex);

                // Separately sort elements before
                // partition and after partition
                sort(arr, startIndex, pi - 1, "compareY");
                sort(arr, pi + 1, lastIndex, "compareY");
            }
        }
    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        Point2D.Double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        int center = (left + right) / 2;
        // Sort A[left], A[right], A[center]
        if (arr[left].getX() > arr[center].getX()) swap(arr, left, center);
        if (arr[left].getX() > arr[right].getX()) swap(arr, left, right);
        if (arr[center].getX() > arr[right].getX()) swap(arr, center, right);
        swap(arr, center, right - 1); // Hide the pivot
        return arr[right - 1];
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        int center = (left + right) / 2;
        // Sort A[left], A[right], A[center]
        if (arr[left].getY() > arr[center].getY()) swap(arr, left, center);
        if (arr[left].getY() > arr[right].getY()) swap(arr, left, right);
        if (arr[center].getY() > arr[right].getY()) swap(arr, center, right);
        swap(arr, center, right - 1); // Hide the pivot
        return arr[right - 1];
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        // pivot
        Point2D.Double pivot = getMedianX(arr, startIndex, lastIndex);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (startIndex - 1);

        for (int j = startIndex; j <= lastIndex - 1; j++) {
            // If current element is smaller
            // than the pivot
            if (arr[j].getX() < pivot.getX()) {
                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, lastIndex - 1);
        return (i + 1);
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        // pivot
        Point2D.Double pivot = getMedianY(arr, startIndex, lastIndex);

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (startIndex - 1);

        for (int j = startIndex; j <= lastIndex - 1; j++) {
            // If current element is smaller
            // than the pivot
            if (arr[j].getY() < pivot.getY()) {
                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, lastIndex - 1);
        return (i + 1);
    }
}
