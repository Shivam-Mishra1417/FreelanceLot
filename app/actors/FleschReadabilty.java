package actors;


import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.max;

/**
 * This Class contains the methods to calculate flesch readabilty Index and flesch kincaid readabilty Index.
 * @author Yash Patel
 */
public class FleschReadabilty extends AbstractActor{
    //AbstractActor {

    public static Props props(){
        return Props.create(FleschReadabilty.class);
    }

    public final class FleschIndexes{
        double fleschIndexRead,fleschKincaidRead;
        String eduLevel;

        public FleschIndexes(double fleschIndexRead, double fleschKincaidRead, String eduLevel) {
            this.fleschIndexRead = fleschIndexRead;
            this.fleschKincaidRead = fleschKincaidRead;
            this.eduLevel = eduLevel;
        }

        public double getFleschIndexRead() {
            return fleschIndexRead;
        }

        public double getFleschKincaidRead() {
            return fleschKincaidRead;
        }

        public String getEduLevel() {
            return eduLevel;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class,message->
                {double flesch=flesch_read(message);
                    double fleschKincaid=flesch_kincaid(message);
                    String eduLevel=getEduLevel(flesch);
                    FleschIndexes f=new FleschIndexes(flesch,fleschKincaid,eduLevel);
                    double fl = f.getFleschIndexRead();
                    double fk = f.getFleschKincaidRead();
                    String el = f.getEduLevel();
                    sender().tell(f,self());
                }).match(Double.class,message->
                {String eduLevel=getEduLevel(message);
                    System.out.println(eduLevel);               })
                .build();
    }

    /**
     * This method is used to identify the educational level based on the flesch readavilty index.
     * @param fleschIndex       flesch redabilty index
     * @return
     */
    public String getEduLevel(double fleschIndex){
        String edulevel=null;
        if (fleschIndex>100)
            edulevel="Early";
        else if (fleschIndex<100&&fleschIndex>90)
            edulevel="5th Grade";
        else if (fleschIndex<90&&fleschIndex>80)
            edulevel="6th Grade";
        else if (fleschIndex<80&&fleschIndex>70)
            edulevel="7th Grade";
        else if (fleschIndex<70&&fleschIndex>66)
            edulevel="8th Grade";
        else if (fleschIndex<66&&fleschIndex>60)
            edulevel="9th Grade";
        else if (fleschIndex<60&&fleschIndex>50)
            edulevel="High School";
        else if (fleschIndex<50&&fleschIndex>30)
            edulevel="some college";
        else if (fleschIndex>0&&fleschIndex<30)
            edulevel="college graduate";
        else if (fleschIndex<=0)
            edulevel="Law School Graduate";

        return edulevel;
    }

    /**
     * This method is used to identify the flesch readabilty index by counting the word,sentences and syllables from the preview description of the project.
     * @param input      preview description
     * @return
     */
    public double flesch_read(String input) {
        double flesch_Index = 0;
        double sentence_count =countSentences(input);
        System.out.println("Sentences " + sentence_count);

        List<String> words =wordCount(input);
        double words_count = (int) words.stream().count();

        double syllables =countSyllables(words);

        flesch_Index = 206.835 - ((84.6 * (syllables / words_count) - 1.015 * (words_count / sentence_count)));
        return flesch_Index;
    }

    /**
     * This method is used to identify the flesch kincaid readabilty index by counting the word,sentences and syllables from the preview description of the project.
     * @param input      preview description
     * @return
     */
    public double flesch_kincaid(String input) {
        double fkgl = 0;
        double sentence_count =countSentences(input);

        List<String> words =wordCount(input);
        double words_count = (int) words.stream().count();
        double syllables = countSyllables(words);

        fkgl = 0.39 * (words_count / sentence_count) + 11.8 * (syllables / words_count) - 15.59;
        return fkgl;
    }
    /**
     * This method is used to count the sentences from the preview description
     * @param input      preview description
     * @return
     */
    public double countSentences(String input){

        String regex_pattern ="([.!?:;])([\\s\\n]*)([a-zA-Z])";
        List<String> sentences = Arrays.asList(input.split(regex_pattern));

        List<String> sen = sentences.stream()
                .filter(s->!s.contentEquals(""))
                .collect(Collectors.toList());

        double sentence_count = sen.stream().count();
        return sentence_count;
    }

    /**
     * This method is used to count the words from the preview description
     * @param input      preview description
     * @return
     */
    public List<String> wordCount(String input){
        input.replaceAll("([^a-zA-Z])"," ");
        List<String> wor = Arrays.asList(input.split("\\s"));
        List<String> words =  wor.stream()
                .map(w-> w.replaceAll("([^a-zA-Z])",""))
                .filter(w->!w.equals(""))
                .collect(Collectors.toList());
        return words;
    }

    /**
     * This method is used to count the syllables from the preview description
     * @param words      List of words
     * @return
     */
    public int countSyllables(List<String> words){
        String i = "(?i)[aiou][aeiou]|e[aeiou](?!d|!s?\\b)|le";
        double syllables = 0;
        for(String word:words){
            int count = 0;
            Matcher m = Pattern.compile(i).matcher(word);
            while (m.find()) {
                count++;
            }
            syllables+=count;
        }
        return (int) max(syllables,1);

    }
}