package com.winteredge.fsbuilder;

import java.net.URL;
import java.nio.file.Path;

/**
 * Implementations of this can be used to load definitions from
 * various locations from a variety for formats (e.g. JSON via Jackson).
 */
public interface StructureDefinitionLoader {
    /**
     * loads the definition from a path instances assumed to be a supported
     * declaration format.
     */
    StructureDefinition load(Path path);

    /**
     * loads the definition from a url, often a classpath url but could
     * be a different url
     */
    StructureDefinition load(URL url);

    /**
     * Loads the definition from a classpath resource.  This is a common
     * way of using this library
     */
    StructureDefinition loadFromClasspath(String url);
}
