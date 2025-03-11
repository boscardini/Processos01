package view;

import controller.RedesController;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		RedesController pCont = new RedesController();
	     
		 Scanner scan= new Scanner(System.in);
		 int menu=0;
	   
		 while (menu!=3) {
			
			System.out.println ("Digite:"+"\n"+" 1 - Para chamada do método ip"+"\n"+" 2 - Para chamada do método ping"+"\n"+" 3 - Para finalizar a aplicação ");
			menu= scan.nextInt ();
			if (menu==1){
				pCont.ip ();
			} else if (menu==2) {
				pCont.ping ();
			}else {
				if (menu==3) {
				System.out.println ("Aplicação finalizada.");}
				else {
					System.out.println ("Opção inválida!Aplicação finalizada.");
					break;
				}
				
			}
		
			System.out.println ("\n");
		}
		
		}
		}


