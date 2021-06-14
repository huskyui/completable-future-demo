package completable_future_advance;

import util.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author huskyui
 */
public class _02_applyToEither {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            SmallTool.sleepMillis(1000);
            SmallTool.printTimeAndThread("first");
            return "A";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            SmallTool.sleepMillis(1000);
            SmallTool.printTimeAndThread("second");
            return "B";
        }),first->first);
        System.out.println(cf.get());
    }
}
