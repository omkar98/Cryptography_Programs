import java.util.*;
import java.lang.Math;

class DiffieHellmanKeyExchange extends ExtendedEuclidean
{
  public static int checkGCD()
  {
    ArrayList values = solveFor_GCD_S_T(CryptSystem.p,CryptSystem.alpha,1);
    if ((Long)values.get(1) == 1) return 1;
    else return 0;
  }
  public static boolean validatePrivateKey(int privateKey)
  {
    if(privateKey>1 && privateKey<CryptSystem.p-1) return true;
    else return false;
  }
  public static void main(String args[])
  {
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter a prime number and primitive root of the given prime number: ");
    CryptSystem.p = sc.nextLong();
    CryptSystem.alpha = sc.nextLong();
    if(checkGCD() == 1)
    {
      int alicePrivateKey, bobPrivateKey;
      do {
        System.out.println("Enter private key values for Alice and Bob: ");
        alicePrivateKey = sc.nextInt();
        bobPrivateKey = sc.nextInt();
      } while (!(validatePrivateKey(alicePrivateKey) && validatePrivateKey(bobPrivateKey)));
      CryptSystem alice = new CryptSystem(alicePrivateKey);
      CryptSystem bob = new CryptSystem(bobPrivateKey);
      System.out.println(alice.getPublicKey());
      System.out.println(bob.getPublicKey());
      double K_ab = alice.generateSessionKey(bob.getPublicKey());
      double K_ba = bob.generateSessionKey(alice.getPublicKey());
      System.out.println("K_ab = "+ K_ab+ "K_ba = "+K_ba);
    }
    else{
      System.out.println("The given alpha:"+CryptSystem.alpha+" is not a primitive prime of p:"+CryptSystem.p);
      System.out.println("Please enter valid values.");
      return;
    }
  }
}

// OUTPUT
// Enter a prime number and primitive root of the given prime number:
// 131 14
// --------------- Extended Euclidean Algorithm---------------
//   q       r1      r2      s1      s2      t1      t2
// -----   -----   -----   -----   -----   -----   -----
//  09      14      05      00      01      01      -9
//  02      05      04      01      -2      -9      19
//  01      04      01      -2      03      19      -28
//  04      01      00      03      -14     -28     131
// ------------------------------------------------------------
// Enter private key values for Alice and Bob:
// 4 7
// 33.0
// 31.0
// K_ab = 102.0K_ba = 102.0
