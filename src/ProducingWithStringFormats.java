
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class ProducingWithStringFormats {

    public static void main (String[] args){
        LoanApplication loanApplication =  ExampleLoan.LOAN_APPLICATION;
        System.out.println(loanApplication);
        System.out.println();
        System.out.println(toJsonStringManually(loanApplication));


        /*
        Self practice

        //Parse and toJsonString with simple-json library
            /*
            readDataFromJsonFile();
            writeDataToJsonFile();
            */

        //Parse and toJsonString with Jackson.core
        //toJsonStringJackson(); */
    }

    private static CharSequence toJsonStringManually(LoanApplication loanApplication) {
        return String.format(
                "{\n"+
                        " \"name\": \"%s\",\n" +
                        " \"purposeOfLoan\": \"%s\",\n" +
                        " \"loanDetails\": %s,\n"+
                        " \"jobs\": %s\n "+
                        "}\n",
                loanApplication.getName(),
                loanApplication.getPurposeOfLoan(),
                toJsonStringManuallyObject(loanApplication.getLoanDetails()),
                toJsonStringManuallyArray(loanApplication.getJobs())
        );
    }

    private static CharSequence toJsonStringManuallyArray(List<Job> jobs) {
        return jobs
                .stream()
                .map(job -> String.format(
                        "{\n" +
                                "\"title\":\"%s\"" +"\n"+
                                "\"annualIncome\":\"%d\""+"\n"+
                                "\"yearsActive\":\"%d\""+"\n"+
                        "\n}",
                        job.getTitle(),
                        job.getAnnualIncome(),
                        job.getYearsActive()
                        ))
                .collect(joining(",\n","[\n","\n]"));

    }

    private static CharSequence toJsonStringManuallyObject(LoanDetails loanDetails) {
        return String.format(
                "{\n"+
                        "\"amount\": %g\n"+
                        "\"startDate\": %s\n"+
                        "\"endDate\": %s\n"+
                "}\n",
                loanDetails.getMount(),
                loanDetails.getStartDate(),
                loanDetails.getEndDate()
        );
    }


    //Self practice
    private static void toJsonStringJackson(){
        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonGenerator generator = jsonFactory.createGenerator(new File("bank_loan_jackson_generator.json"),
                    JsonEncoding.UTF8);

            //Make json mer readable
            generator.setPrettyPrinter(new DefaultPrettyPrinter());
            //Start to write a json object
            generator.writeStartObject();
            //write string field name and purpose of loan
            generator.writeStringField("name","Tom");
            generator.writeStringField("purposeOfLoan","Buy a new car");

            //To write the loanDetails
            toJsonStringJacksonLoanDetailsObject(generator);

            //To write the jobs array
            toJsonStringJacksonJobsArray(generator);
            //End to write a json object
            generator.writeEndObject();
            generator.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void toJsonStringJacksonLoanDetailsObject(JsonGenerator generator){
        try {
            generator.writeFieldName("loanDetails");
            generator.writeStartObject();
            generator.writeNumberField("amount",10000);
            generator.writeStringField("startDate","2018-01-01");
            generator.writeStringField("endDate","2020-01-01");
            generator.writeEndObject();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void toJsonStringJacksonJobObject(JsonGenerator generator, String title,
                                                     int annualIncome, int yearsActive){
        try{
            generator.writeStartObject();
            generator.writeStringField("title",title);
            generator.writeNumberField("annualIncome",annualIncome);
            generator.writeNumberField("yearsActive",yearsActive);
            generator.writeEndObject();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void toJsonStringJacksonJobsArray(JsonGenerator generator){
        try{
            generator.writeFieldName("jobs");
            generator.writeStartArray();
            toJsonStringJacksonJobObject(generator,"Freelance Developer", 10000, 2);
            toJsonStringJacksonJobObject(generator,"Part Time Developer", 200, 3);
            generator.writeEndArray();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private static void toParseJsonJackson(){
        JsonFactory jsonFactory = new JsonFactory();
        try {
            JsonParser parser = jsonFactory.createParser(new URL("bank_loan.json"));
            while(! parser.isClosed()){
                JsonToken jsonToken = parser.nextToken();
                //if(jsonToken.)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    private static void writeDataToJsonFile(){
        //Make jobs part
        JSONObject job1 = new JSONObject();
        job1.put("title","Freelance");
        job1.put("annualIncome",10000);
        job1.put("yearsActive",1);

        JSONObject job2 = new JSONObject();
        job2.put("title","Part time Developer");
        job2.put("annualIncome",20000);
        job2.put("yearsActive",2);

        JSONArray jobs = new JSONArray();
        jobs.add(job1);
        jobs.add(job2);

        //Make loanDetails part
        JSONObject loanDetails = new JSONObject();
        loanDetails.put("amount",10000);
        loanDetails.put("startDate","2019-01-01");
        loanDetails.put("endDate","2020-01-01");

        //MAke name and purpose
        JSONObject loan = new JSONObject();
        loan.put("name", "Tom");
        loan.put("purposeOfLoan", "To buy a car.");

        //Add jobs and loanDetails to loan
        loan.put("loanDetails",loanDetails);
        loan.put("jobs",jobs);

        //Write the json file to bank_loan.json by FileWriter
        try {
            FileWriter fileWriter = new FileWriter("bank_loan_new.json");
            fileWriter.write(loan.toJSONString());
            fileWriter.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readDataFromJsonFile(){
        //Create a parser to read file
        JSONParser jsonParser = new JSONParser();
        try{
            FileReader fileReader = new FileReader("bank_loan.json");
            //Object to read the json file
            JSONObject loan = (JSONObject) jsonParser.parse(fileReader);
            //System.out.printf("loan is %s"+"\n", loan);

            String name = (String)loan.get("name");
            String purposeOfLoan = (String) loan.get("purposeOfLoan");

            JSONObject loanDetails = (JSONObject) loan.get("loanDetails");
            System.out.printf("name is %s and purpose is %s\n",name,purposeOfLoan);
            double amount = ((Number)loanDetails.get("amount")).doubleValue();
            String startDate = (String) loanDetails.get("startDate");
            String endDate = (String) loanDetails.get("endDate");
            System.out.printf("amount is %f and startDate is %s, endDate is %s\n",
                    amount,startDate,endDate);

            JSONArray jobs = (JSONArray) loan.get("jobs");
            parseJobsArray(jobs);


        }catch (FileNotFoundException e ){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    private static void parseJobsArray(JSONArray jobs){
        for(Object item : jobs){
            JSONObject job = (JSONObject) item;
            parseJobObject(job);
        }
    }

    private static void parseJobObject(JSONObject job){
        String title = (String) job.get("title");
        int annualIncome = ((Number) job.get("annualIncome")).intValue();
        int yearsActive = ((Number) job.get("yearsActive")).intValue();
        System.out.printf("title is %s, and annualIncome is %d, yearsActive is %d \n",
                title,annualIncome,yearsActive);
    }

}

