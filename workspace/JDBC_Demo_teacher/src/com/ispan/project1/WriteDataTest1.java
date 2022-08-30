package com.ispan.project1;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;

import com.ispan.model.AqiDao;
import com.ispan.model.AqiDataBean;

public class WriteDataTest1 {

	public static void main(String[] args) {

		AqiDao dao = new AqiDao();

		try {
			dao.createConnection();
			List<AqiDataBean> dataList = dao.readAllData();
			BufferedWriter bWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("C:\\my_image\\out\\aqi.csv"), "UTF-8"));
			bWriter.write("\uFEFF"); // 決定文件 BOM ()
			bWriter.write("id, 位置, aqi, pm2.5, 空氣品質");
			bWriter.newLine();

			for (AqiDataBean bean : dataList) {
				Integer id = bean.getId();
				String sitename = bean.getSiteName();
				String airStatus = bean.getAirStatus();
				Integer pm25 = bean.getPm25();
				bWriter.write(id + "," + sitename + "," + airStatus + "," + pm25 + "," + airStatus);
				bWriter.newLine();
			}

			System.out.println("Write File Finish!!");
			bWriter.close();
			dao.closeConnection();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
