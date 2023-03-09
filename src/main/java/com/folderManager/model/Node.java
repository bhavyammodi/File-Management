package com.folderManager.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Folders")
public class Node {
    private int id;
    private String name;
    private String type;
    private boolean softDelete;
    private int parent;

    public Node(int id, String name, String type, boolean softDelete, int parent) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.softDelete = softDelete;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }
}
