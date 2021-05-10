package com.scb.nextgen.passwordstrength.dto;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyResult {

  private boolean isValid;
  private List<String> violations;

  public PasswordPolicyResult() {
    isValid = true;
    violations = new ArrayList<>();
  }

  public PasswordPolicyResult(boolean isValid, List<String> violations) {
    this.isValid = isValid;
    this.violations = violations;
  }

  public boolean isValid() {
    return isValid;
  }

  public void setValid(boolean valid) {
    isValid = valid;
  }

  public List<String> getViolations() {
    return violations;
  }

  public void setViolations(List<String> violations) {
    this.violations = violations;
  }

}
