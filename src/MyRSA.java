import java.awt.*;
import java.math.BigInteger;
import java.util.Scanner;

public class MyRSA {

    BigInteger n;
    BigInteger phi;
    BigInteger p;
    BigInteger q;
    BigInteger e;
    BigInteger d; //private key
    byte[] encryptedMessage;
    byte[] decryptedMessage;

    public MyRSA(int p1, int p2,int e)
    {
        n = BigInteger.valueOf(p1 * p2);
        phi = BigInteger.valueOf((p1 -1) * (p2-1));
        p = BigInteger.valueOf(p1);
        q = BigInteger.valueOf(p2);
        this.e = BigInteger.valueOf(e);
        d = phi.multiply(new BigInteger("2")).divide(this.e);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message to encrypt");
        String message = scanner.nextLine();

        encrypt(message);

    }

    public void encrypt(String message)
    {
        BigInteger temp = new BigInteger(message);
        temp = temp.modPow(n,e);
        System.out.println("Encrypted byte array: ");
        printByteArray(temp.toByteArray());
        encryptedMessage = temp.toByteArray();
    }

    public void decrypt()
    {
        if(encryptedMessage == null)
        {
            System.out.println("There is no message that has been encrypted");
        }
        else
        {
            BigInteger temp = new BigInteger(encryptedMessage);
            temp = temp.modPow(d,n);
            System.out.println("Decrypted byte array: ");
            printByteArray(temp.toByteArray());
            decryptedMessage = temp.toByteArray();
        }
    }

    public void printByteArray(byte[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        MyRSA myRSA = new MyRSA(11,3,3);
        myRSA.encrypt("Hello");
        myRSA.decrypt();
    }
}
