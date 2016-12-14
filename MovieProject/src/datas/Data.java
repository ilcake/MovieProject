package datas;

import java.io.Serializable;

public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int REGISTER = 1;
	public static final int LOGIN = 2;
	public static final int LOGOUT = 3;
	public static final int GETMOVIEBOXINFO = 4;
	public static final int GETCOMMENT = 5;
	public static final int WRITECOMMENT = 6;
	public static final int GETMYCOMMENT = 7;

	public static final int RG_IDDUB = 1;
	public static final int RG_SUCCESS = 10;

	public static final int FAIL = -1;

	public static final int CHATLOGIN = 22;
	public static final int CHATLOGOUT = 33;
	public static final int CHATMESSAGE = 44;
	public static final int CHATUSERLIST = 55;

	public static final int ICONCHANGE = 85;

	public static final int USERLIKE = 99;
	public static final int GETUSERLIKE_BY_CD = 16;
	public static final int GETUSERLIKE_BY_ID = 17;

	public static final int USERLIKETHIS = 666;

	public static final int GETAVGGRADE = 232;

}
