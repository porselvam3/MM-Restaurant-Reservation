package io.mmrestaurant.model;

public class GuestReservation {
	private int id;
	private String res_date;
	private String res_time;
	private int no_of_guests;
	private String guest_name;
	private String guest_email;
	private String guest_phone;
	private String ccode;
	private String reserve_status;
	private int mm_dtable_id;
	public String getReserve_status() {
		return reserve_status;
	}
	public void setReserve_status(String reserve_status) {
		this.reserve_status = reserve_status;
	}
	public int getMm_dtable_id() {
		return mm_dtable_id;
	}
	public void setMm_dtable_id(int mm_table_id) {
		this.mm_dtable_id = mm_table_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRes_date() {
		return res_date;
	}
	public void setRes_date(String res_date) {
		this.res_date = res_date;
	}
	public String getRes_time() {
		return res_time;
	}
	public void setRes_time(String res_time) {
		this.res_time = res_time;
	}
	public int getNo_of_guests() {
		return no_of_guests;
	}
	public void setNo_of_guests(int no_of_guests) {
		this.no_of_guests = no_of_guests;
	}
	public String getGuest_name() {
		return guest_name;
	}
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
	public String getGuest_email() {
		return guest_email;
	}
	public void setGuest_email(String guest_email) {
		this.guest_email = guest_email;
	}
	public String getGuest_phone() {
		return guest_phone;
	}
	public void setGuest_phone(String guest_phone) {
		this.guest_phone = guest_phone;
	}
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
}
