package com.belichenko.a.messa.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Max Chervatiuk on 31.03.16.
 * Email: duo.blood@gmail.com
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
