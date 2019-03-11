package org.mustafa.yildirimm.exchangerates;

public class Main {
	public static void main(String [] args){
		YapiKrediWS yk = new YapiKrediWS();
		BBankWS bb = new BBankWS();
		
		bb.printResponse();
		//yk.printResponse();
		//yk.processResponse();
	}
}
