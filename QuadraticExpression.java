/** 
 * This class creates an object for the quadratic expression ax^2 + bx + c.
 * @author Ricky Schrombeck
 */
import java.util.*;

public class QuadraticExpression implements Cloneable{
  private double a, b, c;
  /**Default constructor sets values of a, b, and c to be zero*/
  public QuadraticExpression(){
    a = 0; b = 0; c = 0;
  }
  /**Constructor with parameters for a, b and c
    * @param A The coefficent for the first term in the equation
      @param B The coefficent for the second coefficent in the equation
      @param C The value of the constant at the end of the equation*/
  public QuadraticExpression(double A, double B, double C){
    a = A; b = B; c = C;
  }
  /**Returns expression as a String where coefficents will be displayed at numerical value */
  public String toString(){
    String temp = new String();
    
    if(a != 0)
      temp += a + "x^2 ";
    if(b > 0)
      temp += "+ " + b + "x ";
    if(b < 0)
      temp += "- " + -b + "x ";
    if(c < 0)
      temp += "- " + -c;
    if(c > 0)
      temp += "+ " + c;
    return temp;
  }
  /**Gives solution to expression with given value of 'x' 
     @param x Value in which will take the place of x in the equation to solve it
     @return Returns a double after solving the equation with the given value of x*/
  public double evaluate(double x){
    return a * (x * x) + b * x + c;
  }
  /**Set new value for coefficent a 
     @param newA Double value that will replace the current value of the coefficent 'a' within the equation*/
  public void setA(double newA){
    a = newA;
  }
  /**Set new value for coefficent b 
     @param newB Double value that will replace the current value of the coefficent 'b' within the equation*/
  public void setB(double newB){
    b = newB;
  }
  /**Set new value for coefficent c 
     @param newC Double value that will replace the current value of the coefficent 'c' within the equation*/
  public void setC(double newC){
    c = newC;
  }
  /**Takes in QuadraticExpression,
    and double r, multiplies a, b and c of the class by r.  
    @param r The value for how much the coefficents will be multiplied by
    @param q The object which will have a, b, and c scaled by the value of r
    @return Returns reference to new QuadraticExpression with scaled coefficents*/
  public static QuadraticExpression scale(double r, QuadraticExpression q){
    QuadraticExpression c = new QuadraticExpression();
    
    c.a = (q.a * r); c.b = (q.b * r); c.c = (q.c * r);
    
    return c;
  }
  /**Adds coefficent values of two QuadraticExpressions 
    @param a QuadraticExpression object to be added
    @param b Second object to be added
    @return Returns reference to new QuadraticExpression with the coefficents that are the sum of those in the
    parameter*/
  public static QuadraticExpression add(QuadraticExpression a, QuadraticExpression b){
    QuadraticExpression c = new QuadraticExpression();
    
    c.a = (a.a + b.a); c.b = (a.b + b.b); c.c = (a.c + b.c);
    
    return c;
  }
  /**Override method for equals method that returns true only if the a, b and c
    coefficents from both objects are equal.
    @param in QuadraticExpression to be compared to the calling object*/
  public boolean equals(Object in){
    if(in == null)
      return false;
    
    QuadraticExpression a = (QuadraticExpression) in;
    
    if(! (a instanceof QuadraticExpression))
      return false;
    
    if((this.a != a.a)||(this.b != a.b)||(this.c != a.c))
      return false;
    
    else
      return true;
  }
  /**Method to add the calling objects coefficents to those of the object in parameter. Calling object gets new
    * values
     @param q Object with coefficents to be added together with calling objects coefficents*/
  public void add( QuadraticExpression q){
    this.a = this.a + q.a; this.b = this.b + q.b; this.c = this.c + q.c;
  }
  /**Returns a reference to a deep copy of the calling object */
  protected QuadraticExpression clone(){
    QuadraticExpression temp;
    
    try{
       temp = (QuadraticExpression) super.clone( );
        }
    catch(CloneNotSupportedException e){
      throw new RuntimeException("This class is not cloneable");
        }
    
    return temp;
  }
  /**Returns the number of roots for the Quadratic Expression with the given values of a, b, and c 
     @return Returns the number of roots as an int*/
  public int numberOfRoots(){
    int roots = 0;
    double temp = (b * b) - 4 * (a * c);
    
    if(a == 0 && b == 0 && c == 0){
      roots = 3;
    }
    else if((a == 0 && b == 0) && (c != 0)){
      roots = 0;
    }
    else if(a == 0 && b != 0){
      roots = 1;
    }
    else if(a != 0 && temp < 0){
      roots = 0;
    }
    else if(a != 0 && temp == 0){
      roots = 1;
    }
    else if(a != 0 && temp > 0){
      roots = 2;
    }
    return roots;
  }
  /**Returns the double value of the smallest root for the equation with the given values of a, b, and c 
     @return Returns the smallest root of the equation in double format
     @throws IllegalArgumentException Indicates the expression has no roots*/
  public double smallerRoot() throws Exception{
    if(numberOfRoots() == 0)
    throw new IllegalArgumentException("This Quadratic Expression contains no roots");
    
    double temp = (b * b) - 4 * (a * c);
    
    if(numberOfRoots() == 1 && temp == 0){
    return (-b / (2 * a));
    }
    else if(numberOfRoots() == 1 && a == 0){
    return (-c / b);
    }
    
    else if(numberOfRoots() == 2){
     double a1 = (-b - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
     double a2 = (-b + Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
     if(a1 < a2)
       return a1;
     else 
       return a2;
    }
   return -Double.MAX_VALUE;
  }
  /**Returns the value of the largest root of the expression with the given values of a, b, and c 
     @return Returns the largest root in double format
     @throws IllegalArgumentException Indicates the expression has no roots*/
  public double largerRoot() throws Exception{
    if(numberOfRoots() == 0)
    throw new IllegalArgumentException("This Quadratic Expression contains no roots");
    
    double temp = (b * b) - 4 * (a * c);
    
    if(numberOfRoots() == 1 && temp == 0){
    return (-b / (2 * a));
    }
    else if(numberOfRoots() == 1 && a == 0){
    return (-c / b);
    }
    
    else if(numberOfRoots() == 2){
     double a1 = (-b - Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
     double a2 = (-b + Math.sqrt((b * b) - 4 * a * c)) / (2 * a);
     if(a1 > a2)
       return a1;
     else 
       return a2;
    }
   return -Double.MAX_VALUE;
  }
}