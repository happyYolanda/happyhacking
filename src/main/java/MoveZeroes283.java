/**
 * Created by majingmj on 17-1-8.
 */
public class MoveZeroes283 {
    /**
     * 思路
     * input: [0,1,0,3,12]
     * 保持非０顺序一致，那么就记录每次第一个0的位置, 并与非0的交换
     * [0,1,0,3,12]
     * [1,0,0,3,12]
     * [1,3,0,0,12] 交换时要从0后找第一个非0的作为下一个0的起始位置
     * [1,3,12,0,0]
     */

    public void moveZeroes(int[] nums) {
        int first = -1;
        int i = 0;
        while (i < nums.length){
            if (nums[i] != 0){
                if (first == -1) {
                    i++;
                } else {
                    nums[first] = nums[i];
                    nums[i] = 0;
                    for (int j = first + 1; j < nums.length; j++){
                        if (nums[first] != 0){
                            first = j;
                            break;
                        }
                    }
                    i++;
                }
            } else {
                if (first == -1){
                    first = i;
                }
                i++;
            }
        }
    }

    public static void main(String[] args){
        int[] input = {0,1,0,3,12};
        MoveZeroes283 moveZeroes283 = new MoveZeroes283();
        moveZeroes283.moveZeroes(input);
        for (int i = 0; i < input.length; i++){
            System.out.print(input[i] + "\t");
        }
        System.out.println();
    }
}
