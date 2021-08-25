import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class App {
    private static String BASE_PATH = "D:\\PROJECTBIRTEK\\SIMPLEWEBAPP\\TESTKERJAAAPS\\";

    public static void main(String[] args) throws Exception {
        String thePath = BASE_PATH+"test_folder";

        try (Stream<Path> files = Files.walk(Paths.get(thePath)) ) {
           
            Stream<Path> countFile = files.filter(v -> {
                return !v.toFile().isDirectory();
            });
            System.out.println( "There is " + (countFile.count()) + " files in "+thePath);
            // System.out.println( "There is " + (counting) + " files in "+thePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Theres No Folder In Path");
        }
    }
}
