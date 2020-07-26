#include<stdio.h>
#include<string.h>
#include<ctype.h>
#include<stdlib.h>
#include<math.h>

//linked list
typedef struct node {
    double number;
    struct node *next;
}linkedlist;
linkedlist *first = NULL, *last = NULL, *ptr;

int checkNode(int node) {
    //if number == 1 --> 1 or more nodes
    //if number == 2 --> 2 or more nodes
    if (first == NULL) {
        //Don't have node
        printf("Answer> no data\n");
        return 0;
    }
    else if (first -> next == NULL && node == 2) {
        //There is only one node
        printf("Answer> Can't calculate\n");
        return 0;
    }
    else return 1;//2 or more nodes
}

void sqrtNode() {
    if (checkNode(1) == 1) {//1 or more nodes
        if (first -> number < 0) printf("Answer> Can't calculate\n"); //Complex number
        else {
            first -> number = sqrt(first -> number);//Calculate square root
            printf("Answer> %g\n", first -> number);//Show answer
        }
    }
}

void recNode() {
    if (checkNode(1) == 1) {//1 or more nodes
        if (first -> number == 0) printf("Answer> Can't calculate\n");//Don't divide by 0
        else {
            first -> number = 1 / (first -> number);//Calculate rec
            printf("Answer> %g\n", first -> number);//Show answer
        }
    }
}

void negNode() {
    if (checkNode(1) == 1) {//1 or more nodes
        first -> number = (-1) * (first -> number);//Calculate neg
        printf("Answer> %g\n", first -> number);//Show answer
    }
}

void deleteSecondNode() {
    ptr = first -> next;//ptr point to second node
    first -> next = ptr -> next;//next of first point to third node
    free(ptr);//Free memory of second node
}

void plusNode() {
    if (checkNode(2) == 1) {//2 or more nodes
        first -> number = ((first -> next) -> number) + (first -> number);//Calculate second node + first node
        deleteSecondNode();
        printf("Answer> %g\n", first -> number);//Show answer
    }
}

void minusNode() {
    if (checkNode(2) == 1) {//2 or more nodes
        first -> number = ((first -> next) -> number) - (first -> number);//Calculate second node - first node
        deleteSecondNode();
        printf("Answer> %g\n", first -> number);//Show answer
    }
}

void multiplyNode() {
    if (checkNode(2) == 1) {//2 or more nodes
        first -> number = ((first -> next) -> number) * (first -> number);//Calculate second node * first node
        deleteSecondNode();
        printf("Answer> %g\n", first -> number);//Show answer
    }
}

void divideNode() {
    if (checkNode(2) == 1) {//2 or more nodes
        if (first -> number == 0) printf("Answer> Can't calculate\n");//Don't divide by 0
        else {
            first -> number = ((first -> next) -> number) / (first -> number);//Calculate second node / first node
            deleteSecondNode();
            printf("Answer> %g\n", first -> number);//Show answer
        }
    }
}

void powNode() {
    if (checkNode(2) == 1) {//2 or more nodes
        first -> number = pow(((first -> next) -> number), (first -> number));//Calculate second node ^ first node
        deleteSecondNode();
        printf("Answer> %g\n", first -> number);//Show answer
    }
}

linkedlist* search(double item) {
    while (ptr != NULL && ptr -> number != item) {//Loop until ptr pointer == NULL of found item
        ptr = ptr -> next;//Move ptr pointer to each node
    }
    return ptr;//return address of ptr pointer --> ptr pointer point to item
}

void swap(linkedlist *i, linkedlist *j) {
    double temp;

    //swap node of i and node of j
    temp = j -> number;
    j -> number = i -> number;
    i -> number = temp;
}

void selectSortLinkedlist() {
    if (checkNode(1) == 1) {//1 or more nodes
        linkedlist *i, *j, *min;
        i = first;
        while (i != last) {//Loop until before last of node
            min = i;//Save min
            j = i -> next;//Set j pointer is next of i pointer
            while (j != NULL){//Loop until end of node
                if (j -> number < min -> number) min = j;//Compare to find min
                j = j -> next;//Move j pointer to next node
            }
            swap(i, min);//swap between i node and min node
            i = i -> next;//Move i pointer to next node
        }
    }
}

int checkSort() {
    if (first == NULL) return 1;// Don't have node
    ptr = first; //Set ptr pointer to first node
    while(ptr -> next != NULL) { //Loop until end of node
        if ((ptr -> number) > ((ptr -> next) -> number)) return 0;//Don't sorted
        ptr = ptr -> next;//Move pointer to next node
    }
    return 1;// Sorted
}

int askConfirm(double item) {
    char confirm;
    printf("Answer> %g found enter y to confirm ", item);//Ask user
    rewind(stdin);
    scanf("%c", &confirm);
    if (confirm == 'y') return 1; //User confirm
    else return 0;//User not confirm
}

