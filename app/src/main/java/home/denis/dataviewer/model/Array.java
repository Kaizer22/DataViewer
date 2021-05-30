package home.denis.dataviewer.model;

import java.util.List;
import java.util.Map;

import home.denis.dataviewer.model.utils.ParamTypeUtils;

//Дочерний массив сущности; может содержать значения простых типов ParamType
//Засчет определения простого типа ID_LINK реализуется иерархия сущностей
public class Array {
    private String name;
    private List<String> values;
    private String arrayType;
    private Map<String, String> metadata;

    public String getName() {
        return name;
    }

    public List<String> getValues() {
        return values;
    }

    public String getArrayType() {
        return arrayType;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
}
