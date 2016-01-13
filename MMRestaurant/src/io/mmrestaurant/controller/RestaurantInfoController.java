package io.mmrestaurant.controller;


import javax.ws.rs.Consumes;

import javax.ws.rs.GET;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.WebApplicationException;
import io.mmrestaurant.dao.RestaurantInfoDAO;
import io.mmrestaurant.exception.AppException;
import io.mmrestaurant.model.RestaurantInfo;

	// configure controller to reach jersey
	@Path("/RestaurantInfo")
	public class RestaurantInfoController {

		@GET 
		//url pattern for customized requests

		@Path("/{id}")
		//produces for mediatype to inform JSON

		@Produces(MediaType.APPLICATION_JSON)
		//creating a list object

		public RestaurantInfo getRestaurantInfoById(@PathParam("id") int id){
			RestaurantInfo restaurantInfo = null;
			
			if(id == 0){
				throw new WebApplicationException(Status.BAD_REQUEST);
			}
			try{
				// creating a dao object
				RestaurantInfoDAO dao = new RestaurantInfoDAO();
				// return the data retrieved from dao object to Reservation object
				restaurantInfo = dao.getRestaurantInfoById(id);
			}catch(AppException e){
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			return restaurantInfo;
		}
	/*hi*/
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public RestaurantInfo updateRestaurantInfo(@PathParam("id") int id, RestaurantInfo resinfo){
			
			if(id == 0){
				throw new WebApplicationException(Status.BAD_REQUEST);
			}
			try{
				
				// creating a dao object
				RestaurantInfoDAO dao = new RestaurantInfoDAO();
				// return the data retrieved from dao object to Reservation object
				resinfo = dao.updateRestaurantInfo(id,resinfo);
			}catch(AppException e){
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			return resinfo;
		}	
}
