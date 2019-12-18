// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/LinearFixPointEquationSystem.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Sat Nov 16 15:03:29 2019

/**
 * @version     Last modified on Mon Dec 16 09:52:22 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * This implements a system of linear fix-point equations with <tt>double</tt>
 * coefficients of the form:
 *
 * <tt> A &times; X + B = X</tt> where <tt>A</tt> is an <tt>m</tt>
 * &times; <tt>n</tt> <tt>Matrix</tt>, <tt>B</tt> is an
 * <tt>m</tt> &times; <tt>1</tt> <tt>Matrix</tt> (<tt>i.e.</tt>, a
 * column vector of order <tt>m</tt>), and <tt>X</tt> is a column vector
 * of order <tt>n</tt> of variables <tt>X<sub>i</sub></tt>,

 * <tt>i=1, ... n</tt>. For example, if <tt>m = 3</tt>, <tt>n = 4</tt>
 * and:<p/>

 * <tt>
 *     1.1 1.2 1.3 1.4                  1.0
 * A = 2.1 2.2 2.3 2.4              B = 2.0
 *     3.1 3.2 3.3 3.4                  3.0
 * </tt>

 * <p/>then:<p/>

 * <tt>
 * 1.1 &times; X<sub>1</sub> + 1.2 &times; X<sub>2</sub> + 1.3 &times; X<sub>3</sub> + 1.4 &times; X<sub>4</sub> + 1.0 = X<sub>1</sub>
 * 2.1 &times; X<sub>1</sub> + 2.2 &times; X<sub>2</sub> + 2.3 &times; X<sub>3</sub> + 2.4 &times; X<sub>4</sub> + 2.0 = X<sub>2</sub>
 * 3.1 &times; X<sub>1</sub> + 3.2 &times; X<sub>2</sub> + 3.3 &times; X<sub>3</sub> + 3.4 &times; X<sub>4</sub> + 3.0 = X<sub>3</sub>
 * </tt>

 * <p/>which gives the equivalent non-fix-point formulation <tt>(A-I) &times; X = -B</tt>:<p/>

 * <tt>
 * 0.1 &times; X<sub>1</sub> + 1.2 &times; X<sub>2</sub> + 1.3 &times; X<sub>3</sub> + 1.4 &times; X<sub>4</sub> = -1.0
 * 2.1 &times; X<sub>1</sub> + 1.2 &times; X<sub>2</sub> + 2.3 &times; X<sub>3</sub> + 2.4 &times; X<sub>4</sub> = -2.0
 * 3.1 &times; X<sub>1</sub> + 3.2 &times; X<sub>2</sub> + 2.3 &times; X<sub>3</sub> + 3.4 &times; X<sub>4</sub> = -3.0
 * </tt>

 * <p/>and conversely, from the more conventional non-fix-point
 * formulation of the form <tt>A' &times; X = B'</tt>, one recovers the
 * corresponding equivalent fix-point form as:

 * <tt>(A'+I) &times; X + (-B') = X</tt>, and then use this class.

 * Non square equational systems may only have parametric solutions if
 * there are more variables than equations (expressed in terms of the
 * missing variables), or redundant solutions if there are more
 * equations than variables. For a square matrix, a solution will exist
 * iff it has an inverse (<i>i.e.</i>, iff it has a non-zero
 * determinant).
 * 
 * @see         Matrix
 */
public class LinearFixPointEquationSystem
{
}
