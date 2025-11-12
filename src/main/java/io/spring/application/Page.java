package io.spring.application;

import lombok.Getter;

@Getter
public class Page {
  private static final int DEFAULT_OFFSET = 0;
  private static final int DEFAULT_LIMIT = 20;
  private static final int MAX_LIMIT = 100;
  private static final int MIN_OFFSET = 0;
  private static final int MIN_LIMIT = 1;

  private final int offset;
  private final int limit;

  public Page() {
    this.offset = DEFAULT_OFFSET;
    this.limit = DEFAULT_LIMIT;
  }

  public Page(int offset, int limit) {
    this.offset = validateOffset(offset);
    this.limit = validateLimit(limit);
  }

  private int validateOffset(int offset) {
    return Math.max(offset, MIN_OFFSET);
  }

  private int validateLimit(int limit) {
    if (limit < MIN_LIMIT) {
      return DEFAULT_LIMIT;
    }
    return Math.min(limit, MAX_LIMIT);
  }
}
