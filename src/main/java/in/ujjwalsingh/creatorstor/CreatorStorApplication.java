package in.ujjwalsingh.creatorstor;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreatorStorApplication {

    public static void main(String[] args) {
       // Configuration of Dotenv-java
        Dotenv dotenv= Dotenv.configure().ignoreIfMissing().load();
        //dotenv.entries().forEach((DotenvEntry entry)
          //      -> System.setProperty(entry.getKey(),entry.getValue()));
        for (DotenvEntry entry : dotenv.entries()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }
        SpringApplication.run(CreatorStorApplication.class, args);
    }

}
