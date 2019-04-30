package huayllani.cripto.comint;

public class Instruct {
    private String[] columns;
    private String table;
    private String instruct;
    private String[] where;
    private String order;

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] getColumns() {
        return this.columns;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public String[] getWhere() {
        return where;
    }

    public void setWhere(String[] where) {
        this.where = where;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isWhere() {
        return this.getWhere().length > 0;
    }

    public boolean isColumns() {
        return this.getColumns().length > 0;
    }
}
