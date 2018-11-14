package BinarySearchProblems;

import java.util.Arrays;

/**
 * while (left + 1 < right) ...mid
 * first locate the closest one and then 中心开花，谁小移谁，注意不要 out of boundary
 */

public class KClosestInSortedArray {
    private static int[] kClosest(int[] array, int target, int k) {
        // Assumptions: A is not null, k >= 0 and <= A.length, A contains duplicate numbers
        if (array.length == 0 || k == 0) {
            return  new int[0];
        }
        int left = 0;
        int right = array.length - 1;
        int[] result = new int[k];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target <= array[mid]) {//binary search first occurrence
                right = mid;
            } else {
                left = mid;
            }
        }
        for(int i = 0; i < k; i++) {// 中心开花，谁小移谁(<-left  right->)
            // first do operation AND(&&), then OR(||)
            // if (right out of bound) or (left in bound and left distance <= right distance)
            if (right >= array.length || left >= 0 && Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
                result[i] =  array[left--]; //move to left
            } else {
                result[i] =  array[right++]; //move to right
            }
        }

        return result;

    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(KClosestInSortedArray.kClosest(new int[]{1,4,4,6,9,9,12,15}, 2, 3)));
        System.out.println(Arrays.toString(KClosestInSortedArray.kClosest(new int[]{1,4,4,6,9,9,12,15}, 4, 3)));
        System.out.println(Arrays.toString(KClosestInSortedArray.kClosest(new int[]{1,4,4,6,9,9,12,15}, 7, 3)));
    }

}
