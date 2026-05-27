package VIEW;

import CONTROLLER.GestioFitxersXML;
import CONTROLLER.GestiorFitxersTXT;
import static CONTROLLER.Principal.rol;

import java.awt.*;
import javax.swing.*;

public class frmValidacio extends JFrame {

    private JTextField txtNom;
    private JPasswordField txtPassword;
    private JButton btnValidar;
    private JButton btnCancelar;

    public frmValidacio() {

        setTitle("Validació");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Formulari de validació iniciat"
        );
    }

    private void initComponents() {

        Color fons = new Color(20, 20, 20);
        Color panelColor = new Color(35, 35, 35);
        Color textColor = Color.WHITE;

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(fons);
        panelPrincipal.setLayout(new GridBagLayout());

        JPanel card = new JPanel();
        card.setBackground(panelColor);
        card.setPreferredSize(new Dimension(380, 240));
        card.setLayout(null);

        JLabel lblTitol = new JLabel("VALIDACIÓ");
        lblTitol.setForeground(Color.WHITE);
        lblTitol.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitol.setBounds(110, 20, 200, 40);

        JLabel lblNom = new JLabel("Nom d'usuari");
        lblNom.setForeground(textColor);
        lblNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNom.setBounds(40, 80, 120, 25);

        txtNom = new JTextField();
        txtNom.setBounds(170, 80, 160, 30);
        txtNom.setBackground(new Color(55, 55, 55));
        txtNom.setForeground(Color.WHITE);
        txtNom.setCaretColor(Color.WHITE);
        txtNom.setBorder(BorderFactory.createEmptyBorder());

        JLabel lblPassword = new JLabel("Contrasenya");
        lblPassword.setForeground(textColor);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setBounds(40, 130, 120, 25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 130, 160, 30);
        txtPassword.setBackground(new Color(55, 55, 55));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBorder(BorderFactory.createEmptyBorder());

        btnValidar = new JButton("VALIDAR");
        btnValidar.setBounds(70, 190, 120, 40);
        btnValidar.setBackground(new Color(0, 140, 255));
        btnValidar.setForeground(Color.WHITE);
        btnValidar.setFocusPainted(false);

        btnCancelar = new JButton("CANCEL·LAR");
        btnCancelar.setBounds(210, 190, 120, 40);
        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);

        // EVENTS
        btnValidar.addActionListener(e -> validarUsuari());

        btnCancelar.addActionListener(e -> {
            txtNom.setText("");
            txtPassword.setText("");

            GestiorFitxersTXT.escripturaAFitxerLog(
                    "Camps de validació reiniciats"
            );
        });

        // AFEGIR COMPONENTS
        card.add(lblTitol);
        card.add(lblNom);
        card.add(txtNom);
        card.add(lblPassword);
        card.add(txtPassword);
        card.add(btnValidar);
        card.add(btnCancelar);

        panelPrincipal.add(card);

        add(panelPrincipal);
    }

    private void validarUsuari() {

        GestiorFitxersTXT.escripturaAFitxerLog(
                "Intent de validació de l'usuari: " + txtNom.getText()
        );

        boolean valid = GestioFitxersXML.validaUsuari(
                "src/FITXERS/Usuaris.xml",
                txtNom.getText(),
                new String(txtPassword.getPassword())
        );

        if (!valid) {

            JOptionPane.showMessageDialog(
                    null,
                    "Usuari o contrasenya incorrectes"
            );

            GestiorFitxersTXT.escripturaAFitxerLog(
                    "Error de validació"
            );

            return;
        }

        switch (rol) {

            case "ADMIN":

                JOptionPane.showMessageDialog(
                        null,
                        "Ets administrador"
                );

                frmAdmin f = new frmAdmin();
                f.setVisible(true);

                dispose();

                break;

            case "ESPORTISTA":

                JOptionPane.showMessageDialog(
                        null,
                        "Ets esportista"
                );

                frmEsportista es = new frmEsportista();
                es.setVisible(true);

                dispose();

                break;

            case "ENTRENADOR":

                JOptionPane.showMessageDialog(
                        null,
                        "Ets entrenador"
                );

                frmEntrenador en = new frmEntrenador();
                en.setVisible(true);

                dispose();

                break;
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new frmValidacio().setVisible(true);
        });
    }
}