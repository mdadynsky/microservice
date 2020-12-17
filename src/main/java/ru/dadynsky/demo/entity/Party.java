package ru.dadynsky.demo.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonObject;

public class Party {
    private int id;
    private String name;
    private Date createDate;
    private int version;
    private Set<Item> items = new HashSet<>();

    public JsonObject getJson(){
        JsonObject row = new JsonObject();
        row.addProperty("id", this.id);
        row.addProperty("name", this.name);
        return  row;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
