import org.junit.jupiter.api.*;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


class NextDateTest {
    ArrayList<String> input ;
    ArrayList<String> output ;
    ArrayList<String> expected ;
    static PrintWriter logWriter ;
    String tempInput;
    String tempExp;
    String tempOutput ;
    static DateFormat dateFormat ;
    static Date date;

    @BeforeAll
    static void setupOnce() throws IOException {
        logWriter = new PrintWriter(new FileWriter("testLog.txt", true));
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = new Date();
        logWriter.println("Test started at : "+dateFormat.format(date));
        logWriter.println("RESULT" + "\t\t\t" + "INPUT" + "\t\t\t\t" + "EXPECTED" + "\t\t\t\t\t\t\t" +  "OUTPUT" );
    }

    @BeforeEach
    void setupEach()  {
       input = new ArrayList<String>();
       output = new ArrayList<String>();
       expected = new ArrayList<String>();
    }

    @AfterEach
    void writeLog(){
        for (int i=0 ; i < input.size() ; i ++) {
            logWriter.print(expected.get(i).equals(output.get(i))); // true false
            logWriter.println("\t\t"+input.get(i) + "\t\t" + expected.get(i) + "\t\t\t" + output.get(i));
        }
    }

    @AfterAll
    static void closeWriter(){
        logWriter.println("Test done at: "+dateFormat.format(date));
        logWriter.println();
        logWriter.close();
    }

    @Test
    void testD1M1(){ //day<28 30day'month
        tempInput = "1/04/1000" ;
        tempExp = "Tomorrow is 02/april/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);

        tempInput = "27/04/1000" ;
        tempExp = "Tomorrow is 28/april/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD2M1(){ //day28 30day'month
        tempInput = "28/05/1000" ;
        tempExp = "Tomorrow is 29/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD3M1(){ //day29 30day'month
        tempInput = "29/04/1000" ;
        tempExp = "Tomorrow is 30/april/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD4M1(){ //day30 30day'month
        tempInput = "30/04/1000" ;
        tempExp = "Tomorrow is 01/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD5M1(){ //day31 30day'month
        tempInput = "31/04/1000" ;
        tempExp = "Date input is invalid." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testD1M2(){ //day<28 31day'month
        tempInput = "1/05/1000" ;
        tempExp = "Tomorrow is 02/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ;
        expected.add(tempExp);
        output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);

        tempInput = "27/05/1000" ;
        tempExp = "Tomorrow is 28/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD2M2(){ //day28 31day'month
        tempInput = "28/05/1000" ;
        tempExp = "Tomorrow is 29/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD3M2(){ //day29 31day'month
        tempInput = "29/05/1000" ;
        tempExp = "Tomorrow is 30/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD4M2(){ //day30 31day'month
        tempInput = "30/05/1000" ;
        tempExp = "Tomorrow is 31/may/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD5M2(){ //day31 31day'month
        tempInput = "31/05/1000" ;
        tempExp = "Tomorrow is 01/june/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testD1M3(){ //day<28 2'month
        tempInput = "1/02/1000" ;
        tempExp = "Tomorrow is 02/february/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);

        tempInput = "27/02/1000" ;
        tempExp = "Tomorrow is 28/february/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD2M3Y1(){ //day28 2'month leapYear
        tempInput = "28/02/1004" ;
        tempExp = "Tomorrow is 29/february/1004." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }
    @Test
    void testD2M3Y2(){ //day28 2'month commonYear
        tempInput = "28/02/1000" ;
        tempExp = "Tomorrow is 01/march/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD3M3Y1(){ //day29 2'month leapYear
        tempInput = "29/02/1004" ;
        tempExp = "Tomorrow is 01/march/1004." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD3M3Y2(){ //day29 2'month commonYear
        tempInput = "29/02/1000" ;
        tempExp = "Date input is invalid." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD4M3(){ //day30 2'month
        tempInput = "30/02/1000" ;
        tempExp = "Date input is invalid." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD5M3(){ //day31 2'month
        tempInput = "31/02/1000" ;
        tempExp = "Date input is invalid." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    void testD1M4(){ //day<28 12'month
        tempInput = "1/12/1000" ;
        tempExp = "Tomorrow is 02/december/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);

        tempInput = "27/12/1000" ;
        tempExp = "Tomorrow is 28/december/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD2M4(){ //day28 12'month
        tempInput = "28/12/1000" ;
        tempExp = "Tomorrow is 29/december/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD3M4(){ //day29 12'month
        tempInput = "29/12/1000" ;
        tempExp = "Tomorrow is 30/december/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD4M4(){ //day30 12'month
        tempInput = "30/12/1000" ;
        tempExp = "Tomorrow is 31/december/1000." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }

    @Test
    void testD5M4(){ //day31 12'month
        tempInput = "31/12/1000" ;
        tempExp = "Tomorrow is 01/january/1001." ;
        tempOutput = NextDate.nextDate(tempInput) ;
        input.add(tempInput) ; expected.add(tempExp);output.add(tempOutput);
        Assertions.assertEquals(tempExp,tempOutput);
    }


}