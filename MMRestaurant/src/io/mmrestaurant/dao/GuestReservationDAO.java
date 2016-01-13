package io.mmrestaurant.dao;

import io.mmrestaurant.exception.AppException;
import io.mmrestaurant.model.GuestReservation;
import io.mmrestaurant.util.DBUtil;

import java.util.ArrayList;
import java.util.List;
//import java.util.UUID;
import java.util.UUID;

import javax.ws.rs.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//data access object 
public class GuestReservationDAO {

	public List<GuestReservation> getGuestReservations() throws AppException{

		List<GuestReservation> reservations = new ArrayList<GuestReservation>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;

		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mm_guest_reservedetails");

			rs = ps.executeQuery();

			while(rs.next()){
				GuestReservation res = new GuestReservation();
				res.setId(rs.getInt("id"));
				res.setRes_date(rs.getString("res_date"));
				res.setRes_time(rs.getString("res_time"));
				res.setNo_of_guests(rs.getInt("no_of_guests"));
				res.setGuest_name(rs.getString("guest_name"));
				res.setGuest_email(rs.getString("guest_email"));
				res.setGuest_phone(rs.getString("guest_phone"));
				res.setCcode(rs.getString("ccode"));
				res.setReserve_status(rs.getString("reserve_status"));
				res.setMm_dtable_id(rs.getInt("mm_dtable_id"));
				reservations.add(res);
			}


		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return reservations;
	}

	public GuestReservation getGuestReservationByCcode(String ccode) throws AppException, NotFoundException {
		GuestReservation res = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mm_guest_reservedetails where ccode=?");
			ps.setString(1, ccode);
			rs = ps.executeQuery();

			if(rs.next()){
				res = new GuestReservation();
				res.setId(rs.getInt("id"));
				res.setRes_date(rs.getString("res_date"));
				res.setRes_time(rs.getString("res_time"));
				res.setNo_of_guests(rs.getInt("no_of_guests"));
				res.setGuest_name(rs.getString("guest_name"));
				res.setGuest_email(rs.getString("guest_email"));
				res.setGuest_phone(rs.getString("guest_phone"));
				res.setCcode(rs.getString("ccode"));
				res.setReserve_status(rs.getString("reserve_status"));
				res.setMm_dtable_id(rs.getInt("mm_dtable_id"));

			}
			else{
				throw new NotFoundException("Reservation details not found for the provided reservation id");
			}


		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return res;


	}

	public GuestReservation createGuestReservation(GuestReservation res) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1=null;

		int idTemp1 = 0;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String uuid = UUID.randomUUID().toString();
		String randid = uuid.substring(1, 6);

