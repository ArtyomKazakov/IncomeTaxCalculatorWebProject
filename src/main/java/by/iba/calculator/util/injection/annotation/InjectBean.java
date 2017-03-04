package by.iba.calculator.util.injection.annotation;

import java.lang.annotation.*;

/**
 * Identifies injectable by beans fields.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectBean {

    /**
     * Contains the name of a bean, that should be inject.
     * @return bean name
     */
    String beanName();
}
