package io.spring.api.security;

import java.util.Optional;

public class AuthorizationHeaderParser {
  private static final String BEARER_PREFIX = "Bearer ";
  private static final int TOKEN_INDEX = 1;

  public static Optional<String> extractToken(String authorizationHeader) {
    if (authorizationHeader == null) {
      return Optional.empty();
    }

    String[] parts = authorizationHeader.split(" ");
    if (parts.length < 2) {
      return Optional.empty();
    }

    return Optional.ofNullable(parts[TOKEN_INDEX]);
  }

  public static String extractTokenOrThrow(String authorizationHeader) {
    return extractToken(authorizationHeader)
        .orElseThrow(() -> new IllegalArgumentException("Invalid authorization header"));
  }
}
