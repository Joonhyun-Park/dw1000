package uwb;
import java.sql.*;

public class DatabaseHandler {
	protected Connection conn;
	private final String DBurl = "jdbc:mysql://localhost:3306/uwb?autoReconnect=true&useSSL=false";
	private final String DBuser = "root";
	private final String DBpassword = "dhltjs12";
	
	public DatabaseHandler(){
		connect();
	}
	
	public void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DBurl, DBuser, DBpassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement createStatement(String sql, int[] fields){
		PreparedStatement prest = null;
		try {
			if(conn.isClosed()||conn==null){
				connect();
			}
			prest = conn.preparedStatement(sql);
			for(int i=0; i<fields.length;i++){
				prest.setInt(i+1, fields[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
		return prest;
	}
	
	public ResultSet executeQuery(String sql, int[] fields)
	{
		ResultSet rs = null; 
		try {
			rs = createStatement(sql, fields).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/*
	public PreparedStatement createStatement(String sql, String[] fields){
		PreparedStatement prest = null;
		try {
			if((conn.isClosed())||(conn==null)){
				connect();
			}
			prest = conn.prepareStatement(sql);
			for(int i=0;i<fields.length;i++){
				prest.setString(i+1, fields[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prest;
	}
	
	public ResultSet executeQuery(String sql, String[] fields)
	{
		ResultSet rs = null; 
		try {
			rs = createStatement(sql, fields).executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet executeUpdateLocaiton(int tagID, int x, int y){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update UWB set location=? where id=?";
		 
		try{
			if(conn.isClosed()||conn==null){
				connect();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(0, tagID);
			pstmt.setString(1, "GeomFromText('POINT("+x+" "+y+")'");
		} catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean updateSample(String data){
		PreparedStatement pstmt = null;
		String sql="update test set data=?, time=NOW() where id=1;";
		try{
			if(conn.isClosed()||conn==null){
				connect();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	*/
	public int executeUpdate(String sql, int[] val){
		int res = 0;
		try {
			res = createStatement(sql, val).executeUpdate();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean isConnected(){
		boolean res = false;
		if(conn!=null){
			res = true;
		}
		return res;
	}
	
	public void close(){
		if(isConnected()){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
