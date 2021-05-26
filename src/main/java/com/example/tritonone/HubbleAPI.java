package com.example.tritonone;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class HubbleAPI {
	String im="",vi="";
	@GetMapping("/hubble/images")
	
	public String hubbleImgs() {if(!im.equals(""))return im;
		try {
			URL urlObject=new URL("http://hubblesite.org/api/v3/images/all?page=all");
			URLConnection con = urlObject.openConnection();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line =  br.readLine();
			//System.out.println(line);
			im=line;
			return line;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@GetMapping("/hubble/videos")
	public Object hubbleVids() {if(!vi.equals(""))return vi;
		try {
			URL urlObject=new URL("http://hubblesite.org/api/v3/videos/all?page=all");
			URLConnection con = urlObject.openConnection();
			InputStream is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line =  br.readLine();
			//System.out.println(line);
			vi=line;
			return line;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@GetMapping("/hubble/video/{id}")
	public Object getVideoById(@PathVariable String id) {
		try {
			URL urlObject=new URL("http://hubblesite.org/api/v3/video/"+id);
			URLConnection con = urlObject.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line =  br.readLine();
			//System.out.println(line);
			
			return line;
		} catch (Exception e) {
			return null;
		}
		
	}
	@GetMapping("/hubble/image/{id}")
	public Object getImgById(@PathVariable String id) {
		try {
			URL urlObject=new URL("http://hubblesite.org/api/v3/image/"+id);
			URLConnection con = urlObject.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line =  br.readLine();
			//System.out.println(line);
			
			return line;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}
