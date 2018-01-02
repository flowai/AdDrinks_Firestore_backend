package de.addrinks.backend.firestore.service;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import de.addrinks.backend.firestore.FirestoreControllerImpl;
import de.addrinks.backend.firestore.entities.Bestellung;

@Service
public class BestellungService extends FirestoreControllerImpl {
	
	public BestellungService(){
		super();
	}

	public ApiFuture<DocumentReference> createBestellung(Bestellung bestellung) {
		CollectionReference colRef = this.getCollectionReference(BESTELLUNGENTABELLE);
		ApiFuture<DocumentReference> result = colRef.add(bestellung);
		
		return result;
	}

	public DocumentReference getBestellungById(String id) {
		return this.getCollectionReference(BESTELLUNGENTABELLE).document(id);
	}

	public CollectionReference getBestellungen(){
		return this.getCollectionReference(BESTELLUNGENTABELLE);
	}
}
