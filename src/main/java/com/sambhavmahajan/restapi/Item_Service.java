package com.sambhavmahajan.restapi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class Item_Service {
	private static String FILE = "data.txt";
	
	public HashMap<Long, Item> itemList;
	public Item_Service() {
		File file = new File(FILE);
		if(file.exists()) {
			try {
				Scanner sc = new Scanner(file);
				while(sc.hasNextLine()) {
					final String[] s = sc.nextLine().split(" ");
					if(s.length < 3) continue;
					long id = Long.parseLong(s[0]);
					if(itemList.containsKey(id)) {
						Item i = new Item(id, s[1]);
						itemList.put(id, i);
						itemList.get(id).setCount(Integer.parseInt(s[2]));
					}else itemList.get(id).updateCount(Integer.parseInt(s[2]));
					sc.close();
				}
			} catch (FileNotFoundException e) {
				//do nothing
			}
		}
	}
	private void tryRemoveItem(Item i) {
		long id = i.getID();
		if(itemList.get(id).getCount() <= 0) {
			itemList.remove(id);
		}
	}
	public boolean updateFile(Item item, int cnt) {
		try {
			FileWriter fw;
			fw = new FileWriter(FILE, true);
			fw.write(item.getID() + " " + item.getName() + " " + cnt);
			itemList.get(item.getID()).updateCount(cnt);
			fw.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	public void addItem(long id, String name) {
		Item i = new Item(id, name);
		if(!itemList.containsKey(id)) {
			itemList.put(id,  i);
		}
		updateFile(i, 1);
	}
	public void removeItem(long id, String name) {
		if(!itemList.containsKey(id)) return;
		Item i = itemList.get(id);
		i.updateCount(-1);
		updateFile(i, -1);
		tryRemoveItem(i);
	}
	public boolean updateItemCount(long id, int cnt) {
		if(!itemList.containsKey(id)) return false;
		Item i = itemList.get(id);
		updateFile(i, -1 * i.getCount());
		updateFile(i, cnt);
		tryRemoveItem(i);
		return true;
	}
	public String getItemInfo(long ID) {
		if(itemList.containsKey(ID)) {
			return itemList.get(ID).toString();
		}
		return "Error: Could not find any item associated with ID: " + ID;
	}
}
