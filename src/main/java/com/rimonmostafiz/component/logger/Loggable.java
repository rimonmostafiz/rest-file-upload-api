package com.rimonmostafiz.component.logger;

import java.lang.annotation.*;

/**
 * @author Rimon Mostafiz
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable { }
