package net.ts4z.opts;

/**
 * @author tshowalt
 */
@FunctionalInterface
public interface Function3<T,U,V,R> {
  R apply(T t, U u, V v);
}
