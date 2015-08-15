import javafx.application.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Sergey Lavinov on 02.08.2015.
 */
public class GUI extends JFrame {

    private ExcelManager excelManager;
    private JList list = new JList();
    private JLabel textExcelPath = new JLabel("Путь к Excel файлу:");
    private JTextField textFieldExcelPath = new JTextField();
    private JButton buttonFindFile = new JButton("Обзор");
    private JButton buttonUpdateDataBase = new JButton("Обновить");
    private JButton buttonStart = new JButton("Начать");
    private JButton buttonStop = new JButton("Остановить");
    private JTextArea votingLog = new JTextArea("Лог голосования: \n");
    private JFileChooser fileChooser;

    public GUI(String windowName){

        super(windowName);                              //устанавливаем заголовок окна
        setVisible(true);                               //делаем его видимым
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //делаем, чтобы по нажатию на крестик
                                                        //программа закрывалась
        setSize(640, 480);                              //устанавливаем размер экрана
        setResizable(false);                            //запрещаем менять его размер
        setLayout(null);
        setLocationRelativeTo(null);                    //устанавливаем его в центр экрана

        EventHandler handler = new EventHandler(this);

        this.setIconImage(new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        });

        list.setBounds(10,10,200,460);
        textExcelPath.setBounds(220,20,300,20);
        textFieldExcelPath.setBounds(220, 40, 410, 30);
        buttonFindFile.setBounds(440,75,90,25);
        buttonUpdateDataBase.setBounds(540,75,90,25);
        buttonStart.setBounds(270,110,150,60);
        buttonStop.setBounds(430,110,150,60);
        votingLog.setBounds(230,190,380,250);

        this.add(list);
        this.add(textExcelPath);
        this.add(textFieldExcelPath);
        this.add(buttonFindFile);
        this.add(buttonUpdateDataBase);
        this.add(buttonStart);
        this.add(buttonStop);
        this.add(votingLog);

        buttonFindFile.addActionListener(handler);
        buttonUpdateDataBase.addActionListener(handler);
        buttonStart.addActionListener(handler);
        buttonStop.addActionListener(handler);

        votingLog.setEditable(false);

    }

    public void chooseFile(){

        fileChooser = new JFileChooser();
        int requestResult = fileChooser.showDialog(null, "Выберите файл");
        if(requestResult == JFileChooser.APPROVE_OPTION){
            textFieldExcelPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }

    }

    public void fillList(String[] forms){
        list = new JList(forms);
        add(list);
    }

    public JButton getButtonFindFile() {
        return buttonFindFile;
    }

    public JButton getButtonUpdateDataBase() {
        return buttonUpdateDataBase;
    }

    public JButton getButtonStart() {
        return buttonStart;
    }

    public JButton getButtonStop() {
        return buttonStop;
    }

    public JTextArea getVotingLog() {
        return votingLog;
    }

    public JTextField getTextFieldExcelPath() {
        return textFieldExcelPath;
    }
}
