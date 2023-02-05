import java.util.List;

public class FileSort {
    private Config config;
    private List<String> inputFiles;
    private String outputFile;
    public FileSort(Config config, List<String> inputFiles, String outputFile){
        this.config = config;
        this.inputFiles = inputFiles;
        this.outputFile = outputFile;
    }
}
