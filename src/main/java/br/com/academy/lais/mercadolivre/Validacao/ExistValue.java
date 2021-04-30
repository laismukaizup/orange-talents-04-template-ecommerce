package br.com.academy.lais.mercadolivre.Validacao;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ExistValueValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ExistValue {
    String message() default "{{0} não existe no banco }";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> domainClass();

}
