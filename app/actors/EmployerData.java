package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import models.Emp_Status;
import models.Employer;
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
 * This is the Actor class for handling the Employer Data.
 *@author Shivam Mishra
 */
public class EmployerData extends AbstractActor {


    public static Props getProps() {
        return Props.create(EmployerData.class);
    }

    /**
     * Defines which messages the EmployerData actor can handle, along with the implementation of how the messages should be processed.
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, query-> {
                    List<Employer> data = getEmployer(query);
                    sender().tell(data, self());
                })
                .build();
    }
    /**
     * This method gets the data from the JSONObject passed
     *@param key            search keyword
     * @param object        JSONObject
     * @return String
     */
    public static String getData(String key, JSONObject object){
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
    public Emp_Status getStatusData(String ApiData) throws ParseException {
        //JSONObject json=new JSONObject(ApiData);
        //JSONObject dataResult = json.getJSONObject("result");
        //JSONObject status = dataResult.getJSONObject("status");

        Object json_og = new JSONParser().parse(ApiData);
        JSONObject json= (JSONObject) json_og;
        JSONObject dataResult = (JSONObject) json.get("result");
        JSONObject status = (JSONObject) dataResult.get("status");
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
     * This method is used to get the list of the employer.
     * @param query         Api query
     * @return list
     */
    public List<Employer> getEmployer(String query) throws ParseException {
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
        Object json_og = new JSONParser().parse(temp);
        JSONObject json_object= (JSONObject) json_og;
        JSONObject dataResult = (JSONObject) json_object.get("result");
        JSONObject location = (JSONObject) dataResult.get("location");
        JSONObject country = (JSONObject) location.get("country");
        JSONObject currency =(JSONObject) dataResult.get("primary_currency");

        //JSONArray jaar = (JSONArray) dataResult.get("projects");

        //JSONObject json=new JSONObject(temp);
        //JSONObject dataResult = json.getJSONObject("result");
        //JSONObject location = dataResult.getJSONObject("location");
        //JSONObject country = location.getJSONObject("country");
        //JSONObject currency = dataResult.getJSONObject("primary_currency");
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
     * This method is used to get the data from the API calling
     * @param query         search keyword
     * @return list
     */
    public List<projects> getProject(String query) throws ParseException {
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

    public List<projects> getJSONData(String Data) throws ParseException {
        List<projects> ar = new ArrayList<projects>();
        //JSONObject json=new JSONObject(Data);
        Object json = new JSONParser().parse(Data);
        JSONObject json_object= (JSONObject) json;
        JSONObject dataResult = (JSONObject) json_object.get("result");
        JSONArray jaar = (JSONArray) dataResult.get("projects");
        //Yash and Project
//        FleschReadabilty fleschReadabilty=new FleschReadabilty();
        // WordStats wordStats=new WordStats();
        List<String> arr=new ArrayList<>();
        double fleschIndex=0;
        double fleschKincaid=0;
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
            for(int j=0;j<skills.size();j++) {
                JSONObject skillsdata = (JSONObject) skills.get(j);
                skillslist.add((String) skillsdata.get("name"));
            }
            ar.add(new projects(((Long)temp1.get("id")).intValue(), ((Long)temp1.get("owner_id")).intValue(), (String) temp1.get("title"), (String) temp1.get("preview_description"),skillslist,fleschIndex,fleschKincaid,EduLevel,dateSubmitted,type));
        }
        return ar;
    }
}
