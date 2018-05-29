import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8255319694373975038L;

	JButton startBtn;
	Main mainFrame;
	
	public Window (Main mainFrame) {
		setTitle("Futbol IA");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		this.setPreferredSize(new Dimension (Cons.CANVAS_X, Cons.CANVAS_Y));
		//setLayout(null);
		startBtn = new JButton ("Empezar partida!");
		startBtn.addActionListener(this);
		startBtn.setBounds(0, Cons.CANVAS_Y - 20, Cons.CANVAS_X, Cons.CANVAS_Y);
		this.mainFrame = mainFrame;
		add(this.mainFrame);
		add(startBtn, BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo (null);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
			startBtn.setEnabled(false);
			startBtn.setVisible(false);
			mainFrame.start();
		}
		
	}
}
