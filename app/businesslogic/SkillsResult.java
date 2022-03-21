package businesslogic;

import org.checkerframework.checker.units.qual.K;
import play.mvc.*;

import models.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * This method is used to take the skills from the Freelanc api
 * @author Rahul Patel
 */
public class SkillsResult {

    List<projects> ar = new ArrayList<projects>();

    /**
     * This method is used to get JSON data from the freelance api
     * @param ApiData
     * @return
     */
    public List<projects> getJSONData(String ApiData){
        double fleschIndex=0;
        double fleschKincaid=0;
        FleschReadabilty fleschReadabilty=new FleschReadabilty();
        WordStats wordStats=new WordStats();
        JSONObject json=new JSONObject(ApiData);
        JSONObject dataResult = json.getJSONObject("result");
        JSONArray jaar = dataResult.getJSONArray("projects");
        List<String> arr=new ArrayList<>();
        String EduLevel = null;
        for(int i = 0; i < jaar.length();i++) {
            JSONObject temp1=(JSONObject) jaar.get(i);
            JSONArray skills=temp1.getJSONArray("jobs");
            ArrayList<String> skillslist=new ArrayList<>();
            String preview_Description=(String)temp1.get("preview_description");
            String type= (String)temp1.get("type");
            arr.add(preview_Description);
            Map<String,Long> individual_sorted = wordStats.wordStat(preview_Description);
            fleschIndex= Double.parseDouble(String.format("%.2f",fleschReadabilty.flesch_read(preview_Description)));
            fleschKincaid= Double.parseDouble(String.format("%.2f",fleschReadabilty.flesch_kincaid(preview_Description)));
            EduLevel = fleschReadabilty.getEduLevel(fleschIndex);
            long miliseconds= Long.parseLong(temp1.get("submitdate").toString());
            long xyz=miliseconds*1000;
            System.out.println("mil: "+xyz);
            DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
            Date date = new Date(xyz);
            String dateSubmitted = obj.format(date);
            for(int j=0;j<skills.length();j++) {

                JSONObject skillsdata = (JSONObject) skills.get(j);
                skillslist.add((String) skillsdata.get("name"));
                KeyResults.addToSkillMap((String) skillsdata.get("name"), (Integer) skillsdata.get("id"));
            }
            ar.add(new projects((Integer) temp1.get("id"), (Integer) temp1.get("owner_id"), (String) temp1.get("title"), (String) temp1.get("preview_description"),skillslist,fleschIndex,fleschKincaid,EduLevel,dateSubmitted,type));
        }
        return ar;
    }

    /**
     * This method is used to get the data from the api
     * @param query     search keyword
     * @return
     */
    public List<projects> getSkills(String query) {
        String temp="";
        try
        {
            String link="https://www.freelancer.com/api/projects/0.1/projects/active?";
            StringBuilder str= new StringBuilder();
            str.append(link).append("&limit=10")
                    .append("&job_details=true")
                    .append("&compact=true").append("&languages[]=en").append("&jobs[]=").append(Integer.toString(KeyResults.getToSkillMap(query)));
            System.out.println(str.toString());

            URL url=new URL(str.toString());
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if(conn.getResponseCode() == 200)
            {
                Scanner sc=new Scanner(url.openStream());

                while(sc.hasNext())
                {
                    temp=temp+sc.nextLine();
                }

            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<projects> ar=getJSONData(temp);
        return ar;
    }
}
