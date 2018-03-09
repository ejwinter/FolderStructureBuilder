package com.winteredge.fsbuilder

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Subject

import java.nio.file.Files
import java.nio.file.Path

class PathStructureImplementorSpec extends Specification {

    @Subject
    PathStructureImplementor implementor

    @Rule
    TemporaryFolder temporaryFolder

    def setup() {
        implementor = new PathStructureImplementor()
    }

    def "implement simple files with subdirectories"() {

        given:
        StructureDefinition definition = new StructureDefinition(".")
        definition.addFile(new StructureFile("file1.txt", "content1"))
        definition.addSubDirectory(new StructureDefinition("sub").addFile(new StructureFile("file2.txt", "hi")))

        and:
        Path dest = temporaryFolder.newFolder().toPath()

        when:
        implementor.implement(definition, dest)

        then:
        Files.readAllLines(dest.resolve("file1.txt")).get(0).equals("content1")
        Files.readAllLines(dest.resolve("sub").resolve("file2.txt")).get(0).equals("hi")
    }

    def "implement with classpath resource as file source"() {

        given:
        StructureDefinition definition = new StructureDefinition(".")
        definition.addFile(new StructureFile("file1.txt", "content1"))
        definition.addSubDirectory(new StructureDefinition("sub").addFile(new StructureFile("file2.txt", "@classpath:structure1/file2.txt")))

        and:
        Path dest = temporaryFolder.newFolder().toPath()

        when:
        implementor.implement(definition, dest)

        then:
        Files.readAllLines(dest.resolve("file1.txt")).get(0).equals("content1")
        Files.readAllLines(dest.resolve("sub").resolve("file2.txt")).get(0).equals("file2 content")
        Files.readAllLines(dest.resolve("sub").resolve("file2.txt")).get(1).equals("is here")
    }
}
