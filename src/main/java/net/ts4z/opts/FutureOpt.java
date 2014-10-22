package net.ts4z.opts;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * @author tshowalt
 */
public class FutureOpt<T> implements Opt<T> {

  Future<T> _future;

  FutureOpt(Future<T> future) {
    _future = future;
  }

  @Override
  public <R> Opt<R> map(Function<? super T, ? extends R> fn) {
    return null;
  }

  @Override
  public boolean isPresent() {
    try {
      _future.get();
      return true;
    } catch (InterruptedException | ExecutionException e) {
      return false;
    }
  }

  @Override
  public T orElse(T r) {
    try {
      return _future.get();
    } catch (InterruptedException | ExecutionException e) {
      return r;
    }
  }

  @Override
  public T get() throws Exception {
    return _future.get();
  }
}
