package com.scb.nextgen.passwordstrength.constraint;

import com.google.common.base.Joiner;
import com.scb.nextgen.passwordstrength.dto.PasswordPolicyResult;
import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.stereotype.Service;

@Service
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String>, PasswordPolicyValidator {

  @Override
  public void initialize(ValidPassword arg0) {
  }

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    //@formatter:off
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
            new LengthRule(8, 30),

            new CharacterRule(EnglishCharacterData.UpperCase, 1),

            new CharacterRule(EnglishCharacterData.LowerCase, 1),

            new CharacterRule(EnglishCharacterData.Digit, 1),

            new CharacterRule(EnglishCharacterData.Special, 1),

            new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),

            new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),

            new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),

            new WhitespaceRule()
        ));
        //@formatter:on

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return true;
    }
    context.disableDefaultConstraintViolation();
    List<String> messages = validator.getMessages(result);
    context.buildConstraintViolationWithTemplate(Joiner.on(",").join(messages)).addConstraintViolation();
    return false;
  }

  @Override
  public PasswordPolicyResult isValid(String password) {
    //@formatter:off
    PasswordValidator validator = new PasswordValidator(Arrays.asList(
        new LengthRule(8, 30),

        new CharacterRule(EnglishCharacterData.UpperCase, 1),

        new CharacterRule(EnglishCharacterData.LowerCase, 1),

        new CharacterRule(EnglishCharacterData.Digit, 1),

        new CharacterRule(EnglishCharacterData.Special, 1),

        // new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false),

        new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 3, false),

        new IllegalSequenceRule(EnglishSequenceData.USQwerty, 3, false),

        new WhitespaceRule()
    ));
    //@formatter:on

    RuleResult result = validator.validate(new PasswordData(password));
    if (result.isValid()) {
      return new PasswordPolicyResult();
    }

    return new PasswordPolicyResult(false, validator.getMessages(result));
  }
}
