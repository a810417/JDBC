package com.ispan.project1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ispan.model.AqiDao;

public class GetDataTest1 {
	
	
	public List<String> getAqiData() throws Exception{
		String aqiUrl = "https://data.epa.gov.tw/api/v2/aqx_p_432?api_key=e8dd42e6-9b8b-43f8-991e-b3dee723a52d&limit=1000&sort=ImportDate%20desc&format=csv";
		
		List<String> listData = new ArrayList<String>();
		
		URL url = new URL(aqiUrl);
		
		InputStream ips = url.openStream();
		
		InputStreamReader ipsReader = new InputStreamReader(ips, "UTF-8");
		BufferedReader bReader = new BufferedReader(ipsReader);
		
		String content = "";
		
		bReader.readLine(); // read first line 
		
		while(bReader.ready()) {
			content = bReader.readLine();  // start read second line
			
			listData.add(content);
		}
		
		
//		for(String oneRow : listData) {
//			System.out.println("OneRow: " + oneRow);
//		}
		
		return listData;
		
	}
	

	public static void main(String[] args) {
		GetDataTest1 demoProject = new GetDataTest1();
		AqiDao dao = new AqiDao();
		
		try {
			List<String> listData = demoProject.getAqiData();
			
			// testing 1 data
//			String[] tokens = listData.get(5).split(",");
//			System.out.println("===== 一筆資料 =====");
//			System.out.println(tokens[0]);
//			System.out.println(tokens[2]);
//			System.out.println(tokens[5]);
			
			
			
			
			dao.createConnection();
			
			
			for(int i = 0; i < listData.size(); i++) {
				String[] tokens = listData.get(i).split(",");
				
				// deal with blank integer data
				if(tokens[2].equals("")) {
					tokens[2] = "0";
				}
				
				if(tokens[10].equals("")) {
					tokens[10] = "0";
				}
				
				System.out.println("正要輸入: " + tokens[0] + tokens[2] + tokens[10] + tokens[4]);
				
				dao.insertData(tokens[0], Integer.parseInt(tokens[2]) , Integer.parseInt(tokens[10]), tokens[4]);
			}
			
			
			System.out.println("成功輸入完成");
			
			dao.closeConnection();
			
		} catch (Exception e) {
			try {
				dao.closeConnection();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
