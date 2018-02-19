import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NextDateAutoTest {
    static NextDate nextDate ;
    static BufferedReader reader ;
    static PrintWriter logWriter;
    static SimpleDateFormat dateHeaderFormat;
    static SimpleDateFormat dateInputFormat;
    static DateTimeFormatter dateTimeFormatter;
    static Date date;
    static Calendar c;



    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        logWriter = new PrintWriter(new FileWriter("testLog.txt", true));
        dateHeaderFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateInputFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        c = Calendar.getInstance();
        date = new Date();
        logWriter.println("Test started at : "+dateHeaderFormat.format(date));
        logWriter.println("RESULT" + "\t\t\t" + "INPUT" + "\t\t" + "EXPECTED" + "\t\t\t\t" +  "OUTPUT" );

        nextDate = new NextDate();
        reader = new BufferedReader(new FileReader("input.txt"));
        String line = null;

        while ((line = reader.readLine()) != null) {

//            System.out.println(line);
//            c.setTime(dateInputFormat.parse(line));
//            c.add(Calendar.DATE, 1);

            LocalDate myDate = LocalDate.parse(line,dateTimeFormatter);//dateInputFormat.parse(line).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate myAddedDate = myDate.plusDays(1);
            System.out.println(myDate);
            String expected = myAddedDate.format(dateTimeFormatter);
//            String expected = dateInputFormat.format(c.getTime());
            String output = nextDate.nextDate(line);

            logWriter.print(expected.equals(output)); // true false
            logWriter.println("\t\t"+ line + "\t\t" + expected + "\t\t\t" + output);
        }
        logWriter.println("Test done at: "+dateHeaderFormat.format(date));
        logWriter.println();
        logWriter.close();




    }

}
