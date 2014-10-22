package net.ts4z.opts;

import java.util.function.BiFunction;

/**
 * @author tshowalt
 */
public class Also2<T,U> {
  final Opt<T> _t;
  final Opt<U> _u;

  Also2(Opt<T> t, Opt<U> u) {
    _t = t;
    _u = u;
  }

  <V> Also3<T,U,V> also(Opt<V> v) {
    return new Also3<>(this, v);
  }

  public <R> Opt<R> map(BiFunction<T,U,R> fn) {
    return _t.flatMap(t -> _u.map(u -> fn.apply(t, u)));
  }
}
