//package com.minenash.customhud.gui.editor;
//
//
//import com.formdev.flatlaf.FlatIntelliJLaf;
//import com.minenash.customhud.CustomHud;
//import com.minenash.customhud.data.Profile;
//import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
//import org.fife.ui.rtextarea.RTextScrollPane;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class EditorWindow {
//
//
//    //Style: https://www.formdev.com/flatlaf/
//    public static void open(Profile profile) {
//        System.setProperty("java.awt.headless", "false");
//
//        try {
//            UIManager.setLookAndFeel(new FlatIntelliJLaf());
//        } catch (Exception ex) {
//            CustomHud.LOGGER.error("[CustomHud] Failed to initialize LaF");
//        }
//        SwingUtilities.invokeLater(() -> {
//            EditorWindow window = new EditorWindow(profile);
//        });
//    }
//
//    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//    private RSyntaxTextArea textArea;
//    private JButton update;
//    private final Path path;
//
//    public EditorWindow(Profile profile) {
//        path = CustomHud.PROFILE_FOLDER.resolve(profile.name + ".txt");
//
//        JFrame frame = new JFrame();
//        frame.setTitle("CustomHud Editor");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(854, 480);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.setBackground(Color.darkGray);
//
//        frame.getRootPane().registerKeyboardAction(this::save, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
//
////        frame.add(top(), BorderLayout.NORTH);
//        frame.add(editor(), BorderLayout.CENTER);
//        frame.add(top(profile.name), BorderLayout.NORTH);
//
//        textArea.grabFocus();
//    }
//
//    private void save(ActionEvent event) {
//        try {
//            if (Files.notExists(path))
//                Files.createFile(path);
//            Files.writeString(path, textArea.getText());
//            update.setText("Saved");
//            executorService.schedule( () -> update.setText("Save & Update"), 3, TimeUnit.SECONDS );
//
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(new JFrame(), "Can't Save!\n\n" + e.getLocalizedMessage(), "Dialog", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private JPanel editor() {
//        JPanel panel = new JPanel();
//
//        textArea = new RSyntaxTextArea();
//        textArea.setSyntaxEditingStyle("text/customhud");
//        textArea.setCodeFoldingEnabled(false);
////        textArea.setSyntaxScheme(CToken.style);
//
//        try {
//            textArea.setText(Files.readString(path));
//            textArea.setEditable(true);
//        } catch (IOException e) {
//            textArea.setText("Can't Read File");
//            textArea.setEditable(false);
//        }
//
//        RTextScrollPane sp = new RTextScrollPane(textArea);
//        panel.add(sp);
//
//        return panel;
//    }
//
//    private JPanel top(String name) {
//        JPanel left = new JPanel();
//        JPanel innerLeft = new JPanel();
//
//        JLabel label = new JLabel("Profile: " + name, JLabel.CENTER);
//        var font = label.getFont();
//        label.setFont( font.deriveFont(Font.BOLD, 14) );
//
//
//        innerLeft.add(label, BorderLayout.WEST);
//        left.add(innerLeft);
//
//        JPanel right = new JPanel();
//        update = new JButton("Save & Update");
//        update.addActionListener(this::save);
//
//
//        update.setMargin( new Insets(2, 4, 2, 4) );
//        right.add(update);
//
//        JPanel panel = new JPanel(new BorderLayout());
//        panel.add(left, BorderLayout.WEST);
//        panel.add(right, BorderLayout.EAST);
//        return panel;
//    }
//}
