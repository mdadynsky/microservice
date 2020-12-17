package ru.dadynsky.demo.entity;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Party {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Date createDate;
    @Getter
    @Setter
    private int version;
    @Getter
    @Setter
    private Set<Item> items = new HashSet<>();

    public JsonObject getJson() {
        JsonObject row = new JsonObject();
        row.addProperty("id", this.id);
        row.addProperty("name", this.name);
        return row;
    }

}
