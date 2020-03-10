import java.util.*;
import java.lang.Math;
class CryptSystem
{
  static long p, alpha;
  private double privateKey;
  public double publicKey;
  public double sessionKey;
  CryptSystem(int privateKey)
  {
    this.privateKey = privateKey;
    this.publicKey = Math.pow(alpha,this.privateKey)%p;
  }
  public double generateSessionKey(double otherPublicKey)
  {
    this.sessionKey = Math.pow(otherPublicKey, this.privateKey)%p;
    return this.sessionKey;
  }
  public double encrypt_message(double message)
  {
    return (message*this.sessionKey)%p;
  }
  public double decrypt_message(double otherPublicKey, double message)
  {
    ArrayList values = ExtendedEuclidean.solveFor_GCD_S_T((long)this.sessionKey,CryptSystem.p,1);
    long inverse = ExtendedEuclidean.findInverse((long)values.get(5),(long)this.sessionKey,CryptSystem.p);
    double plain_text = (double)((message*inverse)%CryptSystem.p);
    return plain_text;
  }
  public double getPrivateKey()
  {
    return this.privateKey;
  }
  public double getPublicKey()
  {
    return this.publicKey;
  }
}
