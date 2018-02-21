import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MyKnapsack {

    ArrayList<BigInteger> privateKey;
    ArrayList<BigInteger> publicKey;

    public MyKnapsack(int bits, int m, int n)
    {
        privateKey = new ArrayList<>();
        publicKey = new ArrayList<>();

        generateKeys(bits,m,n);
        printKeys();
    }

    public void generateKeys(int bits, int m, int n)
    {
        //Step 1 Generate superincreasing knapsack of size 4 bits
        int sum = 0;
        Random random = new Random();
        for(int i = 0; i < bits; i++)
        {
            //Always increases sum
            sum += sum + random.nextInt(10);
            privateKey.add(BigInteger.valueOf(sum));

            //Add to the public key, after adding the value of sum * m % n
            publicKey.add(BigInteger.valueOf((sum * m) % n));

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

    public static void main(String[] args)
    {
       MyKnapsack a = new  MyKnapsack(4,58,19);
    }


}
