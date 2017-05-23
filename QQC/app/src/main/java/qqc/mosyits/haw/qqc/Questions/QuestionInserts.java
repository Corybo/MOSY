package qqc.mosyits.haw.qqc.Questions;

import android.content.Context;

/**
 * Drawing up the questions for database
 */

public class QuestionInserts {
    private boolean alreadyAddedToDatabase = false;

    //QUESTION 1
    private static final String Q1 = "Welches Getränkemarke hat die Farbe rot";
    private static final String Q1AR = "CocaCola";
    private static final String Q1A1 = "Fanta";
    private static final String Q1A2 = "Sprite";
    private static final String Q1A3 = "Hella naturell";

    //QUESTION 11
    private static final String Q11 = "Ergänze: Wo ein Wille ist, ist auch ...";
    private static final String Q11AR = "...ein Weg.";
    private static final String Q11A1 = "...ein Pfad.";
    private static final String Q11A2 = "...eine Straße.";
    private static final String Q11A3 = "...eine Autobahn.";

    //QUESTION 12
    private static final String Q12 = "Ergänze: Was du heute kannst besorgen, das verschiebe nicht auf ...";
    private static final String Q12AR = "...morgen";
    private static final String Q12A1 = "...Dienstag";
    private static final String Q12A2 = "...übermorgen";
    private static final String Q12A3 = "...Montag";

    //QUESTION 13
    private static final String Q13 = "Ergänze: Wer nicht hören will, muss ...";
    private static final String Q13AR = "…fühlen";
    private static final String Q13A1 = "…riechen";
    private static final String Q13A2 = "…schmecken";
    private static final String Q13A3 = "…sehen";

    //QUESTION 14
    private static final String Q14 = "Wo wohnt der Hamburger Musiker Udo Lindenberg?";
    private static final String Q14AR = "Im Hotel Atlantic";
    private static final String Q14A1 = "In der HafenCity";
    private static final String Q14A2 = "In der Schanze";
    private static final String Q14A3 = "Im Hotel Vier Jahreszeiten";

    //QUESTION 15
    private static final String Q15 = "Welcher Fluss verbindet Hamburg mit der Nordsee?";
    private static final String Q15AR = "Die Elbe";
    private static final String Q15A1 = "Der Rhein";
    private static final String Q15A2 = "Die Alster";
    private static final String Q15A3 = "Die Donau";

    //QUESTION 16
    private static final String Q16 = "Wie hoch waren die endgültigen Baukosten der Elbphilharmonie?";
    private static final String Q16AR = "789 Millionen";
    private static final String Q16A1 = "1 Milliarde";
    private static final String Q16A2 = "186 Millionen";
    private static final String Q16A3 = "500 Millionen";

    //QUESTION 17
    private static final String Q17 = "Ergänze: Auf der Reeperbahn nachts um halb ...";
    private static final String Q17AR = "1";
    private static final String Q17A1 = "2";
    private static final String Q17A2 = "3";
    private static final String Q17A3 = "4";

    //QUESTION 18
    private static final String Q18 = "Welcher der folgenden Zeichentrickfilme war der erste erfolgreiche Film der Walt-Disney-Studios?";
    private static final String Q18AR = "Schneewittchen und die sieben Zwerge";
    private static final String Q18A1 = "Cinderella";
    private static final String Q18A2 = "König der Löwen";
    private static final String Q18A3 = "Pinocchio";

    //QUESTION 19
    private static final String Q19 = "Ab Ende Juni wird es in Deutschland von Tag zu Tag ...";
    private static final String Q19AR = "später hell und früher dunkel";
    private static final String Q19A1 = "früher hell und später dunkel";
    private static final String Q19A2 = "früher hell und früher dunkel";
    private static final String Q19A3 = "später hell und später dunkel";

    //QUESTION 21
    private static final String Q21 = "Was bedeutet die Abkürzung EEK?";
    private static final String Q21AR = "Erneuerbare Energien Klasse";
    private static final String Q21A1 = "Elbe-Einkaufszentrum";
    private static final String Q21A2 = "Elektrische Eisenkonstruktion";
    private static final String Q21A3 = "Energieeffizienzklasse";

    //QUESTION 22
    private static final String Q22 = "Wie viele Bachelor-Studiengänge bietet die HAW?";
    private static final String Q22AR = "42";
    private static final String Q22A1 = "27 ";
    private static final String Q22A2 = "35";
    private static final String Q22A3 = "13";

    //QUESTION 23
    private static final String Q23 = "Wie viele Studierende hat die HAW?";
    private static final String Q23AR = "etwa 16800";
    private static final String Q23A1 = "etwa 11300";
    private static final String Q23A2 = "etwa 2905";
    private static final String Q23A3 = "etwa 22700";

    //QUESTION 24
    private static final String Q24 = "Wann wurde die Fachhochschule Hamburg (HAW) gegründet?";
    private static final String Q24AR = "1970";
    private static final String Q24A1 = "1902";
    private static final String Q24A2 = "1850";
    private static final String Q24A3 = "2001";

    //QUESTION 31
    private static final String Q31 = "Wie viele Standorte hat die HAW?";
    private static final String Q31AR = "4";
    private static final String Q31A1 = "3";
    private static final String Q31A2 = "7";
    private static final String Q31A3 = "2";

