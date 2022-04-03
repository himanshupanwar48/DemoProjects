package mycart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import mycart.model.Order;

public class OrderDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDao(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order model) {
		boolean result = false;
		try {
			query = "insert into orders(p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Order> userOrder(int id) {

		List<Order> list = new ArrayList<>();

		return list;
	}

	public void cancelOrder(int id)
 {
	 try {
		 query="delete form orders where o_id=?";
		 pst= this.con.prepareStatement(query);
		 pst.setInt(1, id);
		 pst.execute();
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
 }
