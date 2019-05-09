package org.assignment.weather.api;

import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
public @interface IpAddress {

    String message() default "{IpAddress.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
