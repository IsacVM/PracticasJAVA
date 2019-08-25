import java.util.ArrayList;
import java.util.List; 
import java.util.Arrays; 
import java.util.Scanner;


class ListaNombres{

    //Atributos
    private String nombreLista;
    private ArrayList <String> listaNombres;

    //Metodos
    public ListaNombres(String nombre){//Metodo constructor
        nombreLista=nombre;
        listaNombres=new ArrayList <String> ();
    }

    public void addName(String nombre){
        listaNombres.add(nombre);

    }

    public boolean deleteName(int index){
        if(index>=0 && index<listaNombres.size()){
            listaNombres.remove(index);
            return (true);
        }else{
            return (false);
        }
    }

    public int findName(String name){
        return (listaNombres.indexOf(name));
    }

    public boolean updateName(String name,  String newName){
        int posicion;
        posicion=listaNombres.indexOf(name);
        if(posicion==-1){
            return (false);
        }else{
            listaNombres.set(posicion,newName);
            return (true);

        }
    }
  
    public String getName(int indice){
        return (listaNombres.get(indice));
    }


}

class ListaFechasNacimiento{

    //Atributos
    private String fecha;
    private String nombreListaF;
    private ArrayList <String> listaNacimientos;

    //Metodos

    public ListaFechasNacimiento(String nombreF){
        nombreListaF=nombreF;
        listaNacimientos=new ArrayList <String> ();
    }

    public void addFecha(String fecha){
        listaNacimientos.add(fecha);

    }

    public String getFecha(int indice){
        return (listaNacimientos.get(indice));
    }

}


public class Main {
    public static void main(String[] args){

        String nlist,busca,nombre,fech;
        Scanner stdin=new Scanner(System.in);

        //Creando la instancia para crear los objetos lista
        ListaNombres nlista=new ListaNombres("ListadeNombres");
        ListaFechasNacimiento flista=new ListaFechasNacimiento("ListadeFechas");
       
        //guardando datos en la lista
        nombre=stdin.nextLine();
        nlista.addName(nombre);
        fech=stdin.nextLine();
        flista.addFecha(fech);
    
        /*buscar un nombre dentro de la lista
        System.out.println("Buscar nombre:")
        busca=stdin.nextLine();

        //se obtiene la posicion del nombre en la lista
        int posicion=nlista.findName(busca);
      
        if(posicion!=-1){//si se encontro el nombre*/
            String name=nlista.getName(0);
            //partimos el nombre completo con el metodo Split
            String[] listaTokens=name.split(" ");
            String nNombre=listaTokens[0];
            
            //--------Quitar casos especiales DE,DEL,LA
            //pasamos a una lista el array listaTokens
            List<String> token_List = new ArrayList<String>(Arrays.asList(listaTokens)); 
            //se busca la palabra "DE", "DEL","LA" y se eliminan
            int de=token_List.indexOf("DE");
            if(de!=-1)token_List.remove(de);
            int del=token_List.indexOf("DEL");
            if(del!=-1)token_List.remove(del);
            int la=token_List.indexOf("LA");
            if(la!=-1)token_List.remove(la);

            //Creamos un nuevo string para el nombre sin casos especiales
            String newlistaTokens[] = new String[token_List.size()]; 
            // Convertimos ArrayList en array 
            Object[] objArr = token_List.toArray(); 
            // Llenamos el nuevo arreglo 
            int i = 0; 
            for (Object obj : objArr) { 
                newlistaTokens[i++] = (String)obj; 
            } 
            //casos especiales para el nombre
            if( (nNombre=="JUAN" || nNombre=="MARIA") && (newlistaTokens.length>3) ){
                nNombre=newlistaTokens[1];
            }
            //separacion en variables de ap. paterno,ap. materno y fecha
            String apM=newlistaTokens[newlistaTokens.length-1];
            String apP=newlistaTokens[newlistaTokens.length-2];
            String date=flista.getFecha(0);

            //---GENRANDO EL RFC--------------------

            //obtención de primera letra y vocal del apP 

            String rfc=String.valueOf(apP.charAt(0));
            //convierto apP a array de tipo Char
            char[] capP=apP.toCharArray();
            
            for(int k=1;k<apP.length();k++){	
                if((capP[k]=='A')||(capP[k]=='E')||(capP[k]=='I')||(capP[k]=='O')||(capP[k]=='U'))
                {
                    rfc=rfc+capP[k];
                    break;
                }  
            }
            //agregando primera letra del apM
            rfc=rfc+String.valueOf(apM.charAt(0));
            //agregando primera letra del nombre
            rfc=rfc+String.valueOf(nNombre.charAt(0));

            //agregando la fecha de nacimiento
            rfc=rfc+String.valueOf(date.charAt(8));
            rfc=rfc+String.valueOf(date.charAt(9));
            rfc=rfc+String.valueOf(date.charAt(3));
            rfc=rfc+String.valueOf(date.charAt(4));
            rfc=rfc+String.valueOf(date.charAt(0));
            rfc=rfc+String.valueOf(date.charAt(1));

            System.out.println(rfc);
           
           
        //}else{
            //System.out.println("NO lo encontro");
        //}

    }
}