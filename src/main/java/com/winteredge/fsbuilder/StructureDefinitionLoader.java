package com.winteredge.fsbuilder;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

public interface StructureDefinitionLoader {
    StructureDefinition load(Path path);
    StructureDefinition load(URL url);
    StructureDefinition load(File file);
    StructureDefinition loadFromClasspath(String url);
}
