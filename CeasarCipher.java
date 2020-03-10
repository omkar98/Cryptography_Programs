class CeasarCipher extends Cipher
{
  public String encrypt(int key, String message)
  {
    System.out.println("Message: "+message+"\nKey: "+key);
    String encrypt_message="";
    for(int i=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=97 && c<=122) encrypt_message += (char)transformCiphers(alphaToInt(c),key,1,1);
      else encrypt_message+=c;
    }
    return encrypt_message;
  }
  public String decrypt(int key, String message)
  {
    System.out.println("Encrypted Message: "+message+"\nKey: "+key);
    String decrypt_message="";
    for(int i=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=65 && c<=90) decrypt_message += (char)transformCiphers(alphaToInt(c),key,2,2);
      else  decrypt_message+=c;
    }
    return decrypt_message;
  }
  public void bruteForce(String message)
  {
    System.out.println("Brute Force technique on Caesar Cipher");
    System.out.println("\t"+message);
    System.out.println("Key"+"\t"+"Decryted Message");
    for(int j=1; j<26; j++)
    {
      String decrypt_message="";
      for(int i=0; i<message.length(); i++)
      {
        char c = message.charAt(i);
        if (c>=65 && c<=90)
        {
          int current = message.charAt(i) - 'A';
          int transform = current - (j%26);
          int decrypt;
          if (transform<0) decrypt = 123 + transform;
          else decrypt = 97 + (transform%26);
          decrypt_message+=((char)decrypt);
        }
        else  decrypt_message+=c;
      }
      System.out.println(j+"\t"+decrypt_message);
    }
  }
//Driver Function
  public static void main(String args[])
  {
    int key=7;
    String plain_text = "tomorrow is a comman-off!";
    CeasarCipher a = new CeasarCipher();
    String encrypt_message = a.encrypt(key, plain_text);
    System.out.println("Encrypted Message: "+ encrypt_message);
    String decrypt_message = a.decrypt(key, encrypt_message);
    System.out.println("Decrypted Message: "+ decrypt_message);
    System.out.println("-------------------------");
    a.bruteForce(encrypt_message);
  }
}
