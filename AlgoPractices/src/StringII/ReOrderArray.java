package StringII;
/*
    Given an array of elements, reorder it as follow:
    { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
    { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

    pre-process: length is odd or even

    Solution:  recursion +  三步反转法
                example: AB CD 12 34 --> AB12 CD34 --> A1B2C3D4
                           |  |  |
                    leftmid mid rightmid

                During each recursion:
                base case:
                ***************************************************
                Critical details: chunk 1 == chunk 3  ** due to pre-process, size must be even number

                A B C D E F G  1 2 3 4 5 6 7
                |     |        |     |
                left  lmid     mid   rmid

                length: A B C  =  1 2 3
                length: D E F G  = 4 5 6 7

                In order to make sure "left -- lmid" and "mid -- rmid" size same:
                    left = 0
                    mid = (1/2)*size
                   lmid = left + size / 4
                   rmid = mid + size 4
                        = left + 3 * size / 4

                ***************************************************
                During each recursion:  each level is O(n)
                step 1: find:   leftmid,
                                mid,
                                rightmid
                step 2: 三步反转法          -- O(n/4 + n/4 + n/2) = O(n)

                step 3: recursion call left part and right part --> be careful to the new left size and right size    -- O(n/2) + O(n/2) = O(n)

                注意： 最后才整体反转，因为每个chunk长度不一致，先整体反转的话，长度不好处理，容易乱

                Time: O(nlogn)

 */

public class ReOrderArray {
    public static int[] reOrderArray(int[] array) {
        //Assumption: the array is not null
        if (array.length == 0 || array.length == 1) return array;

        if (array.length % 2 == 0) {
            convert(array, 0, array.length - 1);
        } else {
            convert(array, 0, array.length - 2);
        }
        return array;
    }
    public static void convert(int[] array, int left, int right) {
        int size = right - left + 1;
        //base case
        if (size <= 2) return; //分不了四段了, size must be even number

        int mid = left + size / 2;
        int lmid = left + size / 4;
        int rmid = left + (3 * size / 4); // don't work for size * (3/4)

        reverse(array, lmid, mid - 1); // exclusive lmid
        reverse(array, mid, rmid - 1);
        reverse(array, lmid, rmid - 1);

        convert(array, left, left + 2 * (lmid - left) - 1); // chunk 1 size * 2 - 1 // because it is index, we need to - 1
        convert(array, left + 2 * (lmid - left), right);
    }

    public static void reverse(int[] array, int a, int b) {
        while (a < b) {
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
            a++;
            b--;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4};
        int[] output = ReOrderArray.reOrderArray(input);
        for (int i : input) {
            System.out.print(i + ", ");
        }

    }

}
