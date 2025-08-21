package backend;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {

    private static DictionaryManager dictionaryManager = null;
    private HashMap<String, Dictionary> map;

    private DictionaryManager() {

        this.map = new HashMap<>();
    }

    public static DictionaryManager get() {

        if (dictionaryManager == null)
            dictionaryManager = new DictionaryManager();

        return dictionaryManager;
    }

    private boolean process(String[] args, WordProcessor processor) {

        String word = args[args.length - 1];
        boolean wordExists = false;

        for (int i = 0; i < args.length - 1; i++) {

            String key = args[i];
            map.putIfAbsent(key, new Dictionary(key)); // if doesn't exist -> so adding to the map
            if (processor.process(map.get(key), word))
                wordExists = true;
        }
        return wordExists;
    }

    public boolean challenge(String... args) {

        return process(args, (dictionary, word) -> dictionary.challenge(word));
    }

    public boolean query(String... args) {

        return process(args, (dictionary, word) -> dictionary.query(word));
    }

    public int getSize() {
        return map.size();
    }

    interface WordProcessor {

        boolean process(Dictionary dictionary, String word);
    }

}
