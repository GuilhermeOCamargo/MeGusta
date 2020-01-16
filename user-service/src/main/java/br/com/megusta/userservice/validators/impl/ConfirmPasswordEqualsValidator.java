package br.com.megusta.userservice.validators.impl;


import br.com.megusta.userservice.validators.annotations.ConfirmPasswordEqualsValid;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @author Guilherme Camargo
 * */
public class ConfirmPasswordEqualsValidator implements ConstraintValidator<ConfirmPasswordEqualsValid, Object> {
    private String senha;
    private String confirmSenha;
    private String message;
    @Override
    public void initialize(ConfirmPasswordEqualsValid constraintAnnotation) {
        this.senha = constraintAnnotation.senha();
        this.confirmSenha = constraintAnnotation.confirmSenha();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        try{
            final String senhaObj = BeanUtils.getProperty(o, senha);
            final String confirmSenhaObj = BeanUtils.getProperty(o, confirmSenha);
            result = senhaObj.equals(confirmSenhaObj);
        }catch (Exception e){
            result = false;
        }
        if(!result){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode("confirmPassword").addConstraintViolation();
        }
        return result;
    }

}
