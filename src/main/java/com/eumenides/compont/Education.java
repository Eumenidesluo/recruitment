package com.eumenides.compont;

public enum Education {
	Secondary("��ר"),
	Undergraduate("����"),
	Maste("˶ʿ"),
	Doctor("��ʿ"),
	Junior("��ר");
	private String name;
    private Education(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
