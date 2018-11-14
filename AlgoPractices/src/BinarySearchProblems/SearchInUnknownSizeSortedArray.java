package BinarySearchProblems;

class Dictionary{
    Integer[] array;
    Dictionary(Integer[] array){
        this.array = array;
    }
    Integer get(int index){
        if (index > array.length) {
            return null;
        } else {
            return array[index];
        }
    };
}

public class SearchInUnknownSizeSortedArray {
    public static int search(Dictionary dict, int target) {
        // Write your solution here
        if (dict == null) {
            return -1;
        }
        int left = 0, right = 1;
        // find the target range: 如果没到头，并且target还比right小，range扩大两倍
        while (dict.get(right) != null && dict.get(right) < target) {               // <------- 注意 range * 2 条件
            left = right;
            right = right * 2;
        }
        //after finding the range, do binary search(may still include nulls)
        return biSearch(dict, left, right, target);
    }
    private static int biSearch(Dictionary dict, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null || target < dict.get(mid)) {                 // <-------  注意 mid 可能还是 null
                right = mid - 1;
            } else if (target > dict.get(mid)) {
                left = mid + 1;
            } else {
                return mid;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(SearchInUnknownSizeSortedArray.search(new Dictionary(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}), 9));
    }

}
