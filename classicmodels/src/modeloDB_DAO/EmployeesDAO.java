package modeloDB_DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.ResultSetRow;

import conexion.ConexionSQL;

import modeloDB_DTO.EmployeesDTO;

public class EmployeesDAO implements Patron_DAO <EmployeesDTO> {
	private static final String SQL_INSERT = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM employees WHERE employeeNumber = ?";
	private static final String SQL_UPDATE = "UPDATE employees SET lastName = ?,firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ? ";
	private static final String SQL_FIND = "SELECT * FROM employees WHERE employeeNumber = ?";
	private static final String SQL_FINDALL = "SELECT * FROM employees";
	private static final String SQL_FINDOFI = "SELECT * FROM employees WHERE officeCode = ? ";
	private static final String SQL_FINDCAT = "SELECT * FROM employees WHERE jobTitle LIKE ? ";
	private static final String SQL_FINDEMP = "SELECT COUNT(*) FROM employees WHERE officeCode = ? ";
	private static final String SQL_SUMAMO = "SELECT sum(amount) FROM payments WHERE customerNumber in(SELECT customerNumber FROM customers WHERE salesRepEmployeeNumber in(SELECT employeeNumber FROM employees WHERE officeCode = ?))";
	private ConexionSQL con = ConexionSQL.getInstance();
	
	public boolean insertar(EmployeesDTO dto){
		PreparedStatement ps = null;
		try{
			ps =con.getCon().prepareStatement(SQL_INSERT);
			ps.setInt(1, dto.getEmployeeNumber());
			ps.setString(2, dto.getLastName());
			ps.setString(3, dto.getFirstName());
			ps.setString(4, dto.getExtension());
			ps.setString(5, dto.getEmail());
			ps.setString(6, dto.getOfficeCode());
			ps.setInt(7, dto.getReportsTo());
			ps.setString(8, dto.getJobTitle());
			
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
	public boolean actualizar(EmployeesDTO t){
		PreparedStatement ps = null;
		try{
			ps = con.getCon().prepareStatement(SQL_UPDATE);
			
			ps.setString(1, t.getLastName());
			ps.setString(2, t.getFirstName());
			ps.setString(3, t.getExtension());
			ps.setString(4, t.getEmail());
			ps.setString(5, t.getOfficeCode());
			ps.setInt(6, t.getReportsTo());
			ps.setString(7, t.getJobTitle());
			ps.setInt(8, t.getEmployeeNumber());
			
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
	
	public EmployeesDTO buscar(Object pk){
		EmployeesDTO Empe = null;
		try{
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FIND);
			ps.setInt(1,(int)pk);
			ResultSet rs = ps.executeQuery();
			if (rs.next()==true){
				Empe = new EmployeesDTO(rs.getInt("employeeNumber"), rs.getString("lastName"), rs.getString("firstName"), rs.getString("extension"), rs.getString("email") , rs.getString("officeCode") ,rs.getInt("reportsTo") ,rs.getString("jobTitle"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Empe;
	}
	
	public ArrayList<EmployeesDTO> listarTodos() {
		ArrayList<EmployeesDTO> lista = new ArrayList<EmployeesDTO>();
		try {
			
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDALL);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EmployeesDTO emp = new EmployeesDTO(rs.getInt("employeeNumber"), rs.getString("lastName"),
						rs.getString("firstName"), rs.getString("extension"), rs.getString("email"),
						rs.getString("officeCode"), rs.getInt("reportsTo"), rs.getString("jobTitle"));
				lista.add(emp);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public ArrayList<EmployeesDTO> listarTodosOfi(String ofi){
		ArrayList<EmployeesDTO> lista = new ArrayList<EmployeesDTO>();
		try{
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDOFI);
			ps.setString(1, ofi);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				EmployeesDTO emp = new EmployeesDTO(rs.getInt("employeeNumber"), rs.getString("lastName"), rs.getString("firstName"), rs.getString("extension"), rs.getString("email") , rs.getString("officeCode") ,rs.getInt("reportsTo") ,rs.getString("jobTitle"));
				lista.add(emp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public ArrayList<EmployeesDTO> listarTodosCat(String cat){
		ArrayList<EmployeesDTO> lista = new ArrayList<EmployeesDTO>();
		try{
			
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDCAT);
			ps.setString(1, cat);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				EmployeesDTO emp = new EmployeesDTO(rs.getInt("employeeNumber"), rs.getString("lastName"), rs.getString("firstName"), rs.getString("extension"), rs.getString("email") , rs.getString("officeCode") ,rs.getInt("reportsTo") ,rs.getString("jobTitle"));
				lista.add(emp);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public int NumeroEmpleados(String ofi){
		
		int num = 0;
		try{
			PreparedStatement ps = con.getCon().prepareStatement(SQL_FINDEMP);
			ps.setString(1, ofi);
			ResultSet rs = ps.executeQuery();		
			 while (rs.next()){
                 num = rs.getInt(1);
             }

	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	public double CalcularRecaudacionOficina(String ofi){
		double num = 0;
		try{
			PreparedStatement ps = con.getCon().prepareStatement(SQL_SUMAMO);
			ps.setString(1, ofi);
			ResultSet rs = ps.executeQuery();		
			 while (rs.next()){
                 num = rs.getDouble(1);
             }


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
		
	}
			
	
}
