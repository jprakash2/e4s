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
"Fh",
"Ca",
"I3",
"PF",
"L1v",
"L1i",
"PF1",
"Ca1",
"L2v",
"L2i",
"PF2",
"Ca2",
"L3v",
"L3i",
"PF3",
"Ca3",
"PP",
"Fc",
"Tmp"
})

public class S71 {

	@JsonProperty("Fh")
	private String fh;
	@JsonProperty("Ca")
	private String ca;
	@JsonProperty("I3")
	private String i3;
	@JsonProperty("PF")
	private String pF;
	@JsonProperty("L1v")
	private String l1v;
	@JsonProperty("L1i")
	private String l1i;
	@JsonProperty("PF1")
	private String pF1;
	@JsonProperty("Ca1")
	private String ca1;
	@JsonProperty("L2v")
	private String l2v;
	@JsonProperty("L2i")
	private String l2i;
	@JsonProperty("PF2")
	private String pF2;
	@JsonProperty("Ca2")
	private String ca2;
	@JsonProperty("L3v")
	private String l3v;
	@JsonProperty("L3i")
	private String l3i;
	@JsonProperty("PF3")
	private String pF3;
	@JsonProperty("Ca3")
	private String ca3;
	@JsonProperty("PP")
	private String pP;
	@JsonProperty("Fc")
	private String fc;
	@JsonProperty("Tmp")
	private String tmp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("Fh")
	public String getFh() {
	return fh;
	}

	@JsonProperty("Fh")
	public void setFh(String fh) {
	this.fh = fh;
	}

	@JsonProperty("Ca")
	public String getCa() {
	return ca;
	}

	@JsonProperty("Ca")
	public void setCa(String ca) {
	this.ca = ca;
	}

	@JsonProperty("I3")
	public String getI3() {
	return i3;
	}

	@JsonProperty("I3")
	public void setI3(String i3) {
	this.i3 = i3;
	}

	@JsonProperty("PF")
	public String getPF() {
	return pF;
	}

	@JsonProperty("PF")
	public void setPF(String pF) {
	this.pF = pF;
	}

	@JsonProperty("L1v")
	public String getL1v() {
	return l1v;
	}

	@JsonProperty("L1v")
	public void setL1v(String l1v) {
	this.l1v = l1v;
	}

	@JsonProperty("L1i")
	public String getL1i() {
	return l1i;
	}

	@JsonProperty("L1i")
	public void setL1i(String l1i) {
	this.l1i = l1i;
	}

	@JsonProperty("PF1")
	public String getPF1() {
	return pF1;
	}

	@JsonProperty("PF1")
	public void setPF1(String pF1) {
	this.pF1 = pF1;
	}

	@JsonProperty("Ca1")
	public String getCa1() {
	return ca1;
	}

	@JsonProperty("Ca1")
	public void setCa1(String ca1) {
	this.ca1 = ca1;
	}

	@JsonProperty("L2v")
	public String getL2v() {
	return l2v;
	}

	@JsonProperty("L2v")
	public void setL2v(String l2v) {
	this.l2v = l2v;
	}

	@JsonProperty("L2i")
	public String getL2i() {
	return l2i;
	}

	@JsonProperty("L2i")
	public void setL2i(String l2i) {
	this.l2i = l2i;
	}

	@JsonProperty("PF2")
	public String getPF2() {
	return pF2;
	}

	@JsonProperty("PF2")
	public void setPF2(String pF2) {
	this.pF2 = pF2;
	}

	@JsonProperty("Ca2")
	public String getCa2() {
	return ca2;
	}

	@JsonProperty("Ca2")
	public void setCa2(String ca2) {
	this.ca2 = ca2;
	}

	@JsonProperty("L3v")
	public String getL3v() {
	return l3v;
	}

	@JsonProperty("L3v")
	public void setL3v(String l3v) {
	this.l3v = l3v;
	}

	@JsonProperty("L3i")
	public String getL3i() {
	return l3i;
	}

	@JsonProperty("L3i")
	public void setL3i(String l3i) {
	this.l3i = l3i;
	}

	@JsonProperty("PF3")
	public String getPF3() {
	return pF3;
	}

	@JsonProperty("PF3")
	public void setPF3(String pF3) {
	this.pF3 = pF3;
	}

	@JsonProperty("Ca3")
	public String getCa3() {
	return ca3;
	}

	@JsonProperty("Ca3")
	public void setCa3(String ca3) {
	this.ca3 = ca3;
	}

	@JsonProperty("PP")
	public String getPP() {
	return pP;
	}

	@JsonProperty("PP")
	public void setPP(String pP) {
	this.pP = pP;
	}

	@JsonProperty("Fc")
	public String getFc() {
	return fc;
	}

	@JsonProperty("Fc")
	public void setFc(String fc) {
	this.fc = fc;
	}

	@JsonProperty("Tmp")
	public String getTmp() {
	return tmp;
	}

	@JsonProperty("Tmp")
	public void setTmp(String tmp) {
	this.tmp = tmp;
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
