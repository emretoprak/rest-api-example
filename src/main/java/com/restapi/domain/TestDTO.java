package com.restapi.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "testDTO")
public class TestDTO {

    String order;
    String serial;
    int number;

    @XmlElement
    public String getorder() {
        return order;
    }

    public void setorder(String order) {
        this.order = order;
    }

    @XmlElement
    public String getserial() {
        return serial;
    }

    public void setserial(String serial) {
        this.serial = serial;
    }

    @XmlAttribute
    public int getnumber() {
        return number;
    }

    public void setnumber(int number) {
        this.number = number;
    }

}