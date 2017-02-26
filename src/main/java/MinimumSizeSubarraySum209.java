/**
 * Created by majing on 2017/2/23.
 */
public class MinimumSizeSubarraySum209 {
    /**
     * Given an array of n positive integers and a positive integer s,
     * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     * For example, given the array [2,3,1,2,4,3] and s = 7,
     * the subarray [4,3] has the minimal length under the problem constraint.
     *
     * 思路：寻找O(n)时间的
     * 2=2
     * 2,3=5
     * 2,3,1=6
     * 2,3,1,2=8----result=4
     * 3,1,2=6
     * 3,1,2,4=10----result=4
     * 1,2,4=7-----result=3
     * 2,4=6
     * 2,4,3=9-----result=3
     * 4,3=7-----result=7
     *
     * 依次遍历，累加，每次记录收尾位置，超过s后开始从首部往下减，知道小于s, 再往前遍历
     */

    public int minSubArrayLen(int s, int[] nums) {
        int begin = 0;
        int end = 0;
        int sum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++){
            //System.out.println(i);
            sum += nums[i];
            end = i;

            while (sum >= s && end >= begin){
                if (sum >= s && (result == 0 || end - begin + 1 < result)){
                    result = end - begin + 1;
                }
                sum -= nums[begin];
                begin++;
            }

            //System.out.println(result);
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {2,3,1,2,4,3};
        MinimumSizeSubarraySum209 minimumSizeSubarraySum209 = new MinimumSizeSubarraySum209();
        System.out.println(minimumSizeSubarraySum209.minSubArrayLen(7, nums));
    }
}
