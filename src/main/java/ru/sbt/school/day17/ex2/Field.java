package ru.sbt.school.day17.ex2;

public class Field {
    private final int [] field;


    public Field(int[] field) {
        this.field = field;
    }

    public Field() {
        this.field = new int[]{5,5};
    }

    public int[] getField() {
        return field;
    }
}
