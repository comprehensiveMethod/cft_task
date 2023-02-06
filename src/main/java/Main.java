public class Main {
    public static void main(String[] args) {
        String[] argum = {"-d", "-i", "out.txt", "wow.txt", "xd.txt"};
        ArgsGetter argsGetter = new ArgsGetter(argum);
        FileSort fileSort = new FileSort(argsGetter.getDataFromArgs());

    }
}
