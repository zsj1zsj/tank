package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.utils.Dir;
import com.mashibing.tank.utils.Group;

import java.awt.*;
import java.util.Random;

public interface Movable {
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    Dir getDir();

    boolean isMoving();
    int getSPEED();

    Group getGroup();

    Random getRandom();

    Rectangle getRect();

    void boundsCheck();
    void randomDir();

    void fire();


    default void move(){
        if (!isMoving())
            return;

        switch (getDir()) {
            case LEFT:
                setX(getX()-getSPEED());
                break;
            case UP:
                setY(getY()-getSPEED());
                break;
            case RIGHT:
                setX(getX()+getSPEED());
                break;
            case DOWN:
                setY(getY()+getSPEED());
                break;
        }

        if (getGroup() == Group.BAD && getRandom().nextInt(100) > 95)
            this.fire();

        if (getGroup() == Group.BAD && getRandom().nextInt(100) > 95)
            this.randomDir();

        boundsCheck();
        // update rect
        getRect().x = getX();
        getRect().y = getY();
    }
}
