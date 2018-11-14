package IntervalFreqProblem;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;


/**
 * n --- truck quantity (number of input tuples)
 * time: O(nlog(n))
 * space: O(n)
 *
 */


public class IntervalFreq {
    public static List<Interval> generateFreqList(List<Tuple> input) {
    //public static void generateFreqList(List<Tuple> input) {
        List<Year> years = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();

        // corner case
        if (input == null || input.size() == 0) {
            return intervals;
        }

        // add start time and end time in the input tuples into 2 separate lists
        List<Integer> startYears = new ArrayList<>();
        List<Integer> endYears = new ArrayList<>();

        for (Tuple t : input) {
            startYears.add(t.start);
            endYears.add(t.end);
        }

        // sort these two lists, time is O(nlogn), in which n is # of trucks(# of tuples)
        Collections.sort(startYears);
        Collections.sort(endYears);

        // calculate # of truck for each year, time is O(n)
        int n = startYears.size(); // totalTruckNum
        int i = 0, j = 0;
        int curTruckNum = 0;

        while (i < n && j < n) {
            int sy = startYears.get(i);
            int ey = endYears.get(j);
            if (sy < ey) {
                curTruckNum++;
                if (years.size() > 1) {
                    if (years.get(years.size() - 1).year != sy) {
                        years.add(new Year(sy, curTruckNum));
                    } else {
                        years.get(years.size() - 1).truckNum = curTruckNum;
                    }
                } else {
                    years.add(new Year(sy, curTruckNum));
                }
                i++;
            } else if (sy > ey) {
                curTruckNum--;
                if (years.size() > 1) {
                    if (years.get(years.size() - 1).year != ey) {
                        years.add(new Year(ey, curTruckNum));
                    } else {
                        years.get(years.size() - 1).truckNum = curTruckNum;
                    }
                }
                j++;

            } else {
                if (years.size() > 1) {
                    if (years.get(years.size() - 1).year != ey) {
                        years.add(new Year(ey, curTruckNum));
                    }
                }
                i++;
                j++;
            }
        }

        while (i < n) {
            int sy = startYears.get(i);
            curTruckNum++;
            if (years.size() > 1) {
                if (years.get(years.size() - 1).year != sy) {
                    years.add(new Year(sy, curTruckNum));
                } else {
                    years.get(years.size() - 1).truckNum = curTruckNum;
                }
            }
            i++;
        }

        while (j < n) {
            int ey = endYears.get(j);
            curTruckNum--;
            if (years.size() > 1) {
                if (years.get(years.size() - 1).year != ey) {
                    years.add(new Year(ey, curTruckNum));
                } else {
                    years.get(years.size() - 1).truckNum = curTruckNum;
                }
            } else {
                years.add(new Year(ey, curTruckNum));
            }
            j++;
        }

        // convert Year to Interval
        for (int k = 0; k < years.size() - 1; k++) {
            intervals.add(new Interval(years.get(k).year, years.get(k + 1).year, years.get(k).truckNum));
        }

        return intervals;
    }


    //print top k
    private static void topK(List<Interval> intervals, int k) {
        //corner case
        if (intervals.size() == 0 || k < 0) {
            return;
        }
        // sorting by value in decreasing order
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.value == i2.value) return 0;
                return i1.value > i2.value ? -1 : 1;
            }
        } );
        System.out.println("Most: " + intervals.get(0).startYear + ", truck quantity: " + intervals.get(0).value);
        System.out.println("Top " + k + ":");
        int index = 0;
        int printYear = intervals.get(index).startYear;
        while (k > 0 && index < intervals.size()) {
            if (printYear < intervals.get(index).endYear) {
                System.out.print("(" + printYear + ", " + intervals.get(index).value + ") ");
                printYear++;
                k--;
            } else {
                if (index + 1 >= intervals.size()) {
                    System.out.print("not enough years");
                    break;
                }
                printYear = intervals.get(++index).startYear;
            }
        }
        System.out.println();
    }

    //print least k
    private static void leastK(List<Interval> intervals, int k) {
        //corner case
        if (intervals.size() == 0 || k < 0) {
            return;
        }
        // sort truck quantity in increasing order
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                if (i1.value == i2.value) return 0;
                return i1.value < i2.value ? -1 : 1;
            }
        } );

        System.out.println("Least: " + intervals.get(0).startYear + ", truck quantity: " + intervals.get(0).value);
        System.out.println("Least " + k + ":");
        int index = 0;
        int printYear = intervals.get(index).startYear;
        while (k > 0 && index >= 0) {
            if (printYear < intervals.get(index).endYear) {
                System.out.print("(" + printYear + ", " + intervals.get(index).value + ") ");
                printYear++;
                k--;
            } else {
                if (index - 1 < 0) {
                    System.out.print("not enough years");
                    break;
                }
                printYear = intervals.get(++index).startYear;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Tuple> testInput = new ArrayList<>();

        //testInput.add(new Tuple(1900, 1901));
        //testInput.add(new Tuple(1800, 2000));

        testInput.add(new Tuple(1900, 1950));
        testInput.add(new Tuple(1910, 1940));
        testInput.add(new Tuple(1905, 1960));
        testInput.add(new Tuple(1960, 1980));

        List<Interval> intervals = IntervalFreq.generateFreqList(testInput);
        topK(intervals, 5);
        leastK(intervals, 3);

    }

}




