package dev.jwall64.midnightlib.requirement;

import dev.jwall64.mdcfg.ConfigObject;

/**
 * A functional interface for implementing requirements
 * @param <T> The type of object which is being checked
 */
public interface RequirementType<T> {

    /**
     * Checks whether the object satisfies the requirement
     * @param data The object to check
     * @param config The requirement configuration
     * @param req The requirement
     * @return Whether the object satisfies the requirement
     */
    boolean check(T data, ConfigObject config, Requirement<T> req);

}
