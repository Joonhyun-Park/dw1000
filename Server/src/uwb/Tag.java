package uwb;

public class Tag {
	int id;
	int x,y;
	//int z;
	
	public Tag(){
		id = 0;
		x = 0;
		y = 0;
		//z=0;
	}
	
	public void set(int id, double x, double y) {
		this.id = id;
		x = (int) Math.round(x/10);
		y = (int) Math.round(y/10);
		//z = (int) Math.round(z/10);
	}
}
