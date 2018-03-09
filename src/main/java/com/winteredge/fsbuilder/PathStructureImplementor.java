package com.winteredge.fsbuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implements the given structure into a Path which should be a directory
 */
public class PathStructureImplementor implements StructureImplementor<Path> {

    /**
     * Takes a given definition and implements it within the given destination
     */
    public void implement(StructureDefinition definition, Path destination) {

        Path where;
        if (definition.getPath() == null || definition.getPath().isEmpty() || definition.getPath().equals(".")) {
            where = destination;
        } else {
            where = destination.resolve(definition.getPath());
        }

        if (!Files.isDirectory(where)) {
            try {
                Files.createDirectory(where);
            } catch (IOException e) {
                throw new StructureImplementationException(e);
            }
        }

        definition.getSubDirectories().forEach(sub -> implement(sub, where));
        definition.getFiles().forEach(file -> writeFile(where, file));
    }

    private void writeFile(Path where, StructureFile file) {
        Path filePath = where.resolve(file.getPath());
        String content;
        if (file.getContent().startsWith("@classpath:")) {
            String resourcePath = file.getContent().substring(11, file.getContent().length());
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            URL url = classLoader.getResource(resourcePath);
            if (url == null) {
                throw new StructureImplementationException("We could not find the resource: " + resourcePath);
            }
            try (Stream<String> lines = Files.lines(new File(url.toURI()).toPath(), file.getCharset())) {
                content = lines.collect(Collectors.joining(System.lineSeparator()));
            } catch (URISyntaxException | IOException e) {
                throw new StructureImplementationException("We could not find resource.", e);
            }

        } else {
            content = file.getContent();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(filePath, file.getCharset(), StandardOpenOption.CREATE)) {
            writer.write(content);
        } catch (IOException e) {
            throw new StructureImplementationException(e);
        }
    }
}
