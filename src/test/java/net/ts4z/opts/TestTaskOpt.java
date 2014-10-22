package net.ts4z.opts;

import com.linkedin.parseq.Engine;
import com.linkedin.parseq.EngineBuilder;
import com.linkedin.parseq.Task;
import com.linkedin.parseq.Tasks;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tshowalt
 */
public class TestTaskOpt {
  private static final Engine ENGINE = makeEngine();
  private static Engine makeEngine() {
    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    return new EngineBuilder().setTimerScheduler(scheduler).setTaskExecutor(scheduler)
        .build();
  }

  @Test
  public static void taskOpt() throws Exception {
    Task<Integer> five = Tasks.callable("five", val(5));
    Task<Integer> six = Tasks.callable("six", val(6));
    Task<?> t = Tasks.par(five, six);
    exec(t);
    Opt<Integer> p = Opt.wrap(five).also(Opt.wrap(six)).map((x,y) -> x + y);
    Assert.assertEquals(p.orElse(-1).intValue(), 11);
  }

  @Test
  public static void taskBombs() throws Exception {
    Task<Integer> five = Tasks.callable("five", val(5));
    Task<Integer> six = Tasks.callable("six", (Callable<Integer>) () -> {
      throw new RuntimeException("boom");
    });
    Task<?> t = Tasks.par(five, six);
    exec(t);
    Opt<Integer> p = Opt.wrap(five).also(Opt.wrap(six)).map((x,y) -> x + y);
    Assert.assertEquals(p.orElse(-1).intValue(), -1);
  }

  private static void exec(Task<?> t) throws InterruptedException {
    ENGINE.run(t);
    t.await(120, TimeUnit.SECONDS);
  }

  private static <T> Callable<T> callable(Callable<T> c) {
    return c;
  }

  private static <T> Callable<T> val(T t) {
    return () -> t;
  }
}
