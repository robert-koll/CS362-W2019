

import junit.framework.TestCase;
import java.util.Random;
import java.util.Date;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!



public class UrlValidatorTest extends TestCase {

	// String array and lengths are defined for the various parts
	// of the URL. These are set as global variables in case they
	// were needed to be accessed by anything else and for ease
	// of editing the content.
	String[] schemesValid = new String[] {"http://", "https://", "ftp://"};
	private static final int schemesValidLength = 3;
	String[] schemesInvalid = new String[] {"http:/", "http:///", "ftp:/", "ftp:///", "https:/", "https:///", "qxr://", "www://"};
	private static final int schemesInvalidLength = 8;
	String[] authorityValid = new String[] {"www.google.com", "google.com", "1.1.1.1", "139.130.4.5"};
	private static final int authorityValidLength = 4;
	String[] authorityInvalid = new String[] {"1.2.3.4.5", ".1..1", "goog.aaaa"};
	private static final int authorityInvalidLength = 3;
	String[] portValid = new String[] {":65535", ":80", ":21", ""};
	private static final int portValidLength = 4;
	String[] portInvalid = new String[] {":-1", ":65536", ":9999999", ":80ad"};
	private static final int portInvalidLength = 4;
	String[] pathValid = new String[] {"/test", "/test1/file", "", "/23$1/t12"};
	private static final int pathValidLength = 4;
	String[] pathInvalid = new String[] {"/../", "/.."};
	private static final int pathInvalidLength = 2;
	String[] queryTest = new String[] {"?action=view", "", "?action=edit&mode=up"};
	private static final int queryTestLength = 3;
	
	private static final boolean VALIDPARTITION_OUTPUT = true;
	private static final boolean INVALIDPARTITION_OUTPUT = true;
	private static final boolean PROGRAMMINGTEST_OUTPUT = true;
	
