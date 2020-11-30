import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String NEW_LINE = "\n";

    public String getResult(String sentence) throws CalculateErrorException {
        try {
            List<WordFrequency> wordFrequencyList = calculateWordFrequency(sentence);

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildWordFrequencyResult(wordFrequencyList);
        } catch (Exception exception) {
            throw new CalculateErrorException();
        }
    }

    private String buildWordFrequencyResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner wordFrequencyResult = new StringJoiner(NEW_LINE);
        for (WordFrequency wordFrequency : wordFrequencyList) {
            String wordFrequencyLine = String.format("%s %d", wordFrequency.getWord(), wordFrequency.getCount());
            wordFrequencyResult.add(wordFrequencyLine);
        }
        return wordFrequencyResult.toString();
    }

    private List<WordFrequency> calculateWordFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        HashSet<String> distinctWords = new HashSet<>(words);

        return distinctWords.stream().map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect((Collectors.toList()));
    }
}
