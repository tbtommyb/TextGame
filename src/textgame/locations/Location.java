package textgame.locations;

import java.util.*;
import javax.xml.bind.annotation.*;

import textgame.game.Outputter;
import textgame.items.*;

@XmlRootElement
public class Location {
	private String id;

	private String name;
	private String description;
	private List<Exit> exits;
	private List<Item> items;

	// Blank constructor
	public Location() {
		// Blanks by default
		id = new String();
		name = new String();
		description = new String();
		exits = new ArrayList<Exit>();
		items = new ArrayList<Item>();
	}

	// Partial constructor
	public Location(String id, String locationName) {
		this.id = id;
		name = locationName;
		description = new String();
		exits = new ArrayList<Exit>();
		items = new ArrayList<Item>();
	}

	// Full constructor
	public Location(String id, String locationName, String locationDesc) {
		this.id = id;
		name = locationName;
		description = locationDesc;
		exits = new ArrayList<Exit>();
		items = new ArrayList<Item>();
	}

	public String toString() {
		return name;
	}

	@XmlElement(name = "exit")
	public void setExit(Exit exit) {
		exits.add(exit);
	}

	public void removeExit(Exit exit) {
		if (exits.contains(exit)) {
			exits.remove(exit);
		}
	}

	public List<Exit> getExits() {
		return (List<Exit>) ((ArrayList<Exit>) exits).clone();
	}

	@XmlElement(name = "item")
	public void setItem(Item item) {
		items.add(item);
	}
	
	public Item getItem(Item item) {
		return getItem(item.getName());
	}

	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public List<Item> getItems() {
		return (List<Item>) ((ArrayList<Item>) items).clone();
	}
	
	public void removeItem(Item item) {
		if (items.contains(item)) {
			items.remove(item);
		}
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String locationName) {
		name = locationName;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String locationDesc) {
		description = locationDesc;
	}

	public String getId() {
		return id;
	}

	@XmlID
	@XmlAttribute
	public void setId(String newId) {
		id = newId;
	}
	
	public void show() {
		Outputter.writeln(getDescription());
		Outputter.writeln("========");
		Outputter.write("Available exits: ");
		for (Exit exit : getExits()) {
			Outputter.write(exit + " ");
		}
		Outputter.writeln("\n========");
		Outputter.write("Available items: ");
		for (Item item : getItems()) {
			Outputter.write(item + " ");
		}
		Outputter.writeln("\n========");
	}
}
