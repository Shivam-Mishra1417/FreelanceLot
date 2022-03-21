package businesslogic;
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
 * This class method is used to fetches the data from freelance api
 * @author group part
 */

public class KeyResults {
    static HashMap<String, Integer> map = new HashMap<>();
    static List<projects> global_list = new ArrayList<projects>();
    static List<ProjectList> globalResult = new ArrayList<>();
    double fleschIndex = 0;
    double fleschKincaid = 0;
    double fleschIndexSum = 0;
    double fleschIndexAvg = 0;
    double fleschKincaidSum = 0;
    double fleschKincaidAvg = 0;

    /**
     * This method puts the value in Hashmap with the key skills name and value skills id
     * @return void
     */

    static void addToSkillMap(String skillsname, int skillsid) {
        map.put(skillsname, skillsid);

    }
    /**
     * This method is used to get the value of the Hashmap using the key skills name
     * @return int
     */
    static int getToSkillMap(String skillsname) {
        return map.get(skillsname);
    }

    public static void main(String[] args) {

    }

    /**
     * This method Uses the data feteches from the freelance api
     * @param ApiData           Api Data
     * @param searchKeyword     Query
     * @return
     */
    public List<ProjectList> getKeyData(String ApiData,String searchKeyword){
        List<projects> ar=new ArrayList<projects>();
        ProjectList projectList = new ProjectList();
        JSONObject json = new JSONObject(ApiData);
        JSONObject dataResult = json.getJSONObject("result");
        JSONArray jaar = dataResult.getJSONArray("projects");
        //Yash and Yagnik part starts here
        WordStats wordStats=new WordStats();
        FleschReadabilty fleschReadabilty=new FleschReadabilty();

        String EduLevel = null;
        System.out.println("length: "+jaar.length());
        for (int i = 0; i < jaar.length(); i++) {
            JSONObject temp1 = (JSONObject) jaar.get(i);
            JSONArray skills = temp1.getJSONArray("jobs");
            ArrayList<String> skillslist = new ArrayList<>();
            String preview_Description = (String) temp1.get("preview_description");
            String type= (String)temp1.get("type");
            //arr.add(preview_Description);
            // Map<String, Long> individual_sorted = fn.wordStat(preview_Description);
//            String FleschFormat=string.for
            fleschIndex = Double.parseDouble(String.format("%.2f", fleschReadabilty.flesch_read(preview_Description)));
            fleschKincaid = Double.parseDouble(String.format("%.2f", fleschReadabilty.flesch_kincaid(preview_Description)));
            EduLevel = fleschReadabilty.getEduLevel(fleschIndex);
            fleschIndexSum+=fleschIndex;
            fleschKincaidSum+=fleschKincaid;
            //Long miliseconds;
            long miliseconds= Long.parseLong(temp1.get("submitdate").toString());
            //String hjh=Integer.toString(miliseconds);
            long xyz=miliseconds*1000;
            //mil=mil.concat("0000");
            System.out.println("mil: "+xyz);
            /*miliseconds= Long.valueOf(mil*1000);
            System.out.println("mil2: "+miliseconds);*/
            DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
            // we create instance of the Date and pass milliseconds to the constructor
            ////long milli;
            //milli=Long.valueOf(mil);
            Date date = new Date(xyz);
            String dateSubmitted = obj.format(date);
            for (int j = 0; j < skills.length(); j++) {
                JSONObject skillsdata = (JSONObject) skills.get(j);
                skillslist.add((String) skillsdata.get("name"));
                addToSkillMap((String) skillsdata.get("name"), (Integer) skillsdata.get("id"));
            }
            ar.add(new projects((Integer) temp1.get("id"), (Integer) temp1.get("owner_id"), (String) temp1.get("title"),(String) temp1.get("preview_description"), skillslist, fleschIndex, fleschKincaid, EduLevel,dateSubmitted,type));
        }
        fleschKincaidAvg= Double.parseDouble(String.format("%.2f",fleschKincaidSum/ jaar.length()));
        fleschIndexAvg= Double.parseDouble(String.format("%.2f",fleschIndexSum/ jaar.length()));
        fleschIndexSum = 0;
        fleschKincaidSum = 0;
        System.out.println("Avg fk: "+fleschIndexAvg);
        System.out.println("Avg fk kincaid: "+fleschKincaidAvg);
        if(globalResult.size() ==10)
        {
            globalResult.remove(9);
        }
        projectList.setSearchedKeyword(searchKeyword);
        projectList.setProjectsList(ar);
        projectList.setAvgFleshIndex(fleschIndexAvg);
        projectList.setAvgFKGL(fleschKincaidAvg);
        globalResult.add(0,projectList);
        global_list.addAll(0,ar);
        return globalResult;
    }


    /**
     * This Method returns the List of the projects
     * @param query
     * @return
     */
    public List<ProjectList> getProjectData(String query) {
        if(query.isEmpty())
        {
            return globalResult;
        }
        String temp = "";
        String searchKeyword= query;

        try {
            String link = "https://www.freelancer.com/api/projects/0.1/projects/active?query";
            query = query.replaceAll(" ", "%20").toLowerCase();
            StringBuilder str = new StringBuilder();
            str.append(link).append("=").append("\"")
                    .append(query).append("\"")
                    .append("&limit=100")
                    .append("&job_details=true")
                    .append("&compact=true").append("&full_description=true");
            System.out.println(str.toString());
            URL url = new URL(str.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                    temp = temp + sc.nextLine();
                }

            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        query = query.replaceAll("%20", " ");
        List<ProjectList> global=getKeyData(temp,query);
        return global;
    }
}