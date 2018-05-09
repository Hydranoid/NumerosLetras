/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ico.fes.aragon;


import java.util.regex.Pattern;
/**
 *
 * @author user
 */
public class Convertir {
    
  

    private final String[] UNID = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DEC = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENT = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

   public Convertir() {
   }

    public String Convirtiendo(String numero, boolean minusculas) {
        String enteros = "";
        String parte_decimal;    
       
        numero = numero.replace(".", ",");
     
        
        if(numero.indexOf(",")==-1){
            numero = numero + ",00";
        }
       
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {

            String Num[] = numero.split(",");            
           
            parte_decimal = Num[1] + "/100";

            if (Integer.parseInt(Num[0]) == 0) {
                enteros = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {
                enteros = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {
                enteros = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {
                enteros = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {
                enteros = getDecenas(Num[0]);
            } else {
                enteros = getUnidades(Num[0]);
            }
           
            if (minusculas) {
                return (enteros + parte_decimal).toUpperCase();
            } else {
                return (enteros + parte_decimal);
            }
        } else {
            return enteros = null;
        }
    }



    private String getUnidades(String numero) {
    
        String num = numero.substring(numero.length() - 1);
        return UNID[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {                        
        int n = Integer.parseInt(num);
        if (n < 10) {
            return getUnidades(num);
        } else if (n > 19) {
            String u = getUnidades(num);
            if (u.equals("")) { 
                return DEC[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DEC[Integer.parseInt(num.substring(0, 1)) + 8] + " y " + u;
            }
        } else {
            return DEC[n - 10];
        }
    }

    private String getCentenas(String num) {
        if( Integer.parseInt(num)>99 ){
            if (Integer.parseInt(num) == 100) {
                return " cien ";
            } else {
                 return CENT[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            } 
        }else{ 
           
            return getDecenas(Integer.parseInt(num)+"");            
        }        
    }

    private String getMiles(String numero) {
       
        String c = numero.substring(numero.length() - 3);
     
        String m = numero.substring(0, numero.length() - 3);
        String n="";
      
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);           
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) {        

        String miles = numero.substring(numero.length() - 6);
   
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if(millon.length()>1){
            n = getCentenas(millon) + "millones ";
        }else{
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);        
    }
}
 

    
    

