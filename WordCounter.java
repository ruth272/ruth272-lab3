// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
   public WordCounter() {
   }

   public static int processText(StringBuffer var0, String var1) throws InvalidStopwordException, TooSmallText {
      int var2 = 0;
      boolean var3 = false;
      Pattern var4 = Pattern.compile("[a-zA-Z0-9']+");
      Matcher var5 = var4.matcher(var0);

      while(var5.find()) {
         ++var2;
         System.out.println("Word found: " + var5.group());
         if (var1 != null && var5.group().equalsIgnoreCase(var1)) {
            var3 = true;
            break;
         }
      }

      if (var1 == null) {
         return var2;
      } else if (!var3) {
         throw new InvalidStopwordException(var1);
      } else if (var2 < 5) {
         throw new TooSmallText(var2);
      } else {
         return var2;
      }
   }

   public static StringBuffer processFile(String var0) throws EmptyFileException {
      StringBuffer var1 = new StringBuffer(var0);
      boolean var2 = false;

      while(!var2) {
      }

      if (var0 == null) {
         throw new EmptyFileException(var1);
      } else {
         return var1;
      }
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      WordCounter var2 = new WordCounter();
      int var3 = 0;

      while(var3 != 1 && var3 != 2) {
         System.out.println("Choose an option: \n1. Process a file \n2. Process text");

         try {
            var3 = Integer.parseInt(var1.nextLine());
            if (var3 != 1 && var3 != 2) {
               System.out.println("Invalid choice, please enter 1 or 2.");
            }
         } catch (NumberFormatException var20) {
            System.out.println("Invalid input, please enter a number.");
         }
      }

      String var4 = var0.length > 0 ? var0[0] : "";
      String var5 = var0.length > 1 ? var0[1] : null;
      int var6 = 0;
      if (var3 == 1) {
         try {
            StringBuffer var7 = processFile(var4);
            var6 = processText(var7, var5);
         } catch (EmptyFileException var17) {
            System.out.println(var17.getMessage());

            try {
               var6 = processText(new StringBuffer(""), var5);
            } catch (InvalidStopwordException var15) {
               System.out.println(var15.getMessage());
            } catch (TooSmallText var16) {
               System.out.println(var16.getMessage());
            }
         } catch (InvalidStopwordException var18) {
            System.out.println(var18.getMessage());
            System.out.println("Please enter a valid stopword:");
            var5 = var1.nextLine();

            try {
               var6 = processText(new StringBuffer(var4), var5);
            } catch (InvalidStopwordException var13) {
               System.out.println("Stopword not found after retry.");
            } catch (TooSmallText var14) {
               System.out.println(var14.getMessage());
            }
         } catch (TooSmallText var19) {
            System.out.println(var19.getMessage());
         }
      } else {
         try {
            var6 = processText(new StringBuffer(var4), var5);
         } catch (InvalidStopwordException var11) {
            System.out.println(var11.getMessage());
            System.out.println("Please enter a valid stopword:");
            var5 = var1.nextLine();

            try {
               var6 = processText(new StringBuffer(var4), var5);
            } catch (InvalidStopwordException var9) {
               System.out.println("Stopword not found after retry.");
            } catch (TooSmallText var10) {
               System.out.println(var10.getMessage());
            }
         } catch (TooSmallText var12) {
            System.out.println(var12.getMessage());
         }
      }

      if (var6 >= 5) {
         System.out.println("Word count: " + var6);
      }

      var1.close();
   }
}
