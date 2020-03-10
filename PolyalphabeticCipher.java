class PolyalphabeticCipher extends Cipher
{
  public String encrypt(String key, String message)
  {
    int k_length = key.length();
    String encrypt_message="";
    for(int i=0, j=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=97 && c<=122) encrypt_message += (char)transformCiphers(alphaToInt(c), alphaToInt(key.charAt(j++%k_length)),1,1);
      else encrypt_message += c;
    }
    return encrypt_message;
  }
  public String decrypt(String key, String message)
  {
    int k_length = key.length();
    String decrypt_message="";
    for(int i=0, j=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=65 && c<=90) decrypt_message += (char)transformCiphers(alphaToInt(c), alphaToInt(key.charAt(j++%k_length)),2,2);
      else decrypt_message += c;
    }
    return decrypt_message;
  }
//Driver Function
  public static void main(String args[])
  {
    String plain_text = "she is listening";
    String key="PASCAL";
    PolyalphabeticCipher a=new PolyalphabeticCipher();
    String cipher_text = a.encrypt(key, plain_text);
    System.out.println("Plain Text:\t" + plain_text+"\n"+"Key:\t\t"+key+"\n"+"Cipher Text:\t"+cipher_text);
    String decrypted_text = a.decrypt(key,cipher_text);
    System.out.println("Cipher Text:\t"+cipher_text+"\nKey:\t"+key+"\n"+"Decrypted Text:\t" + decrypted_text);

  }
}
