// FILE public. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/DoublyStochasticMatrix.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Fri Nov 15 18:10:36 2019

/**
 * @version     Last modified on Thu Dec 12 11:12:31 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * This defines the class <tt>DoublyStochasticMatrix</tt> as a subclass
 * of <tt>Matrix</tt> which has only non-negative entries, and
 * whose every row and column add up to <tt>1.0</tt> (and therefore,
 * this entails that all its entries must be in <tt>[0.0,1.0]</tt>).
 *
 * @see         Matrix
 */
public class DoublyStochasticMatrix extends Matrix
{
  public DoublyStochasticMatrix ()
  {
    super();    
  }

  public DoublyStochasticMatrix (int order)
  {
    super(order);    
  }

  public DoublyStochasticMatrix (double[][] data)
  {
    setData(data);
  }

  public DoublyStochasticMatrix setData (double[][] data)
  {
    if (!isDoublyStochastic(data))
      throw new RuntimeException
	("Non-doubly-stochastic data array may not be used for a DoublyStochasticMatrix");

    super.setData(data);
    
    return this;
  }

  /* ******************************************************************** */
}
