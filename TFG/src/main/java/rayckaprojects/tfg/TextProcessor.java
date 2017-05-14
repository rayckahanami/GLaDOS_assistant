package rayckaprojects.tfg;

/**
 * Created by RayckaPC on 01/20/17.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TextProcessor {

    static TextToSpeech t1;
    private static Context cont;
    private static CallMethods callMe= new CallMethods();

    public TextProcessor(Context cont) {
        this.cont = cont;
    }

    public void StringProcessor(String SpeechToTexto, SharedPreferences SP,SharedPreferences SPTEL) {

        DatabaseHandler db = new DatabaseHandler(cont,"Comands");
        int countActual = db.getComandsCount();
  //      Toast.makeText(cont,"Actualmente hay "+ String.valueOf(countActual) +"asignaciones de texto a comando", Toast.LENGTH_SHORT).show();
        List<Comand> comandlist = db.getAllComands();
        boolean flag = false;
        for (int i = 0; i < countActual; i++) {
            String help = comandlist.get(i).getComandName();
            if (SpeechToTexto.contains(help)) {
                if (SpeechToTexto.indexOf(help) == 0 ) {
                    flag = true;
                    String textValue = comandlist.get(i).getComandText();
                    //           Toast.makeText(cont, "La comanda existe y es ", Toast.LENGTH_SHORT).show();
                    //           Toast.makeText(cont, textValue, Toast.LENGTH_SHORT).show();
                    menuReconocido(textValue, SpeechToTexto, SP, SPTEL);
                }
            }
        }
        if (flag == false) {
            Toast.makeText(cont, "Comando no reconocido", Toast.LENGTH_SHORT).show();

        }
    }

    public static void menuReconocido(String textValue, String StringToText, SharedPreferences SP,SharedPreferences SPTEL) {

        DatabaseHandler db = new DatabaseHandler(cont,"Comands");

        if (textValue.equalsIgnoreCase("call")) {
            callMe.call(cont, StringToText, SP);

        }else if (textValue.equalsIgnoreCase("alarm")) {
            //USAGE: ALARMA a las nueve 40
            Toast.makeText(cont, "Comando alarm", Toast.LENGTH_SHORT).show();

            String helper =StringToText.replace("alarma a las ","");
            String[] arrayhelp = helper.split(" ");
            String[] horacompleta = arrayhelp[0].split(":");
            helper = helper.replace(arrayhelp[0],"");

            Toast.makeText(cont, helper, Toast.LENGTH_SHORT).show();
            Toast.makeText(cont, arrayhelp[0], Toast.LENGTH_SHORT).show();

            Intent i = new Intent(cont,alarma2Activity.class);
            i.putExtra("type","alarm");
            i.putExtra("hora",horacompleta[0]);
            i.putExtra("min",horacompleta[1]);
            i.putExtra("mensaje",helper);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cont.startActivity(i);

        }else if (textValue.equalsIgnoreCase("timer")) {
            //USAGE: temporizador 10 minutos
            int hora = 0;
            int minutos = 0;
            String helper =StringToText.replace("temporizador ","");
            String[] helperArray = helper.split(" ");
            if(helperArray.length == 4){

                if(helperArray[1]=="hora"){
                    hora=1;
                }else {
                    hora = Integer.parseInt(helperArray[0]);
                }if(helperArray[3]=="minuto"){
                    minutos=1;
                }else {
                    minutos = Integer.parseInt(helperArray[2]);
                }

            }else  if(helperArray.length == 2){
                if(helperArray[1]=="hora" || helperArray[1]=="horas"){
                    if(helperArray[1]=="hora"){
                        hora=1;
                    }else {
                        hora=Integer.parseInt(helperArray[0]);
                    }
                    minutos=0;
                }else if(helperArray[1]=="minuto" || helperArray[1]=="minutos"){
                    if(helperArray[1]=="minuto"){
                        minutos=1;
                    }else {
                        minutos=Integer.parseInt(helperArray[0]);
                    }
                    hora=0;
                }
            }

            Toast.makeText(cont, "Comando timer", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(cont,alarma2Activity.class);
            i.putExtra("type","timer");
            i.putExtra("hora",hora);
            i.putExtra("minutos",minutos);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cont.startActivity(i);

        } else if (textValue.equalsIgnoreCase("showcomands")){
            List<Comand> comandlistA = db.getAllComands();

            Intent i = new Intent(cont,ComandShowActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            ArrayList<Comand> aComands = new ArrayList<Comand>();
            for(int y = 0; y < comandlistA.size(); y++){
                aComands.add(comandlistA.get(y));
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", aComands);
            i.putExtras(bundle);
            cont.startActivity(i);

        }else if (textValue.equalsIgnoreCase("DeleteAll")) {
            db.deleteAll();
    //        Toast.makeText(cont, "borrados", Toast.LENGTH_SHORT).show();

        }else if(textValue.equalsIgnoreCase("addcomand")){
            String helper =StringToText.replace("añadir comando","");
            helper.replace(" ","");
            if (!helper.isEmpty()) {
                String array[] = helper.split(" ");
                String comand = array[array.length - 1];
                String text = helper.replace(comand, "");
                text = text.trim();
                int count = db.getComandsCount() + 1;
                db.addComand(new Comand(count, text, comand));
            }else {
                Toast.makeText(cont, "No se puede añadir un comando vacio", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(cont, "id: "+db.getComand(count).getComand_id()+", caller: "+db.getComand(count).getComandName()+", Value: "+db.getComand(count).getComandText(), Toast.LENGTH_SHORT).show();

        }else if(textValue.equalsIgnoreCase("calendar")){
            Toast.makeText(cont, "Comando calendar", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(cont,CalendarViewActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cont.startActivity(i);
        } else if (textValue.equalsIgnoreCase("mail")){
            Toast.makeText(cont, "Comando mail", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(cont,MailActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            cont.startActivity(i);
        }else {
            Toast.makeText(cont, "no existe comando", Toast.LENGTH_SHORT).show();
        }

    }
}
