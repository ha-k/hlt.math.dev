// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/BadFuzzyValuePrecisionException.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Sun May 27 08:15:53 2018

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * Class of <tt>RuntimeException</tt> indicating a bad fuzzy value precision.
 *
 * @see         FuzzyAlgebra
 *
 * @version     Last modified on Sun Dec 08 14:27:12 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

public class BadFuzzyValuePrecisionException extends RuntimeException
{
  /**
   * Constructs a new <tt>BadFuzzyValuePrecisionException</tt> with given message.
   */
  public BadFuzzyValuePrecisionException (String message)
    {
      super(message);
    }
}
