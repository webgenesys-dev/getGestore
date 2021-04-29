package it.ayge.planet.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.ayge.planet.bean.ComunePerCodice;
import it.ayge.planet.bean.ComuniAtem;
import it.ayge.planet.bean.ComuniGestore;
import it.ayge.planet.bean.GestorePerComune;
import it.ayge.planet.services.SqlLiteDbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



@Path("/planet")
public class MainPlanet {
		 
		@GET
		@Path("ricercaPerCodiceIstat")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces(MediaType.APPLICATION_JSON) 
		public List<ComunePerCodice> gestorePerCodice(@QueryParam(value = "codiceIstat") String codiceIstat) {  
			        
			List<ComunePerCodice> l_cpc = new ArrayList<ComunePerCodice>();
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();

			String sql = "SELECT nome_localita, codice_istat_comune, denominazione_gestore, nome_atem FROM dati where codice_istat_comune LIKE ?";
				
			 try (Connection conn = connect.getConection();
					PreparedStatement pstmt  = conn.prepareStatement(sql)){
		            
		            pstmt.setString(1, "%" + codiceIstat + "%");
		            
		            ResultSet rs  = pstmt.executeQuery();
		          
		       		while (rs.next()) {
		            	
		            	ComunePerCodice cpc = new ComunePerCodice();
		            	cpc.setNome(rs.getString("nome_localita"));
		            	cpc.setCodice_istat(rs.getString("codice_istat_comune"));
		            	cpc.setDenominazione_gestore(rs.getString("denominazione_gestore"));
		            	cpc.setNome_atem(rs.getString("nome_atem"));
		            	
		            	l_cpc.add(cpc);
		            	
		            }
		            System.out.println("Query eseguita con successo.");
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			
			connect.closeConnection();
   
			return  l_cpc; 
	    }	
		
		
		@GET
		@Path("ricercaPerComune")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces(MediaType.APPLICATION_JSON) 
		public List<GestorePerComune> gestorePerComune(@QueryParam(value = "nomeComune") String nomeComune) {  
			        
			List<GestorePerComune> l_gpc = new ArrayList<GestorePerComune>();
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();
			
			String sql = "SELECT nome_localita, codice_istat_comune, denominazione_gestore, nome_atem FROM dati where nome_localita LIKE ?";
				
			 try (Connection conn = connect.getConection();
		            PreparedStatement pstmt  = conn.prepareStatement(sql)){
		            
		            pstmt.setString(1, "%" +nomeComune + "%");
		            
		            ResultSet rs  = pstmt.executeQuery();
		            
		            while (rs.next()) {
		            	 
		            	GestorePerComune gpc = new GestorePerComune();
		            	gpc.setNome(rs.getString("nome_localita"));
		            	gpc.setCodice_istat(rs.getString("codice_istat_comune"));
		            	gpc.setDenominazione_gestore(rs.getString("denominazione_gestore"));
		            	gpc.setNome_atem(rs.getString("nome_atem"));
		            	
		            	l_gpc.add(gpc);
		    		            	
		            }
		            System.out.println("Query eseguita con successo.");
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			connect.closeConnection();
			return  l_gpc; 
	    }	
		
		
		
		@GET
		@Path("ricercaPerGestore")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces(MediaType.APPLICATION_JSON)
		  public List<ComuniGestore> ricercaPerGestore(@QueryParam(value = "gestore") String nomeGestore) {  
			  
			List<ComuniGestore> l_cg = new ArrayList<ComuniGestore>();
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();
			
			String sql = "SELECT nome_localita, codice_istat_comune, denominazione_gestore, nome_atem FROM dati where gestore LIKE ?";
				
			 try (Connection conn = connect.getConection();
		            PreparedStatement pstmt  = conn.prepareStatement(sql)){
		           
				 	pstmt.setString(1, "%"+ nomeGestore + "%");
		            
		            ResultSet rs  = pstmt.executeQuery();
		       
		            while (rs.next()) {
		            
		            	ComuniGestore cg = new ComuniGestore();
		            	cg.setNome(rs.getString("nome_localita"));
		            	cg.setCodice_istat(rs.getString("codice_istat_comune"));
		            	cg.setDenominazione_gestore(rs.getString("denominazione_gestore"));
		            	cg.setNome_atem(rs.getString("nome_atem"));
		            	
		            	l_cg.add(cg);
		            	
		           }
		            System.out.println("Query eseguita con successo.");
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			connect.closeConnection();
		
			return  l_cg; 
	    }	
			
		
		@GET
		@Path("ricercaPerAtem")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces(MediaType.APPLICATION_JSON) 
		  public List<ComuniAtem> ricercaPerAtem(
		    		@DefaultValue("0") @QueryParam("anno") int param_anno,
		            @DefaultValue("-") @QueryParam("nomeAtem") String nomeAtem) {  
			
				
				if (param_anno== 0) {
					param_anno = 2008;
				}  
				
							        
			List<ComuniAtem> l_ca = new ArrayList<ComuniAtem>();
			SqlLiteDbConnection connect = new SqlLiteDbConnection();
			connect.initConnection();
		
			int anno = 0;
			anno = param_anno;
			String sql = "SELECT nome_localita, cod_istat, denominazione_gestore, nome_atem, numero_clienti_"+anno+" FROM dati where nome_atem LIKE ?";
				
			 try (Connection conn = connect.getConection();
		            PreparedStatement pstmt  = conn.prepareStatement(sql)){
		            
		            pstmt.setString(1, "%"+ nomeAtem + "%");

		            ResultSet rs  = pstmt.executeQuery();
		            
		            while (rs.next()) {

		            	ComuniAtem ca = new ComuniAtem();
		            		            	
		            	ca.setNome(rs.getString("nome_localita"));
		            	ca.setCodice_istat(rs.getString("cod_istat"));
		            	ca.setDenominazione_gestore(rs.getString("denominazione_gestore"));
		            	ca.setNome_atem(rs.getString("nome_atem"));
		            	ca.setPdr(rs.getInt("numero_clienti_"+anno+""));
		            	
		            	l_ca.add(ca);
		            	
		           }
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }
			connect.closeConnection();
			return  l_ca; 
	    }	

}
