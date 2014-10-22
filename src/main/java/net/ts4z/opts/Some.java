package net.ts4z.opts;

import java.util.function.Function;

/**
 * @author tshowalt
 */
class Some<T> implements Opt<T> {
  T _t;
  Some(T t) {
    _t = t;
  }
  public T get() {
    return _t;
  }
  public <R> Opt<R> map(Function<? super T, ? extends R> fn) {
    return Opt.of(fn.apply(_t));
  }
  public <R> Opt<R> flatMap(Function<T,Opt<R>> fn) {
    return fn.apply(_t);
  }
  public boolean isPresent() {
    return true;
  }
  public T orElse(T r) {
    return _t;
  }
}
