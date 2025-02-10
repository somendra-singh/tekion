
//given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.
//Return the minimum number of intervals required to complete all tasks.
//        Constraints:
//        1 <= tasks.length <= 10^4
//tasks[i] is an uppercase English letter.
//0 <= n <= 100
// AAABBB, n=2 , Output = 8
// AAABBBCD, n=3 , Output = 10

import java.util.*;

public class MainFixed {

    public static void printArray(int[] arr) {
        for (int ch : arr) {
            if (ch == 0)
                System.out.print("_|");
            else
                System.out.print((char)ch + "|");
        }
        System.out.println();
    }

    public static int getCount(int[] arr){
        for(int i = arr.length-1; i > 0; i--){
            if(arr[i]!=0){
                return i+1;
            }
        }
        return 0;
    }

    public static int findZeroIndex(int[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i]==0){
                return i;
            }
        }
        return 0;
    }

    public static int schedule(String str, int interval){
        char[] chars = str.toCharArray();
        int[] schedule = new int[str.length() * interval];

        //Sorting the array
        Arrays.sort(chars);

        char previousChar = 'a';
        int indexAtWhichNextCharToBePlaced, previousIndex = 0;
        for(int i=0; i < chars.length; i++){
            if(chars[i] == previousChar){
                indexAtWhichNextCharToBePlaced = previousIndex + interval +1;
            }
            else {
                indexAtWhichNextCharToBePlaced = findZeroIndex(schedule);
            }
            schedule[indexAtWhichNextCharToBePlaced] = previousChar = chars[i];
            previousIndex = indexAtWhichNextCharToBePlaced;
//            printArray(schedule);
        }
        printArray(schedule);
        return getCount(schedule);
    }

    public static void main(String[] args) {
        System.out.println("Total : " + schedule("AAABBB", 2));
        System.out.println("Total : " + schedule("AAABBBCD", 3));
    }
}

