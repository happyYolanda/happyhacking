import java.util.ArrayList;
import java.util.List;

/**
 * Created by majing on 2017/2/11.
 */
public class CombinationSum216 {
    /**
     * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can
     * be used and each combination should be a unique set of numbers.
     *
     * Example 1:
     * Input: k = 3, n = 7
     * Output:
     * [[1,2,4]]
     *
     * Example 2:
     * Input: k = 3, n = 9
     * Output:
     * [[1,2,6], [1,3,5], [2,3,4]]
     *
     * 思路:
     * 跟39一样，只是自己做Pair数据, 而且要做一个限制，就是数字总数不能超过k
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        //先做Triple数据
        List<Pair> pairs = new ArrayList<Pair>();
        for (int i = 1; i <= 9; i++){
            Pair pair = new Pair(i, 1);
            //System.out.println(pair.a + "\t" + pair.b);
            pairs.add(pair);
        }
        //System.out.println();
        result = recursive(pairs, n, result, new ArrayList<Integer>(), k);
        return result;
    }

    public List<List<Integer>> recursive(List<Pair> pairs, int sum, List<List<Integer>> result,
                                         List<Integer> tempr, int cnt){
        //如果当前和已经超过sum, 则剪枝
        //如果当前和=sum, 加入到结果集中并不继续
        int tsum = 0;
        int tcnt = 0;
        List<Integer> tresult = new ArrayList<Integer>();
        for (int i = 0; i < tempr.size(); i++){
            for (int j = 0; j < tempr.get(i); j++){
                tresult.add(pairs.get(i).a);
            }
            tsum = tsum + tempr.get(i) * pairs.get(i).a;
            tcnt += tempr.get(i);
            if (tcnt > cnt){
                return result;
            }

            if (tsum == sum && tcnt == cnt){
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
                result = recursive(pairs, sum, result, tempr, cnt);
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
        CombinationSum216 combinationSum39 = new CombinationSum216();
        List<List<Integer>> result = combinationSum39.combinationSum3(3, 9);
        System.out.println();
        for (int i = 0; i < result.size(); i++){
            for (int j = 0; j < result.get(i).size(); j++){
                System.out.print(result.get(i).get(j) + "\t");
            }
            System.out.println();
        }

    }
}
