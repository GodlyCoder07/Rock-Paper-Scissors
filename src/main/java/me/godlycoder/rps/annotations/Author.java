package me.godlycoder.rps.annotations;

public @interface Author {
    String name();
    String date() default "";
}
