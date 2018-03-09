package com.winteredge.fsbuilder;

import java.util.*;
import java.util.stream.Collectors;

public class StructureDefinition {

    private String path = ".";
    private List<StructureDefinition> subDirectories = new LinkedList<>();
    private List<StructureFile> files = new LinkedList<>();

    public StructureDefinition(String path, Collection<StructureFile> content, Collection <StructureDefinition> substructures) {
        this.path = path;
        content.stream().collect(Collectors.toCollection(()->this.files));
        substructures.stream().collect(Collectors.toCollection(()->this.subDirectories));
    }

    public StructureDefinition(String path) {
        this.path = path;
    }

    public StructureDefinition() {
    }

    public String getPath() {
        return path;
    }

    public List<StructureDefinition> getSubDirectories() {
        return new ArrayList<>(subDirectories);
    }

    public StructureDefinition addSubDirectory(StructureDefinition sub){
        this.subDirectories.add(sub);
        return this;
    }

    public List<StructureFile> getFiles() {
        return new ArrayList<>(this.files);
    }

    public StructureDefinition addFile(StructureFile file){
        this.files.add(file);
        return this;
    }


}
