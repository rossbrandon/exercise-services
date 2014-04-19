package com.pluralsight.repository;

import java.util.ArrayList;
import java.util.List;

import com.pluralsight.model.Activity;
import com.pluralsight.model.ActivitySearch;
import com.pluralsight.model.User;

public class ActivityRepositoryStub implements ActivityRespository {
	
	@Override
	public void create(Activity activity) {
		//should issue an insert to the db
		System.out.println("Inserting Activity to DB...");
	}
	
	@Override
	public Activity update(Activity activity) {
		//search the db to see if we have an activity with that ID
		System.out.println("Updating Activity ID: " + activity.getId() + " in DB...");
		
		return activity;
	}
	
	@Override
	public void delete(String activityId) {
		//delete activity from db
		System.out.println("Deleting Activity ID: " + activityId + " from DB...");		
	}
	
	public List<Activity> findAllActivities() {
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		
		Activity activity2 = new Activity();
		activity2.setDescription("Cycling");
		activity2.setDuration(120);
		
		activities.add(activity1);
		activities.add(activity2);
		
		return activities;
	}

	@Override
	public Activity findActivity(String activityId) {
		if(activityId.equals("7777")) {
			return null;
		}
		
		Activity activity1 = new Activity();
		User user = new User();
		
		user.setId("5678");
		user.setName("Ross");
		
		activity1.setId("1234");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);
		activity1.setUser(user);
		
		return activity1;
	}

	@Override
	public List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo) {
		//select descriptions from db by description and duration range
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("Swimming");
		activity.setDuration(25);
		
		activities.add(activity);
		
		return activities;
	}

	@Override
	public List<Activity> findByConstraints(ActivitySearch search) {
		System.out.println("Searching From Duration: " + search.getDurationFrom());
		System.out.println("Searching To Duration: " + search.getDurationTo());
		System.out.println("Searching By Type: " + search.getSearchType());
		
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity = new Activity();
		activity.setId("2345");
		activity.setDescription("Swimming");
		activity.setDuration(25);
		
		activities.add(activity);
		
		return activities;
	}

}
