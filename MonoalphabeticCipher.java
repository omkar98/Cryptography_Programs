import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays; // import the Array

class MonoalphabeticCipher
{
  public String encrypt(String message, ArrayList newCharSet)
  {
    System.out.println("Message: "+message);
    String encrypt_message="";
    for(int i=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=97 && c<=122)
      {
        int current = message.charAt(i) - 'a';
        char encrypt = (char)newCharSet.get(current);
        encrypt_message+=((char)encrypt);
      }
      else encrypt_message+=c;
    }
    return encrypt_message;
  }
  public String decrypt(String message, ArrayList newCharSet)
  {
    System.out.println("Encrypted Message: "+message);
    String decrypt_message="";
    for(int i=0; i<message.length(); i++)
    {
      char c = message.charAt(i);
      if (c>=97 && c<=122)
      {
        int current = newCharSet.indexOf(message.charAt(i));
        int decrypt =  97+current;
        decrypt_message+=((char)decrypt);
      }
      else  decrypt_message+=c;
    }
    return decrypt_message;
  }
//Driver Function
  public static void main(String args[])
  {
    ArrayList<Character> newCharSet = new ArrayList<Character>(
              Arrays.asList('p', 'o', 'i', 'u', 'y', 't', 'r', 'e', 'w', 'q', 'l', 'k', 'j', 'h', 'g', 'f', 'd', 's', 'a', 'm', 'n', 'b', 'v', 'c', 'x', 'z'));
    String plain_text = "give me all your practicals";
    MonoalphabeticCipher a = new MonoalphabeticCipher();
    String encrypt_message = a.encrypt(plain_text, newCharSet);
    System.out.println("Encrypted Message: "+ encrypt_message);
    String decrypt_message = a.decrypt(encrypt_message, newCharSet);
    System.out.println("Decrypted Message: "+ decrypt_message);
  }
}

// https://www.geeksforgeeks.org/arraylist-in-java/
