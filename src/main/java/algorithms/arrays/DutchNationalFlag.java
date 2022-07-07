package algorithms.arrays;

import java.util.Arrays;

public class DutchNationalFlag {
    public static void main(String[] args) {

        int[] input = {0, 0, 2, 2, 2, 1, 1, 0, 0, 2, 1, 2, 2, 0, 0, 1};
        DutchNationalFlag flag = new DutchNationalFlag();

        int[] sortedArray = flag.sortArray(input);
        Arrays.stream(sortedArray).forEach(System.out::println);
    }

    public int[] sortArray(int[] input) {
        int low = 0;
        int high = input.length - 1;
        int mid = low;

        while (mid <= high) {
            switch (input[mid]) {
                case 0: {
                    // swap element at low index with this mid-element
                    int temp = input[low];
                    input[low] = input[mid];
                    input[mid] = temp;
                    low++;
                    mid++;
                    break;
                    // we do mid++ because we know that we have considered
                    // the element at index low, as low is always less than mid
                }

                case 1: {
                    // just increase the mid value
                    mid++;
                    break;
                }
                case 2: {
                    // swap the mid with high index element
                    int temp = input[high];
                    input[high] = input[mid];
                    input[mid] = temp;
                    high--;
                    // we should not do mid++ here because we haven't checked the new mid-index element
                }
            }
        }

        return input;
    }
}
