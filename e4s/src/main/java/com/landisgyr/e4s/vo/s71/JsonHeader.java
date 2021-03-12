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
"IdRpt",
"IdPet",
"Version",
"Rtu"
})

public class JsonHeader {

	@JsonProperty("IdRpt")
	private String idRpt;
	@JsonProperty("IdPet")
	private String idPet;
	@JsonProperty("Version")
	private String version;
	@JsonProperty("Rtu")
	private Rtu rtu;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("IdRpt")
	public String getIdRpt() {
	return idRpt;
	}

	@JsonProperty("IdRpt")
	public void setIdRpt(String idRpt) {
	this.idRpt = idRpt;
	}

	@JsonProperty("IdPet")
	public String getIdPet() {
	return idPet;
	}

	@JsonProperty("IdPet")
	public void setIdPet(String idPet) {
	this.idPet = idPet;
	}

	@JsonProperty("Version")
	public String getVersion() {
	return version;
	}

	@JsonProperty("Version")
	public void setVersion(String version) {
	this.version = version;
	}

	@JsonProperty("Rtu")
	public Rtu getRtu() {
	return rtu;
	}

	@JsonProperty("Rtu")
	public void setRtu(Rtu rtu) {
	this.rtu = rtu;
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

