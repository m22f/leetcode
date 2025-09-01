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
        // int n = in.nextInt();
        // String[] strs = new String[n];
        // for (int i = 0; i < n; i++) {
        //     strs[i] = in.next();
        // }
        // Solution2 solution2 = solutions.new Solution2();
        // solution2.groupAnagrams2(strs);
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // Solutions.Solution3 solution3 = solutions.new Solution3();
        // solution3.longestConsecutive(nums);
        // int n = in.nextInt();
        // int[] height = new int[n];
        // for (int i = 0; i < height.length; i++) {
        //     height[i] = in.nextInt();
        // }
        // Solutions.Solution5 solution5 = solutions.new Solution5();
        // solution5.maxArea(height);
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // Solutions.Solution6 solution6 = solutions.new Solution6();
        // solution6.threeSum(nums);
        // int n = in.nextInt();
        // int[] height = new int[n];
        // for (int i = 0; i < height.length; i++) {
        //     height[i] = in.nextInt();
        // }
        // Solutions.Solution7 solution7 = solutions.new Solution7();
        // solution7.trap1(height);
        // String s = in.next();
        // Solutions.Solution8 solution8 = solutions.new Solution8();
        // solution8.lengthOfLongestSubstring(s);
        // String s = in.next();
        // String p = in.next();
        // Solutions.Solution9 solution9 = solutions.new Solution9();
        // solution9.findAnagrams(s, p);
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // int k = in.nextInt();
        // Solutions.Solution10 solution10 = solutions.new Solution10();
        // solution10.subarraySum1(nums, k);
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // int k = in.nextInt();
        // Solutions.Solution11 solution11 = solutions.new Solution11();
        // solution11.longestSubarray(nums, k);
        // int n = in.nextInt();
        // int[] chargeTimes = new int[n];
        // int[] runningCosts = new int[n];
        // for (int i = 0; i < chargeTimes.length; i++) {
        //     chargeTimes[i] = in.nextInt();
        // }
        // for (int i = 0; i < runningCosts.length; i++) {
        //     runningCosts[i] = in.nextInt();
        // }
        // long budget = in.nextInt();
        // Solutions.Solution11 solution11 = solutions.new Solution11();
        // solution11.maximumRobots(chargeTimes, runningCosts, budget);
        
        // int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // int k = in.nextInt();
        // Solutions.Solution11 solution11 = solutions.new Solution11();
        // solution11.shortestSubarray(nums, k);
        // String s = in.next();
        // String t = in.next();
        // Solutions.Solution12 solution12 = solutions.new Solution12();
        // solution12.minWindow(s, t);

        // 以下部分为动态规划相关
        Solutions.SolutionDP solutionDP = solutions.new SolutionDP();
        int n = in.nextInt();
        // int[] nums = new int[n];
        // for (int i = 0; i < nums.length; i++) {
        //     nums[i] = in.nextInt();
        // }
        // solutionDP.rob(nums);
        solutionDP.numSquares(n);


    }
}