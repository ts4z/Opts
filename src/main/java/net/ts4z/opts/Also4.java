package net.ts4z.opts;

/**
 * @author tshowalt
 */
public class Also4<T,U,V,W> {
  final Opt<T> _t;
  final Opt<U> _u;
  final Opt<V> _v;
  final Opt<W> _w;

  Also4(Also3<T,U,V> also3, Opt<W> w) {
    _t = also3._t;
    _u = also3._u;
    _v = also3._v;
    _w = w;
  }

  public <R> Opt<R> map(Function4<T,U,V,W,R> fn) {
    return _t.flatMap(t -> _u.flatMap(u -> _v.flatMap(v -> _w.map(w -> fn.apply(t,u,v,w)))));
  }
}
