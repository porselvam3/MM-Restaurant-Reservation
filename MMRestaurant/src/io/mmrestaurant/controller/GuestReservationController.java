package io.mmrestaurant.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.WebApplicationException;

import io.mmrestaurant.dao.GuestReservationDAO;
import io.mmrestaurant.exception.AppException;
import io.mmrestaurant.model.GuestReservation;



// configure controller to reach jersey
@Path("/GuestReservation")
public class GuestReservationController {

	//annotate to differentiate each methods 
	@GET
	// 
	@Produces(MediaType.APPLICATION_JSON)
	//creating a list object

	//controller calling the DAO
	public List<GuestReservation> getReservations(){
		List<GuestReservation> guestReservations = null;
		try {
			// creating a dao object
			GuestReservationDAO dao = new GuestReservationDAO();
			// return the data retrieved from dao object to Reservation object
			guestReservations = dao.getGuestReservations();
		} catch (AppException e) {
			e.printStackTrace();
			// Error message at client to show SQL exception has occured
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return guestReservations;

	}

	@GET 
	//url pattern for customized requests

	@Path("/{ccode}")
	//produces for mediatype to inform JSON

	@Produces(MediaType.APPLICATION_JSON)
	//creating a list object

	public GuestReservation getReservationByCcode(@PathParam("ccode") String ccode){
		GuestReservation guestReservation = null;
		
		if(ccode == null || ccode.equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		try{
			// creating a dao object
			GuestReservationDAO dao = new GuestReservationDAO();
			// return the data retrieved from dao object to Reservation object
			guestReservation = dao.getGuestReservationByCcode(ccode);
		}catch(AppException e){
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return guestReservation;
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GuestReservation createReservation(GuestReservation res){

		//Code to catch exception 
		if(res.getRes_date() == null || res.getRes_date().equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}if(res.getRes_time() == null || res.getRes_time().equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}if(res.getNo_of_guests() == 0 ){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}if(res.getGuest_name() == null || res.getGuest_name().equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}if(res.getGuest_email() == null || res.getGuest_email().equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}if(res.getGuest_phone() == null || res.getGuest_phone().equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		try{
			// creating a dao object
			GuestReservationDAO dao = new GuestReservationDAO();
			// return the data retrieved from dao object to Reservation object
			res = dao.createGuestReservation(res);
		}catch(AppException e){
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	//localhost:8080/MMRestaurant/api/Reservation/1020
	//{} - request body 
	@PUT
	@Path("/{ccode}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GuestReservation updateReservation(@PathParam("ccode") String ccode, GuestReservation reserve){
		
		if(ccode == null || ccode.equals("")){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
		try{
			// creating a dao object
			GuestReservationDAO dao = new GuestReservationDAO();
			// return the data retrieved from dao object to Reservation object
			reserve = dao.updateGuestReservationById(ccode,reserve);
		}catch(AppException e){
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reserve;
	}
	@DELETE
	@Path("/{ccode}")
	public void deleteReservation(@PathParam("ccode") String ccode){
		try{
			
			if(ccode == null || ccode.equals("")){
				throw new WebApplicationException(Status.BAD_REQUEST);
			}
			// creating a dao object
			GuestReservationDAO dao = new GuestReservationDAO();
			// return the data retrieved from dao object to Reservation object
			dao.deleteReservation(ccode);
		}catch(AppException e){
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	//annotate to differentiate each methods 
			@GET
			// 
			@Produces(MediaType.APPLICATION_JSON)
			//creating a list object
			
			@Path("/OwnerViewReservation")
			//produces for mediatype to inform JSON

			//controller calling the DAO
			public List<GuestReservation> getOwnerViewReservation(){
				List<GuestReservation> OwnerViewReservation = null;
				try {
					// creating a dao object
					GuestReservationDAO dao = new GuestReservationDAO();
					// return the data retrieved from dao object to Reservation object
					OwnerViewReservation = dao.getOwnerViewReservation();
				} catch (AppException e) {
					e.printStackTrace();
					// Error message at client to show SQL exception has occured
					throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
				}

				return OwnerViewReservation;

			}
	//annotate to differentiate each methods 
		@GET
		// 
		@Produces(MediaType.APPLICATION_JSON)
		//creating a list object
		
		@Path("/ContactList")
		//produces for mediatype to inform JSON

		//controller calling the DAO
		public List<GuestReservation> getGuestContactList(){
			List<GuestReservation> GuestContatList = null;
			try {
				// creating a dao object
				GuestReservationDAO dao = new GuestReservationDAO();
				// return the data retrieved from dao object to Reservation object
				GuestContatList = dao.getGuestContactList();
			} catch (AppException e) {
				e.printStackTrace();
				// Error message at client to show SQL exception has occured
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}

			return GuestContatList;

		}
}


