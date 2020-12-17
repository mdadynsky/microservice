package ru.dadynsky.demo.entity;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Item {
    public static final Map<String,Integer> TYPES;
    public static final Map<Integer,String> TYPE_IDS;

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

    private Integer id;
    private Integer parentId;
    private Integer ownerId;
    private Party owner;
    private String serial;
    private String type;
    private Integer typeId;
    private Integer childrenCount;
    private Date createDate;

    public JsonObject getJson(){
        JsonObject row = new JsonObject();
        row.addProperty("id", this.id);
        row.addProperty("serial", this.serial);
        return  row;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Party getOwner() {
        return owner;
    }

    public void setOwner(Party owner) {
        this.owner = owner;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSerialNumber() {
        return serial;
    }

    public void setSerialNumber(String serial) {
        this.serial = serial;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setType(String type) {
        this.type = type;
        this.typeId = TYPES.get(type);
    }

    public String getType() {
        return TYPE_IDS.get(this.typeId)  ;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}
