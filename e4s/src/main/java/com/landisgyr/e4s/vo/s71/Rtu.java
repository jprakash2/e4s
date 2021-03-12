package com.landisgyr.e4s.vo.s71;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"Id",
"LVSLine"
})

public class Rtu {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("LVSLine")
	private List<LVSLine> lVSLine = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Id")
	public String getId() {
	return id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
	this.id = id;
	}

	@JsonProperty("LVSLine")
	public List<LVSLine> getLVSLine() {
	return lVSLine;
	}

	@JsonProperty("LVSLine")
	public void setLVSLine(List<LVSLine> lVSLine) {
	this.lVSLine = lVSLine;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}


}
