package com.pluralsight;

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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.pluralsight.model.Activity;
import com.pluralsight.model.User;
import com.pluralsight.repository.ActivityRepositoryStub;
import com.pluralsight.repository.ActivityRespository;

@Path("activities") // /exercise-services/webapi/activities
public class ActivityResource {

	private ActivityRespository activityRepository = new ActivityRepositoryStub();
	
	@POST
	@Path("activity") // /exercise-services/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity create(Activity activity) {
		System.out.println("Received Activity Description: " + activity.getDescription());
		System.out.println("Received Activity Duraction: " + activity.getDuration());
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	@PUT
	@Path("{activityId}") // /exercise-services/webapi/activities/3456
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response update(Activity activity) {
		System.out.println("About to Update Activity ID: " + activity.getId());
		System.out.println("Received Activity Description: " + activity.getDescription());
		System.out.println("Received Activity Duraction: " + activity.getDuration());
		
		activityRepository.update(activity);
		
		return Response.ok().entity(activity).build();
	}
	
	@DELETE
	@Path("{activityId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response delete(@PathParam("activityId") String activityId) {
		System.out.println("About to Delete Activity ID: " + activityId);
		
		activityRepository.delete(activityId);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("activity") // /exercise-services/webapi/activities/activity
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
		System.out.println("Received Activity Description: " + formParams.getFirst("description"));
		System.out.println("Received Activity Duraction: " + formParams.getFirst("duration"));
		
		Activity activity = new Activity();
		activity.setDescription(formParams.getFirst("description"));
		activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
		
		activityRepository.create(activity);
		
		return activity;
	}
	
	@GET // /exercise-services/webapi/activities
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}") // /exercise-services/webapi/activities/1234
	public Response getActivity(@PathParam("activityId") String activityId) {
		if(activityId == null || activityId.length() < 4) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		Activity activity = activityRepository.findActivity(activityId);
		
		if(activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(activity).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{activityId}/user") // /exercise-services/webapi/activities/1234/user
	public User getActivityUser(@PathParam("activityId") String activityId) {
		return activityRepository.findActivity(activityId).getUser();
	}
	
}
