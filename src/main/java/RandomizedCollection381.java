import java.util.*;

/**
 * Created by majingmj on 17-1-7.
 */
public class RandomizedCollection381 {
    /**
     * 思路１
     * 能够在O(1)里取到值, 只能是map
     * 可以是Map<Integer, Integer>, 其中key是添加进去的数字, value是个数, 添加是加1, 删去时减1
     * 随机取值概率平等:  1, 2, 2, 3, 4, 5, 5, 5, 6 取到2的概率C(2,1)/C(9,1)=2/9
     * 也就是随机数(1~9), 取到2,3时都会映射到2
     * 因此上面map有如下映射1,2,2,3,4,5,5,5,6~1,2,3,4,5,6,7,8,9
     * 构造映射方法：
     * 存储数组[1,2,1,1,3,1]为每个数对应的个数, 当随机数选择了3, 会依次寻找1, 1 + 2 = 3, 因此是2
     * 当随机数选择8, 1+2+1+1+3=8, 因此是5, 但是这样不是O(1)
     * 可以考虑构造一个map：
     * 1~1
     * 2,3~2
     * 4~3
     * 5~4
     * 6,7,8~5
     * 9~6
     * 当再添加一个元素5时
     * 5,10~4
     * 随机数选到10, 会找到4
     * 现在要解决找到这些元素的问题：
     * 假设插入顺序为5,3,7,3,4,5, 过程如下(两个Map分别为存数map和存映射的map)：
     * map<5,1>  map<1,5>
     * map<3,1>  map<2,3>
     * map<7,1>  map<3,7>
     * map<3,2>  map<4,3>
     * map<4,1>  map<5,4>
     * map<5,2>  map<6,5>
     * 但这样有一个问题，删除3时,
     * map<3,1>  把map<2,3>变成map<2,5> 但是没有一个映射知道3对应的是这边的map2, 因此第一个map的value应该是映射
     * 也就是说
     * map<5,Set<1,6>> map<1,5>
     * map<3,Set<2,4>> map<2,3>
     * map<7,Set<3>> map<3,7>
     * ............ map<4,3>
     * map<4,Set<5>> map<5,4>
     * ............ map<6,5>
     * 删除3时
     * map<3,Set<4>> 右边删除map<6,5>, map<2,3>-->map<2,5>, 左边map<5,Set<1,6>>-->map<5,Set<1,2>>
     * 删除7时
     * 删除map<7,Set<3>>, 右边删除map<5,4>,map<3,7>-->map<3,4>, 左边map<4,Set<5>>-->map<4,Set<3>>
     * 通常如果删除的是最后一个则不用改了
     * 取随机数时，选择一个随机数，然后可以直接取到值
     */

    Integer count;

    Map<Integer, Set<Integer>> valueMap;

    Map<Integer, Integer> randomMap;

    Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection381() {
        count = 0;
        valueMap = new HashMap<Integer, Set<Integer>>();
        randomMap = new HashMap<Integer, Integer>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = false;
        count++;
        if (valueMap.containsKey(val)){
            flag = true;
            valueMap.get(val).add(count);
        } else {
            HashSet<Integer> temp = new HashSet<Integer>();
            temp.add(count);
            valueMap.put(val, temp);
        }
        randomMap.put(count, val);

        return flag;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!valueMap.containsKey(val)){
            return false;
        }

        Integer index = -1;//index是val对应的random索引
        for (Integer i : valueMap.get(val)){
            index = i;
            break;
        }
        valueMap.get(val).remove(index);
        if (valueMap.get(val).size() == 0){//如果删除索引后不存在了，要把这个值删掉
            valueMap.remove(val);
        }

        if (index == count){
            randomMap.remove(index);
        } else {
            Integer newvalue = randomMap.get(count);//newvalue是count索引对应的值
            randomMap.put(index, newvalue);
            randomMap.remove(count);
            if (valueMap.get(newvalue) != null) {//这里如果不加条件限制会有bug, 从原理上没懂为什么
                valueMap.get(newvalue).remove(count);//newvalue原先对应的random索引是count, 现在改为index
                valueMap.get(newvalue).add(index);
            }
        }

        count--;
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        if (count > 0) {//注意count=0或负数的bug
            int randomIndex = random.nextInt(count);
            return randomMap.get(randomIndex + 1);
        }
        return -1;
    }

    public static void main(String[] args){
        RandomizedCollection381 collection = new RandomizedCollection381();
        collection.insert(1);
        collection.insert(1);
        collection.insert(2);
        collection.getRandom();
        collection.remove(1);
        System.out.println(collection.getRandom());
    }
}
