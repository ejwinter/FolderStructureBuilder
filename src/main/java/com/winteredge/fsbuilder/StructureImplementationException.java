package com.winteredge.fsbuilder;

public class StructureImplementationException extends RuntimeException {
    public StructureImplementationException(Exception e) {
        super(e);
    }

    public StructureImplementationException() {
        super();
    }

    public StructureImplementationException(String message) {
        super(message);
    }

    public StructureImplementationException(String message, Throwable cause) {
        super(message, cause);
    }
}
