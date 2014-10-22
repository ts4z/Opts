package net.ts4z.opts;

import java.util.function.Function;

/**
 * @author tshowalt
 */
public class Empty<T> implements Opt<T> {

  static final Opt<?> INSTANCE = new Empty();

  public <R> Opt<R> map(Function<? super T, ? extends R> fn) {
    return Opt.empty();
  }
  public <R> Opt<R> flatMap(Function<T,Opt<R>> fn) {
    return Opt.empty();
  }
  public T orElse(T t) { return t; }
  public boolean isPresent() { return false; }
  public T get() {
    throw new UnsupportedOperationException("can't get() on Opt.Empty");
  }
}
