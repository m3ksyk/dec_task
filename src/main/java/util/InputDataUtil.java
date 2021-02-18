package util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InputDataUtil {

    private Random random;

    public InputDataUtil() {
        this.random = new Random();
    }

    public Integer generateRandomNumber(int range) {
        return random.nextInt(range);
    }
}
