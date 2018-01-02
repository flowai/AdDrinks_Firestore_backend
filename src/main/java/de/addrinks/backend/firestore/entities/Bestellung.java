package de.addrinks.backend.firestore.entities;

import java.util.Map;

public class Bestellung {
	//TODO Maybe UUIDs
	private String NutzerId;

	private Map<String, Integer> ProduktIds;

	private String Status;
	
	public Bestellung(){
		//Must have Firestore prerequisite
	}

	public String getNutzerId() {
		return NutzerId;
	}

	public void setNutzerId(String nutzerId) {
		NutzerId = nutzerId;
	}

	public Map<String, Integer> getProduktId() {
		return ProduktIds;
	}

	public void setProduktId(Map<String, Integer> produktIds) {
		ProduktIds = produktIds;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
}
