public class FileData<E> { //можно было сделать костыль конкретно на String и Int, но хотелось бы чтобы оно было хоть немного расширяемым
    private E currentData;

    public FileData(E data){
        currentData = data;
    }
    public void changeData(E newData,SortType sortType,DataType dataType){
        switch (dataType){
            case STRING:
                switch (sortType){
                    case DESCENDING:
                        if(newData.toString().compareTo(currentData.toString())>0){
                            throw new IllegalStateException("File is not sorted");
                        }
                        else currentData = newData;
                        break;
                    case ASCENDING:
                        if(newData.toString().compareTo(currentData.toString())<0){
                            throw new IllegalStateException("File is not sorted");
                        }
                        else currentData = newData;
                        break;
                }
                break;
            case INTEGER:
                switch (sortType){
                    case DESCENDING:
                        if(Integer.parseInt(newData.toString())>Integer.parseInt(currentData.toString())){
                            throw new IllegalStateException("File is not sorted");
                        }
                        else currentData = newData;
                        break;
                    case ASCENDING:
                        if(Integer.parseInt(newData.toString())<Integer.parseInt(currentData.toString())){
                            throw new IllegalStateException("File is not sorted");
                        }
                        else currentData = newData;
                        break;
                }
        }

    }
    public E getCurrentData(){
        return this.currentData;
    }
}
