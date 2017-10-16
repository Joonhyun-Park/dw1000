package shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import uwb.Anchor;
import uwb.Triangulation;

public class Object {
	public static ArrayList<Anchor> anchorList = new ArrayList<Anchor>();
	public static HashMap<Integer, Double> distanceList = new HashMap<Integer, Double>();
		
	public static void put(int key, double value) {
		distanceList.put(key, value);
	}
	public static ArrayList<Integer> getDistance(){
		if(distanceList.size()<4)
			return null;
		
		ArrayList<Integer> distance = new ArrayList<Integer>();
		Iterator it = sortByValue(distanceList).iterator();
		
		if(it.hasNext()) {
			distance.add((Integer) it.next());
			distance.add((Integer) it.next());
			distance.add((Integer) it.next());
		}
		return distance;
	}
	private static List<Integer> sortByValue(HashMap<Integer, Double> map){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer n1, Integer n2) {
				Double v1 = map.get(n1);
				Double v2 = map.get(n2);
				
				return v2.compareTo(v1);
			}
		});
		//Collections.reverse(list);
		return list;
	}
}
