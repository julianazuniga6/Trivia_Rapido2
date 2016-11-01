package juliana.com.tmp.Modelo;

import java.util.ArrayList;

/**
 * Created by Juliana on 27/10/2016.
 */

public class Pregunta {

    public final static int CANT=3;

    private String enunciado;
    private String ok;
    private int posOK;

    private ArrayList<String> opcIncorrectas;

    public Pregunta(String[] w, String ok, String enunciado){

        opcIncorrectas= new ArrayList<String>();

        for (int i=0; i<w.length; i++){
            opcIncorrectas.add(w[i]);
        }

        this.enunciado= enunciado;
        this.ok=ok;
    }

    public String[] getOpciones() {
        posOK= (int)(Math.random()*3);
        String[] opciones= new String[CANT];
        for (int i=0; i<opciones.length; i++){
            opciones[i]=null;
        }
        opciones[posOK]=ok;
        for (int i=0; i<opciones.length; i++){
            if(opciones[i]==null){
                int temp=(int)(Math.random()*(opcIncorrectas.size()));
                String q= opcIncorrectas.get(temp);
                opcIncorrectas.remove(temp);
                opciones[i]=q;
            }
        }
        return opciones;
    }

    public boolean validar(int pos){
        return pos==posOK;
    }
    public String getEnunciado(){return enunciado;}
    public int getPosOK(){return posOK;}
    public void printPregunta(){
        System.out.print(enunciado);
        for (int i=0; i<opcIncorrectas.size();i++){
            System.out.print(opcIncorrectas.get(i));
        }
        System.out.print(ok);
    }

}
