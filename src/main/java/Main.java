
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) {
        Logger WARN_LOGGER = Logger.getLogger(Main.class.getName());
        try {
            ArgsGetter argsGetter = new ArgsGetter(args);
            FileSort fileSort = new FileSort(argsGetter.getDataFromArgs());
            fileSort.merge();
        }catch (IllegalArgumentException e){
            WARN_LOGGER.log(Level.WARNING, e.getMessage()+", program force-stopped");
        }
        catch (IOException e) {
            WARN_LOGGER.log(Level.SEVERE, "Something gone completely wrong, got IOException");
        }
        catch (IndexOutOfBoundsException e){
            WARN_LOGGER.log(Level.WARNING, "Недостаточно параметров");
        }




    }
}
