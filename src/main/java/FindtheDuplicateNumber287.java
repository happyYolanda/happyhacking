/**
 * Created by majingmj on 17-1-8.
 */
public class FindtheDuplicateNumber287 {
    /**
     * 思路１
     * 时间复杂度是O(n2), 那就一个数一个数试
     */

    public int findDuplicate(int[] nums) {
        //先遍历一遍找出最大数和最小数
        int min = 1, max = 1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] < min){
                min = nums[i];
            }

            if (nums[i] > max){
                max = nums[i];
            }
        }
        //从min到max挨个试
        for (int i = min; i < max; i++){
            int times = 0;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] == i){
                    times++;
                }

                if (times >= 2){
                    return i;
                }
            }
        }

        return max;
    }

    public static void main(String[] args){

    }
}
