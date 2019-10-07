package egovframework.com.cmm.util.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import egovframework.com.cmm.util.model.Pair;

public class MapUtil {
	
	public static int ASCENDING_ORDER = 1;
	public static int DESCENDING_ORDER = -1;
	
	public static <K, V> List<K> getKeyList(Map<K,V> map){
		Iterator<K> it = map.keySet().iterator();
		List<K> result = new ArrayList<K>();
		while(it.hasNext()){
			result.add(it.next());
		}
		if(result.size() == 0){
			return null;
		}
		it = null;
		return result;
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V>
	sortByValue( Map<K, V> map ,final int order)
	{
		List<Map.Entry<K, V>> list =
				new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()
				{
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{            	
				if(order == ASCENDING_ORDER){
					return (o1.getValue()).compareTo( o2.getValue() );
				}
				else if(order == DESCENDING_ORDER){
					return (o2.getValue()).compareTo( o1.getValue() );
				}				
				return (o2.getValue()).compareTo( o1.getValue() );
			}
				} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		list = null;
		return result;
	}
	
	public static <K, V> List<Pair<K,V>> getListPair(Map<K,V> map){
		Iterator<K> it = map.keySet().iterator();
		List<Pair<K,V>> result = new ArrayList<Pair<K,V>>();
		while(it.hasNext()){
			K key = it.next();
			V value = map.get(key);
			result.add(new Pair<K, V>(key,value));
		}
		it = null;
		return result;
	}
	
	public static <K> void appendKey(Map<K,Integer> map,K key){
		Integer tf = map.get(key);
		if(tf == null){
			tf = 0;
		}
		tf++;
		map.put(key, tf);
	}
}
