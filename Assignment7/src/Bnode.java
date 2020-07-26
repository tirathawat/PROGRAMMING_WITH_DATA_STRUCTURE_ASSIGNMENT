import java.util.ArrayList;

public class Bnode implements Comparable<Bnode> {
    String word;
    ArrayList<String> mean;

    //Default constructor
    Bnode() {
        word = "";
        mean = new ArrayList<>();
    }

    //Constructor
    Bnode(String buff) {
        String[] str = buff.replaceAll("\\s+", " ").trim().split(",");//Make it in the same style then split.

        //Save word and means.
        this.word = str[0];
        String meaning = str[1] + "(" + str[2] + ")";//Mean + Type
        this.mean = new ArrayList<>();//Has many means and therefore is stored in the array list.
        this.mean.add(meaning);
    }

    @Override
    public int compareTo(Bnode x) {
        return this.word.compareToIgnoreCase(x.word);
    }//Compare word.

    //Show word and means.
    void showWord(){
        for (int i = 0; i < mean.size(); i++) {
            System.out.println((i + 1) + ") " + word + "    " + mean.get(i));
        }
    }
}
