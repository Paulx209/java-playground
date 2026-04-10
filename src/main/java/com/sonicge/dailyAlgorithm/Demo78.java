package com.sonicge.dailyAlgorithm; // 声明当前类所在的包

import java.util.*;

public class Demo78 { // 定义 Demo78 类

    public static void main(String[] args) { // 程序入口方法

        Scanner scanner = new Scanner(System.in); // 创建 Scanner 对象，用来接收用户输入
        System.out.println("请输入数组的长度："); // 提示用户先输入数组长度
        int size = scanner.nextInt(); // 读取数组长度
        int[] nums = new int[size]; // 创建指定长度的整型数组
        System.out.println("请输入 " + size + " 个整数："); // 提示用户输入数组元素
        for (int i = 0; i < size; i++) { // 使用 for 循环依次读取每一个元素
            nums[i] = scanner.nextInt(); // 把当前输入的整数放到数组对应位置
        } // 结束读取数组元素的循环
        System.out.println("排序前的数组：" + Arrays.toString(nums)); // 输出排序前的数组内容
        quickSort(nums, 0, nums.length - 1); // 调用快速排序方法，对整个数组进行排序
        System.out.println("排序后的数组：" + Arrays.toString(nums)); // 输出排序后的数组内容
        scanner.close(); // 关闭 Scanner，释放资源
    } // main 方法结束

    public static void quickSort(int[] nums, int left, int right) { // 定义快速排序方法
        if (left >= right) { // 如果当前区间只有一个元素或没有元素，就不用再排了
            return; // 直接结束当前递归
        } // 结束递归终止条件判断
        int pivotIndex = partition(nums, left, right); // 先做一轮分区，并拿到基准值最终所在的位置
        quickSort(nums, left, pivotIndex - 1); // 递归排序基准值左边的部分
        quickSort(nums, pivotIndex + 1, right); // 递归排序基准值右边的部分
    } // quickSort 方法结束

    public static int partition(int[] nums, int left, int right) { // 定义分区方法，作用是确定基准值的位置
        int pivot = nums[right]; // 选择最右边的元素作为基准值
        int i = left - 1; // i 表示“小于等于基准值区域”的最后一个位置，开始时还没有这个区域
        for (int j = left; j < right; j++) { // j 从左到右扫描，直到基准值前一个位置
            if (nums[j] <= pivot) { // 如果当前元素小于等于基准值
                i++; // 先把小于等于区域向右扩一格
                swap(nums, i, j); // 再把当前这个较小的元素交换到左边区域里
            } // 结束当前元素与基准值的比较
        } // 结束整轮扫描
        swap(nums, i + 1, right); // 扫描结束后，把基准值放到它正确的位置
        return i + 1; // 返回基准值最终所在的下标
    } // partition 方法结束

    public static void swap(int[] nums, int i, int j) { // 定义交换方法，用来交换数组中的两个元素
        int temp = nums[i]; // 先暂存第一个位置的值
        nums[i] = nums[j]; // 把第二个位置的值放到第一个位置
        nums[j] = temp; // 再把暂存的值放回第二个位置
    } // swap 方法结束
} // Demo78 类结束
