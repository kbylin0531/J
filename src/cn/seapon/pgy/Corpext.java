package cn.seapon.pgy;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.CorpExtListlabelgroupsRequest;
import com.dingtalk.api.response.CorpExtListlabelgroupsResponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.taobao.api.ApiException;

/**
 * 外部联系人
 * 
 * @author Lin
 */
public class Corpext {

	public static final String SUITE_KEY = "suitef1qbslmh0lgskpu1";
	public static final String SUITE_SECRET = "e1_mEzGwBZ_MeTkBCOCR1odfrKLvhVAHbeQhqU-mcqKOEHcdT9u6G6jkZV6h-i1f";
	public static final String SUITE_TICKET = "suitef1qbslmh0lgskpu1";

	public static void main(String[] args) throws ApiException {
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动！");
		} catch (ClassNotFoundException e1) {
			System.out.println("找不到MySQL驱动!");
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://120.55.163.154:10010/pgyxwd"; // JDBC的URL
		// 调用DriverManager对象的getConnection()方法，获得一个Connection对象
		Connection conn;
		try {
			conn = (Connection) DriverManager.getConnection(url, "lin", "pgyxwd8888");
			// 创建一个Statement对象
			Statement stmt = (Statement) conn.createStatement(); // 创建Statement对象
			System.out.print("成功连接到数据库！");
			try {
				String sql = "select * from users"; // 要执行的SQL
				ResultSet rs = stmt.executeQuery(sql);// 创建数据对象
				System.out.println("编号" + "\t" + "姓名" + "\t" + "年龄");
				while (rs.next()) {
					System.out.print(rs.getString(1) + "\t");
					System.out.print(rs.getString(2) + "\t");
					System.out.print(rs.getString(3) + "\t");
					System.out.println();
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static String getAccessToken() {

		return "";
	}

	/**
	 * dingtalk.corp.ext.listlabelgroups (标签列表) 环境 HTTP请求地址 HTTPS请求地址 正式环境
	 * http://gw.api.taobao.com/router/rest https://eco.taobao.com/router/rest
	 * 沙箱环境 http://gw.api.tbsandbox.com/router/rest
	 * https://gw.api.tbsandbox.com/router/rest
	 * 
	 * @throws ApiException
	 */
	public static void listLabelGroups() throws ApiException {
		String url = "https://gw.api.tbsandbox.com/router/rest";
		DingTalkClient client = new DefaultDingTalkClient(url);
		CorpExtListlabelgroupsRequest req = new CorpExtListlabelgroupsRequest();
		req.setSize(20L);
		req.setOffset(0L);
		CorpExtListlabelgroupsResponse rsp = client.execute(req, "suitef1qbslmh0lgskpu1");
		System.out.println(rsp.getBody());
	}
}
