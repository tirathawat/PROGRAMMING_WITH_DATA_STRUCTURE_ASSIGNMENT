import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Assignment7 {
    public static void main (String[] args) {
        TreeSet<Bnode> dict = new TreeSet<>();
        ResultSearch rs = new ResultSearch();//Search results.
        int count;

        if ((count = readLexitronFile(dict)) != -1) {
            System.out.printf("Total Read %d records.\n", count);//Show read records.
            System.out.printf("Total word size %d words.\n", dict.size());//Show word size.
            System.out.printf("Total meaning size %d words.\n", countMean(dict));//Show meaning size.

            findMaxMean(dict, rs);//Finding the most meaning.

            //Show result of finding the most meaning.
            System.out.printf("Maximum Meaning word %s have %d meaning.\n", rs.getResult().word, rs.getResult().mean.size());

            rs.getResult().showWord();//Show the words that have the most meaning.

            do {
                if (searchVocab(dict, askUser(), rs)) {//Ask users to search for a word and then search.
                    //Search term found.

                    //Show results from search
                    System.out.printf("found %s %d means at %d\n", rs.getResult().word, rs.getResult().mean.size(), rs.getIndex());

                    rs.getResult().showWord();//Show the search terms
                }
                else System.out.println(rs.getResult().word + " Not found");//No words found.
            }while (!rs.getResult().word.equalsIgnoreCase("end"));//Keep doing until user search for end.
        }

        //end program
        System.out.println("End program");
        System.out.println("Program written by Tirathawat Chansarekorn 62070501022");
    }


    private static String askUser() {
        //Get searches from users.

        System.out.print("Enter word : ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static Boolean searchVocab(TreeSet<Bnode> dict, String word, ResultSearch rs) {
        Bnode key = new Bnode();//This node used to search.
        key.word = word.toLowerCase().replaceAll("\\s+", " ").trim();//Make the search term suitable for search
        if (dict.contains(key)) {
            //Found;
            TreeSet<Bnode> z = (TreeSet<Bnode>) dict.subSet(key, true, key, true);
            rs.setResult(z.first());//Set result.
            rs.setIndex(dict.headSet(key).size());//Set index.
            return true;
        }
        else {
            //Not found;
            rs.setResult(key);//Set result.
            rs.setIndex(-1);//Set index = -1.
            return false;
        }
    }

    private static void findMaxMean(TreeSet<Bnode> dict, ResultSearch rs) {
        Iterator<Bnode> itr = dict.iterator();//Used for travel tree set.
        Bnode x;//Used for point other node.
        int countMax = 0;//Set maximum meaning = 0.
        int check;
        while (itr.hasNext()) {
            x = itr.next();//Point to current node.
            check = x.mean.size();//Check mean size.
            if (check > countMax) {
                //Current node have maximum mean.
                countMax = check;//Set new maximum meaning
                rs.setResult(x);//Set result.
                rs.setIndex(dict.headSet(x).size());//Set index.
            }
        }
    }

    private static int countMean(TreeSet<Bnode> dict) {
        Iterator<Bnode> itr = dict.iterator();//Used for travel tree set.
        int count = 0;//Used to count the number of all meaning.
        while (itr.hasNext()) count += itr.next().mean.size();//Count meaning.
        return count;
    }


    private static int readLexitronFile(TreeSet<Bnode> data) {
        String buff;
        int count = 0;
        try {
            // Set FileInputStream & Encoding
            FileInputStream fr = new FileInputStream("src\\utf8lexitron.csv");
            InputStreamReader csv = new InputStreamReader(fr, StandardCharsets.UTF_8);
            BufferedReader fsc = new BufferedReader(csv);

            fsc.read();//Remove BOM.

            while ((buff = fsc.readLine()) != null) {//Read until end of data.
                Bnode x = new Bnode(buff);//Create object.
                if (data.contains(x)) {
                    //The words that are read the same as the words already existing.

                    //Find duplicate words.
                    TreeSet<Bnode> z = (TreeSet<Bnode>) data.subSet(x,  true, x, true);

                    //If the meaning is not repeated, add the meaning of that word.
                    if (!z.first().mean.contains(x.mean.get(0))) z.first().mean.addAll(x.mean);
                }
                else
                    data.add(x);//The words that are read are not the same as those that already exist then add this word to dict
                count++;//Count read records.
            }
            fsc.close();//Close file.
        } catch (FileNotFoundException e) {
            //Error finding file.
            System.out.println(e.getMessage());
            System.out.println("File not found");
            return -1;
        } catch (Exception e) {
            //General error.
            System.out.println("Error " + e.getMessage());
            System.out.println("Operation error");
            return -1;
        }
        return count;
    }
}