    //QUESTION 32
    private static final String Q32 = "Weshalb war das Gebäude Berliner Tor 5 von 2001 bis 2009 mit einem Gerüst umbaut?";
    private static final String Q32AR = "Glasplatten fielen aus der Fassade.";
    private static final String Q32A1 = "Der Bauträger ging pleite.";
    private static final String Q32A2 = "Die Wände waren undicht.";
    private static final String Q32A3 = "Das Gebäude wurde in dieser Zeit gebaut.";

    //QUESTION 33
    private static final String Q33 = "Wer ist geschäftsführender Präsident der HAW?";
    private static final String Q33AR = "Claus-Dieter Wacker";
    private static final String Q33A1 = "Jacqueline Otten";
    private static final String Q33A2 = "Bernd Klöver";
    private static final String Q33A3 = "Bernd Höcke";

    //QUESTION 34
    private static final String Q34 = "Welche Schriftart wird im HAW Logo genutzt?";
    private static final String Q34AR = "Frutiger NEXT";
    private static final String Q34A1 = "ComicSans";
    private static final String Q34A2 = "Helvetica Neue";
    private static final String Q34A3 = "Gill Sans";

    //QUESTION 35
    private static final String Q35 = "Wann wurde das HAW Logo zuletzt angepasst??";
    private static final String Q35AR = "2017";
    private static final String Q35A1 = "1970";
    private static final String Q35A2 = "2002";
    private static final String Q35A3 = "2010";

    //QUESTION 36
    private static final String Q36 = "Wieso wäre die E-Learning Plattform 'EMIL' Ende 2016 beinahe abgeschaltet worden?";
    private static final String Q36AR = "Vertragsanpassung durch VG Wort";
    private static final String Q36A1 = "Zu geringe Nutzung";
    private static final String Q36A2 = "Virenbefall";
    private static final String Q36A3 = "Zu hohe Betriebskosten";


    public QuestionInserts(Context context) {
        if(!alreadyAddedToDatabase) {
            new Question(context, Q1, Q1AR, Q1A1, Q1A2, Q1A3);
//        new Question(context, Q2, Q2AR, Q2A1, Q2A2, Q2A3);
//        new Question(context, Q3, Q3AR, Q3A1, Q3A2, Q3A3);
//        new Question(context, Q4, Q4AR, Q4A1, Q4A2, Q4A3);
//        new Question(context, Q5, Q5AR, Q5A1, Q5A2, Q5A3);
//        new Question(context, Q6, Q6AR, Q6A1, Q6A2, Q6A3);
//        new Question(context, Q7, Q7AR, Q7A1, Q7A2, Q7A3);
//        new Question(context, Q8, Q8AR, Q8A1, Q8A2, Q8A3);
//        new Question(context, Q9, Q9AR, Q9A1, Q9A2, Q9A3);
//        new Question(context, Q10, Q10AR, Q10A1, Q10A2, Q10A3);
            new Question(context, Q11, Q11AR, Q11A1, Q11A2, Q11A3);
            new Question(context, Q12, Q12AR, Q12A1, Q12A2, Q12A3);
            new Question(context, Q13, Q13AR, Q13A1, Q13A2, Q13A3);
            new Question(context, Q14, Q14AR, Q14A1, Q14A2, Q14A3);
            new Question(context, Q15, Q15AR, Q15A1, Q15A2, Q15A3);
            new Question(context, Q16, Q16AR, Q16A1, Q16A2, Q16A3);
            new Question(context, Q17, Q17AR, Q17A1, Q17A2, Q17A3);
            new Question(context, Q18, Q18AR, Q18A1, Q18A2, Q18A3);
            new Question(context, Q19, Q19AR, Q19A1, Q19A2, Q19A3);
//        new Question(context, Q20, Q20AR, Q20A1, Q20A2, Q20A3);
            new Question(context, Q21, Q21AR, Q21A1, Q21A2, Q21A3);
            new Question(context, Q22, Q22AR, Q22A1, Q22A2, Q22A3);
            new Question(context, Q23, Q23AR, Q23A1, Q23A2, Q23A3);
            new Question(context, Q24, Q24AR, Q24A1, Q24A2, Q24A3);
//        new Question(context, Q25, Q25AR, Q25A1, Q25A2, Q25A3);
//        new Question(context, Q26, Q26AR, Q26A1, Q26A2, Q26A3);
//        new Question(context, Q27, Q27AR, Q27A1, Q27A2, Q27A3);
//        new Question(context, Q28, Q28AR, Q28A1, Q28A2, Q28A3);
//        new Question(context, Q29, Q29AR, Q29A1, Q29A2, Q29A3);
//        new Question(context, Q30, Q30AR, Q30A1, Q30A2, Q30A3);
            new Question(context, Q31, Q31AR, Q31A1, Q31A2, Q31A3);
            new Question(context, Q32, Q32AR, Q32A1, Q32A2, Q32A3);
            new Question(context, Q33, Q33AR, Q33A1, Q33A2, Q33A3);
            new Question(context, Q34, Q34AR, Q34A1, Q34A2, Q34A3);
            new Question(context, Q35, Q35AR, Q35A1, Q35A2, Q35A3);
            new Question(context, Q36, Q36AR, Q36A1, Q36A2, Q36A3);
//        new Question(context, Q37, Q37AR, Q37A1, Q37A2, Q37A3);
//        new Question(context, Q38, Q38AR, Q38A1, Q38A2, Q38A3);
//        new Question(context, Q39, Q39AR, Q39A1, Q39A2, Q39A3);
//        new Question(context, Q40, Q40AR, Q40A1, Q40A2, Q40A3);
            alreadyAddedToDatabase = true;
        }
    }
}
