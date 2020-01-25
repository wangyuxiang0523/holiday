package com.fh.util.excelUtils;

import com.fh.annotation.ExcelAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelUtils {
    //支持任何一个类
    public static String export(List<?> data,Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
            //title，column,mkdir,sheetName,columnName
        ExcelBean excelBean=new ExcelBean();
        //用到java的反射机制获取注解
        ExcelAnnotation excelAnnotation= (ExcelAnnotation) clazz.getAnnotation(ExcelAnnotation.class);
        if(excelAnnotation != null){
            excelBean.setTitle(excelAnnotation.title());
            excelBean.setMkdir(excelAnnotation.mkdir());
            excelBean.setSheetName(excelAnnotation.sheetName());
            //获取字段和字段对应的名称
            List<String> columnList=new ArrayList<String>();
            List<String> columnNameList=new ArrayList<String>();
            //获取类中的所有属性
            Field[] declaredFields = clazz.getDeclaredFields();
            for(int i=0;i<declaredFields.length;i++){
                Field field=declaredFields[i];

                //获取属性上的注解
                ExcelAnnotation annotation = field.getAnnotation(ExcelAnnotation.class);
                if(annotation !=null){
                    //获取属性名
                    columnList.add(field.getName());
                    columnNameList.add(annotation.columnName());
                }
            }
            excelBean.setColumnNames(columnNameList);
            excelBean.setColumns(columnList);
        }
        return CreateExcel.createExcel(data,excelBean);
    }



}
