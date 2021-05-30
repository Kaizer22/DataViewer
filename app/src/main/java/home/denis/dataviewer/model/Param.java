package home.denis.dataviewer.model;

import java.util.Map;

import home.denis.dataviewer.model.utils.ParamTypeUtils;

//Параметр метасущности
public class Param {
    private String name;
    private String value;
    private String type;
    private Map<String, String> metadata;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "Param{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                ", metadata=" + metadata +
                '}';
    }
}
