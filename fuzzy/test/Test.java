// FILE. . . . . d:/hak/hlt/src/hlt/math/fuzzy/test/Test.java
// EDIT BY . . . Hassan Ait-Kaci
// ON MACHINE. . Hp-Zbook
// STARTED ON. . Tue Jun 12 08:34:40 2018

// Last modified on Wed Dec 18 09:12:00 2019 by hak

package test;

import hlt.fot.*;
import hlt.fot.fuz.*;
import hlt.math.fuzzy.*;
import hlt.math.matrix.Matrix;
import hlt.math.matrix.NumberAlgebra;

import hlt.language.util.ArrayList;
import hlt.language.util.IntArrayList;
import hlt.language.util.DoubleArrayList;

public class Test
{
  public static void main (String[] args)
  {
    setParameters(fuzzyAlgebra,2,4,0.7,0.1,0.5);

    testFuzzyPackage();

    // say("------------------------------------------------------------------");
    // NumberAlgebra.showRegisteredAlgebras();
    // say("------------------------------------------------------------------");
  }

  static FuzzyAlgebra fuzzyAlgebra = FuzzyAlgebra.zadehAlgebra();

  static void setPrintingParameters (int digits, int widthIncrement)
  {
    // desired number of decimal digits after the dot for this app
    Matrix.setCurrentSignificantDigits(digits);
    // desired print width of floating-point numbers for this app
    Matrix.setPrintWidth(digits+widthIncrement);
  }

  static void setParameters
    (FuzzyAlgebra algebra, int digits, int widthIncrement, double threshold0, double threshold1, double bias)
  {
    // Set the fuzzy algebra to the specified fuzzy algebra
    FuzzyAlgebra.setCurrentAlgebra(algebra);

    // Printing parameters settings:
    setPrintingParameters(digits,widthIncrement);

    // Matrix parameters settings:
    Matrix.setThreshold0(threshold0); // any value between threshold0 and 1.0 may be
			       // randomly rounded to 0.0 according to
			       // current bias (high value favors 0.0s)
    Matrix.setThreshold1(threshold1); // any value between (1.0-threshold1) and 1.0 may be
			       // rounded up to 1.0 according to current
			       // bias (high value favors 1.0s)
    // uncomment the following to override default fair coin tosses (0.5):
    // Matrix.setBias(bias);   // lower to make coin tosses biased towards 0.0;
    // 			       // this makes random generation more  prone to yield 
    // 			       // 0.0s than 1.0s (and vice versa to bias towards 1.0)
  }    

  public static void testFuzzyPackage ()
  {
    testBasics();

    testFuzzyMatrix1();
    testSignatureSimilarity();
    testSignatureSimilarityOther();

    testFuzzyMatrix2();

  }
  
  public static void testBasics ()
  {
    setPrintingParameters(1,4); // 1-digit mantissa
    Matrix.showParameters();

    FuzzyMatrix M = new FuzzyMatrix(6);

    M.set(1,2,.8);
    M.set(1,3,.2);
    M.set(1,4,.6);
    M.set(2,5,.6);
    M.set(3,5,.5);
    M.set(4,5,.6);
    M.set(4,6,.4);

    say("a 6x6 matrix M:");
    M.show();

    say("M's reflexive closure:");
    M.reflexive_closure().show();

    say("M's reflexive-transitive closure:");
    M.reflexive_closure().transitive_closure().show();

    say("M's preorder closure (should be the same as the reflexive-transitive):");
    M.preorder_closure().show();

    say("this is M again: is it anti-symmetric? (should be yes)");
    M.show();
    sln("answer: "+(M.isAntiSymmetric()?"yes it is!":"no it is not."));

    say("modify M in-place with M.set(5,3,.7):");
    M.set(5,3,.7);
    M.show();
    say("is this new M anti-symmetric? (should be no)");
    sln("answer: "+(M.isAntiSymmetric()?"yes it is!":"no it is not."));

    say("modified M's preorder closure:");
    M.preorder_closure().show();

    say("modified M's similarity closure:");
    M.i_similarity_closure().show();

    DoubleArrayList degrees = M.computeDegrees();
    sln("M's set of degrees: "+setForm(degrees));

    sln("M's fuzzy partitions:");

    for (int k=0; k<degrees.size(); k++)
      {
    	double cut = degrees.get(k);
    	say("at or over cut "+cut+":");
    	IntArrayList[] classes = M.partition(cut);
    	for (int row=0; row<M.rows(); row++)
    	  say("["+(row+1)+"] = "+setForm(classes[row]));
      }

  }

