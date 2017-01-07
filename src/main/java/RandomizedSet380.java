import java.util.*;

/**
 * Created by majingmj on 17-1-7.
 */
public class RandomizedSet380 {
    /**
     * 思路
     * 跟381不同的是, 当发现是重复元素时, 不影响random的结果
     * 因此只需要一个map存放index与value的映射, 和一个map存放每次insert和remove的结果
     */

    Integer count;
    
    Map<Integer, Integer> randomMap;

    Map<Integer, Integer> valueMap;

    Random random;

    /** Initialize your data structure here. */
    public RandomizedSet380() {
        count = 0;
        randomMap = new HashMap<Integer, Integer>();
        valueMap = new HashMap<Integer, Integer>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valueMap.containsKey(val)){
            return true;
        }

        count++;
        valueMap.put(val, count);
        randomMap.put(count, val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valueMap.containsKey(val)){
            return false;
        }

        Integer index = valueMap.get(val);
        if (index == count){
            randomMap.remove(index);
        } else {
            valueMap.remove(val);
            Integer value = valueMap.get(count);
            randomMap.put(index, value);
            randomMap.remove(count);
            valueMap.put(value, index);
        }

        count--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        if (count > 0) {
            int randomIndex = random.nextInt(count);
            return randomMap.get(randomIndex + 1);
        }
        return -1;
    }
}
