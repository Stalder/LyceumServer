import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Sergey Lavinov on 12.08.2015.
 */
public class DataBaseManager {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private  ExcelManager excelManager;

    public void connect() throws ClassNotFoundException, SQLException{

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:DataBase.s3db");

        System.out.println("База Подключена!");
    }

    public void createTable() throws ClassNotFoundException, SQLException{
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists 'pupils' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text," +
                " 'form' text, 'score' INT);");
        System.out.println("Таблица создана или уже существует.");
    }

    public void writeDataBase() throws SQLException {
        System.out.println("Таблица заполняется");

        for (Row row : excelManager.getWorkbook().getSheetAt(0)) {
            String name = row.getCell(1).getStringCellValue();
            String form = row.getCell(2).getStringCellValue();
            int score = (int)row.getCell(3).getNumericCellValue();
            statement.execute("INSERT INTO 'pupils' ('name','form', 'score') VALUES ('"+name+"', '"+form+"', "+score+"); ");
        }

        System.out.println("Таблица заполнена");
    }

    public void readDataBase() throws ClassNotFoundException, SQLException {

        resultSet = statement.executeQuery("SELECT * FROM pupils");

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String form = resultSet.getString("form");
            String score = resultSet.getString("score");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "form = " + form);
            System.out.println( "score = " + score );
            System.out.println();
        }

        System.out.println("Таблица выведена");

    }

    public void closeDataBase() throws SQLException{

        connection.close();
        statement.close();
        resultSet.close();

    }

    public void deleteTable() throws SQLException, ClassNotFoundException{
        connect();
        statement = connection.createStatement();
        statement.execute("DROP TABLE if exists 'pupils';");
        System.out.println("Всё от души, братан, удалено");
    }

    public void updateDataBase(GUI gui){

        excelManager = new ExcelManager();                       //через эту штуку работаем с Excel файлом

        try{
            excelManager.openFile(gui.getTextFieldExcelPath().getText());       //открываем файл для чтения
        } catch (IOException ex){gui.getVotingLog().append("IOException Excel файл не найден \n");}

        try{
            excelManager.closeFile();                            //закрываем его
        } catch (IOException ex){gui.getVotingLog().append("IOException ошибка во время закрытия Excel файла \n");}

        try{
            connect();
            createTable();
            writeDataBase();
            readDataBase();
            closeDataBase();
        }catch(ClassNotFoundException e){
            gui.getVotingLog().append("ClassNotFoundException работа с базой данных прервана \n");
        }catch (SQLException e){
            gui.getVotingLog().append("SQLException работа с базой данных прервана \n");
        }

        gui.getVotingLog().append("База учеников обновлена \n");

    }

}
