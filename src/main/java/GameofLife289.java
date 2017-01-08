import java.util.ArrayList;
import java.util.List;

/**
 * Created by majingmj on 17-1-8.
 */
public class GameofLife289 {
    /**
     * 想法1:
     * 更新原则：
     * (1) 活cell: 周围活cell<2, 变dead
     * (2) 活cell: 周围活cell=2或3, 继续live
     * (3) 活cell: 周围活cell>3, 变dead
     * (4) 死cell: 周围活cell=3, 变live
     * 对于一维:
     * [1,0,0,0,1,0] 从左到右更新时只要记住前面那个更新前是什么就可以, 同理一列也是
     * 对于二维:
     * [1,0,0,1,0]
     * [1,1,0,1,1]
     * [0,1,1,0,1]
     * 先建立一个矩阵, 每个位置记录当前状态和周围状态, 整体记录一遍后再更新(三元组分别表示当前状态, 周围1的个数, 周围是2的个数)
     * [(1,2,1), (0,3,3).....]
     *
     * 题目中的Follow up 2, 没理解什么意思
     */
    public class TwoTuple<A, B> {
        public final A first;
        public final B second;

        public TwoTuple(A a, B b) {
            this.first = a;
            this.second = b;
        }
    }

    public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
        public final C third;

        public ThreeTuple(A a, B b, C c) {
            super(a, b);
            this.third = c;
        }
    }

    public void gameOfLife(int[][] board) {
        List<List<ThreeTuple<Integer, Integer, Integer>>> states = new ArrayList
                <List<ThreeTuple<Integer,Integer,Integer>>>();

        for (int i = 0; i < board.length; i++){
            List<ThreeTuple<Integer, Integer, Integer>> state = new ArrayList
                    <ThreeTuple<Integer, Integer, Integer>>();
            for (int j = 0; j < board[i].length; j++){
                int live = 0;
                int dead = 0;
                //左上
                if (i - 1 >= 0 && j - 1 >= 0){
                    if (board[i - 1][j - 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //正上
                if (i - 1 >= 0){
                    if (board[i - 1][j] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //右上
                if (i - 1 >= 0 && j + 1 < board[i - 1].length){
                    if (board[i - 1][j + 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //左边
                if (j - 1 >= 0){
                    if (board[i][j - 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //右边
                if (j + 1 < board[i].length){
                    if (board[i][j + 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //左下
                if (i + 1 < board.length && j - 1 >= 0){
                    if (board[i + 1][j - 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //正下
                if (i + 1 < board.length){
                    if (board[i + 1][j] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                //右下
                if (i + 1 < board.length && j + 1 < board[i + 1].length){
                    if (board[i + 1][j + 1] == 0){
                        dead++;
                    } else {
                        live++;
                    }
                }
                state.add(new ThreeTuple<Integer, Integer, Integer>(board[i][j], live, dead));
            }
            states.add(state);
        }

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 1){
                    if (states.get(i).get(j).second < 2 || states.get(i).get(j).second > 3){
                        board[i][j] = 0;
                    }
                } else {
                    if (states.get(i).get(j).second == 3){
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args){
    }
}
