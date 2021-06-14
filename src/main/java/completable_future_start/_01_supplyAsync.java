package completable_future_start;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static util.SmallTool.printTimeAndThread;
import static util.SmallTool.sleepMillis;

/**
 * @author huskyui
 */

@Slf4j
public class _01_supplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            sleepMillis(2000);
            printTimeAndThread("cooking");
            return "tomato ";
        });
        printTimeAndThread("king of glory");
        printTimeAndThread(cf1.get());
    }
}
