package com.sprint.hibernate.validator;

import lombok.var;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.Optional;
import java.util.Set;
@Service
public class EntityValidate {
    public static <T> boolean validate ( T entity ) throws ConstraintViolationException, IllegalAccessException, NoSuchFieldException {
        String message="";;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate ( entity );
        for (ConstraintViolation<T> violation : violations) {
            message+="Error in field " +violation.getPropertyPath ()+ " with value "+violation.getInvalidValue ()+": "+violation.getMessage ()+'\n';

        }
        if(message.length() == 0)
            return true;
        else throw new ConstraintViolationException ( message.substring(0,message.length ()-1),violations );

    }
}
