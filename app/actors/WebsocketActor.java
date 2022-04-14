package actors;

import akka.actor.*;
import actors.FleschReadabilty;
import actors.FleschReadabilty.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import models.Stats;
import models.projects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Future;
import scala.runtime.Static;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;
import static com.google.common.base.Charsets.UTF_8;

/**
 * This is the WebSocket Actor. This Actor is used to fetch the API data.
 *@author Group Part
 */
public class WebsocketActor extends AbstractActor {
    HttpResponse res = null;
    public JSONArray result = new JSONArray();
    static HashMap<String, Integer> map = new HashMap<>();
    public static List<projects> global_list=new ArrayList<projects>();
    boolean match = false;
    double fleschIndex = 0;
    double fleschKincaid = 0;
    double fleschIndexSum = 0;
    double fleschIndexAvg = 0;
    double fleschKincaidSum = 0;
    double fleschKincaidAvg = 0;
    ActorSystem akkaSystem =  ActorSystem.create("System");
    ActorRef index = akkaSystem.actorOf(FleschReadabilty.props());

    /**
     * This method adds the given parameters into the skills map.
     *@param skillsid           Skill ID
     * @param skillsname        name of the skill
     */
    //List<projects> ar=new ArrayList<projects>();
    public static void addToSkillMap(String skillsname, int skillsid) {
        map.put(skillsname, skillsid);

    }

    /**
     * This method get the given skills map from the skills name.
     *@param skillsname        name of the skill
     */
    public static int getToSkillMap(String skillsname) {
        if(skillsname.equals("test"))
            return 1;
        return map.get(skillsname);
    }


    public static Props props(ActorRef out) {
        return Props.create(WebsocketActor.class, out);
    }
    public static Props props() {
        return Props.create(WebsocketActor.class);
    }

    public WebsocketActor(){

    }

    private ActorRef out;

    public WebsocketActor(ActorRef out) {

        this.out = out;
    }

    /**
     * This method fetches the data by API calling.
     *@param query           search keyword
     * @return JSONArray
     */

