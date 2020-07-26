import java.util.Comparator;

//This class(node) means 1 data consisting of 3 fields
class node {
    private long field1;
    private String field2;
    private String field3;

    node(long field1, String field2, String field3) {
        //Constructor for set data default
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    //For other class get field1
    long getField1() {
        return field1;
    }

    //For other class get field3
    String getField3() {
        return field3;
    }

    //Comparator for compare field1(long data)
    static Comparator<node> CmpField1 = (o1, o2) -> {
        if (o1.field1 - o2.field1 > 0) return 1;
        else if (o1.field1 - o2.field1 < 0) return -1;
        else return 0;
    };

    //Comparator for compare field3(String)
    static Comparator<node> CmpField3 = (o1, o2) -> o1.field3.compareToIgnoreCase(o2.field3);

    //Show field1 field2 field3
    void showNode() {
        System.out.println(field1 + "," + field2 + "," + field3);
    }
}
