package BinarySearchProblems;

public class BinarySearch {
    private static int binarySearch0(int[] array, int target) {
        if (array == null || array.length == 0)  return -1;
        int start = 0, end = array.length - 1;
        while (start <= end) { // <= (after while loop, start - end == 1,  last while loop start == end and will check array[start/end], no need post-process
            int mid = start + (end - start) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearch1(int[] array, int target) {
        if (array == null || array.length == 0)  return -1;
        int start = 0, end = array.length - 1;
        while (start < end) {  // < (after while loop, start == end,  last while loop start + 1 == end and will check array[start], hasn't array[end]), need post-process
            int mid = start + (end - start) / 2;
            if (target == array[mid]) {
                return mid;
            } else if (target > array[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (array[start] == target) {
            return start;
        }
        return -1;
    }

    private static int binarySearch2(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        while (start + 1 < end) { // + 1 <  (right after while loop, start + 1 == end, array[start] and array[end] haven't been checked, need post-process
            int mid = start + (end - start)/2;
            if (target < array[mid])
                end = mid;
            else if (target > array[mid])
                start = mid;
            else
                return mid;
        }
        if (array[start] == target)
            return start;
        if (array[end] == target)
            return end;
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(BinarySearch.binarySearch0(new int[]{3,4,5,6,6,9,16}, 5));
        System.out.println(BinarySearch.binarySearch1(new int[]{3,4,5,6,6,9,16}, 5));
        System.out.println(BinarySearch.binarySearch2(new int[]{3,4,5,6,6,9,16}, 5));
    }

}
