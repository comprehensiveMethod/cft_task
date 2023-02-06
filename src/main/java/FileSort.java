import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FileSort {
    private DataType dataType;
    private SortType sortType;
    private List<String> inputFiles;
    private String outputFile;
    public FileSort(ArgsData argsData){
        this.outputFile = argsData.getOutputFile();
        this.inputFiles = argsData.getInputFiles();
        this.dataType = argsData.getDataType();
        this.sortType = argsData.getSortType();
    }
    public void sortAndWrite(){
        HashMap<String,FileData> fileDataHashMap = new HashMap<>();
        try{
            List<FileReader> frs = new ArrayList<>();
            List<BufferedReader> readers = new ArrayList<>();
            Iterator iterator = inputFiles.iterator();
            while (iterator.hasNext()){
                FileReader fr = new FileReader(iterator.next().toString());
                BufferedReader reader = new BufferedReader(fr);
                frs.add(fr);
                readers.add(reader);
            }


        }catch (IllegalStateException e){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }
    public Object findMinOrMaxInHashmap(HashMap<String,FileData> fileDataHashMap, List<String> inputFiles){

    }
}
