import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSort {
    private static Logger LOGGER = Logger.getLogger(FileSort.class.getName());
    private DataType dataType;
    private SortType sortType;
    private Set<String> inputFiles;
    private String outputFile;
    private HashMap<String,FileData> fileDataHashMap = new HashMap<>();
    private List<FileReader> frs = new ArrayList<>();
    private HashMap<String,BufferedReader> readers = new HashMap<String,BufferedReader>();
    public FileSort(ArgsData argsData){ //не нашёл варианта какая практика лучше тут, сразу передать весь объект или значения из него(возможно стоило использовать синглтон)
        this.outputFile = argsData.getOutputFile();
        this.inputFiles = argsData.getInputFiles();
        this.dataType = argsData.getDataType();
        this.sortType = argsData.getSortType();

    }
    public void merge() throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        boolean allReadersEmpty = false;
        try{
            this.fillInFileDataHashMapWithValues();
            while (!allReadersEmpty) {
                if(IsAllNullValuesInFileDataHashMap()) {
                    allReadersEmpty = true;
                    continue;
                }
                String minOrMaxFile = findMinOrMaxInHashmap(dataType, sortType);
                writer.write(fileDataHashMap.get(minOrMaxFile).getCurrentData().toString()+System.getProperty("line.separator"));
                FileData fileData = fileDataHashMap.get(minOrMaxFile);
                BufferedReader reader = readers.get(minOrMaxFile);
                String nextLine = reader.readLine();
                if(nextLine == null){
                    fileDataHashMap.replace(minOrMaxFile, new FileData(null));
                    continue;
                }
                boolean successfullyInserted = false;
                while (!successfullyInserted){
                    if(fileData.changeData(nextLine,sortType,dataType)==1){
                        successfullyInserted = true;
                    }else{
                        LOGGER.log(Level.WARNING, "in "+minOrMaxFile+" file line skipped, file not sorted");
                        nextLine = reader.readLine();
                        if(nextLine == null) {
                            fileDataHashMap.replace(minOrMaxFile, new FileData(null));
                            break;
                        }

                    }

                }
                if(!successfullyInserted) continue;
                fileDataHashMap.replace(minOrMaxFile,fileData);
                readers.replace(minOrMaxFile,reader);
            }
            LOGGER.log(Level.INFO, "Program successfully finished");

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "No such file found");
        } catch (NumberFormatException e){
            LOGGER.log(Level.SEVERE, "Can not convert NON"+dataType+" data to "+dataType+", stopped work and writing last value");
        }catch (IOException e){
            LOGGER.log(Level.SEVERE, "Severe IOException in readers/writers");
        }
        finally { //close all readers
            writer.close();
            for (FileReader fr:frs) {
                fr.close();
            }
            for(String inputFile:inputFiles){
                readers.get(inputFile).close();
            }
        }
    }
    private void fillInFileDataHashMapWithValues() throws IOException {
        String filename = null;
        Iterator<String> iterator = inputFiles.iterator();
        while (iterator.hasNext()) { //создаем файлридеры и баффередридеры на каждый файл(закрываем их в finally)
            filename = iterator.next().toString();
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            frs.add(fr);
            readers.put(filename, reader);
        }
        for (String inputFileName:inputFiles) { //первые значения в мапу значений из файлов
            switch (dataType){
                case STRING:
                    BufferedReader reader = readers.get(inputFileName);
                    FileData<String> fileDataString = new FileData<String>(reader.readLine());
                    readers.replace(inputFileName,reader);
                    fileDataHashMap.put(inputFileName,fileDataString);
                    break;
                case INTEGER:
                    BufferedReader reader1 = readers.get(inputFileName);
                    FileData<Integer> fileDataInt = new FileData<Integer>(Integer.parseInt(readers.get(inputFileName).readLine()));
                    readers.replace(inputFileName,reader1);
                    fileDataHashMap.put(inputFileName,fileDataInt);
                    break;
            }
        }
    }

    private boolean IsAllNullValuesInFileDataHashMap(){ //проверка(для окончания цикла) когда все value будут null
        boolean notNull = false;
        for(String inputFile:inputFiles){
            if(fileDataHashMap.get(inputFile).getCurrentData() != null) notNull = true;
        }
        return !notNull;
    }

    private String findMinOrMaxInHashmap(DataType dataType, SortType sortType) throws IOException { //поиск минимального/максимального по хешмапе
        String minMaxFileDataString = "";
        boolean firstNonEmptyFileChecked = false;
        Object obj = new Object();
        for (String inputFileString:inputFiles) {
            if(!firstNonEmptyFileChecked){
                obj = fileDataHashMap.get(inputFileString).getCurrentData();
                minMaxFileDataString = inputFileString;
                firstNonEmptyFileChecked = true;
                continue;
            }
            FileData fileData = fileDataHashMap.get(inputFileString);
            if(fileData.compareTo(obj,sortType,dataType)>0){
                minMaxFileDataString = inputFileString;
                obj = fileData.getCurrentData();
            }
        }
        return minMaxFileDataString;
    }
}
