package net.ts4z.opts;

/**
 * @author tshowalt
 */
public class Also3<T,U,V> {
  final Opt<T> _t;
  final Opt<U> _u;
  final Opt<V> _v;

  Also3(Also2<T,U> also2, Opt<V> v) {
    _t = also2._t;
    _u = also2._u;
    _v = v;
  }

  public <W> Also4<T,U,V,W> also(Opt<W> w) {
    return new Also4<>(this, w);
  }

  public <R> Opt<R> map(Function3<T,U,V,R> fn) {
    return _t.flatMap(t -> _u.flatMap(u -> _v.map(v -> fn.apply(t, u, v))));
  }
}
