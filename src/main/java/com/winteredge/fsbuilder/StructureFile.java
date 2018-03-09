package com.winteredge.fsbuilder;

import java.nio.charset.Charset;

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

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public Charset getCharset() {
        return Charset.forName(charset);
    }

    public StructureFile setCharset(String charset) {
        this.charset = charset;
        return this;
    }
}
