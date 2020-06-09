package ir.eshragh.planning.modules.jwt.model;

import com.fasterxml.jackson.core.JsonParser;
import org.json.JSONObject;

import java.util.*;

public class ResponseToken {

//	private String token;
//
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}

	private String accessToken;
	private String tokenType = "Bearer";
	private Boolean success = true;
	private String data;

	public ResponseToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public ResponseToken(String accessToken, Boolean success ) {
		this.accessToken = accessToken;
//		this.data = "accessToken" + accessToken + "tokenType :" + this.tokenType;
	}

	public Map getResponse(){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonobj = new JSONObject();

		map.put("success" , this.success.toString());

		jsonobj.put("accessToken", this.accessToken);
		jsonobj.put("tokenType", this.tokenType);
		map.put("data" , jsonobj.toString());
		return  map;

	}
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
