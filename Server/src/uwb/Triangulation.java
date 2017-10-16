package uwb;
//import java.math.BigDecimal;

import java.util.ArrayList;

public class Triangulation extends Thread {
	public Tag tag;
	protected ArrayList<Integer> list;

	protected double Ax, Ay, Az;
	protected double Bx, By, Bz;
	protected double Cx, Cy, Cz;

	protected double Ca, Cb, Cc;
	protected double Yn, Yd;
	protected double Xn, Xd;

	protected double Mx;
	protected double My;
	// protected double Mz;

	double Da, Db, Dc;

	public Triangulation() {
		Anchor a = new Anchor(0, 0, 0, 0);
		Anchor b = new Anchor(1, 0, 1090, 0);
		Anchor c = new Anchor(2, 1900, 1090, 0);
		shared.Object.anchorList.add(a);
		shared.Object.anchorList.add(b);
		shared.Object.anchorList.add(c);
	}
	
	public void run() {
		while(true) {
			list = shared.Object.getDistance();
			if (list == null || list.size()<3)
				continue;
			Da = shared.Object.distanceList.get(list.get(0));
			Db = shared.Object.distanceList.get(list.get(1));
			Db = shared.Object.distanceList.get(list.get(2));
	
			Ax = (double) shared.Object.anchorList.get(list.get(0)).x;
			Ay = (double) shared.Object.anchorList.get(list.get(0)).y;
			Az = (double) shared.Object.anchorList.get(list.get(0)).z;
	
			Bx = (double) shared.Object.anchorList.get(list.get(1)).x;
			By = (double) shared.Object.anchorList.get(list.get(1)).y;
			Bz = (double) shared.Object.anchorList.get(list.get(1)).z;
	
			Cx = (double) shared.Object.anchorList.get(list.get(2)).x;
			Cy = (double) shared.Object.anchorList.get(list.get(2)).y;
			Cz = (double) shared.Object.anchorList.get(list.get(2)).z;
	
			Ca = Math.pow(Ax, 2) + Math.pow(Ay, 2) + Math.pow(Da, 2);
			Cb = Math.pow(Bx, 2) + Math.pow(By, 2) + Math.pow(Db, 2);
			Cc = Math.pow(Cx, 2) + Math.pow(Cy, 2) + Math.pow(Dc, 2);
	
			Yn = ((Cx - Ax) * (Ca - Cb)) + ((Bx - Ax) * (Cc - Ca));
			Yd = 2 * (((Bx - Ax) * (Cy - Ay)) - ((Cx - Ax) * (By - Ay)));
			My = (double) Yn / Yd;
			Xn = Cb - Ca - (2 * (By - Ay) * My);
			Xd = 2 * (Bx - Ax);
			Mx = (double) Xn / Xd;
			// Mz=Math.sqrt(Math.abs(Math.pow(Ad, 2)-Math.pow(Mx-Ax,2)-Math.pow(My-Ay, 2)));
			tag.set(0, Mx, My);
			System.out.println("x:" + tag.x + " y:" + tag.y);
		}
	}
}
