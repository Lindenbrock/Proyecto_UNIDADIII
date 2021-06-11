//	LUIS JOSÉ IXTA ZAMUDIO 17420565

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Interface extends JPanel implements ChangeListener{
	JFrame W;
	Figure3D Fig3D;
	JSlider sliderX,sliderY,sliderZ,sliderSca;
	JPanel southPanel,panelSX,panelSY,panelSZ;
	boolean drag = false;
	Color one,two;
	JButton btnRef;
	Image icon;
	
	public Interface () {
		W = new JFrame("Medial Disdyakis Triacontahedron");
		W.setSize(800,600);
		W.setLocationRelativeTo(this);
		W.add(this);
		
		//Icono de la interfaz
		icon = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Resources/icon.png"));
		W.setIconImage(icon);
		
		one = new Color(46,58,58);
		two = new Color(32,202,209);
		
		setBackground(one);
		
		sliderX = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderX.setMinorTickSpacing(15);
		sliderX.setMajorTickSpacing(45);
		sliderX.setPaintTicks(true);
		sliderX.setPaintLabels(true);
		sliderX.setBackground(one);
		sliderX.setForeground(two);
		
		panelSX = new JPanel();
		TitledBorder tbPSX = new TitledBorder("Rotación en el eje X");
		tbPSX.setTitleJustification(TitledBorder.CENTER);
		panelSX.setBorder(tbPSX);
		panelSX.add(sliderX);
		panelSX.setBackground(one);
		tbPSX.setTitleColor(two);
		
		sliderY = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderY.setMinorTickSpacing(15);
		sliderY.setMajorTickSpacing(45);
		sliderY.setPaintTicks(true);
		sliderY.setPaintLabels(true);
		sliderY.setBackground(one);
		sliderY.setForeground(two);
		
		panelSY = new JPanel();
		TitledBorder tbPSY = new TitledBorder("Rotación en el eje Y");
		tbPSY.setTitleJustification(TitledBorder.CENTER);
		panelSY.setBorder(tbPSY);
		panelSY.add(sliderY);
		panelSY.setBackground(one);
		tbPSY.setTitleColor(two);
		
		sliderZ = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderZ.setMinorTickSpacing(15);
		sliderZ.setMajorTickSpacing(45);
		sliderZ.setPaintTicks(true);
		sliderZ.setPaintLabels(true);
		sliderZ.setBackground(one);
		sliderZ.setForeground(two);
		
		panelSZ = new JPanel();
		TitledBorder tbPSZ = new TitledBorder("Rotación en el eje Z");
		tbPSZ.setTitleJustification(TitledBorder.CENTER);
		panelSZ.setBorder(tbPSZ);
		panelSZ.add(sliderZ);
		panelSZ.setBackground(one);
		tbPSZ.setTitleColor(two);
		
		btnRef = new JButton("Reflejar");
		URL ruta = getClass().getResource("/Resources/reflect.png");
		btnRef.setIcon(new ImageIcon(ruta));
		btnRef.setBackground(one);
		btnRef.setForeground(two);
		btnRef.setBorder(null);
		
		southPanel = new JPanel(new GridLayout(1,3));
		southPanel.add(btnRef); southPanel.add(panelSX); southPanel.add(panelSY); southPanel.add(panelSZ);
		
		sliderSca = new JSlider(JSlider.VERTICAL,100,2300,1000);
		sliderSca.setMinorTickSpacing(50);
		sliderSca.setMajorTickSpacing(100);
		sliderSca.setPaintTicks(true);
		sliderSca.setPaintLabels(true);
		sliderSca.setBackground(one);
		sliderSca.setForeground(two);
		
		W.add(sliderSca,BorderLayout.EAST);
		
		W.add(southPanel,BorderLayout.SOUTH);
		
		Fig3D = new Figure3D();
		
		sliderX.addChangeListener(this);
		sliderY.addChangeListener(this);
		sliderZ.addChangeListener(this);
		sliderSca.addChangeListener(this);
		
		//Escalamiento con la rueda del ratón
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				int rotSent = e.getWheelRotation();
				if(rotSent > 0)
					Fig3D.scale(1.05,1.05,1.05);
				else
					if(rotSent < 0)
						Fig3D.scale(0.95,0.95,0.95);
				repaint();
			}
		});
		
		//Traslación con el arrastre del ratón
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent ev) {
				int xc = ev.getX(), yc = ev.getY();
				drag = Fig3D.isInside(xc,yc);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent ev) {
				if(drag) {
					int xc = ev.getX(), yc = ev.getY();
					Fig3D.translate(xc,yc);
					repaint();
				}
			}
		});
		
		btnRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ans = new ReflectDialog(Interface.this,true).showDialog(),refx,refy;
				if(ans == 1)
					Fig3D.scale(-1, 1, 1);
				else
					if(ans == 2)
						Fig3D.scale(1, -1, 1);
					else
						if(ans == 3)
							Fig3D.scale(1, 1, -1);
						else
							if(ans == 4)
								Fig3D.scale(-1, -1, 1);
							else
								if(ans == 5)
									Fig3D.scale(-1, 1, -1);
								else
									if(ans == 6)
										Fig3D.scale(1, -1, -1);
									else
										if(ans == 7)
											Fig3D.scale(-1, -1, -1);
				repaint();
			}
		});
		
		W.setVisible(true);
		W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Pintar la figura en la interface
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Fig3D.convertTo2D();
		Fig3D.draw(g);
		Fig3D.topView(g);
		Fig3D.sideView(g);
		Fig3D.frontView(g);
	}
	
	//EVENTOS DE ROTACIÓN
	public void stateChanged(ChangeEvent ev) {
		if(ev.getSource() == sliderX || ev.getSource() == sliderY || ev.getSource() == sliderZ)
			Fig3D.rotateXYZH(sliderX.getValue(),sliderY.getValue(),sliderZ.getValue());
		else
			if(ev.getSource() == sliderSca) {
				int distance = sliderSca.getValue();
				Fig3D.updateDistance(distance);
			}
		repaint();
	}
	
	public static void main(String[] args) {
		new Interface();
	}
}
