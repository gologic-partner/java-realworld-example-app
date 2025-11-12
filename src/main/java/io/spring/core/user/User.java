package io.spring.core.user;

import io.spring.Util;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {
  private String id;
  private String email;
  private String username;
  private String password;
  private String bio;
  private String image;

  public User(String email, String username, String password, String bio, String image) {
    this.id = UUID.randomUUID().toString();
    this.email = email;
    this.username = username;
    this.password = password;
    this.bio = bio;
    this.image = image;
  }

  public void update(String email, String username, String password, String bio, String image) {
    if (!Util.isNullOrEmpty(email)) {
      this.email = email;
    }

    if (!Util.isNullOrEmpty(username)) {
      this.username = username;
    }

    if (!Util.isNullOrEmpty(password)) {
      this.password = password;
    }

    if (!Util.isNullOrEmpty(bio)) {
      this.bio = bio;
    }

    if (!Util.isNullOrEmpty(image)) {
      this.image = image;
    }
  }
}
