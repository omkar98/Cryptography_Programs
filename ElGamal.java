import java.util.*;
import java.lang.Math;

class ElGamal extends DiffieHellmanKeyExchange
{
  public static void main(String args[])
  {
    Scanner sc= new Scanner(System.in);
    System.out.println("You(Alice) wants to encrypt a message. Enter the message value: ");
    double msg = sc.nextLong();
    System.out.println("Help Bob by choosing a prime number and a primitive root: ");
    CryptSystem.p = sc.nextLong();
    CryptSystem.alpha = sc.nextLong();
    if(checkGCD() == 1)
    {
      int alicePrivateKey, bobPrivateKey;
      do {
        System.out.println("Enter private key value for Bob: ");
        bobPrivateKey = sc.nextInt();
      } while (!validatePrivateKey(bobPrivateKey));
      CryptSystem bob = new CryptSystem(bobPrivateKey);
      System.out.println("Values sent to Alice are:" + "("+bob.getPublicKey()+", "+CryptSystem.p+", "+CryptSystem.alpha+").");
      do {
        System.out.println("Enter random key value for Alice: ");
        alicePrivateKey = sc.nextInt();
      } while (!validatePrivateKey(alicePrivateKey));
      CryptSystem alice = new CryptSystem(alicePrivateKey);
      double K_ab = alice.generateSessionKey(bob.getPublicKey());
      double K_ba = bob.generateSessionKey(alice.getPublicKey());
      System.out.println("K_ab = "+ K_ab+ "K_ba = "+K_ba);
      double cipher_text = alice.encrypt_message(msg);
      System.out.println("Values generated by Alice are:" + "("+alice.getPublicKey() + ", "+ cipher_text+").");
      System.out.println("Decryted Message is :");
      System.out.println(bob.decrypt_message(alice.getPublicKey(), cipher_text));
    }
    else{
      System.out.println("The given alpha:"+CryptSystem.alpha+" is not a primitive prime of p:"+CryptSystem.p);
      System.out.println("Please enter valid values.");
      return;
    }
  }
}
//
// E:\8th Sem\CNS\cns_practicals>javac ElGamal.java
//
// E:\8th Sem\CNS\cns_practicals>java ElGamal
// You(Alice) wants to encrypt a message. Enter the message value:
// 26
// Help Bob by choosing a prime number and a primitive root:
// 29 2
// --------------- Extended Euclidean Algorithm---------------
//   q       r1      r2      s1      s2      t1      t2
// -----   -----   -----   -----   -----   -----   -----
//  14      02      01      00      01      01      -14
//  02      01      00      01      -2      -14     29
// ------------------------------------------------------------
// Enter private key value for Bob:
// 12
// Values sent to Alice are:(7.0, 29, 2).
// Enter random key value for Alice:
// 5
// K_ab = 16.0K_ba = 16.0
// Values generated by Alice are:(3.0, 10.0).
// Decryted Message is :
// --------------- Extended Euclidean Algorithm---------------
//   q       r1      r2      s1      s2      t1      t2
// -----   -----   -----   -----   -----   -----   -----
//  01      16      13      00      01      01      -1
//  01      13      03      01      -1      -1      02
//  04      03      01      -1      05      02      -9
//  03      01      00      05      -16     -9      29
// ------------------------------------------------------------
// 26.0