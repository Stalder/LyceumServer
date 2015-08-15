import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Sergey Lavinov on 05.08.2015.
 */
public class EventHandler implements ActionListener{

    private GUI gui;
    private Thread thread;
    private ExcelManager excelManager;
    private DataBaseManager dataBaseManager;

    public EventHandler(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if(event.getSource() == gui.getButtonUpdateDataBase()){
            dataBaseManager = new DataBaseManager();

            try {
                dataBaseManager.deleteTable();
                dataBaseManager.updateDataBase(gui);
            }catch (SQLException ex){gui.getVotingLog().append("При обновлении базы данных произошла ошибка \n");
            }catch (ClassNotFoundException ex){gui.getVotingLog().append("При обновлении базы данных произошла ошибка эээх\n");}
        }

        if(event.getSource() == gui.getButtonFindFile()){
            gui.chooseFile();
        }

        if(event.getSource() == gui.getButtonStart()){
            //создаем поток
        }

        if(event.getSource() == gui.getButtonStop()){
            //останавливаем поток
        }

    }
}
