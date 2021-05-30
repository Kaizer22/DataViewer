package home.denis.dataviewer.model.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import home.denis.dataviewer.model.Param;

//Перечисление простых типов, которые могут быть сохранены в Param
//А в дальнейшем обработаны
//Данное перечисление не предполагает гибкой кастомизации
public enum ParamTypeUtils {
    NUMERIC,
    TEXT,
    ID_LINK //Id другой сущности
}
