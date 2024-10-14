package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "listaCasetas")
public class ListaCasetas {
    @XmlElement(name = "caseta")
    private List<Caseta> casetas;

    public ListaCasetas() {
        this.casetas = new ArrayList<Caseta>();
    }

    public List<Caseta> getCasetas() {
        return casetas;
    }

    public void leerDatosCasetas(String ruta){
        try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea;
            int id = 1;
           while((linea = br.readLine()) != null){
               String[] listita = linea.split("-");
               Caseta casetita = new Caseta(id,listita[0],listita[1],Integer.parseInt(listita[2].trim()),listita[3]);
               this.casetas.add(casetita);
               id++;
           }
        }catch (FileNotFoundException e){
            Auxiliares.ficheroNoExiste();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCasetas(){
        for(Caseta c: casetas){
            System.out.println(c.toString());
        }
    }




}
