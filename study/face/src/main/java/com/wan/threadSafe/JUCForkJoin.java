package com.wan.threadSafe;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import com.wan.threadSafe.JUCForkJoin.MyTask;

// 数组排序
public class JUCForkJoin {

    public static class MyTask extends RecursiveTask<int[]>{
        private int[] nums;
        private int length;
        public MyTask(int[] nums){
            this.nums = nums;
            this.length = nums.length;
        }
        @Override
        protected int[] compute() {
            if(length > 2){
                int mid = length/2;
                int[] nums1 = Arrays.copyOf(nums, mid);;
                MyTask myTask1 = new MyTask(nums1);;
                int[] nums2 = Arrays.copyOfRange(nums, mid, length);
                MyTask myTask2 = new MyTask(nums2);
                myTask1.fork();
                myTask2.fork();
                nums1 = myTask1.join();
                nums2 = myTask2.join();
                int[] temp = new int[length];
                int nums1I = 0;
                int nums2I = 0;
                int tempI = 0;
                while (nums1I < nums1.length && nums2I < nums2.length) {
                    temp[tempI++] = nums1[nums1I] <= nums2[nums2I] ? nums1[nums1I++] : nums2[nums2I++];
                }
                while (nums1I < nums1.length) {
                    temp[tempI++] = nums1[nums1I++];
                }
                while (nums2I < nums2.length) {
                    temp[tempI++] = nums2[nums2I++];
                }
                return temp;
            } else if(length == 2){
                if(nums[0] > nums[1]){
                    int temp = nums[0];
                    nums[0] = nums[1];
                    nums[1] = temp;
                }
                return nums;
            } else{
                return nums;
            }
            
        }
    
        
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println();
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask myTask = new MyTask(nums);
        ForkJoinTask<int[]> submit = forkJoinPool.submit(myTask);
        try {
            int[] ans = submit.get();
            Arrays.toString(ans);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        sort(nums, 0, n - 1);
        
    }

    private static void sort(int[] nums, int a, int b) {
        if (b - a == 1) {
            if (nums[a] > nums[b]) {
                int temp = nums[a];
                nums[a] = nums[b];
                nums[b] = temp;
            }
        } else if (b - a > 1) {
            sort(nums, a, (a + b) / 2);
            sort(nums, (a + b) / 2 + 1, b);
            int i = a;
            int j = (a + b) / 2 + 1;
            int[] temp = new int[b - a + 1];
            int k = 0;
            while (j <= b && i <= (a + b) / 2) {
                if (nums[i] <= nums[j]) {
                    temp[k++] = nums[i];
                    i++;
                } else {
                    temp[k++] = nums[j];
                    j++;
                }
            }
            while (j <= b) {
                temp[k++] = nums[j++];
            }
            while (i <= (a + b) / 2) {
                temp[k++] = nums[i++];
            }
            System.arraycopy(temp, 0, nums, a, temp.length);
        }
    }
}
