package com.tajo.memfoyy;

public class Not {

    private String nott, govde, key;

    public Not(){
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public Not(String nott, String govde, String key){
        this.nott = nott;
        this.govde = govde;
        this.key = key;
    }

    public String getNott(){
        return nott;
    }

    public void setNott(String nott) {
        this.nott = nott;
    }

    public String getGovde(){
        return govde;
    }

    public void setGovde(String govde){
        this.govde = govde;
    }
}
