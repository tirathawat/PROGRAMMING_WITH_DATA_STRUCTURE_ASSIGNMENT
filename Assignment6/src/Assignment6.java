import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assignment6 {
    public static void main(String[] args) {

        int count;
        ArrayList<Dnode> dict = new ArrayList<> ();
        ResultMaxMean rMaxMean = new ResultMaxMean();//The result of finding the most meaning.
        ResultSearch rs = new ResultSearch();//Search results.

        if ((count = readLexitronFile(dict)) != -1) {
            System.out.printf("Total Read %d records.\n", count);//Show read records.
            System.out.printf("Total duplicate found %d records.\n", duplicate(dict));//Show duplicate found records.
            System.out.printf("Total remaining size %d records.\n", dict.size());//Show remaining records.

            findMaxMean(dict, rMaxMean);//Finding the most meaning.
            System.out.println("Maximum Meaning word " + dict.get(rMaxMean.getIndex()).getWord() + " have "
                    + rMaxMean.getCount() + " meaning.");//Show result of finding the most meaning.
            showVocab(dict, rMaxMean.getIndex(), rMaxMean.getCount());//Show the words that have the most meaning.

            do {
                searchVocab(dict, askUser(), rs);//Ask users to search for a word and then search.
                if (rs.isFound()) {
                    //Search term found.

                    //Show results from search
                    System.out.printf("found %s %d words at %d - %d\n", rs.getStr(), rs.getCount(), rs.getStart(), rs.getStop());

                    showVocab(dict, rs.getStart(), rs.getCount());//Show the search terms
                }
                else System.out.println(rs.getStr() + " Not found");//No words found.
            }while (!rs.getStr().equalsIgnoreCase("end"));//Keep doing until user search for end.
        }

        //end program
        System.out.println("End program");
        System.out.println("Program written by Tirathawat Chansarekorn 62070501022");
    }

    private static void searchVocab(ArrayList<Dnode> dict, String str, ResultSearch rs) {
        Dnode key = new Dnode();

        //Save what users were searching for in the results.
        rs.setStr(str.toLowerCase().replaceAll("\\s+", " ").trim());

        //Set the search term.
        key.setWord(rs.getStr());

        int index = Collections.binarySearch(dict, key);//Search for words with Binary search.
        int i = index, j = index;

        if (index >= 0) {
            //Found.

            //Check words before and after.
            while (index != dict.size() - 1 && dict.get(i + 1).compareTo(dict.get(index)) == 0) ++i;
            while (index != 0 && dict.get(j - 1).compareTo(dict.get(index)) == 0) --j;

            //Save result.
            rs.setStart(j);
            rs.setStop(i);
            rs.setCount(i - j + 1);
            rs.setFound(true);
        }
        else rs.setFound(false);//Not found.
    }

    private static String askUser() {
        //Get searches from users.

        System.out.print("Enter word : ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void showVocab(ArrayList<Dnode> dict, int firstIndex, int count) {
        //Show vocabulary from First Index to the number of count.
        for (int i = firstIndex, j = 1; j <= count; i++) dict.get(i).show(j++);
    }

    private static void findMaxMean(ArrayList<Dnode> dict, ResultMaxMean result) {
        int count = 1, i, indexMax = -1, countMax = 0;
        for (i = 0; i < dict.size() - 1; i++) {
            if (dict.get(i).compareTo(dict.get(i + 1)) == 0)//Search for adjacent repeated words.
                count++;//The adjacent words are the same, therefore counting.
            else{
                //When the adjacent words are not the same then find the maximum number of repeated words
                //and it's index at that time.
                if (count > countMax) {
                    countMax = count;
                    indexMax = i - count + 1;
                }

                count = 1;//Prepare to count again.
            }
        }

        //Save result.
        result.setCount(countMax);
        result.setIndex(indexMax);
    }

    private static int duplicate(ArrayList<Dnode> dict) {
        int countDup = 0;
        Collections.sort(dict);//Sort words.
        for (int i = 0; i < dict.size() - 1; i++) {
            if (dict.get(i).compareAll(dict.get(i + 1))){//Search for adjacent repeated words.
                dict.remove(i + 1);//Remove duplicate words.
                i--;
                countDup++;//Count duplicate words.
            }
        }
        return countDup;
    }

    private static int readLexitronFile(ArrayList<Dnode> data) {
        String buff;
        int count = 0;
        try {
            // Set FileInputStream & Encoding
            FileInputStream fr = new FileInputStream("src\\utf8lexitron.csv");
            InputStreamReader csv = new InputStreamReader(fr, StandardCharsets.UTF_8);
            BufferedReader fsc = new BufferedReader(csv);

            fsc.read();//Remove BOM.

            while ((buff = fsc.readLine()) != null) {//Read until end of data.
                Dnode x = new Dnode(buff.toLowerCase().replaceAll("\\s+", " ").trim());//Create object.
                data.add(x);//Save object to array list.
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

