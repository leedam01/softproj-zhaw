package addresses;

import java.util.HashSet;
import java.util.Set;

public class Group {

	private String name;
	private String description;
	private Set<Contact> contacts;

	public Group(String name) {
		this(name, "");
	}

	public Group(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.contacts = new HashSet<Contact>();
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
}
