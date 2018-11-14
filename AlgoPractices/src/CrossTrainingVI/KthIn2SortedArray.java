package CrossTrainingVI;

public class KthIn2SortedArray {
    //Solution1: two pointers, 谁小移谁 T(n)
    public static int kthTwoPointers(int[] a, int[] b, int k) {
        //Assumption: a and b are not null, at least one is not empty, k >= 1 and k <= a.length + b.length
        if (a.length == 0) {
            return b[k - 1];
        }
        if (b.length == 0) {
            return a[k - 1];
        }
        //semantic meaning of count: so far find count-th element in two arrays.
        int i = 0, j = 0, count = 0;
        while (i < a.length && j < b.length) {
            if (i < a.length && j < b.length && a[i] <= b[j]) { // check which smaller
                count++;
                if (count == k) {
                    return a[i];
                } else {
                    i++; // after check: left side (exclude i) has been checked
                }

            } else if (i < a.length && j < b.length && a[i] > b[j]) {
                count++;
                if (count == k) {
                    return b[j];
                } else {
                    j++;
                }
            }
        }
        if (count < k) {
            if (i < a.length)
                return a[i + k - count - 1]; // i haven't been checked yet, so - 1
            else
                return b[j + k - count - 1];
        }
        return -1;
    }

    // Solution 2: binary search (remove k / 2 element each time)
    public static int kthBinarySearch(int[] a, int[] b, int k) {
        //Assumption: a and b are not null, at least one is not empty, k >= 1 and k <= a.length + b.length
        if (a.length == 0) {
            return b[k - 1];
        }
        if (b.length == 0) {
            return a[k - 1];
        }

        return -1;
    }
//    private static int binarySearchHelper(int[] a, int[] b, int astart, int bstart, int k) {
//        // base case
//        if ()
//    }




    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7, 9, 10, 11, 12, 13};
        int[] b = new int[]{2, 4, 6, 8};
        int r11 = KthIn2SortedArray.kthTwoPointers(a, b, 5);
        int r12 = KthIn2SortedArray.kthTwoPointers(a, b, 11);
        System.out.println(r11);
        System.out.println(r12);

        int r21 = KthIn2SortedArray.kthBinarySearch(a, b, 5);
        int r22 = KthIn2SortedArray.kthBinarySearch(a, b, 11);
        System.out.println(r21);
        System.out.println(r22);


    }

}
