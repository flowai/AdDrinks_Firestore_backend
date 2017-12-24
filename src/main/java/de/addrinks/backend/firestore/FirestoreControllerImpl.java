package de.addrinks.backend.firestore;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirestoreControllerImpl implements DatabaseController {
	// Use the application default credentials
	GoogleCredentials credentials;
	FirebaseOptions options;
	Firestore db;
	
	public FirestoreControllerImpl() {
		try {
			init();
		} catch (IOException e) {
			//TODO Handle Exception
			e.printStackTrace();
		}
	}
	
	private void init() throws IOException{
		credentials = GoogleCredentials.getApplicationDefault();
		
		options = new FirebaseOptions.Builder()
			    .setCredentials(credentials)
			    .setProjectId(PROJECTID)
			    .build();
		
		FirebaseApp.initializeApp(options);
		
		db = FirestoreClient.getFirestore();
	}
	 
	public CollectionReference getCollectionReference(String collectionName){
		CollectionReference collection = db.collection(collectionName);
		
		return collection;
	}
	
	public DocumentReference getDocumentReference(CollectionReference collection, String documentName){
		DocumentReference document = collection.document(documentName);
		return document;
	}
	
	public DocumentReference getDocumentReferenceSubCollection(CollectionReference collection, String documentName, String subCollection, String subDocumentName){
		DocumentReference subDocument = collection.document(documentName).collection(subCollection).document(subDocumentName);
		return subDocument;
	}
}
