package io.spring.application.user;

import io.spring.core.user.User;
import io.spring.core.user.UserRepository;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {
  private UserRepository userRepository;
  private String defaultImage;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(
      UserRepository userRepository,
      @Value("${image.default}") String defaultImage,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.defaultImage = defaultImage;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(@Valid RegisterParam registerParam) {
    User user =
        new User(
            registerParam.getEmail(),
            registerParam.getUsername(),
            passwordEncoder.encode(registerParam.getPassword()),
            "",
            defaultImage);
    userRepository.save(user);
    return user;
  }

  public void updateUser(@Valid UpdateUserCommand command) {
    User user = command.getTargetUser();
    UpdateUserParam updateUserParam = command.getParam();
    user.update(
        updateUserParam.getEmail(),
        updateUserParam.getUsername(),
        updateUserParam.getPassword(),
        updateUserParam.getBio(),
        updateUserParam.getImage());
    userRepository.save(user);
  }
}

@Constraint(validatedBy = UpdateUserValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@interface UpdateUserConstraint {

  String message() default "invalid update param";

  Class[] groups() default {};

  Class[] payload() default {};
}

class UpdateUserValidator implements ConstraintValidator<UpdateUserConstraint, UpdateUserCommand> {

  @Autowired private UserRepository userRepository;

  @Override
  public boolean isValid(UpdateUserCommand value, ConstraintValidatorContext context) {
    String inputEmail = value.getParam().getEmail();
    String inputUsername = value.getParam().getUsername();
    final User targetUser = value.getTargetUser();

    boolean isEmailValid = isFieldAvailableForUser(inputEmail, targetUser, userRepository::findByEmail);
    boolean isUsernameValid = isFieldAvailableForUser(inputUsername, targetUser, userRepository::findByUsername);

    if (isEmailValid && isUsernameValid) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    if (!isEmailValid) {
      addConstraintViolation(context, "email", "email already exist");
    }
    if (!isUsernameValid) {
      addConstraintViolation(context, "username", "username already exist");
    }
    return false;
  }

  private boolean isFieldAvailableForUser(String fieldValue, User targetUser, java.util.function.Function<String, java.util.Optional<User>> finder) {
    return finder.apply(fieldValue).map(user -> user.equals(targetUser)).orElse(true);
  }

  private void addConstraintViolation(ConstraintValidatorContext context, String property, String message) {
    context
        .buildConstraintViolationWithTemplate(message)
        .addPropertyNode(property)
        .addConstraintViolation();
  }
}
