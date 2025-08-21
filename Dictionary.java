package backend;

import java.util.Scanner;
import java.io.File;


public class Dictionary {

    CacheManager exist, notExist;
    BloomFilter bf;
    private String[] fileNames;

    public Dictionary(String... fileNames) {

        exist = new CacheManager(400, new LRU());
        notExist = new CacheManager(100, new LFU());
        bf = new BloomFilter(256, "MD5", "SHA1");

        this.fileNames = fileNames;
        for (String fileName : fileNames) {

            try {

                Scanner s = new Scanner(new File(fileName));
                while (s.hasNext()) {
                    bf.add(s.next());
                }
                s.close();

            } catch (Exception e) {
            }
        }
    }

    public boolean query(String word) {

        if (exist.query(word))
            return true;

        if (notExist.query(word))
            return false;

        boolean doesExist = bf.contains(word);

        if (doesExist)

            exist.add(word);
        else
            notExist.add(word);

        return doesExist;
    }

    public boolean challenge(String word) {

        try {

            boolean doesExist = IOSearcher.search(word, fileNames);
            if (doesExist)
                exist.add(word);
            else
                notExist.add(word);

            return doesExist;

        } catch (Exception e) {
            return false;
        }
    }

}
