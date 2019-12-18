// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/FuzzyMatrix.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Wed Mar 21 11:24:34 2018

/**
 * @version     Last modified on Tue Dec 17 16:25:23 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.fuzzy;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.fuzzy</tt>
 * package documentation listing</a>
 */

import hlt.language.tools.Debug;

import hlt.math.matrix.Matrix;
import hlt.math.matrix.NumberAlgebra;
// for the set of fuzzy degrees used by this fuzzy matrix (see end of file):
import hlt.language.util.DoubleArrayList;
// for the set of matrix indices representing similarity classes:
import hlt.language.util.IntArrayList;

/**
 * This is a generic class defining linear algebra operations on
 * matrices of fuzzy degrees in <tt>[0.0,1.0]</tt> in terms of its two
 * redefinable binary fuzzy operations on <tt>[0.0,1.0]</tt>: the
 * additive law <tt>sum</tt> and the multiplicative law
 * <tt>product</tt>. If not overridden, the default implementations for
 * <tt>sum</tt> and <tt>product</tt> are respectively <tt>Math.max</tt>
 * and <tt>Math.min</tt>.
 *
 * <p/>
 *
 * <span style="font-family:arial,helvetica;">
 * <a name="contents"><b>Contents</b></a> <small>(<a href="../matrix/000StartHere.html"><tt>package hlt.math.matrix</tt> package documentation listing</a>)</small> 
 * <ul>
 * <li><a href="#constructors">Object Constructors</a></li>
 * <p/>
 * <li><a href="#components">Object Components</a></li>
 * <p/>
 * <li><a href="#objectmethods">Object Methods</a></li>
 *     <ul>
 *     <li><a href="#setget">Component-Setter/Getter Methods</a></li>
 *     <li><a href="#closure">Fuzzy Matrix-Closure Methods</a></li>
 *     <li><a href="#misc">Miscellaneous Object Methods</a></li>
 *     </ul>
 * <p/>
 * <li><a href="#classmethods">Class Components and Methods</a></li>
 * </ul>
 * </span>
 *
 * @see         ../matrix/Matrix
 * @see         ../matrix/NumberAlgebra
 * @see         FuzzyAlgebra
 */

public class FuzzyMatrix extends Matrix
{
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="constructors" href="#contents">Object Constructors</a>
   * </span></h2>
   */

  /**
   * Create a new <tt>FuzzyMatrix</tt> using the default
   * <tt>NumberAlgebra</tt>).
   */
  public FuzzyMatrix ()
  {
    super();
    //    FuzzyAlgebra.setDefaultAlgebra();
  }

  /**
   * Create a new <tt>FuzzyMatrix</tt> using the specified
   * <tt>NumberAlgebra</tt> as the fuzzy operations.
   */
  private FuzzyMatrix (NumberAlgebra algebra)
  {
    super();
    NumberAlgebra.setCurrentAlgebra(algebra);
  }

  /**
   * Construct a <tt>FuzzyMatrix</tt> of order <tt>order</tt> with all
   * entries <tt>0.0</tt>'s.
   */
  public FuzzyMatrix (int order)
  {
    super(order);
    //    FuzzyAlgebra.setDefaultAlgebra();
  }

  /**
   * Create a new fuzzy matrix with a new <tt>rows</tt>-by-<tt>cols</tt>
   * data array of <tt>0.0</tt>'s.
   */
  public FuzzyMatrix (int rows, int cols)
  {
    super(rows,cols);
    //    FuzzyAlgebra.setDefaultAlgebra();
  }

  /**
   * Create a new <tt>FuzzyMatrix</tt> sharing the given array
   * <tt>data</tt> of <tt>double</tt>s in <tt>[0.0,0.1]</tt> (trusting
   * them to be so).
   */
  public FuzzyMatrix (double[][] data)
  {
    super(data,!COPY);
    //    FuzzyAlgebra.setDefaultAlgebra();
  }

