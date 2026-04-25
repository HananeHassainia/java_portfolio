package fr.adaming.utils;

public class IntegerOfString {
	    // Function to return the modified string
	    public static int extractInt(String str)
	    {
	        // Replacing every non-digit number
	        // with a space(" ")
	        str = str.replaceAll("[^\\d]", " ");
	  
	        // Remove extra spaces from the beginning
	        // and the ending of the string
	        str = str.trim();
	  
	        // Replace all the consecutive white
	        // spaces with a single space
	        str = str.replaceAll(" +", " ");
	  
	        if (str.equals(""))
	            return -1;
	        
	        int strInt = Integer.parseInt(str);
	        return strInt;
	    }
}
