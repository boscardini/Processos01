package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;




public class RedesController {
	private String os () 
	{
		return System.getProperty ("os.name");
		
		}
	
	public String nomedosistemaoperacional () {
		return os();
		
	}
	
	
	public void ip (String comand) {
		String sistemaoperacional = os();
		
		if (sistemaoperacional.contains("Win"))
		{
		 comand = "ipconfig";
			
		} else if (sistemaoperacional.contains("nux")) {
			 comand= "ip addr";
		}  else {
            System.out.println("Sistema Operacional nao registrado");
            return;
        }
		
		try {
		 Process p= Runtime.getRuntime().exec(comand);
		 InputStream fluxo= p.getInputStream();
		 InputStreamReader leitor= new InputStreamReader (fluxo, Charset.forName("CP850"));
		 BufferedReader buffer = new BufferedReader (leitor);
		 String linha= buffer.readLine();
		  String ultimalinha = "";
		 
		 while (linha !=null) {
			 if ((sistemaoperacional.contains("Win")) && (linha.toLowerCase().contains("adaptador")) || (linha.toLowerCase().contains("adapter"))) {
				 ultimalinha = linha; 
			 
			 } else  if ((sistemaoperacional.contains("nux")) && (linha.contains("flags")) || (linha.contains("mtu"))) {
				 ultimalinha = linha;
			 }
			 
			 if ((sistemaoperacional.contains("Win")) && (linha.toLowerCase().contains("ipv4")) ||
	                    (sistemaoperacional.contains("nux")) && (linha.contains("inet ")) && (linha.contains("inet6"))) {
			System.out.println(ultimalinha+"  "+linha);
			 
			}
			 linha = buffer.readLine();
		 }
		 buffer.close();
		 leitor.close();
		 fluxo.close();
		 
		  } catch (Exception e) {
			String msg = e.getMessage ();
			System.err.println (msg);
			
		 }
		
		}
	      
	       public void ping (String comando) {
	    	   String sistemaoperacional = os();
	    	 
	    	   
	    	   if (sistemaoperacional.contains("Win")) {
	    		   comando= "ping -4 -n 10 www.google.com.br";
	    		   
	    	   } else if (sistemaoperacional.contains("nux")) {
	    		   comando= "ping -4 -c 10 www.google.com.br"; 
	    	   }
	    	   
	    	 
	    	  try {
	    		  Process p= Runtime.getRuntime().exec(comando);
	    	      InputStream fluxo= p.getInputStream();
	    	      InputStreamReader leitor= new InputStreamReader (fluxo,  Charset.forName("CP850"));
	    	      BufferedReader buffer = new BufferedReader (leitor);
	    		  String linha= buffer.readLine();
	    		  
	    		  while (linha !=null) {
	    			 

	                 
	                  if (sistemaoperacional.contains("Win")) {
	                      if (linha.toLowerCase().contains("média")) {
	                          String[] partes = linha.split("=");
	                          if (partes.length > 1) {
	                              String media = partes[1].trim().split("")[0];
	                              System.out.println("Média do Ping: " + media+"ms");
	                          }
	                      }
	                  } else if (sistemaoperacional.contains("nux")) {
	                      if (linha.toLowerCase().contains("avg")) {
	                          String[] partes = linha.split("/");
	                          if (partes.length > 1) {
	                              String media = partes[1].trim();
	                              System.out.println("Média do Ping: " + media + " ms");
	                          }
	                      }
	                  }

	              linha = buffer.readLine();
	    		  }
	    		  buffer.close();
	    		 leitor.close();
	    		fluxo.close();
	    	  } catch (Exception e) {
	    		  String msg = e.getMessage ();
	  			System.err.println (msg);
	    	  }
	       }
	       
}
