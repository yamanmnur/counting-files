import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {


    private static Stream<Path> files;
    public static void main(String[] args) throws Exception {
        String thePath = Constant.BASEPATH;

        files = Files.walk(Paths.get(thePath));
        
        try {
            List<String> dataFiles = convertToList(files);

            System.out.println( "There is " + dataFiles.size() + " files in "+thePath);
            
            List<Content> data = getContent(dataFiles);

            Content maxContent = data.stream().max(Comparator.comparing(Content::getLength)).get();
            System.out.println("MAX "+ maxContent.getContent() );

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println( "Theres No Folder In Path");
        }
    }

    public static List<Content> getContent(List<String> dataFiles) {
        Map<String, List<String>> contentMap = dataFiles.stream().collect(Collectors.groupingBy(v -> v));
            
        List<Content> content = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry: contentMap.entrySet()) {
            Content model = new Content(entry.getKey(),entry.getValue().size());
            content.add(model);

            System.out.println("There are " + entry.getValue().size() + " with content " + entry.getKey());
        }

        return content;
    }

    public static List<String> convertToList(Stream<Path> files) {
        List<String> dataFiles = new ArrayList<>();

        Supplier<Stream<Path>> streamSupplier = () -> files.filter(v -> {
            return !v.toFile().isDirectory();
        });

        streamSupplier.get().forEach(v -> {
            String content = readAllBytes(v.toFile().getPath());
            dataFiles.add(content);
        });

        return dataFiles;
    }

    private static String readAllBytes(String filePath) 
    {
        String content = "";
 
        try {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return content;
    }
}
