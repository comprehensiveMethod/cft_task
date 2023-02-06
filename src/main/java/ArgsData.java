import java.util.List;

public class ArgsData {
    private DataType dataType;
    private SortType sortType;
    private List<String> inputFiles;
    private String outputFile;
    public void setDataType(DataType dataType){
        this.dataType = dataType;
    }
    public void setSortType(SortType sortType){
        this.sortType = sortType;
    }
    public void setInputFiles(List<String> inputFiles){
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
    public List<String> getInputFiles(){
        return this.inputFiles;
    }
    public String  getOutputFile(){
        return this.outputFile;
    }


}
