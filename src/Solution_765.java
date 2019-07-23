import java.util.Arrays;

/**
 * Author  :   ChenKang
 * Time    :   2019/7/22
 * Info    :    情侣牵手
 */

class Solution_765 {

    public static void main(String[] args) {
        Solution_765 s = new Solution_765();
        int[] row = {10,7,4,2,3,0,9,11,1,5,6,8};
        s.minSwapsCouples(row);
    }

    public int search(int i,int[] row)
    {
        for(int k = 0; k < row.length; k++)
        {
            if(row[i]%2==1)
            {
                if(row[k]==row[i]-1)
                {
                    return k;
                }
            }
            else
            {
                if(row[k]==row[i]+1)
                {
                    return k;
                }
            }
        }

        return -1;
    }
    public int minSwapsCouples(int[] row) {
        int cut = 0;
        for(int i = 0; i < row.length; i+=2)
        {
            if(row[i]%2==1 && row[i]-1==row[i+1] || row[i]%2==0 && row[i]+1==row[i+1])
            {
                continue;
            }
            else
            {
                int t = search(i,row);

                int temp = row[i+1];
                row[i+1] = row[t];
                row[t] = temp;
                cut ++;
            }
        }
        return cut;
    }
}