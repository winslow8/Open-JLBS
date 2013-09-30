package com.lanx.app.lbs.util;

/**
 * <p>LBS的常量类</p>
 * 
 * @author Ramboo
 * @date 2013-09-09
 * */
public class LBSConstants {
	public static final class Domain{
		public static String serialVersionUID = "serialVersionUID"; 
	}
	
	public static final class AccuracyValue{
		public static String ONE_KM = "3"; //范围，半径为1公里
		public static String FIVE_HUNDRED_METER = "2"; //范围，半径为500米
		public static String ONE_HUNDRED_METER = "1"; //范围，半径为100米
		
		public static String OTHER_KM = "0"; //超出范围的选项
	}
	
	public static final class Pagination{
		public static int DEFAULT_PAGE_SIZE = 10;
		public static int DEFAULT_MAX_COUNT = 500;//缺省最大数
		public static int DEFAULT_MIN_COUNT = 3;//缺省最大数
	}
	
	public static final class Params{
		public static String UID = "uid";
		public static String DEVICE_ID = "deviceId";		
	}
	
	public static enum ID{
		ID,
		UID,
		DEVICE_ID,
		PID,
		ALL,//上面几种标识都有		
	}
	
	//排序的分类
	public enum SortType {
		ID_DESC,
		ID_ASC,
		CHECKIN_TIME_DESC,
		CHECKIN_TIME_ASC,
		LOC_TIME_DESC,
		LOC_TIME_ASC,
		NONE,//不排序
	}
	
	public enum PoiType {
		BANK,
		FOOD,
		EDU,
		ENTERTAINMENT,		
		UGC,
		UGC_VIRTUAL,
		NONE,//不分类
	}
	
//	public static final class Operator {
//		public static String EQ = "";
//		public static String LIKE = "";
//		public static String GT = "";
//		public static String LT = "";
//		public static String GTE = "";
//		public static String LTE = "";
		
		//CRUD操作类型
		public enum OperType {
			CREATE_TABLE,
			SELECT,
			DELETE,
			TRUNCATE,
			UPDATE,
			INSERT,
			SAVE,
		}		
//	}
	//查找附近poi或者查找附近checkin数据	
	public enum NearType{
		POI,
		CHECKIN,
	}

	public enum LocType{
		NEAR_LONG_LAT,
		NEAR_PID,
	}
	
	public static final class CacheKeyPrefix{
		public static final String DEFAULT_LOCATION_UID_NAME = "lbs-location-uid";
		public static final String DEFAULT_LOCATION_DEVICE_NAME = "lbs-location-device";
		public static final String DEFAULT_LOCATION_UID_DEVICE_NAME = "lbs-location-uid-device";
				
		public static final String DEFAULT_LIST_APPEND = "-list";
		
		public static final String DEFAULT_CHECKIN_NAME = "lbs-checkin";
		public static final String DEFAULT_NEIGHBOR_NAME = "lbs-neighbor";
		public static final String DEFAULT_UGC_NAME = "lbs-poi";
		
		//下面2个是查找附近checkin数据，附近uid数据的cachekey
		public static final String NEAR_CHECKIN_LONGLAT = "nearcheckin-longlat-";
		public static final String NEAR_CHECKIN_PID = "nearcheckin-pid-";
		
		//下面2个是查找附近poi数据的cachekey
		public static final String NEAR_POI_LONGLAT = "nearpoi-longlat-";
		public static final String NEAR_POI_PID = "nearpoi-pid-";
		
		public static final String POI_UNIQUE = "poi-unique-";//poi_unique_KeyPrefix
	}
	
	public static final class Regex{
		public static final String WILDCARD = "*";
		public static final String LINE = "-";
	}	
	
}
