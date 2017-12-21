package de.addrinks.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;

import de.addrinks.backend.firestore.service.AbonnementService;
import de.addrinks.backend.firestore.entities.Abonnement;

@RestController
@RequestMapping("/")
public class RESTClientController {

	@Autowired
	AbonnementService abonnementService;
	
    //----------- createAbonnement -------
	/*
	 * {
	 *	"start": "",
	 *	"ende": "123-321-2334",
	 *	"abokategorie": "11",
	 * }
	 */
	@RequestMapping(value = "/abonnement", method = RequestMethod.POST)
	public ResponseEntity<?> createAbonnement(@RequestBody Abonnement abonnement, UriComponentsBuilder uriCB) throws Exception{
        
		ApiFuture<DocumentReference> docRef = abonnementService.createAbonnement(abonnement);
 
        HttpHeaders headers = new HttpHeaders();
        
        headers.setLocation(uriCB.path(docRef.get().getPath()).buildAndExpand(docRef.get().getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
}
