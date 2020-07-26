import java.io.BufferedReader;
import java.io.FileReader;

public class Assignment2 {

    public static void main(String[] args) {
        Data[] data1 = new Data[100002];
        Data[] data2 = new Data[100002];
        Data[] data3 = new Data[100002];
        Data[] data4 = new Data[100002];
        System.out.println("This is Sorting Comparison program.");
        int count1 = read_file(data1, "test.csv");
        if (count1 == -1) {
            end_program();
        }
        else {
            long time_before, time_after;
            long[] time_selection = new long[3];
            long[] time_insertion = new long[3];
            long[] time_buble = new long[3];
            long[] time_shell = new long[3];

            System.out.println("Random data.");
            System.out.println();

            //System.out.println("Start selection sort...");
            time_before = System.currentTimeMillis();
            selection_sort(data1, 0, 99998, false);
            time_after = System.currentTimeMillis();
            time_selection[0] = time_after - time_before;
            System.out.println("selection sort complete taking time " + time_selection[0] + " millisecond.");

            int count2 = read_file(data2, "test.csv");
            //System.out.println("Start insertion sort...");
            time_before = System.currentTimeMillis();
            insertion_sort(data2, 0, 99998, false);
            time_after =  System.currentTimeMillis();
            time_insertion[0] = time_after - time_before;
            System.out.println("insertion sort complete taking time " + time_insertion[0] + " millisecond.");

            int count3 = read_file(data3, "test.csv");
            //System.out.println("Start buble sort...");
            time_before = (int) System.currentTimeMillis();
            buble_sort(data3, 0, 99998, false);
            time_after = (int) System.currentTimeMillis();
            time_buble[0] = time_after - time_before;
            System.out.println("buble sort complete taking time " + time_buble[0] + " millisecond.");

            int count4 = read_file(data4, "test.csv");
            //System.out.println("Start shell sort...");
            time_before = (int) System.currentTimeMillis();
            shell_sort(data4, 0, 99998, false);
            time_after = (int) System.currentTimeMillis();
            time_shell[0] = time_after - time_before;
            System.out.println("shell sort complete taking time " + time_shell[0] + " millisecond.");

            System.out.println("Insert data.");
            System.out.println();
            /*System.out.println("Add one data");
            data1[100001] = new Data("100000,620705010222,tirathawat,chansarekorn", Long.parseLong("620705010222")
                    , "tirathawat", "chansarekorn");
            data2[100001] = new Data("100000,620705010222,tirathawat,chansarekorn", Long.parseLong("620705010222")
                    , "tirathawat", "chansarekorn");
            data3[100001] = new Data("100000,620705010222,tirathawat,chansarekorn", Long.parseLong("620705010222")
                    , "tirathawat", "chansarekorn");
            data4[100001] = new Data("100000,620705010222,tirathawat,chansarekorn", Long.parseLong("620705010222")
                    , "tirathawat", "chansarekorn");*/

            //System.out.println("Start selection sort...");
            time_before = System.currentTimeMillis();
            selection_sort(data1, 0, 99999, false);
            time_after = System.currentTimeMillis();
            time_selection[0] = time_after - time_before;
            System.out.println("selection sort complete taking time " + time_selection[0] + " millisecond.");

            //System.out.println("Start insertion sort...");
            time_before = System.currentTimeMillis();
            insertion_sort(data2, 0, 99999, false);
            time_after = System.currentTimeMillis();
            time_insertion[0] = time_after - time_before;
            System.out.println("insertion sort taking time " + time_insertion[0] + " millisecond.");

            //System.out.println("Start buble sort...");
            time_before = System.currentTimeMillis();
            buble_sort(data3, 0, 99999, false);
            time_after = System.currentTimeMillis();
            time_buble[0] = time_after - time_before;
            System.out.println("buble sort complete taking time " + time_buble[0] + " millisecond.");

            //System.out.println("Start shell sort...");
            time_before = System.currentTimeMillis();
            shell_sort(data4, 0, 99999, false);
            time_after = System.currentTimeMillis();
            time_shell[0] = time_after - time_before;
            System.out.println("shell sort complete taking time " + time_shell[0] + " millisecond.");

            System.out.println("Descending data.");
            System.out.println();

            //System.out.println("Start selection sort...");
            time_before = System.currentTimeMillis();
            selection_sort(data1, 0, 99999, true);
            time_after = System.currentTimeMillis();
            time_selection[0] = time_after - time_before;
            System.out.println("selection sort complete taking time " + time_selection[0] + " millisecond.");

            //System.out.println("Start insertion sort...");
            time_before = System.currentTimeMillis();
            insertion_sort(data2, 0, 99999, true);
            time_after =  System.currentTimeMillis();
            time_insertion[0] = time_after - time_before;
            System.out.println("insertion sort complete taking time " + time_insertion[0] + " millisecond.");

            //System.out.println("Start buble sort...");
            time_before = (int) System.currentTimeMillis();
            buble_sort(data3, 0, 99999, true);
            time_after = (int) System.currentTimeMillis();
            time_buble[0] = time_after - time_before;
            System.out.println("buble sort complete taking time " + time_buble[0] + " millisecond.");

            //System.out.println("Start shell sort...");
            time_before = (int) System.currentTimeMillis();
            shell_sort(data4, 0, 99999, true);
            time_after = (int) System.currentTimeMillis();
            time_shell[0] = time_after - time_before;
            System.out.println("shell sort complete taking time " + time_shell[0] + " millisecond.");
        }
    }

