
public class BitWiseTest {
	
	
	public static void test() {
		  String s = "Hello";
		  StringBuilder t = new StringBuilder(s.length());
		  for (int i = 0; i < s.length(); i++) {
		    t.append((char) inc(s.charAt(i)));
		  }
		  System.out.println(s);
		  System.out.println(t.toString());
		}

		private static int inc(int x) {
		  // Check each bit 
		  for (int i = 0; i < Integer.SIZE; i++) {
		    // Examine that bit
		    int bit = 1 << i;
		    // If it is zero
		    if ((x & bit) == 0) {
		      // Set it to 1
		      x |= bit;
		      // And stop the loop - we have added one.
		      break;
		    } else {
		      // Clear it.
		      x &= ~bit;
		    }
		  }
		  return x;
		}

 		
   public static boolean isUniqueChars(String str) {
			int checker = 0;
			for (int i = 0; i < str.length(); ++i) {
				
				int val = (int)str.charAt(i) - (int)'a';
				
				int v1 = (1 << val);
				
				if ((checker & (1 << val)) > 0) return false;
				
				checker |= (1 << val);
			}

			return true;
		}
   
   public static boolean anagram(String s, String t) {
	   if (s.length() != t.length()) return false;
	   int[] letters = new int[256];
	   int num_unique_chars = 0;
	   int num_completed_t = 0;
	   char[] s_array = s.toCharArray();
	   for (char c : s_array) { // count number of each char in s.
		  
		   if (letters[c] == 0) ++num_unique_chars;
		   
		   ++letters[c];
	   }
	   for (int i = 0; i < t.length(); ++i) {
		   int c = (int) t.charAt(i);
		   if (letters[c] == 0) { // Found more of char c in t than in s.
			   return false;
		   }
		   --letters[c];
		   if (letters[c] == 0) {
			   ++num_completed_t;
			   if (num_completed_t == num_unique_chars) {
				   // it’s a match if t has been processed completely
				   return i == t.length() - 1;
			   }
		   }
	   }
	   return false;
   }
   
   public static int getMax(int a, int b) {
	    int c = a - b;
	    int k = (c >> 31) & 0x1;
	    int max = a - k * c;
	    return max;
	}
   
   
   public static String printBinary(String n) {
	   int intPart = Integer.parseInt(n.substring(0, n.indexOf(".")));
	   double decPart = Double.parseDouble(
			   n.substring(n.indexOf("."), n.length()));
	   String int_string = "";
	   while (intPart > 0) {
		   int r = intPart % 2;
		   intPart >>= 1;
				int_string = r + int_string;
	   }
	   StringBuffer dec_string = new StringBuffer();
	   while (decPart > 0) {
		   if (dec_string.length() > 32) return "-1";
		   if (decPart == 1) {
			   dec_string.append((int)decPart);
			   break;
		   }
		   double r = decPart * 2;
		   if (r >= 1) {
			   dec_string.append(1);
			   decPart = r - 1;
		   } else {
			   dec_string.append(0);
			   decPart = r;
		   }
	   }
	   return int_string + "." + dec_string.toString();
   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//getMax(5,10);
		printBinary("3.12");
		//anagram("Testing", "testing");
		//if (isUniqueChars("testing")) 
		//	System.out.println("True");
		//else
		//	System.out.println("false");
	}

}
