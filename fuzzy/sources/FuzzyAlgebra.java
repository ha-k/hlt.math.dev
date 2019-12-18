// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/sources/FuzzyAlgebra.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Tue Dec  3 17:40:58 2019

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

import hlt.math.matrix.NumberAlgebra;

/**
 * This is an abstract subclass of
 * <tt>hlt.math.matrix.NumberAlgebra</tt> defining its
 * <tt>NumberAlgebra</tt> operations as the fuzzy algebra with the final
 * methods <tt>zero()</tt> as <tt>0.0</tt>, <tt>one()</tt> as
 * <tt>1.0</tt>, <tt>negation(x)</tt> as <tt>one()-x</tt>, and
 * <tt>difference(x,y)</tt> as <tt>sum(x,negation(y))</tt>. The methods
 * <tt>sum</tt> and <tt>product</tt> are abstract and expected to be
 * redefined in concrete subclasses. Default implementations are
 * provided for <tt>sum</tt> as <tt>Math.max</tt>, and <tt>product</tt>
 * as <tt>Math.min</tt>.<p/>

 * <b>N.B.:</b> For this to work correctly, all values must be
 * <tt>double</tt>s in <tt>[0.0,1.0]</tt>. This methods defined in this
 * class do not check whether their arguments are such <tt>double</tt>s.<p/>
 
 * <span style="font-family:arial,helvetica;"> <a
 * name="contents"><b>Contents</b></a> <small>(<a
 * href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>)</small>

 * <ul>
 * <li><a href="#operations">Fuzzy Algebra Operations</a></li>
 * <p/>
 * <li><a href="#algebras">Defined Fuzzy Algebras</a></li>
 * <p/>
 * </ul>
 * </span>
 *
 * @see         ZadehAlgebra
 * @see         LukasieviczAlgebra
 * @see         ProbabilisticAlgebra
 * @see         ../matrix/Matrix
 * @see         ../matrix/NumberAlgebra
 * @see         ../matrix/MatrixPackageDescription
 *
 * @version     Last modified on Sun Dec 15 11:32:32 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */
abstract public class FuzzyAlgebra extends NumberAlgebra
{
  /**
   * Defines the <tt>String</tt> form for this
   * <tt>FuzzyAlgebra</tt>. It must be redefined in a subclass.
   */
  public String toString ()
  {
    return "Abstract Fuzzy Algebra";
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="operations" href="#contents">Fuzzy Algebra Operations</a>
   * </span></h2>
   */

  /**
   * Define <tt>zero()</tt> as <tt>0.0</tt>.
   */
  public final double zero ()
  {
    return 0.0;
  }

  /**
   * Return the <tt>one</tt> of this algebra as <tt>1.0</tt>.  It
   * verifies <tt>product(x,one())</tt> = <tt>product(one(),x)</tt> =
   * <tt>x</tt>, for any <tt>double</tt> <tt>x</tt>.
   */
  public final double one ()
  {
    return 1.0;
  }

  /**
   * Define <tt>negation(x)</tt> as <tt>one()-x</tt>.
   */
  public final double negation (double x)
  {
    return one()-x;
  }

  /**
   * Define <tt>difference(x,y)</tt> as <tt>sum(x,negation(y))</tt>.
   */
  public final double difference (double x, double y)
  {
    return sum(x,negation(y));	
  }

  /* ************************************************************************ */
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="algebras" href="#contents">Defined Fuzzy Algebras</a>
   * </span></h2>
   */

  /**
   * Set the current fuzzy algebra to the canonical default
   * <tt>FuzzyAlgebra</tt> (<tt>zadehAlgebra</tt>).
   */
  static final public void setDefaultAlgebra ()
  {
    NumberAlgebra.setCurrentAlgebra(zadehAlgebra);
  }

  /**
   * A package-accessible handle to a canonical <tt>ZadehAlgebra</tt>.
   */
  static final ZadehAlgebra zadehAlgebra
    = (ZadehAlgebra)NumberAlgebra.registeredAlgebra(new ZadehAlgebra());

  /**
   * Return the canonical <tt>ZadehAlgebra</tt>.
   */
  static public final ZadehAlgebra zadehAlgebra ()
  {
    return zadehAlgebra;
  }

  /**
   * Set the current fuzzy algebra to the canonical
   * <tt>ZadehAlgebra</tt>.
   */
  static final public void setZadehAlgebra ()
  {
    NumberAlgebra.setCurrentAlgebra(zadehAlgebra);
  }

  /**
   * A package-accessible handle to a canonical <tt>LukasieviczAlgebra</tt>.
   */
  static final LukasieviczAlgebra lukasieviczAlgebra
    = (LukasieviczAlgebra)NumberAlgebra.registeredAlgebra(new LukasieviczAlgebra());
  
  /**
   * Set the current fuzzy algebra to the canonical
   * <tt>LukasieviczAlgebra</tt>.
   */
  static public final void setLukasieviczAlgebra ()
  {
    NumberAlgebra.setCurrentAlgebra(lukasieviczAlgebra);
  }

  /**
   * A package-accessible handle to a canonical <tt>ProbabilisticAlgebra</tt>.
   */
  static final ProbabilisticAlgebra probabilisticAlgebra
    = (ProbabilisticAlgebra)NumberAlgebra.registeredAlgebra(new ProbabilisticAlgebra());
  
  /**
   * Set the current fuzzy algebra to the canonical
   * <tt>ProbabilisticAlgebra</tt>.
   */
  static public final void setProbabilisticAlgebra ()
  {
    NumberAlgebra.setCurrentAlgebra(probabilisticAlgebra);
  }

}
