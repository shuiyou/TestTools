package com.controller.wgs;

/**
 * Created by hanxiaodi on 18/11/26.
 */
public class tes
{
	public  static void main(String[] args){
		int[] a ={1,3,7,5,2,11,10,18};
		int size = a.length;
		for (int i =0;i<size-1;i++){
			for(int j = 0;j<size-1-i;j++){
				if(a[j]>a[j+1]){
					int temp =a[j];
					a[j] = a[j+1];
					a[j+1]=temp;

				}
			}
		}
		for(int i =0;i<size;i++){
			System.out.print( a[i]);
		}


	}
}
