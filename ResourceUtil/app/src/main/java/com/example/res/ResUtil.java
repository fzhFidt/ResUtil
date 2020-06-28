package com.example.res;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.example.res.annotation.FieldType;
import com.example.res.annotation.ViewField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ResUtil {
    /**
     *
     * @param cls 类的名称
     * @param obj 类的实例
     * @param mainView 布局Viwe
     */
    public static void setViewItem1(Class<?> cls, Object obj, View mainView){
        Field[] fields = cls.getDeclaredFields();//获取定义的类
        if(fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();//变量的名称
                String declaringClassName = field.getType().getName();//定义的类
                //用于判断是否是View或是View的子类。
                if(!declaringClassName.contains("android.view") &&
                        !declaringClassName.contains("android.widget")){
                    continue;
                }
                if(name == null){
                    continue;
                }
                Context context = mainView.getContext();
                int id = getIdResId(context,context.getPackageName(),name);//通过名称获取id
                if(id <= 0){
                    continue;
                }
                try {
                    View view = mainView.findViewById(id);
                    if(view != null){
                        field.set(obj,view);//给变量赋值
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     *
     * @param cls 类的名称
     * @param obj 类的实例
     * @param mainView 布局Viwe
     */
    public static void setResItem(Class<?> cls, Object obj, Context context,View mainView){
        Field[] fields = cls.getDeclaredFields();//获取定义的类
        if(fields != null) {
            for (Field field : fields) {
                Log.e("cl1"," field is "+field);
                field.setAccessible(true);
                ViewField annotation = field.getAnnotation(ViewField.class);
                Log.e("cl1"," annotation is "+annotation+" fieldName is "+ field.getName());
                if(annotation == null) {
                    continue;
                }
                FieldType fieldType = annotation.fieldType();
                int resId = annotation.resId();
                if(resId != -1){
                }else{
                    String name = field.getName();//变量的名称
                    Log.e("cl"," name is "+name);
                    if(name == null){
                        continue;
                    }
                    String defType = fieldType.getDefType();
                    /*
                    String defType = "";
                    switch (fieldType){
                        case STRING:
                            defType = "string";
                            break;
                        case COLOR:
                            defType = "color";
                            break;
                        case ANIM:
                            defType = "anim";
                            break;
                        case DINEN:
                            defType = "dimen";
                            break;
                    }*/
                    resId= getResId(context,context.getPackageName(),name,defType);//通过名称获取id
                    if(resId <= 0){
                        continue;
                    }
                }
                Object res = null;
                Resources resources = context.getResources();
                if(fieldType == FieldType.ID){
                    res = mainView.findViewById(resId);
                }else{
                    /*
                    switch (fieldType){
                        case STRING:
                            res = resources.getString(resId);
                            break;
                        case COLOR:
                            res = resources.getColor(resId);
                            break;
                        case ANIM:
                            res = resources.getAnimation(resId);
                            break;
                        case DINEN:
                            res = resources.getDimension(resId);
                            break;
                    }*/
                    try {
                        Log.e("cl"," methodName is "+fieldType.getMethodName());
                        Method method = Resources.class.getMethod(fieldType.getMethodName(),int.class);
                        Log.e("cl"," method is "+method);
                        if(method != null){
                            res = method.invoke(resources,resId);
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    if(res != null){
                        field.set(obj,res);//给变量赋值
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 得到资源id
    public static int getResId(Context context, String pageName, String resName, String type){
        return context.getResources().getIdentifier(resName, type, pageName);
    }

    /**
     * 取得指定名称的资源id
     * @param idName
     * @return
     */
    public static int getIdResId(Context context, String pageName, String idName){
        return getResId(context, pageName, idName, "id");
    }
}
