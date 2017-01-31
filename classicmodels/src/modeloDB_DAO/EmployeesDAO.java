package modeloDB_DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.ConexionSQL;
import modeloDB_DTO.EmployeesDTO;

public class EmployeesDAO implements Patron_DAO <EmployeesDTO> {
	private static final String SQL_INSERT = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM employees WHERE employeeNumber = ?";
	private static final String SQL_UPDATE = "UPDATE employees SET lastName = ?,firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ? ";
	private static final String SQL_FIND = "SELECT * FROM employees WHERE employeeNumber = ?";
	private static final String SQL_FINDALL = "SELECT * FROM employees";
	private static final String SQL_FINDDPTO = "SELECT * FROM employees WHERE "
	
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
	
}
