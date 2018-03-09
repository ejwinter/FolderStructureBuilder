package com.winteredge.fsbuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class JacksonStructureDefinitionLoader implements StructureDefinitionLoader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JacksonStructureDefinitionLoader() {
    }

    public JacksonStructureDefinitionLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public StructureDefinition load(Path path){
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
    public StructureDefinition load(File file){
        return load(file.toPath());
    }

    @Override
    public StructureDefinition loadFromClasspath(String url) {
        return load(ClassLoader.getSystemClassLoader().getResource(url));
    }
}
