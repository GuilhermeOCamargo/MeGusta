package br.com.megusta.userservice.model.dto;
/**
 * @author Guilherme Camargo
 * */
public class StandardResponse {
    private Integer status;
    private String msg;
    private Long timeStamp;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
