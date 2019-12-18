// FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/sources/MatrixPackageDescription.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hak-Laptop
// STARTED ON. . Fri Dec  6 10:06:34 2019

/**
 * @version     Last modified on Sun Dec 15 13:50:51 2019 by hak
 * @author      <a href="mailto:hak@acm.org">Hassan A&iuml;t-Kaci</a>
 * @copyright   &copy; <a href="http://www.hassan-ait-kaci.net/">by the author</a>
 */

package hlt.math.matrix;
/**
 * <a href="000StartHere.html"><tt>package hlt.math.matrix</tt>
 * documentation listing</a>
 */

/**
 * The Java classes defined in this package implement basic
 * two-dimensional linear algebra in the form of a collection of
 * operations defined on a <tt>Matrix</tt> class represented as a 2D
 * array of <tt>double</tt>s. (Follow the link for a short, interesting,
 * entertaining, and informative, presentation on the origins and
 * history of <a target="_blank"
 * href="https://archive.siam.org/meetings/la09/talks/higham.pdf">Matrix
 * Algebra</a>).
 *
 * <a name="algebras">
 * <h4>Organization of the algebra classes</h4>
 * </a>
 *
 * The arithmetic on <tt>double</tt> matrix entries is generic.  It is
 * generic because all matrix-algebra operations (matrix <tt>plus</tt>,
 * unary and binary matrix <tt>minus</tt>, matrix <tt>times</tt>,
 * <i>etc.</i>), are expressed in terms of the operations of an abstract
 * <a href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> defining the
 * following number operations on <tt>double</tt> entries:

 * <ul>
 * <li/> <tt>double zero()                    </tt>
 *      :<tt>  </tt>constant of type <tt>double</tt>;
 * <li/> <tt>double one()                     </tt>
 *      :<tt>  </tt>constant of type <tt>double</tt>;
 * <li/> <tt>double sum(double,double)        </tt>
 *      :<tt>  </tt>addition operation on <tt>double</tt>s;
 * <li/> <tt>double product(double,double)    </tt>
 *      :<tt>  </tt>multiplication  operation on <tt>double</tt>s;
 * <li/> <tt>double negation(double)          </tt>
 *      :<tt>  </tt>negation operation on a <tt>double</tt>;
 * <li/> <tt>double difference(double,double) </tt>
 *      :<tt>  </tt>difference operation on <tt>double</tt>s.
 * </ul>

 * <p/>

 * More operations on number entries could be defined depending on the
 * nature of specific subclasses of a <tt>NumberAlgebra</tt>
 * (<i>e.g.</i>, <tt>divide</tt>, <tt>inverse</tt>, <a target="_blank"
 * href="https://www.hassan-ait-kaci.net/pdf/oo-fp-lineqs.pdf"><tt>quasi-inverse</tt></a>,
 * <i>etc.</i>). Therefore, these six operations are a required minimum
 * set. More operations defined on numbers allow then more operations to
 * be defined on matrices (<i>e.g.</i>, matrix entry pivoting, matrix
 * inversion, linear equation solving, simplex, graph-path operations,
 * <i>etc.</i>). Therefore, the <a
 * href="000StartHere.html"><tt>hlt.math.matrix</tt> package</a> is
 * expected to grow as more capabilities are supported.

 * <p/>

 * In this way, all the matrix operations are thus parameterized with
 * respect to any concrete subclass of <a
 * href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> algebra implementing
 * these methods for the values making up matrix of <tt>double</tt>
 * entries.

 * It also defines two concrete such subclasses.

 * <ul> <li/><a href="StandardAlgebra.html"><tt>StandardAlgebra</tt></a>
 * defining:
 *
 * <p/>
 *
 *      <ul>
 *      <li/> <tt>zero()          </tt> &#8797; <tt> 0.0</tt>;
 *      <li/> <tt>one()           </tt> &#8797; <tt> 1.0</tt>;
 *      <li/> <tt>sum(x,y)        </tt> &#8797; <tt> x+y</tt>;
 *      <li/> <tt>product(x,y)    </tt> &#8797; <tt> x\*y</tt>;
 *      <li/> <tt>negation(x)     </tt> &#8797; <tt> -x</tt>;
 *      <li/> <tt>difference(x,y) </tt> &#8797; <tt> x-y</tt>;
 *      </ul>
 *
 * <p/>
 *
 * <li/><a href="MaxMinAlgebra.html"><tt>MaxMinAlgebra</tt></a> defining:
 *
 * <p/>
 *
 *      <ul>
 *      <li/> <tt>zero()          </tt> &#8797; <tt> Double.NEGATIVE_INFINITY</tt>;
 *      <li/> <tt>one()           </tt> &#8797; <tt> Double.POSITIVE_INFINITY</tt>;
 *      <li/> <tt>sum(x,y)        </tt> &#8797; <tt> Math.max(x,y)</tt>;
 *      <li/> <tt>product(x,y)    </tt> &#8797; <tt> Math.min(x,y)</tt>;
 *      <li/> <tt>negation(x)     </tt> is undefined;
 *      <li/> <tt>difference(x,y) </tt> is undefined.
 *      </ul>
 *
 * </ul>
 *

 * We will also define the following abstract subclass of <a
 * href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> to be used for
 * defining fuzzy operations:

 * <ul> <li/><a
 * href="../fuzzy/FuzzyAlgebra.html"><tt>FuzzyAlgebra</tt></a> with default
 * operations:
 *
 * <p/>
 *
 *      <ul>
 *      <li/> <tt>zero()          </tt> &#8797; <tt> 0.0</tt>;
 *      <li/> <tt>one()           </tt> &#8797; <tt> 1.0</tt>;
 *      <li/> <tt>sum(x,y)        </tt> &#8797; <tt> Math.max(x,y)</tt>;
 *      <li/> <tt>product(x,y)    </tt> &#8797; <tt> Math.min(x,y)</tt>;
 *      <li/> <tt>negation(x)     </tt> &#8797; <tt> one()-x</tt>;
 *      <li/> <tt>difference(x,y) </tt> &#8797; <tt> sum(x,negation(y))</tt>.
 *      </ul>
 *
 * </ul>

 * Importantly, the class <a
 * href="../fuzzy/FuzzyAlgebra.html"><tt>FuzzyAlgebra</tt></a> is
 * abstract because it defines the <tt>sum</tt> and <tt>product</tt>
 * operations as abstract methods. It has three concrete subclasses
 * defined defined in the current <a
 * href="../fuzzy/000StartHere.html"><tt>hlt.math.fuzzy</tt></a>
 * package: (1) the class <a
 * href="../fuzzy/ZadehAlgebra.html"><tt>hlt.math.fuzzy.ZadehAlgebra</tt></a>,
 * (2) the class <a
 * href="../fuzzy/ProbabilisticAlgebra.html"><tt>hlt.math.fuzzy.ProbabilisticAlgebra</tt></a>,
 * and the class (3) <a
 * href="../fuzzy/LukasieviczAlgebra.html"><tt>hlt.math.fuzzy.LukasieviczAlgebra</tt></a>.<p/>

 * <ol>
 *
 * <li/>
 * The class <tt>ZadehAlgebra</tt> is the fuzzy algebra the most
 * commonly known and used. It is the fuzzy algebra defined in <a
 * href="https://hassan-ait-kaci.net/pdf/others/Fuzzy-Sets-Information-and-Control-1965.pdf">
 * Lotfi Zadeh's 1965 paper</a>. This class inherits all the defaults
 * defined in its abstract <a
 * href="../fuzzy/FuzzyAlgebra.html"><tt>FuzzyAlgebra</tt></a>
 * superclass.<p/>

 * <li/>
 * The class <tt>ProbabilisticAlgebra</tt> inherits all methods, except
 * <tt>sum</tt> and <tt>product</tt> that are defined as:<p/>

 * <ul>
 * <li/> <tt>sum(x,y)        </tt> &#8797; <tt> x+y-x\*y</tt>;
 * <li/> <tt>product(x,y)    </tt> &#8797; <tt> x\*y</tt>;
 * </ul><p/>

 * <li/>
 * The class <tt>LukasieviczAlgebra</tt> inherits all methods, except
 * <tt>sum</tt> and <tt>product</tt> that are defined as:<p/>

 * <ul>
 * <li/> <tt>sum(x,y)        </tt> &#8797; <tt> Math.min(x+y,1.0)</tt>;
 * <li/> <tt>product(x,y)    </tt> &#8797; <tt> Math.max(0.0,x+y-1.0)</tt>.
 * </ul><p/>

 * </ul>

 * To be well-defined, methods implementing a fuzzy algebra's operations
 * should obey at least the following axioms, for any <tt>double</tt>
 * <tt>x</tt>:

 * <ul>
 *
 * <li/> <tt>sum(x,zero())    </tt> = <tt>sum(zero(),x)    </tt> = <tt>x</tt>;
 *
 * <li/> <tt>product(x,zero())</tt> = <tt>product(zero(),x)</tt> = <tt>zero()</tt>.
 *
 * <li/> <tt>product(x,one()) </tt> = <tt>product(one(),x) </tt> = <tt>x</tt>;
 *
 * </ul><p/>
 
 * These look familiar as they do hold in standard arithmetic. But what
 * about:
 *
 * <ul>
 * <li/> <tt>sum(x,one())     </tt> = <tt>sum(one(),x)     </tt> = <tt>one()</tt>?
 * </ul><p/>

 * While this obviously does not hold for the <a
 * href="StandardAlgebra.html"><tt>StandardAlgebra</tt></a> defing
 * standard number arithmetic (since it is not true that <tt>x+1</tt> =
 * <tt>1</tt> or <tt>1+x</tt> = <tt>1</tt>, for any <tt>x</tt>), it does
 * hold in many algebras such as <a
 * href="MaxMinAlgebra.html"><tt>MaxMinAlgebra</tt></a> or <a
 * href="../fuzzy/FuzzyAlgebra.html"><tt>FuzzyAlgebra</tt></a> at its
 * subclasses, in addition to the three axioms above.
 *
 * <p/>
 *
 * It is important to understand the justification for making the same
 * number-entry algebra be the value of a static component of the class
 * <a href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> to be shared by
 * all matrix operations unless redefined explicitly by a <a
 * href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> static class
 * method. Proceeding otherwise (<i>i.e.</i>, allowing each matrix to
 * carry its own algebra) is both inefficient and opens the door to
 * inconsistencies that can only be avoided by using mostly useless
 * checks. Using only one unique number algebra in effect for any matrix
 * operations at a given a time solves the issue. For example, if the
 * current algebra set to be in effect in the class <a
 * href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> is <a
 * href="MaxMinAlgebra.html"><tt>MaxMinAlgebra</tt></a>, all matrix
 * operations will implicitly use the operations of a <a
 * href="MaxMinAlgebra.html"><tt>MaxMinAlgebra</tt></a> for its operations on
 * the <tt>double</tt> entries.
 *
 * <p/>
 *
 * Therefore, one must be aware that if some computation in the same
 * application requires, say, both <a
 * href="StandardAlgebra.html"><tt>StandardAlgebra</tt></a> and <a
 * href="MaxMinAlgebra.html"><tt>MaxMinAlgebra</tt></a> matrix operations,
 * the only safe and consistent way to proceed is to reset the value of
 * the static algebra in <a
 * href="NumberAlgebra.html"><tt>NumberAlgebra</tt></a> to the necessary
 * algebra, which will not change until the next such explicit
 * invocation of the class method:

 * <pre>
 *     NumberAlgebra.setCurrentAlgebra(NumberAlgebra.maxMinAlgebra());</pre>

 * until its is set to another explicit algebra setting; say:

 * <pre>
 *     NumberAlgebra.setCurrentAlgebra(NumberAlgebra.standardAlgebra());</pre>

 * or more succinctly, with the equivalent:

 * <pre>
 *     NumberAlgebra.setMaxMinAlgebra());</pre>

 * and:

 * <pre>
 *     NumberAlgebra.setStandardAlgebra();</pre>

 * provided shorthand class methods.
 *
 *
 * @see         Matrix
 */
