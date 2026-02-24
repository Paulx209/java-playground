package com.sonicge.dailyAlgorithm;


import java.util.*;

/**
 *  【三数之和】
 *  给定一个数组（未经过排序的），然后你需要找出其中三个数字的和为0的组合，然后返回一个集合
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-24
 */

public class Demo22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++) {
            nums[i] = sc.nextInt();
        }
        List<List<Integer>> lists = threeSum(nums);
        for(List<Integer> part : lists){
            for(Integer num : part){
                System.out.print(num + " ");
            }
            System.out.println(" ");
        }
    }
    public static List<List<Integer>> threeSum(int[] nums){
        //1.先对集合进行排序
        Arrays.sort(nums);
        //2.开始遍历，控制一个index下标，然后找到对应的数组，添加到List中
        List<List<Integer>> res=  new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if( i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            while(left < right){
                int num1 = nums[left];
                int num2 = nums[right];
                if(num1 + num2 == -nums[i]){
                    List<Integer> part = List.of(nums[i], num1, num2);
                    res.add(part);
                    //这种情况可能会出现重复的问题
                    while(left<right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(left<right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(num1 + num2 > -nums[i]){
                    right--;
                }else{
                    left++;
                }
            }
        }
        //3.结果收集完成  返回
        return res;
    }
}
