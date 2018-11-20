package cn.shovi.edu.common.bean;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Tbuser {
    private Integer userid;

    private String useruuid;

    private String username;

    private String passwd;

    private String userveritype;

    private String usernickname;

    private Date userregtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid == null ? null : useruuid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getUserveritype() {
        return userveritype;
    }

    public void setUserveritype(String userveritype) {
        this.userveritype = userveritype == null ? null : userveritype.trim();
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }

    public Date getUserregtime() {
        return userregtime;
    }

    public void setUserregtime(Date userregtime) {
        this.userregtime = userregtime;
    }
}