    private static void end_program() {
        System.out.println("End Program.");
        System.out.println("Program written by 62070501022 Thirathawat Chansarikorn");
    }

    private static void show_data(Data[] data, int count) {
        int i;
        for (i = 0; i < count; i++) {
            System.out.println(data[i].getData());
        }
    }

    private static int read_file(Data[] data, String filename) {
        String read;
        int first_index, last_index, count = 0;
        //System.out.println("Read file from " + filename + "...");
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((read = in.readLine()) != null) {
                data[count] = new Data();
                data[count].setData(read);
                first_index = read.indexOf(',');
                last_index = read.indexOf(',', first_index + 1);
                data[count].setField1(Long.parseLong(read.substring(first_index + 1, last_index)));
                first_index = last_index;
                last_index = read.indexOf(',', last_index + 1);
                data[count].setField2(read.substring(first_index + 1, last_index));
                data[count].setField3(read.substring(last_index + 1));
                count++;
            }
            //System.out.println("Read file complete.");
            //System.out.println("Have " + count + " data.");
            in.close();
            //System.out.println("Closed file.");
            return count;
        }
        catch (Exception e) {
            System.out.println("Error to read file from " + filename + ".");
            return -1;
        }
    }

    private static void selection_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j, max, min;
        Data temp;
        if (descending) {
            for (i = start; i < stop; i++) {
                max = i;
                for (j = i + 1; j <= stop; j++) {
                    if (data[j].getField1() > data[max].getField1()) {
                        max = j;
                    }
                }
                temp = data[max];
                data[max] = data[i];
                data[i] = temp;
            }
        }
        else {
                for (i = start; i < stop; i++) {
                    min = i;
                    for (j = i + 1; j <= stop; j++) {
                        if (data[j].getField1() < data[min].getField1()) {
                            min = j;
                        }
                    }
                    temp = data[min];
                    data[min] = data[i];
                    data[i] = temp;
                }
        }
    }

    private static void insertion_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j;
        Data x;
        if (descending) {
            for (i = start + 1; i <= stop; i++) {
                x = data[i];
                for (j = i; ((j > start) && (x.getField1() > data[j - 1].getField1())); j--) {
                    data[j] = data[j - 1];
                }
                data[j] = x;
            }
        }
        else {
            for (i = start + 1; i <= stop; i++) {
                x = data[i];
                for (j = i; ((j > start) && (x.getField1() < data[j - 1].getField1())); j--) {
                    data[j] = data[j - 1];
                }
                data[j] = x;
            }
        }
    }

    private static void buble_sort(Data[] data, int start, int stop, boolean descending) {
        int i, j;
        boolean do_more = true;
        Data temp;
        if (descending) {
            for (i = start; i <= stop - 1 && do_more; i++) {
                do_more = false;
                for (j = stop; j > i; j--) {
                    if (data[j].getField1() > data[j - 1].getField1()) {
                        temp = data[j];
                        data[j] = data[j - 1];
                        data[j - 1] = temp;
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
                        temp = data[j];
                        data[j] = data[j - 1];
                        data[j - 1] = temp;
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
                            temp = data[i];
                            data[i] = data[i + gap];
                            data[i + gap] = temp;
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
                            temp = data[i];
                            data[i] = data[i + gap];
                            data[i + gap] = temp;
                            change = true;
                        }
                    }
                }while(change);
            }while(gap > 1);
        }
    }
}

class Data {
    private String data;
    private long field1;
    private String field2;
    private String field3;

    public Data() {
    }

    public Data(String data, long field1, String field2, String field3) {
        this.data = data;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    String getData() {
        return data;
    }

    long getField1() {
        return field1;
    }

    void setData(String data) {
        this.data = data;
    }

    void setField1(long field1) {
        this.field1 = field1;
    }

    void setField2(String field2) {
        this.field2 = field2;
    }

    void setField3(String field3) {
        this.field3 = field3;
    }
}
