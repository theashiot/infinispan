package org.infinispan.server.resp;

public class Util {
   private Util() { }

   /**
    * Checks if target is equal to expected. This method is case-insensitive and only works with ASCII characters.
    * The characters on expected must be in uppercase.
    *
    * @param expected: Upper case ASCII characters.
    * @param target: ASCII characters to verify.
    * @return true if target is equal to expected, false otherwise.
    */
   public static boolean isAsciiBytesEquals(byte[] expected, byte[] target) {
      if (expected.length != target.length) return false;

      for (int i = 0; i < expected.length; i++) {
         assert isAsciiUppercase(expected[i]) : "Expected byte is not uppercase ASCII";
         assert isAsciiChar(target[i]) : "Target byte is not ASCII";

         byte l = target[i];
         byte r = expected[i];
         if (l != r && l != (r - 32)) return false;
      }
      return true;
   }

   /**
    * Checks if target is equal to expected. This method is case-insensitive and only works with ASCII characters.
    * The expected char must be in uppercase.
    *
    * @param expected: Upper case ASCII character.
    * @param actual: ASCII character to verify.
    * @return true if actual is equal to expected, false otherwise.
    */
   public static boolean caseInsensitiveAsciiCheck(char expected, byte actual) {
      assert isAsciiUppercase((byte) expected) : "Expected byte is not uppercase ASCII";
      assert isAsciiChar(actual) : "Target byte is not ASCII";

      return expected == actual || expected == (actual - 32);
   }

   public static boolean isAsciiChar(byte b) {
      return isAsciiLowercase(b) || isAsciiUppercase(b);
   }

   public static boolean isAsciiUppercase(byte b) {
      return b >= 65 && b <= 90;
   }

   public static boolean isAsciiLowercase(byte b) {
      return b >= 97 && b <= 122;
   }
}
