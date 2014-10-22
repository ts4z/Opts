package net.ts4z.opts;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * @author tshowalt
 */
public class TestOptAlso {

  @Test
  public static void twoExistentArgs() {
    Opt<Integer> one = Opt.of(7);
    Opt<Integer> two = Opt.wrap(Optional.of(12));
    Assert.assertEquals(one.also(two).map((x, y) -> x + y).orElse(-1), Integer.valueOf(19));
  }

  @Test
  public static void threeExistentArgs() {
    Opt<Integer> one = Opt.of(7);
    Opt<Integer> two = Opt.wrap(Optional.of(12));
    Opt<Long> three = Opt.wrap(Optional.of(-9L));
    Assert.assertEquals(one.also(two).also(three).map((x, y, z) -> x + y + z).orElse(-1L), Long.valueOf(10));
  }

  @Test
  public static void noFirstArg() {
    Opt<Integer> one = Opt.empty();
    Opt<Integer> two = Opt.wrap(Optional.of(12));
    Opt<Long> three = Opt.wrap(Optional.of(-9L));
    Assert.assertFalse(one.also(two).also(three).map((x, y, z) -> x + y + z).isPresent());
  }

  @Test
  public static void noSecondArg() {
    Opt<Integer> one = Opt.wrap(Optional.of(12));
    Opt<Integer> two = Opt.empty();
    Opt<Long> three = Opt.wrap(Optional.of(-9L));
    Assert.assertFalse(one.also(two).also(three).map((x, y, z) -> x + y + z).isPresent());
  }

  @Test
  public static void noThirdArg() {
    Opt<Integer> one = Opt.wrap(Optional.of(12));
    Opt<Integer> two = Opt.wrap(Optional.of(12));
    Opt<Long> three = Opt.empty();
    Assert.assertFalse(one.also(two).also(three).map((x, y, z) -> x + y + z).isPresent());
  }

  @Test
  public static void noThirdArgButItsWrapped() {
    Opt<Integer> one = Opt.wrap(Optional.of(12));
    Opt<Integer> two = Opt.wrap(Optional.of(12));
    Opt<Long> three = Opt.wrap(Optional.<Long>empty());
    Assert.assertFalse(one.also(two).also(three).map((x, y, z) -> x + y + z).isPresent());
  }

  @Test
  public static void fourHappyArgs() {
    Opt<Integer> one = Opt.of(1);
    Opt<Integer> two = Opt.of(2);
    Opt<Integer> three = Opt.of(3);
    Opt<Integer> four = Opt.of(4);
    Assert.assertEquals(
        one.also(two).also(three).also(four).map((w,x,y,z) -> w + x + y + z).orElse(-1).intValue(),
        10);
  }
}