  /**
   * If the flag <tt>copy</tt> is <tt>false</tt>, create a fuzzy matrix
   * sharing the given data array; otherwise, create a fuzzy matrix with
   * a new copy of the data array, in which case it also verifies that
   * all the <tt>double</tt> degrees in <tt>data</tt> are indeed in the
   * continuous interval <tt>[0.0,0.1]</tt>.
   * 
   */
  public FuzzyMatrix (double[][] data, boolean copy)
  {
    rows = data.length;
    cols = data[0].length;

    if (copy)
      {
	this.data = new double[rows][cols];
	for (int i = 0; i < rows; i++)
	  for (int j = 0; j < cols; j++)
	    this.data[i][j] = FuzzyTools.checkFuzzyValue(data[i][j]);
      }
    else
      this.data = data;

    //    FuzzyAlgebra.setDefaultAlgebra();
  }

  /**
   * Construct a new <tt>FuzzyMatrix</tt> from the data array of the given
   * <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix (FuzzyMatrix M)
  {
    this(M.data,true);
  }

  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="components" href="#contents">Object Components</a>
   * </span></h2>
   */

  /**
   * <a name="degrees"></a> The <tt>degrees</tt> of this fuzzy matrix
   * are its entries.  This set is represented as a list of
   * <tt>double</tt>s kept in ascending order.
   */
  final protected DoubleArrayList degrees = new DoubleArrayList();

  /* ************************************************************************ */
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="objectmethods" href="#contents">Object Methods</a>
   * </span></h2>
   */

  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="setget" href="#contents">Component-Setter/Getter Methods</a>
   * </span></h4>
   */

  /**
   * Modify this <tt>FuzzyMatrix</tt> to have the given two-dimensional
   * data array of <tt>double</tt>s and corresponding numbers of rows
   * and columns.  If the array is <tt>null</tt>, throw a runtime
   * exception.
   */
  public FuzzyMatrix setData (double[][] data)
  {
    return (FuzzyMatrix)super.setData(data);
  }

  /**
   * Set the (<tt>i</tt>,<tt>j</tt>) entry of this <tt>Matrix</tt> to
   * the given <tt>value</tt> and returns the previous value that was
   * there. Throws a <tt>NonFuzzyValueException</tt> exception if
   * <tt>degree</tt> is not within <tt>[0.0,1.0]</tt>. Throws a
   * <tt>RuntimeException</tt> for indices out of bounds.  <b>N.B.:</b>
   * Matrix indices are counted from <tt>1</tt>.
   */
  public double set (int i, int j, double degree)
  {
    return super.set(i,j,FuzzyTools.checkFuzzyValue(degree));
  }

  /* ******************************************************************** */
  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="closure" href="#contents">Fuzzy Matrix-Closure Methods</a>
   * </span></h4>
   *
   * The following public methods define the reflexive, symmetric,
   * transitive, and similarity closure operations on a fuzzy
   * matrix. Each closure operation comes in two versions: one that
   * returns a new fuzzy matrix without modifying the invoking matrix,
   * and one that modifies the data array of the invoking matrix in
   * place and returns the invoking matrix. The "in-place" version's
   * name of any operation is the same as the non-in-place version
   * starting with "<tt>i_</tt>".  Each pair of methods rely on a static
   * data version method (whose name starts with <code>data</code>),
   * acting on a <code>double[][]</code> data array and returning a
   * resulting <code>double[][]</code> data array. <p/>

   * As for all methods in this class, these work correctly assuming
   * specifically that all <tt>FuzzyMatrix</tt> objects are well-defined
   * (<i>i.e.</i>, they are all square and with entries in
   * <tt>[0.0,1.0]</tt>).
   */

  /**
   * Return a new <tt>FuzzyMatrix</tt> that is the reflexive
   * closure of this <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix reflexive_closure ()
  {
    return (new FuzzyMatrix(data)).i_reflexive_closure();
  }

  /**
   * Modify this fuzzy matrix to its reflexive closure and return it(self).
   */
  public FuzzyMatrix i_reflexive_closure ()
  {
    dataReflexiveClosure(data);
    return this;
  }

  /**
   * Update the<code> data</code> array of this <code>FuzzyMatrix</code>
   * to its reflexive closure and return it(self).
   */
  static final public double[][] dataReflexiveClosure (double[][] data)
  {
    for (int row=0; row < data.length; row++)
      data[row][row] = 1.0;

    return data;
  }

