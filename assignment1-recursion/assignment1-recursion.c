#include <stdio.h>

int Check_Error(char ch) {
    if (ch != ' ') {
        //base case
        if (ch == '\n') return 1; //user input OK
        else return 0; //user input error
    }
    else {
        //recursive case
        scanf("%c", &ch);
        return Check_Error(ch);
    }
}

int Read_Int(int min, int max) {
    //Declare local variable
    int i, input = 0;
    char ch;

    //Get values from keyboard
    rewind(stdin);
    printf("Enter n = ");
    if (scanf("%d%c", &input, &ch) == 2 && input >= min && input <= max && Check_Error(ch) == 1) {
        //base case
        return input;
    }
    else {
        //recursive case
        printf("Input error, please enter number between %d-%d\n", min, max);
        return Read_Int(min, max);
    }
}

long long Factorial(int n) {
    //Declare local variable
    long long ans, factorial = 1;

    if (n == 0) {
        //base case
        printf("0! is base case return answer of 0! = 1\n");
        ans = 1;
    }
    else {
        //recursive case
        printf("%d! is recursive case ", n);
        printf("Answer = %d * recursive of %d!\n", n, n - 1);
        printf("\tRecursion to calculate %d!\n", n - 1);

        factorial = Factorial(n - 1);
        ans = n * factorial;

        printf("Calculate %d! complete.\n", n - 1);
        printf("\tReturn answer from %d! = %llu ", n - 1, ans);
        printf("to calculate %d! = [%d * %d!] = %d * %llu = %llu\n"
            , n, n, n - 1, n, factorial, ans);
    }
    return ans;
}

void Run(char check_end) {
    //Declare local variable
    int n;
    long long ans;

    if (check_end != 'y') {
        //base case --> end function(end program)
        printf("End Program.\n");
        printf("Program written by 62070501022 Thirathawat Chansarikorn");
    }
    else {
        //recursive case
        n = Read_Int(0, 15);
        ans = Factorial(n);
        printf("Complete calculate of %d!, answer = %llu\n", n, ans);
        printf("press [y] to continue, others to exit.");
        rewind(stdin);
        scanf("%c", &check_end);

        return Run(check_end);
    }

}

int main() {
    //Introduction program
    printf("My Recursion Program.\n");
    printf("Program calculate n! by recursion (n <= 15)\n");

    Run('y');
    return 0;
}
