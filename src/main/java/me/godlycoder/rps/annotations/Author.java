package me.godlycoder.rps.annotations;

public @interface Author {
    String name();
    String dateCreated() default "";
    String dateUpdated() default "";
}
