

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!



public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   public boolean TrueAssert(boolean statement, String test_case) {
	   if (statement) {
		   return true;
	   }
	   else {
		   System.out.println("TrueAssert failed: " + test_case);
		   return false;
	   }
   }
   
   public boolean FalseAssert(boolean statement, String test_case) {
	   if (statement) {
		   System.out.println("FalseAssert failed: " + test_case);
		   return false;
	   }
	   else {
		   return true;
	   }
   }
   
   
   public void testManualTest()
   {
	   //You can use this function to implement your manual testing	   
	   //UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	      //assertTrue(urlVal.isValid("http://www.google.com"));
	      //assertTrue(urlVal.isValid("http://www.google.com/"));
       //assertTrue(urlValidator.isValid("http://example.rocks/"));
       //assertTrue(urlValidator.isValid("http://example.rocks"));
	   
   }
   
   
   public void testRandomTest()
   {
	 //You can use this function to implement your Random testing	   

   }
   
   
   public void testProgrammingTest()
   {
	   //You can use this function for programming based testing

       UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   TrueAssert(urlValidator.isValid("http://www.google.com"), "http");
       TrueAssert(urlValidator.isValid("https://www.google.com"), "https");
       
	   /* for (int i = 0; i < SchemeTests.length; i++) {
		   if (SchemeTests[i].valid) {
			   FalseAssert(urlValidator.isValid(SchemeTests[i].item), SchemeTests[i].item);
		   }
		   else {
			   TrueAssert(urlValidator.isValid(SchemeTests[i].item), SchemeTests[i].item);
		   }
	   } */

   }
   
   /* ResultPair[] SchemeTests = {
		   new ResultPair("http://google.com", true),
		   new ResultPair("http://www.google.com", true),
           new ResultPair("ftp://www.google.com", true),
           new ResultPair("h3t://www.google.com", true),
           new ResultPair("3ht://www.google.com", false),
           new ResultPair("http:/www.google.com", false),
           new ResultPair("http:www.google.com", false),
           new ResultPair("http/www.google.com", false),
           new ResultPair("://www.google.com", false)
   }; */
   


}
