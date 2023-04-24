package client;
import com.beust.jcommander.Parameter;
public class Args {
    @Parameter(names = {"-t", "typeRequest"}, description = "Type of request")
    private String typeRequest;
    @Parameter(names = "-k", description = "key to value")
    private String key;
    @Parameter(names = "-v", description = "value to save in database")
    private String value;
    @Parameter(names = "-in", description = "file name")
    private String inputFileName;
    public String getTypeRequest() {
        return typeRequest;
    }
    public void setTypeRequest(String typeRequest) {
        this.typeRequest = typeRequest;
    }
    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getInputFileName() {return inputFileName;}
    public void setInputFileName(String inputFileName) {this.inputFileName = inputFileName;}
}