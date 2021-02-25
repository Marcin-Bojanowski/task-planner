package coderslab.pl.taskplanner.exceptions.globalExceptionHandler.apierror;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
@Service
@Slf4j
public class ApiErrorService {

    private final MessageSource messageSource;
//    private final MessageCodesResolver messageCodesResolver;

    private ApiSubError getValidationError(String object, String field, Object rejectValue, String message) {
        return new ApiValidationError(object, field, rejectValue, message);
    }

    public ApiSubError getValidationError(ConstraintViolation<?> ex) {
        StringBuilder builder=new StringBuilder(ex.getMessage());
        builder.append(ex.getRootBean().toString()).append( ((PathImpl) ex.getPropertyPath()).getLeafNode().asString());
        log.info(builder.toString());
        log.info(ex.getRootBean().toString());
        log.info(((PathImpl) ex.getPropertyPath()).getLeafNode().getClass().toString());

        return getValidationError(ex.getRootBean().toString(),
                ((PathImpl) ex.getPropertyPath()).getLeafNode().asString(),
                ex.getInvalidValue(),
                ex.getMessage());

    }

    public ApiSubError getValidationError(FieldError error) {

        return getValidationError(error.getObjectName(),
                error.getField(),
                error.getRejectedValue(),
                messageSource.getMessage(error,LocaleContextHolder.getLocale()));

    }

//    private String getMessageByErrorCode(ConstraintViolation<?> error) {
//        DefaultMessageSourceResolvable defaultMessageSourceResolvable=new DefaultMessageSourceResolvable(error.)
//        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
//    }
//
////    private String codeBuilder(FieldError error){
////        StringBuilder builder=new StringBuilder(error.getCode());
////        bu
////
////    }
//
//    private String [] codesBuilder(ConstraintViolation<?> error){
//        error.
//    }
}
