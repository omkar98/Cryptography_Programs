//We assume that plain_int is always lowercase
//We assume that CipherInt is always uppercase
class Cipher
{
  public int alphaToInt(char c)
  {
    int intChar;
    if (c>=97 && c<=122) intChar = c - 'a';
    else if (c>=65 && c<=90) intChar = c - 'A';
    else intChar=(int)c;
    return intChar;
  }
  //Status:
    // 0 - Returns the alphabet number
    // 1 - Returns uppercase alphabet ASCII Value
    // 2 - Returns  lowercase alphabet ASCII Value
  //Type:
    // 1 - Converts Plain int to Cipher int (Encryption)
    // 2 - Converts Cipher int to Plain int (Decryption)
  public int transformCiphers(int int_value, int key, int status, int type)
  {
    int new_value;
    if(type==1) new_value = int_value + (key%26);
    else new_value = int_value - (key%26);
    if (new_value<0) new_value = 26 + new_value;
    if (status==0) return new_value%26;
    else if (status == 1) return 65 + (new_value%26);//uppercase
    else if (status == 2) return 97 + (new_value%26);//lowercase
    else return -1;
  }
}
