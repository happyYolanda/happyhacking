import java.util.ArrayList;
import java.util.List;

/**
 * Created by majingmj on 17-1-7.
 */
public class FindAllNumbersDisappearedinanArray448 {
    /**
     * 想法1：
     * input: [4,3,2,7,8,2,3,1]
     * 如果全都有, 可能是 A: [1,2,3,4,5,6,7,8]
     * 把input的每个位置看成一个桶, 要把input按照A的顺序摆放, B: [1,2,3,4, , ,7,8]
     * 遍历一遍B可知不存在哪个元素
     * 摆放方法:
     * [7,3,2,4,8,2,3,1]
     * [3,3,2,4,8,2,7,1]
     * [2,3,3,4,8,2,7,1]
     * [3,2,3,4,8,2,7,1]
     * [-1,2,3,4,8,2,7,1]
     * [-1,2,3,4,1,2,7,8]
     * [1,2,3,4,-1,2,7,8]
     * [1,2,3,4,-1,-1,7,8]
     * 遍历其中-1为没出现过的
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
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
                index++;
            } else {
                nums[index] = to;
                nums[from - 1] = from;
            }
        }

        for (int i = 0; i < nums.length; i++){
            if (nums[i] == -1){
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int nums[] = {4,3,2,7,8,2,3,1};
        FindAllNumbersDisappearedinanArray448 test = new FindAllNumbersDisappearedinanArray448();
        List<Integer> result = test.findDisappearedNumbers(nums);
        for (Integer re : result){
            System.out.print(re + "\t");
        }
        System.out.println();
    }
}
