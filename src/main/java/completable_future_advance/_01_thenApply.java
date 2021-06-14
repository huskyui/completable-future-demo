package completable_future_advance;

import util.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @author huskyui
 */
public class _01_thenApply {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(()->{
            SmallTool.sleepMillis(1000);
            SmallTool.printTimeAndThread("first");
            return "first";
        }).thenApply(res->{
            SmallTool.sleepMillis(500);
            SmallTool.printTimeAndThread("second");
            return res+"--"+"second";
        });
        cf1.join();
        System.out.println(cf1.get());
    }
}