  /* ************************************************************************ */

  /**
   * Return <code>true</code> iff this <code>FuzzyMatrix</code> is
   * anti-symmetric (<i>i.e.</i>, symmetric entries may not be both
   * non-zero).
   */
  public boolean isAntiSymmetric ()
  {
    return dataIsAntiSymmetric(data);
  }

  /**
   * Return <code>true</code> iff the specified <code>data</code> array
   * is anti-symmetric (<i>i.e.</i>, symmetric entries may not be both
   * non-zero).
   */
  static public boolean dataIsAntiSymmetric (double[][] data)
  {
    for (int row = 0; row < data.length-1; row++)
      for (int col = row+1; col < data[0].length; col++)
	if (data[row][col] != 0.0 && data[col][row] != 0.0)
	  return false;

    return true;
  }

  /* ************************************************************************ */

  /**
   * Return a new <tt>FuzzyMatrix</tt> that is the symmetric
   * closure of this <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix symmetric_closure ()
  {
    return (new FuzzyMatrix(data)).i_symmetric_closure();
  }

  /**
   * Modify this <tt>FuzzyMatrix</tt> to its symmetric closure and return it(self).
   */
  public FuzzyMatrix i_symmetric_closure ()
  {
    dataSymmetricClosure(data);
    return this;
  }

  /**
   * Update the given <code>data</code> array its symmetric closure and
   * return it(self).
   */
  static final public double[][] dataSymmetricClosure (double[][] data)
  {
    for (int row = 0; row < data.length; row++)
      for (int col = row+1; col < data[0].length; col++)
	{
	  data[row][col] = sum(data[row][col],
			       data[col][row]);
	  data[col][row] = data[row][col];
	}

    return data;
  }

  /* ************************************************************************ */

  /**
   * Return a new <tt>FuzzyMatrix</tt> that is the  transitive
   * closure of this <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix transitive_closure ()
  {
    return (new FuzzyMatrix(data)).i_transitive_closure();
  }

  /**
   * Modify this <tt>FuzzyMatrix</tt> to its transitive closure and
   * return it(self).
   */
  public FuzzyMatrix i_transitive_closure ()
  {
    return setData(dataTransitiveClosure(data));
  }

  /**
   * Compute the transitive closure of the fuzzy relation specified in
   * the <code>data</code> array and return the resulting array.  The
   * algorithm goes as follows: <p/>

   * <div>
   * Given a fuzzy matrix <tt>M</tt>, its transitive closure is the
   * matrix <tt>M</tt><sup>\*</sup> = <font
   * size="+1">&cup;</font><sub><tt>i</tt>=<tt>0</tt>,...,<tt>n</tt></sub>
   * <tt>M<sup>i</sup></tt> which is computed as the limit of the
   * sequence <tt>N<sub>0</sub></tt> = <tt>M</tt>, and for <tt>k</tt>
   * &gt; <tt>0</tt>, <tt>N<sub>k</sub></tt> = <tt>N<sub>k-1</sub></tt>
   * &times; <tt>N<sub>k-1</sub></tt> until it becomes stationary;
   * <i>i.e.</i>, <tt>N<sub>k</sub></tt> = <tt>N<sub>k-1</sub></tt> =
   * <tt>M</tt><sup>\*</sup>.
   * </div>

   * <p/>

   * This has complexity
   * <i>O</i>(<tt>n</tt><sup>3</sup>log(<tt>n</tt>)).
   */
  static final public double[][] dataTransitiveClosure (double[][] data)
  {
    double[][] newData = data;
    double[][] oldData;

       int it = 0;

    do 
      {
	// it++;
	
    	oldData = copyData(newData);
    	newData = square(oldData);

	// 	System.err.println(">>> PREVIOUS DATA:\n");
	// 	showDataArray(oldData);
	// 	System.err.println(">>> SQUARED DATA:\n");
	// 	showDataArray(newData);
	// 	if (!equalData(newData,oldData))
	// 	  {
	// 	    String answer = Debug.step("continue computing dataTransitiveClosure? (\"q\" to quit)");
	// 	    if (Debug.isQuitString(answer))
	// 	      break;
	// 	  }
      }
    while
      (!equalData(newData,oldData));

    // System.err.println(">>> Number of iterations = "+it);

    return newData;
  }

