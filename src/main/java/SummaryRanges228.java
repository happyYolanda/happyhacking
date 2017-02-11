import java.util.ArrayList;
import java.util.List;

/**
 * Created by majing on 2017/2/11.
 */
public class SummaryRanges228 {
    /**
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
     *
     * 思路: 从头到结尾，每次记录上一个数是什么(A)，以及上一个range是什么(B)，如果没连上，则是记录一个range为A-->B
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if (nums.length == 0){
            return result;
        } else {
            int A = nums[0];
            int B = nums[0];
            for (int i = 1; i < nums.length; i++){
                if (nums[i] == B + 1){
                    B = nums[i];
                    continue;
                }

                result.add(String.valueOf(A) + "->" + String.valueOf(B));
                A = nums[i];
                B = nums[i];
            }
            result.add(String.valueOf(A) + "->" + String.valueOf(B));
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {0,1,2,4,5,7};
        SummaryRanges228 summaryRanges228 = new SummaryRanges228();
        List<String> result = summaryRanges228.summaryRanges(nums);
        for (String s : result){
            System.out.println(s);
        }
    }
}
