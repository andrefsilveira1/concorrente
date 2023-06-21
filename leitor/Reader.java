package leitor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class Reader {
    private String filePath;
    public void reader(String filePath) {
        this.filePath = filePath;
    }
    public List<Integer> read(String filePath) {
        List<Integer> content = new ArrayList<Integer>();

        try {

            File file = new File("./input/"+ filePath + ".in");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                int itemList = Integer.valueOf(scanner.next());
                content.add(itemList);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return content;
    }
}
