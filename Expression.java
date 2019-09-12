package A2;
import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
  private ArrayList<String> tokenList;

  //  Constructor
  /**
   * The constructor takes in an expression as a string
   * and tokenizes it (breaks it up into meaningful units)
   * These tokens are then stored in an array list 'tokenList'.
   */

 Expression(String expressionString) throws IllegalArgumentException {
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		// ADD YOUR CODE BELOW HERE

		expressionString = expressionString.replaceAll("\\s", "");
		String pattern = "(?<![+-])(?=[*/+-[\\\\]()])|(?<=[*/+-[\\\\]()])(?![+-])";

		for (final String element : expressionString.split(pattern)) {
			tokenList.add(element);
		}

		// ADD YOUR CODE ABOVE HERE
	}

  /**
   * This method evaluates the expression and returns the value of the expression
   * Evaluation is done using 2 stack ADTs, operatorStack to store operators
   * and valueStack to store values and intermediate results.
   * - You must fill in code to evaluate an expression using 2 stacks
   */
  public Integer eval(){
    Stack<String> operatorStack = new Stack<String>();
    Stack<Integer> valueStack = new Stack<Integer>();

    //ADD YOUR CODE BELOW HERE
    //..
    for(int i = 0; i < tokenList.size(); i++) { //scan through every element of the ArrayList
      if (!isInteger(tokenList.get(i)) && !tokenList.get(i).equals("(")) { //if an operator
        if (!tokenList.get(i).equals("(") && !tokenList.get(i).equals("[")) {//if not a left bracket
          operatorStack.push(tokenList.get(i));
        }
          switch (tokenList.get(i)) {
              case ")":
                  operatorStack.pop();
                  String prev = operatorStack.peek();
        switch (prev) {
            case "+":
                {
                    int second = valueStack.pop();
                    int first = valueStack.pop();
                    int sum = first + second;
                    valueStack.push(sum);
                    break;
                }
            case "-":
                {
                    int second = valueStack.pop();
                    int first = valueStack.pop();
                    int diff = first - second;
                    valueStack.push(diff);
                    break;
                }
            case "*":
                {
                    int second = valueStack.pop();
                    int first = valueStack.pop();
                    int times = first * second;
                    valueStack.push(times);
                    break;
                }
            case "/":
                {
                    int secondNum = valueStack.pop();
                    int firstNum = valueStack.pop();
                    int divide = firstNum / secondNum;
                    valueStack.push(divide);
                    break;
                }
            case "++":
                {
                    int Num = valueStack.pop();
                    int addOne = Num + 1;
                    valueStack.push(addOne);
                    break;
                }
            case "--":
                {
                    int Num = valueStack.pop();
                    int minus = Num - 1;
                    valueStack.push(minus);
                    break;
                }
        }
operatorStack.pop();
          //If it doesnt follow the normal sequence then stack is empty
                  break;
              case "]":
                  int Num1 = valueStack.pop();
                  int abs1 = Math.abs(Num1);
                  valueStack.push(abs1);
                  break;
          }
        System.out.println("values: " + valueStack);
        System.out.println("operators: " + operatorStack);
      }else if (isInteger(tokenList.get(i))){//when it is an integer push it to value sack
        valueStack.push(Integer.valueOf(tokenList.get(i)));
      }
    }
    int result = valueStack.peek(); //result is at the top of the stack.
    return result;
    //..
    //ADD YOUR CODE ABOVE HERE

  }

  //Helper methods
  /**
   * Helper method to test if a string is an integer
   * Returns true for strings of integers like "456"
   * and false for string of non-integers like "+"
   * - DO NOT EDIT THIS METHOD
   */
  private boolean isInteger(String element){
    try{
      Integer.valueOf(element);
    }catch(NumberFormatException e){
      return false;
    }
    return true;
  }

  /**
   * Method to help print out the expression stored as a list in tokenList.
   * - DO NOT EDIT THIS METHOD
   */

  @Override
  public String toString(){
    String s = new String();
    for (String t : tokenList )
      s = s + "~"+  t;
    return s;
  }

}
