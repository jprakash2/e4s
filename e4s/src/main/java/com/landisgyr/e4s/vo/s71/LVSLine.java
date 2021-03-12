package com.landisgyr.e4s.vo.s71;

import java.util.HashMap;
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
"Pos",
"S71"
})

public class LVSLine {

	@JsonProperty("Id")
	private String id;
	@JsonProperty("Pos")
	private String pos;
	@JsonProperty("S71")
	private S71 s71;
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

	@JsonProperty("Pos")
	public String getPos() {
	return pos;
	}

	@JsonProperty("Pos")
	public void setPos(String pos) {
	this.pos = pos;
	}

	@JsonProperty("S71")
	public S71 getS71() {
	return s71;
	}

	@JsonProperty("S71")
	public void setS71(S71 s71) {
	this.s71 = s71;
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
