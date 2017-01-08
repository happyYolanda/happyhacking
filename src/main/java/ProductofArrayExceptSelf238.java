/**
 * Created by majingmj on 17-1-8.
 */
public class ProductofArrayExceptSelf238 {
    /**
     * 思路１
     * 使用移位操作代替除法
     * 要十分注意0的问题
     */
    public int BinaryDivide(int a, int b){
        boolean neg = (a > 0) ^ (b > 0);
        if(a < 0)
            a = -a;
        if(b < 0)
            b = -b;
        if(a < b)
            return 0;
        int msb = 0;
        //msd记录除数需要左移的位数
        for(msb = 0; msb < 32; msb++) {
            if((b << msb) >= a)
                break;
        }
        int q = 0; //记录每次除法的商
        for(int i = msb; i >= 0; i--) {
            if((b << i) > a)
                continue;
            q |= (1 << i);
            a -= (b << i);
        }
        if(neg)
            return -q;
        return q;
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
                nums[i] = BinaryDivide(product, nums[i]);
            }
        }
        return nums;
    }

    public static void main(String[] args){
        ProductofArrayExceptSelf238 test = new ProductofArrayExceptSelf238();
        System.out.println(test.BinaryDivide(-1,-1));
    }
}
