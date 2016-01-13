package io.mmrestaurant.dao;

import io.mmrestaurant.exception.AppException;
import io.mmrestaurant.util.DBUtil;
import javax.ws.rs.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.mmrestaurant.model.RestaurantInfo;

public class RestaurantInfoDAO  {

	public RestaurantInfo getRestaurantInfoById(int id) throws AppException, NotFoundException{
		RestaurantInfo res = null;
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mm_restaurant_info where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()){
				res = new RestaurantInfo();
				res.setId(rs.getInt("id"));
				res.setRest_name(rs.getString("rest_name"));
				res.setRest_contact(rs.getString("rest_contact"));
				res.setRest_email(rs.getString("rest_email"));
				res.setRest_open_time(rs.getString("rest_open_time"));
				res.setRest_close_time(rs.getString("rest_close_time"));
				res.setRest_open_days(rs.getString("rest_open_days"));
				res.setRest_close_days(rs.getString("rest_close_days"));
				res.setAuto_assign(rs.getBoolean("auto_assign"));

			}
			else{
				throw new NotFoundException("Restaurant info not found for the provided restaurant info id");
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

	public RestaurantInfo updateRestaurantInfo(int id, RestaurantInfo resinfo) throws AppException, NotFoundException{
		Connection con = DBUtil.connect();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;


		try{
			ps = con.prepareStatement("SELECT * FROM mmrestaurant_db.mm_restaurant_info where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(rs.next()){
				ps1 = con.prepareStatement("UPDATE mmrestaurant_db.mm_restaurant_info SET rest_name =?,rest_contact =?,rest_email =?,rest_open_time =?,rest_close_time =?,rest_open_days =?, rest_close_days =?, auto_assign =? WHERE id =?");
				ps1.setString(1, resinfo.getRest_name());
				ps1.setString(2, resinfo.getRest_contact());
				ps1.setString(3, resinfo.getRest_email());
				ps1.setString(4, resinfo.getRest_open_time());
				ps1.setString(5, resinfo.getRest_close_time()); 
				ps1.setString(6, resinfo.getRest_open_days());
				ps1.setString(7, resinfo.getRest_close_days());
				ps1.setBoolean(8, resinfo.isAuto_assign());
				ps1.setInt(9, id);

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
		return resinfo;


	}

}
