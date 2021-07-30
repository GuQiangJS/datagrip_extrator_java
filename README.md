# datagrip_extrator_java
DataGrip适用的数据提取器。可以将所需数据导出为Java的ArrayList&lt;Map&lt;String,Object>>格式

## 使用方式
参考 [Add a custom extractor](https://www.jetbrains.com/help/datagrip/data-extractors.html#creating-any-text-extractor-with)

## Example
| NAME | SUBSYSTEM | COUNT | TIME\_ENABLED |
| :--- | :--- | :--- | :--- |
| metadata\_table\_reference\_count | metadata | 0 | NULL |
| lock\_deadlocks | lock | 0 | 2021-07-29 10:43:24 |
| lock\_timeouts | lock | 0 | 2021-07-29 10:43:24 |

导出后的java代码如下

```java
        new ArrayList<HashMap<String, Object>>() {{
            add(new HashMap<String, Object>(4) {{
                put("NAME", "metadata_table_reference_count");
                put("SUBSYSTEM", "metadata");
                put("COUNT", 0);
                put("TIME_ENABLED", null);}});
            add(new HashMap<String, Object>(4) {{
                put("NAME", "lock_deadlocks");
                put("SUBSYSTEM", "lock");
                put("COUNT", 0);
                put("TIME_ENABLED", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-07-29 10:43:24"));}});
            add(new HashMap<String, Object>(4) {{
                put("NAME", "lock_timeouts");
                put("SUBSYSTEM", "lock");
                put("COUNT", 0);
                put("TIME_ENABLED", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-07-29 10:43:24"));}});
        }};
```