  public static void testFuzzyMatrix1 ()
  {
    say("*** TESTING FuzzyMatrix 2\n");

    // say("the 6x6 identity I:");

    // FuzzyMatrix I = FuzzyMatrix.identity(6);
    // I.show();

    say("a 6x6 matrix M:");
    FuzzyMatrix M = new FuzzyMatrix(6);
    // M.show();
    
    // say("adding some pairs to M:");
    M.set(1,2,.2);
    M.set(1,3,.4);
    M.set(3,4,.8);
    M.set(4,6,.7);;
    M.show();
        
    // say("modifying M:");
    // M.set(1,2,.3);
    // M.set(1,4,.5);
    // M.set(2,4,1.0);
    // M.set(2,5,.4);
    // M.set(4,6,.2);
    // M.show();

    sln("M's set of degrees: "+M.computeDegrees());

    say("the in-place transpose of M:");
    M.i_transpose();
    M.show();

    say("the in-place reflexive closure of the modified M:");
    M.i_reflexive_closure();
    M.show();

    say("the in-place symmetric closure of the modified M");
    M.i_symmetric_closure();
    M.show();

    // save resulting M as A:
    FuzzyMatrix A = new FuzzyMatrix(M);
    say("saving the modified M into a new matrix A:");
    A.show();

    say("the in-place transitive closure of M:");
    M.i_transitive_closure();
    M.show();

    say("the transitive closure of A (should be the same as M's):");
    A.transitive_closure().show();

    say("the in-place similarity closure of A (should be the same as the two previous ones):");
    A.i_similarity_closure().show();

    ////////////////////////////////////////////////////////////////////////

    FuzzyMatrix result;

    sln("------------------------------------------------------------------------");
    FuzzyAlgebra.setProbabilisticAlgebra();
    sln("setting the current fuzzy algebra to: "+NumberAlgebra.getCurrentAlgebra());

    // System.err.println(">>> \tTESTING CURRENT FUZZY ALGEBRA OPERATIONS: ");
    // System.err.println(">>> \t"+0.3+" \\/ "+0.4+" = "+
    // 		       ((ProbabilisticAlgebra)NumberAlgebra.getCurrentAlgebra()).sum(0.3,0.4));
    // System.err.println(">>> \t"+0.3+" /\\ "+0.4+" = "+
    // 		       ((ProbabilisticAlgebra)NumberAlgebra.getCurrentAlgebra()).product(0.3,0.4));

    result = M.similarity_closure();
    say("M's similarity closure with "+NumberAlgebra.getCurrentAlgebra()+": ");
    result.show();

    sln("M's set of degrees with "+NumberAlgebra.getCurrentAlgebra()
	+": "+result.computeDegrees());

    sln("------------------------------------------------------------------------");
    FuzzyAlgebra.setLukasieviczAlgebra();
    sln("setting the current fuzzy algebra to: "+NumberAlgebra.getCurrentAlgebra());

    // System.err.println(">>> \tTESTING CURRENT FUZZY ALGEBRA OPERATIONS: ");
    // System.err.println(">>> \t"+0.3+" \\/ "+0.4+" = "+
    // 		       ((LukasieviczAlgebra)NumberAlgebra.getCurrentAlgebra()).sum(0.3,0.4));
    // System.err.println(">>> \t"+0.3+" /\\ "+0.4+" = "+
    // 		       ((LukasieviczAlgebra)NumberAlgebra.getCurrentAlgebra()).product(0.3,0.4));

    result = M.similarity_closure();
    say("M's similarity closure with "+NumberAlgebra.getCurrentAlgebra()+": ");
    result.show();

    sln("M's set of degrees with "+NumberAlgebra.getCurrentAlgebra()
	+": "+result.computeDegrees());

    sln("------------------------------------------------------------------------");
    FuzzyAlgebra.setZadehAlgebra();
    sln("setting the current fuzzy algebra to: "+NumberAlgebra.getCurrentAlgebra());

    // System.err.println(">>> \tTESTING CURRENT FUZZY ALGEBRA OPERATIONS: ");
    // System.err.println(">>> \t"+0.3+" \\/ "+0.4+" = "+
    // 		       ((ZadehAlgebra)NumberAlgebra.getCurrentAlgebra()).sum(0.3,0.4));
    // System.err.println(">>> \t"+0.4+" /\\ "+0.4+" = "+
    // 		       ((ZadehAlgebra)NumberAlgebra.getCurrentAlgebra()).product(0.3,0.4));

    result = M.similarity_closure();
    say("M's similarity closure with "+NumberAlgebra.getCurrentAlgebra()+": ");
    result.show();

    DoubleArrayList degrees = result.computeDegrees();

    sln("M's set of degrees with "+NumberAlgebra.getCurrentAlgebra()
	+": "+result.computeDegrees());

    sln("M's fuzzy partitions with "+NumberAlgebra.getCurrentAlgebra()+": ");

    for (int k=0; k<degrees.size(); k++)
      {
    	double cut = degrees.get(k);
    	say("at or over cut "+cut+":");
    	IntArrayList[] classes = result.partition(cut);
    	for (int row=0; row<result.rows(); row++)
    	  say("["+(row+1)+"] = "+setForm(classes[row]));
      }

  }

