// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/NonFuzzyValueException.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Tue Mar 27 06:44:19 2018

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * Class of <tt>RuntimeException</tt> indicating a violation of
 * [0,1]-fuzzy value range constraint.
 *
 * @see         FuzzyAlgebra
 *
 * @version     Last modified on Sun Dec 08 14:28:55 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

public class NonFuzzyValueException extends RuntimeException
{
  /**
   * Constructs a new <tt>NonFuzzyValueException</tt> with given message.
   */
  public NonFuzzyValueException (String message)
    {
      super(message);
    }
}
