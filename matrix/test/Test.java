//  FILE. . . . . d:/hak/hlt/src/hlt/math/matrix/test/Test.java
//  EDIT BY . . . Hassan Ait-Kaci
//  ON MACHINE. . Hak-Laptop
//  STARTED ON. . Fri Nov 15 11:53:58 2019

//  Last modified on Wed Dec 18 07:43:16 2019 by hak

package test;

import hlt.math.matrix.*;
import hlt.language.tools.Misc;
import hlt.language.util.IntIterator;

public class Test
{
  public static void main (String[] args)
  {
    NumberAlgebra.setCurrentAlgebra(new StandardAlgebra());

    // Desired number of decimal digits after the dot for this app
    int digits = 2;

    // Desired print width of floating-point numbers for this app
    int printWidth = digits+5; // "***.digits": 3 positions left of sign + sign + dot = 5

    // Matrix parameter settings:
    Matrix.setCurrentSignificantDigits(digits);
    Matrix.setPrintWidth(printWidth);
    Matrix.setThreshold0(0.7); // any value between 0.7 and 1.0 may be
			       // randomly rounded to 0.0 according to
			       // current bias (high value favors 0.0s)
    Matrix.setThreshold1(0.1); // any value between 0.09 and 1.0 may be
			       // rounded up to 1.0 according to current
			       // bias (high value favors 1.0s)
    // uncomment the following to override default fair coin tosses (0.5):
    // Matrix.setBias(0.25);   // make coin tosses biased towards 0.0;
    // 			       // this makes random generation more
    // 			       // prone to yield 0.0s than 1.0s:

    // Show the parameter settings:
    Matrix.showParameters();
    ln();

    // Set and show the desired floating-point number scale for this app to 100.0
    scale = 100.0;
    say("Current scale: "+scale);
    say("--------------------------------------------------------------------");

    basic();
    basicMatrix();
    matrix();
    randoms(4,8,8);
    graphs(9);

    say("******************************************************************");
    NumberAlgebra.showRegisteredAlgebras();
    say("******************************************************************");

  }

  /* ************************************************************************ */

  // The desired floating-point number scale for this app
  static double scale = 1.0;

  // a 2x2 doubly stochastic data array
  static double[][] a =
    { { 0.2,  0.8 }
    , { 0.8,  0.2 }
    };

  // a 2x2 data array
  static double[][] b =
    { { 5,  6 }
    , { 7,  8 }
    };

  // a 3x2 data array
  static double[][] c =
    { { 1,  2 }
    , { 3,  4 }
    , { 5,  6 }
    };

  // a 3x4 data array
  static double[][] d =
    { { 1,  2,  3,  4 }
    , { 5,  6,  7,  8 }
    , { 9, 10, 11, 12 }
    };

  // a 4x4 stochastic data array
  static double[][] s =
    { {  1.10,  2.20,  3.30,  4.40 }
    , {  5.50,  6.60,  7.70,  8.80 }
    , {  9.90, 11.11, 12.12, 13.13 }
    , { 14.14, 15.15, 16.16, 17.17 }
    };

  static
  {
    s = Matrix.truncate(s);
  }

  // empty line
  public static void ln ()
  {
    Misc.ln();
  }

  // say in a line not ending in a CR
  public static void jot (String what)
  {
    Misc.jot(what);
  }

  // say in a line ending in a CR
  public static void say (String what)
  {
    Misc.say(what);
  }

  // say in a line ending in a CR and skip a line
  public static void sln (String what)
  {
    Misc.sln(what);
  }

  public static void basic ()
  {
    ln();

    // a 2x2 data array
    double[][] w =
      { { 0.00001,  0.3456 }
      , { 0.78910,  0.9911 }
      };
    w = Matrix.truncate(w);

    Matrix A = new Matrix(w);
    say("a square matrix of order 2:");
    A.show();

    A = new Matrix(3);
    say("a new square matrix of order 3:");
    A.show();

    A.set(2,2,2.3456);
    say("setting this matrix (2,2) entry to 2.3456:");
    A.show();


    A.set(1,3,1.23456);
    say("setting its (1,3) entry to 1.23456:");
    A.show();
    try
      {
	sln("attempting to set (3,4) to 5.0:");
	A.set(3,4,5.6);
      }
    catch (RuntimeException e)
      {
	say(e.toString());
	A.show();
      }
  }

  public static void basicMatrix ()
  {
    Matrix M = new Matrix(s);

    sln("created a matrix M from a "+s.length+" by "+s[0].length+" data array");
    say("a "+M.rows()+" by "+M.cols()+" matrix:");
    M.show();

    say("M.minus() =");
    M.minus().show();

    say("M.plus(M) =");
    M.plus(M).show();

    say("M.minus(M) =");
    M.minus(M).show();

    say("M.i_scale(0.01):");
    M.i_scale(0.01).show();

    say("M.product(M):");
    M.times(M).show();
  }

