package com.winteredge.fsbuilder

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class JacksonStructureDefinitionLoaderSpec extends Specification {

    StructureDefinitionLoader loader = new JacksonStructureDefinitionLoader()
    PathStructureImplementor implementor = new PathStructureImplementor()
    @Rule
    TemporaryFolder temporaryFolder

    def "from url"() {

        given:
        URL url = ClassLoader.getSystemClassLoader().getResource("structure1/structure1.json")

        when:
        StructureDefinition definition = loader.load(url)

        then:
        definition.getPath() == "."
        definition.getSubDirectories().get(0).getFiles().get(0).getPath() == "file3.txt"
    }

    def "from url integration"() {

        given:
        Path tempPath = temporaryFolder.newFolder().toPath()

        when:
        StructureDefinition definition = loader.loadFromClasspath("structure1/structure1.json")
        and:
        implementor.implement(definition, tempPath)

        then:
        Files.exists(tempPath.resolve("subdir").resolve("file3.txt"))
        Files.exists(tempPath.resolve("file1.txt"))
        Files.exists(tempPath.resolve("file2.txt"))

    }
}
