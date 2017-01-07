import java.util.ArrayList;
import java.util.List;

/**
 * Created by majingmj on 17-1-7.
 */
public class FindAllDuplicatesinanArray442 {
    /**
     * 想法：
     * input: [4,3,2,7,8,2,3,1]
     * 延续FindAllNumbersDisappearedinanArray448的方法, 只是当发现重合时直接记录到result里
     *
     */

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length == 0){
            return result;
        }

        int index = 0;
        while (index < nums.length){
            if (index + 1 == nums[index] || nums[index] == -1){
                index++;
                continue;
            }

            int from = nums[index];
            int to = nums[from - 1];

            if (to == from){
                nums[index] = -1;
                result.add(from);//发现重复, 记录
                index++;
            } else {
                nums[index] = to;
                nums[from - 1] = from;
            }
        }

        return result;
    }

    public static void main(String[] args){
        int nums[] = {4,3,2,7,8,2,3,1};
        FindAllDuplicatesinanArray442 test = new FindAllDuplicatesinanArray442();
        List<Integer> result = test.findDuplicates(nums);
        for (Integer re : result){
            System.out.print(re + "\t");
        }
        System.out.println();
    }
}
