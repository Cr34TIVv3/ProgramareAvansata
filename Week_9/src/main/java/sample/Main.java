package sample;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import sample.entities.Movie;
import sample.repository.MovieRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        MyEntityManagerFactory myEntityManagerFactory = MyEntityManagerFactory.getInstance();
        MovieRepository repository = new MovieRepository(myEntityManagerFactory.getFactory());


        repository.create(new Movie("dear john7", "01-JAN-90",90,9));
        repository.create(new Movie("dear john1", "01-JAN-90",90,10));
        repository.create(new Movie("dear john3", "01-JAN-90",90,5));
        repository.create(new Movie("dear john2", "01-JAN-90",90,8));
        repository.create(new Movie("dear john4", "01-JAN-90",90,6));
        repository.create(new Movie("dear john10", "01-JAN-90",90,7));



        Movie movie = repository.findObjectById(3);
        System.out.println(movie.toString());

        Movie movie1 = repository.findObjectByName("dear john1");
        System.out.println(movie1.toString());


        makeReport(repository);
        myEntityManagerFactory.closeFactory();
    }

    public static void makeReport(MovieRepository repository) throws Exception {


        Configuration configuration = new Configuration();

        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\rafae\\Desktop\\FII_2_SEM2\\PA\\Week_9\\src\\main\\java\\sample\\templates"));

        configuration.setIncompatibleImprovements(new Version(2, 3, 31));
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);

        Template template = configuration.getTemplate("movies.ftl");

        Writer writer = new FileWriter(new File("output.html"));

        Map<String, Object> input = new HashMap<>();
        List<Movie> list = repository.getAllObjects();

        List<Model> modelList = new ArrayList<>();
        for(var movie : list) {
            Model record = new Model();
            record.title = movie.getTitle();
            record.score = movie.getScore();
            modelList.add(record);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.now();

        input.put("date", formatter.format(localTime));
        input.put("models", list);


        template.process(input, writer);
        writer.close();
    }
}
