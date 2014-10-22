package net.ts4z.opts;

import com.linkedin.parseq.Task;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * <p> Optional is cool, but can't use it on ParSeq {@link Task}.
 * So here's a version that's an interface that can wrap
 * anything.  This implementation knows how to wrap {@link Optional}
 * and {@link Task}.
 * </p>
 *
 * <p> {@link Task} is not treated specially.  It's up to the caller
 * to enforce that the task is running before doing optional operations
 * on it. </p>
 *
 * <p> Similarly, {@link Future} objects are not treated specially.
 * Attempts to access a {@link Future}-based {@link Opt} will block;
 * attempts to block a compounded object (via {@link #also} will block
 * in some ordering. </p>
 *
 * <p> All methods here are intended to be identical
 * to {@link Optional}. </p>
 *
 * @author tshowalt
 */
public interface Opt<T> {

  @SuppressWarnings("unchecked")
  static <T> Opt<T> empty() {
    return (Opt<T>) Empty.INSTANCE;
  }

  <R> Opt<R> map(Function<? super T, ? extends R> fn);
  default <R> Opt<R> flatMap(Function<T,Opt<R>> fn) {
    try {
      return fn.apply(get());
    } catch (Exception ex) {
      return empty();
    }
  }
  /** Like {@link Optional#isPresent} */
  boolean isPresent();
  /** Like {@link Optional#orElse} */
  T orElse(T r);
  /** Like {@link Optional#get}. Don't call this. */
  T get() throws Exception;

  /** Wrap in an {@link Opt}. */
  static <T> Opt<T> wrap(Task<T> task) {
    return new TaskOpt<>(task);
  }
  /** Wrap in an {@link Opt}. */
  static <T> Opt<T> wrap(Optional<T> optional) {
    return new OptionalOpt<>(optional);
  }
  /** Wrap in an {@link Opt}. */
  static <T> Opt<T> wrap(Future<T> future) { return new FutureOpt<>(future); }

  /** Wrap an object in an Opt.  (This object exists.) */
  static <T> Opt<T> of(T t) {
    return new Some<>(t);
  }

  /** Produce a compound sort of option. */
  default <U> Also2<T,U> also(Opt<U> u) {
    return new Also2<>(this, u);
  }
}

