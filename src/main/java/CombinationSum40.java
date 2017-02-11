import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by majing on 2017/2/11.
 */
public class CombinationSum40 {
    /**
     * 跟39的区别是输入可以有重复，输出不允许重复(除非输入重复了)
     * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     *
     * 思路:
     * 39中输入数据中每个数的个数改为根据candidate来
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //先做Triple数据
        List<Pair> pairs = new ArrayList<Pair>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < candidates.length; i++){
            if (map.containsKey(candidates[i])){
                int value = map.get(candidates[i]);
                map.put(candidates[i], value + 1);
            } else {
                map.put(candidates[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> m : map.entrySet()){
            Pair pair = new Pair(m.getKey(), m.getValue());
            pairs.add(pair);
        }
        //System.out.println();
        result = recursive(pairs, target, result, new ArrayList<Integer>());
        return result;
    }

    public List<List<Integer>> recursive(List<Pair> pairs, int sum, List<List<Integer>> result, List<Integer> tempr){
        //如果当前和已经超过sum, 则剪枝
        //如果当前和=sum, 加入到结果集中并不继续
        int tsum = 0;
        List<Integer> tresult = new ArrayList<Integer>();
        for (int i = 0; i < tempr.size(); i++){
            for (int j = 0; j < tempr.get(i); j++){
                tresult.add(pairs.get(i).a);
            }
            tsum = tsum + tempr.get(i) * pairs.get(i).a;
            if (tsum == sum){
                result.add(tresult);
                return result;
            }

            if (tsum > sum){
                return result;
            }
        }

        int i = tempr.size();
        if (i < pairs.size()) {
            for (int j = 0; j <= pairs.get(i).b; j++) {
                tempr.add(j);
                /*for (int k = 0; k < tempr.size(); k++) {
                    System.out.print(tempr.get(k) + "\t");
                }*/
                //System.out.println();
                result = recursive(pairs, sum, result, tempr);
                tempr.remove(tempr.size() - 1);
            }
        }

        return result;
    }

    public class Pair{
        public int a;//数字
        public int b;//个数
        public Pair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args){
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        CombinationSum40 combinationSum40 = new CombinationSum40();
        List<List<Integer>> result = combinationSum40.combinationSum2(candidates, target);
        System.out.println();
        for (int i = 0; i < result.size(); i++){
            for (int j = 0; j < result.get(i).size(); j++){
                System.out.print(result.get(i).get(j) + "\t");
            }
            System.out.println();
        }

    }
}
