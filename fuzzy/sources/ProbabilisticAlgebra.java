// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/ProbabilisticAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Sun May  6 08:23:47 2018

/**
 * @version     Last modified on Sun Dec 15 11:34:00 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * This defines the class <tt>ProbabilisticAlgebra</tt> as:
 *
 * <ul>
 * <li/><tt>sum(x,y)     </tt>&#8797;<tt> x+y-x\*y</tt>
 * <li/><tt>product(x,y) </tt>&#8797;<tt> x\*y</tt>
 * </ul>
 *
 * @see         FuzzyAlgebra
 * @see         FuzzyMatrix
 */
public class ProbabilisticAlgebra extends FuzzyAlgebra
{
  /**
   * Return a <tt>String</tt> form for this
   * <tt>ProbabilisticAlgebra</tt>.
   */
  public final String toString ()
  {
    return "Probabilistic Fuzzy Algebra";
  }

  /**
   * Define <tt>sum</tt> for this <tt>ProbabilisticAlgebra</tt>.
   */
  public final double sum (double x, double y)
  {
    return x+y - x*y;
  }

  /**
   * Define <tt>product</tt> for this <tt>ProbabilisticAlgebra</tt>.
   */
  public final double product (double x, double y)
  {
    return x*y;
  }

}
