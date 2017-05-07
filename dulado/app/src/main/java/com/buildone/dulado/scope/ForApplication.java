package com.buildone.dulado.scope;

/**
 * Created by Alessandro Pryds on 24/04/2017.
 */

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier @Retention(RUNTIME)
public @interface ForApplication {
}
