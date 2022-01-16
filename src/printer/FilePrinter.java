package printer;

import server.todo.Check;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePrinter implements Printer{

    private static String printerPath = "printer/print.txt";
    private static FilePrinter filePrinter;

    private FilePrinter(){}

    public static FilePrinter getInstance(){
        if(filePrinter == null){
            filePrinter = new FilePrinter();
        }
        return filePrinter;
    }

    @Override
    public void print(Check check) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(check.toString());
        File file = new File(printerPath);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
