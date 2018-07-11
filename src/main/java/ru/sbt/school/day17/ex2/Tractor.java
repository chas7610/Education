package ru.sbt.school.day17.ex2;

public class Tractor implements Moveable{

    private int[] position;
    private Field field;
    Orientation orientation;

    public Tractor() {
        position = new int[]{0, 0};
        orientation = Orientation.NORTH;
        field = new Field();
    }

    public Tractor(int[] position, Field field, Orientation orientation) {
        this.position = position;
        this.field = field;
        this.orientation = orientation;
    }

    public void move(Command command) {
        switch (command){
            case FORWARD:{
                moveForwards();
            break;
            }
            case TURN:{
                turnClockwise();
                break;
            }
        }
    }

    public void moveForwards() {
        switch (orientation){
            case NORTH:{
                position = new int[]{position[0], position[1] + 1};
                break;
            }
            case EAST:{
                position = new int[]{position[0] + 1, position[1]};
                break;
            }
            case WEST:{
                position = new int[]{position[0] - 1, position[1]};
                break;
            }
            case SOUTH:{
                position = new int[]{position[0], position[1] - 1};
                break;
            }
        }
        if (position[0] > field.getField()[0] || position[1] > field.getField()[1]) {
            throw new TractorInDitchException();
        }
    }

    public void turnClockwise() {
        switch (orientation){
            case NORTH:{
                orientation = Orientation.EAST;
                break;
            }
            case EAST:{
                orientation = Orientation.SOUTH;
                break;
            }
            case SOUTH:{
                orientation = Orientation.WEST;
                break;
            }
            case WEST:{
                orientation = Orientation.NORTH;
                break;
            }
        }
    }

    public int getPositionX() {
        return position[0];
    }

    public int getPositionY() {
        return position[1];
    }

    public Orientation getOrientation() {
        return orientation;
    }


}
