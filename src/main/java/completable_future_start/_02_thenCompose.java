package completable_future_start;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static util.SmallTool.printTimeAndThread;
import static util.SmallTool.sleepMillis;

/**
 * @author huskyui
 */
public class _02_thenCompose {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            sleepMillis(2000);
            printTimeAndThread("cooking");
            return "tomato ";
        }).thenCompose(dish -> CompletableFuture.supplyAsync(() -> {
            sleepMillis(1000);
            printTimeAndThread("dafan ing");
            return dish + "-----" + "rice";

        }));
        printTimeAndThread("king of glory");
        printTimeAndThread(cf1.get());
    }
}
