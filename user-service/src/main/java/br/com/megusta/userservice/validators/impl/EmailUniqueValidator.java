package br.com.megusta.userservice.validators.impl;

import br.com.megusta.userservice.exceptions.ObjectNotFoundException;
import br.com.megusta.userservice.model.domain.User;
import br.com.megusta.userservice.service.UserService;
import br.com.megusta.userservice.validators.annotations.EmailUniqueValid;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Guilherme Camargo
 * */
public class EmailUniqueValidator implements ConstraintValidator<EmailUniqueValid, Object> {

    @Autowired
    private UserService userService;
    private String email;
    private String message;
    private String id;

    @Override
    public void initialize(EmailUniqueValid constraintAnnotation) {
        this.email = constraintAnnotation.email();
        this.message = constraintAnnotation.message();
        this.id = constraintAnnotation.id();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean result=false;
        try{
            final String email = BeanUtils.getProperty(o, this.email);
            final String id = BeanUtils.getProperty(o, this.id);
            User user = userService.findByEmail(email);
            if(id != null && !id.isEmpty()){
                if(user.getId().equals(Long.parseLong(id))){
                    result = true;
                }
            }else {
                result = false;
            }
        }catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e){

        }catch (ObjectNotFoundException e){
            result = true;
        }

        if(!result){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("email").addConstraintViolation();
        }
        return result;
    }
}
