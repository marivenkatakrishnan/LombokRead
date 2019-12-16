package com.java.lombok.read;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
//import com.oracle.sss.model.ApplicationDefinition;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Application {

	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String compartmentId;
	    private String displayName;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String className;
	    @JsonInclude(JsonInclude.Include.NON_NULL)
	    private String type;
	    private String lang;
}


