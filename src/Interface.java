//	LUIS JOSÉ IXTA ZAMUDIO 17420565

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

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
	
	public Interface (double figCoordinates[][], int figSequence[]) {
		W = new JFrame("Transformaciones en 3D");
		W.setSize(800,600);
		W.setLocationRelativeTo(this);
		W.add(this);
		
		sliderX = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderX.setMinorTickSpacing(15);
		sliderX.setMajorTickSpacing(45);
		sliderX.setPaintTicks(true);
		sliderX.setPaintLabels(true);
		
		panelSX = new JPanel();
		TitledBorder tbPSX = new TitledBorder("Rotación en el eje X");
		tbPSX.setTitleJustification(TitledBorder.CENTER);
		panelSX.setBorder(tbPSX);
		panelSX.add(sliderX);
		
		sliderY = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderY.setMinorTickSpacing(15);
		sliderY.setMajorTickSpacing(45);
		sliderY.setPaintTicks(true);
		sliderY.setPaintLabels(true);
		
		panelSY = new JPanel();
		TitledBorder tbPSY = new TitledBorder("Rotación en el eje Y");
		tbPSY.setTitleJustification(TitledBorder.CENTER);
		panelSY.setBorder(tbPSY);
		panelSY.add(sliderY);
		
		sliderZ = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
		sliderZ.setMinorTickSpacing(15);
		sliderZ.setMajorTickSpacing(45);
		sliderZ.setPaintTicks(true);
		sliderZ.setPaintLabels(true);
		
		panelSZ = new JPanel();
		TitledBorder tbPSZ = new TitledBorder("Rotación en el eje Z");
		tbPSZ.setTitleJustification(TitledBorder.CENTER);
		panelSZ.setBorder(tbPSZ);
		panelSZ.add(sliderZ);
		
		southPanel = new JPanel(new GridLayout(1,3));
		southPanel.add(panelSX); southPanel.add(panelSY); southPanel.add(panelSZ);
		
		sliderSca = new JSlider(JSlider.VERTICAL,100,2300,1000);
		sliderSca.setMinorTickSpacing(50);
		sliderSca.setMajorTickSpacing(100);
		sliderSca.setPaintTicks(true);
		sliderSca.setPaintLabels(true);
		
		W.add(sliderSca,BorderLayout.EAST);
		
		W.add(southPanel,BorderLayout.SOUTH);
		
		Fig3D = new Figure3D(figCoordinates,figSequence);
		
		sliderX.addChangeListener(this);
		sliderY.addChangeListener(this);
		sliderZ.addChangeListener(this);
		sliderSca.addChangeListener(this);
		
		W.setVisible(true);
		W.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Pintar la figura en la interface
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Fig3D.convertTo2D();
		Fig3D.draw(g);
	}
	
	public static void main(String[] args) {
		//Trabajar con vector bidimencional
		double figCoordinates[][] = {
							{0,35,0},		//A 0
							{30,-35,20},	//B 1
							{-30,-35,20},	//C 2
							{-30,-35,-20},	//D 3
							{30,-35,-20}	//E 4
						};
		int figSequence[]= {2,1,2,3,3,4,4,1,1,0,2,0,3,0,4,0};
		
		new Interface(figCoordinates,figSequence);
	}

	
	//EVENTOS DE ROTACIÓN
	@Override
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
}
