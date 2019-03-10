import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;


public class FileAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numWords;
        int numSentences;
        double numPassiveWords;
        boolean pass1 = true;
        File file;
        String selection;
        // ----------------------------------------------------------------------
        System.out.println("Welcome to FileReader");

        do{
            System.out.print("Enter The File Path Of The Desired Text File(.txt): ");
            String fileName = scanner.nextLine();
            file = new File(fileName);

            if (!file.exists()) {
                System.out.println("File Does Not Exist.");
            }

        } while(!file.exists());

        do {
            do {
                System.out.println("\nSimple File Reader");
                System.out.println("-----------------------------------------");
                System.out.println("1) Echo");
                System.out.println("2) Word Count");
                System.out.println("3) Sentence Count");
                System.out.println("4) Passive Voice Detector");
                System.out.println("5) Calculate Flesch-Kincaid Reading Level");
                System.out.println("6) Close Program");
                System.out.println("-----------------------------------------");
                System.out.print("Enter: ");
                selection = scanner.nextLine();

                if (selection.length() > 1) {
                    System.out.println("Error: Option Not Available");
                } else if (Character.isLetter(selection.charAt(0))) {
                    System.out.println("Error: Option Not Available");
                } else if (Integer.parseInt(selection) < 0 || Integer.parseInt(selection) > 6) {
                    //TODO: change this to 6 later because you only have 6 legitimate options
                    //TODO: one is for the test
                    System.out.println("Error: Option Not Available");
                } else {
                    pass1 = false;
                }

            } while (pass1);

            switch(selection) {
                case "1":
                    System.out.println("\nBeginning of File\n");
                    Operations.echo(file);
                    System.out.println("\nEnd of File\n");
                    break;
                case "2":
                    numWords = Operations.wordCounter(file);
                    System.out.println("\nWord Count: " + numWords);
                    break;
                case "3":
                    numSentences = Operations.sentenceCounter(file);
                    System.out.println("Sentence Count: " + numSentences);
                    break;
                case "4":
                    // this will be given as a percentage or a score out of 10;
                    numWords = Operations.wordCounter(file);
                    numPassiveWords = Operations.passiveWordScanner(file);
                    System.out.printf("\nThis text file contains %.1f%% passive voice.\n", (numPassiveWords / numWords) * 100);
                    break;
                case "5":
                    double flesch_kincaid = Operations.fleschKincaidCalculator(file);
                    System.out.println("Flesch-Kincaid Reading Level: " + flesch_kincaid);
                    break;
//                case "7":
//                    int num = Operations.syllableCounter(file);
//                    System.out.println("Number of syllables is = " + num);
//                    break;
            }

        } while (!selection.equals("6"));
        System.out.println("Ending Program...");


    } // main

} // FileReader

