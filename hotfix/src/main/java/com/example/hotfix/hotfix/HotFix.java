package com.example.hotfix.hotfix;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HotFix {
    public static void installPatch(Context context, File patch) {
        System.out.println("installPatch: " + patch.getAbsolutePath());

        // 1.获取当前应用的PathClassLoader，也可以直接new一个PathClassLoader
        ClassLoader classLoader = context.getClassLoader();

        try {
            // 2.反射获取BaseDexClassLoader的属性对象pathList
            Field pathListField = ReflectUtil.getField(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);

            // 3.反射修改pathList的dexElements

            // 3.1把patch.dex转化为Element[]
            Method makeDexElements = ReflectUtil.getMethod(pathList, "makeDexElements", List.class, File.class, List.class, ClassLoader.class);

            List<File> files = new ArrayList<>(10);
            files.add(patch);
            File fileDir = context.getFilesDir();
            List<IOException> suppressedExceptionList = new ArrayList<IOException>();
            // 反射执行makeDexElements方法
            Object[] patchElements = (Object[]) makeDexElements.invoke(null, files, fileDir, suppressedExceptionList, classLoader);

            // 3.2 获得pathList的dexElements属性
            Field dexElementsField = ReflectUtil.getField(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementsField.get(pathList);

            // 3.3 patchElements与dexElements合并，并反射赋值给pathList的dexElements
            Object[] newElements = (Object[]) Array.newInstance(dexElements[0].getClass(), patchElements.length + dexElements.length);
            System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
            System.arraycopy(dexElements, 0, newElements, patchElements.length, dexElements.length);
            dexElementsField.set(pathList, newElements);

            for (Object o : newElements) {
                System.out.println(o);
            }

            System.out.println("install success ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("installPatch exception: " + e.getMessage());
        }
    }
}