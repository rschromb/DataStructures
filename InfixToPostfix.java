/*Ricky Schrombeck
 * CS 275
 * Professor Zhao
 * 11/30/2019
 */
import java.util.*;
import java.util.Stack;
import java.util.regex.Pattern;
public class InfixToPostfix
{
  public static void main(String[] args)
  {
    String s = null;
    String sum = null;
    
    System.out.println("Please enter a mathematical expression such as one of the following");
    System.out.println("1+2 or (1+2)-3 or 4-(6+3-(2+1))");
    System.out.println("Make sure to use an operation before parenthesis. Also only use numbers and the");
    System.out.println("following operators: +, -, *, /, (, ).");
    System.out.println("All other input will cause an error.");
        
    do
    {
      Scanner input = new Scanner(System.in);
      System.out.println("Use the input 'exit' to leave the program.");
      s = input.nextLine();//1+2 //"1+2*3"; //"4-(6+3-(2+1))"; //"1+(2-(3*(4/5))) //"(((1+7)*(3/4))-(2*5))";
      
      if(!s.contentEquals("exit"))
      {
        sum = readInput(s);
        System.out.print(s + " = ");
        System.out.println(sum);
      }
    }while(!s.contentEquals("exit"));
  }
  //Reads String object with scanner, looking for UNSIGNED_DOUBLE and CHARACTER elements. UNSIGNED_DOUBLE elements
  //go straight to the operands stack. Legal CHARACTER input goes into the operations stack, which gets evaulated 
  //in the evalStacks method. This cycle is repeated until the input is empty. Illegal operations throw 
  //IllegalArgumentException.
  private static String readInput(String s)
  {
    Scanner input = new Scanner(s);
    Stack<Character> operations = new Stack<Character>();
    Stack<String> operands = new Stack<String>();
    String next, post;
    char first;
    
    while(input.hasNext())
    {
      if(input.hasNext(UNSIGNED_DOUBLE))
      {
        next = input.findInLine(UNSIGNED_DOUBLE);
        operands.push(next);
      }
      else
      {
        next = input.findInLine(CHARACTER);
        first = next.charAt(0);
        
        switch(first)
        {
          case '(':
            operations.push(first);
            break;
          case '*':
          case '/':
          case '+':
          case '-':
            operations.push(first);
            evalStack(operands, operations);
            break;
          case ')':
            rightPar(operands, operations);
            break;
          default:
            throw new IllegalArgumentException("Illegal Operation");
        }     
      }
//      System.out.println(operands);
//      System.out.println(operations);
    }
    
    while(!operations.empty() && operations.peek() != '(')
      operands.push(charToString(operations.pop()));
    
    if(!operations.empty())
      throw new IllegalArgumentException("Illegal Expression");
    
    next = solve(operands, operations);
    
    return next;
  }
  
  private static String solve(Stack<String> operands, Stack<Character> operations)
  {
    if(!operations.empty())
      throw new IllegalArgumentException("Illegal Expression");
    
    String post = "";
    Stack<String> postStore = new Stack<String>();
    
    while(!operands.empty())
    {
      postStore.push(operands.pop());
    }
    
    while(!postStore.empty())
    {
      post += postStore.peek() + " ";
      switch(postStore.peek())
      {
        case "*":
        case "/":
        case "+":
        case "-":
          calculate(operands, postStore);
          break;
        default:
          operands.push(postStore.pop());
      }
    }
    
    if(operands.size() >1)
      throw new IllegalArgumentException("Illegal Expression");
    
    System.out.println("Postfix expression = " + post);
    String sum = operands.pop();
    return sum;
  }
  
  private static void calculate(Stack<String> operands, Stack<String> postStore)
  {
    if(operands.size() < 2)
      throw new IllegalArgumentException("Illegal Expression");
    
    double operand1, operand2, sum;
    
    operand2 = Double.valueOf(operands.pop());
    operand1 = Double.valueOf(operands.pop());
    
    if(operand2 == 0.0 && postStore.peek() == "/")
      throw new IllegalArgumentException("Undefined expression: Cannot divide by 0");
    
    switch(postStore.pop())
    {
      case "+":
        sum = operand1 + operand2;
        operands.push(String.valueOf(sum));
        return;
      case "-":
        sum = operand1 - operand2;
        operands.push(String.valueOf(sum));
        return;
      case "*":
        sum = operand1 * operand2;
        operands.push(String.valueOf(sum));
        return;
      case "/":
        sum = operand1 / operand2;
        operands.push(String.valueOf(sum));
        return;
    }
  }
  //Evaluates stack after legal input has been verified. If operations.size() is bigger than 1, it evaluates by
  //switch statement what opertion to execute. 
  private static void evalStack(Stack<String> operands, Stack<Character> operations)
  {
    if(operations.empty())
      throw new IllegalArgumentException("Illegal Expression");
    
    if(operations.size() == 1)
      return;
    
    char temp = operations.pop();
    
    if(operations.peek() == '(')
    {
      operations.push(temp);
      return;
    }
    
    switch(temp)
    {
      case '+':
      case '-':
        operands.push(charToString(operations.pop()));
        operations.push(temp);
        break;
      case '*':
      case '/':
        checkPrecedence(temp, operands, operations);
        break;
    }
  }
  
  //Check the precedence of the operation, pushing whichever has higher precedence onto operands stack.
  private static void checkPrecedence(char temp, Stack<String> operands, Stack<Character> operations)
  {
    if(operations.peek() == '*' || operations.peek() == '/')
    {
      operands.push(charToString(operations.pop()));
      operations.push(temp);
      return;
    }
    
    while(!operations.empty())
    {
      if(operations.peek() != '+' && operations.peek() != '-')
        operands.push(charToString(operations.pop()));
      
      if(operations.peek() == '+' || operations.peek() == '-')
      {
        operations.push(temp);
        return;
      }
    }
    
    return;
  }
  //Converts character operations to String, so they can go into the operands stack.
  private static String charToString(char operation)
  {
    
    switch(operation)
    {
      case '+':
        return "+";
      case '-':
        return "-";
      case '*':
        return "*";
      case '/':
        return "/";
      default:
        throw new IllegalArgumentException("Illegal Operation");
    }
  }
  //Right parenthesis has been reached, method pushes any operations between right and left onto operands stack.
  //Then pops off one left parenthesis. Throws exception if it does not find a left parenthesis.
  private static void rightPar(Stack<String> operands, Stack<Character> operations)
  {
    while(!operations.empty() && operations.peek() != '(')
      operands.push(charToString(operations.pop()));
    
    if(operations.empty())
      throw new IllegalArgumentException("Unbalanced Parenthesis");
    else
      operations.pop();
    
    return;
  }
  public static final Pattern CHARACTER =
    Pattern.compile("\\S.*?");
  public static final Pattern UNSIGNED_DOUBLE =
    Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?"); 
}