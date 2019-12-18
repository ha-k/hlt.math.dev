// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/FuzzyTools.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Wed May 16 05:28:50 2018

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * This is a grab-bag class for static methods implementing useful tools
 * when dealing with fuzzy algebra.
 *
 * @see         FuzzyMatrix
 *
 * @version     Last modified on Sun Dec 08 14:28:37 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

public class FuzzyTools
{
  /**
   * Determines the number of digits on the right of the dot to account
   * for when comparing and printing fuzzy degrees. It is set with the
   * <tt>setPrecision</tt> method. Must be at least <tt>1</tt>. Defaults
   * to <tt>2</tt>.
   */
  static private int precision = 2;
  
  static public void setPrecision (int precision) throws BadFuzzyValuePrecisionException
  {
    if (precision > 0)
      precision = precision;
    else
      throw new BadFuzzyValuePrecisionException("Bad fuzzy value precision: "+precision);
  }

  /* ******************************************************************** */

  /**
   * Verifies that the given double is a legitimate fuzzy truth degree
   * (<i>i.e.</i>, in <tt>[0.0]</tt>). If it is not, it throws a
   * <tt>NonFuzzyValueException</tt> otherwise it returns its value.
   */
  static final double checkFuzzyValue (double value) throws NonFuzzyValueException
  {
    if (value < 0.0 || value > 1.0)
      throw new NonFuzzyValueException("Fuzzy degree out of range: "+value);
			  
    return value;
  }

}