  /**
   * Return a new <tt>double[][]</tt> array equal to the matrix-square
   * of the given square data array.
   */
  static final private double[][] square (double[][] data)
  {
    // System.err.println(">>> CHECKING FUZZY OPS");
    // System.err.println(">>> 0.4 \\/ 0.6 = "+sum(0.4,0.6));
    // System.err.println(">>> 0.4 /\\ 0.6 = "+product(0.4,0.6));
    // System.err.println(">>> SQUARING A "+data.length+" by "+data[0].length+" DATA ARRAY");
    // showDataArray(data);

    double[][] newData = new double[data.length][data[0].length];

    for (int row = 0; row < data.length; row++)
      for (int col = 0; col < data[0].length; col++)
	for (int k = 0; k < data.length; k++)
	  {
	    newData[row][col] = sum(newData[row][col],
				    product(data[row][k],
					    data[k][col]));

	    // if (newData[row][col] > 1.0)
	    //   System.err.println(">>> Bad fuzzy entry: "+newData[row][col]);
	  }

    // System.err.println(">>> THE RESULTING SQUARED MATRIX IS:\n");
    // showDataArray(newData);
    // //    Debug.step("continue");

    return newData;
  }

  /* ************************************************************************ */

  /**
   * Return a new <tt>FuzzyMatrix</tt> that is the preorder
   * closure of this <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix preorder_closure ()
  {
    return (new FuzzyMatrix(data)).i_preorder_closure();    
  }

  /**
   * Modify this fuzzy matrix to its preorder (<i>i.e.</i>,
   * symmetric-transitive) closure and return it(self).
   */
  public FuzzyMatrix i_preorder_closure ()
  {
    data = dataPreorderClosure(data);
    return this;
  }

  /**
   * Return a <code>double[][]</code> array that is the preorder
   * (<i>i.e.</i>, reflexive-transitive) closure of the given
   * <code>data</code>.
   */
  static public double[][] dataPreorderClosure (double[][] data)
  {
    return dataTransitiveClosure(dataReflexiveClosure(data));
  }

  /* ************************************************************************ */

  /**
   * Return a <tt>FuzzyMatrix</tt> that is the similarity closure
   * of this <tt>FuzzyMatrix</tt>.
   */
  public FuzzyMatrix similarity_closure ()
  {
    return reflexive_closure().i_symmetric_closure().i_transitive_closure();
  }

  /**
   * Modify this  <tt>FuzzyMatrix</tt> to its similarity closure and return it(self).
   */
  public FuzzyMatrix i_similarity_closure ()
  {
    return i_reflexive_closure().i_symmetric_closure().i_transitive_closure();
  }

  /* ******************************************************************** */

