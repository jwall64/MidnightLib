package dev.jwall64.midnightlib.types;

import dev.jwall64.midnightlib.event.HandlerList;

/**
 * A {@link dev.jwall64.midnightlib.types.Singleton Singleton} whose value can be reset after being set.
 * @param <T> The type of value stored in the Singleton
 */
public class ResettableSingleton<T> extends Singleton<T> {

    /**
     * An event fired when the Singleton's value is reset.
     */
    public final HandlerList<ResettableSingleton<T>> resetEvent = new HandlerList<>();

    /**
     * Clears the value stored in this Singleton
     */
    public void reset() {
        this.value = null;
        setEvent.reset();
        resetEvent.invoke(this);
    }

}
