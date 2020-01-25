package com.fh.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {

    /**?
     * 标题
     * @return
     */
    String title()default "";

    /**
     * 字段名
     * @return
     */
    String columnName()default "";

    /**?
     * shee页名称
     * @return
     */
    String sheetName()default "";

    /**
     * 业务区分，文件夹路径
     * @return
     */
    String mkdir()default "";
}
