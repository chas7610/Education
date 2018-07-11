package ru.sbt.school.day17.ex2;

public interface Moveable {
    void move(Command command);
    void moveForwards();
    void turnClockwise();
}
