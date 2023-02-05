public class Config {
    final private byte dataType; //0 - int, 1 - string
    final private byte sortType; //0 - ascending, 1 - descending
    public Config(byte dataType, byte sortType){
        this.dataType = dataType;
        this.sortType = sortType;
    }
}
