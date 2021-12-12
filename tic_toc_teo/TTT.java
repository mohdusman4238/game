package ttt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class TTT {
	private JFrame frame;
	private JLabel lb;
	JLabel msg;
	private JPanel panel1, panel2;
	String str = "C:\\Users\\mohdusman\\eclipse-workspace\\Gaming\\src\\ttt\\sounds\\user1.wav";
	//String tie1 = "C:\\Users\\mohdusman\\eclipse-workspace\\Gaming\\src\\ttt\\sounds\\tei.wav";
	String str1 = "C:\\Users\\mohdusman\\eclipse-workspace\\Gaming\\src\\ttt\\sounds\\background.wav";
    String buttonSound="C:\\Users\\mohdusman\\eclipse-workspace\\Gaming\\src\\ttt\\sounds\\button.wav";	
	ImageIcon icon1 = new ImageIcon(getClass().getResource("image/user1.png"));
	ImageIcon icon2 = new ImageIcon(getClass().getResource("image/user2.png"));
	JButton[] btn = new JButton[9];
	boolean firstUser = true;
	boolean winnerFound = false;
	JButton reset;
	int count = 0;
	private JLabel xmsg;
	private JLabel oMsg;
	private JLabel xCount;
	private JLabel oCount;
	private int xcount = 0;
	private int ocount = 0;

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		TTT objTtt = new TTT();
	}

	public TTT() {
		initialize();
	}

	private void initialize() {
		lb = new JLabel(new ImageIcon(getClass().getResource("image/back.jpg")));
		lb.setBounds(0, 0, 546, 705);
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image/iconimage.jpg")));
		frame.setTitle("Tic Toc Toe");
		frame.setBounds(350, 50, 559, 734);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().add(lb);
		lb.setLayout(null);

		panel2 = new JPanel();
		lb.add(panel2);
		panel2.setBounds(47, 103, 436, 363);

		panel1 = new JPanel();
		lb.add(panel1);
		panel1.setBackground(new Color(240, 248, 255));
		panel1.setBounds(0, 21, 546, 54);
		panel1.setLayout(null);
		ResetListener RL = new ResetListener();

		msg = new JLabel("First player turn...");
		msg.setBounds(0, 0, 546, 54);
		panel1.add(msg);
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setFont(new Font("elephent", 0, 40));

		JPanel firstPanel = new JPanel();
		firstPanel.setBackground(new Color(240, 255, 255));
		firstPanel.setBounds(48, 490, 180, 70);
		lb.add(firstPanel);
		firstPanel.setLayout(null);

		xmsg = new JLabel("X Winner :-");
		xmsg.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		xmsg.setBounds(10, 10, 104, 49);
		firstPanel.add(xmsg);

		xCount = new JLabel("0");
		xCount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		xCount.setBounds(124, 10, 46, 49);
		firstPanel.add(xCount);

		JPanel secondPanel = new JPanel();
		secondPanel.setBackground(new Color(240, 255, 255));
		secondPanel.setBounds(300, 485, 180, 70);
		lb.add(secondPanel);
		secondPanel.setLayout(null);

		oMsg = new JLabel("O Winner :-");
		oMsg.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		oMsg.setBounds(10, 10, 104, 49);
		secondPanel.add(oMsg);

		oCount = new JLabel("0");
		oCount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		oCount.setBounds(124, 10, 46, 49);
		secondPanel.add(oCount);

		JButton Refresh = new JButton("All Reset");
		Refresh.setFont(new Font("Tahoma", Font.BOLD, 13));
		Refresh.setBounds(310, 635, 109, 30);
		lb.add(Refresh);
		Refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFile(buttonSound);
				int respons = JOptionPane.showConfirmDialog(frame, "All Reset", "Conform", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (respons == JOptionPane.YES_OPTION) {
					 setFile(buttonSound);
					 for (JButton bb : btn) {
							bb.setIcon(null);
							bb.setBackground(Color.YELLOW);
						}
					ocount = 0;
					xcount=0;
					oCount.setText("0");
					xCount.setText("0");
					winnerFound = false;
					firstUser = true;
					count = 0;
					msg.setText("First Player trun..");
					msg.setForeground(Color.blue);
					reset.setEnabled(false);

				}
			}
		});

		reset = new JButton("Reset");
		lb.add(reset);
		reset.setFont(new Font("Tahoma", Font.BOLD, 13));
		reset.setBounds(112, 635, 109, 30);
		reset.setEnabled(false);
		reset.addActionListener(RL);
		addButton();

		frame.setVisible(true);

	}

	private void addButton() {
		actionlitoner listener = new actionlitoner();
		panel2.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 9; i++) {
			btn[i] = new JButton();
			btn[i].addActionListener(listener);
			btn[i].setBackground(Color.yellow);
			btn[i].setBorder(BorderFactory.createLineBorder(Color.green, 3));
			panel2.add(btn[i]);
		}
	}

	class actionlitoner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evn) {
			JButton bb = (JButton) evn.getSource();
			if (bb.getIcon() != null || winnerFound)
				return;
			if (firstUser) {
				bb.setIcon(icon1);
				msg.setForeground(Color.magenta);
				msg.setText("second player turn....");
				findWinner(icon1);
				 setFile(str);
			} else {
				bb.setIcon(icon2);
				msg.setText("First player turn....");
				msg.setForeground(Color.blue);
				findWinner(icon2);
				 setFile(str);
			}
			firstUser = !firstUser;
			count++;
			if (count ==9 && !winnerFound) {
				
				msg.setText("Game ended into tie...");
				msg.setForeground(Color.red);
				reset.setEnabled(true);

			}
		}
	}

	private void findWinner(ImageIcon icon) {
		if (btn[0].getIcon() == icon && btn[1].getIcon() == icon && btn[2].getIcon() == icon)
			setWinner(0, 1, 2, icon);

		if (btn[3].getIcon() == icon && btn[4].getIcon() == icon && btn[5].getIcon() == icon)
			setWinner(3, 4, 5, icon);
		if (btn[6].getIcon() == icon && btn[7].getIcon() == icon && btn[8].getIcon() == icon)
			setWinner(6, 7, 8, icon);
		if (btn[0].getIcon() == icon && btn[3].getIcon() == icon && btn[6].getIcon() == icon)
			setWinner(0, 3, 6, icon);
		if (btn[1].getIcon() == icon && btn[4].getIcon() == icon && btn[7].getIcon() == icon)
			setWinner(1, 4, 7, icon);
		if (btn[2].getIcon() == icon && btn[5].getIcon() == icon && btn[8].getIcon() == icon)
			setWinner(2, 5, 8, icon);
		if (btn[0].getIcon() == icon && btn[4].getIcon() == icon && btn[8].getIcon() == icon)
			setWinner(0, 4, 8, icon);
		if (btn[2].getIcon() == icon && btn[4].getIcon() == icon && btn[6].getIcon() == icon)
			setWinner(2, 4, 6, icon);

	}

	private void setWinner(int i, int j, int k, ImageIcon icon) {
		btn[i].setBackground(Color.GRAY);
		btn[j].setBackground(Color.GRAY);
		btn[k].setBackground(Color.GRAY);
		 setFile(str1);
		if (icon == icon1) {
			msg.setText("First player won...");
			xcount++;
			xCount.setText(String.valueOf(xcount));

		} else {
			msg.setText("Second Player Won...");
			ocount++;
			oCount.setText(String.valueOf(ocount));
		}
		winnerFound = true;
		reset.setEnabled(true);

	}

	class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (JButton bb : btn) {
				bb.setIcon(null);
				bb.setBackground(Color.YELLOW);
			}
			 setFile(buttonSound);
			winnerFound = false;
			firstUser = true;
			count = 0;
			msg.setText("First Player trun..");
			msg.setForeground(Color.blue);
			reset.setEnabled(false);
		}

	}

	private void setFile(String url) {
		Clip clip;
		try {
			File file = new File(url);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
		
		} catch (Exception e) {
			System.out.println("file not Exist =" + e);
		}
	}
}