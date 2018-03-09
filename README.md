# Folder Structure Builder
This project allows you declare a directory structure either in Java code or
within a declarative format.

It is often used in creating unit tests of file system intensive projects.
It is an alternative to having a lot of file structures on the classpath or
painfully building up a directory structure manually.   

## Features
  * You can build up a directory structure with Java and deploy to a target directory
  * You can define your directory structure in JSON including which files,
    subdirectories, and file content should be present.
  * File content can be defined as simple text in the definition file or 
    by referencing classpath resources (when content is richer)
  * You can define what the character set is used in the content (default UTF-8)

## Usage
You can always reference the unit tests of this project to see some examples
of you could use it in your project.

This is a common way to use it
```java
//we need to know how we should implement the structure, in this case
// a PathStructureImplementor will implement within a directory 
PathStructureImplementor implementor = new PathStructureImplementor()

// StructureDefinitionLoader instances are used to load the structure
// from declarative formats (e.g. Json)
StructureDefinitionLoader loader = new JacksonStructureDefinitionLoader()

// this builder is a convenience wrapper, you could load and implment yourself
// but you can also define one structure builder
StructureBuilder<Path> builder = new StructureBuilder<>(implementor, loader)

// this call will load the structure defined in structure1.json and then 
//implement that structure in the given path (in this case a temporary folder)
Path temp = builder.implementFromClasspath("structure1/structure1.json", temporaryFolder.newFolder().toPath())

// and now we can use the structure in our own testing
assert(Files.exists(temp.resolve("subdir").resolve("file3.txt")))
```

## License
MIT License as presented in the LICENSE.txt by Eric Winter 2018.

## Future Work (possibilities)
  * All you to use more JSON libraries
  * Allow other declaration formats besides json (XML, YAML)
  * Allow the structures to be implemented in other destinations
    than a directory (e.g. FTP server)
  * Define file content in ways besides text or contents of classpath
    resources such as downloading from a http/ftp server
