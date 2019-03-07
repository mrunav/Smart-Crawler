package smartCrawler.googleinfo;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nisha
 */


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
 
public class TestGoogle {
	 String[] p = new String[15];
	 String[] p1 = new String[15];
	 String[] p2 = new String[10];
	 public ArrayList objectarray =new ArrayList();
	 
	 public static ArrayList GetLinks(String URL ) {  
    	 System.out.println("\nURL : " +URL);
    	ArrayList ar=new ArrayList();
        try{
        Document doc = Jsoup.connect(URL).get();  
        Elements links = doc.select("a[href]");  
        for (Element link : links) {  
        	if(!link.attr("href").equals("")&&! link.text().equals(""))
        	{
        		
        	
            ar.add(link.text());
            ar.add(link.attr("href"));
           // System.out.println("\nlink : " + link.attr("href"));  
           // System.out.println("text : " + link.text());
        	}
        }  
        }catch(Exception e)
        {
        	System.err.println("Get Link");
        	//e.printStackTrace();
        }
        return ar;
}  
	 
     public String Getkeyword(String URL ) {
    	 String keywords =null;
    	 try{
            Document doc = Jsoup.connect(URL).get();  
             keywords = doc.select("meta[name=keywords]").first().attr("content");  
         //   System.out.println("Meta keyword : " + keywords);
    	 }
    	 catch(Exception e)
    	 {
    		 System.err.println("Get Keyword Function");
    		// e.printStackTrace();
    	 }
            return keywords;
}  

      public static String GetDescription (String URL ){
    	  String description=null;
    	  try{
            Document doc = Jsoup.connect(URL).get();  
             description = doc.select("meta[name=description]").get(0).attr("content");  
          //  System.out.println("Meta description : " + description);
    	  }catch(Exception e)
    	  {
    		  System.err.println("Get Discriptionfunction");
    		 // e.printStackTrace();
    	  }
            return description;
}  
      
       public static String GetTitle(String URL) {
    	   String title=null;
    	   try{
                Document doc = Jsoup.connect(URL).get();  
                 title = doc.title();  
               // System.out.println("title is: " + title);
    	   }catch(Exception e)
    	   {
    		   System.err.println("Get title function");
    		  // e.printStackTrace();
    	   }
                return title ;
    }

	 
	 
	 
	public static void main(String[] args) throws IOException {
		
		String data="";
		
		ArrayList URLans=new ArrayList();
		TestGoogle googleSea=new TestGoogle();
		
		
		  
		    
		
		URLans=googleSea.googleurls("aaa");
		/*TestGoogle test=new TestGoogle();
		for(int i=0;i<URLans.size();i++)
		{
			data=URLans.get(i).toString();
			System.out.println("---------Arraylist ----- "+data);
		
		String[] data1=data.split("#");
		String Title=data1[0];
		String Description=GetDescription(data1[1]);
		System.out.println("------------- "+Description);
		String Combo=Title+" "+Description;
		RemoveStopwords r = new RemoveStopwords();
        String stopwordless = r.RemoveWords(Combo);
		 Stemmer ss = new Stemmer();
		 String fname="D:\\StemmerInput.txt";
       
          Writer writer = null;
          writer = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(fname), "utf-8"));
          writer.append(stopwordless);
          writer.close();
          String Stemmed=ss.GetData(fname);
          System.out.println("Final Data after Stemmed: - \t"+Stemmed);
      
		
		
		}
	*/	 
		
			}
	
	public ArrayList googleurls(String searchword)
	{
		String google = "http://www.google.com/search?q=";
		String search =searchword;
		String charset = "UTF-8";
		String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

		int pp=0;
		Elements links;
		try {
			links = Jsoup.connect(google + URLEncoder.encode(search, charset)).userAgent(userAgent).get().select(".g>.r>a");
		

		for (Element link : links) {
		    String title = link.text();
		    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

		    if (!url.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
		    objectarray.add(title+"#"+url);
		    p[pp]=url;
		       p1[pp]=title;
		       pp++;
		    System.out.println("Title: " + title);
		    System.out.println("URL: " + url);
		}
		
		/*System.out.println("URL............1: " + p[0]);
	       System.out.println("URL............2: " + p[1]);
	       System.out.println("URL............3: " + p[2]);
	       System.out.println("URL............4: " + p[3]);
	       System.out.println("URL............5: " + p[4]);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectarray;
	}
}
 



 
