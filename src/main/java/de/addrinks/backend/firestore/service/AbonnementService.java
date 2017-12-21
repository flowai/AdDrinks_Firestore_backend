package de.addrinks.backend.firestore.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;

import de.addrinks.backend.firestore.FirestoreControllerImpl;
import de.addrinks.backend.firestore.entities.Abonnement;

public class AbonnementService extends FirestoreControllerImpl {
	
	public AbonnementService() {
		super();
	}
	
	public ApiFuture<DocumentReference> createAbonnement(Abonnement abo){
		CollectionReference colRef = this.getCollectionReference(ABONNEMENTTABELLE);
		ApiFuture<DocumentReference> result = colRef.add(abo);
		
		return result;
	}
	
	

}
