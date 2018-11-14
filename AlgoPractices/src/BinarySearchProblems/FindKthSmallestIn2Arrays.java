package BinarySearchProblems;

/**
 * return kth smallest number
 * 在两个array中分别找到K/2的elements，比较二者，其中较小的以及它之前的elements都一定在前K中。
 * 在较小者之后再取K/4，在另一个array中取K/4，如此递归比较。
 * Time: logk
 */
public class FindKthSmallestIn2Arrays {
    public static int kth(int[] a, int[] b, int k) {
        //Assumption: a and b are not null, at least one is not empty, k >= 1 and k <= a.length + b.length
        if (a.length == 0) return b[k - 1];
        if (b.length == 0) return a[k - 1];

        return bSearchHelper(a, 0, b, 0, k);
    }
    private static int bSearchHelper(int[] a, int aleft, int[] b, int bleft, int k) {
        //base cases: 1.超界  2.k == 1    ---------> base cases are important
        if (aleft > a.length - 1) {
            return b[bleft + k - 1];
        }
        if (bleft > b.length - 1) {
            return a[aleft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }

        //recursively compare and divide k
        int amid = aleft + k / 2 - 1;
        int bmid = bleft + k / 2 - 1;
        int aval = amid < a.length ? a[amid] : Integer.MAX_VALUE;  // ----------> assign max value if out of bound
        int bval = bmid < b.length ? b[bmid] : Integer.MAX_VALUE;

        if (aval <= bval) {
            return bSearchHelper(a, aleft + k / 2, b, bleft, k - k / 2); // ---> need to be k - k/2 !!!
        } else {
            return bSearchHelper(a, aleft, b, bleft + k / 2, k - k / 2); // ---> need to be k - k/2 !!!
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{1,4,5,5,8,12,12,12};
        int[] b = new int[]{2,2,3,7,9,9,9};
        System.out.println(FindKthSmallestIn2Arrays.kth(a, b, 14));

    }
}
