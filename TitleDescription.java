package smartCrawler.googleinfo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import smartCrawler.LoginRegister.Dbconn;

public class TitleDescription {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void TitleDescriptionInDb(String Url,
			String SearchingKeyword, int iteration) {
		try {
			if (iteration != 3) {
				int javed = 1;
				ArrayList Urls = new ArrayList();

				String Title = TestGoogle.GetTitle(Url);
				String Decription1 = TestGoogle.GetDescription(Url);
				String Combo = Title + Decription1;
				System.out.println("-----Title---------" + Title);
				System.out.println("-----Description---------" + Decription1);

				RemoveStopwords r = new RemoveStopwords();
				String stopwordless = r.RemoveWords(Combo);
				Stemmer ss = new Stemmer();
				String fname = "D:\\StemmerInput.txt";

				Writer writer = null;
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(fname), "utf-8"));
				writer.append(stopwordless);
				writer.close();
				String Stemmed = ss.GetData(fname);
				// System.out.println("Final Data after Stemmed: - \t"+Stemmed);

				if (Title != null && Decription1 != null) {

					double SimilarityTitle = CosineSimilarity.GetSimilarity(
							Title, SearchingKeyword);
					double SimilarityDesc = CosineSimilarity.GetSimilarity(
							Decription1, SearchingKeyword);
					System.out.println("------+++++++" + SearchingKeyword
							+ "++++---- " + Title + " ----- " + Decription1);
					System.out.println("------+++++++" + SearchingKeyword
							+ "++++---- " + SimilarityTitle + " ----- "
							+ SimilarityDesc);

					if (SimilarityTitle > 0.2 || SimilarityDesc > 0.2) {
						System.out.println("iteration --------"+iteration);
						Dbconn.insertUpdateFromSqlQuery("insert into indexing(searchkeyword,url,title,description,clicking,iteration) values('"
								+ SearchingKeyword
								+ "','"
								+ Url.replaceAll("\\'", "")
								+ "','"
								+ Title.replaceAll("\\'", "")
								+ "','"
								+ Decription1.replaceAll("\\'", "") + "','0','"+iteration+"')");

						Dbconn.insertUpdateFromSqlQuery("insert into formindex(searchkeyword,url,clicking,iteration) values('"
								+ SearchingKeyword
								+ "','"
								+ Url.replaceAll("\\'", "")
								+ "','0','"+iteration+"')");

						
						Dbconn.insertUpdateFromSqlQuery("insert into siteindex(searchkeyword,url,title,description) values('"
								+ SearchingKeyword
								+ "','"
								+ Url.replaceAll("\\'", "")
								+ "','"
								+ Title.replaceAll("\\'", "")
								+ "','"
								+ Decription1.replaceAll("\\'", "") + "')");

						
						
						
						Urls = TestGoogle.GetLinks(Url);

						System.out.println("inside url----------" + Urls.size());
						
						
						
						for (int i = 0; i < Urls.size(); i++) {
							
							try{
								System.out.println("----------- for---------- //"+Urls.get(i).toString()+"===="+Url);
								String urlfirst=getDomainName(Urls.get(i).toString());
								
								System.out.println("----------- urlfirst---------- //"+urlfirst+"====");
							if(urlfirst!="")
							{
								String urlsecond=getDomainName(Url);
								System.out.println("----------- urlsecond---------- //"+urlsecond+"====");
								if(urlsecond!="")
								{
								if(!urlfirst.equals(urlsecond))
								{
									
									System.out.println("----------- match---------- //"+urlfirst+"===="+urlsecond);
								TitleDescriptionInDb(Urls.get(i).toString(),
										SearchingKeyword, iteration+1);
								}
								}
								else
								{
									System.err.println("second not find domain");
								}
							}	
							else
							{
								
								System.out.println("-----------not match---------- //"+urlfirst);
							}
							
							}catch(Exception e)
							{
								System.err.println("error");
								//e.printStackTrace();
							}
						}
						

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getDomainName(String url) throws URISyntaxException {
		try{
		    URI uri = new URI(url);
		    String domain = uri.getHost();
		    return domain.startsWith("www.") ? domain.substring(4) : domain;
			}catch(Exception e)
			{
				System.err.println("error in getname"+url);
			}
			 return "";
	}

}
