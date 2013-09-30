package com.lanx.app.lbs.util;

/**
 * <p>pid生成器</p>
 * 
 * @author Ramboo
 * @date 2013-09-27
 */
public class PidGenerator {
	
	/**
	 * 根据位数生成pid字符
	 * 
	 * @param prefix
	 * @param pos 位数，例如pos=10，就生成一个10位的唯一标识，不包含前后缀
	 * @return
	 */
	public static String pid(String prefix,int pos){
		return null;
	}
	
	/**
	 * 根据前缀生成缺省12位的pid字符
	 * 
	 * @param prefix
	 * @return
	 */
	public static String pid(String prefix){
		return pid(prefix,12);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
