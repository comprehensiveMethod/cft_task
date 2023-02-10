public class FileData<E> { //можно было сделать костыль конкретно на String и Int, но хотелось бы чтобы оно было хоть немного расширяемым
    private E currentData;

    public FileData(E data){
        currentData = data;
    }
    public int changeData(E newData,SortType sortType,DataType dataType){ //при смене даты проверяется сортировка файла
        if(this.compareTo(newData,sortType,dataType)<0){
            return -1;
        }
        else{
            this.currentData = newData;
            return 1;
        }

    }
    public int compareTo(E data, SortType sortType, DataType dataType){ //сравнение внутреннего значения с переданным
        if(data == null) return 1;
        if(this.currentData == null) return -1;
        switch (dataType){
            case STRING:
                switch (sortType){
                    case DESCENDING:
                        if(data.toString().compareTo(currentData.toString())>0){
                            return -1;
                        }else if(data.toString().compareTo(currentData.toString())==0){
                            return 0;
                        }
                        else return 1;

                    case ASCENDING:
                        if(data.toString().compareTo(currentData.toString())<0){
                            return -1;
                        }else if(data.toString().compareTo(currentData.toString())==0){
                            return 0;
                        }
                        else return 1;

                }
                break;
            case INTEGER:
                switch (sortType){
                    case DESCENDING:
                        if(Integer.parseInt(data.toString())>Integer.parseInt(currentData.toString())){
                            return -1;
                        }else if(Integer.parseInt(data.toString())==Integer.parseInt(currentData.toString())){
                            return 0;
                        }
                        else return 1;

                    case ASCENDING:
                        if(Integer.parseInt(data.toString())<Integer.parseInt(currentData.toString())){
                            return -1;
                        }else if(Integer.parseInt(data.toString())==Integer.parseInt(currentData.toString())){
                            return 0;
                        }
                        else return 1;

                }
        }
        return 0;
    }
    public E getCurrentData(){
        return this.currentData;
    }

}
