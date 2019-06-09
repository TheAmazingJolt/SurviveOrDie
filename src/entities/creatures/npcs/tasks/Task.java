package entities.creatures.npcs.tasks;

import java.awt.Graphics;

import entities.Entity;
import entities.creatures.npcs.NPC;
import items.Item;
import main.Handler;
import utils.Countdown;

public class Task {

	private Handler handler;
	
	private String taskType;
	
	private Entity toKill;
	private NPC npcToKill;
	private NPC toSpawn;
	
	private Item toCollect;
	private Item toCollect2;
	private Item toCollect3;
	
	private double timeToSpawn;
	
	private Countdown timer;
	
	private boolean completed;
	
	public Task(String type, Entity toKill, Item toCollect, Handler handler) {
		this.taskType = type;
		this.toKill = toKill;
		this.toCollect = toCollect;
		this.handler = handler;
	}
	
	public Task(String type, NPC toKill, Handler handler) {
		this.taskType = type;
		this.npcToKill = toKill;
		this.handler = handler;
	}
	
	public Task(String type, Item toCollect, Item toCollect2, Item toCollect3, Handler handler) {
		this.taskType = type;
		this.toCollect = toCollect;
		this.toCollect2 = toCollect2;
		this.toCollect3 = toCollect3;
		this.handler = handler;
	}
	
	public Task(String type, NPC toSpawn, double timeToSpawn, Handler handler) {
		this.taskType = type;
		this.toSpawn = toSpawn;
		this.timeToSpawn = timeToSpawn;
		this.handler = handler;
	}
	
	public void tick() {
		if(taskType == "4") {
			if(!toKill.isActive()) {
				System.out.println("Done");
				completed = true;
			}
		}
		if(taskType == "3") {
			if(handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(toCollect.getId())) {
				System.out.println("Done");
				completed = true;
			}
		}
		if(taskType == "2") {
			if(handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(toCollect.getId())) {
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(toCollect2.getId())) {
					if(toCollect3 != null && handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(toCollect3.getId())) {
						System.out.println("Done");
						completed = true;
					}else if(toCollect3 == null) {
						System.out.println("Done");
						completed = true;
					}
				}
			}
		}
		if(taskType == "1") {
			if(!toKill.isActive()) {
				if(handler.getWorld().getEntityManager().getPlayer().getInventory().checkIfContains(toCollect.getId())) {
					System.out.println("Done");
					completed = true;
				}
			}
		}
		if(taskType == "5") {
			if(timer == null) {
				timer = new Countdown((long) timeToSpawn, "Francisco arrives", handler);
			}else if(timer != null) {
				timer.tick();
			}
			if(timer.isCompleted()) {
				handler.getWorld().getEntityManager().addNPC2(toSpawn);
				toSpawn.setX(handler.getWorld().getEntityManager().getPlayer().getStartX());
				toSpawn.setY(handler.getWorld().getEntityManager().getPlayer().getStartY() - 100);
				this.completed = true;
			}
		}
		if(taskType == "6") {
			if(!npcToKill.isActive()) {
				completed = true;
			}
		}
	}
	
	public void render(Graphics g) {
		if(timer != null)
			timer.render(g);
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public Item getToCollect() {
		return toCollect;
	}

	public Item getToCollect2() {
		return toCollect2;
	}

	public Item getToCollect3() {
		return toCollect3;
	}
	
}
