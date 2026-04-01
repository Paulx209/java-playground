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
