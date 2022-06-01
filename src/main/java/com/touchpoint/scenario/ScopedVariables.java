package com.touchpoint.scenario;

import com.google.common.collect.Maps;

import java.util.Map;

public class ScopedVariables {

    private final Map<String, Object> variables = Maps.newHashMap();

    public void put(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        Object variable = variables.get(name);
        if (variable != null) {
            return variable;
        } else {
            throw new IllegalArgumentException(String.format("Variable with name [%s] isn't set", name));
        }
    }
}
