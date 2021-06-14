package completable_future_start;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static util.SmallTool.printTimeAndThread;
import static util.SmallTool.sleepMillis;

/**
 * @author huskyui
 */
public class _03_thenCombine {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            sleepMillis(1000);
            printTimeAndThread("cooking");
            return "tomato ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            sleepMillis(1000);
            printTimeAndThread("take rice");
            return "rice";
        }), (dish, rice) -> {
            return dish + rice;
        });


        String s = cf1.get();
        System.out.println(s);
    }
}