    public JSONArray getData(String query) {
//        FleschReadabilty fleschReadabilty=new FleschReadabilty();
        CompletionStage<Object> fleschIndexOutput;
        FleschIndexes fleschOutput=null;
        List<projects> ar=new ArrayList<projects>();
        String output=null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            String link = "https://www.freelancer.com/api/projects/0.1/projects/active?query=";
            HttpRequest req = HttpRequest.newBuilder().uri(URI.create(link + URLEncoder.encode(query,UTF_8) + "&limit=20&job_details=true&compact=true&sort_field=time_updated")).build();

            res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(res.uri());
            System.out.println(res.body().toString());
            Object json = new JSONParser().parse(String.valueOf(res.body()));
            JSONObject testzz = (JSONObject) json;
            JSONObject dataResult = (JSONObject) testzz.get("result");
            JSONArray jaar = (JSONArray) dataResult.get("projects");

            for (int i=0;i<jaar.size();i++){
                System.out.println("Starting to build project list");
                String EduLevel=null;
                JSONObject temp1=(JSONObject) jaar.get(i);
                JSONArray skills=(JSONArray)temp1.get("jobs");
                String type= (String)temp1.get("type");
                ArrayList<String> skillslist = new ArrayList<String>();
                long miliseconds= Long.parseLong(temp1.get("submitdate").toString());
                long xyz=miliseconds*1000;
                DateFormat obj = new SimpleDateFormat("dd MMM yyyy");
                Date date = new Date(xyz);
                String dateSubmitted = obj.format(date);

                fleschIndexOutput = FutureConverters.toJava(ask(index, (String) temp1.get("preview_description"), Integer. MAX_VALUE));
                System.out.println("Bamboozled : "+fleschIndexOutput);
                fleschOutput= (FleschIndexes) fleschIndexOutput.toCompletableFuture().get();
                System.out.println("Bamboozled : "+fleschOutput.getFleschIndexRead());
//                fleschOutputFinal=fleschOutput.getFleschIndexRead();
                fleschIndex = Double.parseDouble(String.format("%.2f", fleschOutput.getFleschIndexRead()));
                System.out.println("yahan phata");
                fleschKincaid=Double.parseDouble(String.format("%.2f", fleschOutput.getFleschKincaidRead()));;
//                fleschKincaid = Double.parseDouble(String.format("%.2f", fleschReadabilty.flesch_kincaid((String) temp1.get("preview_description"))));
//                EduLevel = fleschReadabilty.getEduLevel(fleschIndex);
                EduLevel=fleschOutput.getEduLevel();
                fleschIndexSum+=fleschIndex;
                fleschKincaidSum+=fleschKincaid;
                //  System.out.println(skills.size());
                for (int j = 0; j < skills.size(); j++) {
                    JSONObject skillsdata = (JSONObject) skills.get(j);
                    skillslist.add((String) skillsdata.get("name"));
                    addToSkillMap((String) skillsdata.get("name"), ((Long)skillsdata.get("id")).intValue());
                }
                // System.out.println("enter");

                projects p1 =new projects(((Long)temp1.get("id")).intValue() ,
                        ((Long)temp1.get("owner_id")).intValue(),
                        (String) temp1.get("title"),(String) temp1.get("preview_description"),
                        skillslist,fleschIndex,fleschKincaid,
                        EduLevel,
                        dateSubmitted,
                        type);
                ar.add(0,p1);
            }

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            output = ow.writeValueAsString(ar);
            JSONParser parser = new JSONParser();
            JSONArray test1 = (JSONArray)parser.parse(output);
            fleschKincaidAvg= Double.parseDouble(String.format("%.2f",fleschKincaidSum/ jaar.size()));
            fleschIndexAvg= Double.parseDouble(String.format("%.2f",fleschIndexSum/ jaar.size()));
            fleschIndexSum = 0;
            fleschKincaidSum = 0;
            System.out.println("Avg fk: "+fleschIndexAvg);
            System.out.println("Avg fk kincaid: "+fleschKincaidAvg);
            JSONObject test=new JSONObject();
            test.put("key", query);
            test.put("data",test1);
            test.put("Avg_FRI",fleschKincaidAvg);
            test.put("Avg_FKGL",fleschIndexAvg);
            System.out.println("ya idhar phata");
            if (result.isEmpty()&&query!="test") {
                result.add(test);
            } else {
                result.add(test);
                match = false;
                result = getValuesForGivenKey(result, test, new JSONArray());
            }
        } catch (Exception e) {
            System.out.println("phat gaya --"+e.toString());
        }
        System.out.println("idhar dekho bc "+result);
        global_list.addAll(0,ar);
        return result;
    }

    /**
     * This method gets the data from the map using a an identifier as key.
     *@param jsonArrayStr           JSONArray containing data
     * @param test                  JSONObject
     * @param result                JSONArray containing the result
     */
    public JSONArray getValuesForGivenKey(JSONArray jsonArrayStr, JSONObject test, JSONArray result) {
        JSONObject obj = (JSONObject) jsonArrayStr.get(0);
        if (obj.get("key").toString().equals(test.get("key").toString()) && match == false) {
            match = true;
            jsonArrayStr.remove(0);
            result.add(test);
            if (jsonArrayStr.size() > 0) {
                getValuesForGivenKey(jsonArrayStr, test, result);
            } else {
                return result;
            }
        } else if (!obj.get("key").toString().equals(test.get("key").toString())) {
            jsonArrayStr.remove(0);
            result.add(obj);
            if (jsonArrayStr.size() > 0) {
                getValuesForGivenKey(jsonArrayStr, test, result);
            }
        } else {
            jsonArrayStr.remove(0);
            if (jsonArrayStr.size() > 0) {
                getValuesForGivenKey(jsonArrayStr, test, result);
            } else {
                return result;
            }
        }
        return result;
    }

    /**
     * Defines which messages the WebSocket actor can handle, along with the implementation of how the messages should be processed.
     * @return Receive
     */
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    out.tell(this.getData(message).toJSONString(), self());
                }).match(Integer.class, words-> {
                    sender().tell(this.global_list, self());
                })
                .build();
    }

}