import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
        //Introduction program
        System.out.println("My Recursion Program.");
        System.out.println("Program calculate n! by recursion (n <= 15)");

        Run('y');

        //End program
        System.out.println("End Program.");
        System.out.println("Program written by 62070501022 Thirathawat Chansarikorn");
    }

    private static char Run(char check_end) {
        //Declare variable
        int n;
        long ans;

        if (check_end != 'y') {
            //base case --> end function (end program)
            return check_end;
        }
        else {
            //recursive case
            n = Read_Int(0 , 15);
            ans = Factorial(n);
            System.out.printf("Complete calculate of %d!, answer = %d\n", n, ans);
            System.out.print("press [y] to continue, others to exit.");
            Scanner in = new Scanner(System.in);
            try {
                check_end = in.next().charAt(0);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                in.nextLine();
            }
            return Run(check_end);
        }
    }

    private static long Factorial(int n) {
        //Declare variable
        long ans, factorial;

            if (n == 0) {
                //base case
                System.out.println("0! is base case return answer of 0! = 1");
                System.out.println("Calculate 0! complete.");
                return 1;
            }
            else {
                //recursive case
                System.out.printf("%d! is recursive case ", n);
                System.out.printf("Answer = %d * recursive of %d!\n", n, n - 1);
                System.out.printf("\tRecursion to calculate %d!\n", n - 1);
                factorial = Factorial(n - 1);
                if (n > 1)
                    System.out.printf("Calculate %d! complete.\n", n - 1);
                System.out.printf("\tReturn answer from %d! = %d ", n - 1, factorial);
                ans = n * factorial;//ans of n!
                System.out.printf("to calculate %d! = [%d * %d!] = %d * %d = %d\n"
                        , n, n, n - 1, n, factorial, ans);
                return ans;
            }
    }


    private static int Read_Int(int min, int max) {
        //declare local variable
        int input;

        //get values from keyboard
        Scanner in = new Scanner(System.in);

        try {
            System.out.print("Enter n : ");
            input = in.nextInt();
            if (input >= min && input <= max)
                return input;
            else
                System.out.printf("Input error, please enter between %d - %d\n", min, max);
        }
        catch (Exception e) {
            System.out.printf("Input error, please enter between %d - %d\n", min, max);
            in.nextLine();
        }

        return Read_Int(min, max);
    }
}
