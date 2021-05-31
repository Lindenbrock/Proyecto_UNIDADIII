//	LUIS JOSÉ IXTA ZAMUDIO 17420565

import java.awt.Graphics;

public class Figure3D {
	double Fig3D[][],Fig3DOriginal[][], Fig2D[][];
	int Sequence[], distance=800, mz=-250;
	
	//	ACTUALIZAR LA DISTANCIA DEL OBSERVADOR
	public void updateDistance(int dist) {
		distance = dist;
	}
	
	public Figure3D(double Fig[][],int Seq[]) {
		Fig3DOriginal = new double [Fig.length][3];
		Fig3D = new double[Fig.length][3];
		Fig2D = new double[Fig.length][2];
		Sequence = new int[Seq.length];
		
		for(int i=0;i<Fig3D.length;i++) {
			Fig3DOriginal[i][0] = Fig3D[i][0] = Fig[i][0];
			Fig3DOriginal[i][1] = Fig3D[i][1] = Fig[i][1];
			Fig3DOriginal[i][2] = Fig3D[i][2] = Fig[i][2];
		}
		
		for(int i=0;i<Sequence.length;i++)
			Sequence[i] = Seq[i];
	}
	
	//	Convertir coordenadas 3D a 2D antes de dibujar
	public void convertTo2D() {
		for(int i=0;i<Fig2D.length;i++) {
			//cx = (d*x)/(z+mz)
			Fig2D[i][0] = (distance*Fig3D[i][0]) / (Fig3D[i][2]+mz);
			//cy = (d*y)/(z+mz)
			Fig2D[i][1] = (distance*Fig3D[i][1]) / (Fig3D[i][2]+mz);
			Fig2D[i][0]+=400;
			Fig2D[i][1]+=300;
		}			
	}
	
	//	DIBUJAR FIGURA 3D
	public void draw(Graphics g) {
		for(int i=0;i<=Sequence.length-1;i+=2)
			g.drawLine((int)Fig2D[Sequence[i]][0], (int)Fig2D[Sequence[i]][1], (int)Fig2D[Sequence[i+1]][0], (int)Fig2D[Sequence[i+1]][1]);
	}
	
	/*		------TRASFORMACIONES------		*/
	
	//ROTACIÓN EN EL EJE X
	public void rotateX(int deg) {
		//[x, y*Cos - z*Sen, y*Sen + z*cos]
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double y = Fig3DOriginal[i][1], z = Fig3DOriginal[i][2];
			Fig3D[i][1] = y*cos - z*sin;
			Fig3D[i][2] = y*sin + z*cos;
		}
	}
	
	//ROTACIÓN EN EL EJE Y
	public void rotateY(int deg) {
		//[x*Cos - z*Sen, y, x*Sen + z*cos]
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOriginal[i][0], z = Fig3DOriginal[i][2];
			Fig3D[i][0] = x*cos - z*sin;
			Fig3D[i][2] = x*sin + z*cos;
		}
	}
	
	//ROTACIÓN EN EL EJE Z
	public void rotateZ(int deg) {
		//[x*Cos - y*Sen, x*Sen  + y*Cos, z]
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOriginal[i][0], y = Fig3DOriginal[i][1];
			Fig3D[i][0] = x*cos - y*sin;
			Fig3D[i][1] = x*sin + y*cos;
		}
	}
	
	
	//ROTACIÓN ENLAZANDO LOS TRES EJES
	//  ROTACIÓN EN EL EJE X QUE RETORNA LA FIGURA CON LA TRASFORMACIÓN APLICADA
	public double[][] rotateXT(int deg) {
		double Fig3DTemp[][] = new double[Fig3DOriginal.length][3];
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOriginal[i][0],y = Fig3DOriginal[i][1], z = Fig3DOriginal[i][2];
			Fig3DTemp[i][0] = x;
			Fig3DTemp[i][1] = y*cos - z*sin;
			Fig3DTemp[i][2] = y*sin + z*cos;
		}
		
		return Fig3DTemp;
	}
	
	//	ROTACIÓN EN EL EJE Y QUE RETORNA LA FIGURA CON LA TRASFORMACIÓN APLICADA
	public double[][] rotateYT(int deg, double Fig3DAux[][]) {
		double Fig3DTemp[][] = new double[Fig3DOriginal.length][3];
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DAux[i][0],y = Fig3DAux[i][1], z = Fig3DAux[i][2];
			Fig3DTemp[i][0] = x*cos - z*sin;
			Fig3DTemp[i][1] = y;
			Fig3DTemp[i][2] = x*sin + z*cos;
		}
		
		return Fig3DTemp;
	}
	
	//	ROTACIÓN EN EL EJE Z APLICADA A LA FIGURA TRANSFORMADA POR LOS OTROS MÉTODOS
	public void rotateZT(int deg, double[][] Fig3DAux) {
		//[x*Cos - y*Sen, x*Sen  + y*Cos, z]
		double degRad = Math.toRadians(deg), cos = Math.cos(degRad), sin = Math.sin(degRad);
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DAux[i][0],y = Fig3DAux[i][1], z = Fig3DAux[i][2];
			Fig3D[i][0] = x*cos - y*sin;
			Fig3D[i][1] = x*sin + y*cos;
			Fig3D[i][2] = z;
		}
	}
	
	//	MANDAR ROTAR EN X, LUEGO EN Y, Y POR ULTIMO EN Z LA MISMA FIGURA
	public void rotateXYZ(int degX, int degY, int degZ) {
		double Fig3DRes[][] = rotateXT(degX);
		Fig3DRes = rotateYT(degY,Fig3DRes);
		rotateZT(degZ, Fig3DRes);
	}
	
	//	ROTACIÓN CON COORDENADAS HOMOGÉNEAS
	public void rotateXYZH(int degX, int degY, int degZ) {
		/* x*(Cos 2* Cos 3)+y*((Sen 1*(-Sen 2))*Cos 3+Cos 1*-Sen 3)+z*((Cos 1*-Sen 2)*Cos 3+(-Sen 1)*( -Sen 3)),
		  x*( Cos 2*Sen 3)+y*((Sen 1*(-Sen 2))* Sen 3+-Cos 1*Cos 2)+z(Cos 1*(-Sen 2)*(Sen 3)+(-Sen 1*Cos 3),
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
	public void scaleXYZ(double scale) {
		//x+Ty,y+Ty,z+Tz
		for(int i=0;i < Fig3D.length;i++) {
			double x = Fig3DOriginal[i][0],y = Fig3DOriginal[i][1], z = Fig3DOriginal[i][2];
			Fig3D[i][0] = Fig3DOriginal[i][0]*scale;
			Fig3D[i][1] = Fig3DOriginal[i][1]*scale;
			Fig3D[i][2] = Fig3DOriginal[i][2]*scale;
		}
	}
}
