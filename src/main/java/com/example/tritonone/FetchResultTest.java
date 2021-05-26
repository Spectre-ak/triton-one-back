package com.example.tritonone;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;



public class FetchResultTest {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		URL urlObject=new URL("http://hubblesite.org/api/v3/videos/all?page=all");
		URLConnection con = urlObject.openConnection();
		InputStream is = con.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line =  br.readLine();
		System.out.println(line);
		
		JSONArray jsonArray=new JSONArray(line);
	System.out.println(jsonArray.length());
		//System.out.println(jsonArray.toString(3));
		
		Thread t2=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//getImages();
			}
		});
		Thread t1=new Thread(new Runnable() {
				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//getVideos();
			}
		});
		
		t1.start();t2.start();
		
		//merge();
	}
	static void merge() {
		try {
			Scanner scannerim=new Scanner(new File("hubbleImages.json"));
			String txt="";
			while(scannerim.hasNextLine()) {
				txt+=scannerim.nextLine();
			}
			JSONArray imgsArray=new JSONArray(txt);
			txt="";
			Scanner scannervids=new Scanner(new File("hubbleVideos.json"));
			while(scannervids.hasNextLine()) {
				txt+=scannervids.nextLine();
			}
			JSONArray VidsArray=new JSONArray(txt);
			
			for(Object o:VidsArray) {
				imgsArray.put(o);
			}
			
			System.out.println(imgsArray.toString(2));
		} catch (Exception e) {
			e.printStackTrace();		}
	}

	static void getVideos() {
		JSONArray jsonArray=new JSONArray();
		for(int i=1;i<=1400;i++) {
			try {
				URL urlObject=new URL("http://hubblesite.org/api/v3/video/"+i);
				URLConnection con = urlObject.openConnection();
				InputStream is = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line =  br.readLine();
				//System.out.println(line);
				JSONObject jsonObject=new JSONObject(line);
				System.out.println(i+" vids "+jsonObject.length());
				
				jsonArray.put(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("done video......");
		//System.out.println(jsonArray.toString(3));
		try {
			FileWriter fwFileWriter=new FileWriter(new File("hubbleVideos.json"));
			fwFileWriter.write(jsonArray.toString(3));
			fwFileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	static void getImages() {
		JSONArray jsonArray=new JSONArray();
		for(int i=1;i<=5300;i++) {
			try {
				URL urlObject=new URL("http://hubblesite.org/api/v3/image/"+i);
				URLConnection con = urlObject.openConnection();
				InputStream is = con.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String line =  br.readLine();
				//System.out.println(line);
				JSONObject jsonObject=new JSONObject(line);
				System.out.println(i+" imgs "+jsonObject.length());
				
				jsonArray.put(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("done images......");
		//System.out.println(jsonArray.toString(3));
		try {
			FileWriter fwFileWriter=new FileWriter(new File("hubbleImages.json"));
			fwFileWriter.write(jsonArray.toString(3));
			fwFileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
/*
{
"image": "//imgsrc.hubblesite.org/hvi/uploads/video/image_attachment/92/thumb_low_STScI-H-v9421a-1280x720.png",
"mission": "hubble",
"name": "Comet P/Shoemaker-Levy 9 Fragments Slam into Jupiter",
"news_name": "a",
"id": 92,
"collection": "news"
},

   {
      "mission": "hubble",
      "name": "Hubble's Deployment (1990)",
      "id": 4524,
      "collection": "spacecraft"
   }
   
*/