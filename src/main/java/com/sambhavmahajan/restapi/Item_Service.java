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
		itemList = new HashMap<Long, Item>();
		loadItemsFromFile();
	}
    private void loadItemsFromFile() {
        File file = new File(FILE);
        if (file.exists()) {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String[] s = sc.nextLine().split(" ");
                    if (s.length < 3) continue;
                    long id = Long.parseLong(s[0]);
                    Item item = new Item(id, s[1]);
                    item.setCount(Integer.parseInt(s[2]));
                    itemList.put(id, item);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
	private void tryRemoveItem(Item i) {
		long id = i.getID();
		if(itemList.get(id).getCount() <= 0) {
			itemList.remove(id);
		}
	}
    private void updateFile() {
        try (FileWriter fw = new FileWriter(FILE)) {
            for (Item item : itemList.values()) {
                fw.write(item.getID() + " " + item.getName() + " " + item.getCount() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public String addItem(long id, String name) {
		Item i = new Item(id, name);
		if(!itemList.containsKey(id)) {
			itemList.put(id,  i);
		}
		i.updateCount(1);
		updateFile();
		return "Successfully added ID: " + id + " with Name: " + name;
	}
	public String removeItem(long id) {
		if(!itemList.containsKey(id)) {
			return "No such ID " + id + " to remove";
		}
		Item i = itemList.get(id);
		i.updateCount(-1);
		tryRemoveItem(i);
		updateFile();
		return "Removed 1 item with ID: " + id;
	}
	public boolean updateItemCount(long id, int cnt) {
		if(!itemList.containsKey(id)) return false;
		Item i = itemList.get(id);
		i.setCount(cnt);
		tryRemoveItem(i);
		updateFile();
		return true;
	}
	public String deleteItem(long id) {
		if(!itemList.containsKey(id)) {
			return "No such ID: " + id;
		}
		return "ID: " + id + " with Name: " + itemList.get(id).getName() + " was deleted.";
	}
	public String getItemInfo(long id) {
		if(itemList.containsKey(id)) {
			return itemList.get(id).toString() + " " + itemList.get(id).getCount();
		}
		return "Error: Could not find any item associated with ID: " + id;
	}
}
