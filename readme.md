## CompletableFuture.supplyAsync
开启一个任务


线程池中运行一个方法，并返回结果
```java
CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
    sleepMillis(2000);
    printTimeAndThread("cooking");
    return "tomato ";
});
printTimeAndThread("king of glory");
printTimeAndThread(cf1.get());
```



## CompletableFuture.thenCompose
连接，第一个结果交给第二个使用，并返回结果


第一个的异步结果交给第二个来运行
```java
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
```


## CompletableFuture.thenCombine
合并两个任务，结果由合并函数（BiFunction）返回


可以实现两个并行的task
```java
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
```
## CompletableFuture.thenApply
一个任务结束后执行第二个任务。有点像thenCompose

thenApply和thenApplyAsync
区别是可以放入一个线程池参数，指定线程池执行
## applyToEither
两个task谁先执行完，使用谁的结果
```java
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
```
## exceptionally
处理异常
```java
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
```