  public static void matrix ()
  {
    // Change desired number of decimal digits after the dot for this app
    int digits = 3;

    // Change print width of floating-point numbers for this app
    int printWidth = digits+6; // "***.digits": 4 positions left of sign + sign + dot = 6

    // Adjust Matrix parameter settings:
    Matrix.setCurrentSignificantDigits(digits);
    Matrix.setPrintWidth(printWidth);
    // Show the parameter settings:
    Matrix.showParameters();

    // // Set and show the desired floating-point number scale for this app to 100.0
    // scale = 100.0;
    say("Current scale: "+scale);
    say("--------------------------------------------------------------------");
    ln();

    Matrix Q = new Matrix();
    
    sln("a vacuous 2x2 stochastic matrix Q:");
    sln("Is Q vacuous? ["+Q.isVacuous()+"]");

    try
      {
	Q.setData(a);
	say("a non-vacuous 2x2 doubly-stochastic matrix Q:");
	Q.show();
      }
    catch (RuntimeException e)
      {
	sln(e.toString());
      }

    try
      {
	Q = new Matrix(s);
	say("yet another 4x4 doubly-stochastic matrix Q:");
	Q.show();
      }
    catch (RuntimeException e)
      {
	ln();
	sln(e.toString());
      }

    sln("Is Q vacuous? ["+Q.isVacuous()+"]");

    Matrix A = new Matrix(a);
    Matrix B = new Matrix(b);
    sln("two 2x2 matrices A and B:");
    say("A:");
    A.show();

    say("and B:");
    B.show();

    say("their sum A+B:");
    A.plus(B).show();

    say("A has not changed:");
    A.show();

    jot("invoking their in_place sum A.i_plus(B); ");
    A.i_plus(B);
    say("so now A has changed:");
    A.show();

    A = new Matrix(a);
    B = new Matrix(b);
    sln("reset the two 2x2 matrices A and B as they were:");
    say("A:");
    A.show();

    say("and B:");
    B.show();

    say("their product A*B:");
    A.times(B).show();

    say("A has not changed:");
    A.show();

    jot("invoking their in_place product A.i_times(B); ");
    A.i_times(B);
    say("so now A has changed:");
    A.show();

    Matrix S = Matrix.randomSquare(4,scale);
    say("a random square matrix of order 4:");
    S.show();

    say("its transpose:");
    S.transpose().show();

    S.i_transpose();
    say("its in-place transpose:");
    S.show();

    A = Matrix.random(3,4,scale);
    say("a random 3x4 matrix A:");
    A.show(); 

    B = Matrix.random(3,4,scale);
    say("a random 3x4 matrix B:");
    B.show(); 

    say("their difference as the 3x4 matrix A.minus(B):");
    A.minus(B).show(); 

    say("their difference as the 3x4 matrix B.minus(A):");
    B.minus(A).show(); 

    A.i_minus(B);
    say("A's in-place difference as the 3x4 matrix A.i_minus(B):");
    A.show(); 

    B.i_minus(A);
    say("the new A used for B's in-place difference as the 3x4 matrix B.i_minus(A):");
    B.show(); 

    A.swapRows(1,2);
    say("the above matrix A after swapping rows 1 and 2:");
    A.show(); 

    A.swapCols(1,3);
    say("the above matrix after swapping columns 1 and 3:");
    A.show(); 

    B = A.transpose();
    say("the 4x3 transpose of the above matrix:");
    B.show();

    Matrix I = Matrix.identity(5);
    say("a 5x5 identity matrix:");
    I.show();

    A = Matrix.random(5,5,scale);
    say("a random 5x5 matrix (A):");
    A.show();

    A = Matrix.random(5,5,scale);
    say("another random 5x5 matrix (A):");
    A.show();

    A = Matrix.random(5,5,scale);
    say("yet another random 5x5 matrix (A):");
    A.show();

    B = A.transpose();
    say("its transpose (B):");
    B.show();

    say("the sum A+B (must be symmetric):");
    A.plus(B).show();

    // Adjusting print width of floating-point numbers for product
    printWidth = digits+8; // "***.digits": 6 positions left of sign + sign + dot = 8
    Matrix.setPrintWidth(printWidth);
    // Show the current parameters
    Matrix.showParameters();
    scale = 10000.0; // 100 x 100
    say("Current scale: "+scale);
    say("--------------------------------------------------------------------");

    Matrix AB = A.times(B);
    say("the product A*B (must be symmetric):");
    AB.show();

    Matrix BA = B.times(A);
    say("the product B*A (must be symmetric):");
    BA.show();

    sln("Is A*B = B*A? [this code slns: "+AB.equal(BA)+"]");
    sln("NB: since in general A*B != B*A, this is *very* likely to be false for *random* A and B.");
  }

  public static void randoms (int num, int rows, int cols)
  {    
    Matrix A = Matrix.random(rows,cols,scale);

    for (int i = 1; i <= num; i++)
      {
    	say("a random "+rows+"x"+cols+" [0.0,"+scale+"]-matrix ("+i+"):");
    	A.show();
    	A = Matrix.random(rows,cols,scale);
      }

    say("a random "+rows+"x"+cols+" row-stochastic matrix (A):");
    A.show();

    // A.makeRowStochastic();
    // say("make matrix A row-stochastic:");
    // A.show();

    // say("is A now row-stochastic indeed? ["+A.isRowStochastic()+"]");

  }

  public static void graphs (int order)
  {
    // Change desired number of decimal digits after the dot for this app
    int digits = 2;

    // Change print width of floating-point numbers for this app
    int printWidth = digits+6; // "***.digits": 4 positions left of sign + sign + dot = 6

    // Adjust Matrix parameter settings:
    Matrix.setCurrentSignificantDigits(digits);
    Matrix.setPrintWidth(printWidth);
    // Show the parameter settings:
    Matrix.showParameters();

    // Set and show the desired floating-point number scale for this app to 100.0
    scale = 100.0;
    say("Current scale: "+scale);
    say("--------------------------------------------------------------------");


    BipartiteGraph B = new BipartiteGraph(Matrix.randomDataArray(order,order,scale));

    ln();
    sln("A random "+order+"x"+order+" bipartite graph:");
    B.show();
    ln();
    B.showComponents();
    ln();
  }

}
