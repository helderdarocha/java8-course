package br.com.argonavis.java.threads.nolocks;

import java.util.ArrayList;
import java.util.Collection;

public class Order {
	private Collection<ServiceItem> items = new ArrayList<>();
	
	public Collection<ServiceItem> getItems() {
		return items;
	}
	public void addItem(ServiceItem item) {
		items.add(item);
	}
	
}
