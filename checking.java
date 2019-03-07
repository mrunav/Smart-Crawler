package smartCrawler.testing;

import java.net.URI;
import java.net.URISyntaxException;

public class checking {
	
	public static String getDomainName(String url)  {
		try{
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain.startsWith("www.") ? domain.substring(4) : domain;
		}catch(Exception e)
		{
			
		}
		 return "";
	}

	public static void main(String[] args)  {
		System.out.println("---- "+getDomainName("crawler"));
		
		/*
		// TODO Auto-generated method stub
String abc="https://en.oxforddictionaries.com/definition/crawler";
String abc1="https://en.oxforddictionaries.com/definition/grammer";

String []arr=abc.split("/");
System.out.println("--- "+arr[2]);

System.out.println("--- "+abc1.split("/")[2]);


if(abc.contains(abc1))
{
System.out.println("contains");	
}
else
{
System.out.println("not");	
}

	*/}

}
