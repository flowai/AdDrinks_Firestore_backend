package de.addrinks.backend.firestore.entities;

import java.sql.Timestamp;

public class Abonnement {
	private Timestamp start;
	private Timestamp ende;
	
	private int abokategorie;
	
	public Abonnement() {
		//Must have Firestore prerequisite
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnde() {
		return ende;
	}

	public void setEnde(Timestamp ende) {
		this.ende = ende;
	}

	public int getAbokategorie() {
		return abokategorie;
	}

	public void setAbokategorie(int abokategorie) {
		this.abokategorie = abokategorie;
	}
}
