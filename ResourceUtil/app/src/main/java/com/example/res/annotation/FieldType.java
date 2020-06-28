package com.example.res.annotation;

public enum FieldType {
    ID(0,"id",""),
    STRING(1,"string","getString"),
    COLOR(2,"color","getColor"),
    DINEN(3,"dimen","getDimension"),
    DINEN_PIXEL_SIZE(4,"dimen","getDimensionPixelSize"),
    ANIM(5,"anim","getAnimation");
    private String defType;
    private int defTypeId = 0;
    private String methodName = "";
    private FieldType(int type,String typeName,String methodName){
        defTypeId = type;
        defType = typeName;
        this.methodName = methodName;
    }

    public String getDefType(){
        return defType;
    }

    public String getMethodName(){
        return methodName;
    }
}
