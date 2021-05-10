package com.common;

import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hanxiaodi on 18/11/4.
 */
public class testmap
{
	public static void main(String[] args){
		HashMap<String, String> header = new HashMap<String, String>();
		HashMap<String,String> end = new HashMap<String,String>();
		header.put( "a","b" );
		header.put( "c","d" );
		if (!header.isEmpty()){
			Set<String> keysets =header.keySet();
			Iterator<String> iterator = keysets.iterator();
			while (iterator.hasNext()){
				String o = iterator.next();
				end.put( iterator.next(),header.get(o));
			}
			Iterator iter = end.entrySet().iterator();
			while (iter.hasNext())
			{
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				System.out.println( key + ":" + value );
			}
		}else {
			System.out.println("hello");
		}


	}
}
