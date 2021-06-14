package completable_future_advance;

import util.SmallTool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author huskyui
 */
public class _03_exceptionally {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf = CompletableFuture.supplyAsync(()->{
            SmallTool.sleepMillis(100);
            SmallTool.printTimeAndThread("first");
            if(true) {
                throw new RuntimeException("a first");
            }
            return "A";
        }).exceptionally(throwable -> {
            return "A first";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            SmallTool.sleepMillis(1000);
            SmallTool.printTimeAndThread("second");
            return "B";
        }),first-> {
            if (first.equals("A")){
                throw new RuntimeException("run time exception");
            }
            return first;
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            return "C";
        });

        System.out.println(cf.get());
    }
}
