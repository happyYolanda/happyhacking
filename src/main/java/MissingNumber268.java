/**
 * Created by majingmj on 17-1-8.
 */
public class MissingNumber268 {
    /**
     * 思路1
     * [2,3,6,7,1,4,0]少了5
     * 上面的和是23, 如果不少5和是(1+7)*7/2=28, 差了5
     * 因此求和, 差多少就少谁
     */

    public int missingNumber(int[] nums) {
        int max = nums.length;
        int sum1 = (1 + max) * max / 2;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i++){
            sum2 += nums[i];
        }
        return sum1 - sum2;
    }

    public static void main(String[] args){

    }
}
