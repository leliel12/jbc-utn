public class GestionGraficos
{
	public static void mostrarVentanaGraficoBarras(int valores[][],String lX[],String lY[],String titulo,String titulos[])
	{
		VentanaGraficoBarras ventana=new VentanaGraficoBarras (titulo,valores,lX,lY,titulo,true,true,true,titulos,new GestionGrafico());
		ventana.setVisible(true);			
	}       

    public static String[] getLabelY(int v[],int cantidadIntervalos)
    {
   		double mayor=GestionVector.getMayor(v);
   		double tamanioIntervalo=mayor/cantidadIntervalos;
   		int i;
   		String aux[]=new String[cantidadIntervalos];
   		for (i=0;i<aux.length-1;i++)
   		{
   			aux[i]=new String(""+Matematica.redondear(tamanioIntervalo*(i+1),4));
   		}
   		aux[aux.length-1]=new String(""+Matematica.redondear(mayor,4));   		
    	return aux;
    }    

	public static int[][] getValoresBarras(double v[])
	{
		int aux[][]=new int[v.length][2];
		for (int i=0;i<v.length;i++)
			aux[i][1]=(int)v[i];
		return aux;
	}

	public static int[] getValoresTorta(double v[])
	{
		int aux[]=new int[v.length];
		for (int i=0;i<v.length;i++)
			aux[i]=(int)v[i];
		return aux;
	}

	public static String[] getLabelY(double v[])
	{
		return getLabelY(v,10);
	}

    public static String[] getLabelY(double v[],int cantidadIntervalos)
    {
   		double mayor=GestionVector.getMayor(v);
   		double tamanioIntervalo=mayor/cantidadIntervalos;
   		int i;
   		String aux[]=new String[cantidadIntervalos];
   		for (i=0;i<aux.length-1;i++)
   		{
   			aux[i]=new String(""+Matematica.redondear(tamanioIntervalo*(i+1),4));
   		}
   		aux[aux.length-1]=new String(""+Matematica.redondear(mayor,4));   		
    	return aux;
    }     

	public static void mostrarVentanaGraficoTorta(int valores[],String label[],String titulo)
	{
		VentanaGraficoTorta ventana=new VentanaGraficoTorta (valores,label,titulo,new GestionGrafico());
		ventana.setVisible(true);			
	}       
}