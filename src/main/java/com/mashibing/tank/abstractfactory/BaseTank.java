package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.utils.Dir;
import com.mashibing.tank.utils.Group;
import lombok.Data;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

@Data
public abstract class BaseTank implements Movable{
	public Group group = Group.BAD;
	public Rectangle rect;

	private int x, y;
	public Dir dir;
	private boolean moving = true;
	public static final int SPEED = 2;
	private Random random = new Random();

	@Override
	public int getSPEED() {
		return SPEED;
	}

	@Override
	public abstract void boundsCheck();

	@Override
	public abstract void randomDir();


	public abstract void paint(Graphics g);

	public Group getGroup() {
		return this.group;
	}

	public abstract void die();
}
