import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class dictMap {
    public static void main (String[] args) {
        HashMap<String, ArrayList<String>> dict = new HashMap <>();
        int count;

        if ((count = readLexitronFile(dict)) != -1) {
            System.out.printf("Total Read %d records.\n", count);//Show read records.
            System.out.printf("Total word size %d words.\n", dict.size());//Show word size.
            System.out.printf("Total meaning size %d words.\n", countMean(dict));//Show meaning size.

            String maxMean = findMaxMean(dict);//Finding the most meaning.

            //Show result of finding the most meaning.
            System.out.printf("Maximum Meaning word %s have %d meaning.\n", maxMean, dict.get(maxMean).size());

            showWord(dict, maxMean);//Show the words that have the most meaning.

            String ask;//For storing messages asked by users.
            do {
                ask = askUser().replaceAll("\\s+", " ").trim().toLowerCase();//Ask users and to search.
                if (dict.containsKey(ask)) {//Ask users to search for a word and then search.
                    //Search term found.

                    //Show results from search
                    System.out.printf("found %s %d means\n", ask, dict.get(ask).size());

                    showWord(dict, ask);//Show the search terms
                }
                else System.out.println(ask + " Not found");//No words found.
            }while (!ask.equalsIgnoreCase("end"));//Keep doing until user search for end.
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

    private static void showWord(HashMap<String, ArrayList<String>> dict, String key) {
        //Show word and meaning.
        for (int i = 0; i < dict.get(key).size(); i++) {
            System.out.println((i + 1) + ") " + key + "     " + dict.get(key).get(i));
        }
    }

    private static String findMaxMean(HashMap<String, ArrayList<String>> dict) {
        int countMax = 0;//Set maximum meaning = 0.
        int check;//Used to check number of meaning.
        String maxMean = null;//Word that have the most meaning
        for (String itr : dict.keySet()) {
            check = dict.get(itr).size();//Check number of meaning.
            if (check > countMax) {
                //Current word have maximum mean.
                countMax = check;//Set new maximum meaning.
                maxMean = itr;//Set Word that have the most meaning.
            }
        }
        return maxMean;
    }

    private static int countMean(HashMap<String, ArrayList<String>> dict) {
        int count = 0;//Used to count the number of all meaning.
        for (String itr : dict.keySet()) {
            count += dict.get(itr).size();//Count mean.
        }
        return count;
    }

    private static int readLexitronFile(HashMap<String, ArrayList<String>> data) {
        String buff;
        int count = 0;
        try {
            // Set FileInputStream & Encoding
            FileInputStream fr = new FileInputStream("src\\utf8lexitron.csv");
            InputStreamReader csv = new InputStreamReader(fr, StandardCharsets.UTF_8);
            BufferedReader fsc = new BufferedReader(csv);

            fsc.read();//Remove BOM.

            while ((buff = fsc.readLine()) != null) {//Read until end of data.
                String[] str = buff.replaceAll("\\s+", " ").trim().split(",");//Format read and split.
                String word = str[0].toLowerCase();//Make word to lower case and save.
                String mean = str[1] + "(" + str[2] + ")";//save mean
                if (data.containsKey(word)) {
                    //Have duplicate words.
                    ArrayList <String> z = data.get(word);//Pull all the meanings to z
                    if (!z.contains(mean)) {
                        //No duplicate meaning.
                        z.add(mean);//add mean.
                        data.put(word, z);//add word and mean to Hash map.
                    }
                }
                else {
                    //Do not have duplicate words.
                    ArrayList <String> z = new ArrayList<>();
                    z.add(mean);//add mean.
                    data.put(word, z);//add word and mean to Hash map.
                }
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
