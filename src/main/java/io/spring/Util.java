package io.spring;

public class Util {
  public static boolean isNullOrEmpty(String value) {
    return value == null || value.isEmpty();
  }

  /** @deprecated Use {@link #isNullOrEmpty(String)} instead */
  @Deprecated
  public static boolean isEmpty(String value) {
    return isNullOrEmpty(value);
  }
}
