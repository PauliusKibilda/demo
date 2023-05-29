package demo.useCases;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class AsyncClass implements Serializable {

    public Integer asyncMethod() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted exception caught in asyncMethod: " + e.getMessage());
        }
        return new Random().nextInt(100);
    }
}
