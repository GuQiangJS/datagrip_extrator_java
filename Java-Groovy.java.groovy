/*
 * Available context bindings:
 *   COLUMNS     List<DataColumn>
 *   ROWS        Iterable<DataRow>
 *   OUT         { append() }
 *   FORMATTER   { format(row, col); formatValue(Object, col); getTypeName(Object, col); isStringLiteral(Object, col); }
 *   TRANSPOSED  Boolean
 * plus ALL_COLUMNS, TABLE, DIALECT
 *
 * where:
 *   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
 *   DataColumn  { columnNumber(), name() }
 */


import static com.intellij.openapi.util.text.StringUtil.escapeStringCharacters as escapeStr

NEWLINE = System.getProperty("line.separator")
INDENT = "  "

def printJava(level, col, o) {
    switch (o) {
        case null: OUT.append("null"); break
        case Tuple: printJava(level, o[0], o[1]); break
        case Map:
            OUT.append("new HashMap<String, Object>("+COLUMNS.size()+") {{")
            o.entrySet().eachWithIndex { entry, i ->
                OUT.append("$NEWLINE${INDENT * (level + 1)}")
                OUT.append("put(\"${escapeStr(entry.getKey().toString())}\", ")
                printJava(level + 1, col, entry.getValue())
                OUT.append(");")
            }
            OUT.append("}}")
            break
        case Object[]:
        case Iterable:
            OUT.append("new ArrayList<Map<String, Object>>() {{")
            def plain = true
            o.eachWithIndex { item, i ->
                plain = item == null || item instanceof Number || item instanceof Boolean || item instanceof String
                OUT.append("$NEWLINE${INDENT * (level + 1)}")
                OUT.append("add(")
                printJava(level + 1, col, item)
                OUT.append(");")
            }
            if (plain) OUT.append("}};") else OUT.append("$NEWLINE${INDENT * level}}};")
            break
        case Boolean: OUT.append("$o"); break
        default:
            def str = FORMATTER.formatValue(o, col)
            if (FORMATTER.getTypeName(o, col).toUpperCase() == "DATETIME") {
                OUT.append("new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\").parse(\"${escapeStr(str)}\")")
            } else if (FORMATTER.isStringLiteral(o, col)) {
                OUT.append("\"${escapeStr(str)}\"")
            } else {
                OUT.append(str)
            }
            break
    }
}

printJava(0, null, ROWS.transform { row ->
    def map = new LinkedHashMap<String, String>()
    COLUMNS.each { col ->
        if (row.hasValue(col)) {
            def val = row.value(col)
            map.put(col.name(), new Tuple(col, val))
        }
    }
    map
})
