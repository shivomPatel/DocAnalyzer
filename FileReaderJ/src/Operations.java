import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * @Name File Reader
 *
 * The passiveWordsScanner function is inaccurate due to the fact that it doesn't
 * fully adhere to the grammatical rules of the passive tense. AI would be needed to
 * @version January 13, 2019
 * @Author Shivom Patel
 * */

public class Operations {

    private static String[] passiveWordsList = {
            "is",
            "are",
            "was",
            "were",
            "has",
            "have",
            "had"};
    private static String[] passivePhrasesList = {
            "is being",
            "are being",
            "is being",
            "was being",
            "were being",
            "has been",
            "have been",
            "had been",
            "will be",
            "will have been",
            "would be",
            "should be",
            "could be",
            "should've been",
            "could've been",
            "would've been",
            "should have been",
            "could have been",
            "would have been"};
    private static String[] adjacentVowels = {};

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static void echo(File file) {
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new java.io.FileReader(file));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Something went wrong in the echo function.");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int wordCounter(File file) {
        int sumWords = 0;
        String line;
        String[] wordsInLine;
        BufferedReader br;
        ArrayList<String[]> wordsPerLine = new ArrayList<String[]>();
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                wordsInLine = line.split(" ");
                wordsPerLine.add(wordsInLine);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in the wordCounter function.");
        }

        for (int i = 0; i < wordsPerLine.size(); i++) {
            sumWords += wordsPerLine.get(i).length;
        }
        return sumWords;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int sentenceCounter(File file) {
        int sentenceCounter = 0;
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '.') {
                        sentenceCounter++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong in the sentenceCounter function.");
        }
        return sentenceCounter;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int passiveWordScanner(File file) {
        String line;
        String numWords;
        String passiveWord;
        int passiveWordCounter = 0;
        BufferedReader br;


        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < passiveWordsList.length; i++) {
                    for (int j = 0; j < line.length() - passiveWordsList[i].length(); j++) {
                        if (line.substring(j, j + passiveWordsList[i].length()).equals(passiveWordsList[i])) {
                            passiveWordCounter++;
                        }
                    }
                }

                for (int i = 0; i < passivePhrasesList.length; i++) {
                    for (int j = 0; j < line.length() - passivePhrasesList[i].length(); j++) {
                        if (line.substring(j, j + passivePhrasesList[i].length()).equals(passivePhrasesList[i])) {
                            passiveWordCounter++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in the passiveWordScanner function.");
        }

        return passiveWordCounter;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static double fleschKincaidCalculator(File file) {
        double FKC = 0.0;
        double average_sentence_length = 0.0;
        double average_syllables_per_word = 0.0;

        double total_words = wordCounter(file);
        double total_syllables = syllableCounter(file);
        double total_sentences = sentenceCounter(file);

        average_sentence_length = total_words / total_sentences;
        average_syllables_per_word = total_syllables / total_words;

        FKC = 206.835 - (1.015 * average_sentence_length) - (84.6 * average_syllables_per_word);

        return FKC;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static int syllableCounter(File in_file) {

        String line = "";
        BufferedReader br;
        ArrayList<String[]> words = new ArrayList<>();
        String wordsInLine[];

        int syllableCounter = 0;

        try {
            br = new BufferedReader(new FileReader(in_file));
            while ((line = br.readLine()) != null) {
                wordsInLine = line.split(" ");
                words.add(wordsInLine);
            }

        } catch (IOException e) {
            System.out.println("Something went wrong in syllableCounter()!");
        }

        int a_count = 0;
        int e_count = 0;
        int i_count = 0;
        int o_count = 0;
        int u_count = 0;
        int y_count = 0;

        int num_adjacent_vowels = 0;
        int word_is_num_count = 0;


        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length; j++) {
                String word = words.get(i)[j];

                /* Counting all the vowels in each word */

                for (int k = 0; k < word.length(); k++) {
                    if (word.charAt(k) == 'a') {
                        a_count++;
                    }
                    if (word.charAt(k) == 'e') {
                        if (k == word.length() - 1 && word.length() > 3) {
                            e_count--;
                        }
                        e_count++;
                    }
                    if (word.charAt(k) == 'i') {
                        i_count++;
                    }
                    if (word.charAt(k) == 'o') {
                        o_count++;
                    }
                    if (word.charAt(k) == 'u') {
                        u_count++;
                    }
                }

                /* Deducting count for all adjacent vowel combinations */

                num_adjacent_vowels += Vowels.comboHunter(word);

                /* Special Case for ending y */

                if (word.charAt(word.length() - 1) == 'y') {
                    y_count++;
                }

                /* Special Case for numbers */

                if (isNumeric(word)) {
                    if (word.contains(".") || word.contains("!") || word.contains("?") || word.contains(",")) {
                        word = word.substring(0, word.length() - 1);
                    }

                    if (Integer.parseInt(word) == 7) {
                        word_is_num_count += 2;
                        break;
                    }
                    if (Integer.parseInt(word) == 11) {
                        word_is_num_count += 3;
                        break;
                    }
                    word_is_num_count += word.length();
                }

            }
        }

        int total_syllable_count = a_count + e_count + i_count + o_count + u_count;
        total_syllable_count -= num_adjacent_vowels;
        total_syllable_count += y_count;
        total_syllable_count += word_is_num_count;


        /* This is for printing the words in the arrayList */
//        for (int i = 0; i < words.size(); i++) {
//            for (int j = 0; j < words.get(i).length; j++) {
//                System.out.println(words.get(i)[j]);
//            }
//        }
        return total_syllable_count;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static boolean isNumeric(String word) {
        try {
            double num = Double.parseDouble(word);

        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
