package BinarySearchProblems;

public class FindFirstOccurrence {
    private static int firstOccur(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        //if (target > array[array.length - 1] || target < array[0]) return -1; --> not necessary

        int left = 0;
        int right = array.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target <= array[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (array[left] == target) {
            return left;
        }
        if (array[right] == target) {
            return right;
        }

        return -1;
    }

    private static int lastOccur(int[] array, int target) {
        if (array == null || array.length == 0) return -1;

        int start = 0, end = array.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target >= array[mid]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (array[end] == target) return end;
        if (array[start] == target) return start;
        return -1;

    }

    public static void main(String[] args) {
        System.out.println(FindFirstOccurrence.firstOccur(new int[]{3,4,5,6,6,9,16}, 6));
        System.out.println(FindFirstOccurrence.lastOccur(new int[]{3,4,5,6,6,9,16}, 6));
    }
}
