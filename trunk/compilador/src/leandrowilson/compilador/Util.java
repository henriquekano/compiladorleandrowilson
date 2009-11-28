package leandrowilson.compilador;

import java.util.Scanner;

public  class Util {
	public static Boolean log = false;
	
	public static void Log(String mensagem){
		if (log){
			System.out.println(mensagem);
		}
	}
	
}
