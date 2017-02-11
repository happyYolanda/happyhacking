import java.util.ArrayList;
import java.util.List;

/**
 * Created by majing on 2017/2/11.
 */
public class CombinationSum39 {
    /**
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.
     * The same repeated number may be chosen from C unlimited number of times.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * For example, given candidate set [2, 3, 6, 7] and target 7,
     * A solution set is:
     * [
     * [7],
     * [2, 2, 3]
     * ]
     *
     * 思路:
     * 先将C排序，遍历所有可能
     * 最多3个2，2个3，1个6，1个7，存储在D:[3,2,1,1]
     * 搜索树类似:
     *                               a
     *                  1            2           3
     *                  b            b           b
     *               1    2       1     2     1     2
     *               c    c       c     c     c     c
     *               1    1       1     1     1     1
     *               d    d       d     d     d     d
     *               1    1       1     1     1     1
     * 剪枝条件，路径sum<=k
     * 用递归实现遍历
     * 传入[(3,2,0), (2,3,0), (1,6,0), (1,7,0)],sum,result
     * 当遍历(3,2)时会依次(3,2,1), (3,2,2), (3,2,3), 直到不满足剪枝条件
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //先做Triple数据
        List<Pair> pairs = new ArrayList<Pair>();
        for (int i = 0; i < candidates.length; i++){
            int num = target / candidates[i];
            Pair pair = new Pair(candidates[i], num);
            pairs.add(pair);
        }

        result = recursive(pairs, 0, result, new ArrayList<Integer>());
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
            if (tsum > sum){
                return result;
            }

            if (tsum == sum){
                result.add(tresult);
                return result;
            }
        }

        for (int i = 0; i < pairs.size(); i++){
            if (i < tempr.size()){
                continue;
            }

            for (int j = 0; j <= pairs.get(i).b; j++){
                tempr.add(j);
                result = recursive(pairs, sum, result, tempr);
                tempr.remove(j);
            }

            tempr.add(0);
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
}
