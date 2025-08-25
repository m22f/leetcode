package com.wan;
import java.util.Scanner;

import com.wan.Solutions;
import com.wan.Solutions.Solution2;




public class Main {
    public static void main(String[] args) {
        System.out.println();
        Scanner in = new Scanner(System.in);
        Solutions solutions = new Solutions();
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < n; i++) {
        //     nums[i] = in.nextInt();
        // }
        // Solution0 s = solutions.new Solution0();
        // s.maxValue(nums);

        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // int target = in.nextInt();
        // Solutions.Solution1 solution1 = solutions.new Solution1();
        // solution1.twoSum(nums, target);
        
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
        }
        Solution2 solution2 = solutions.new Solution2();
        solution2.groupAnagrams2(strs);
    }
}