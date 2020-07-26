//This class is used for storing multiple data. And handle amounts of data
class DataArray {
    node[] data;//Array of node
    int count;//count of data

    DataArray(int max) {
        //construct to set maximum data
        data = new node[max];//Object array of node (number max node)
        count = 0;//set count of data to 0
    }

    //Used for add data(field1 field2 field3) to array of node
    void addData(long field1, String field2, String field3) {
        node x = new node(field1, field2, field3);//Object node x for save data
        data[count++] = x;//Save node x to array of node. And count data
    }

    //Used for swap position of data[i] and data[j]
    private void swap(int i, int j) {
        node temp;
        temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    //Quick sort number
    void quickSortField1(int first, int last) {
        int i = first, j = last;
        long pivot;
        if (first < last) {
            pivot = data[(first + last + 1) / 2].getField1();//Set pivot to middle data
            while (i < j) {
                while (data[i].getField1() < pivot) i++;//Compare field1 of data until left data more than pivot
                while (data[j].getField1() > pivot) j--;//Compare field1 of data until right data less than pivot
                if (i <= j) {
                    swap(i,j);//Swap position of data[i] and data[j]
                    i++;
                    j--;
                }
            }

            //Recursion for sort the remaining groups
            if (first < j) quickSortField1(first, j);
            if (i < last) quickSortField1(i, last);
        }
    }

    //Quick sort string
    void quickSortField3(int first, int last) {
        int i = first, j = last;
        String pivot;
        if (first < last) {
            pivot = data[(first + last + 1) / 2].getField3();//Set pivot to middle data
            while (i < j) {
                while (data[i].getField3().compareToIgnoreCase(pivot) < 0) i++;//Compare field3 of data until left data more than pivot
                while (data[j].getField3().compareToIgnoreCase(pivot) > 0) j--;//Compare field3 of data until right data less than pivot
                if (i <= j) {
                    swap(i,j);//Swap position of data[i] and data[j]
                    i++;
                    j--;
                }
            }
            //Recursion for sort the remaining groups
            if (first < j) quickSortField3(first, j);
            if (i < last) quickSortField3(i, last);
        }
    }

    ////Show data for validation
    void showData() {
        int[] index = {0, 49999, 99999};//Show index first, mid, and last
        int i;
        //Show data by index
        for (i = 0; i < index.length; i++) {
            System.out.printf("data[%d] : ", index[i]);
            data[index[i]].showNode();
        }
    }
}
