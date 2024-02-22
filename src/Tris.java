import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Tris extends JFrame {
    public Random random = new Random();
    private JPanel jpnTitolo = new JPanel();
    private JPanel jpnBottoni = new JPanel();
    private JButton jbReset = new JButton();
    public JLabel jlbTesto = new JLabel();
    private JButton[] bottoni = new JButton[9];
    public int count = 0; // per determinare la fine
    public boolean turnoGiocatore1; // vero se turno del giocare1, falso se turno del giocatore 2

    public Tris() {
        this.setTitle("Gioco del Tris"); // nome della scheda
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 650);
        this.setLayout(new BorderLayout());

        jlbTesto.setBackground(Color.BLACK); // colore dietro al titolo
        jlbTesto.setForeground(new Color(25, 255, 25)); // colore del titolo (testo)
        jlbTesto.setFont(new Font("Comic Sans MS", Font.BOLD, 60));
        jlbTesto.setHorizontalAlignment(JLabel.CENTER);
        jlbTesto.setOpaque(true);

        jpnTitolo.setLayout(new BorderLayout());
        jpnTitolo.setBounds(0, 0, 800, 100); // controlla dopo

        jpnTitolo.add(jlbTesto); // aggiungo etichetta con testo al pannello col titolo

        jpnBottoni.setLayout(new GridLayout(3, 3));// griglia con i bottoni (9)
        jpnBottoni.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            bottoni[i] = new JButton();
            jpnBottoni.add(bottoni[i]);
            bottoni[i].setFont(new Font("Bell MT", Font.BOLD, 90)); // font e dimensione del testo del bottone
            bottoni[i].setFocusable(false);
            bottoni[i].setBackground(new Color(200, 200, 200));
            bottoni[i].addActionListener(new AscoltaBottone());
        }

        // reset
        jbReset.setText("Reset");
        jbReset.setFocusable(false);
        jbReset.setFont(new Font("Ink Free", Font.BOLD, 30));
        jbReset.addActionListener(new AscoltaReset());
        jpnTitolo.add(jbReset, BorderLayout.EAST);

        // aggiungo i pannelli al frame e rendo visibile
        this.add(jpnTitolo, BorderLayout.NORTH);
        this.add(jpnBottoni);
        this.setVisible(true);

        primoTurno();
    }

    /**
     * Determina chi deve iniziare il gioco con un numero casuale tra 0 e 1
     * (giocatore1, giocatore2)
     */
    public void primoTurno() {
        if (random.nextInt(2) == 0) {
            turnoGiocatore1 = true;
            jlbTesto.setText("Turno di 'X'");
        } else {
            turnoGiocatore1 = false;
            jlbTesto.setText("Turno di 'O'");
        }
    }

    /**
     * Metodo per controllare tutte le combinazioni e richiamare la vincita del
     * giocatore
     */
    public void controllaVincita() {
        count++;
        if (count < 9) {
            // X vince
            if ((bottoni[0].getText().equals("X")) && (bottoni[1].getText().equals("X"))
                    && (bottoni[2].getText().equals("X"))) {
                vinceX(0, 1, 2); // combinazione vincente (prima linea)l
            }
            if ((bottoni[3].getText().equals("X")) && (bottoni[4].getText().equals("X"))
                    && (bottoni[5].getText().equals("X"))) {
                vinceX(3, 4, 5); // seconda linea
            }
            if ((bottoni[6].getText().equals("X")) && (bottoni[7].getText().equals("X"))
                    && (bottoni[8].getText().equals("X"))) {
                vinceX(6, 7, 8); // terza linea
            }
            // incrociate
            if ((bottoni[0].getText().equals("X")) && (bottoni[3].getText().equals("X"))
                    && (bottoni[6].getText().equals("X"))) {
                vinceX(0, 3, 6);
            }
            if ((bottoni[1].getText().equals("X")) && (bottoni[4].getText().equals("X"))
                    && (bottoni[7].getText().equals("X"))) {
                vinceX(1, 4, 7);
            }
            if ((bottoni[2].getText().equals("X")) && (bottoni[5].getText().equals("X"))
                    && (bottoni[8].getText().equals("X"))) {
                vinceX(2, 5, 8);
            }
            if ((bottoni[0].getText().equals("X")) && (bottoni[4].getText().equals("X"))
                    && (bottoni[8].getText().equals("X"))) {
                vinceX(0, 4, 8);
            }
            if ((bottoni[2].getText().equals("X")) && (bottoni[4].getText().equals("X"))
                    && (bottoni[6].getText().equals("X"))) {
                vinceX(2, 4, 6);
            }

            // 0 vince
            if ((bottoni[0].getText().equals("O")) && (bottoni[1].getText().equals("O"))
                    && (bottoni[2].getText().equals("O"))) {
                vinceO(0, 1, 2); // combinazione vincente (prima linea)l
            }
            if ((bottoni[3].getText().equals("O")) && (bottoni[4].getText().equals("O"))
                    && (bottoni[5].getText().equals("O"))) {
                vinceO(3, 4, 5); // seconda linea
            }
            if ((bottoni[6].getText().equals("O")) && (bottoni[7].getText().equals("O"))
                    && (bottoni[8].getText().equals("O"))) {
                vinceO(6, 7, 8); // terza linea
            }
            // incrociate
            if ((bottoni[0].getText().equals("O")) && (bottoni[3].getText().equals("O"))
                    && (bottoni[6].getText().equals("O"))) {
                vinceO(0, 3, 6);
            }
            if ((bottoni[1].getText().equals("O")) && (bottoni[4].getText().equals("O"))
                    && (bottoni[7].getText().equals("O"))) {
                vinceO(1, 4, 7);
            }
            if ((bottoni[2].getText().equals("O")) && (bottoni[5].getText().equals("O"))
                    && (bottoni[8].getText().equals("O"))) {
                vinceO(2, 5, 8);
            }
            if ((bottoni[0].getText().equals("O")) && (bottoni[4].getText().equals("O"))
                    && (bottoni[8].getText().equals("O"))) {
                vinceO(0, 4, 8);
            }
            if ((bottoni[2].getText().equals("O")) && (bottoni[4].getText().equals("O"))
                    && (bottoni[6].getText().equals("O"))) {
                vinceO(2, 4, 6);
            }
        } else {
            jlbTesto.setText("Nessun vincitore");
            for (int i = 0; i < 9; i++) {
                bottoni[i].setEnabled(false);
            }
        }
    }

    /**
     * Prende come parametri le tre caselle vincenti e le colora di verde.
     * Blocca anche i bottoni dopo la vittoria
     *
     * @param a
     * @param b
     * @param c
     */
    public void vinceX(int a, int b, int c) {
        bottoni[a].setBackground(Color.GREEN);
        bottoni[b].setBackground(Color.GREEN);
        bottoni[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            bottoni[i].setEnabled(false);
            jlbTesto.setText("X Vince!");
        }
    }

    /**
     * Prende come parametri le tre caselle vincenti e le colora di verde.
     * Blocca anche i bottoni dopo la vittoria
     *
     * @param a
     * @param b
     * @param c
     */
    public void vinceO(int a, int b, int c) {
        bottoni[a].setBackground(Color.GREEN);
        bottoni[b].setBackground(Color.GREEN);
        bottoni[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++) {
            bottoni[i].setEnabled(false);
            jlbTesto.setText("O Vince!");
        }
    }

    /**
     * Ascoltatore del bottone.
     * Fa comparire le X e 0 sui tasti quandi pigiati e stampa nel titolo il turno.
     * Controlla anche se qualcuno ha vinto
     */
    class AscoltaBottone implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton bottonePremuto = (JButton) e.getSource();

            if (turnoGiocatore1) {
                if (bottonePremuto.getText().isEmpty()) { // se non c'è ancora nulla, significa che la casella è libera
                    bottonePremuto.setForeground(Color.BLUE); // il colore di blu, colora la casella
                    bottonePremuto.setText("X");
                    turnoGiocatore1 = false;
                    jlbTesto.setText("Turno di 'O'");
                    controllaVincita();
                }
            } else {
                if (bottonePremuto.getText().isEmpty()) {
                    bottonePremuto.setForeground(Color.RED); // il colore di blu, colora la casella
                    bottonePremuto.setText("O");
                    turnoGiocatore1 = true;
                    jlbTesto.setText("Turno di 'X'");
                    controllaVincita();
                }
            }
        }
    }

    /**
     * Bottone Reset
     */
    class AscoltaReset implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 9; i++) {
                bottoni[i].setText("");
                bottoni[i].setBackground(new Color(200, 200, 200));
                bottoni[i].setEnabled(true);
            }
            primoTurno();
            count = 0; // contatore per partita
        }
    }
}