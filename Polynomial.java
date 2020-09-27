/*
 * Ricky Schrombeck
 * CS 275
 * Professor Zhao
 * 11/07/2019
 * HW3
 */
import java.util.*;

public class Polynomial
{
  TermNode first;
  
  public static class TermNode
  {
    private int exp;
    private double coefficent;
    private TermNode next;
    
    public TermNode(int exponent, double coeff, TermNode nextTerm)
    {
      exp = exponent;
      coefficent = coeff;
      next = nextTerm;
    }
  }
  /*
  public static void main(String[] args)//Main I used to test methods when writing code.
  {
    Polynomial a1 = new Polynomial(1.0, 0);
    a1.add_to_coef(1.0, 3);
    a1.add_to_coef(1.0, 1);
    Polynomial a2 = new Polynomial(1.0, 0);
    a2.assign_coef(1.0, 1);
    a2.assign_coef(1.0, 2);
    System.out.println(a1 + " = a1(example for add_to_coef)");
    System.out.println(a2 + " = a2(example for assign_coef)");
    Polynomial a3 = a1.add(a2);
    System.out.println(a3 + " = a3[example for a1.add(a2)]");
    a3 = a1.multiply(a2);
    System.out.println(a3 + " = a3[example for a1.multiply(a2)]");
    System.out.println(a3.eval(0) + " = a3.eval(0)");
    System.out.println(a3.eval(1) + " = a3.eval(1)");
    System.out.println(a3.eval(2) + " = a3.eval(2)");
    System.out.println(a3.coefficent(3) + " = a3.coefficent(3)");
    System.out.println(a3.coefficent(5) + " = a3.coefficent(5)");
    System.out.println(a3.coefficent(6) + " = a3.coefficent(6)");
  }
  */
    public static void main(String[] args)
  {
    Polynomial p1 = new Polynomial();
    System.out.println("p1 = new Polynomial(): p1 = " + p1);
    
    p1.add_to_coef(1, 100);    
    System.out.println("        p1.add_to_coef(1, 100), p1 = " + p1);
    
    p1.assign_coef(-1, 5);            
    System.out.println("        p1.assign_coef(-1, 5),  p1 = " + p1);
    
    p1.add_to_coef(-1, 0);            
    System.out.println("        p1.add_to_coef(-1, 0),  p1 = " + p1);
    
    System.out.println("        p1.coefficient(110) = " + p1.coefficient(110));
    System.out.println("        p1.coefficient(80) = " + p1.coefficient(80));                       
    System.out.println("        p1.coefficient(5) = " + p1.coefficient(5));                       
    System.out.println("        p1.coefficient(3) = " + p1.coefficient(3));                       
    
                       
    p1.assign_coef(0, 0);      
    System.out.println("        p1.assign_coef(0, 0),  p1 = " + p1);
    
    p1.assign_coef(0, 100);            
    System.out.println("        p1.assign_coef(0, 100),  p1 = " + p1);
   
    p1.add_to_coef(1, 0);
    System.out.println("        p1.add_to_coef(1, 0),  p1 = " + p1);
    
    p1.assign_coef(0, 0);      
    System.out.println("        p1.assign_coef(0, 0),  p1 = " + p1);
    
   System.out.println("        p1(2) = " + p1.eval(2)); 
    System.out.println();
    
    Polynomial p3 = new Polynomial (p1);
        
    System.out.println("p3 = new Polynomial (p1),  p3 = " + p3); 
    
    System.out.println("        p3 == p1 " + (p3 == p1));
    
    System.out.println("        p3.equals(p1) " + (p3.equals(p1)));

    p3.assign_coef(1, 5);           
    System.out.println("        p3.assign_coef(1, 5),  p3 = " + p3);
  
    System.out.println("        p3.equals(p1) " + (p3.equals(p1)));
        
    p3.assign_coef(2,2);
    
    System.out.println("        p3.assign_coef(2,2),  p3 = " + p3);
    
 
    System.out.println("        p3(2) = " + p3.eval(2)); 
    System.out.println();
   
    
    p1.assign_coef(-2,2);
     System.out.println("        p1.assign_coef(-2,2),  p1 = " + p1);   
    
    System.out.println("        p3 + p1= " + p3.add(p1));   
  
    
    System.out.println();      
    Polynomial p2 = new Polynomial(1, 0); 
    
    System.out.println("p2 = new Polynomial(1): p2 = " + p2); 
    
    p2.assign_coef(1, 5);   
    
    System.out.println("      p2.assign_coef(1,5), p2 = " + p2);
    System.out.println();  
    System.out.println("      p2 * (-1) = " + p2.multiply(new Polynomial(-1, 0)));  
    
    
    System.out.println("      p2 * p1 = " + p1.multiply(p2));    
    
    p1.assign_coef(0,2);
    System.out.println("      p1.assign_coef(0,2),  p1 = " + p1); 
    
    p1.assign_coef(-1,0);
    System.out.println("      p1.assign_coef(-1,0),  p1 = " + p1); 
    
    p1.assign_coef(1,5);
    System.out.println("      p1.assign_coef(1,5),  p1 = " + p1); 
    
    System.out.println("      p2 * p1 = " + p1.multiply(p2));    
    
 
  }
  public Polynomial()
  {
    first = new TermNode(0, 0, null);
  }
  
