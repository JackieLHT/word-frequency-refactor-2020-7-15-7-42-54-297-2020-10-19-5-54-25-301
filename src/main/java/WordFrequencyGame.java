import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String NEW_LINE = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String WHITE_SPACE = " ";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(WHITE_SPACE_REGEX);

            List<Input> inputList = new ArrayList<>();
            for (String word : words) {
                Input input = new Input(word, 1);
                inputList.add(input);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<Input>> wordFrequencyMap = getListMap(inputList);

            List<Input> wordFrequencyList = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : wordFrequencyMap.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().size());
                wordFrequencyList.add(input);
            }
            inputList = wordFrequencyList;

            inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());

            StringJoiner joiner = new StringJoiner(NEW_LINE);
            for (Input wordFrequency : inputList) {
                String wordFrequencyLine = wordFrequency.getValue() + WHITE_SPACE + wordFrequency.getWordCount();
                joiner.add(wordFrequencyLine);
            }
            return joiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}