  public static void testSignatureSimilarity ()
  {
    SimilarFunctorSignature signature = new SimilarFunctorSignature();

    // Signature and similarity of example 8 page 27 of fuzfotlat.pdf
    
    Functor a = signature.functor("a",0);
    Functor b = signature.functor("b",0);
    Functor c = signature.functor("c",0);
    Functor d = signature.functor("d",0);
    Functor f = signature.functor("f",2);
    Functor g = signature.functor("g",2);
    Functor h = signature.functor("h",2);
    Functor l = signature.functor("l",3);

    double[][] data = 
                   //   a     b     c     d     f     g    l     h
      /* a */      { { 0.0,  0.7,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
      /* b */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
      /* c */      , { 0.0,  0.0,  0.0,  0.6,  0.0,  0.0, 0.0, 0.0 }
      /* d */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
      /* f */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
      /* g */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
      /* l */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.8 }
      /* h */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0, 0.0, 0.0 }
                   };

    showPartitions(signature,data);
  }

  public static void testSignatureSimilarityOther ()
  {
    SimilarFunctorSignature signature = new SimilarFunctorSignature();

    // Signature and similarity of example 8 page 27 of fuzfotlat.pdf
    
    Functor a = signature.functor("a",0);
    Functor b = signature.functor("b",1);
    Functor e = signature.functor("e",2);
    Functor f = signature.functor("f",3);
    Functor g = signature.functor("g",4);
    Functor h = signature.functor("h",5);

    double[][] data1 = 
                   //   a     b     e     f     g     h
      /* a */      { { 0.0,  0.2,  0.4,  0.5,  0.0,  0.0 }
      /* b */      , { 0.0,  0.0,  0.0,  1.0,  0.4,  0.0 }
      /* e */      , { 0.0,  0.0,  0.0,  0.8,  0.6,  0.0 }
      /* f */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.7 }
      /* g */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* h */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
                   };

    showPartitions(signature,data1);
    
    double[][] data2 = 
                   //   a     b     e     f     g     h
      /* a */      { { 0.0,  0.2,  0.4,  0.0,  0.0,  0.0 }
      /* b */      , { 0.0,  0.0,  0.0,  1.0,  0.0,  0.0 }
      /* e */      , { 0.0,  0.0,  0.0,  0.8,  0.6,  0.0 }
      /* f */      , { 0.0,  0.0,  0.0,  0.0,  0.9,  0.7 }
      /* g */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* h */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
                   };

    showPartitions(signature,data2);
    
    double[][] data3 = 
                   //   a     b     e     f     g     h
      /* a */      { { 0.0,  0.8,  0.0,  0.0,  0.0,  0.0 }
      /* b */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* e */      , { 0.0,  0.0,  0.0,  0.6,  0.7,  0.4 }
      /* f */      , { 0.0,  0.0,  0.0,  0.0,  0.9,  0.7 }
      /* g */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* h */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
                   };

    showPartitions(signature,data3);
    
    double[][] data4 = 
                   //   a     b     e     f     g     h
      /* a */      { { 0.0,  0.3,  0.0,  0.5,  0.0,  0.0 }
      /* b */      , { 0.0,  0.0,  0.0,  0.5,  0.4,  0.0 }
      /* e */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* f */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.2 }
      /* g */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
      /* h */      , { 0.0,  0.0,  0.0,  0.0,  0.0,  0.0 }
                   };

    showPartitions(signature,data4);
  }

  private static void showPartitions (SimilarFunctorSignature signature, double[][] data)
  {
    int size = signature.size();

    say("------------------------------------------------------------------------");

    say("a signature of "+size+" functors: "+signature);

    SignatureSimilarity sim = new SignatureSimilarity(signature,data);

    say("the "+size+"x"+size+
		       " similarity closure of the similar pairs in this signature:");
    sim.show();

    DoubleArrayList degrees = sim.computeDegrees();

    sln("and the set of degrees: "+setForm(degrees));

    sln("and the fuzzy partitions:");

    for (int k = 0; k < degrees.size(); k++)
      {
	double cut = degrees.get(k);

	// warn
	sim.sayIfTracing
	  ("computing partition["+k+"] corresponding to degree "+cut);

	say(">= "+cut+": "+sim.partitionToString(sim.getPartition(k)));
      }
  }

  private static String setForm (ArrayList list)
  {
    StringBuilder buf = new StringBuilder("{");

    int size = list.size();
    for (int i=0; i<size; i++)
      buf.append(list.get(i)+(i==size-1?"":","));

    return (buf.append("}")).toString();
  }
  
  private static String setForm (IntArrayList list)
  {
    StringBuilder buf = new StringBuilder("{");

    int size = list.size();
    for (int i=0; i<size; i++)
      buf.append((list.get(i)+1)+(i==size-1?"":","));

    return (buf.append("}")).toString();
  }
  
  private static String setForm (DoubleArrayList list)
  {
    StringBuilder buf = new StringBuilder("{");

    int size = list.size();
    for (int i=0; i<size; i++)
      buf.append(list.get(i)+(i==size-1?"":", "));

    return (buf.append("}")).toString();
  }

  public static void testFuzzyMatrix2 ()
  {
    setPrintingParameters(2,4); // 2-digit mantissa
    Matrix.showParameters();

    double[][] d = { { 0.00, 0.18, 0.23, 0.36 }
                   , { 0.45, 0.55, 0.61, 0.79 }
                   , { 0.82, 0.97, 1.00, 1.00 }
                   };

    // 1
    say("*** TESTING Fuzzy Matrix Methods and Operations 2\n");

    FuzzyMatrix M = new FuzzyMatrix(d); // shares the actual array d, not a copy
    // Could also be:
    // FuzzyMatrix M = new FuzzyMatrix(d,true); // true: to copy and ensure only degrees within [0.0,1.0]
    say("a 3x4 fuzzy matrix M defined from a preset data array:");
    M.show();

    FuzzyMatrix A = (FuzzyMatrix)FuzzyMatrix.random(5,8);
    // 2
    say("a random 5x8 fuzzy matrix A:");
    A.show();

    FuzzyMatrix At = (FuzzyMatrix)A.transpose();
    // 3
    say("A's transpose At, a "+At.rows()+" by "+At.cols()+" fuzzy matrix:");
    At.show();

    FuzzyMatrix B = (FuzzyMatrix)FuzzyMatrix.random(5,8);
    // 4
    say("another random 5x8 fuzzy matrix B:");
    B.show();

    FuzzyMatrix Bt = (FuzzyMatrix)B.transpose();
    // 5
    say("B's transpose Bt, a "+Bt.rows()+" by "+Bt.cols()+" fuzzy matrix:");
    Bt.show();

    say("Fuzzy matrix addition A.plus(B) (also a 5x8 fuzzy matrix):");
    A.plus(B).show();

    // 6
    say("Fuzzy matrix multiplication A.times(Bt) (a 5x5 fuzzy matrix):");

    A.times(Bt).show();

    // 7
    say("A.plus(At.transpose())  (i.e., itself):");
    A.plus(At.transpose()).show();

    // 8
    say("A.times(A.transpose()) (a "+A.rows()+"x"+At.cols()+" matrix):");
    A.times(At).show();

  }

  ////////////////////////////////////////////////////////////////////////
  static int number = 0;  // for debugging
  static void ln ()
  { FuzzyMatrix.ln(); }
  static void say (String s)
  // { FuzzyMatrix.say(s); }
  { FuzzyMatrix.say("["+(++number)+"]\t"+s); } // for debugging
  static void jot (String s)
  // { FuzzyMatrix.jot(s); }
  { FuzzyMatrix.jot("["+(++number)+"]\t"+s); } // for debugging
  static void sln (String s)
  // { FuzzyMatrix.sln(s); }
  { FuzzyMatrix.sln("["+(++number)+"]\t"+s); } // for debugging
  ////////////////////////////////////////////////////////////////////////
}
