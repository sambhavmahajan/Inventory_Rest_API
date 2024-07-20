package com.sambhavmahajan.restapi;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class ItemController {
	private final Item_Service Iservice = new Item_Service();
	@GetMapping("/fetch/{id}")
	public String fetch(@PathVariable("id") long id) {
		return Iservice.getItemInfo(id);
	}
	@PostMapping("/add/{id}/{name}")
	public String add(@PathVariable("id") long id, @PathVariable("name") String name) {
		return Iservice.addItem(id, name);
	}
	@PostMapping("/remove/{id}")
	public String remove(@PathVariable("id") long id) {
		return Iservice.removeItem(id);
	}
	@GetMapping("/getall")
	public HashMap<Long, Item> all() {
		return Iservice.itemList;
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		return Iservice.deleteItem(id);
	}
}
