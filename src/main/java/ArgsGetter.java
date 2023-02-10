
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArgsGetter {
    private String[] args;

    public ArgsGetter(String[] args){
        this.args = args;
    }
    public ArgsData getDataFromArgs() throws IllegalArgumentException, IndexOutOfBoundsException{
        ArgsData argsData = new ArgsData();

        if(args[0].equals("-a")){
            if(args[1].equals("-s")){
                argsData.setDataType(DataType.STRING);
                argsData.setSortType(SortType.ASCENDING);
                args[0] = null;
                args[1] = null;

            }else if(args[1].equals("-i")){
                argsData.setDataType(DataType.INTEGER);
                argsData.setSortType(SortType.ASCENDING);
                args[0] = null;
                args[1] = null;
            }else throw new IllegalArgumentException("Wrong data format");
        }else if(args[0].equals("-d")){
            if(args[1].equals("-s")){
                argsData.setDataType(DataType.STRING);
                argsData.setSortType(SortType.DESCENDING);
                args[0] = null;
                args[1] = null;
            }else if(args[1].equals("-i")){
                argsData.setDataType(DataType.INTEGER);
                argsData.setSortType(SortType.DESCENDING);
                args[0] = null;
                args[1] = null;
            }else throw new IllegalArgumentException("Wrong data format");
        }else if(args[0].equals("-i")){
            argsData.setDataType(DataType.INTEGER);
            argsData.setSortType(SortType.ASCENDING);
            args[0] = null;
        }else if(args[0].equals("-s")){
            argsData.setDataType(DataType.STRING);
            argsData.setSortType(SortType.ASCENDING);
            args[0] = null;
        }else throw new IllegalArgumentException("Wrong data/sort format");
        int i = 0;
        while (args[i] == null){
            i++;
        }
        if(!args[i].contains(".txt")){
            throw new IllegalArgumentException("output file must be a .txt");
        }
        argsData.setOutputFile(args[i]);
        args[i] = null;

        Set<String> inputFiles = new HashSet<>();
        for(i = 0; i < args.length;i++){
            if(args[i]==null){
                continue;
            }
            if(args[i].contains(".txt")){
                File file = new File(args[i]);
                if(file.exists()) inputFiles.add(args[i]);
                else throw new IllegalArgumentException("File " + args[i]+ " does not exists");
            }else throw new IllegalArgumentException(args[i] + " not a .txt file");
        }
        argsData.setInputFiles(inputFiles);
        return argsData;


    }


}
