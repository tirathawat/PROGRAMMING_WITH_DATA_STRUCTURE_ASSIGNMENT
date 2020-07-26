import java.io.BufferedReader;
import java.io.FileReader;

public class Assignment2 {

    public static void main(String[] args) {
        Data[] data = new Data[100002];
        System.out.println("This is Sorting Comparison program.");//Introduction
        int count = read_file(data, "test.csv");//Read file
        if (count == -1) {
            //Read file fail
            end_program();
        }
        else {
            //Read file success
            long time_before, time_after;//Used for timer
            int count_test = 10000;//Amount of data tested

            //index[0] ---> random data time
            //index[1] ---> insert data time
            //index[2] ---> descending time
            long[] time_selection = new long[3];
            long[] time_insertion = new long[3];
            long[] time_bubble = new long[3];
            long[] time_shell = new long[3];

            System.out.println("Sorting data...");

            //Selection sort, random data
            Data[] data_copy = data.clone(); //Copy the data to use for sorting
            time_before = System.currentTimeMillis(); //Start timer
            selection_sort(data_copy, 0, count_test - 1, false);
            time_after = System.currentTimeMillis();//Stop timer
            time_selection[0] = time_after - time_before;//Calculate time
            System.out.println("selection sort random data taking time " + time_selection[0] + " millisecond.");
            System.out.println("Sorting " + (count_test - 1) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test - 1, false));
            System.out.println();

            //selection sort, insert data
            time_before = System.currentTimeMillis();//Start timer
            selection_sort(data_copy, 0, count_test, false);
            time_after = System.currentTimeMillis();//Stop timer
            time_selection[1] = time_after - time_before;//Calculate time
            System.out.println("selection sort insert data taking time " + time_selection[1] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, false));
            System.out.println();

