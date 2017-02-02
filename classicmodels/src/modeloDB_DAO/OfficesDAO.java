package modeloDB_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionSQL;
import modeloDB_DTO.OfficesDTO;

public class OfficesDAO implements Patron_DAO <OfficesDTO>{
	
	private static final String SQL_INSERT = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM employees WHERE employeeNumber = ?";
	private static final String SQL_UPDATE = "UPDATE employees SET lastName = ?,firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ? ";
	private static final String SQL_FIND = "SELECT * FROM employees WHERE employeeNumber = ?";
	private static final String SQL_FINDALL = "SELECT * FROM offices";

	private ConexionSQL con = ConexionSQL.getInstance();
	
	public boolean insertar(OfficesDTO dto){
		PreparedStatement ps = null;
		try{
			ps =con.getCon().prepareStatement(SQL_INSERT);
			ps.setString(1, dto.getOfficeCode());
			ps.setString(2, dto.getCity());
			ps.setString(3, dto.getPhone());
			ps.setString(4, dto.getAddressLine1());
			ps.setString(5, dto.getAddressLine2());
			ps.setString(6, dto.getState());
			ps.setString(7, dto.getCountry());
			ps.setString(8, dto.getPostalCode());
			ps.setString(9, dto.getTerritory());
			
			if(ps.executeUpdate()>0) return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if (ps!=null) ps.close();
			}catch (Exception e2) {
				e2.printStackTrace();
				
			}
		}
		return false;
	}
	public boolean borrar(Object pk){
		PreparedStatement ps = null;
		try{
			ps = con.getCon().prepareStatement(SQL_DELETE);
			ps.setInt(1, (int)pk);
			
			if (ps.executeUpdate()>0) return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps!=null) ps.close();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		}
		return false;
		
	}
	public boolean actualizar(OfficesDTO t){
		PreparedStatement ps = null;
		try{
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			ps.setString(1, t.getOfficeCode());
			ps.setString(2, t.getCity());
			ps.setString(3, t.getPhone());
			ps.setString(4, t.getAddressLine1());
			ps.setString(5, t.getAddressLine2());
			ps.setString(6, t.getState());
			ps.setString(7, t.getCountry());
			ps.setString(8, t.getPostalCode());
			ps.setString(9, t.getTerritory());
			
			if (ps.executeUpdate()>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps!=null) ps.close();
			} catch (Exception e4) {
				e4.printStackTrace();
			}
		}
		return false;
	}
	public OfficesDTO buscar(Object pk){
		OfficesDTO Offi = null;
		try{
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			
			ps.setString(1,(String)pk);
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				Offi = new OfficesDTO(rs.getString("officeCode"), rs.getString("city"), rs.getString("phone"), rs.getString("addressLine1"), rs.getString("addressLine2") , rs.getString("state") ,rs.getString("country") ,rs.getString("postalCode"),rs.getString("territory"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Offi;
	}
	public ArrayList<OfficesDTO> listarTodos() {
		ArrayList<OfficesDTO> lista = new ArrayList<OfficesDTO>();
		try {
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OfficesDTO Offi = new OfficesDTO(rs.getString("officeCode"), rs.getString("city"), rs.getString("phone"), rs.getString("addressLine1"), rs.getString("addressLine2") , rs.getString("state") ,rs.getString("country") ,rs.getString("postalCode"),rs.getString("territory"));
				lista.add(Offi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
