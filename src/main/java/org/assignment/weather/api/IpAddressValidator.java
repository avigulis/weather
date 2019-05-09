package org.assignment.weather.api;

import static java.lang.Integer.valueOf;
import static java.util.regex.Pattern.compile;
import static java.util.stream.IntStream.rangeClosed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IpAddressValidator implements ConstraintValidator<IpAddress, String> {

   private final Pattern ipPattern = compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");

   public boolean isValid(String ip, ConstraintValidatorContext context) {
      Matcher matcher = ipPattern.matcher(ip);
      if (matcher.matches()) {
         return rangeClosed(1, matcher.groupCount())
                 .allMatch(i -> valueOf(matcher.group(i)) <= 255);
      }
      return false;
   }
}
