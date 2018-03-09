package com.winteredge.fsbuilder;

import java.io.IOException;

/**
 * Implementations will deploy the given definition to the given destination.
 * <p>
 * For now we just have a filesystem Path implementation
 * <p>
 * In the future we could have FTP implementations for example...
 */
public interface StructureImplementor<T> {
    void implement(StructureDefinition definition, T destination) throws IOException;
}
