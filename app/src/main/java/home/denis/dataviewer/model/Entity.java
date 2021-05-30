package home.denis.dataviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//Метасущность для представления любых типов
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


    private String getRawParam(String key) {
        List<String> result = params.stream()
                .filter(item -> item.getName().equals(key))
                .map(Param::getValue)
                .collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }

    public String getTextParam(String key) {
        return getRawParam(key);
    }

    public Double getDoubleParam(String key) {
        String value = getRawParam(key);
        if (value != null) {
            return Double.parseDouble(value);
        }
        return null;
    }

    public Integer getIntegerParam(String key) {
        String value = getRawParam(key);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public String getTextMetadata(String key) {
        return metadata.get(key);
    }

    public Integer getIntegerMetadata(String key) {
        String val = metadata.get(key);
        if (val != null) {
            return Integer.valueOf(val);
        } else {
            return null;
        }
    }
    public Double getDoubleMetadata(String key) {
        String val = metadata.get(key);
        if (val != null) {
            return Double.valueOf(val);
        } else {
            return null;
        }
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
