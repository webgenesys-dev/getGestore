package it.ayge.planet.rest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.ayge.planet.bean.ComuniAtemUT;
import it.ayge.planet.services.SqlLiteDbConnection;

	

@Path("/planet")
public class calcoloUnaTantum {
	@GET
	@Path("calcoloUnaTantum")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON) 
	
    public List<ComuniAtemUT> calcoloUT(
    		@DefaultValue("0") @QueryParam("anno") int param_anno,
            @DefaultValue("-") @QueryParam("nomeAtem") String nomeAtem) {  
	
			final double pdr_min = 17802;
			final double pdr_max = 1311604;
			final double loc_min = 1;
			final double loc_max = 147;
			double QA = 0;
			double QC = 0;
			double qaEuroPdr = 0;
        	double qcEuroPdr = 0;
			double qaComune = 0;
			double qcComune = 0;
		
		if (param_anno== 0) {
			param_anno = 2008;
		}
				
		List<ComuniAtemUT> l_caut = new ArrayList<ComuniAtemUT>();
		
		SqlLiteDbConnection connect = new SqlLiteDbConnection();
		connect.initConnection();
		int anno = 0;
		anno = param_anno;
		String sql = "SELECT nome_localita, cod_istat, provincia, denominazione_gestore, nome_atem, numero_clienti_"+anno+" FROM dati where nome_atem LIKE ?";
		
		 try (Connection conn = connect.getConection();
	             PreparedStatement pstmt  = conn.prepareStatement(sql)){
	            
	            pstmt.setString(1, "%" + nomeAtem + "%");
	            
	            ResultSet rs  = pstmt.executeQuery();
	            double pdr = getNumeroPdrLoc(nomeAtem, "SUM(numero_clienti_"+anno+")");
            	double loc = getNumeroPdrLoc(nomeAtem, "COUNT(id)");
            	
            	QA = (1+0.4*(pdr_max - pdr)/(pdr_max - pdr_min))*pdr;         	
            	QC = (4-0.6*(loc_max - loc)/(loc_max - loc_min))*pdr;
            	
            	if ((QA+QC)>600000.0) {
            		QA = 120000.0;         	
                	QC = 480000.0;
            	}
            	
            	qaEuroPdr = QA/pdr;
            	qcEuroPdr = QC/pdr;
            	
         	            	
            		while (rs.next()) {
            			
            		qaComune = qaEuroPdr*rs.getInt("numero_clienti_"+anno+"");
                    qcComune = qcEuroPdr*rs.getInt("numero_clienti_"+anno+"");	
            			
            		ComuniAtemUT caut = new ComuniAtemUT();
	            	caut.setNome(rs.getString("nome_localita"));
	            	caut.setCod_istat(rs.getString("cod_istat"));
	            	caut.setProvincia(rs.getString("provincia"));
	            	caut.setPdr(rs.getInt("numero_clienti_"+anno+""));
	            	caut.setDenominazione_gestore(rs.getString("denominazione_gestore"));
	            	caut.setNome_atem(rs.getString("nome_atem"));
	            	caut.setQa_comune(qaComune);
	            	caut.setQc_comune(qcComune);
	            	caut.setTotale(qaComune+qcComune);
	            	           	
	            	l_caut.add(caut);
	         	            	
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		connect.closeConnection();
		
		return  l_caut; 
    }	
	
	
	private int getNumeroPdrLoc(String atem, String aggregazione){
		
		int pdrloc = 0;
		
		SqlLiteDbConnection connect = new SqlLiteDbConnection();
		connect.initConnection();
			
		String nomeAtem = atem;
		
		String sql = "SELECT "+aggregazione+" AS totale FROM dati where nome_atem = ?";
		 try (Connection conn = connect.getConection();
	             PreparedStatement pstmt  = conn.prepareStatement(sql)){
	            
	            pstmt.setString(1, nomeAtem);
	            
	            ResultSet rs  = pstmt.executeQuery();
	            
	            if (rs.next()) {
	            	pdrloc = rs.getInt("totale");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		connect.closeConnection();
		
		return pdrloc;
	}
	
	public int getNumeroLoc(int anno)
	{
		int loc = 0;
		
		
		return loc;
	}
	
	
	
	
}
