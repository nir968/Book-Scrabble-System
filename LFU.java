package backend;

import java.util.HashMap;
import java.util.PriorityQueue;



public class LFU implements CacheReplacementPolicy {

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    PriorityQueue<String> queue = new PriorityQueue<>((s1, s2) -> {
        return map.get(s1).intValue() - map.get(s2).intValue();

    });

    @Override
    public void add(String word) {

        if (map.containsKey(word)) {

            int count = map.get(word) + 1;
            map.put(word, count);
            queue.remove(word);
            queue.add(word);

        } else {
            map.put(word, 1);
            queue.add(word);
        }
    }

    @Override
    public String remove() {

        String r = queue.poll();
        map.remove(r);
        return r;
    }

}