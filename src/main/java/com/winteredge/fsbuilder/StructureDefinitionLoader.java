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
     *
     * @param path the path to the file we want to load
     * @return the structure definition defined in the path
     */
    StructureDefinition load(Path path);

    /**
     * loads the definition from a url, often a classpath url but could
     * be a different url
     *
     * @param url the path to the file we want to load
     * @return the structure definition defined in the path
     */
    StructureDefinition load(URL url);

    /**
     * Loads the definition from a classpath resource.  This is a common
     * way of using this library
     *
     * @param url the path to the file we want to load
     * @return the structure definition defined in the path
     */
    StructureDefinition loadFromClasspath(String url);
}
