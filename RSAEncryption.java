import java.util.*;
import java.lang.Math;

class RSAEncryption extends ExtendedEuclidean
{
  public static ArrayList publicKeyCalculation(long p, long q)
  {
    long n = p * q;
    long phi = (p-1)*(q-1);
    System.out.printf("Step 3: Bob now computes N and phi(N) as : %d %d", n, phi);
    ArrayList e_values = findExponent(p,q,phi);
    System.out.println("Bob found the following values of 'e' valid: "+e_values);
    Scanner sc= new Scanner(System.in);
    long e=0;
    System.out.println("Step 4: Bob chooses one of the values for e.");
    do
    {
      System.out.println("Enter a value of \"e\" from the above list: ");
      e = sc.nextLong();
    }
    while(!e_values.contains(e));
    System.out.printf("Step 5: Bob's public key value is : (%d, %d)\n", n, e);
    ArrayList<Long> publicKey = new ArrayList<Long>(Arrays.asList(n,e));
    return publicKey;
  }
  public static void RSAEncryptionAlgorithm(long p, long q, long m)
  {
    System.out.printf("...Bob has to compute his public key...\n");
    ArrayList Bob_Public_Key = publicKeyCalculation(p, q);
    long n = (Long)Bob_Public_Key.get(0); long e = (Long)Bob_Public_Key.get(1);
    System.out.println("Step 6: Now Alice can encrypt her message");
    double cipher = Math.pow(m,e)%n;
    System.out.println("Encrypted Alices' Message: " + (int)cipher);
    ArrayList Bob_Private_Key = solveFor_GCD_S_T((p-1)*(q-1),e,0);
    double d = (Long)Bob_Private_Key.get(5);
    System.out.println("Step 7: Bob decrypts the message: ");
    double decrypt = Math.pow(cipher,d)%n;
    System.out.println("Bob obtains the following message: "+(int)decrypt);
  }
  public static ArrayList findExponent(long p, long q, long phi)
  {
    ArrayList<Long> E_values = new ArrayList<Long>();
    for(long e=2; e<phi; e++)
    {
      long values1 = (Long)solveFor_GCD_S_T(e,phi, 0).get(1);
      long values2 = (Long)solveFor_GCD_S_T(e-1,p-1, 0).get(1);
      long values3 = (Long)solveFor_GCD_S_T(e-1,q-1, 0).get(1);
      if (values1 == 1 && values2 == 2 && values3 == 2)
      {
        E_values.add(e);
      }
    }
    System.out.println(E_values);
    return E_values;
  }
  public static void main(String args[])
  {
    System.out.println("Initial Setup:");
    Scanner sc= new Scanner(System.in);
    System.out.println("Step 1: Bob selects a random prime number p and q: (Enter the values)");
    long p = sc.nextLong();
    long q = sc.nextLong();
    System.out.println("...Initial Setup Done...");
    System.out.println("Step 2: Alice has a message to Encrpyt. (Enter an integer)");
    long m = sc.nextLong();
    // findExponent(p,q,phi);
    RSAEncryptionAlgorithm(p,q, m);
  }
}
