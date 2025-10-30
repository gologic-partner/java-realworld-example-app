package io.spring.api.security;

import java.util.Optional;

/**
 * Utility class for extracting JWT tokens from Authorization headers.
 * Centralizes token parsing logic to follow DRY principle.
 */
public final class TokenExtractor {

  private static final String BEARER_PREFIX = "Bearer ";
  private static final int TOKEN_INDEX = 1;
  private static final int MIN_PARTS = 2;

  private TokenExtractor() {
    // Utility class - prevent instantiation
  }

  /**
   * Extracts the token from an Authorization header string.
   * Expected format: "Bearer {token}"
   *
   * @param authorizationHeader the full Authorization header value
   * @return Optional containing the token if present and valid, empty otherwise
   */
  public static Optional<String> extractToken(String authorizationHeader) {
    if (authorizationHeader == null) {
      return Optional.empty();
    }

    String[] parts = authorizationHeader.split(" ");
    if (parts.length < MIN_PARTS) {
      return Optional.empty();
    }

    return Optional.ofNullable(parts[TOKEN_INDEX]);
  }
}
