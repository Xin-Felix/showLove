package work.huangxin.collect.model;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "`message`")
public class Message {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`content`")
    private String content;

    @Column(name = "`music_url`")
    private String musicUrl;

    @Column(name = "`me_name`")
    private String meName;

    @Column(name = "`other_name`")
    private String otherName;

    @Column(name = "`time`")
    private Date time;

    @Column(name = "`ip`")
    private String ip;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return music_url
     */
    public String getMusicUrl() {
        return musicUrl;
    }

    /**
     * @param musicUrl
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    /**
     * @return me_name
     */
    public String getMeName() {
        return meName;
    }

    /**
     * @param meName
     */
    public void setMeName(String meName) {
        this.meName = meName;
    }

    /**
     * @return other_name
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * @param otherName
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}