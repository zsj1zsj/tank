package com.mashibing.tank.abstractfactory;

import com.mashibing.tank.Bullet;
import com.mashibing.tank.TankFrame;
import com.mashibing.tank.strategy.DefaultFireStrategy;
import com.mashibing.tank.strategy.FireStrategy;
import com.mashibing.tank.utils.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class RectTank extends BaseTank {

	public static int WIDTH = ResourceMgr.goodTankU.getWidth();

	public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

	public Rectangle rect = new Rectangle();

	private Random random = new Random();

	int x, y;

	Dir dir = Dir.DOWN;

	private boolean moving = true;
	TankFrame tf;
	private boolean living = true;
	Group group = Group.BAD;

	FireStrategy fs;

	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = WIDTH;
		rect.height = HEIGHT;

		if (group == Group.GOOD) {
			String goodFSName = (String) PropertyMgr.get("goodFS");
			fs = new DefaultFireStrategy();
		}

	}

	public void fire() {
		// fs.fire(this);

		int bX = this.x + RectTank.WIDTH / 2 - Bullet.WIDTH / 2;
		int bY = this.y + RectTank.HEIGHT / 2 - Bullet.HEIGHT / 2;

		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {
			tf.gf.createBullet(bX, bY, dir, group, tf);
		}

		if (group == Group.GOOD)
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

	@Override
	public void boundsCheck() {
		if (this.x < 2)
			x = 2;
		if (this.y < 28)
			y = 28;
		if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2)
			x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
		if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2)
			y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
	}

	@Override
	public void randomDir() {

		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void paint(Graphics g) {
		if (!living)
			tf.tanks.remove(this);

		Color c = g.getColor();
		g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
		g.fillRect(x, y, 40, 40);
		g.setColor(c);
		move();

	}

	public void die() {
		this.living = false;
	}

}