  /**
   * Given a degree <tt>cut</tt> &isin; <tt>degrees</tt>,
   * <tt>partition(cut)</tt> of this <tt>FuzzyMatrix</tt> (when it is a
   * similarity relation on the set <tt>{0,...,N}</tt>), is an array of
   * <tt>N</tt> lists of <tt>int</tt>s, each list containing the indices
   * in the set <tt>{0,...,N}</tt> constituting a similarity class at a
   * fuzzy approximation degree <tt>&alpha;</tt> &isin;
   * <tt>[cut,1.0]</tt> (<i>i.e.</i>, it is a partition of the set
   * <tt>{0,...,N}</tt> for similarity degrees that are not less than
   * <tt>cut</tt>). The list at index <tt>i</tt> contains the indices of
   * the elements in the class <tt>[i]<sub>&alpha;</sub></tt> ordered in
   * ascending order, where<tt>[i]<sub>&alpha;</sub></tt> &#8797;
   * {<tt>j</tt> &isin; <tt>{0,...,N}</tt> | <tt>data[i][j]</tt> &geq;
   * <tt>&alpha;</tt>}. Each class is uniquely represented and shared by
   * all indices in the class (it is the same list object) for all
   * indices it contains. For example, if for <tt>N=6</tt>, the matrix
   * is:
   *
   * <p>
   *
   * <pre>
   *      0   1   2   3   4   5
   *     --- --- --- --- --- ---
   * 0 | 1.0 0.5 0.0 0.5 0.4 0.2
   * 1 | 0.5 1.0 0.0 0.5 0.4 0.0
   * 2 | 0.0 0.0 1.0 0.0 0.0 0.0
   * 3 | 0.5 0.5 0.0 1.0 0.4 1.2
   * 4 | 0.4 0.4 0.0 0.4 1.0 0.2
   * 5 | 0.2 0.2 0.0 0.2 0.2 1.0</pre>
   *
   * then its array <tt>degrees</tt> is <tt>[0.0,0.2,0.4,0.5,1.0]</tt>
   * and its <tt>partition(0.4)</tt> array is:

   * <pre>
   * 0: A
   * 1: A
   * 2: B
   * 3: A
   * 4: A
   * 5: C</pre>

   * with the three shared index lists:
   * <ul>
   * <li/><tt>A</tt> &#8797; <tt>[0,1,3,4] </tt>
   *      (= <tt>[0]<sub>0.4</sub></tt>
   *       = <tt>[1]<sub>0.4</sub></tt>
   *       = <tt>[3]<sub>0.4</sub></tt>
   *       = <tt>[4]<sub>0.4</sub></tt>);
   * <li/><tt>B</tt> &#8797; <tt>[2]       </tt>
   *      (= <tt>[2]<sub>0.4</sub></tt>);  and,
   * <li/><tt>C</tt> &#8797; <tt>[5]       </tt>
   *      (= <tt>[5]<sub>0.4</sub></tt>).
   * </ul>
   */
  public IntArrayList[] partition (double cut)
  {
    int rank = rows;
    
    // The array of rank classes
    IntArrayList[] classes = new IntArrayList[rank];

    // initialize all classes to singletons
    for (int i=0; i < rank; i++)
      {
	classes[i] = new IntArrayList();
	classes[i].add(i);
      }

    // sweep through the upper right triangle and merge the partitions
    // of equivalent pairs over or at the cut
    for (int i=0; i < rank-1; i++)
      // {
	for (int j=i+1; j < rank; j++)
	  if (data[i][j] >= cut)
	    // merge the classes at indices i and j and sets all related elements classes
	    merge(classes,i,j);

    return classes;
  }

  /**
   * This merges the classes <tt>classes[i]</tt> and <tt>classes[j]</tt>
   * into a new class keeping it sorted in increasing order, and sets
   * <tt>classes[k]</tt> to be this new merged class for all indices
   * <tt>k</tt> in this class.
   */
  private void merge (IntArrayList[] classes, int i, int j)
  {
    IntArrayList c1 = classes[i];
    IntArrayList c2 = classes[j];

    if (c1 != c2) // otherwise this would keep going
      if (c2.size() < c1.size())
	{
	  for (int k=0; k < c2.size(); k++)
	    insertIndex(c2.get(k),c1);
	  // update all appropriate classes to the merged class
	  for (int k=0; k < c1.size(); k++)
	    classes[c1.get(k)] = c1;
	}
      else
	{
	  for (int k=0; k < c1.size(); k++)
	    insertIndex(c1.get(k),c2);
	  // update all appropriate classes to the merged class
	  for (int k=0; k < c2.size(); k++)
	    classes[c2.get(k)] = c2;
	}
  }

  /**
   * Adds <tt>index</tt> to <tt>orderedClass</tt> keeping it in
   * increasing order.
   */
  private void insertIndex (int index, IntArrayList orderedClass)
  {
    int size = orderedClass.size();
    int i = 0;
    while (i < size && orderedClass.get(i) < index)
      i++;

    if (index != orderedClass.get(i))
      orderedClass.add(i,index);
  }

