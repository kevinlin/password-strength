package com.scb.nextgen.passwordstrength.constraint;

import com.scb.nextgen.passwordstrength.dto.PasswordPolicyResult;

public interface PasswordPolicyValidator {

  PasswordPolicyResult isValid(String password);

}
