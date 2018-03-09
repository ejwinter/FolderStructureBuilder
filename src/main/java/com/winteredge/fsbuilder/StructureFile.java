package com.winteredge.fsbuilder;

import java.nio.charset.Charset;

/**
 * This is used to define a file that should exist in the structure.
 */
public class StructureFile {
    private String path;
    private String content;
    private String charset = "UTF-8";

    public StructureFile(String path, String content) {
        this.path = path;
        this.content = content;
    }

    public StructureFile() {
    }

    /**
     * The path to the file relative to the parent directory
     */
    public String getPath() {
        return path;
    }

    /**
     * The content of the file either in text or referenced from
     * an external location (e.g. @classpath:{classpath path})
     */
    public String getContent() {
        return content;
    }

    /**
     * The character set for the file, defaults to UTF-8
     */
    public Charset getCharset() {
        return Charset.forName(charset);
    }

    public StructureFile setCharset(String charset) {
        this.charset = charset;
        return this;
    }
}
