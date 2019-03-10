

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!



public class UrlValidatorTest1 extends TestCase {


   public UrlValidatorTest1(String testName) {
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
   
   public void ErrorCatch(Error e, String test_case) {
	   System.out.println("Error caught (" + e + "): " + test_case);
   }
   
   
   
   public void testProgrammingTest()
   {
	   //You can use this function for programming based testing

       UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
       String url;
       boolean valid;
       int num_errors = 0;
       int num_failed_asserts = 0;
       int num_tests = 0;
       boolean result;
       
       for (int i = 0; i < SchemeTests.length; i++) {
    	   for (int j = 0; j < AuthorityTests.length; j++) {
    		   for (int k = 0; k < PortTests.length; k++) {
    			   for (int l = 0; l < PathTests.length; l++) {
    				   for (int m = 0; m < QueryTests.length; m++) {
			    		   url = SchemeTests[i].item + AuthorityTests[j].item + PortTests[k].item + PathTests[l].item + QueryTests[m].item;
			    		   valid = SchemeTests[i].valid && AuthorityTests[j].valid && PortTests[k].valid && PathTests[l].valid && QueryTests[m].valid;
			    		   num_tests++;
				    	   try {
				    		   if (valid) {
				    			   result = TrueAssert(urlValidator.isValid(url), url);
				    		   }
				    		   else {
				    			   result = FalseAssert(urlValidator.isValid(url), url);
				    		   }
				    		   if (!result) {
				    			   num_failed_asserts++;
				    		   }
				    	   } catch (Error e) {
				    		   ErrorCatch(e, url);
				    		   num_errors++;
				    	   }
    				   }
    			   }
    		   }
    	   }
       }
       System.out.println("Testing complete, " + num_tests + " tests. " + num_failed_asserts + " failed asserts, " + num_errors + " errors caught.");
       System.out.println(((100 - (num_failed_asserts + num_errors) * 100 / num_tests)) + "% successful.");

   }
   
   /* ResultPair[] SchemeTests = {
		   new ResultPair("http://google.com", true),
		   new ResultPair("https://google.com", true),
           new ResultPair("ftp://google.com", true),
           new ResultPair("h3t://google.com", true),
           new ResultPair("3ht://google.com", false),
           new ResultPair("http:/google.com", false),
           new ResultPair("http:google.com", false),
           new ResultPair("http/google.com", false),
           new ResultPair("://google.com", false)
   }; */
   
   ResultPair[] SchemeTests = {
		   new ResultPair("http://", true),
           new ResultPair("ftp://", true),
           new ResultPair("h3t://", true),
           new ResultPair("3ht://", false),
           new ResultPair("http:/", false),
           new ResultPair("http:", false),
           new ResultPair("http/", false),
           new ResultPair("://", false)
   };
   
   ResultPair[] AuthorityTests = {
		   new ResultPair("www.google.com", true),
           new ResultPair("www.google.com.", true),
           new ResultPair("go.com", true),
           new ResultPair("go.au", true),
           new ResultPair("0.0.0.0", true),
           new ResultPair("255.255.255.255", true),
           new ResultPair("256.256.256.256", false),
           new ResultPair("255.com", true),
           new ResultPair("1.2.3.4.5", false),
           new ResultPair("1.2.3.4.", false),
           new ResultPair("1.2.3", false),
           new ResultPair(".1.2.3.4", false),
           new ResultPair("go.a", false),
           new ResultPair("go.a1a", false),
           new ResultPair("go.cc", true),
           new ResultPair("go.1aa", false),
           new ResultPair("aaa.", false),
           new ResultPair(".aaa", false),
           new ResultPair("aaa", false),
           new ResultPair("", false)
   };
   
   ResultPair[] PortTests = {
		   new ResultPair(":80", true),
           new ResultPair(":65535", true), // max possible
           new ResultPair(":65536", false), // max possible +1
           new ResultPair(":0", true),
           new ResultPair("", true),
           new ResultPair(":-1", false),
           new ResultPair(":65636", false),
           new ResultPair(":999999999999999999", false),
           new ResultPair(":65a", false)
   };
   
   ResultPair[] PathTests = {
		   new ResultPair("/test1", true),
           new ResultPair("/t123", true),
           new ResultPair("/$23", true),
           new ResultPair("/..", false),
           new ResultPair("/../", false),
           new ResultPair("/test1/", true),
           new ResultPair("", true),
           new ResultPair("/test1/file", true),
           new ResultPair("/..//file", false),
           new ResultPair("/test1//file", false)
   };
   
   ResultPair[] QueryTests = {
		   new ResultPair("?action=view", true),
           new ResultPair("?action=edit&mode=up", true),
           new ResultPair("", true)
   };
   


}
