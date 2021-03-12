package it.ayge.planet.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*import it.ayge.framework.AbstractApplication;
import it.ayge.framework.DBHelper;*/
import it.ayge.planet.services.SqlLiteDbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



@Path("/planet")
public class MainPlanet {
		 
		@POST
		@Path("gestorePerCodiceIstat")
		@Consumes({MediaType.TEXT_PLAIN})
		@Produces(MediaType.TEXT_PLAIN) 
	    public String gestorePerCodice(String codiceIstat) {  
			        
			String res= "";
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();
			
			String sql = "SELECT denominazione, rag_soc_gestore FROM dati where codice_istat_comune = ?";
				
			 try (Connection conn = connect.getConection();
		             PreparedStatement pstmt  = conn.prepareStatement(sql)){
		            
		            // set the value
		            pstmt.setString(1, codiceIstat);
		            //
		            ResultSet rs  = pstmt.executeQuery();
		            
		            // loop through the result set
		            while (rs.next()) {
		            	 res = rs.getString("denominazione") +  "\t" + rs.getString("rag_soc_gestore");
		            }
		            System.out.println("Query eseguita con successo.");    
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
			     
			connect.closeConnection();
			     
			return  res; 
	    }	
		
		
		@POST
		@Path("gestorePerComune")
		@Consumes({MediaType.TEXT_PLAIN})
		@Produces(MediaType.TEXT_PLAIN) 
	    public String gestorePerComune(String nomeComune) {  
			        
			String res= "";
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();
			
			String sql = "SELECT codice_istat_comune, rag_soc_gestore FROM dati where denominazione = ?";
				
			 try (Connection conn = connect.getConection();
		             PreparedStatement pstmt  = conn.prepareStatement(sql)){
		            
		            // set the value
		            pstmt.setString(1, nomeComune);
		            //
		            ResultSet rs  = pstmt.executeQuery();
		            
		            // loop through the result set
		            while (rs.next()) {
		            	 res = rs.getString("codice_istat_comune") +  "\t" + rs.getString("rag_soc_gestore");
		            }
		            System.out.println("Query eseguita con successo.");    
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
			     
			connect.closeConnection();
			     
			return  res; 
	    }	
		
		
		
		
			

}
