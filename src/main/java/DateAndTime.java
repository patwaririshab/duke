import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateAndTime {
    protected Map<Integer,String> month;
    protected int numerical_month;
    protected int date;
    protected int year;
    protected int numerical_time;
    protected String hour;
    protected String minutes;
    String ampm = "am";

    private void mapMonths(Map month){
        month.put(1,"January");
        month.put(2,"February");
        month.put(3,"March");
        month.put(4,"April");
        month.put(5,"May");
        month.put(6,"June");
        month.put(7, "July");
        month.put(8,"August");
        month.put(9,"September");
        month.put(10,"October");
        month.put(11,"November");
        month.put(12,"December");
    }

    public DateAndTime (String unparsedDT) {
        String[] date_time = unparsedDT.split(" ",0);
        String upDate = date_time[0];
        numerical_time = Integer.parseInt(date_time[1]);

        String[] parsedDate = upDate.split("/", 0);
        date = Integer.parseInt(parsedDate[0]);
        numerical_month = Integer.parseInt(parsedDate[1]);
        year = Integer.parseInt(parsedDate[2]);
    }

    private String getEnding(int Date) {

        if (Date == 11 || Date == 12 || Date == 13) return "th";
        switch(Date%10){
            case(1):
                return "st";
            case(2):
                return "nd";
            case(3):
                return "rd";
            default:
                return "th";
        }
    }

    public String getDate() {
        month = new HashMap();
        mapMonths(month);
        return ( date + getEnding(date) + " of " + month.get(numerical_month) + " " + year);
    }

    public String getTime() {
        String input = Integer.toString(numerical_time);
        DateFormat oldFormat = new SimpleDateFormat("HHmm");

        DateFormat outputFormat = new SimpleDateFormat("hh:mm aa");
        String time12hr = null;
        Date time24hr = null;
        try{
            time24hr = oldFormat.parse(input);
            time12hr = outputFormat.format(time24hr);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        return time12hr;

    }
}
