package aesExam;

public class AesTest {
	 
	 /**
	  * @param args
	  */
	 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  try {
	   test();
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }
	 
	 
	 public static void test() throws Exception{
	  System.out.println("test");
	  
	  String key = "RescuedogManagement";       // key는 16자 이상 
	        Aes256 aes256 = new Aes256(key);
	         
	        String text = "test용 데이터";
	        String encText = aes256.aesEncode(text);
	        String decText = aes256.aesDecode(encText);
	         
	        System.out.println("암호화할 문자 : " + text);
	        System.out.println("암호화된 문자 : " + encText);
	        System.out.println("복호화된 문자 : " + decText);
	 }
	}