import java.util.List;
import java.util.Set;

public class ArgsData {
    private DataType dataType;
    private SortType sortType;
    private Set<String> inputFiles;
    private String outputFile;
    public void setDataType(DataType dataType){
        this.dataType = dataType;
    }
    public void setSortType(SortType sortType){
        this.sortType = sortType;
    }
    public void setInputFiles(Set<String> inputFiles){
        this.inputFiles = inputFiles;
    }
    public void setOutputFile(String outputFile){
        this.outputFile = outputFile;
    }
    public DataType getDataType(){
        return this.dataType;
    }
    public SortType getSortType(){
        return this.sortType;
    }
    public Set<String> getInputFiles(){
        return this.inputFiles;
    }
    public String  getOutputFile(){
        return this.outputFile;
    }


}
