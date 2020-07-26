public class Dnode implements Comparable <Dnode> {
    private String word;
    private String mean;
    private String type;

    @Override
    public int compareTo(Dnode x) {
        return this.word.compareToIgnoreCase(x.word);//Compare word.
    }

    //Constructor
    Dnode() {}

    Dnode(String buff) {
        String [] str = buff.split(",");
        word = str[0];
        mean = str[1];
        type = str[2];
    }

    //Getter and Setter
    void setWord(String word) {
        this.word = word;
    }

    String getWord() {
        return word;
    }


    boolean compareAll(Dnode x) {
        //Compare word, mean and type.
        return this.word.equalsIgnoreCase(x.word) && this.mean.equalsIgnoreCase(x.mean) && this.type.equalsIgnoreCase(x.type);
    }

    //Show word mean and type.
    void show(int count) {
        System.out.println(count + ") " + word + "      " + mean + "(" + type + ")");
    }
}