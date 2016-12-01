package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.jdbc.PoolConnection;

public class EquipeDAO {

	private String insertRqt = "INSERT INTO equipe (nomEquipe,idLeader) VALUES (?,?)";
	private final String insertAssoRqt = "INSERT INTO assoEquipePersonne (idEquipe,idPersonne) VALUES (?,?)";
	private final String updateRqt = "UPDATE equipe SET nomEquipe=?, idLeader=? WHERE idEquipe=?";
	private final String deleteRqt = "DELETE FROM equipe WHERE idEquipe=?";
	private final String deleteAssoRqt = "DELETE FROM assoEquipePersonne WHERE idEquipe=?";
	private final String selectByIdRqt = "SELECT e.idEquipe, nomEquipe, idLeader, pLead.nom as nomLeader, pLead.prenom as prenomLeader, aep.idPersonne, p.nom as nom, p.prenom as prenom FROM equipe e LEFT JOIN assoEquipePersonne aep ON aep.idEquipe=e.idEquipe " 
			+ "LEFT JOIN personne p ON p.idPersonne=aep.idPersonne INNER JOIN personne pLead ON pLead.idPersonne = idLeader WHERE e.idEquipe=?";
	private final String selectAllRqt = "SELECT idEquipe, nomEquipe, idLeader, pLead.nom as nomLeader, pLead.prenom as prenomLeader FROM equipe "
									+ "INNER JOIN personne pLead ON pLead.idPersonne = idLeader";
	private final String selectAllDetailRqt = "SELECT e.idEquipe,nomEquipe, idLeader, pLead.nom as nomLeader, pLead.prenom as prenomLeader, aep.idPersonne, p.nom, p.prenom  FROM equipe e" 
											+ "LEFT JOIN assoEquipePersonne aep ON aep.idEquipe=e.idEquipe" 
											+ "LEFT JOIN personne p ON p.idPersonne=aep.idPersonne"
											+ "INNER JOIN personne pLead ON pLead.idPersonne=e.idLeader"
											+ "WHERE e.idEquipe=11";
	

	
	public void insert(Equipe data) throws Exception {
		Statement rqt = null;
		PreparedStatement rqtAsso = null;
		try {
			//inertion de l'equipe
			rqt = PoolConnection.getConnection().createStatement();
			this.insertRqt = "INSERT INTO equipe (nomEquipe,idLeader) VALUES ('" + data.getNom() + "'," + data.getLeader().getId() + ")";
			rqt.executeUpdate(this.insertRqt,Statement.RETURN_GENERATED_KEYS);
			//Récupération de la primary key générée
			ResultSet rs = rqt.getGeneratedKeys();
			while(rs.next()){
				data.setId(rs.getInt(1));
			}
			//insertion de l'association des personnes dans cette équipe
			rqtAsso = PoolConnection.getConnection().prepareStatement(this.insertAssoRqt);
			rqtAsso.setInt(1, data.getId());
			for(Personne p : data.getEmployes()){
				rqtAsso.setInt(2,p.getId());
				rqtAsso.executeUpdate();
			}
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
	public void update(Equipe data) throws Exception {
		this.updateEquipe(data);
		this.deleteAsso(data);
		this.insertAsso(data);
	}
	
	public void updateEquipe(Equipe data) throws Exception {
		PreparedStatement rqt = null;
		try {
			rqt = PoolConnection.getConnection().prepareStatement(this.updateRqt);
			rqt.setString(1, data.getNom());
			rqt.setInt(2, data.getLeader().getId());
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
	
	public void deleteAsso(Equipe data) throws Exception {
		PreparedStatement rqtAsso = null;
		try {
			rqtAsso = PoolConnection.getConnection().prepareStatement(this.deleteAssoRqt);
			rqtAsso.setInt(1, data.getId());
			rqtAsso.executeUpdate();
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqtAsso!=null)
				rqtAsso.close();
			PoolConnection.closeConnection();
		}
	}
	
	public void insertAsso(Equipe data) throws Exception {
		PreparedStatement rqtAsso = null;
		try {
			rqtAsso = PoolConnection.getConnection().prepareStatement(this.insertAssoRqt);
			rqtAsso.setInt(1, data.getId());
			for(Personne p : data.getEmployes()){
				rqtAsso.setInt(2,p.getId());
				rqtAsso.executeUpdate();
			}
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqtAsso!=null)
				rqtAsso.close();
			PoolConnection.closeConnection();
		}
	}

	public void delete(Equipe data) throws Exception {
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

	public Equipe selectById(int id) throws Exception {
		PreparedStatement rqt = null;
		Equipe eq = null;
		try{
			rqt = PoolConnection.getConnection().prepareStatement(this.selectByIdRqt);
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			ArrayList<Personne> listPersonne = new ArrayList<Personne>();
			Personne leader = null;
			while(rs.next()){
				if(rs.getInt("idPersonne") != 0){
					Personne p = new Personne(rs.getInt("idPersonne"),rs.getString("nom"), rs.getString("prenom"),0);
					listPersonne.add(p);
				}
				
				if(leader == null){
					leader = new Personne(rs.getInt("idLeader"), rs.getString("nomLeader"), rs.getString("prenomLeader"),0);
					if(eq == null)
						eq = new Equipe(rs.getInt("idEquipe"), rs.getString("nomEquipe"), leader);
				}
			}
			if(eq != null)
				eq.setEmployes(listPersonne);
			
			return eq;
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt !=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
	}

	public ArrayList<Equipe> selectAll() throws Exception {
		Statement rqt = null;
		try{
			rqt = PoolConnection.getConnection().createStatement();
			ResultSet rs = rqt.executeQuery(this.selectAllRqt);
			ArrayList<Equipe> listEquipe = new ArrayList<Equipe>();
			while(rs.next()){
				Personne leader = new Personne(rs.getInt("idLeader"), rs.getString("nomLeader"), rs.getString("prenomLeader"),0);
				Equipe eq = new Equipe(rs.getInt("idEquipe"), rs.getString("nomEquipe"), leader);
				listEquipe.add(eq);
			}
			
			return listEquipe;
		}
		catch(Exception e){
			throw e;
		}
		finally {
			if(rqt !=null)
				rqt.close();
			PoolConnection.closeConnection();
		}
	}

}
