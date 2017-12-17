package sh.okx.sql;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class TestFuture {
    @Test
    public void test() {
        CompletableFuture<Boolean> cp = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        });

        cp.thenAccept(System.out::println);

        while(true) {

        }
    }
}
