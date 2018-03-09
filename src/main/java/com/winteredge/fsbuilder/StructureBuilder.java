package com.winteredge.fsbuilder;

import java.io.IOException;

/**
 * A convenience wrapper for both loading the definition and implementing in a destination
 * <p>
 * This will have the most common ways that this library is used
 */
public class StructureBuilder<T> {

    private final StructureImplementor<T> implementor;

    private final StructureDefinitionLoader loader;

    public StructureBuilder(StructureImplementor<T> implementor, StructureDefinitionLoader loader) {
        this.implementor = implementor;
        this.loader = loader;
    }

    public T implementFromClasspath(String classpathUrl, T destination) {
        try {
            implementor.implement(loader.loadFromClasspath(classpathUrl), destination);
        } catch (IOException e) {
            throw new StructureImplementationException(e);
        }
        return destination;
    }
}
