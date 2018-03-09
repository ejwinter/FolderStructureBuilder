package com.winteredge.fsbuilder

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path

class StructureBuilderSpec extends Specification {

    @Rule
    TemporaryFolder temporaryFolder

    PathStructureImplementor implementor = new PathStructureImplementor()
    StructureDefinitionLoader loader = new JacksonStructureDefinitionLoader()
    StructureBuilder<Path> builder = new StructureBuilder<>(implementor, loader)

    def "integrated implementation"() {
        when:
        Path temp = builder.implementFromClasspath("structure1/structure1.json", temporaryFolder.newFolder().toPath())

        then:
        Files.exists(temp.resolve("subdir").resolve("file3.txt"))
    }
}
