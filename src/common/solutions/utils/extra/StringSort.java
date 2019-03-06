package common.solutions.utils.extra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliza on 05.03.19.
 */
public class StringSort {

    public static void mergeSort(List<String> strings, int lowIndex, int highIndex){

        if (strings == null || lowIndex == highIndex)
            return;
        else {
            int midIndex = (lowIndex + highIndex) / 2;
            mergeSort(strings, lowIndex, midIndex);
            mergeSort(strings, midIndex + 1, highIndex);
            merge(strings, lowIndex, midIndex, highIndex);
        }

    }

    private static void merge(List<String> originalList, int lowIndex, int midIndex, int highIndex){

        List<String> leftPart = new ArrayList<>();
        List<String> rightPart = new ArrayList<>();

        for (int i = lowIndex; i <= midIndex; i++) {
            leftPart.add(originalList.get(i));
        }
        leftPart.add("zzzzzzzzzzzzzzzzz");

        for (int i = midIndex + 1; i <= highIndex; i++) {
            rightPart.add(originalList.get(i));
        }
        rightPart.add("zzzzzzzzzzzzzzzzz");

        // Sorts in increasing order
        int i = 0, j = 0;
        for (int k = lowIndex; k <= highIndex; k++) {
            if (compareStrings(leftPart.get(i), rightPart.get(j)) == -1
                    || compareStrings(leftPart.get(i), rightPart.get(j)) == 0){
                originalList.set(k, leftPart.get(i));
                i++;
            }
            else if (compareStrings(leftPart.get(i), rightPart.get(j)) == 1){
                originalList.set(k, rightPart.get(j));
                j++;
            }
        }

    }

    // Returns -1 if firstLine < secondLine
    private static Integer compareStrings(String firstLine, String secondLine){

        if (firstLine == null || secondLine == null)
            return null;

        int length = firstLine.length() < secondLine.length() ? firstLine.length() : secondLine.length();

        for (int i = 0; i < length; i++) {
            if (firstLine.charAt(i) > secondLine.charAt(i))
                return 1;
            else if (firstLine.charAt(i) < secondLine.charAt(i))
                return -1;
        }

        if (firstLine.length() == secondLine.length())
            return 0;
        if (firstLine.length() < secondLine.length())
            return -1;
        if (firstLine.length() > secondLine.length())
            return 1;

        return null;
    }

    /*
    public static void main(String[] args){

        List<String> testListOfStrings = new ArrayList<>();
        testListOfStrings.add("vgdt");
        testListOfStrings.add("dkkvg");
        testListOfStrings.add("Asddf");
        testListOfStrings.add("qww");
        testListOfStrings.add("asddf");
        testListOfStrings.add("Bjjfe");
        testListOfStrings.add("fjey");
        testListOfStrings.add("vjeyw");
        testListOfStrings.add("vjeywqnzx");
        testListOfStrings.add("vjeywqazx");

        mergeSort(testListOfStrings, 0, testListOfStrings.size() - 1);

        for(String element: testListOfStrings){
            System.out.println(element);
        }
    }
     */
}