void deleteNode(double item) {
    linkedlist *prev;
    int found = 0;
    int ask = 0;
    ptr = first;//Set ptr pointer to first node
     if (checkNode(1) == 1) {//1 or more nodes
        do {
            if (ptr -> number == item) {
                found = 1;//Found item
                ask = askConfirm(item);
                if (ask == 1) {//Confirm to delete
                    if (ptr == first) {//Found at first node
                        first = first -> next;//Move first pointer to next node
                        free(ptr);//Free old first node
                    }
                    else if (ptr == last) {//Found at last node
                        prev -> next = NULL;//Next of previous node point to NULL
                        last = prev;//set last to previous
                        free(ptr);//Free old last node
                    }
                    else {
                        //Found at between first and last node
                        prev -> next = ptr -> next;//Next of previous node point to next of found node
                        free(ptr);//Free found node
                    }
                    ptr = NULL;//set ptr to NULL
                }
            }
            prev = ptr;//set previous node
            if (ptr != NULL) ptr = ptr -> next; //ptr pointer point to next node
        }while (ptr != NULL);//Loop until end of node
        if (found == 0) printf("Answer> %g Not found\n", item);//Not found
    }
}

void pop() {
    if (checkNode(1) == 1) {//1 or more nodes
        printf("Answer> %g\n", first -> number);//Show first node(answer)

        ///delete first node
        ptr = first;//Set ptr pointer to first node
        first = first -> next;//Set first to next node
        free(ptr);//Delete old first node
    }
}

void push(double item) {
    ptr = (linkedlist *)malloc(sizeof(linkedlist));//Create new node
    ptr -> number = item;//Set number of new node to item
    if (first == NULL) {//Don't have node
        //Set linked list
        ptr -> next = NULL;//Next of new node point to NULL
        first = last = ptr;//Set first and last to new node
    }
    else {
        //Set new node to first node
        ptr -> next = first;//Next of new node point to first node
        first = ptr;//Set first to new node
    }
}

void insert(double item) {
    linkedlist *check;
    ptr = (linkedlist *)malloc(sizeof(linkedlist));//Create new node
    ptr -> number = item;//Set number of new node to item
    if (first == NULL) {//Don't have node
        //Set linked list
        ptr -> next = NULL;//Next of new node point to NULL
        first = last = ptr;//Set first and last to new node
    }
    else if (first -> number > item) {//item less than number of first node
        //Set new node to first node
        ptr -> next = first;//Next of new node point to first node
        first = ptr;//Set first to new node
    }
    else {
        check = first;
        while (check -> next != NULL && (check -> next) -> number < item) {//Find position of new node
            check = check -> next;//Move pointer to each node for finding position
        }
        //Found position than insert new node
        ptr -> next = check -> next;
        check -> next = ptr;
        if (check == last) last = ptr;
    }
}

void peek(int position) {
    int count = 0;
    if (position == -1) printf("Answer> %g\n", last -> number);//Show last node
    else {
        ptr = first;//Set ptr pointer to first node
        //Loop for find position
        do {
            if (count == position) printf("Answer> %g\n", ptr -> number);//Found position
        }while (count++ != position && (ptr = ptr -> next) != NULL);
        if (ptr == NULL) printf("Answer> Maximum peek = %d\n", count - 1);//Not found
    }
}

void list() {
    if (first == NULL) printf("NULL");//Don't have node
    else {
        ptr = first;//Set ptr pointer to first node
        do {
            printf("%g ", ptr -> number);//Show number of each node
        }while ((ptr = ptr -> next) != NULL);//Loop until end of node
    }
    printf("\n");
}

void add(double item) {
    ptr = (linkedlist *)malloc(sizeof(linkedlist));//Create new node
    ptr -> number = item;//Set number of new node to item
    ptr -> next = NULL;//Set next of new node to NULL
    if (first == NULL) {//Don't have node
        first = last = ptr;//Set first and last to new node
    }
    else {
        //Have 1 or more nodes
        last -> next = ptr;//set next of last point to new node
        last = ptr;//Set last to new node
    }
}

//This function used to check the correctness of the command.
int checkFunction(char *key, char *func, double para[], int *count) {
    char nameFuncP1[19][10] = {"list", "end", "sort", "pop", "help", "sqrt", "rec", "neg", "pow"
                                , "+", "-", "*", "/"};//No parameter
    char nameFuncP2[4][10] = {"delete", "search", "peek", "push"};//1 parameter
    char nameFuncP3[2][10] = {"add", "insert"};//1 or more parameters
    char *token;
    int i, j;
    if (strcmp(key, "") == 0) {//User don't enter command
        printf("Answer> Syntax Error\n");
        return 0;
    }
    token = strtok(key, " ");//Substring to separate commands and parameters
    strcpy(func, token);//Save command to func
    strlwr(func);//Lower case command function
    int countPara = 0;//count parameter
    *count = 0;//set count to 0
    while ((token = strtok(NULL, " ")) != NULL){
        if (isNumber(token) == 1){
            para[countPara++] = atof(token);//Save parameter to para
        }
        else {
            printf("Answer> Parameter error\n");
            return 0;//return 0 ----> error
        }
    }
    *count = countPara;
    for(i = 0; i < 19; i++){
        if(strcmp(func, nameFuncP1[i]) == 0) {
            if (*count == 0) return 1;//Return 1 when syntax correct
            else {
                printf("Answer> Parameter error\n");
                return 0;//return 0 ----> error
            }
        }
        if(strcmp(func, nameFuncP2[i]) == 0) {
            if (*count == 1) return 1;//Return 1 when syntax correct
            else {
                printf("Answer> Parameter error\n");
                return 0;//return 0 ----> error
            }
        }
        if(strcmp(func, nameFuncP3[i]) == 0) {
            if (*count >= 1) return 1;//Return 1 when syntax correct
            else {
                printf("Answer> Parameter error\n");
                return 0;//return 0 ----> error
            }
        }
    }
    printf("Answer> Syntax Error\n");
    return 0;//return 0 ----> error
}

