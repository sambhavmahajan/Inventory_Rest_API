package com.sambhavmahajan.restapi;


public class Item {
	private long ID;
	private String Name;
	private int cnt;
	
	public Item(long ID, String Name) {
		this.ID = ID;
		this.Name = Name;
		cnt = 0;
	}
	public long getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public void updateCount(int updateBy) {
		if(cnt + updateBy > 0) {
			cnt += updateBy;
		}else cnt = 0;
	}
	public void setCount(int cnt) {
		if(cnt >= 0) this.cnt = cnt;
	}
	public int getCount(){
		return cnt;
	}
	@Override
	public String toString() {
		return ID + " " + Name;
	}
}
