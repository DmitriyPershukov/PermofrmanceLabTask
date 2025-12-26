package org.example;

public class Test {
    private int id;
    private String title;
    private String value;
    private Test[] values;

    public Test(int id, String title, String value, Test[] values) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Test[] getValues() {
        return values;
    }

    public void setValues(Test[] values) {
        this.values = values;
    }
}
