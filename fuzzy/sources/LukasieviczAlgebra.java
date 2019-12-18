// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/LukasieviczAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Sun May  6 08:23:47 2018

/**
 * @version     Last modified on Sun Dec 15 10:45:17 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

/**
 * This defines the class <tt>LukasieviczAlgebra</tt> as:<p/>

 *
 * <ul>
 * <li/><tt>sum(x,y)     </tt>&#8797;<tt> Math.min(x+y,1.0)</tt>
 * <li/><tt>product(x,y) </tt>&#8797;<tt> Math.max(0.0,x+y-1.0)</tt>
 * </ul>

 * @see         FuzzyAlgebra
 * @see         FuzzyMatrix
 */
public class LukasieviczAlgebra extends FuzzyAlgebra
{
  /**
   * Return a <tt>String</tt> form for this <tt>LukasieviczAlgebra</tt>.
   */
  public final String toString ()
  {
    return "Lukasievicz Fuzzy Algebra";
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="objectmethods" href="#contents">Object Methods</a>
   * </span></h2>
   */

  /**
   * Define <tt>sum</tt> for this <tt>LukasieviczAlgebra</tt>.
   */
  public final double sum (double x, double y)
  {
    return  Math.min(x+y,1.0);
  }

  /**
   * Define <tt>product</tt> for this <tt>LukasieviczAlgebra</tt>.
   */
  public final double product (double x, double y)
  {
    return  Math.max(0.0,x+y-1);
  }

}
