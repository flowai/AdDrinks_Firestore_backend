package de.addrinks.backend.firestore.entities;

import java.util.Date;


public class Abonnement {
	private Date start;
	private Date ende;
	
	private int abokategorie;
	
	public Abonnement() {
		//Must have Firestore prerequisite
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnde() {
		return ende;
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	public int getAbokategorie() {
		return abokategorie;
	}

	public void setAbokategorie(int abokategorie) {
		this.abokategorie = abokategorie;
	}
}
