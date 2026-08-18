package com.jalch.kata.algorithm.lang;

import java.util.*;
import java.util.stream.Collectors;

//Problem:
// N-gram - Programming Challenge Description: Your task is to build a type-ahead feature for an upcoming product.
// When the user enters a word or set of words, we want to be able to "predict" what they're going to type next with
// some level of accuracy. We've chosen to implement this using the N-Gram algorithm defined here
// http://en.wikipedia.org/wiki/N-gram.
// Your program should return a tuple of predictions sorted high to low based on the prediction score (up to a maximum
// of three decimal places, or pad with zeroes up to three decimal places i.e. 0.2 should be shown as 0.200),
// (if predictions share the same score, they are sorted alphabetically.) Words should be split by whitespace with all
// non-alphanumeric characters stripped off the beginning and end.
//
// Prediction scores are calculated like this: Occurrences of a word after an N-gram / total number of words after an
// N-gram e.g. for an N-gram of length 2: 'ONE TWO ONE TWO THREE TWO THREE' -> "TWO" has the following predictions:
// THREE:0.666,ONE:0.333 "THREE" occurred 2 times after a "TWO" and "ONE" occurred 1 time after a "TWO", for a total
// of 3 occurrences after a "TWO".
// Your program will getProbableWords against the following text. You may hardcode it into your program:
// Mary had a little lamb its fleece was white as snow; And everywhere that Mary went, the lamb was sure to go. It
// followed her to school one day, which was against the rule; It made the children laugh and play, to see a lamb at
// school. And so the teacher turned it out, but still it lingered near, And waited patiently about till Mary did appear.
// "Why does the lamb love Mary so?" the eager children cry;"Why, Mary loves the lamb, you know" the teacher did reply."
//
// Input: Your program should read lines of text from standard input. Each line contains a number followed by a string,
// separated by a comma. The number is the n-gram length. The string is the text from the user. You will be predicting
// the text following this string. Output: For each line of input print a single line to standard output which is the
// predictions for what the user is going to type next.
//
// Test Input 2,the Expected Output lamb,0.375;teacher,0.250;children,0.125;eager,0.125;rule,0.125
public class NGramTypeAhead {

    private static final String CORPUS_TEXT = "Mary had a little lamb its fleece was white as snow;" +
            "And everywhere that Mary went, the lamb was sure to go. " +
            "It followed her to school one day, which was against the rule;" +
            "It made the children laugh and play, to see a lamb at school." +
            "And so the teacher turned it out, but still it lingered near," +
            "And waited patiently about till Mary did appear." +
            "Why does the lamb love Mary so?\" the eager children cry;" +
            "\"Why, Mary loves the lamb, you know\" the teacher did reply.";

    private static int totalNGramOccurrences;

    public static void main(String... args){
        run();
    }

