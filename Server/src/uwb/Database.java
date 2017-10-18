package uwb;

public class Database extends DatabaseHandler{
	public boolean installTable(){
		String sql = "create table if not exists tag (`id` int(3) unsigned NOT NULL AUTO_INCREMENT, `location` point DEFAULT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		String[] fields = {};
		executeUpdate(sql, fields);
		return true;
	}
	
	public boolean updateTagLog(int id, int x, int y){
		String sql = "insert into ? values(x, y, time) ";
		
		return true;
	}
}
