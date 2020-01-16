package br.com.megusta.userservice.validators.annotations;

import br.com.megusta.userservice.validators.impl.ConfirmPasswordEqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author Guilherme Camargo
 * */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordEqualsValidator.class)
public @interface ConfirmPasswordEqualsValid {
    String message()default "O campo nao corresponde com a senha informada.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    String senha() default "";
    String confirmSenha() default "";

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        ConfirmPasswordEqualsValid[] value();
    }
}
