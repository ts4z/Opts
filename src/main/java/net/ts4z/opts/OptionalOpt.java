package net.ts4z.opts;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author tshowalt
 */
public class OptionalOpt<T> implements Opt<T> {
  Optional<T> _optional;

  OptionalOpt(Optional<T> optional) {
    _optional = optional;
  }

  public <U> Opt<U> map(Function<? super T, ? extends U> fn) {
    return Opt.wrap(_optional.map(fn));
  }
  public <R> Opt<R> flatMap(Function<T,Opt<R>> fn) {
    if (_optional.isPresent()) {
      return fn.apply(_optional.get());
    } else {
      return Opt.empty();
    }
  }
  public T orElse(T t) { return _optional.orElse(t); }
  public boolean isPresent() { return _optional.isPresent(); }
  public T get() { return _optional.get(); }
}
