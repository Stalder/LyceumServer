import java.io.IOException;

/**
 * Created by Sergey Lavinov on 01.08.2015.
 */
public class LyceumServer {

    //укажите текущий год
    public static final int CURRENT_YEAR = 2016;

    public static void main(String[] args){
        GUI gui = new GUI("Лицеист года " + CURRENT_YEAR); //создаем графический интерфейс
    }

}
