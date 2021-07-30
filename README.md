# datagrip_extrator_java
DataGrip适用的数据提取器。可以将所需数据导出为Java的ArrayList&lt;Map&lt;String,Object>>格式

## 使用方式
参考 [Add a custom extractor](https://www.jetbrains.com/help/datagrip/data-extractors.html#creating-any-text-extractor-with)

## Example
| TABLE\_NAME | COLUMN\_NAME | ORDINAL\_POSITION | CHARACTER\_MAXIMUM\_LENGTH | CHARACTER\_OCTET\_LENGTH |
| :--- | :--- | :--- | :--- | :--- |
| ALL\_PLUGINS | PLUGIN\_NAME | 1 | 64 | 192 |
| ALL\_PLUGINS | PLUGIN\_VERSION | 2 | 20 | 60 |
| ALL\_PLUGINS | PLUGIN\_STATUS | 3 | 16 | 48 |

导出后的java代码如下

```java
new ArrayList<HashMap<String, Object>>() {{
    add(new HashMap<String, Object>(5) {{
        put("TABLE_NAME", "ALL_PLUGINS");
            put("COLUMN_NAME", "PLUGIN_NAME");
            put("ORDINAL_POSITION", 1);
            put("CHARACTER_MAXIMUM_LENGTH", 64);
            put("CHARACTER_OCTET_LENGTH", 192);}});
        add(new HashMap<String, Object>(5) {{
            put("TABLE_NAME", "ALL_PLUGINS");
            put("COLUMN_NAME", "PLUGIN_VERSION");
            put("ORDINAL_POSITION", 2);
            put("CHARACTER_MAXIMUM_LENGTH", 20);
            put("CHARACTER_OCTET_LENGTH", 60);}});
        add(new HashMap<String, Object>(5) {{
            put("TABLE_NAME", "ALL_PLUGINS");
            put("COLUMN_NAME", "PLUGIN_STATUS");
            put("ORDINAL_POSITION", 3);
            put("CHARACTER_MAXIMUM_LENGTH", 16);
            put("CHARACTER_OCTET_LENGTH", 48);}});
}};
```

