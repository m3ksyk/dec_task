import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
        (basePackages = {
                "rest",
                "util",
                "plugin",
                "factory"
        })
public class DecertoApp {

    public static void main(String[] args) {
        SpringApplication.run(DecertoApp.class, args);
    }
}
