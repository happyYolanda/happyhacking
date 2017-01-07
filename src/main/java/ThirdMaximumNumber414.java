import java.util.*;

/**
 * Created by majingmj on 17-1-7.
 */
public class ThirdMaximumNumber414 {
    /**
     * 想法１
     * 使用优先级队列
     * 错误：对2, 2, 3, 1这样的case无法解决重复问题, 如[2,2,3,1]
     */
    public int thirdMax_failed1(int[] nums) {
        Comparator<Integer> OrderIsdn =  new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                if (o1 > o2){
                    return -1;
                }
                if (o1 < o2){
                    return 1;
                }
                return 0;
            }
        };

        Queue<Integer> priorityQueue =  new PriorityQueue<Integer>(nums.length,OrderIsdn);
        for (int i = 0; i < nums.length; i++){
            priorityQueue.add(nums[i]);
        }

        if (priorityQueue.size() < 3){
            return priorityQueue.peek();
        } else {
            int A = priorityQueue.peek();
            priorityQueue.remove();
            int B = priorityQueue.peek();
            priorityQueue.remove();
            int C = priorityQueue.peek();
            priorityQueue.remove();

            if (A != B && A != C && B != C){
                return C;
            } else {
                return A;
            }
        }
    }

    /**
     * 想法2
     * 每遍历一个数与已有的前三名比较, 更新前三名数组
     * input: [2,2,3,1]
     * A: null
     * 1. [2]
     * 2.[2]
     * 3.[3,2]每次在前面插入, 如果数量超了则最后一个remove
     * 4.[3,2,1]
     *
     */

    public int thirdMax(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(Integer.MIN_VALUE);//哨兵
        for (int i = 0; i < nums.length; i++){
            int index = -1;
            for (int j = 0; j < result.size(); j++){
                if (result.get(j) < nums[i]){
                    index = j;
                    break;
                } else if (result.get(j) == nums[i]){//处理重复
                    break;
                }
            }

            if (index >= 0){
                result.add(index, nums[i]);
                if (result.size() > 4){
                    result.remove(result.size() - 1);
                }
            }
        }

        if (result.size() < 4){
            return result.get(0);
        } else {
            return result.get(2);
        }
    }


    public static void main(String[] args){
        int[] nums = {3, 2, 1};
        ThirdMaximumNumber414 test = new ThirdMaximumNumber414();
        System.out.println(test.thirdMax(nums));
    }
}
