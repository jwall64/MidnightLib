package dev.jwall64.midnightlib.event;

public interface EventHandler<T> {

    void invoke(T event);

}
