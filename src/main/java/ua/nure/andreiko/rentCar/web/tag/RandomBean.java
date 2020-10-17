package ua.nure.andreiko.rentCar.web.tag;

import java.util.Random;

/**
 * Random bean
 *
 * @author E.Andreiko
 */
public class RandomBean {

    private static final Random RANDOM = new Random();

    public int nextInt() {
        return RANDOM.nextInt();
    }
}
