import java.util.Scanner;

public class VierGewinnt {
    
    // Spielfeldgröße
    private static final int REIHEN = 6;
    private static final int SPALTEN = 7;
    private static char[][] feld = new char[REIHEN][SPALTEN];
    
    public static void main(String[] args) {
        // Initialisieren des Spielfelds
        initialisiereFeld();
        
        // Scanner für Benutzereingaben
        Scanner scanner = new Scanner(System.in);
        
        char aktuellerSpieler = 'X';
        boolean spielBeendet = false;
        
        // Spielschleife
        while (!spielBeendet) {
            druckeFeld();
            
            // Frage den aktuellen Spieler nach einem Zug
            System.out.println("Spieler " + aktuellerSpieler + ", gib die Spalte (0-6) ein:");
            int spalte = scanner.nextInt();
            
            // Überprüfen, ob der Zug gültig ist
            if (spalte < 0 || spalte >= SPALTEN || feld[0][spalte] != ' ') {
                System.out.println("Ungültiger Zug. Versuche es noch einmal.");
                continue;
            }
            
            // Führe den Zug aus
            int reihe = findeFreieReihe(spalte);
            feld[reihe][spalte] = aktuellerSpieler;
            
            // Überprüfen, ob der aktuelle Spieler gewonnen hat
            if (hatGewonnen(aktuellerSpieler)) {
                druckeFeld();
                System.out.println("Spieler " + aktuellerSpieler + " hat gewonnen!");
                spielBeendet = true;
            } else {
                // Wechsle den Spieler
                aktuellerSpieler = (aktuellerSpieler == 'X') ? 'O' : 'X';
            }
        }
        
        scanner.close();
    }
    
    // Initialisiert das Spielfeld mit leeren Feldern
    private static void initialisiereFeld() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                feld[i][j] = ' ';
            }
        }
    }
    
    // Gibt das Spielfeld aus
    private static void druckeFeld() {
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                System.out.print("|" + feld[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(SPALTEN * 2 + 1));
    }
    
    // Findet die erste freie Reihe in einer gegebenen Spalte
    private static int findeFreieReihe(int spalte) {
        for (int i = REIHEN - 1; i >= 0; i--) {
            if (feld[i][spalte] == ' ') {
                return i;
            }
        }
        return -1;  // Sollte nicht erreicht werden, da der Zug validiert wird
    }
    
    // Überprüft, ob ein Spieler gewonnen hat
    private static boolean hatGewonnen(char spieler) {
        // Überprüfen der horizontalen Linien
        for (int i = 0; i < REIHEN; i++) {
            for (int j = 0; j < SPALTEN - 3; j++) {
                if (feld[i][j] == spieler && feld[i][j + 1] == spieler &&
                    feld[i][j + 2] == spieler && feld[i][j + 3] == spieler) {
                    return true;
                }
            }
        }
        
        // Überprüfen der vertikalen Linien
        for (int i = 0; i < REIHEN - 3; i++) {
            for (int j = 0; j < SPALTEN; j++) {
                if (feld[i][j] == spieler && feld[i + 1][j] == spieler &&
                    feld[i + 2][j] == spieler && feld[i + 3][j] == spieler) {
                    return true;
                }
            }
        }
        
        // Überprüfen der Diagonalen (von oben links nach unten rechts)
        for (int i = 0; i < REIHEN - 3; i++) {
            for (int j = 0; j < SPALTEN - 3; j++) {
                if (feld[i][j] == spieler && feld[i + 1][j + 1] == spieler &&
                    feld[i + 2][j + 2] == spieler && feld[i + 3][j + 3] == spieler) {
                    return true;
                }
            }
        }
        
        // Überprüfen der Diagonalen (von oben rechts nach unten links)
        for (int i = 0; i < REIHEN - 3; i++) {
            for (int j = 3; j < SPALTEN; j++) {
                if (feld[i][j] == spieler && feld[i + 1][j - 1] == spieler &&
                    feld[i + 2][j - 2] == spieler && feld[i + 3][j - 3] == spieler) {
                    return true;
                }
            }
        }
        
        return false;
    }
}


