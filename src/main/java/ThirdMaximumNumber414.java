import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by majingmj on 17-1-7.
 */
public class ThirdMaximumNumber414 {
    /**
     * 想法１
     * 使用优先级队列
     * 错误：对2, 2, 3, 1这样的case无法解决重复问题，　不简介
     */
    public int thirdMax(int[] nums) {
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
            priorityQueue.remove();
            priorityQueue.remove();
            return priorityQueue.peek();
        }
    }


    public static void main(String[] args){
        int[] nums = {2, 2, 3, 1};
        ThirdMaximumNumber414 test = new ThirdMaximumNumber414();
        System.out.println(test.thirdMax(nums));
    }
}
