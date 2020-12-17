package ru.dadynsky.demo.entity;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Item {
    public static final Map<String, Integer> TYPES;
    public static final Map<Integer, String> TYPE_IDS;

    public final static Integer TYPE_PACK = 1;
    public final static Integer TYPE_ITEM = 2;

    static {
        TYPES = new HashMap<>();
        TYPES.put("PACK", TYPE_PACK);
        TYPES.put("ITEM", TYPE_ITEM);
    }

    static {
        TYPE_IDS = new HashMap<>();
        TYPE_IDS.put(TYPE_PACK, "PACK");
        TYPE_IDS.put(TYPE_ITEM, "ITEM");
    }

    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private Integer parentId;
    @Getter
    @Setter
    private Integer ownerId;
    @Getter
    @Setter
    private Party owner;
    @Getter
    @Setter
    private String serial;
    @Getter
    @Setter
    private Integer typeId;
    @Getter
    @Setter
    private Integer childrenCount;
    @Getter
    @Setter
    private Date createDate;

    public JsonObject getJson() {
        JsonObject row = new JsonObject();
        row.addProperty("id", this.id);
        row.addProperty("serial", this.serial);
        return row;
    }

    public String getSerialNumber() {
        return serial;
    }

    public void setSerialNumber(String serial) {
        this.serial = serial;
    }

    public void setType(String type) {
        this.typeId = TYPES.get(type);
    }

    public String getType() {
        return TYPE_IDS.get(this.typeId);
    }


}
