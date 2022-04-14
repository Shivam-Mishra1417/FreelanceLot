package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import models.projects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This is the Actor class to handle the Skills from the API.
 * @author Rahul Patel
 */
public class SkillsResult extends AbstractActor {

    public static Props getProps() {
        return Props.create(SkillsResult.class);
    }

    /**
     * Defines which messages the SkillsResult actor can handle, along with the implementation of how the messages should be processed.
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, query-> {
                    System.out.println("------------Debug----------");
                    List<projects> data = getSkills(query);
                    sender().tell(data, self());
                })
                .build();
    }
    /**
     * This method is used to get JSON data from the freelance api
     * @param ApiData
     * @return
     */
    public static List<projects> getJSONData(String ApiData) throws ParseException {
        double fleschIndex=0;
        double fleschKincaid=0;
        List<projects> ar = new ArrayList<projects>();
//        FleschReadabilty fleschReadabilty=new FleschReadabilty();
        //   WordStats wordStats=new WordStats();
        Object json_og = new JSONParser().parse(ApiData);
        JSONObject json=(JSONObject) json_og;
        JSONObject dataResult =(JSONObject) json.get("result");
        JSONArray jaar =(JSONArray) dataResult.get("projects");
        List<String> arr=new ArrayList<>();
        String EduLevel = null;
        for(int i = 0; i < jaar.size();i++) {
            JSONObject temp1=(JSONObject) jaar.get(i);
            JSONArray skills=(JSONArray) temp1.get("jobs");
            ArrayList<String> skillslist=new ArrayList<>();
            String preview_Description=(String)temp1.get("preview_description");
            String type= (String)temp1.get("type");
            arr.add(preview_Description);
            // Map<String,Long> individual_sorted = wordStats.wordStat(preview_Description);
//            fleschIndex= Double.parseDouble(String.format("%.2f",fleschReadabilty.flesch_read(preview_Description)));
//            fleschKincaid= Double.parseDouble(String.format("%.2f",fleschReadabilty.flesch_kincaid(preview_Description)));
//            EduLevel = fleschReadabilty.getEduLevel(fleschIndex);
            fleschIndex=0.0;
            fleschKincaid=0.0;
            EduLevel="xyz le";
            long miliseconds= Long.parseLong(temp1.get("submitdate").toString());
            long xyz=miliseconds*1000;
            System.out.println("mil: "+xyz);
            DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
            Date date = new Date(xyz);
            String dateSubmitted = obj.format(date);
            for(int j=0;j<skills.size();j++) {

                JSONObject skillsdata = (JSONObject) skills.get(j);
                skillslist.add((String) skillsdata.get("name"));
                WebsocketActor.addToSkillMap((String) skillsdata.get("name"), ((Long)skillsdata.get("id")).intValue());
            }
            ar.add(new projects(((Long)temp1.get("id")).intValue(), ((Long)temp1.get("owner_id")).intValue(), (String) temp1.get("title"), (String) temp1.get("preview_description"),skillslist,fleschIndex,fleschKincaid,EduLevel,dateSubmitted,type));
        }
        return ar;
    }

    /**
     * This method is used to get the data from the api
     * @param query     search keyword
     * @return
     */
    public List<projects> getSkills(String query) throws ParseException {
        String temp="";
        try
        {
            String link="https://www.freelancer.com/api/projects/0.1/projects/active?";
            StringBuilder str= new StringBuilder();
            str.append(link).append("&limit=10")
                    .append("&job_details=true")
                    .append("&compact=true").append("&languages[]=en").append("&jobs[]=").append(Integer.toString(WebsocketActor.getToSkillMap(query)));
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
