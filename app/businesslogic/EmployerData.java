package businesslogic;
import akka.protobufv3.internal.Api;
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
 * This Class contains the employer data
 *
 */
public class EmployerData {
    /**
     * This method is used to get the employer data
     * @param key
     * @param object
     * @return
     */
    public String getData(String key, JSONObject object){
        String data;
        try {
            data = object.get(key).toString();
        }
        catch(Exception e){
            data="NA";
        }
        return data;
    }

    /**
     * This method is used to get the status of the employer
     * @param ApiData
     * @return
     */
    public Emp_Status getStatusData(String ApiData){
        JSONObject json=new JSONObject(ApiData);
        JSONObject dataResult = json.getJSONObject("result");
        JSONObject status = dataResult.getJSONObject("status");
        String payment_verified= getData("payment_verified",status);
        String email_verified= getData("email_verified",status);
        String deposit_made= getData("deposit_made",status);
        String profile_complete= getData("profile_complete",status);
        String phone_verified= getData("phone_verified",status);
        String identity_verified= getData("identity_verified",status);
        String facebook_connected= getData("facebook_connected",status);
        String freelancer_verified_user= getData("freelancer_verified_user",status);
        String linkedin_connected= getData("linkedin_connected",status);
        Emp_Status es = new Emp_Status(payment_verified,email_verified,deposit_made,profile_complete,
                phone_verified,identity_verified,facebook_connected,freelancer_verified_user,linkedin_connected);
        return es;

    }

    /**
     * This method is used to get the list of the employer
     * @param query         Api query
     * @return list
     */
    public List<Employer> getEmployer(String query) {
        String temp="";
        try
        {
            String link="https://www.freelancer.com/api/users/0.1/users/";
            //query = query.replaceAll(" ","%20").toLowerCase();
            StringBuilder str= new StringBuilder();
            str.append(link).append(query).
                    append("?avatar=true&display_info=true&country_details=true&jobs=true&portfolio_details=true&preferred_details=true&profile_description=true&qualification_details=true&recommendations=true&responsiveness=true").
                    append("&status=true&&operating_areas=true&compact=true");
            System.out.println(str);
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

        System.out.println(temp);
        JSONObject json=new JSONObject(temp);
        JSONObject dataResult = json.getJSONObject("result");
        JSONObject location = dataResult.getJSONObject("location");
        JSONObject country = location.getJSONObject("country");
        JSONObject currency = dataResult.getJSONObject("primary_currency");
        String id = getData("id",dataResult);
        String username = getData("username",dataResult);
        String closed= getData("closed",dataResult);
        String profile_description = getData("profile_description",dataResult);

        String hourly_rate= getData("hourly_rate",dataResult);
        String registration_date= getData("registration_date",dataResult);
        String limited_account= getData("limited_account",dataResult);
        String display_name= getData("display_name",dataResult);
        String tagline= getData("tagline",dataResult);
        String country_name= getData("name",country);
        String city_name= getData("city",location);
        String role= getData( "role",dataResult);

        Emp_Status es=getStatusData(temp);

        String currency_code= getData( "code",currency);
        String language=  getData(  "primary_language",dataResult);
        String portfolio_count= getData( "portfolio_count",dataResult);
        String public_name= getData( "public_name",dataResult);
        String company= getData( "company",dataResult);



        List<projects> employer_proj =  getProject(id);
        Employer a1 = new Employer(  id,username,closed,profile_description,hourly_rate,registration_date,limited_account,display_name,tagline,country_name,city_name,role,es,currency_code,language,portfolio_count,public_name,company,employer_proj);
        List<Employer> ar1 = new ArrayList<>();
        ar1.add(a1);
        return ar1;
    }

    /**
     * This method is used to get the project data
     * @param query         search keyword
     * @return list
     */
    public List<projects> getProject(String query) {
        String temp="";
        try
        {
            String link="https://www.freelancer.com/api/projects/0.1/projects/?owners[]=";
            query = query.replaceAll(" ","%20").toLowerCase();
            StringBuilder str= new StringBuilder();
            str.append(link).append(query)
                    .append("&limit=10")
                    .append("&job_details=true")
                    .append("&compact=true").append("&full_description=true");
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
        List<projects>ar =getJSONData(temp);
        return ar;
    }

    /**
     * This method is used to get the project data
     * @param Data         search keyword
     * @return list
     */

    public List<projects> getJSONData(String Data){
        List<projects> ar = new ArrayList<projects>();
        JSONObject json=new JSONObject(Data);
        JSONObject dataResult = json.getJSONObject("result");
        JSONArray jaar = dataResult.getJSONArray("projects");
        //Yash and Project
        FleschReadabilty fleschReadabilty=new FleschReadabilty();
        WordStats wordStats=new WordStats();
        List<String> arr=new ArrayList<>();
        double fleschIndex=0;
        double fleschKincaid=0;
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
            for(int j=0;j<skills.length();j++) {
                JSONObject skillsdata = (JSONObject) skills.get(j);
                skillslist.add((String) skillsdata.get("name"));
            }
            ar.add(new projects((Integer) temp1.get("id"), (Integer) temp1.get("owner_id"), (String) temp1.get("title"), (String) temp1.get("preview_description"),skillslist,fleschIndex,fleschKincaid,EduLevel,dateSubmitted,type));
        }
        return ar;
    }
}
