package VIEW;

import CONTROLLER.GestiorFitxersTXT;
import CONTROLLER.Principal;
import DATA.Querys;
import static DATA.Querys.afegirComentari;
import MODEL.Comentari;
import MODEL.Entrenament;
import MODEL.TipusEsport;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class frmEntrenador extends JFrame {

    // COMPONENTS
    private JTable tblEntrenaments;
    private JTable tblComentaris;

    private JTextArea txtComentari;
    private JTextField txtIdEntrenador;

    private JButton btnAfegirComentari;

    private JCheckBox ckValidar;

    public frmEntrenador() {

        setTitle("Panell Entrenador");
        setSize(1450, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        desactivarCamps();

        carregarTipusEsport();

        Querys.mostrarEntrenaments();
        omplirTaulaEntrenaments();

        afegirListenerTaulaEntrenament();

        afegirDocumentListener(txtComentari);
        afegirDocumentListener(txtIdEntrenador);
    }

    private void initComponents() {

        // COLORS
        Color bg = new Color(20, 20, 20);
        Color panel = new Color(35, 35, 35);

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(bg);

        // ===== HEADER =====

        JLabel lblTitol = new JLabel("PANEL ENTRENADOR");

        lblTitol.setForeground(new Color(0, 220, 120));
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitol.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        principal.add(lblTitol, BorderLayout.NORTH);

        // ===== CONTENT =====

        JPanel content = new JPanel(new GridLayout(1, 2, 20, 20));

        content.setBackground(bg);

        content.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 20, 20)
        );

        // ==========================================
        // PANEL ESQUERRA
        // ==========================================

        JPanel leftPanel = new JPanel();

        leftPanel.setBackground(panel);
        leftPanel.setLayout(null);

        JLabel lblComentari = new JLabel("AFEGIR COMENTARI");

        lblComentari.setForeground(Color.WHITE);
        lblComentari.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblComentari.setBounds(30, 20, 300, 30);

        leftPanel.add(lblComentari);

        // TEXT AREA
        JLabel lblText = new JLabel("COMENTARI");

        lblText.setForeground(Color.WHITE);
        lblText.setBounds(30, 80, 150, 25);

        leftPanel.add(lblText);

        txtComentari = new JTextArea();

        txtComentari.setLineWrap(true);
        txtComentari.setWrapStyleWord(true);

        JScrollPane spComentari = new JScrollPane(txtComentari);

        spComentari.setBounds(30, 110, 350, 140);

        leftPanel.add(spComentari);

        // ID ENTRENADOR
        JLabel lblIdEntrenador = new JLabel("ID ENTRENADOR");

        lblIdEntrenador.setForeground(Color.WHITE);
        lblIdEntrenador.setBounds(30, 290, 150, 25);

        leftPanel.add(lblIdEntrenador);

        txtIdEntrenador = new JTextField();

        txtIdEntrenador.setBounds(30, 320, 150, 35);

        leftPanel.add(txtIdEntrenador);

        // CHECK VALIDAR
        ckValidar = new JCheckBox("VALIDAR ENTRENAMENT");

        ckValidar.setBounds(30, 390, 220, 30);

        ckValidar.setForeground(Color.WHITE);
        ckValidar.setBackground(panel);

        leftPanel.add(ckValidar);

        // BOTO
        btnAfegirComentari = new JButton("AFEGIR COMENTARI");

        btnAfegirComentari.setBounds(30, 470, 350, 50);

        estilBoto(
                btnAfegirComentari,
                new Color(0, 140, 255)
        );

        leftPanel.add(btnAfegirComentari);

        // ==========================================
        // PANEL DRETA
        // ==========================================

        JPanel rightPanel = new JPanel();

        rightPanel.setBackground(panel);
        rightPanel.setLayout(new BorderLayout(15, 15));

        JPanel topTables = new JPanel(new GridLayout(2, 1, 10, 20));

        topTables.setBackground(panel);

        // ===== TAULA ENTRENAMENTS =====

        JPanel entrenamentPanel = new JPanel(new BorderLayout());

        entrenamentPanel.setBackground(panel);

        JLabel lblEntrenaments = new JLabel("ENTRENAMENTS");

        lblEntrenaments.setForeground(Color.WHITE);
        lblEntrenaments.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        tblEntrenaments = new JTable();

        JScrollPane sp1 = new JScrollPane(tblEntrenaments);

        entrenamentPanel.add(lblEntrenaments, BorderLayout.NORTH);
        entrenamentPanel.add(sp1, BorderLayout.CENTER);

        // ===== TAULA COMENTARIS =====

        JPanel comentariPanel = new JPanel(new BorderLayout());

        comentariPanel.setBackground(panel);

        JLabel lblComentaris = new JLabel("COMENTARIS");

        lblComentaris.setForeground(Color.WHITE);
        lblComentaris.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        tblComentaris = new JTable();

        JScrollPane sp2 = new JScrollPane(tblComentaris);

        comentariPanel.add(lblComentaris, BorderLayout.NORTH);
        comentariPanel.add(sp2, BorderLayout.CENTER);

        topTables.add(entrenamentPanel);
        topTables.add(comentariPanel);

        rightPanel.add(topTables, BorderLayout.CENTER);

        // ===== AFEGIR PANELS =====

        content.add(leftPanel);
        content.add(rightPanel);

        principal.add(content, BorderLayout.CENTER);

        add(principal);

        // EVENTS

        btnAfegirComentari.addActionListener(e -> afegirComentari());

        ckValidar.addActionListener(e -> validarEntrenament());
    }

    // ==========================================
    // ESTILS
    // ==========================================

    private void estilBoto(JButton boto, Color color) {

        boto.setBackground(color);
        boto.setForeground(Color.WHITE);
        boto.setFocusPainted(false);
        boto.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ==========================================
    // LOGICA
    // ==========================================

    private void activarCamps() {

        txtComentari.setEnabled(true);
        txtIdEntrenador.setEnabled(true);

        btnAfegirComentari.setEnabled(false);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Camps activats"
        );
    }

    private void desactivarCamps() {

        txtComentari.setEnabled(false);
        txtIdEntrenador.setEnabled(false);

        btnAfegirComentari.setEnabled(false);

        ckValidar.setEnabled(false);

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Camps desactivats"
        );
    }

    private void validarBoto() {

        int fila = tblEntrenaments.getSelectedRow();

        boolean entrenamentSeleccionat = fila != -1;

        boolean comentariOk =
                !txtComentari.getText().trim().isEmpty();

        boolean idOk =
                !txtIdEntrenador.getText().trim().isEmpty();

        btnAfegirComentari.setEnabled(
                entrenamentSeleccionat
                && comentariOk
                && idOk
        );
    }

    private void afegirDocumentListener(
            javax.swing.text.JTextComponent camp
    ) {

        camp.getDocument().addDocumentListener(
                new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validarBoto();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validarBoto();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validarBoto();
            }
        });
    }

    private void afegirComentari() {

        int fila = tblEntrenaments.getSelectedRow();

        if (fila != -1) {

            Integer idEntrenament =
                    (Integer) tblEntrenaments
                            .getModel()
                            .getValueAt(fila, 0);

            Integer idEntrenador =
                    Integer.parseInt(
                            txtIdEntrenador.getText()
                    );

            String text = txtComentari.getText();

            LocalDate data = LocalDate.now();

            Querys.afegirComentari(
                    text,
                    data,
                    idEntrenador,
                    idEntrenament
            );

            txtComentari.setText("");
            txtIdEntrenador.setText("");

            desactivarCamps();

            Querys.mostrarComentari(idEntrenament);

            omplirTaulaComentaris();

            GestiorFitxersTXT.escripturaAFitxerLog(
                    "Comentari afegit"
            );
        }
    }

    private void validarEntrenament() {

        boolean validat = ckValidar.isSelected();

        int fila = tblEntrenaments.getSelectedRow();

        if (fila == -1) {
            return;
        }

        int id =
                (int) tblEntrenaments.getValueAt(fila, 0);

        Querys.validarEntrenament(id, validat);

        ckValidar.setSelected(false);

        ckValidar.setEnabled(false);

        Querys.mostrarEntrenaments();

        omplirTaulaEntrenaments();

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Entrenament validat"
        );
    }

    private void carregarTipusEsport() {

        Principal.tipusesports.clear();

        try {

            ResultSet rs = Querys.getTipusEsport();

            while (rs.next()) {

                int id = rs.getInt("id");
                String nom = rs.getString("nom");

                TipusEsport tipus =
                        new TipusEsport(id, nom);

                Principal.tipusesports.add(tipus);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    private String obtenirNomTipusEsport(int idTipus) {

        for (TipusEsport t : Principal.tipusesports) {

            if (t.getId() == idTipus) {

                return t.getNom();
            }
        }

        return "";
    }

    public void omplirTaulaEntrenaments() {

        String[] columnes = {
            "ID",
            "DATA",
            "DURADA",
            "DISTANCIA",
            "DESCRIPCIO",
            "INTENSITAT",
            "COMPLETAT",
            "VALIDAT",
            "ID_USUARI",
            "TIPUS ESPORT"
        };

        DefaultTableModel model =
                new DefaultTableModel();

        model.setColumnIdentifiers(columnes);

        for (Entrenament e : Principal.entrenaments) {

            Object[] fila = {

                e.getId(),
                e.getData(),
                e.getDuradaMinuts(),
                e.getDistancia(),
                e.getDescripcio(),
                e.getIntensitat(),
                e.isCompletat(),
                e.isValidat(),
                e.getUsuariId(),
                obtenirNomTipusEsport(
                        e.getTipusEsportId()
                )
            };

            model.addRow(fila);
        }

        tblEntrenaments.setModel(model);
    }

    public void omplirTaulaComentaris() {

        String[] columnes = {
            "ID",
            "TEXT",
            "DATA",
            "ENTRENAMENT_ID",
            "ENTRENADOR_ID"
        };

        DefaultTableModel model =
                new DefaultTableModel();

        model.setColumnIdentifiers(columnes);

        for (Comentari c : Principal.comentaris) {

            Object[] fila = {

                c.getId(),
                c.getText(),
                c.getData(),
                c.getEntrenamentId(),
                c.getEntrenadorId()
            };

            model.addRow(fila);
        }

        tblComentaris.setModel(model);
    }

    private void afegirListenerTaulaEntrenament() {

        tblEntrenaments.getSelectionModel()
                .addListSelectionListener(
                        (ListSelectionEvent e) -> {

            if (!e.getValueIsAdjusting()) {

                int fila =
                        tblEntrenaments.getSelectedRow();

                if (fila != -1) {

                    int idEntrenament =
                            Integer.parseInt(
                                    tblEntrenaments
                                            .getValueAt(fila, 0)
                                            .toString()
                            );

                    activarCamps();

                    boolean esCompletat =
                            (boolean) tblEntrenaments
                                    .getValueAt(fila, 6);

                    boolean esValidat =
                            (boolean) tblEntrenaments
                                    .getValueAt(fila, 7);

                    if (esCompletat && !esValidat) {

                        ckValidar.setEnabled(true);

                    } else {

                        ckValidar.setEnabled(false);
                    }

                    Querys.mostrarComentari(
                            idEntrenament
                    );

                    omplirTaulaComentaris();
                }
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new frmEntrenador().setVisible(true);
        });
    }
}