		try{

			ps = con.prepareStatement("SELECT MIN(mm_dining_table.id) FROM mmrestaurant_db.mm_dining_table where mm_dining_table.id NOT IN (SELECT mm_dtable_id FROM mmrestaurant_db.mm_guest_reservedetails WHERE res_date =? AND res_time =? AND reserve_status =?)");

			ps.setString(2, res.getRes_time());
			ps.setString(1, res.getRes_date());
			ps.setString(3, "Confirmed");

			rs = ps.executeQuery();
			while(rs.next()){

				idTemp1 = rs.getInt(1);
				ps1 = con.prepareStatement("INSERT INTO mmrestaurant_db.mm_guest_reservedetails (res_date,res_time,no_of_guests,guest_name,guest_email,guest_phone,ccode,reserve_status,mm_dtable_id) VALUES (?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
				ps1.setString(1, res.getRes_date());
				ps1.setString(2, res.getRes_time());
				ps1.setInt(3, res.getNo_of_guests());
				ps1.setString(4, res.getGuest_name());
				ps1.setString(5, res.getGuest_email()); 
				ps1.setString(6, res.getGuest_phone());
				ps1.setString(7, randid);
				if(rs.wasNull()== false){ 
					ps1.setString(8,"Confirmed");
					ps1.setInt(9, idTemp1);
				}
				else{
					ps1.setString(8,"Waiting");	
					ps1.setInt(9, 0);
				}

				//			ps1.setString(7, res.getCcode());

				ps1.executeUpdate();

				// generated key for id 
				rs1 = ps1.getGeneratedKeys();
				// setting id value to column  from generated key result set
				if(rs1.next()){
					res.setId(rs1.getInt(1));
				}
				else{
					throw new NotFoundException("Reservation details not found for the provided reservation id");
				}
			}
		}
		catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
				if(ps1 != null){
					ps.close();
				}
				if(rs1 != null){
					rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return res;

	}

	public GuestReservation updateGuestReservationById(String ccode, GuestReservation reserve) throws AppException, NotFoundException{
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		int idTemp1 = 0;
		ResultSet rs = null;
		ResultSet rs1 = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mm_guest_reservedetails where ccode=?");
			ps.setString(1, ccode);
			rs = ps.executeQuery();

			if(rs.next()){
				ps1 = con.prepareStatement("SELECT MIN(mm_dining_table.id) FROM mmrestaurant_db.mm_dining_table where mm_dining_table.id NOT IN (SELECT mm_dtable_id FROM mmrestaurant_db.mm_guest_reservedetails WHERE res_date =? AND res_time =? AND reserve_status =?)");

				ps1.setString(2, reserve.getRes_time());
				ps1.setString(1, reserve.getRes_date());
				ps1.setString(3, "Confirmed");

				rs1 = ps1.executeQuery();

				while(rs1.next()){


					ps1 = con.prepareStatement("UPDATE mmrestaurant_db.mm_guest_reservedetails SET res_date =?,res_time=?,no_of_guests =?,guest_name =?,guest_email =?,guest_phone =?,ccode =?,reserve_status =?,mm_dtable_id =? WHERE ccode =?");
					idTemp1 = rs1.getInt(1);
					ps1.setString(1, reserve.getRes_date());
					ps1.setString(2, reserve.getRes_time());
					ps1.setInt(3, reserve.getNo_of_guests());
					ps1.setString(4, reserve.getGuest_name());
					ps1.setString(5, reserve.getGuest_email()); 
					ps1.setString(6, reserve.getGuest_phone());
					ps1.setString(7, ccode);
					ps1.setString(10, ccode);
					if(rs1.wasNull()== false){ 
						ps1.setString(8,"Confirmed");
						ps1.setInt(9, idTemp1);
					}
					else{
						ps1.setString(8,"Waiting");	
						ps1.setInt(9, 0);
					}

					//			ps1.setString(7, res.getCcode());

					ps1.executeUpdate();	

				}

			}
			else{
				throw new NotFoundException("Reservation details not found for the provided reservation id");
			}

		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return reserve;


	}

	public void deleteReservation(String ccode) throws AppException {
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mm_guest_reservedetails where ccode=?");
			ps.setString(1, ccode);
			rs = ps.executeQuery();

			if(rs.next()){
				ps1 = con.prepareStatement("DELETE FROM mmrestaurant_db.mm_guest_reservedetails WHERE ccode =?");
				ps1.setString(1, ccode);

				ps1.executeUpdate();

			}
			else{
				throw new NotFoundException("Reservation details not found for the provided reservation id");
			}


		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}

	public List<GuestReservation> getGuestContactList() throws AppException{
		List<GuestReservation> guestContactList = new ArrayList<GuestReservation>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;

		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT DISTINCT guest_name,guest_email,guest_phone FROM  mmrestaurant_db.mm_guest_reservedetails");

			rs = ps.executeQuery();

			while(rs.next()){
				GuestReservation res = new GuestReservation();
				res.setGuest_name(rs.getString("guest_name"));
				res.setGuest_email(rs.getString("guest_email"));
				res.setGuest_phone(rs.getString("guest_phone"));
				guestContactList.add(res);
			}


		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return guestContactList;
	}

	public List<GuestReservation> getOwnerViewReservation() throws AppException {
		List<GuestReservation> OwnerViewReservation = new ArrayList<GuestReservation>();
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;

		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT mm_dtable_id,res_time,no_of_guests,ccode,reserve_status FROM mmrestaurant_db.mm_guest_reservedetails;");

			rs = ps.executeQuery();

			while(rs.next()){
				GuestReservation res = new GuestReservation();
				res.setMm_dtable_id(rs.getInt("mm_dtable_id"));
				res.setRes_time(rs.getString("res_time"));
				res.setNo_of_guests(rs.getInt("no_of_guests"));
				res.setCcode(rs.getString("ccode"));
				res.setReserve_status(rs.getString("reserve_status"));
				OwnerViewReservation.add(res);
			}


		}catch(SQLException e){
			e.printStackTrace();

			throw new AppException(e.getMessage());
		}

		finally{

			try {
				if(ps != null){
					ps.close();
				}
				if(rs != null){
					rs.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return OwnerViewReservation;
	}

}
