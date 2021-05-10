package com.scb.nextgen.passwordstrength.controller;

import com.scb.nextgen.passwordstrength.constraint.PasswordConstraintValidator;
import com.scb.nextgen.passwordstrength.dto.PasswordPolicyResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class PasswordController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

  @Autowired
  private PasswordConstraintValidator passwordConstraintValidator;

  @RequestMapping(value = "check", method = RequestMethod.POST, produces = {"application/json"})
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody
  PasswordPolicyResult check(@RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
    LOGGER.info("check()->" + password);
    return passwordConstraintValidator.isValid(password);
  }

}
