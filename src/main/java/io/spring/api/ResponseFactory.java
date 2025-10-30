package io.spring.api;

import java.util.Collections;
import java.util.Map;

/**
 * Factory class for creating standardized API response structures.
 * Centralizes response creation to follow DRY principle.
 */
public final class ResponseFactory {

  private ResponseFactory() {
    // Utility class - prevent instantiation
  }

  public static Map<String, Object> singletonMap(String key, Object value) {
    return Collections.singletonMap(key, value);
  }

  public static Map<String, Object> userResponse(Object userData) {
    return singletonMap("user", userData);
  }

  public static Map<String, Object> articleResponse(Object articleData) {
    return singletonMap("article", articleData);
  }

  public static Map<String, Object> profileResponse(Object profileData) {
    return singletonMap("profile", profileData);
  }

  public static Map<String, Object> commentResponse(Object commentData) {
    return singletonMap("comment", commentData);
  }

  public static Map<String, Object> commentsResponse(Object commentsList) {
    return singletonMap("comments", commentsList);
  }
}
