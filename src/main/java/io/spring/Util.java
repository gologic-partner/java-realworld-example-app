package io.spring;

/**
 * String utility methods for common operations.
 */
public final class Util {

  private Util() {
    // Utility class - prevent instantiation
  }

  /**
   * Checks if a string is null or empty.
   *
   * @param value the string to check
   * @return true if the string is null or empty, false otherwise
   */
  public static boolean isEmpty(String value) {
    return value == null || value.isEmpty();
  }
}
