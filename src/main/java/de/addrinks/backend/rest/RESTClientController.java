package de.addrinks.backend.rest;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import de.addrinks.backend.firestore.service.AbonnementService;
import de.addrinks.backend.error.HTTPError;
import de.addrinks.backend.firestore.entities.Abonnement;

@RestController
public class RESTClientController {

	@Autowired
	AbonnementService abonnementService;
	
	
	@RequestMapping("/")
    public String healthCheck() {
	       return "RESTClient is up and running";
	}
	
	  /**
	   * (Optional) App Engine health check endpoint mapping.
	   * @see <a href="https://cloud.google.com/appengine/docs/flexible/java/how-instances-are-managed#health_checking"></a>
	   * If your app does not handle health checks, a HTTP 404 response is interpreted
	   *     as a successful reply.
	   */
	  @RequestMapping("/_ah/health")
	  public String healthy() {
	    // Message body required though ignored
	    return "Still surviving.";
	  }
	
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
	
	//------------ getAbonnement ----------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/abonnement/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAbonnement(@PathVariable("id") String id) {
		//TODO logger.info("Fetching User with id {}", id);
		
		DocumentReference docRef = abonnementService.getAbonnementById(id);
		if(docRef == null){
			//TODO logger.error("User with id {} not found", id);
			return new ResponseEntity(new HTTPError("Abo with id "+id+" not found"), HttpStatus.NOT_FOUND);
		}
		
		Abonnement abo;
		
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document;
		try {
			document = future.get();
			// convert document to POJO
			abo = document.toObject(Abonnement.class);
			return new ResponseEntity<Abonnement>(abo, HttpStatus.OK);
		} catch (InterruptedException e) {
			//TODO logger.error("No such document");
			  return new ResponseEntity(new HTTPError("Abo with id "+id+" not found"), HttpStatus.NOT_FOUND);
		} catch (ExecutionException e) {
			//TODO logger.error("No such document");
			  return new ResponseEntity(new HTTPError("Abo with id "+id+" not found"), HttpStatus.NOT_FOUND);
		}
				
	}
	
}
