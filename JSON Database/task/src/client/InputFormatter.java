package client;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class InputFormatter {
    private final Args args;
    InputFormatter(Args args){
        this.args = args;
    }

    public List<String> inputToJson(){
        Gson gson = new Gson();
        if(this.args.getInputFileName()!=null)
            return inputInFile(this.args.getInputFileName());
        if(this.args.getTypeRequest().equals("exit")){
            return Collections.singletonList(gson.toJson(Map.of("type", "exit")));
        }else{
            return Collections.singletonList(gson.toJson(getParameters()));
        }
    }


    public List<String> inputInFile(String fileName){
        Gson gson = new Gson();
        if (this.args.getInputFileName()!=null){
            try {
                File inputFile = new File("src/client/data/"+fileName);
                List<String> msgJson = new ArrayList<>();
                final Scanner myReader = new Scanner(inputFile);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    msgJson.add(data);
                }
                myReader.close();
                return msgJson;

            }catch(Exception e){
                System.out.println("Something went wrong");
            }
        }
        return null;
    }

    private Map<String, String> getParameters(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("type", this.args.getTypeRequest());
        parameters.put("key", this.args.getKey());
        if (this.args.getTypeRequest().equals("set"))
            parameters.put("value", this.args.getValue());
        return parameters;
    }


}
