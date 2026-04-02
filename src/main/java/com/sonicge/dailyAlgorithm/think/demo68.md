写一下二分法的模板：

1.找一个目标值

    /**
     * 找到对应的目标值
     * @param nums
     * @param target
     * @return
     */
    public static int findTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


2.找第一个满足条件的  

这里为什么要left < right呢？由于mid == target之后，我们也不能够确定该mid就是符合条件的值，所以我们要对左右不断逼近，逼近到left == right之后，答案就出来了，那此时就不需要二分法了，因此left == right就是while循环的结束条件
    
    /**
     * 找第一个满足条件的值
     * @param nums
     * @param target
     * @return
     */
    public static int findMinTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

3.找最后一个满足条件的值

这里在计算mid的时候为什么要多加一个1，是因为我们在处理left right的时候，有可能遇到无限循环的情况，(0+1)/2，一直计算0

    /**
     * 找最后一个满足条件的值
     * @param nums
     * @param target
     * @return
     */
    public static int findMaxTarget(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
