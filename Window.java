import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame{
	private final int WINDOW_WIDTH=400, WINDOW_HEIGHT=300;
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton findButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public Window() {
		setTitle("FindIP");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  //centers window
		setResizable(false);
		init();
		add(panel);
		setVisible(true);
	}

	private void init() {
		panel = new JPanel();
		
		label = new JLabel("Enter URL");
		panel.add(label);
		
		textField = new JTextField(20);
		panel.add(textField);
		
		findButton = new JButton("find IP");
		findButton.addActionListener(new ButtonListener());
		panel.add(findButton);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(360, 200));
		scrollPane.setBounds(10, 50, 373, 203);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private class ButtonListener implements ActionListener{
		String url, ip;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				url = textField.getText();
				InetAddress host = InetAddress.getByName(url);
				ip = host.getHostAddress();
				textArea.append(url + " ip: " + ip + "\n");
			} catch (UnknownHostException e) {
				textArea.append(url + " Unknown Host\n");
			}
		}
	}
}
