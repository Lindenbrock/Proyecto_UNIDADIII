//	LUIS JOSÉ IXTA ZAMUDIO 17420565
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Figure3D {
	double Fig3D[][],Fig3DOriginal[][], Fig2D[][],Fig3DOOriginal[][];
	int Sequence[][], distance=800, mz=-250, transX, transY;
	Color one, two,three;
	
	//	ACTUALIZAR LA DISTANCIA DEL OBSERVADOR
	public void updateDistance(int dist) {
		distance = dist;
	}
	
	public Figure3D() {
		
		one = new Color(157,226,228,150);
		two = new Color(32,202,209,50);
		three = new Color(32,202,209);
		
		double C0 = 5 * (Math.sqrt(5) - 1) / 12,
				C1 = 5 / 6,
				C2 = (3 * Math.sqrt(5) - 5) / 2,
				C3 = 5 * (1 + Math.sqrt(5)) / 12,
				C4 = (5 - Math.sqrt(5)) / 2,
				C5 = 5 / 3,
				C6 = (5 + Math.sqrt(5)) / 2,
				C7 = (5 + 3 * Math.sqrt(5)) / 2;

		Fig3DOriginal = new double [][] {
				{ C6, 0.0,  C7}, { C6, 0.0, -C7}, {-C6, 0.0,  C7}, {-C6, 0.0, -C7}, { C7,  C6, 0.0}, { C7, -C6, 0.0}, {-C7,  C6, 0.0},
				{-C7, -C6, 0.0}, {0.0,  C7,  C6}, {0.0,  C7, -C6}, {0.0, -C7,  C6}, {0.0, -C7, -C6}, {0.0, 0.0,  C5}, {0.0, 0.0, -C5},
				{ C5, 0.0, 0.0}, {-C5, 0.0, 0.0}, {0.0,  C5, 0.0}, {0.0, -C5, 0.0}, { C2, 0.0,  C4}, { C2, 0.0, -C4}, {-C2, 0.0,  C4},
				{-C2, 0.0, -C4}, { C4,  C2, 0.0}, { C4, -C2, 0.0}, {-C4,  C2, 0.0}, {-C4, -C2, 0.0}, {0.0,  C4,  C2}, {0.0,  C4, -C2},
				{0.0, -C4,  C2}, {0.0, -C4, -C2}, { C0,  C1,  C3}, { C0,  C1, -C3}, { C0, -C1,  C3}, { C0, -C1, -C3}, {-C0,  C1,  C3},
				{-C0,  C1, -C3}, {-C0, -C1,  C3}, {-C0, -C1, -C3}, { C3,  C0,  C1}, { C3,  C0, -C1}, { C3, -C0,  C1}, { C3, -C0, -C1},
				{-C3,  C0,  C1}, {-C3,  C0, -C1}, {-C3, -C0,  C1}, {-C3, -C0, -C1}, { C1,  C3,  C0}, { C1,  C3, -C0}, { C1, -C3,  C0},
				{ C1, -C3, -C0}, {-C1,  C3,  C0}, {-C1,  C3, -C0}, {-C1, -C3,  C0}, {-C1, -C3, -C0},
		};
		
		Fig3DOOriginal = new double [Fig3DOriginal.length][3];
		Fig3D = new double[Fig3DOriginal.length][3];
		Fig2D = new double[Fig3DOriginal.length][2];
		Sequence = new int[][] {
			{  0, 14, 22 }, {  0, 22, 46 }, {  0, 46, 26 }, {  0, 26, 34 }, {  0, 34, 20 }, {  0, 20, 36 }, {  0, 36, 28 }, {  0, 28, 48 },
			{  0, 48, 23 }, {  0, 23, 14 }, {  1, 14, 23 }, {  1, 23, 49 }, {  1, 49, 29 }, {  1, 29, 37 }, {  1, 37, 21 }, {  1, 21, 35 },
			{  1, 35, 27 }, {  1, 27, 47 }, {  1, 47, 22 }, {  1, 22, 14 }, {  2, 15, 25 }, {  2, 25, 52 }, {  2, 52, 28 }, {  2, 28, 32 },
			{  2, 32, 18 }, {  2, 18, 30 }, {  2, 30, 26 }, {  2, 26, 50 }, {  2, 50, 24 }, {  2, 24, 15 }, {  3, 15, 24 }, {  3, 24, 51 },
			{  3, 51, 27 }, {  3, 27, 31 }, {  3, 31, 19 }, {  3, 19, 33 }, {  3, 33, 29 }, {  3, 29, 53 }, {  3, 53, 25 }, {  3, 25, 15 },
			{  4, 16, 26 }, {  4, 26, 30 }, {  4, 30, 18 }, {  4, 18, 40 }, {  4, 40, 23 }, {  4, 23, 41 }, {  4, 41, 19 }, {  4, 19, 31 },
			{  4, 31, 27 }, {  4, 27, 16 }, {  5, 17, 29 }, {  5, 29, 33 }, {  5, 33, 19 }, {  5, 19, 39 }, {  5, 39, 22 }, {  5, 22, 38 },
			{  5, 38, 18 }, {  5, 18, 32 }, {  5, 32, 28 }, {  5, 28, 17 }, {  6, 16, 27 }, {  6, 27, 35 }, {  6, 35, 21 }, {  6, 21, 45 },
			{  6, 45, 25 }, {  6, 25, 44 }, {  6, 44, 20 }, {  6, 20, 34 }, {  6, 34, 26 }, {  6, 26, 16 }, {  7, 17, 28 }, {  7, 28, 36 },
			{  7, 36, 20 }, {  7, 20, 42 }, {  7, 42, 24 }, {  7, 24, 43 }, {  7, 43, 21 }, {  7, 21, 37 }, {  7, 37, 29 }, {  7, 29, 17 },
			{  8, 12, 18 }, {  8, 18, 38 }, {  8, 38, 22 }, {  8, 22, 47 }, {  8, 47, 27 }, {  8, 27, 51 }, {  8, 51, 24 }, {  8, 24, 42 },
			{  8, 42, 20 }, {  8, 20, 12 }, {  9, 13, 21 }, {  9, 21, 43 }, {  9, 43, 24 }, {  9, 24, 50 }, {  9, 50, 26 }, {  9, 26, 46 },
			{  9, 46, 22 }, {  9, 22, 39 }, {  9, 39, 19 }, {  9, 19, 13 }, { 10, 12, 20 }, { 10, 20, 44 }, { 10, 44, 25 }, { 10, 25, 53 },
			{ 10, 53, 29 }, { 10, 29, 49 }, { 10, 49, 23 }, { 10, 23, 40 }, { 10, 40, 18 }, { 10, 18, 12 }, { 11, 13, 19 }, { 11, 19, 41 },
			{ 11, 41, 23 }, { 11, 23, 48 }, { 11, 48, 28 }, { 11, 28, 52 }, { 11, 52, 25 }, { 11, 25, 45 }, { 11, 45, 21 }, { 11, 21, 13 },
		};
		for(int i=0;i<Fig3D.length;i++) {
			Fig3DOOriginal[i][0] = Fig3D[i][0] = Fig3DOriginal[i][0] *= 7;
			Fig3DOOriginal[i][1] = Fig3D[i][1] = Fig3DOriginal[i][1] *= 7;
			Fig3DOOriginal[i][2] = Fig3D[i][2] = Fig3DOriginal[i][2] *= 7;
		}
		
		transX = 400; transY = 230;
	}
	
	//	Convertir coordenadas 3D a 2D antes de dibujar
	public void convertTo2D() {
		for(int i=0;i<Fig2D.length;i++) {
			//cx = (d*x)/(z+mz)
			Fig2D[i][0] = (distance*Fig3D[i][0]) / (Fig3D[i][2]+mz);
			//cy = (d*y)/(z+mz)
			Fig2D[i][1] = (distance*Fig3D[i][1]) / (Fig3D[i][2]+mz);
			Fig2D[i][0]+=transX;
			Fig2D[i][1]+=transY;
		}			
	}
	
	//	DIBUJAR FIGURA 3D
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		for(int i=0;i<=Sequence.length-1;i++) {
			GeneralPath gp = new GeneralPath();
			gp.moveTo(Fig2D[Sequence[i][0]][0], Fig2D[Sequence[i][0]][1]);
			for(int j=1;j<Sequence[i].length;j++)
				gp.lineTo(Fig2D[Sequence[i][j]][0], Fig2D[Sequence[i][j]][1]);
			gp.closePath();
			g2.setPaint(two);
			g2.fill(gp);
		}
		g2.setPaint(three);
		for(int i=0;i<=Sequence.length-1;i++) {
			for(int j=0;j<Sequence[i].length-1;j++)
				g2.drawLine((int)Fig2D[Sequence[i][j]][0], (int)Fig2D[Sequence[i][j]][1], (int)Fig2D[Sequence[i][j+1]][0], (int)Fig2D[Sequence[i][j+1]][1]);
			g2.drawLine((int)Fig2D[Sequence[i][Sequence[i].length-1]][0], (int)Fig2D[Sequence[i][Sequence[i].length-1]][1], (int)Fig2D[Sequence[i][0]][0], (int)Fig2D[Sequence[i][0]][1]);
		}
	}
	
	//	GENERAR VISTA SUPERIOR
	public void topView(Graphics g) {
		double Fig3DTemp[][]=rotateXT(90);
		//	Elimina la profundidad de la figura
		for(int i=0;i<Fig3DTemp.length;i++) {
			Fig3DTemp[i][2] = 0;
			Fig3DTemp[i][0] += 60;
			Fig3DTemp[i][1] += 80;
		}
		drawView(g,Fig3DTemp,"VISTA SUPERIOR",12,30);
	}
	
	//	GENERAR VISTA LATERAL
	public void sideView(Graphics g) {
		double Fig3DTemp[][]=rotateY(-90);
		//	Elimina la profundidad de la figura
		for(int i=0;i<Fig3DTemp.length;i++) {
			Fig3DTemp[i][2] = 0;
			Fig3DTemp[i][1] *= -1;
			Fig3DTemp[i][0] += 60;
			Fig3DTemp[i][1] += 180;
		}
		drawView(g,Fig3DTemp,"VISTA LATERAL",17,135);
	}
	
	//	GENERAR VISTA FRONTAL
	public void frontView(Graphics g) {
		double Fig3DTemp[][]=new double[Fig3D.length][2];
		//	Elimina la profundidad de la figura
		for(int i=0;i<Fig3DTemp.length;i++) {
			Fig3DTemp[i][0] = Fig3DOOriginal[i][0];
			Fig3DTemp[i][1] = -Fig3DOOriginal[i][1];
			Fig3DTemp[i][0] += 60;
			Fig3DTemp[i][1] += 280;
		}
		drawView(g,Fig3DTemp,"VISTA FRONTAL",17,235);
	}
	
	//	DIBUJAR CUALQUIER VISTA DE LA FIGURA
	public void drawView(Graphics g, double Fig3DTemp[][], String msg, int Xco, int Yco) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(three);
		g.drawString(msg, Xco, Yco);
		for(int i=0;i<=Sequence.length-1;i++) {
			for(int j=0;j<Sequence[i].length-1;j++)
				g2.drawLine((int)Fig3DTemp[Sequence[i][j]][0], (int)Fig3DTemp[Sequence[i][j]][1], (int)Fig3DTemp[Sequence[i][j+1]][0], (int)Fig3DTemp[Sequence[i][j+1]][1]);
			g2.drawLine((int)Fig3DTemp[Sequence[i][Sequence[i].length-1]][0], (int)Fig3DTemp[Sequence[i][Sequence[i].length-1]][1], (int)Fig3DTemp[Sequence[i][0]][0], (int)Fig3DTemp[Sequence[i][0]][1]);
		}
	}
	
	// 	COMPROBAR SI LAS COORDENADAS DEL CLICK SE ENCUENTRAN DENTRO DEL ÁREA DE LA FIGURA
	public boolean isInside(int xc, int yc) {
		double minX=Fig2D[0][0],minY=Fig2D[0][1],maxX=0,maxY=0;
		// Se encuentran los valores menores
		for(int i=0;i<Fig2D.length;i++) {
			if(Fig2D[i][0] < minX) minX = Fig2D[i][0];
			if(Fig2D[i][1] < minY) minY = Fig2D[i][1];
		}
		// Se encuentran los valores mayores
		for(int i=1;i<Fig2D.length;i++) {
			if(Fig2D[i][0] > maxX) maxX = Fig2D[i][0];
			if(Fig2D[i][1] > maxY) maxY = Fig2D[i][1];
		}
		if((xc >= minX && xc <= maxX) && (yc >= minY && yc <= maxY))
			return true;
		else
			return false;
	}
	
	/*		------TRASFORMACIONES------		*/
	
	//	ROTACIÓN EN EL EJE Y
	public double[][] rotateY(int deg) {
		//[x*Cos - z*Sen, y, x*Sen + z*cos]
		double Fig3DTemp[][] = new double[Fig3DOOriginal.length][3];
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOOriginal[i][0], y = Fig3DOOriginal[i][1], z = Fig3DOOriginal[i][2];
			Fig3DTemp[i][0] = x*cos + z*sin;
			Fig3DTemp[i][1] = y;
			Fig3DTemp[i][2] = -x*sin + z*cos;
		}
		return Fig3DTemp;
	}
	
	//  ROTACIÓN EN EL EJE X QUE RETORNA LA FIGURA CON LA TRASFORMACIÓN APLICADA
	public double[][] rotateXT(int deg) {
		double Fig3DTemp[][] = new double[Fig3DOOriginal.length][3];
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOOriginal[i][0],y = Fig3DOOriginal[i][1], z = Fig3DOOriginal[i][2];
			Fig3DTemp[i][0] = x;
			Fig3DTemp[i][1] = y*cos - z*sin;
			Fig3DTemp[i][2] = y*sin + z*cos;
		}
		
		return Fig3DTemp;
	}
	
	//	ROTACIÓN CON COORDENADAS HOMOGÉNEAS
	public void rotateXYZH(int degX, int degY, int degZ) {
		/* x*(Cos 2* Cos 3)+y*((Sen 1*(-Sen 2))*Cos 3+Cos 1*-Sen 3)+z*((Cos 1*-Sen 2)*Cos 3+(-Sen 1)*( -Sen 3)),
		  x*( Cos 2*Sen 3)+y*(Sen 1*(-Sen 2)* Sen 3+ Cos 1*Cos 2)+z(Cos 1*(-Sen 2)*(Sen 3)+(-Sen 1*Cos 3),
		  x*(Sen 2)+y*(Sen 1*Cos 2)+z*(Cos 1*Cos 2) */
		double degRadX = Math.toRadians(degX), degRadY = Math.toRadians(degY), degRadZ = Math.toRadians(degZ);
		double cos1 = Math.cos(degRadX), sin1 = Math.sin(degRadX);
		double cos2 = Math.cos(degRadY), sin2 = Math.sin(degRadY);
		double cos3 = Math.cos(degRadZ), sin3 = Math.sin(degRadZ);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOriginal[i][0],y = Fig3DOriginal[i][1], z = Fig3DOriginal[i][2];
			Fig3D[i][0] = x*(cos2*cos3) + y*(sin1*(-sin2)*cos3 + cos1*(-sin3)) + z*(cos1*(-sin2)*cos3 + (-sin1)*(-sin3));
			Fig3D[i][1] = x*(cos2*sin3) + y*(sin1*(-sin2)*sin3 + cos1*cos3) + z*(cos1*(-sin2)*(sin3)+(-sin1)*cos3);
			Fig3D[i][2] = x*(sin2)+y*(sin1*cos2)+z*(cos1*cos2);
		}
	}
	
	//	ESCALAMIENTO
	public void scale(double Sx, double Sy, double Sz) {
		//x*Sx,y*Sy,z*Sz
		for(int i=0;i < Fig3D.length;i++) {
			Fig3DOriginal[i][0] = Fig3D[i][0] *= Sx;
			Fig3DOriginal[i][1] = Fig3D[i][1] *= Sy;
			Fig3DOriginal[i][2] = Fig3D[i][2] *= Sz;
		}
	}
	
	// TRASLACIÓN
	public void translate(int xc, int yc) {
		transX = xc;
		transY = yc;
	}
}
