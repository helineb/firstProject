package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.jdbc.*;

public class PersonneDAO {

	private String insertRqt = "INSERT INTO personne (nom, prenom) values";
	private final String updateRqt = "UPDATE personne SET nom=?, prenom=? WHERE idPersonne=?";
	private final String deleteRqt = "DELETE FROM personne WHERE idPersonne=?";
	private final String deleteAssoRqt = "DELETE FROM assoEquipePersonne WHERE idPersonne=?";
	private final String selectByIdRqt = "SELECT * FROM personne WHERE idPersonne=?";
	private final String selectAllRqt = "SELECT * FROM personne";
	
	
	public int insert(Personne data) throws Exception {
		Statement rqt = null;
		int id = 0;
		try {
			rqt = PoolConnection.getConnection().createStatement();
			this.insertRqt = this.insertRqt + " ('" + data.getNom() + "','" + data.getPrenom() + "')";
			rqt.executeUpdate(this.insertRqt,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = rqt.getGeneratedKeys();
			while(rs.next()){
				id = rs.getInt(1);
			}
			return id;
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt!=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
	}
	
	
	public void update(Personne data) throws Exception {
		PreparedStatement rqt = null;
		try {
			rqt = PoolConnection.getConnection().prepareStatement(this.updateRqt);
			rqt.setString(1, data.getNom());
			rqt.setString(2, data.getPrenom());
			rqt.setInt(3, data.getId());
			rqt.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt!=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
		
	}

	
	public void delete(Personne data) throws Exception {
		PreparedStatement rqt = null;
		PreparedStatement rqtAsso = null;
		try {
			rqtAsso = PoolConnection.getConnection().prepareStatement(this.deleteAssoRqt);
			rqtAsso.setInt(1, data.getId());
			rqtAsso.executeUpdate();
			rqt = PoolConnection.getConnection().prepareStatement(this.deleteRqt);
			rqt.setInt(1, data.getId());
			rqt.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt!=null)
				rqt.close();
			if(rqtAsso!=null)
				rqtAsso.close();
			PoolConnection.closeConnection();
		}
	}

	
	public Personne selectById(int id) throws Exception {
		Personne p = null;
		PreparedStatement rqt = null;
		try {
			rqt = PoolConnection.getConnection().prepareStatement(this.selectByIdRqt);
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			while(rs.next()){
				p = new Personne(rs.getInt("idPersonne"),rs.getString("nom"), rs.getString("prenom"),0);
			}
			return p;
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt!=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
	}

	
	public ArrayList<Personne> selectAll() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Personne> listPers = new ArrayList<Personne>();
		Statement rqt = null;
		try {
			rqt = PoolConnection.getConnection().createStatement();
			ResultSet rs = rqt.executeQuery(this.selectAllRqt);
			while(rs.next()){
				listPers.add(new Personne(rs.getInt("idPersonne"),rs.getString("nom"), rs.getString("prenom"),0));
			}
			return listPers;
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt!=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
	}
	
	
	
}