            //selection sort, descending
            time_before = System.currentTimeMillis();//Start timer
            selection_sort(data_copy, 0, count_test, true);
            time_after = System.currentTimeMillis();//Stop timer
            time_selection[2] = time_after - time_before;//Calculate time
            System.out.println("selection sort descending data taking time " + time_selection[2] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, true));
            System.out.println();

            //insertion sort, random data
            data_copy = data.clone();
            time_before = System.currentTimeMillis();//Start timer
            insertion_sort(data_copy, 0, count_test - 1, false);
            time_after =  System.currentTimeMillis();//Stop timer
            time_insertion[0] = time_after - time_before;//Calculate time
            System.out.println("insertion sort random data taking time " + time_insertion[0] + " millisecond.");
            System.out.println("Sorting " + (count_test - 1) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test - 1, false));
            System.out.println();

            //insertion sort, insert data
            time_before = System.currentTimeMillis();//Start timer
            insertion_sort(data_copy, 0, count_test, false);
            time_after = System.currentTimeMillis();//Stop timer
            time_insertion[1] = time_after - time_before;//Calculate time
            System.out.println("insertion sort insert data taking time " + time_insertion[1] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, false));
            System.out.println();

            //insertion sort, descending
            time_before = System.currentTimeMillis();//Start timer
            insertion_sort(data_copy, 0, count_test, true);
            time_after = System.currentTimeMillis();//Stop timer
            time_insertion[2] = time_after - time_before;//Calculate time
            System.out.println("insertion sort descending data taking time " + time_insertion[2] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, true));
            System.out.println();

            //bubble sort, random data
            data_copy = data.clone();
            time_before = System.currentTimeMillis();//Start timer
            bubble_sort(data_copy, 0, count_test - 1, false);
            time_after =  System.currentTimeMillis();//Stop timer
            time_bubble[0] = time_after - time_before;//Calculate time
            System.out.println("bubble sort random data taking time " + time_bubble[0] + " millisecond.");
            System.out.println("Sorting " + (count_test - 1) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test - 1, false));
            System.out.println();

            //bubble sort, insert data
            time_before = System.currentTimeMillis();//Start timer
            bubble_sort(data_copy, 0, count_test, false);
            time_after = System.currentTimeMillis();//Stop timer
            time_bubble[1] = time_after - time_before;//Calculate time
            System.out.println("bubble sort insert data taking time " + time_bubble[1] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, false));
            System.out.println();

            //bubble sort, descending
            time_before = System.currentTimeMillis();//Start timer
            bubble_sort(data_copy, 0, count_test, true);
            time_after = System.currentTimeMillis();//Stop timer
            time_bubble[2] = time_after - time_before;//Calculate time
            System.out.println("bubble sort descending data taking time " + time_bubble[2] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, true));
            System.out.println();

            //shell sort, random data
            data_copy = data.clone();
            time_before = System.currentTimeMillis();//Start timer
            shell_sort(data_copy, 0, count_test - 1, false);
            time_after =  System.currentTimeMillis();//Stop timer
            time_shell[0] = time_after - time_before;//Calculate time
            System.out.println("shell sort random data taking time " + time_shell[0] + " millisecond.");
            System.out.println("Sorting " + (count_test - 1) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test - 1, false));
            System.out.println();

            //shell sort, insert data
            time_before = System.currentTimeMillis();//Start timer
            shell_sort(data_copy, 0, count_test, false);
            time_after = System.currentTimeMillis();//Stop timer
            time_shell[1] = time_after - time_before;//Calculate time
            System.out.println("shell sort insert data taking time " + time_shell[1] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, false));
            System.out.println();

            //shell sort, descending
            time_before = System.currentTimeMillis();//Start timer
            shell_sort(data_copy, 0, count_test, true);
            time_after = System.currentTimeMillis();//Stop timer
            time_shell[2] = time_after - time_before;//Calculate time
            System.out.println("shell sort descending data taking time " + time_shell[2] + " millisecond.");
            System.out.println("Sorting " + (count_test) + " records");
            System.out.println("Sorting correct : " + check_sort(data_copy, 0, count_test, true));
            System.out.println();

            System.out.println("Conclusion");
            System.out.println("Sort : Random data : Insert data : Descending");
            System.out.printf("Selection Sort : %d ms : %d ms : %d ms\n", time_selection[0], time_selection[1], time_selection[2]);
            System.out.printf("Insertion Sort : %d ms : %d ms : %d ms\n", time_insertion[0], time_insertion[1], time_insertion[2]);
            System.out.printf("Bubble Sort : %d ms : %d ms : %d ms\n", time_bubble[0], time_bubble[1], time_bubble[2]);
            System.out.printf("Shell Sort : %d ms : %d ms : %d ms\n", time_shell[0], time_shell[1], time_shell[2]);

            System.out.println();
            end_program();
        }
    }

    private static void end_program() {
        System.out.println("End Program.");
        System.out.println("Program written by 62070501022 Thirathawat Chanserikorn");
    }

    private static int read_file(Data[] data, String filename) {
        String read;//Used to read the value of each line in the file
        int first_index, last_index;//Used to divide fields
        int count = 0;
        System.out.println("Read file from " + filename + "...");
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((read = in.readLine()) != null) { //Read all file
                data[count] = new Data();//Array of object
                data[count].setData(read);//Save value of each line
                first_index = read.indexOf(',');//Search index of (,)
                last_index = read.indexOf(',', first_index + 1);//Search index of (,)
                data[count].setField1(Long.parseLong(read.substring(first_index + 1, last_index)));//Save Field1
                first_index = last_index;
                last_index = read.indexOf(',', last_index + 1);//Search index of (,)
                data[count].setField2(read.substring(first_index + 1, last_index));//Save Field2
                data[count].setField3(read.substring(last_index + 1));//Save Field3
                count++;
            }
            System.out.println("Read file complete.");
            System.out.println("Read " + count + " records.");
            in.close();
            System.out.println("Closed file.");
            //Read file success return count
            return count;
        }
        catch (Exception e) {
            //Read file fail
            System.out.println("Error to read file from " + filename + ".");
            return -1;
        }
    }

    //Used for check sorting is correct or not
    private static boolean check_sort(Data[] data, int start, int stop, boolean descending) {
        int i;
        if (descending) {
            //descending
            for (i = start; i < stop; i++) {
                if (data[i].getField1() < data[i + 1].getField1()) return false;//Wrong sort return false
            }
            //correct sort return true
            return true;
        }
        else{
            //Ascending
            for (i = start; i < stop; i++) {
                if (data[i].getField1() > data[i + 1].getField1()) return false;//Wrong sort return false
            }
            //correct sort return true
            return true;
        }
    }

    private static void selection_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j, max, min;
        Data temp;
        if (descending) {
            //descending
            for (i = start; i < stop; i++) {
                max = i; //set maximum data index
                for (j = i + 1; j <= stop; j++) {
                    if (data[j].getField1() > data[max].getField1()) {
                        max = j;//keep maximum data index
                    }
                }
                if (i != max) {
                    //swap data
                    temp = data[max];
                    data[max] = data[i];
                    data[i] = temp;
                }
            }
        }
        else {
            //Ascending
            for (i = start; i < stop; i++) {
                min = i;//set minimum data index
                for (j = i + 1; j <= stop; j++) {
                    if (data[j].getField1() < data[min].getField1()) {
                        min = j;//keep minimum data index
                    }
                }
                if (i != min) {
                    //swap data
                    temp = data[min];
                    data[min] = data[i];
                    data[i] = temp;
                }
            }
        }
    }

    private static void insertion_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j;
        Data x;
        if (descending) {
            //descending
            for (i = start + 1; i <= stop; i++) {
                x = data[i];//select data for checking
                for (j = i; ((j > start) && (x.getField1() > data[j - 1].getField1())); j--) {
                    data[j] = data[j - 1];//Scroll data when the previous data is less than
                }
                data[j] = x;//j is the appropriate index of the selected data
            }
        }
        else {
            //Ascending
            for (i = start + 1; i <= stop; i++) {
                x = data[i];//select data for checking
                for (j = i; ((j > start) && (x.getField1() < data[j - 1].getField1())); j--) {
                    data[j] = data[j - 1];//Scroll data when the previous data is more than
                }
                data[j] = x;//j is the appropriate index of the selected data
            }
        }
    }

    private static void bubble_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j;
        boolean do_more = true;
        Data temp;
        if (descending) {
            for (i = start; i <= stop - 1 && do_more; i++) {
                do_more = false;
                for (j = stop; j > i; j--) {
                    if (data[j].getField1() > data[j - 1].getField1()) {
                        //swap data
                        temp = data[j];
                        data[j] = data[j - 1];
                        data[j - 1] = temp;
                        //Do more when data is swapped
                        do_more = true;
                    }
                }
            }
        }
        else {
            for (i = start; i <= stop - 1 && do_more; i++) {
                do_more = false;
                for (j = stop; j > i; j--) {
                    if (data[j].getField1() < data[j - 1].getField1()) {
                        //swap data
                        temp = data[j];
                        data[j] = data[j - 1];
                        data[j - 1] = temp;
                        //Do more when data is swapped
                        do_more = true;
                    }
                }
            }
        }
    }

    private static void shell_sort(Data[] data, int start, int stop, boolean descending) {
        int gap, i;
        boolean change;
        Data temp;
        if (descending) {
            gap = stop - start + 1;
            do {
                gap /= 2;
                do {
                    change = false;
                    for (i = start; i < stop - gap + 1; i++) {
                        if (data[i].getField1() < data[i + gap].getField1()) {
                            //swap data
                            temp = data[i];
                            data[i] = data[i + gap];
                            data[i + gap] = temp;
                            //Do more when data is changed
                            change = true;
                        }
                    }
                }while(change);
            }while(gap > 1);
        }
        else {
            gap = stop - start + 1;
            do {
                gap /= 2;
                do {
                    change = false;
                    for (i = start; i < stop - gap + 1; i++) {
                        if (data[i].getField1() > data[i + gap].getField1()) {
                            //swap data
                            temp = data[i];
                            data[i] = data[i + gap];
                            data[i + gap] = temp;
                            //Do more when data is changed
                            change = true;
                        }
                    }
                }while(change);
            }while(gap > 1);//Do until there is only one group of data
        }
    }
}

class Data {
    private String data;
    private long field1;
    private String field2;
    private String field3;

    public Data() {
        //constructor
    }

    long getField1() {
        return field1;
    } //Used for get Field1

    void setData(String data) {
        this.data = data;
    }//Used set get data

    void setField1(long field1) {
        this.field1 = field1;
    }//Used for set Field1

    void setField2(String field2) {
        this.field2 = field2;
    }//Used for set Field2

    void setField3(String field3) {
        this.field3 = field3;
    }//Used for set Field3
}

