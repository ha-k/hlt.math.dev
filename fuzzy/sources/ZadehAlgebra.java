// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/ZadehAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Sun May  6 08:23:47 2018

/**
 * @version     Last modified on Sun Dec 15 10:44:40 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * This defines the class <tt>ZadehAlgebra</tt> as:
 * <ul>
 * <li/><tt>sum(x,y)     </tt>&#8797;<tt> Math.max(x,y)</tt>
 * <li/><tt>product(x,y) </tt>&#8797;<tt> Math.min(x,y)</tt>
 * </ul>

 *
 * @see         FuzzyAlgebra
 * @see         FuzzyMatrix
 */
public class ZadehAlgebra extends FuzzyAlgebra
{
  /**
   * Return a <tt>String</tt> form for this <tt>ZadehAlgebra</tt>. 
   */
  public final String toString ()
  {
    return "Zadeh Fuzzy Algebra";
  }

  /**
   * Define <tt>sum</tt> as <tt>Math.max</tt>.
   */
  public final double sum (double x, double y)
  {
    return Math.max(x,y);	
  }

  /**
   * Define <tt>product</tt> as <tt>Math.min</tt>.
   */
  public final double product (double x, double y)
  {
    return Math.min(x,y);	
  }
}