int isNumber(char *token) {
    int i, countDot = 0, countOper; // The number can contain only 1 dot. Use count_dot to check.
    for (i = 0; i < strlen(token); i++) {
        if (isdigit(token[i]) == 0 && token[i] != '.' && token[i] != '-' && token[i] != '+') return 0;//False(It isn't number and dot).
        if (token[i] == '.') countDot++; //Count dot.
        if (token[i] == '-' || token[i] == '+') countOper++; //Count minus.
        if (countDot >= 2 || countOper > 1) return 0;//False
    }
    return 1;//true
}

void help() {
    printf("Answer> list of command\n");
    printf("[add]    <list>   Add numerals to the linked list respectively.\n");
    printf("                  Put after the last number.\n");
    printf("[insert] <list>   Add numerals to the linked list.\n");
    printf("                  Only available when the data is in order.\n");
    printf("[push]   <n>      Add number to the first of linked list.\n");
    printf("[peek]   <n>      Browse to data at position n of linked list. (n = 0, 1, 2...)\n");
    printf("                    - If equal to -1, will display the last data.\n");
    printf("                    - If n exceeds the amount of available data,\n");
    printf("                  the maximum amount of data will be alerted.\n");
    printf("[delete] <n>      Search to delete numbers with value n from the linked list,\n");
    printf("                  asking for confirmation before deleting.\n");
    printf("                    - If not found, will alert that not found\n");
    printf("[search] <n>      Search to numbers with value n from the linked list,\n");
    printf("                  show position where found.\n");
    printf("                    - If not found, will alert that not found\n");
    printf("[sqrt][rec][neg]  Retrieve the first data to operate and then put it back.\n");
    printf("[+][-][*][/][pow] Retrieve the first two data to operate and then put it back.\n");
    printf("[list]            Show all in linked list.\n");
    printf("[pop]             Delete first data.\n");
    printf("[end]             End program.\n");
}

void inputCommand(char *str, char *func, double *para, int *count) {
    do{
            rewind(stdin);
            printf("list> ");
            list();//Show list of node
            printf("Command> ");
            gets(str);//Input command
    }while(checkFunction(str, func, para, count) == 0);
}

void endProgram() {
    printf("End program\n");
    printf("Program written by Tirathawat Chansarekorn 62070501022");
}

int runCommand(char func[], double para[], int count) {
    int i;
    if (strcmp(func, "add") == 0){
        for (i = 0; i < count; i++) {
            add(para[i]);
        }
    }
    else if (strcmp(func, "list") == 0) list();
    else if (strcmp(func, "peek") == 0) peek(para[0]);
    else if (strcmp(func, "push") == 0) push(para[0]);
    else if (strcmp(func, "delete") == 0) deleteNode(para[0]);
    else if (strcmp(func, "sqrt") == 0) sqrtNode();
    else if (strcmp(func, "rec") == 0) recNode();
    else if (strcmp(func, "neg") == 0) negNode();
    else if (strcmp(func, "+") == 0) plusNode();
    else if (strcmp(func, "-") == 0) minusNode();
    else if (strcmp(func, "*") == 0) multiplyNode();
    else if (strcmp(func, "/") == 0) divideNode();
    else if (strcmp(func, "pow") == 0) powNode();
    else if (strcmp(func, "sort") == 0) selectSortLinkedlist();
    else if (strcmp(func, "pop") == 0) pop();
    else if (strcmp(func, "help") == 0) help();
    else if (strcmp(func, "search") == 0) search(para[0]);
    else if (strcmp(func, "end") == 0) {
        endProgram();
        return 0;
    }
    else if (strcmp(func, "insert") == 0){
        if (checkSort() == 1) {
            for (i = 0; i < count; i++) {
                insert(para[i]);
            }
        }
        else printf("Answer> can't insert please sorted before\n");
    }
    return 1;
}

int main() {
    double para[10];
    char func[10];
    char str[50];
    int count, check;
    do {
        inputCommand(&str, &func, &para, &count);
        check = runCommand(func, para, count);
    }while(check != 0);
    return 0;
}



