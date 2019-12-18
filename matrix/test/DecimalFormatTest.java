//  FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/test/DecimalFormatTest.java
//  EDIT BY . . . Hassan Ait-Kaci
//  ON MACHINE. . Hak-Laptop
//  STARTED ON. . Mon Dec  2 12:06:26 2019

package test;

import java.util.Locale;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class DecimalFormatTest
{
  public static void main (String[] args)
  {
    // Print out a number using the localized number, integer, currency,
    // and percent format for each locale
    
    Locale[] locales = NumberFormat.getAvailableLocales();
    double myNumber = -1234.56;
    NumberFormat form;
    
    for (int j = 0; j < 4; ++j) {
      System.out.println("FORMAT");
      for (int i = 0; i < locales.length; ++i)
	{
	  if (locales[i].getCountry().length() == 0)
	    continue; // Skip language-only locales

	  System.out.print(locales[i].getDisplayName());

	  switch (j)
	    {
	    case 0:
	      form = NumberFormat.getInstance(locales[i]);
	      break;
	    case 1:
	      form = NumberFormat.getIntegerInstance(locales[i]);
	      break;
	    case 2:
	      form = NumberFormat.getCurrencyInstance(locales[i]);
	      break;
	    default:
	      form = NumberFormat.getPercentInstance(locales[i]);
	    }

	  if (form instanceof DecimalFormat)
	    System.out.print(": " + ((DecimalFormat) form).toPattern());

	  System.out.print(" -> " + form.format(myNumber));

	  try
	    {
	      System.out.println(" -> " + form.parse(form.format(myNumber)));
	    }
	  catch (Exception e)
	    {
	      System.err.println("An exception has occurred: "+e);
	    }
      }
    }
  }
}
