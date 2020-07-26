import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment3 {
    public static void main(String[] args) {
        int max = 100000, i; //max --> maximum data of data array

        //Declare variable for save times
        double Time, TimeSortField1, TimeSortField3, mTimeSortField1, mTimeSortField3;
        TimeSortField1 = TimeSortField3 = mTimeSortField1 = mTimeSortField3 = 0;

        DataArray dataArray = new DataArray(max);//Object class

        if (readFile(dataArray) != 0) {
            //Read file success

            //test 3 times to find the best time
            for (i = 1; i <= 3; i++) {
                System.out.printf("Round%d test\n\n", i);

                /*Test sort --> return time
                * Mode1 --> my quick sort number
                * Mode2 --> my quick sort string
                * Mode3 --> library quick sort number
                * Mode4 --> library quick sort string
                */

                System.out.println("Test my quick sort field1");
                Time = testSort(dataArray, dataArray.data.length, 1);
                if (Time < TimeSortField1 || i == 1) TimeSortField1 = Time;//Compare to find the fastest time.

                System.out.println("Test my quick sort field3");
                Time = testSort(dataArray, dataArray.data.length, 2);
                if (Time < TimeSortField3 || i == 1) TimeSortField3 = Time;//Compare to find the fastest time.

                System.out.println("Test library quick sort field1");
                Time = testSort(dataArray, dataArray.data.length, 3);
                if (Time < mTimeSortField1 || i == 1) mTimeSortField1 = Time;//Compare to find the fastest time.

                System.out.println("Test library quick sort field3");
                Time = testSort(dataArray, dataArray.data.length, 4);
                if (Time < mTimeSortField3 || i == 1) mTimeSortField3 = Time;//Compare to find the fastest time.
            }

            //Show the best time
            System.out.println("Conclusion");
            System.out.printf("Time of my quick sort field1 : %g ms\n", TimeSortField1);
            System.out.printf("Time of my quick sort field3 : %g ms\n",TimeSortField3);
            System.out.printf("Time of library quick sort field1 : %g ms\n", mTimeSortField1);
            System.out.printf("Time of library quick sort field3 : %g ms\n\n", mTimeSortField3);

            endProgram();
        }
        else endProgram();
    }

    private static double testSort(DataArray dataArray, int max, int mode) {
        double timeDiff = -1; //set default -1 when wrong mode is selected
        DataArray test = new DataArray(max); //Object class for test sort data
        test.data = Arrays.copyOf(dataArray.data, dataArray.count);//copy data to test
        test.count = dataArray.count; //copy count of data
        System.out.println("Before");
        test.showData();//Show data for validation

        /*Test sort --> return time
         * Mode1 --> my quick sort number
         * Mode2 --> my quick sort string
         * Mode3 --> library quick sort number
         * Mode4 --> library quick sort string
         */

        if (mode == 1) {
            long timeNano = System.nanoTime();//Start timer
            test.quickSortField1(0, test.count - 1);//my quick sort number
            timeDiff = (System.nanoTime() - timeNano) / 1e6;//Stop timer
        }
        else if (mode == 2) {
            long timeNano = System.nanoTime();//Start timer
            test.quickSortField3(0, test.count - 1);//my quick sort string
            timeDiff = (System.nanoTime() - timeNano) / 1e6;//Stop timer
        }
        else if (mode == 3) {
            long timeNano = System.nanoTime();//Start timer
            Arrays.sort(test.data, node.CmpField1);//library quick sort number
            timeDiff = (System.nanoTime() - timeNano) / 1e6;//Stop timer
        }
        else if (mode == 4) {
            long timeNano = System.nanoTime();//Start timer
            Arrays.sort(test.data, node.CmpField3);//library quick sort string
            timeDiff = (System.nanoTime() - timeNano) / 1e6;//Stop timer
        }

        System.out.println("After");
        test.showData();//Show data for validation
        System.out.println("Time : " + timeDiff + " ms");//Show time
        System.out.println();
        return timeDiff;//Return time
    }

    private static void endProgram() {
        System.out.println("End Program.");
        System.out.println("Program written by 62070501022 Thirathawat Chanserikorn");
    }

    private static int readFile(DataArray data) {
        try {
            File file = new File("test.csv");//Object class and path file
            Scanner in = new Scanner(file);//Object scanner to read file
            System.out.println("Read file " + "test.csv");
            in.useDelimiter("[,]|[\n]");//Set characters that don't want to read

            //Declare variable to save field1 field2 field3
            long field1;
            String field2, field3;

            while (in.hasNext()) {//Read until out of data
                field1 = in.nextLong();
                field2 = in.next();
                field3 = in.next();
                data.addData(field1, field2, field3);//add data to DataArray(data)
            }
            in.close();//Close file.
            System.out.printf("Read file %d records\n\n", data.count);
            return data.count;//Return count of data
        }
        catch (FileNotFoundException e) {
            //File not found
            System.out.println("Error can not open " + "test.csv");
            return 0;
        }
    }
}