    public static void run() {
        System.out.println("Please enter an n-gram length and text to predict the next word.");
        System.out.println("Type 'exit' to terminate the program.");
        System.out.println("");
        System.out.println("The text used to predict the next word is:");
        System.out.println(CORPUS_TEXT);
        String input = null;
        Scanner scanner = new Scanner(System.in);
        while (!"exit".equals(input)) {
            System.out.println("Use the format 'n,text'. e.g '2,the' or '3,Mary had'. Then press Enter.");
            input = scanner.nextLine();
            if (!"exit".equals(input.toLowerCase())) {
                if (input.matches("^[0-9]+,\\w+(\\s\\w+)*$")) {
                    String[] split = input.split(",");
                    String probableWordsWithProbabilities = getProbableWords(Integer.parseInt(split[0]), split[1], CORPUS_TEXT);
                    System.out.println(probableWordsWithProbabilities);
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
        scanner.close();
    }


    public static String getProbableWords(int n, String inputText, String corpusText) {

        if (inputText == null || inputText.trim().isEmpty())
            return "Invalid input text, cannot be null, empty or be just space characters.";
        if (n <= 1) return "Cannot predict next word with a unigram or n < 1.";
        totalNGramOccurrences = 0;

        //Assumption: If the input text has more words than n, then the last (n-1) words are extracted.
        List<String> nGramSuitableInputWords = cleanAndAdaptUserText(n, inputText);
        List<String> corpusWords = cleanAndTokenizeWords(corpusText);
        TreeMap<Integer, TreeSet<String>> probableNextWordsWithOccurrencesCount =
                scanWithNGramWindow(n, nGramSuitableInputWords, corpusWords);

        return buildResultString(probableNextWordsWithOccurrencesCount);
    }

    private static List<String> cleanAndAdaptUserText(int n, String userText) {
        List<String> userWords = cleanAndTokenizeWords(userText);
        int userWordsSize = userWords.size();
        return userWordsSize >= n ?
                userWords.subList(userWords.size() - n + 1, userWords.size())
                : userWords;
    }

    private static List<String> cleanAndTokenizeWords(String text) {
        return Arrays.asList(text.trim().replaceAll("[^\\p{Alnum}\\s]", " ").trim().toLowerCase().split("\\s+"));
    }

    private static TreeMap<Integer, TreeSet<String>> scanWithNGramWindow(int n, List<String> nGramSuitableInputWords,
                                                                  List<String> corpusWords) {

        int upperWindowIndexForSublist = n;
        Map<String, Integer> probableWordsAndOccurrences = new HashMap<>();

        for (int lowerWindowIndexForSublist = 0; upperWindowIndexForSublist <= corpusWords.size(); lowerWindowIndexForSublist++) {
            List<String> nGramElement = corpusWords.subList(lowerWindowIndexForSublist, upperWindowIndexForSublist);
            int indexOfSubList = Collections.indexOfSubList(nGramElement, nGramSuitableInputWords);

            if (indexOfSubList != -1) {
                int indexOfPossibleNextWord = indexOfSubList + nGramSuitableInputWords.size();
                if (indexOfPossibleNextWord < nGramElement.size()) {
                    totalNGramOccurrences++;
                    String probableWordFound = nGramElement.get(indexOfPossibleNextWord);
                    int occurrencesCount = probableWordsAndOccurrences.getOrDefault(probableWordFound, 0);
                    probableWordsAndOccurrences.put(probableWordFound, ++occurrencesCount);
                }
            }
            upperWindowIndexForSublist++;
        }

        return getSortedOccurrencesMap(probableWordsAndOccurrences);
    }

    private static String buildResultString(TreeMap<Integer, TreeSet<String>> probableNextWordsWithOcurrencesCount) {
        StringBuilder resultBuilder = new StringBuilder();
        String prefix = "";
        for (Map.Entry<Integer, TreeSet<String>> entry : probableNextWordsWithOcurrencesCount.entrySet()) {
            resultBuilder.append(prefix);
            prefix = ";";
            resultBuilder.append(getProbabilityString(entry));
        }
        return resultBuilder.toString();

    }

    private static TreeMap<Integer, TreeSet<String>> getSortedOccurrencesMap(Map<String, Integer> occurrencesMap) {

        TreeMap<Integer, TreeSet<String>> sortedProbableWordsOccurrences = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {

            if (sortedProbableWordsOccurrences.containsKey(entry.getValue())) {
                TreeSet<String> wordsForOccurrenceCount = sortedProbableWordsOccurrences.get(entry.getValue());
                wordsForOccurrenceCount.add(entry.getKey());
            } else {
                TreeSet<String> wordsForOccurrenceCount = new TreeSet<>();
                wordsForOccurrenceCount.add(entry.getKey());
                sortedProbableWordsOccurrences.put(entry.getValue(), wordsForOccurrenceCount);
            }

        }
        return sortedProbableWordsOccurrences;
    }

    private static String getProbabilityString(Map.Entry<Integer, TreeSet<String>> entry) {

        String probability = getProbability(entry.getKey());
        return String.join(";",
                entry.getValue().stream().map(word -> word + "," + probability).collect(Collectors.toList()));
    }

    private static String getProbability(int count) {
        Float result = (float) count / totalNGramOccurrences;
        return String.format("%.3f", result);
    }

}