  public Polynomial(double a0, int exp)
  {
    first = new TermNode(exp, a0, null);
  }
  
  public Polynomial(Polynomial p)
  {
    TermNode last = null, cursor = p.first;
    first = new TermNode(cursor.exp, cursor.coefficent, null);
    last = first;
    for(cursor = p.first.next; cursor != null; cursor = cursor.next)
    {
      last.next = new TermNode(cursor.exp, cursor.coefficent, null);
      last = last.next;
    }
  }
  
  public void add_to_coef(double amount, int exponent)//Adds coefficent to existing node, or creates new one.
  {
    TermNode last = first;
    for(TermNode cursor = first; cursor != null; cursor = cursor.next)
    {
      if(exponent > first.exp)
      {
        first = new TermNode(exponent, amount, first);
        return;
      }
      
      if(cursor.exp < exponent && last.exp > exponent)
      {
        last.next = new TermNode(exponent, amount, cursor);
        return;
      }
      
      if(cursor.exp == exponent)
      {
        cursor.coefficent += amount;
        return; 
      }
      
      last = cursor;
    }
  }
  
  
  public void assign_coef(double coeff, int exponent)//assign coefficent to existing node, or create a new one
  {
    TermNode last = first;
    for(TermNode cursor = first; cursor != null; cursor = cursor.next)
    {
      if(exponent > first.exp)
      {
        first = new TermNode(exponent, coeff, first);
        return;
      }
      
      if(cursor.exp < exponent && last.exp > exponent)
      {
        last.next = new TermNode(exponent, coeff, cursor);
        return;
      }
      
      if(cursor.exp == exponent)
      {
        cursor.coefficent = coeff;
        return; 
      }
      
      last = cursor;
    }
  }
  
  public double coefficient(int exponent)
  {
    for(TermNode cursor = first; cursor != null; cursor = cursor.next)
    {
      if(exponent == cursor.exp)
        return cursor.coefficent;
    }
    return 0;
  }
  
  public double eval(double x)
  {
    double sum = 0, prodOfX;
    
    for(TermNode cursor = first; cursor != null; cursor = cursor.next)
    {
      prodOfX = x;
      
      for(int i = 1; i < cursor.exp; i++)
        prodOfX = prodOfX * x;
      
      if(cursor.exp > 0) 
        sum += cursor.coefficent * prodOfX;
      if(cursor.exp < 0)
        sum += cursor.coefficent / prodOfX;
      if(cursor.exp == 0)
        sum += cursor.coefficent;
    }
    return sum;
  }
  
  public String toString()
  { 
    String s = null;
    if(first.coefficent != 0)
    {
    s = Double.toString(first.coefficent);
    if(first.exp != 0)
      s += "x";
    if(first.exp != 1 && first.exp != 0)
      s += "^(" + first.exp + ")";
    }else 
      s = "";
    for(TermNode cursor = first.next; cursor != null; cursor = cursor.next)
    {
      if(cursor.coefficent > 0)
      {
        s += "+";
        s += cursor.coefficent;
      }
      
      if(cursor.coefficent < 0)
        s += cursor.coefficent;
      
      if(cursor.exp != 0 && cursor.coefficent !=0)
        s += "x";
      if(cursor.exp != 1 && cursor.exp != 0 && cursor.coefficent != 0)
        s += "^(" + cursor.exp + ")";
    }
    if(s == "")
      s = "0.0";
    return s;
  }
  
  public Polynomial add(Polynomial p)
  {
    Polynomial sum = new Polynomial(p);
    for(TermNode cursor = first; cursor != null; cursor = cursor.next)
    {
      sum.add_to_coef(cursor.coefficent, cursor.exp);
    }
    return sum;
  }
  
  public Polynomial multiply(Polynomial p)
  {
    Polynomial sum = new Polynomial();
    for(TermNode cursorA = first; cursorA != null; cursorA = cursorA.next)
      for(TermNode cursorB = p.first; cursorB != null; cursorB = cursorB.next)
      {
       sum.add_to_coef((cursorA.coefficent * cursorB.coefficent), (cursorA.exp + cursorB.exp));
      }
    return sum;
  }
}