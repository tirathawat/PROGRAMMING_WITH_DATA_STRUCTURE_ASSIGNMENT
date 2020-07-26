import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Assignment5 {

    private static ArrayList <String> postfix = new ArrayList<>();
    private static Stack <String> oprStack = new Stack<>();
    private static Stack <Double> numStack = new Stack<>();
    private static double ans;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str;
        String[] token;
        NumberFormat nf = new DecimalFormat("##.###");
        int count = 1;

        do {
            //Input expression from user
            System.out.print(count++ + ".) " + "expression> ");
            str = in.nextLine();

            str = str.toLowerCase();//Change expression to lower case
            str = "(" + str + ")";//Add ( ) to transform Infix To Postfix
            str = addSpace(str);//Add space between operator


            if (str.equalsIgnoreCase("( help )") || str.equalsIgnoreCase("( token )") || str.equalsIgnoreCase("( end )")) {
                //User uses function that is not math function
                if (!str.equalsIgnoreCase("( end )")) {
                    //User want to see all function
                    System.out.println("    token = sin, cos, tan, asin, acos, atan, sqrt, log, exp, abs, cosec, sec, cot");
                    System.out.println("    +, -, *, /, ^, (, ), pi, e, ans, help, token, end");
                }
            }
            else {
                token = str.split(" ");//Split expression with " " and save it to token
                changeNegativeSign(token);//Change negative sign to !
                if (stateCorrect(token)) {
                    //Expression syntax is correct
                    transformInToPost(token);//Transform infix to postfix
                    ans = calculatePost();//Calculate answer from postfix
                    if (!Double.isNaN(ans) && !Double.isInfinite(ans)) System.out.println("    answer> " + nf.format(ans));//Get an answer then show answer
                    else System.out.println("    answer> error");//Error calculation
                }
                else System.out.println("    answer> error");//Expression syntax is not correct
            }
        }while(!str.equalsIgnoreCase("( end )"));//loop until user want to end program

        //end program
        System.out.println("    End program");
        System.out.println("    Program written by Tirathawat Chansarekorn 62070501022");
        in.close();
    }

    private static double calculatePost() {
        //This method used to calculate postfix expression
        double ans, num, num1, num2;
        int i, group;
        String token;
        for (i = 0; i < postfix.size(); i++) {

            //check group of member in postfix array list(token)
            token = postfix.get(i);
            group = checkGroup(token);


            if (group == 1) {
                //token is number or constant

                //change number(String) to double or change constant to number(double) then push it to number stack
                num1 = valueOf(token);
                numStack.push(num1);
            }
            else if (group >= 2 && group <= 4) {
                //token is binary operator
                //pop 2 number from number stack
                num1 = numStack.pop();
                num2 = numStack.pop();

                //Find the result of the mathematical operation of 2 numbers and then push it to number stack
                if (group == 2 && token.equals("+")) numStack.push(num2 + num1);
                else if (group == 2 && token.equals("-")) numStack.push(num2 - num1);
                else if (group == 3 && token.equals("*")) numStack.push(num2 * num1);
                else if (group == 3 && token.equals("/")) numStack.push(num2 / num1);
                else if (group == 4) numStack.push(Math.pow(num2, num1));
            }
            else if (group == 5) {
                //token is negative sign
                //pop number from number stack and then multiplied by negative and push it to number stack
                num1 = numStack.pop();
                numStack.push(-num1);
            }
            else if (group == 6) {
                //token is function
                //find pop number from number stack and find answer from function then push it to number stack
                if (token.equalsIgnoreCase("sin")) {
                    num = Math.sin(numStack.pop() * Math.PI / 180);
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("cos")) {
                    num = Math.cos(numStack.pop() * Math.PI / 180);
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("tan")) {
                    num = Math.tan(numStack.pop() * Math.PI / 180);
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("asin")) {
                    num = Math.asin(numStack.pop());
                    numStack.push(Math.toDegrees(num));
                }
                else if (token.equalsIgnoreCase("acos")) {
                    num = Math.acos(numStack.pop());
                    numStack.push(Math.toDegrees(num));

                }
                else if (token.equalsIgnoreCase("atan")) {
                    num = Math.atan(numStack.pop());
                    numStack.push(Math.toDegrees(num));
                }
                else if (token.equalsIgnoreCase("cosec")) {
                    num = Math.sin(numStack.pop() * Math.PI / 180);
                    if (num != 0)numStack.push(1/num);
                }
                else if (token.equalsIgnoreCase("sec")) {
                    num = Math.cos(numStack.pop() * Math.PI / 180);
                    if (num != 0)numStack.push(1/num);
                }
                else if (token.equalsIgnoreCase("cot")) {
                    num = Math.tan(numStack.pop() * Math.PI / 180);
                    if (num != 0)numStack.push(1/num);
                }
                else if (token.equalsIgnoreCase("sqrt")) {
                    num = Math.sqrt(numStack.pop());
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("log")) {
                    num = Math.log(numStack.pop());
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("exp")) {
                    num = Math.exp(numStack.pop());
                    numStack.push(num);
                }
                else if (token.equalsIgnoreCase("abs")) {
                    num = Math.abs(numStack.pop());
                    numStack.push(num);
                }
            }
        }
        ans = numStack.pop();//The remaining 1 on the stack is the answer to expression.
        return ans;//return answer
    }

    private static double valueOf(String str) {
        if (str.equalsIgnoreCase("pi")) return Math.PI;//token is PI constant
        else if (str.equalsIgnoreCase("e")) return Math.E;//token is e constant
        else if (str.equalsIgnoreCase("ans")) return ans;//token is answers from previous calculations
        else return Double.parseDouble(str);//convert token(string) to double
    }

    private static void transformInToPost(String[] token) {
        //This method used to transform infix to postfix
        int group, cur, prior, i;
        String buff;
        postfix.clear();//clear postfix array list
        oprStack.clear();//clear operator stack
        for (i = 0; i < token.length; i++) {
            group = checkGroup(token[i]);//check group of token
            if (group == 1) postfix.add(token[i]);//token is number then add it to postfix array list
            else if (group == 7) oprStack.push(token[i]);//token is left parenthesis then push it to operator stack
            else if (group >= 2 && group <= 6) {//token is operator negative sign or function
                do {
                    cur = checkGroup(token[i]);//check token group current
                    buff = oprStack.peek();//check top of operator stack then save to buff
                    prior = checkGroup(buff);//check group top of operator stack then save to prior
                    if (prior >= cur && prior <= 6) {
                        //prior important more than current

                        //pop prior then add it to postfix array list
                        buff = oprStack.pop();
                        postfix.add(buff);
                    }
                    //loop until prior is not operator negative sign or function and current important more than prior
                }while (prior >= cur && prior <= 6);
                oprStack.push(token[i]);//push current to operator stack
            }
            else if (group == 8) {
                //token is right parenthesis
                do {
                    //pop operator in stack then add it to postfix array list until operator in stack is (
                    buff = oprStack.pop();
                    if (!buff.equals("(")) postfix.add(buff);
                }while (!buff.equals("("));
            }
        }
    }

    private static boolean stateCorrect(String[] token) {
        //This method used to check syntax of expression
        int  state = 0, next = 0, countParent = 0, i;
        for (i = 1; i < token.length - 1 && state >= 0; i++) {
            state = next;//set current state
            next = checkGroup(token[i]);//check next state from token
            if (next >= 2 && next <= 4) next = 2;//next is math operator

            //find error state
            if (next == 0) return false;//next(token) isn't math operation
            if (next == 7) countParent++;//check number of parenthesis
            else if (next == 8) countParent--;//check number of parenthesis
            if (countParent < 0) return false;//right parenthesis more over
            else if (state == 0 && (next == 8 || next == 2)) return false;//Start with right parenthesis or operator
            else if (state == 1 && (next == 1 || next == 6 || next == 7)) return false;//after number is left parenthesis function number or constant
            else if (state == 2 && (next == 2 || next == 8)) return false;//after operator is operator or right parenthesis
            else if (state == 5 && (next == 2 || next == 8)) return false;//after negative sign is operator or right parenthesis
            else if (state == 6 && next != 7) return false;//after math function is left parenthesis
            else if (state == 7 && (next == 2 || next == 8)) return false;//after left parenthesis is operator or right parenthesis
         }
        return (countParent == 0);//return correctness of state that true or false
    }

    private static int checkGroup(String str) {
        //This method used to check token type
        if (isNumber(str) || isConstant(str)) return 1;//token is number or constant
        else if (str.matches("[-+]")) return 2;//token is plus or minus operator
        else if (str.matches("[*/]")) return 3;//token is multiply or divide operator
        else if (str.equals("^")) return 4;//token is power operator
        else if (str.equals("!")) return 5;//token is negative sign
        else if (str.equals("(")) return 7;//token is left parenthesis
        else if (str.equals(")")) return 8;//token is right parenthesis
        else if (checkFunction(str) > 0) return 6;//token is math function
        else return 0;//token is other math operation
    }

    private static int checkFunction(String str) {
        //This method used to check token that is math function or not
        String[] mFunc = {"", "sin", "cos", "tan", "cosec", "sec", "cot",
            "asin", "acos", "atan", "sqrt", "log", "exp", "abs"};//Array of math function
        int funcCode = 0, i;//funCode is index of array of math function
        //if funcCode is 0 that is mean token doesn't match name of math function
        for (i = 0; i < mFunc.length; i++) {
            if (mFunc[i].equalsIgnoreCase(str)) funcCode = i;//token match name of math function
        }
        return funcCode;
        //return index of array of math function to identify function
    }

    private static boolean isConstant(String str) {
        //This method used to check token that match math constant or not
        String[] mConst = {"pi", "e", "ans", "g"};
        boolean check = false;
        int i;
        for (i = 0; i < mConst.length; i++) {
            if (mConst[i].equalsIgnoreCase(str)) check = true;//token is math constant
        }
        return check;//return answer that is constant or not
    }

    private static boolean isNumber(String str) {
        //This method used to check token that is number or not
        boolean check = true;
        try {
            Double.parseDouble(str);//token is number then convert string to double
        } catch (Exception e) {
            check = false;//token isn't number
        }
        return check;//return answer that is number or not
    }

    private static void changeNegativeSign(String[] token) {
        //This method used to change negative sign to !
        //don't check token[0] because it is (
        int i;
        for (i = 0; i < token.length - 1; i++) {
            //minus operator after math operator will be negative sign
            if (token[i + 1].equals("-") && token[i].matches("[-+/(*^]"))
                token[i + 1] = "!";//change negative sign to !
        }
    }

    private static String addSpace(String str) {
        //This method used to add space between operator

        //Add space between operator
        str = str.replace("+", " + ");
        str = str.replace("-", " - ");
        str = str.replace("*", " * ");
        str = str.replace("/", " / ");
        str = str.replace("^", " ^ ");
        str = str.replace("(", " ( ");
        str = str.replace(")", " ) ");

        str = str.trim();//Delete space front and back expression
        str = str.replaceAll("\\s+", " ");//Replace space more than one to one space
        return str;
    }
}
