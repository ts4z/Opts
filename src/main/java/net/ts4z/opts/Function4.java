package net.ts4z.opts;

/**
 * @author tshowalt
 */
@FunctionalInterface
public interface Function4<T,U,V,W,R> {
  R apply(T t, U u, V v, W w);
}
