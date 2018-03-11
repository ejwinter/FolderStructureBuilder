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
     *
     * @return the path to this file relative to the parent
     */
    public String getPath() {
        return path;
    }

    /**
     * The content of the file either in text or referenced from
     * an external location (e.g. @classpath:{classpath path})
     *
     * @return the content of this file
     */
    public String getContent() {
        return content;
    }

    /**
     * The character set for the file, defaults to UTF-8
     *
     * @return the characterset that should be used for this content
     */
    public Charset getCharset() {
        return Charset.forName(charset);
    }

    /**
     * The character set for the file, defaults to UTF-8
     *
     * @param charset the characterset that should be used for this content
     * @return this
     */
    public StructureFile setCharset(String charset) {
        this.charset = charset;
        return this;
    }
}
