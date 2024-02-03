package org.example.Entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Bean_author {
    private String author="-";
    private String orcid="-";
    private String institution="-";
    private String mailbox="-";

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOrcid() {
        return orcid;
    }

    public void setOrcid(String orcid) {
        this.orcid = orcid;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }
}
