package io.mmrestaurant.model;

public class RestaurantInfo {
	private int id;
	private String rest_name;
	private String rest_contact;
	private String rest_email;
	private String rest_open_time;
	private String rest_close_time;
	private String rest_open_days;
	private String rest_close_days;
	private boolean auto_assign;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRest_name() {
		return rest_name;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	public String getRest_contact() {
		return rest_contact;
	}
	public void setRest_contact(String rest_contact) {
		this.rest_contact = rest_contact;
	}
	public String getRest_email() {
		return rest_email;
	}
	public void setRest_email(String rest_email) {
		this.rest_email = rest_email;
	}
	public String getRest_open_time() {
		return rest_open_time;
	}
	public void setRest_open_time(String rest_open_time) {
		this.rest_open_time = rest_open_time;
	}
	public String getRest_close_time() {
		return rest_close_time;
	}
	public void setRest_close_time(String rest_close_time) {
		this.rest_close_time = rest_close_time;
	}
	public String getRest_open_days() {
		return rest_open_days;
	}
	public void setRest_open_days(String rest_open_days) {
		this.rest_open_days = rest_open_days;
	}
	public String getRest_close_days() {
		return rest_close_days;
	}
	public void setRest_close_days(String rest_close_days) {
		this.rest_close_days = rest_close_days;
	}
	public boolean isAuto_assign() {
		return auto_assign;
	}
	public void setAuto_assign(boolean auto_assign) {
		this.auto_assign = auto_assign;
	}
}
