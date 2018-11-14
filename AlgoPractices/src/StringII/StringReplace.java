package StringII;
/*
    Solution 1:
    create a StringBuilder to put(append) result.
    use pointer "fromIndex" to read input string and when find s, write t in the result, jump pointer to s length

    Solution 2:
    use char[]
    2 cases:
    replace shorter
    replace longer, need to pre-calculate the result length

    **************************************************************
    * IF do replace longer in place: need to do backward!!!
    * IF do replace longer in a char array, no need to do backward
    **************************************************************

 */

import java.util.ArrayList;

public class StringReplace {

    // Solution 1: Use StringBuilder *********************************************************
    public static String replace(String input, String s, String t) {
        //Assumption: input, s and t are not null, s is not empty string
        if (input.length() == 0) return input;

        StringBuilder sb = new StringBuilder();
        int fromIndex = 0;
        int matchIndex = input.indexOf(s, fromIndex); //find s's start index in input, searching part is after fromIndex

        while(matchIndex != -1) { //if not find: return -1
            sb.append(input, fromIndex, matchIndex);
            sb.append(t);
            fromIndex = matchIndex + s.length();
            matchIndex = input.indexOf(s, fromIndex);
        }

        sb.append(input, fromIndex, input.length()); // append input[fromIndex, endIndex) in sb

        return sb.toString();
    }

    /*
    API summary:

    1. input.indexOf(str, fromIndex) --- find str start index in input, searching part is after fromIndex:
        if find out: return str start index
        if not find: return -1

    2. result.append(input, fromIndex, endIndex)  --- append result input[fromIndex, endIndex)
    */

    // Solution 2: Not use StringBuilder ******************************************************
    public static String replaceShorter(char[] in, String s, String t) {
        int slow = 0; // all content before slow (exclusive) will be kept/return
        int fast = 0;

        while (fast < in.length) {
            if (fast + s.length() < in.length && equalString(in, fast, s)) {
                copyString(in, slow, t);
                fast += s.length();
                slow += t.length();

            } else {
                in[slow] = in[fast];
                slow++;
                fast++;
            }
        }
        return new String(in, 0, slow);
    }

    public static String replaceLonger(char[] in, String s, String t) {
        //we need a longer array, so need to calculate the length we need
        ArrayList<Integer>  matches = getAllMatches(in, s);
        //System.out.println(matches);
        char[] result = new char[in.length + matches.size() * (t.length() - s.length())];

        //In order to not recklessly lose data, do backwards!!!
        int resultIndex = result.length - 1;
        int inputIndex = in.length - 1;
        int m = matches.size() - 1;
        while (inputIndex >= 0  && resultIndex >= 0) {
            if (m >= 0 && inputIndex == matches.get(m)) { //had a bug here: don't forget m < matches.size()
                copyString(result, resultIndex - t.length() + 1, t);
                m--;
                resultIndex -= t.length();
                inputIndex -= s.length();
            }
            else {
                result[resultIndex--] = in[inputIndex--];
            }
        }
        return new String(result);

    }

    //find all s in String input and return last index in ArrayList
    public static ArrayList<Integer> getAllMatches(char[] in, String s) {
        ArrayList<Integer> lastIndexes = new ArrayList<>();

        int i = 0;
        while (i <= in.length - s.length()) {
            if (equalString(in, i, s)) {
                lastIndexes.add(i + s.length() - 1);
                i += s.length();
            } else {
                i++;
            }
        }
        return lastIndexes;
    }

    //copy String t into char array "in" starting from index "start"
    public static void copyString(char[] in, int start, String t) {
        for (int i = 0; i < t.length(); i++) {
            in[start + i] = t.charAt(i);
        }
    }


    // Assumption: fromIndex + s.length() < input.length()
    public static  boolean equalString(char[] in, int fromIndex, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (in[fromIndex + i] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }


    // main ***************************************************************************
    public static void main(String[] args) {
        System.out.println(StringReplace.replace("student", "den", "XXXX"));
        System.out.println(StringReplace.replaceShorter("student".toCharArray(), "den", "XX"));
        System.out.println(StringReplace.replaceLonger("student".toCharArray(), "den", "XXXXX"));
        //System.out.println(StringReplace.getAllMatches("studentStudent".toCharArray(), "den"));
    }

}


