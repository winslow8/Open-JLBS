package com.lanx.app.lbs.core;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.lanx.app.lbs.util.LBSConstants.AccuracyValue;

/**
 * <p>表示经纬度需要存储的精度</p>
 * 从比较老旧的网格算法而来，
 * 网格算法可能需要保留3组甚至更多经纬度字段，因此需要用精度来表示
 * 
 * 
 * @author Ramboo
 * @date 2013-08-24
 * */
public enum Accuracy {

	ONE_KM(AccuracyValue.ONE_KM),FIVE_HUNDRED_METER(AccuracyValue.FIVE_HUNDRED_METER),ONE_HUNDRED_METER(AccuracyValue.ONE_HUNDRED_METER),
	OTHER_KM(AccuracyValue.OTHER_KM);
	
	//使用静态的Map把相关的数据与枚举项关联起来
	private static final Map<String,String> resultMap = new HashMap<String,String>(); 
	private static final Map<String,Accuracy> results = new HashMap<String,Accuracy>(); 
	
	//把有的关联项放到Map里
	static{
		for(Accuracy result : EnumSet.allOf(Accuracy.class)){
			resultMap.put(result.getStr(), result.getStr());
			results.put(result.getStr(), result);
		}
	}

	private String str;
	
	Accuracy(String i){
		this.str = i;
	}
	
	public String getStr(){
		return str;
	}
	
	//查找
	public static String getStr(String res){
		return resultMap.get(res);
	}
	
	public static Accuracy getValue(String res){
		return results.get(res);
	}
}