  /* ******************************************************************** */
  /**
   * <h4 align="center"><span style="font-family:arial,helvetica;">
   * <a name="misc" href="#contents">Miscellaneous Object Methods</a>
   * </span></h4>
   */

  /**
   * Return the supremum of its arguments as elements of a
   * <tt>FuzzyAlgebra</tt> (<i>i.e.</i>, their <tt>sum</tt>, as defined
   * by the current <tt>FuzzyAlgebra</tt> in effect).
   */
  static public final double sup (double x, double y)
  {
    return sum(x,y);
  }

  /**
   * Return the infimum of its arguments as elements of a
   * <tt>FuzzyAlgebra</tt> (<i>i.e.</i>, their <tt>product</tt>, as
   * defined by the current <tt>FuzzyAlgebra</tt> in effect).
   */
  static public final double inf (double x, double y)
  {
    return product(x,y);
  }

  /**
   * Return a new <tt>FuzzyMatrix</tt> that is the transpose of this
   * one.
   */
  public FuzzyMatrix transpose ()
  {
    return new FuzzyMatrix().setData(dataTranspose(data));
  }

  public FuzzyMatrix i_transpose ()
  {
    if (!isSquare())
      throw new RuntimeException
	("Cannot transpose a non-square matrix in place");
      
    int rank = rank();
    
    for (int i = 0; i < rank; i++)
      for (int j = i+1; j < rank; j++)
	{
	  double tmp = data[i][j];
	  data[i][j] = data[j][i];
	  data[j][i] = tmp;
	}

    return this;
  }

  /**
   * Return the set of degrees of this fuzzy matrix.
   */
  public final DoubleArrayList degrees ()
  {
    if (degrees.isEmpty())
      computeDegrees();

    return degrees;
  }

  /**
   * Recompute and returns the set of degrees of this fuzzy matrix.
   */
  public final DoubleArrayList computeDegrees ()
  {
    degrees.setSize(0); // erase all entries if any

    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++)
	addDegree(data[i][j]);

    return degrees;
  }

  /**
   * Inserts a double <tt>degree</tt> into this matrix' set of degree
   * keeping this set sorted in increasing order, and returns the set.
   */
  protected final DoubleArrayList addDegree (double degree)
  {
    int i = 0;

    while (i < degrees.size() && degree > degrees.get(i))
      i++;

    if (degree != degrees.get(i))
      // will shift degrees at indices to the right and insert degree at i
      degrees.add(i,degree);

    return degrees;
  }

  /**
   * Return a new <tt>FuzzyMatrix</tt> with a data array that is
   * a copy of this one's.
   */
  public FuzzyMatrix copy ()
  {
    return copy(data);
  }

  /**
   * Return a new <tt>FuzzyMatrix</tt> having a different data array
   * than that of the given one, but containg equal array degrees as the
   * given one.
   */
  public FuzzyMatrix copy (FuzzyMatrix M)
  {
    return copy(M.data);
  }

  /**
   * Return a new <tt>FuzzyMatrix</tt> whose data array is a copy
   * of the given <tt>data</tt> array.
   */
  public FuzzyMatrix copy (double[][] data)
  {
    return new FuzzyMatrix(data,COPY);
  }

  /* ******************************************************************** */
  /**
   * <h2 align="center"><span style="font-family:arial,helvetica;">
   * <a name="classmethods" href="#contents">Class Components and Methods</a>
   * </span></h2>
   */

  /**
   * Just a convenience to remind that making a copy is in effect (can
   * then be used instead of <tt>true</tt>).
   */
  static boolean COPY = true;

  /**
   * A public handle to the canonical <tt>FuzzyAlgebra</tt> currently in
   * effect.
   */
  static public final FuzzyAlgebra fuzzyAlgebra ()
  {
    return (FuzzyAlgebra)NumberAlgebra.currentAlgebra();
  }

  /* ******************************************************************** */

  /**
   * Create and return a random <tt>rows</tt>-by-<tt>cols</tt>
   * <tt>FuzzyMatrix</tt>.
   */
  public static FuzzyMatrix random (int rows, int cols)
  {
    return new FuzzyMatrix().setData(randomDataArray(rows,cols));
  }

}
