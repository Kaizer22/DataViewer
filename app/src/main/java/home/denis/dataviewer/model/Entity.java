package home.denis.dataviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Entity {
    private long id;
    @SerializedName("type_name")
    private String typeName;
    private List<Param> params;
    private List<Array> arrays;
    private Map<String, String> metadata;

    public Entity(String typeName) {
        this.typeName = typeName;
        params = new LinkedList<>();
        arrays = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public List<Param> getParams() {
        return params;
    }

    public List<Array> getArrays() {
        return arrays;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + typeName + '\'' +
                ", params=" + params +
                ", arrays=" + arrays +
                ", metadata=" + metadata +
                '}';
    }
}
