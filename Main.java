
//given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
//Return the minimum number of intervals required to complete all tasks.
//        Constraints:
//        1 <= tasks.length <= 10^4
//tasks[i] is an uppercase English letter.
//0 <= n <= 100
// AAABBB, n=2 , Output = 8
// AAABBBCD, n=3 , Output = 10

import java.util.HashMap;

public class Main {

    public static void decrementArray(int[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i]--;
        }
    }
    public static int findFirstNonZero(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return i;
            }
        }
        return arr.length - 1;
    }

    public static int findFirstNonZeroWhereGapIsZero(int[] gap, int[] arr) {
        for (int i = 0; i < gap.length; i++) {
            if (gap[i] == 0 && arr[i] > 0) {
                return i;
            }
        }
        return -1;
    }
    public static int schedule(String str, int interval){
        int count = 0;
        char[] chars = str.toCharArray();
        int[] counts = new int[26];
        int[] gap = new int[26];
        int start = 0;
        int total = 0;
        HashMap<Character, Integer> scheduleMap = new HashMap<>();
        for (char c : chars) {
            counts[(int)(c - 'A')]++;
            total+=1;
        }
        while(total > 0){
            int indexToSchedule = findFirstNonZero(counts);

            if(gap[indexToSchedule] != 0) {
                indexToSchedule = findFirstNonZeroWhereGapIsZero(gap, counts);
                if(indexToSchedule == -1){
                    start++;
                    count++;
                    decrementArray(gap);
                    total--;
                    continue;
                }
            }

            System.out.print(""+(char)('A'+indexToSchedule)+"|");
            counts[indexToSchedule]--;
            gap[indexToSchedule]= interval;

            if(start != 0){
                start++;
                decrementArray(gap);
            }
            start++;
            count++;
            total--;
        }

        return count;
    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Total : " + schedule("AAABBB", 2));
    }
}

