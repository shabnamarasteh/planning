package ir.eshragh.planning.modules.jwt;

import ir.eshragh.planning.modules.jwt.model.ResponseToken;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    private Boolean success;
    private String message;
    private int faultCode;
    private String data;

    private ResponseToken responseToken;

    public ApiResponse(Boolean success, String message, int faultCode) {
        this.success = success;
        this.message = message;
        this.faultCode = faultCode;
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, String data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public ApiResponse() {
    }

    public ApiResponse(boolean success, ResponseToken responseToken) {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFaultmessage() {
        return message;
    }

    public void setFaultmessage(String message) {
        this.message = message;
    }

    public int getFaultCode() {
        return faultCode;
    }

    public void setFaultCode(int faultCode) {
        this.faultCode = faultCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ResponseToken getResponseToken() {
        return responseToken;
    }

    public void setResponseToken(ResponseToken responseToken) {
        this.responseToken = responseToken;
    }

    public Map getFaultResponse(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("success" , this.success.toString());
        map.put("faultCode" , String.valueOf(this.faultCode));
        map.put("message" , this.message);
        return map;
    }

    public Map getSuccessResponse(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("success" , this.success.toString());
        map.put("message" , this.message);
        return map;
    }

    public Map getSuccessResponseData(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("success" , this.success.toString());
        map.put("message" , this.message);
        map.put("data" , this.data);
        return map;
    }
}
