#include <stdio.h>
#include <string.h>

typedef struct data {
    long long field1;
    char filed2[64];
    char filed3[64];
    char text[200];
}Data;

void end_program() {
    printf("End Program.\n");
    printf("Program written by 62070501022 Thirathawat Chanserikorn.");
}

int read_file(Data data[], char filename[]) {
    int count = 0;
    FILE *p;
    char test[200];
   p = fopen(filename, "r");
    if (p != NULL) {
        printf("File open success.\n");
        while (fscanf(p, "%s", test) != NULL) {
            strcpy(data[count++].text, test);
            printf("%s\n", data[count - 1].text);
            //printf("%d\n", count);
        }
    }
    else {
        printf("Open file fail.\n");
    }
    fclose(p);
    return count;
}

int main () {
    const filename = "test.csv";
    Data *p;
    p = (Data*)malloc(sizeof(Data) * 100002);
    int count = read_file(p, filename);
    for(int i = 0; i < count; i++) {
        printf("%s\n", p -> text);
    }
    free(p);
    return 0;
}
