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

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getInputFileName() {
        return inputFileName;
    }

}