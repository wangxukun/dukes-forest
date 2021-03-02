package org.xkidea.qualifiers;

import javax.inject.Qualifier;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Qualifier
@Retention(RUNTIME)
@Target({METHOD,FIELD,PARAMETER,TYPE})
public @interface LoggedIn {
}
