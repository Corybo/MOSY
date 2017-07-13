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
    private static final String Q2 = "QUESTION 2";
    private static final String Q2AR = "Q2";
    private static final String Q2A1 = "Q2";
    private static final String Q2A2 = "Q2";
    private static final String Q2A3 = "Q2";

    //QUESTION 3
    private static final String Q3 = "QUESTION 3";
    private static final String Q3AR = "Q3";
    private static final String Q3A1 = "Q3";
    private static final String Q3A2 = "Q3";
    private static final String Q3A3 = "Q3";

    //QUESTION 4
    private static final String Q4 = "QUESTION 4";
    private static final String Q4AR = "Q4";
    private static final String Q4A1 = "Q4";
    private static final String Q4A2 = "Q4";
    private static final String Q4A3 = "Q4";

    //QUESTION 5
    private static final String Q5 = "QUESTION 5";
    private static final String Q5AR = "Q5";
    private static final String Q5A1 = "Q5";
    private static final String Q5A2 = "Q5";
    private static final String Q5A3 = "Q5";

    //QUESTION 6
    private static final String Q6 = "QUESTION 6";
    private static final String Q6AR = "Q6";
    private static final String Q6A1 = "Q6";
    private static final String Q6A2 = "Q6";
    private static final String Q6A3 = "Q6";

    //QUESTION 7
    private static final String Q7 = "QUESTION 7";
    private static final String Q7AR = "Q7";
    private static final String Q7A1 = "Q7";
    private static final String Q7A2 = "Q7";
    private static final String Q7A3 = "Q7";

    //QUESTION 8
    private static final String Q8 = "QUESTION 8";
    private static final String Q8AR = "Q8";
    private static final String Q8A1 = "Q8";
    private static final String Q8A2 = "Q8";
    private static final String Q8A3 = "Q8";

    //QUESTION 9
    private static final String Q9 = "QUESTION 9";
    private static final String Q9AR = "Q9";
    private static final String Q9A1 = "Q9";
    private static final String Q9A2 = "Q9";
    private static final String Q9A3 = "Q9";

    //QUESTION 10
    private static final String Q10 = "Wie schafft die Hauptperson Karlsson in \"Karlsson vom Dach\" zu fliegen?";
    private static final String Q10AR = "er hat einen Propeller auf dem Rücken";
    private static final String Q10A1 = "mit einem Flugzeug";
    private static final String Q10A2 = "er hat Flügel";
    private static final String Q10A3 = "auf einem fliegenden Teppich";

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
    private static final String Q20 = "In welcher Stadt spielt der Disneyfilm Glöckner von Notre-Dame?";
    private static final String Q20AR = "Paris";
    private static final String Q20A1 = "Lyon";
    private static final String Q20A2 = "Marseille";
    private static final String Q20A3 = "Nancy";


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

    //QUESTION 25
    private static final String Q25 = "Wie viele Einwohner hat Hamburg?";
    private static final String Q25AR = "ca. 1,8 Millionen";
    private static final String Q25A1 = "ca. 18 Millionen";
    private static final String Q25A2 = "ca. 3,5 Millionen ";
    private static final String Q25A3 = "ca. 1,5 Millionen ";

    //QUESTION 26
    private static final String Q26 = "Wie heißt die größte Hamburger Tageszeitung?";
    private static final String Q26AR = "Hamburger Abendblatt";
    private static final String Q26A1 = "Bild";
    private static final String Q26A2 = "MOPO";
    private static final String Q26A3 = "Hamburger Tageblatt";

    //QUESTION 27
    private static final String Q27 = "Wo findet der Hamburger Dom statt?";
    private static final String Q27AR = "Heiligengeistfeld";
    private static final String Q27A1 = "St. Petri Kirche";
    private static final String Q27A2 = "Hans Albert Platz";
    private static final String Q27A3 = "Michel";

    //QUESTION 28
    private static final String Q28 = "Wie viele Stadtteile hat Hamburg?";
    private static final String Q28AR = "104";
    private static final String Q28A1 = "7";
    private static final String Q28A2 = "89";
    private static final String Q28A3 = "17";

    //QUESTION 29
    private static final String Q29 = "Wie heißt der Angriff, der in Hamburg im 2. Weltkrieg große Teile der Stadt zerstörte und ca. 50.000 Menschen das Leben kostete?";
    private static final String Q29AR = "Operation Gomorrha";
    private static final String Q29A1 = "Sodoms Revenge";
    private static final String Q29A2 = "Angriff Feuersturm";
    private static final String Q29A3 = "Code Hamburg";

    //QUESTION 30
    private static final String Q30 = "Wer war KEIN Ehrenbürger von Hamburg?";
    private static final String Q30AR = "Helmut Kohl";
    private static final String Q30A1 = "Helmut Schmidt";
    private static final String Q30A2 = "Adolf Hitler";
    private static final String Q30A3 = "Max Brauer";

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
    private static final String Q33 = "Welcher HAW Campus soll komplett erneuert werden?";
    private static final String Q33AR = "Berliner Tor";
    private static final String Q33A1 = "Finkenau";
    private static final String Q33A2 = "Armgartstraße";
    private static final String Q33A3 = "HafenCity";

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

    //QUESTION 37
    private static final String Q37 = "An welchem Campus wird an der HAW Kostümdesign gelehrt?";
    private static final String Q37AR = "Armgartstraße";
    private static final String Q37A1 = "Schlump";
    private static final String Q37A2 = "Berliner Tor";
    private static final String Q37A3 = "Friedrichshain";

    //QUESTION 38
    private static final String Q38 = "Bis wann wurden die Gebäude in der Finkenau 35 als Krankenhaus genutzt?";
    private static final String Q38AR = "2000";
    private static final String Q38A1 = "2013";
    private static final String Q38A2 = "1899";
    private static final String Q38A3 = "1993";

    //QUESTION 39
    private static final String Q39 = "Welcher neuer Master-Studiengang soll 2018 in DMI angeboten werden?";
    private static final String Q39AR = "Virtual Reality";
    private static final String Q39A1 = "Sound Vision";
    private static final String Q39A2 = "Keiner";
    private static final String Q39A3 = "Cyber Warfare";

    //QUESTION 40
    private static final String Q40 = "Wie viele Schulen haben einen festen Sitz am Campus FInkenau 35?";
    private static final String Q40AR = "4";
    private static final String Q40A1 = "2";
    private static final String Q40A2 = "1";
    private static final String Q40A3 = "6";

    //QUESTION 51
    private static final String Q51 = "Welche Haarfarbe hat das Biest aus \"Die Schöne und das Biest\" nachdem es sich in einen wundervollen Prinzen verwandelt hat?";
    private static final String Q51AR = "rothaarig";
    private static final String Q51A1 = "schwarz";
    private static final String Q51A2 = "blond";
    private static final String Q51A3 = "brünett";

    //QUESTION 52
    private static final String Q52 = "Wie heißt die Seehexe bei Arielle?";
    private static final String Q52AR = "Ursula";
    private static final String Q52A1 = "Olga";
    private static final String Q52A2 = "Petra";
    private static final String Q52A3 = "Beatrix";

    //QUESTION 53
    private static final String Q53 = "Mit welchem Gegenstand wird in \"Rotkäppchen\" dem bösen Wolf der Bauch gefüllt?";
    private static final String Q53AR = "Steine";
    private static final String Q53A1 = "Flaschenpfand";
    private static final String Q53A2 = "Holz";
    private static final String Q53A3 = "Wasser";

    //QUESTION 54
    private static final String Q54 = "Wo versteckte sich das siebte Geißlein im Märchen \"Der Wolf und die sieben Geißlein\"?";
    private static final String Q54AR = "Im Uhrenkasten";
    private static final String Q54A1 = "Im Schrank";
    private static final String Q54A2 = "Im Bett";
    private static final String Q54A3 = "Unter der Waschschüssel";

    //QUESTION 55
    private static final String Q55 = "Aus welchem Märchen stammt der Ausdruck \"Sieben auf einen Streich\" erschlagen?";
    private static final String Q55AR = "Das tapfere Schneiderlein";
    private static final String Q55A1 = "Schneewittchen und die sieben Zwerge";
    private static final String Q55A2 = "Der Wolf und die sieben Geißlein";
    private static final String Q55A3 = "Die sieben Raben";

    //QUESTION 56
    private static final String Q56 = "Welches Tier gehört nicht zu den Bremer Stadtmusikanten?";
    private static final String Q56AR = "Hase";
    private static final String Q56A1 = "Esel";
    private static final String Q56A2 = "Hund";
    private static final String Q56A3 = "Hahn";

    //QUESTION 57
    private static final String Q57 = "Welches Märchen stammt nicht von den Brüdern Grimm?";
    private static final String Q57AR = "Das hässliche Entlein";
    private static final String Q57A1 = "Der gestiefelte Kater";
    private static final String Q57A2 = "Tischlein deck dich";
    private static final String Q57A3 = "König Drosselbart";

    //QUESTION 58
    private static final String Q58 = "Wie heißen die zwei Landstreicher in Pippi Langstrumpf?";
    private static final String Q58AR = "Blom und Donner-Karlsson";
    private static final String Q58A1 = "Blom und Donner-Kesson";
    private static final String Q58A2 = "Blombe und Donner-Kessel";
    private static final String Q58A3 = "Blom und Blitz-Kalle";

    //QUESTION 59
    private static final String Q59 = "Wie heißen die Brüder Grimm mit Vornamen?";
    private static final String Q59AR = "Wilhem und Jacob";
    private static final String Q59A1 = "Wilhelm und Otto";
    private static final String Q59A2 = "Jacob und Karl";
    private static final String Q59A3 = "Jacob und Otto";

    //QUESTION 60
    private static final String Q60 = "Wie heißen die drei Kinder von Gru aus \"Ich einfach unverbesserlich\"?";
    private static final String Q60AR = "Margo, Edith, Agnes";
    private static final String Q60A1 = "Julia, Hannah, Edith";
    private static final String Q60A2 = "Margo, Julia Anna";
    private static final String Q60A3 = "Maria, Anni, Eli";

    //QUESTION 61
    private static final String Q61 = "Wer ist Präsident der HAW?";
    private static final String Q61AR = "Micha Teuscher";
    private static final String Q61A1 = "Dr. Claus-Dieter Wacker";
    private static final String Q61A2 = "Andy Grote";
    private static final String Q61A3 = "Katharina Fegebank";

    //QUESTION 62
    private static final String Q62 = "Welche HAW Studiengänge feierten 2017 10-jähriges?";
    private static final String Q62AR = "Rettungsingenieurwesen & Gefahrenabwehr";
    private static final String Q62A1 = "Medientechnik, Media Systems";
    private static final String Q62A2 = "BWL, Mathematik";
    private static final String Q62A3 = "Erneuerbare Energien, Nanotechnologie";

    //QUESTION 63
    private static final String Q63 = "Wovon profitieren Bewerber für 'Digitale Kommunikation'?";
    private static final String Q63AR = "Studienberatung per WhatsApp";
    private static final String Q63A1 = "Kein NC";
    private static final String Q63A2 = "Gratis USB-Stick";
    private static final String Q63A3 = "Schnupperkurs";

    //QUESTION 64
    private static final String Q64 = "Welcher HAW Studiengang feierte 2017 100-jähriges?";
    private static final String Q64AR = "Soziale Arbeit";
    private static final String Q64A1 = "Physik";
    private static final String Q64A2 = "Politikwissenschaften";
    private static final String Q64A3 = "Geschichte";

    //QUESTION 65
    private static final String Q65 = "Wie heißt das Racing Team der HAW?";
    private static final String Q65AR = "HAWKS";
    private static final String Q65A1 = "EAGLES";
    private static final String Q65A2 = "S. Hawking";
    private static final String Q65A3 = "LIONS";

    //QUESTION 66
    private static final String Q66 = "Wo befand sich die Mensa Finkenau vor Eröffnung des Neubaus?";
    private static final String Q66AR = "In einem Zelt";
    private static final String Q66A1 = "Es gab keine Mensa";
    private static final String Q66A2 = "Untergeschoss Altbau";
    private static final String Q66A3 = "In Räumen der HFBK";

    //QUESTION 67
    private static final String Q67 = "Welches W-LAN findet man NICHT an der HAW?";
    private static final String Q67AR = "HAW.5G";
    private static final String Q67A1 = "HAW.1X";
    private static final String Q67A2 = "HAW.guest";
    private static final String Q67A3 = "eduroam";

    //QUESTION 68
    private static final String Q68 = "Wie oft fand das Projekt 'ShortCut' im Fach Medientechnik statt?";
    private static final String Q68AR = "8";
    private static final String Q68A1 = "32";
    private static final String Q68A2 = "1";
    private static final String Q68A3 = "Hat noch nicht stattgefunden";

    //QUESTION 69
    private static final String Q69 = "Wie heißt das Filmfest am Campus Finkenau?";
    private static final String Q69AR = "Flimmerfest";
    private static final String Q69A1 = "Filmpreis Finkenau";
    private static final String Q69A2 = "ShortCut";
    private static final String Q69A3 = "The Finks";

    //QUESTION 70
    private static final String Q70 = "Wie heißt das 'Campusmagazin' der Finkenau?";
    private static final String Q70AR = "IMPETUS";
    private static final String Q70A1 = "Finkenau Journal";
    private static final String Q70A2 = "FNK";
    private static final String Q70A3 = "RETUNSUS";

    //QUESTION 71
    private static final String Q71 = "Woher hat die Reeperbahn ihren Namen?";
    private static final String Q71AR = "Tauherstellung für Schiffe";
    private static final String Q71A1 = "Bahnverbindung St. Pauli zur Neustadt";
    private static final String Q71A2 = "Nach Karl Friedrich Reep";
    private static final String Q71A3 = "Vom Reep dreschen";

    //QUESTION 72
    private static final String Q72 = "Wieviele Brücken hat Hamburg?";
    private static final String Q72AR = "ca. 2500";
    private static final String Q72A1 = "ca. 1300";
    private static final String Q72A2 = "ca. 2000";
    private static final String Q72A3 = "ca. 550";

    //QUESTION 73
    private static final String Q73 = "Wann wurde der alte Elbtunnel eröffnet?";
    private static final String Q73AR = "1911";
    private static final String Q73A1 = "1878";
    private static final String Q73A2 = "1924";
    private static final String Q73A3 = "1959";

    //QUESTION 74
    private static final String Q74 = "Welche ist keine Partnerstadt von Hamburg?";
    private static final String Q74AR = "Istanbul";
    private static final String Q74A1 = "Prag";
    private static final String Q74A2 = "Osaka";
    private static final String Q74A3 = "Shanghai";

    //QUESTION 75
    private static final String Q75 = "Was bedeutet die Endung -beek in Straßen- oder Ortsnamen?";
    private static final String Q75AR = "Bach";
    private static final String Q75A1 = "Hügel";
    private static final String Q75A2 = "Beet oder Feld";
    private static final String Q75A3 = "Bäcker";

    //QUESTION 76
    private static final String Q76 = "Die wievieldgrößte Stadt Deutschlands ist Hamburg?";
    private static final String Q76AR = "Zweite";
    private static final String Q76A1 = "Erste";
    private static final String Q76A2 = "Dritte";
    private static final String Q76A3 = "Sechste";

    //QUESTION 77
    private static final String Q77 = "Wie oft war der HSV Deutscher Meister seit Einführung der Bundesliga?";
    private static final String Q77AR = "3 mal";
    private static final String Q77A1 = "0 mal";
    private static final String Q77A2 = "1 mal";
    private static final String Q77A3 = "2 mal";

    //QUESTION 78
    private static final String Q78 = "?";
    private static final String Q78AR = "";
    private static final String Q78A1 = "";
    private static final String Q78A2 = "";
    private static final String Q78A3 = "";

    //QUESTION 79
    private static final String Q79 = "?";
    private static final String Q79AR = "";
    private static final String Q79A1 = "";
    private static final String Q79A2 = "";
    private static final String Q79A3 = "";

    //QUESTION 80
    private static final String Q80 = "?";
    private static final String Q80AR = "";
    private static final String Q80A1 = "";
    private static final String Q80A2 = "";
    private static final String Q80A3 = "";


    public QuestionInserts(Context context, int round) {
        int i;
        switch (round) {
            case 0:
                i = 0;
                new Question(context, Q1, Q1AR, Q1A1, Q1A2, Q1A3, i++);
                new Question(context, Q2, Q2AR, Q2A1, Q2A2, Q2A3, i++);
                new Question(context, Q3, Q3AR, Q3A1, Q3A2, Q3A3, i++);
                new Question(context, Q4, Q4AR, Q4A1, Q4A2, Q4A3, i++);
                new Question(context, Q5, Q5AR, Q5A1, Q5A2, Q5A3, i++);
                new Question(context, Q6, Q6AR, Q6A1, Q6A2, Q6A3, i++);
                new Question(context, Q7, Q7AR, Q7A1, Q7A2, Q7A3, i++);
                new Question(context, Q8, Q8AR, Q8A1, Q8A2, Q8A3, i++);
                new Question(context, Q9, Q9AR, Q9A1, Q9A2, Q9A3, i++);
                new Question(context, Q10, Q10AR, Q10A1, Q10A2, Q10A3, i++);
                break;
            case 1:
                i = 0;
                new Question(context, Q11, Q11AR, Q11A1, Q11A2, Q11A3, i++);
                new Question(context, Q12, Q12AR, Q12A1, Q12A2, Q12A3, i++);
                new Question(context, Q13, Q13AR, Q13A1, Q13A2, Q13A3, i++);
                new Question(context, Q14, Q14AR, Q14A1, Q14A2, Q14A3, i++);
                new Question(context, Q15, Q15AR, Q15A1, Q15A2, Q15A3, i++);
                new Question(context, Q16, Q16AR, Q16A1, Q16A2, Q16A3, i++);
                new Question(context, Q17, Q17AR, Q17A1, Q17A2, Q17A3, i++);
                new Question(context, Q18, Q18AR, Q18A1, Q18A2, Q18A3, i++);
                new Question(context, Q19, Q19AR, Q19A1, Q19A2, Q19A3, i++);
                new Question(context, Q20, Q20AR, Q20A1, Q20A2, Q20A3, i++);
                break;
            case 2:
                i = 0;
                new Question(context, Q21, Q21AR, Q21A1, Q21A2, Q21A3, i++);
                new Question(context, Q22, Q22AR, Q22A1, Q22A2, Q22A3, i++);
                new Question(context, Q23, Q23AR, Q23A1, Q23A2, Q23A3, i++);
                new Question(context, Q24, Q24AR, Q24A1, Q24A2, Q24A3, i++);
                new Question(context, Q25, Q25AR, Q25A1, Q25A2, Q25A3, i++);
                new Question(context, Q26, Q26AR, Q26A1, Q26A2, Q26A3, i++);
                new Question(context, Q27, Q27AR, Q27A1, Q27A2, Q27A3, i++);
                new Question(context, Q28, Q28AR, Q28A1, Q28A2, Q28A3, i++);
                new Question(context, Q29, Q29AR, Q29A1, Q29A2, Q29A3, i++);
                new Question(context, Q30, Q30AR, Q30A1, Q30A2, Q30A3, i++);
            case 3:
                i = 0;
                new Question(context, Q31, Q31AR, Q31A1, Q31A2, Q31A3, i++);
                new Question(context, Q32, Q32AR, Q32A1, Q32A2, Q32A3, i++);
                new Question(context, Q33, Q33AR, Q33A1, Q33A2, Q33A3, i++);
                new Question(context, Q34, Q34AR, Q34A1, Q34A2, Q34A3, i++);
                new Question(context, Q35, Q35AR, Q35A1, Q35A2, Q35A3, i++);
                new Question(context, Q36, Q36AR, Q36A1, Q36A2, Q36A3, i++);
                new Question(context, Q37, Q37AR, Q37A1, Q37A2, Q37A3, i++);
                new Question(context, Q38, Q38AR, Q38A1, Q38A2, Q38A3, i++);
                new Question(context, Q39, Q39AR, Q39A1, Q39A2, Q39A3, i++);
                new Question(context, Q40, Q40AR, Q40A1, Q40A2, Q40A3, i++);
            case 4:
                i = 0;
//                new Question(context, Q41, Q41AR, Q41A1, Q41A2, Q41A3, i++);
//                new Question(context, Q42, Q42AR, Q42A1, Q42A2, Q42A3, i++);
//                new Question(context, Q43, Q43AR, Q43A1, Q43A2, Q43A3, i++);
//                new Question(context, Q44, Q44AR, Q44A1, Q44A2, Q44A3, i++);
//                new Question(context, Q45, Q45AR, Q45A1, Q45A2, Q45A3, i++);
//                new Question(context, Q46, Q46AR, Q46A1, Q46A2, Q46A3, i++);
//                new Question(context, Q47, Q47AR, Q47A1, Q47A2, Q47A3, i++);
//                new Question(context, Q48, Q48AR, Q48A1, Q48A2, Q48A3, i++);
//                new Question(context, Q49, Q49AR, Q49A1, Q49A2, Q49A3, i++);
//                new Question(context, Q50, Q50AR, Q50A1, Q50A2, Q50A3, i++);
            case 5:
                i = 0;
                new Question(context, Q51, Q51AR, Q51A1, Q51A2, Q51A3, i++);
                new Question(context, Q52, Q52AR, Q52A1, Q52A2, Q52A3, i++);
                new Question(context, Q53, Q53AR, Q53A1, Q53A2, Q53A3, i++);
                new Question(context, Q54, Q54AR, Q54A1, Q54A2, Q54A3, i++);
                new Question(context, Q55, Q55AR, Q55A1, Q55A2, Q55A3, i++);
                new Question(context, Q56, Q56AR, Q56A1, Q56A2, Q56A3, i++);
                new Question(context, Q57, Q57AR, Q57A1, Q57A2, Q57A3, i++);
                new Question(context, Q58, Q58AR, Q58A1, Q58A2, Q58A3, i++);
                new Question(context, Q59, Q59AR, Q59A1, Q59A2, Q59A3, i++);
                new Question(context, Q60, Q60AR, Q60A1, Q60A2, Q60A3, i++);
            case 6:
                i = 0;
                new Question(context, Q61, Q61AR, Q61A1, Q61A2, Q61A3, i++);
                new Question(context, Q62, Q62AR, Q62A1, Q62A2, Q62A3, i++);
                new Question(context, Q63, Q63AR, Q63A1, Q63A2, Q63A3, i++);
                new Question(context, Q64, Q64AR, Q64A1, Q64A2, Q64A3, i++);
                new Question(context, Q65, Q65AR, Q65A1, Q65A2, Q65A3, i++);
                new Question(context, Q66, Q66AR, Q66A1, Q66A2, Q66A3, i++);
                new Question(context, Q67, Q67AR, Q67A1, Q67A2, Q67A3, i++);
                new Question(context, Q68, Q68AR, Q68A1, Q68A2, Q68A3, i++);
                new Question(context, Q69, Q69AR, Q69A1, Q69A2, Q69A3, i++);
                new Question(context, Q70, Q70AR, Q70A1, Q70A2, Q70A3, i++);
            case 7:
                i = 0;
                new Question(context, Q71, Q71AR, Q71A1, Q71A2, Q71A3, i++);
                new Question(context, Q72, Q72AR, Q72A1, Q72A2, Q72A3, i++);
                new Question(context, Q73, Q73AR, Q73A1, Q73A2, Q73A3, i++);
                new Question(context, Q74, Q74AR, Q74A1, Q74A2, Q74A3, i++);
                new Question(context, Q75, Q75AR, Q75A1, Q75A2, Q75A3, i++);
                new Question(context, Q76, Q76AR, Q76A1, Q76A2, Q76A3, i++);
                new Question(context, Q77, Q77AR, Q77A1, Q77A2, Q77A3, i++);
//                new Question(context, Q78, Q78AR, Q78A1, Q78A2, Q78A3, i++);
//                new Question(context, Q79, Q79AR, Q79A1, Q79A2, Q79A3, i++);
//                new Question(context, Q80, Q80AR, Q80A1, Q80A2, Q80A3, i++);
                alreadyAddedToDatabase = true;
        }
    }
}
