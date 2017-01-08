/**
 * Created by majingmj on 17-1-8.
 */
public class ProductofArrayExceptSelf238 {
    /**
     * 思路１
     * 使用移位操作代替除法
     * 要十分注意0的问题
     */
    public int Div(int x, int y)//利用二进制的性质,减小加减次数，但是乘法次数增加
    {
        int ans = 0;
        while(x >= y)
        {
            int mul = 1;
            while(y*mul <= x)
                mul <<= 1;
            mul >>=1;//上一步多左移了一位，需要右移补回
            ans += mul;
            x -= mul*y;
        }
        return ans;
    }

    public int[] productExceptSelf(int[] nums) {
        int product = 1;
        int time = 0;//计算0出现的次数
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 0 && time == 0){
                time++;
                continue;
            } else if (nums[i] == 0 && time == 1){
                time++;
                product = 0;
                break;
            }
            product *= nums[i];
        }

        for (int i = 0; i < nums.length; i++){
            if (time == 2){
                nums[i] = 0;
            } else if (time == 1){
                if (nums[i] == 0){
                    nums[i] = product;
                } else {
                    nums[i] = 0;
                }
            } else {
                nums[i] = Div(product, nums[i]);
            }
        }
        return nums;
    }

    public static void main(String[] args){
        ProductofArrayExceptSelf238 test = new ProductofArrayExceptSelf238();
        System.out.println(test.Div(-1,-1));
    }
}
