import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String WHITE_SPACE = " ";

    public String getResult(String sentence) {
        try {
            List<Input> wordFrequencyList = calculateWordFrequency(sentence);

            wordFrequencyList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            return buildWordFrequencyResult(wordFrequencyList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String buildWordFrequencyResult(List<Input> wordFrequencyList) {
        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE);
        for (Input wordFrequency : wordFrequencyList) {
            String wordFrequencyLine = String.format("%s %d", wordFrequency.getValue(), wordFrequency.getWordCount());
            wordFrequencyResult.add(wordFrequencyLine);
        }
        return wordFrequencyResult.toString();
    }

    private List<Input> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream().map(word -> new Input(word, Collections.frequency(words, word)))
                .collect((Collectors.toList()));
    }
}
