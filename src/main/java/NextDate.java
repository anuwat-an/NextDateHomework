import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NextDate {

    private static ArrayList<String> months = new ArrayList<String>(){{
        add("january"); // 31
        add("february"); // 28-29
        add("march"); // 31
        add("april"); // 30
        add("may"); // 31
        add("june"); // 30
        add("july"); // 31
        add("august"); // 31
        add("september"); // 30
        add("october"); // 31
        add("november"); // 30
        add("december"); // 31
    }};

   /*
     public static void main(String[] args) {

     }
  */

  /*
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int date = 0;
        while (date <= 0 || date > 31) {
            System.out.print("Enter today date: ");
            try {
                date = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                date = 0;
            }
            if (date <= 0 || date > 31)
                System.out.println("Date input is invalid.");
        }

        String month = "";
        while (!months.contains(month)) {
            System.out.print("Enter today month: ");
            String monthInput = br.readLine().toLowerCase();
            try {
                int m = Integer.parseInt(monthInput);
                month = months.get(m-1);
            } catch (NumberFormatException e) {
                month = monthInput;
            } catch (IndexOutOfBoundsException e) {
                month = monthInput;
            }
            if (!months.contains(month))
                System.out.println("Month input is invalid. Try full name month.");
        }

        int year = 0;
        while (year <= 0) {
            System.out.print("Enter today year: ");
            try {
                year = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                year = 0;
            }
            if (year <= 0)
                System.out.println("Year input is invalid.");
        }

        System.out.printf("Today is %02d/%s/%d.\n", date, month, year);

        System.out.println(nextDate(date, month, year));

    }
    */

    public static String nextDate(String dateStr) { //example 01/01/2017
        String[] array = dateStr.split("/");
        int date = Integer.parseInt(array[0]);
        String month = months.get(Integer.parseInt(array[1])-1);
        int year = Integer.parseInt(array[2]);

        return nextDate(date,month,year);
    }

    public static String nextDate(int date, String month, int year) {

        if (date <= 0 || date > 31 || !months.contains(month) || year <= 0)
            return "Data input is invalid.";

        boolean dateInvalid = false;
        if (months.indexOf(month) == 3 || months.indexOf(month) == 5 || months.indexOf(month) == 8 || months.indexOf(month) == 10) {
            if (date >= 1 && date <= 29) {
                date += 1;
            }
            else if (date == 30) {
                date = 1;
                month = months.get(months.indexOf(month)+1);
            }
            else {
                dateInvalid = true;
            }
        }
        else if (months.indexOf(month) == 0 || months.indexOf(month) == 2 || months.indexOf(month) == 4 ||
                months.indexOf(month) == 6 || months.indexOf(month) == 7 || months.indexOf(month) == 9) {
            if (date >= 1 && date <= 30) {
                date += 1;
            }
            else if (date == 31) {
                date = 1;
                month = months.get(months.indexOf(month)+1);
            }
        }
        else if (months.indexOf(month) == 11) {
            if (date >= 1 && date <= 30) {
                date += 1;
            }
            else if (date == 31) {
                date = 1;
                month = months.get(0);
                year += 1;
            }
        }
        else if (months.indexOf(month) == 1) {
            if ((date >= 1 && date <= 27) || (date == 28 && isLeapYear(year))) {
                date += 1;
            }
            else if ((date == 28 && !isLeapYear(year)) || (date == 29 && isLeapYear(year))) {
                date = 1;
                month = months.get(months.indexOf(month)+1);
            }
            else if ((date == 29 && !isLeapYear(year)) || date >= 30) {
                dateInvalid = true;
            }
        }

        if (dateInvalid)
            return "Date input is invalid.";
        return String.format("%02d/%02d/%d", date, months.indexOf(month)+1, year);

    }

    private static boolean isLeapYear(int year) {
        return (year%4 == 0 && year%100 != 0) || year%400 == 0;
    }

}
