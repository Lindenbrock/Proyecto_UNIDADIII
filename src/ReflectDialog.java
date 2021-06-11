import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

public class ReflectDialog extends JDialog{
	int refType;
	JRadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7;
	ButtonGroup rbg;
	JButton btnAllow,btnCancel;
	
	public ReflectDialog(Interface w,boolean modal) {
		super(w.W,modal);
		setTitle("Reflejar la figura");
		setSize(450,130);
		setLayout(new FlowLayout());
		setLocationRelativeTo(w.W);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		setIconImage(icon);
		
		rbg = new ButtonGroup();
		rb1 = new JRadioButton("Reflejar en X");
		rb1.setForeground(w.two);
		rb1.setSelected(true);
		rb2 = new JRadioButton("Reflejar en Y");
		rb2.setForeground(w.two);
		rb3 = new JRadioButton("Reflejar en Z");
		rb3.setForeground(w.two);
		rb4 = new JRadioButton("Reflejar en X y Y");
		rb4.setForeground(w.two);
		rb5 = new JRadioButton("Reflejar en X y Z");
		rb5.setForeground(w.two);
		rb6 = new JRadioButton("Reflejar en Y y Z");
		rb6.setForeground(w.two);
		rb7 = new JRadioButton("Reflejar en X, Y Z");
		rb7.setForeground(w.two);
		rbg.add(rb1); rbg.add(rb2); rbg.add(rb3); rbg.add(rb4);
		rbg.add(rb5); rbg.add(rb6); rbg.add(rb7);
		btnAllow = new JButton("Aceptar");
		btnAllow.setBackground(w.two);
		btnAllow.setForeground(w.one);
		btnCancel = new JButton("Cancelar");
		btnCancel.setBackground(w.two);
		btnCancel.setForeground(w.one);
		
		add(rb1); add(rb2); add(rb3); add(rb4);
		add(rb5); add(rb6); add(rb7);add(btnAllow); add(btnCancel);
		
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rb1.isSelected()) 
					refType = 1;
				else
					if(rb2.isSelected())
						refType = 2;
					else
						if(rb3.isSelected())
							refType = 3;
						else
							if(rb4.isSelected())
								refType = 4;
							else
								if(rb5.isSelected())
									refType = 5;
								else
									if(rb6.isSelected())
										refType = 6;
									else
										refType = 7;
				setVisible(false);
				dispose();
			}	
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refType = 4;
				setVisible(false);
				dispose();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	}
	
	public int showDialog() {
		setVisible(true);
		return refType;
	}
}
