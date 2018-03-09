package com.winteredge.fsbuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

/**
 * Use this if you want to load definitions from json files using Jackson.  You
 * will need to make sure Jackson 2.x is on your classpath.
 */
public class JacksonStructureDefinitionLoader implements StructureDefinitionLoader {

    private final ObjectMapper objectMapper;

    public JacksonStructureDefinitionLoader() {
        objectMapper = new ObjectMapper();
    }

    public JacksonStructureDefinitionLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public StructureDefinition load(Path path) {
        try {
            return objectMapper.readValue(path.toFile(), StructureDefinition.class);
        } catch (IOException e) {
            throw new StructureImplementationException(e);
        }
    }

    @Override
    public StructureDefinition load(URL url) {
        try {
            return objectMapper.readValue(url, StructureDefinition.class);
        } catch (IOException e) {
            throw new StructureImplementationException(e);
        }
    }

    @Override
    public StructureDefinition loadFromClasspath(String url) {
        return load(ClassLoader.getSystemClassLoader().getResource(url));
    }
}
