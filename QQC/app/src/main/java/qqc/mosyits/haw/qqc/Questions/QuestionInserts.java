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

    //QUESTION 2
    private static final String Q2 = "Jeder ist seines Glückes...";
    private static final String Q2AR = "Schmied";
    private static final String Q2A1 = "Bäcker";
    private static final String Q2A2 = "Bauer";
    private static final String Q2A3 = "Maler";

    //QUESTION 3
    private static final String Q3 = "Knapp daneben...";
    private static final String Q3AR = "ist auch vorbei";
    private static final String Q3A1 = "ist nicht schlimm";
    private static final String Q3A2 = "passiert jedem";
    private static final String Q3A3 = "ist halt daneben";

    //QUESTION 4
    private static final String Q4 = "Aller guten Dinge sind...";
    private static final String Q4AR = "3";
    private static final String Q4A1 = "1";
    private static final String Q4A2 = "2";
    private static final String Q4A3 = "4";

    //QUESTION 5
    private static final String Q5 = "Auch ein blindes Huhn findet mal...";
    private static final String Q5AR = "ein Korn";
    private static final String Q5A1 = "einen Nagel";
    private static final String Q5A2 = "eine Nadel";
    private static final String Q5A3 = "eine Reiszwecke";

    //QUESTION 6
    private static final String Q6 = "Da wird doch der ... in der Pfanne verrückt";
    private static final String Q6AR = "Hund";
    private static final String Q6A1 = "Hahn";
    private static final String Q6A2 = "Esel";
    private static final String Q6A3 = "Hase";

    //QUESTION 7
    private static final String Q7 = "In der Not frisst der Teufel...";
    private static final String Q7AR = "Fliegen";
    private static final String Q7A1 = "Bienen";
    private static final String Q7A2 = "Einhörner";
    private static final String Q7A3 = "Frösche";

    //QUESTION 8
    private static final String Q8 = "Lügen haben...";
    private static final String Q8AR = "kurze Beine";
    private static final String Q8A1 = "lange Beine";
    private static final String Q8A2 = "keine Beine";
    private static final String Q8A3 = "unterschiedlich lange Beine";

    //QUESTION 9
    private static final String Q9 = "Der Apfel fällt nicht weit vom...";
    private static final String Q9AR = "Stamm";
    private static final String Q9A1 = "Baum";
    private static final String Q9A2 = "Ast";
    private static final String Q9A3 = "Zweig";

    //QUESTION 10
    private static final String Q10 = "QUESTION 10";
    private static final String Q10AR = "Q10";
    private static final String Q10A1 = "Q10";
    private static final String Q10A2 = "Q10";
    private static final String Q10A3 = "Q10";

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

    //QUESTION 20
    private static final String Q20 = "QUESTION 20";
    private static final String Q20AR = "Q20";
    private static final String Q20A1 = "Q20";
    private static final String Q20A2 = "Q20";
    private static final String Q20A3 = "Q20";


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
    private static final String Q33 = "Question 33";
    private static final String Q33AR = "Q33";
    private static final String Q33A1 = "Q33";
    private static final String Q33A2 = "Q33";
    private static final String Q33A3 = "Q33";

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

    //QUESTION 41
    private static final String Q41 = "Wenn zwei sich streiten,...";
    private static final String Q41AR = "freut sich der Dritte";
    private static final String Q41A1 = "freue ich mich";
    private static final String Q41A2 = "freuen sich die anderen";
    private static final String Q41A3 = "freut sich keiner";

    //QUESTION 42
    private static final String Q42 = "Wie viele Bundesländer hat Deutschland?";
    private static final String Q42AR = "16";
    private static final String Q42A1 = "17";
    private static final String Q42A2 = "14";
    private static final String Q42A3 = "21";

    //QUESTION 43
    private static final String Q43 = "Wie heißt die einzige deutsche Hochseeinsel?";
    private static final String Q43AR = "Helgoland";
    private static final String Q43A1 = "Sylt";
    private static final String Q43A2 = "Rügen";
    private static final String Q43A3 = "Föhr";

    //QUESTION 44
    private static final String Q44 = "Welche Stadt ist die nördlichste in Deutschland?";
    private static final String Q44AR = "Flensburg";
    private static final String Q44A1 = "Kiel";
    private static final String Q44A2 = "Husum";
    private static final String Q44A3 = "Cuxhaven";

    //QUESTION 45
    private static final String Q45 = "Welches ist das größte Bundesland?";
    private static final String Q45AR = "Bayern";
    private static final String Q45A1 = "Niedersachsen";
    private static final String Q45A2 = "Baden-Würtemberg";
    private static final String Q45A3 = "Sachsen";

    //QUESTION 46
    private static final String Q46 = "Wie viele deutsche Städte sind offiziell Hansestädte?";
    private static final String Q46AR = "25";
    private static final String Q46A1 = "3";
    private static final String Q46A2 = "5";
    private static final String Q46A3 = "12";

    //QUESTION 47
    private static final String Q47 = "Welches Tier wird Reinecke in Fabeln genannt";
    private static final String Q47AR = "Fuchs";
    private static final String Q47A1 = "Hahn";
    private static final String Q47A2 = "Biber";
    private static final String Q47A3 = "Dachs";

    //QUESTION 48
    private static final String Q48 = "Welches Tier gilt in Fabeln als störrisch und faul";
    private static final String Q48AR = "Esel";
    private static final String Q48A1 = "Ente";
    private static final String Q48A2 = "Bär";
    private static final String Q48A3 = "Lamm";

    //QUESTION 49
    private static final String Q49 = "Wie nennt man den Hasen in Fabeln";
    private static final String Q49AR = "Meister Lampe";
    private static final String Q49A1 = "Isegrim";
    private static final String Q49A2 = "Meister Petz";
    private static final String Q49A3 = "Henning";


    public QuestionInserts(Context context, int round) {
        switch (round) {
            case 0:
                new Question(context, Q1, Q1AR, Q1A1, Q1A2, Q1A3, 0);
                new Question(context, Q2, Q2AR, Q2A1, Q2A2, Q2A3, 1);
                new Question(context, Q3, Q3AR, Q3A1, Q3A2, Q3A3, 2);
                new Question(context, Q4, Q4AR, Q4A1, Q4A2, Q4A3, 3);
                new Question(context, Q5, Q5AR, Q5A1, Q5A2, Q5A3, 4);
                new Question(context, Q6, Q6AR, Q6A1, Q6A2, Q6A3, 5);
                new Question(context, Q7, Q7AR, Q7A1, Q7A2, Q7A3, 6);
                new Question(context, Q8, Q8AR, Q8A1, Q8A2, Q8A3, 7);
                new Question(context, Q9, Q9AR, Q9A1, Q9A2, Q9A3, 8);
                new Question(context, Q10, Q10AR, Q10A1, Q10A2, Q10A3, 9);
                break;
            case 1:
                new Question(context, Q11, Q11AR, Q11A1, Q11A2, Q11A3, 0);
                new Question(context, Q12, Q12AR, Q12A1, Q12A2, Q12A3, 1);
                new Question(context, Q13, Q13AR, Q13A1, Q13A2, Q13A3, 2);
                new Question(context, Q14, Q14AR, Q14A1, Q14A2, Q14A3, 3);
                new Question(context, Q15, Q15AR, Q15A1, Q15A2, Q15A3, 4);
                new Question(context, Q16, Q16AR, Q16A1, Q16A2, Q16A3, 5);
                new Question(context, Q17, Q17AR, Q17A1, Q17A2, Q17A3, 6);
                new Question(context, Q18, Q18AR, Q18A1, Q18A2, Q18A3, 7);
                new Question(context, Q19, Q19AR, Q19A1, Q19A2, Q19A3, 8);
                new Question(context, Q20, Q20AR, Q20A1, Q20A2, Q20A3, 9);
                break;
            case 2:
                new Question(context, Q21, Q21AR, Q21A1, Q21A2, Q21A3, 0);
                new Question(context, Q22, Q22AR, Q22A1, Q22A2, Q22A3, 1);
                new Question(context, Q23, Q23AR, Q23A1, Q23A2, Q23A3, 2);
                new Question(context, Q24, Q24AR, Q24A1, Q24A2, Q24A3, 3);
//        new Question(context, Q25, Q25AR, Q25A1, Q25A2, Q25A3, 4);
//        new Question(context, Q26, Q26AR, Q26A1, Q26A2, Q26A3, 5);
//        new Question(context, Q27, Q27AR, Q27A1, Q27A2, Q27A3, 6);
//        new Question(context, Q28, Q28AR, Q28A1, Q28A2, Q28A3, 7);
//        new Question(context, Q29, Q29AR, Q29A1, Q29A2, Q29A3, 8);
//        new Question(context, Q30, Q30AR, Q30A1, Q30A2, Q30A3, 9);
            case 3:
                new Question(context, Q31, Q31AR, Q31A1, Q31A2, Q31A3, 0);
                new Question(context, Q32, Q32AR, Q32A1, Q32A2, Q32A3, 1);
                new Question(context, Q33, Q33AR, Q33A1, Q33A2, Q33A3, 2);
                new Question(context, Q34, Q34AR, Q34A1, Q34A2, Q34A3, 3);
                new Question(context, Q35, Q35AR, Q35A1, Q35A2, Q35A3, 4);
                new Question(context, Q36, Q36AR, Q36A1, Q36A2, Q36A3, 5);
//        new Question(context, Q37, Q37AR, Q37A1, Q37A2, Q37A3, 6);
//        new Question(context, Q38, Q38AR, Q38A1, Q38A2, Q38A3, 7);
//        new Question(context, Q39, Q39AR, Q39A1, Q39A2, Q39A3, 8);
//        new Question(context, Q40, Q40AR, Q40A1, Q40A2, Q40A3, 9);
                alreadyAddedToDatabase = true;
        }
    }
}
