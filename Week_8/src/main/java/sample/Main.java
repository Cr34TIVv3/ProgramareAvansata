package sample;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import sample.dao.DAO;
import sample.dao.Movie;
import sample.dao.MovieDAO;
import sample.dao.DataBaseConnection;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static String convertDateToSqlDate(String date) {
        // XXXX-XX-XX
        if(!date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            return "01-JAN-90";
        }

        StringTokenizer stringTokenizer = new StringTokenizer(date, "-");
        List<String> list = new ArrayList<>();
        while(stringTokenizer.hasMoreTokens()) {
            String string = stringTokenizer.nextToken();
            list.add(string.substring(string.length() - 2));
        }

        String year = list.get(0);
        String month = list.get(1);
        String day = list.get(2);

        String output = day + "-" + fromNumberToMonth(month) + "-" + year;
        System.out.println(output);
        return output;
    }

    public static String fromNumberToMonth(String month) {
        switch (month) {
            case "01":
                return "JAN";
            case "02":
                return "FEB";
            case "03":
                return "MAR";
            case "04":
                return "APR";
            case "05":
                return "MAY";
            case "06":
                return "JUN";
            case "07":
                return "JUL";
            case "08":
                return "AUG";
            case "09":
                return "SEP";
            case "10":
                return "OCT";
            case "11":
                return "NOV";
            case "12":
                return "DEC";
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            DataBaseConnection connection = DataBaseConnection.getInstance();
            MovieDAO implementation = new MovieDAO(connection.getConnection());

            makeReport(implementation);

//            String csvPath = "C:\\Users\\rafae\\Desktop\\test\\src\\main\\java\\sample\\IMDb movies.csv";
//            try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
//                String[] csvLine;
//                int id = (int)Math.random()*1000 % 300;
//                csvLine = reader.readNext();
//                for(int i=0;i<2000;i++) {
//                    csvLine = reader.readNext();
//                    String title = csvLine[1];
//                    String date = csvLine[4];
//                    String duration = csvLine[6];
//                    String votes = csvLine[14];
//                    Movie newRecord = new Movie(id,title, convertDateToSqlDate(date), parseInt(duration), parseFloat(votes));
//                    implementation.add(newRecord);
//                    id++;
//                }
//            }
            connection.closeConnection();

        }
        catch (Exception exception) {
            System.out.println("Exception :" + exception.getMessage());
        }

    }


    private static void makeReport(DAO<Movie> implementation) throws  Exception{
        Configuration configuration = new Configuration();

        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\rafae\\Desktop\\FII_2_SEM2\\PA\\Week_8\\src\\main\\java\\sample\\resources"));

        configuration.setIncompatibleImprovements(new Version(2, 3, 31));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);

        Template template = configuration.getTemplate("movies.ftl");

        Writer writer = new FileWriter(new File("output.html"));

        Map<String, Object> input = new HashMap<>();
        List<Movie> list = implementation.getAll();
        input.put("movies", list);


        template.process(input, writer);
        writer.close();
    }
}
