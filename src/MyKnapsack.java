import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MyKnapsack {

    ArrayList<BigInteger> privateKey;
    ArrayList<BigInteger> publicKey;
    // 0 3 14 32  : public key

    public MyKnapsack(int bits, int m, int n)
    {
        privateKey = new ArrayList<>();
        publicKey = new ArrayList<>();

        generateKeys(bits,m,n);
        printKeys();

        String binstring = "1100";
        int val = encrypt(binstring);
        decrypt(val,m,n);
    }

    public void generateKeys(int bits, int m, int n)
    {
        //Step 1 Generate superincreasing knapsack of size 4 bits
        int sum = 0;
        //To test against a uniform input we use the same seed
        Random random = new Random(42);
        for(int i = 0; i < bits; i++)
        {
            //Always increases sum
            sum += sum + random.nextInt(10);
            privateKey.add(BigInteger.valueOf(sum));

        }

        for(int i = 0; i < bits; i++)
        {
            //Add to the public key, after adding the value of sum * m % n
            publicKey.add(BigInteger.valueOf((privateKey.get(i).intValue() * m) % n));
        }


    }

    public void printKeys()
    {
        for (int i = 0; i < privateKey.size();i++)
        {
            System.out.print(privateKey.get(i) + " ");
        }

        System.out.println();

        for (int i = 0; i < publicKey.size();i++)
        {
            System.out.print(publicKey.get(i) + " ");
        }

    }

    public int encrypt(String binary)
    {
        int sum = 0;
        for(int i = 0; i < binary.length(); i++)
        {
            if(binary.substring(i,i+1).equals("1"))
            {
                sum += publicKey.get(i).intValue();
            }
        }

        return sum;
    }



    public void decrypt(int encoded, int m, int n)
    {

        int value = (encoded /m) % n;
        String finalVal = "";
        for(int i = privateKey.size() -1; i > -1; i--)
        {
            if(privateKey.get(i).intValue() <= value)
            {
                value = value - privateKey.get(i).intValue();
                finalVal += "1";
            }
            else
            {
                finalVal += "0";
            }
        }
        //Reverse String
        String temp = "";
        for(int i = finalVal.length() -1; i > -1; i--)
        {
            temp += finalVal.charAt(i);
        }
        System.out.println("");
        System.out.println("Passed in Encrypted Text: " + temp);
    }

    public static void main(String[] args)
    {
       MyKnapsack a = new  MyKnapsack(4,19, 83);
    }


}
