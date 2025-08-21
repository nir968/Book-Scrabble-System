package backend;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {

    private DictionaryManager dictionaryManager;
    private PrintWriter out;
    private Scanner in;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

        this.out = new PrintWriter(outToClient);
        this.in = new Scanner(inFromclient);
        this.dictionaryManager = DictionaryManager.get();

        String inputFromClient = in.nextLine();//read the string till the \n
        boolean isWordExists;

        if (inputFromClient.startsWith("Q")) {

            String[] words = inputFromClient.substring(2).split(",");
            isWordExists = dictionaryManager.query(words);

        } else if (inputFromClient.startsWith("C")) {

            String[] words = inputFromClient.substring(2).split(",");
            isWordExists = dictionaryManager.challenge(words);
        } else {

            out.println("false");
            out.flush();
            return;
        }

        out.println(isWordExists ? "true" : "false");
        out.flush();
    }

    @Override
    public void close() {
        out.close();
        in.close();
    }

}
