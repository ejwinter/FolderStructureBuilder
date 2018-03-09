package com.winteredge.fsbuilder;

import java.io.IOException;

public interface StructureImplementor<T> {
    void implement(StructureDefinition definition, T destination) throws IOException;
}