	static int num_errors = 0;
    static int num_failed_asserts = 0;
    static int num_tests = 0;
	
   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
//You can use this function to implement your manual testing	
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("http://www.google.com/"));
   }
   
   
   public void testValidPartition()
   {
	   boolean result;
	   int numberOfTests = 1000;
	   long seed = 1284;
	   // A static seed was used for more consistent testing. This could be
	   // changed at a later time.
	   // Date d1 = new Date();
	   // long seed = d1.getTime();
	   Random random = new Random(seed);
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   for (int i = 0; i < numberOfTests; i++)
	   {
		   if (VALIDPARTITION_OUTPUT) {
			   num_tests++;
		   }
		   // Randomly select from the various valid
		   // arrays needed to compile a valid URL.
		   int index = random.nextInt(schemesValidLength);
		   String scheme = schemesValid[index];
		   index = random.nextInt(authorityValidLength);
		   String authority = authorityValid[index];
		   index = random.nextInt(portValidLength);
		   String port = portValid[index];
		   index = random.nextInt(pathValidLength);
		   String path = pathValid[index];
		   index = random.nextInt(queryTestLength);
		   String query = queryTest[index];
		   
		   String testUrl = scheme + authority + port + path + query;
		   try {
			   result = TrueAssert(urlVal.isValid(testUrl), testUrl, VALIDPARTITION_OUTPUT);
			   if (!result && VALIDPARTITION_OUTPUT) {
				   num_failed_asserts++;
			   }
		   } catch (Error e) {
			   if (VALIDPARTITION_OUTPUT) {
				   ErrorCatch(e, testUrl);
				   num_errors++;
			   }
    	   }
	   }
   }
   
   public void testInvalidPartition()
   {
	   boolean result;
	   int numberOfTests = 1000;
	   long seed = 1284;
	   // A static seed was used for more consistent testing. This could be
	   // changed at a later time.
	   // Date d1 = new Date();
	   // long seed = d1.getTime();
	   Random random = new Random(seed);
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   for (int i = 0; i < numberOfTests; i++)
	   {
		   if (INVALIDPARTITION_OUTPUT) {
			   num_tests++;
		   }
		   // Randomly select from the various valid
		   // arrays needed to compile a valid URL.
		   int index = random.nextInt(schemesInvalidLength);
		   String scheme = schemesInvalid[index];
		   index = random.nextInt(authorityInvalidLength);
		   String authority = authorityInvalid[index];
		   index = random.nextInt(portInvalidLength);
		   String port = portInvalid[index];
		   index = random.nextInt(pathInvalidLength);
		   String path = pathInvalid[index];
		   index = random.nextInt(queryTestLength);
		   String query = queryTest[index];
		   
		   String testUrl = scheme + authority + port + path + query;
		   try {
			   result = FalseAssert(urlVal.isValid(testUrl), testUrl, INVALIDPARTITION_OUTPUT);
			   if (!result && INVALIDPARTITION_OUTPUT) {
				   num_failed_asserts++;
			   }
		   } catch (Error e) {
			   if (INVALIDPARTITION_OUTPUT) {
				   ErrorCatch(e, testUrl);
				   num_errors++;
			   }
    	   }
	   }
	   
   }
   //You need to create more test cases for your Partitions if you need to 
   
   public void testIsValid()
   {
	   //You can use this function for programming based testing
	   System.out.println("Testing complete, " + num_tests + " tests. " + num_failed_asserts + " failed asserts, " + num_errors + " errors caught.");
       System.out.println(((100 - (num_failed_asserts + num_errors) * 100 / num_tests)) + "% successful.");
   }
   
   public boolean TrueAssert(boolean statement, String test_case, boolean print) {
	   if (statement) {
		   return true;
	   }
	   else {
		   if (print) {
			   System.out.println("TrueAssert failed: " + test_case);
		   }
		   return false;
	   }
   }
   
   public boolean FalseAssert(boolean statement, String test_case, boolean print) {
	   if (statement) {
		   if (print) {
			   System.out.println("FalseAssert failed: " + test_case);
		   }
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
	   //UrlValidator urlValidator = new UrlValidator();
       String url;
       boolean valid;
       //int num_errors = 0;
       //int num_failed_asserts = 0;
       //int num_tests = 0;
       boolean result;
       
       for (int i = 0; i < SchemeTests.length; i++) {
    	   for (int j = 0; j < AuthorityTests.length; j++) {
    		   for (int k = 0; k < PortTests.length; k++) {
    			   for (int l = 0; l < PathTests.length; l++) {
    				   for (int m = 0; m < QueryTests.length; m++) {
			    		   url = SchemeTests[i].item + AuthorityTests[j].item + PortTests[k].item + PathTests[l].item + QueryTests[m].item;
			    		   valid = SchemeTests[i].valid && AuthorityTests[j].valid && PortTests[k].valid && PathTests[l].valid && QueryTests[m].valid;
			    		   if (PROGRAMMINGTEST_OUTPUT) {
			    			   num_tests++;
			    		   }
				    	   try {
				    		   if (valid) {
				    			   result = TrueAssert(urlValidator.isValid(url), url, PROGRAMMINGTEST_OUTPUT);
				    		   }
				    		   else {
				    			   result = FalseAssert(urlValidator.isValid(url), url, PROGRAMMINGTEST_OUTPUT);
				    		   }
				    		   if (!result && PROGRAMMINGTEST_OUTPUT) {
				    			   num_failed_asserts++;
				    		   }
				    	   } catch (Error e) {
				    		   if (PROGRAMMINGTEST_OUTPUT) {
				    			   ErrorCatch(e, url);
				    			   num_errors++;
				    		   }
				    	   }
    				   }
    			   }
    		   }
    	   }
       }
       //System.out.println("Testing complete, " + num_tests + " tests. " + num_failed_asserts + " failed asserts, " + num_errors + " errors caught.");
       //System.out.println(((100 - (num_failed_asserts + num_errors) * 100 / num_tests)) + "% successful.");

   }
   
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
