package application;

import javafx.beans.property.SimpleStringProperty;

public class MyPlans {
	 
    private String pl;
    private String ct;
    private String time;
    

    public MyPlans(){
    	this.pl = "";
    	this.pl = "";
    	this.pl = "";
    }
    public MyPlans(String pl, String ct, String time) {
        this.pl = pl;
        this.ct = ct;
        this.time = time;
    }

    public String getPl() {
        return pl;
    }

    public void setPl(String col1) {
        this.pl = col1;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String col2) {
        this.ct = col2;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String col3) {
        this.time = col3;
    }
    
}