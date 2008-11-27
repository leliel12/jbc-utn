public class Derivada
{
	private double factor;
	private int orden;
	private int numero;
	private int pivot;
	private int factores[];
	private double factorError;
	private int potenciaError;
	private int derivadaError1;
	private int derivadaError2;
	
	
	public Derivada(int numero,int orden,double factor,int pivot,int factores[],double factorError,int potenciaError,int derivadaError1,int derivadaError2)
	{
		setNumero(numero);
		setOrden(orden);
		setFactor(factor);
		setPivot(pivot);
		setFactores(factores);
		setFactorError(factorError);
		setPotenciaError(potenciaError);
		setDerivadaError1(derivadaError1);
		setDerivadaError2(derivadaError2);
	}
	
	public Derivada(int numero,int orden,double factor,int pivot,int factores[])
	{
		setNumero(numero);
		setOrden(orden);
		setFactor(factor);
		setPivot(pivot);
		setFactores(factores);
	}	
	
	public int getNumero()
	{
		return numero;
	}
	
	public int getOrden()
	{
		return orden;		
	}
	
	public double getFactor()
	{
		return factor;
	}
	
	public int getPivot()
	{
		return pivot;
	}
	
	public int getCantidadPuntos()
	{
		return factores.length;
	}
	
	public int[] getFactores()
	{
		return factores;
	}
	
	public double getFactorError()
	{
		return factorError;
	}
	
	public int getPotenciaError()
	{
		return potenciaError;
	}
	
	public int getDerivadaError1()
	{
		return derivadaError1;
	}
	
	public int getDerivadaError2()
	{
		return derivadaError2;
	}	
	
	public void setNumero(int numero)
	{
		this.numero=numero;
	}
	
	public void setOrden(int orden)
	{
		this.orden=orden;
	}
	
	public void setFactor(double factor)
	{
		this.factor=factor;
	}
	
	public void setPivot(int pivot)
	{
		this.pivot=pivot;
	}
	
	public void setFactores(int factores[])
	{
		this.factores=factores;
	}
	
	public void setFactorError(double factorError)
	{
		this.factorError=factorError;
	}
	
	public void setPotenciaError(int potenciaError)
	{
		this.potenciaError=potenciaError;
	}
	
	public void setDerivadaError1(int derivadaError1)
	{
		this.derivadaError1=derivadaError1;
	}
	
	public void setDerivadaError2(int derivadaError2)
	{
		this.derivadaError2=derivadaError2;
	}	
	
	public double calcular(double valores[],double h) throws Exception
	{
		if (valores.length<getCantidadPuntos())
		{
			throw new Exception("tamanio de vector incorrecto");
		}
		if (h<=0||h>=1)
		{
			throw new Exception("tamanio de h incorrecto");
		}
		return procedimiento(valores,h);
	}
	
	public double calcularError(double valores[],double h) throws Exception
	{
		double valorF=0;
		if (derivadaError2==-1)
			valorF=Matematica.calcularDerivada(derivadaError1,valores,h);
		else
			valorF=Matematica.calcularDerivada(derivadaError1,derivadaError2,valores,h);
		return factorError*Math.pow(h,potenciaError)*valorF;
	}
	
	private double procedimiento(double valores[],double h) throws Exception
	{
		double res=0;
		for (int i=0;i<factores.length;i++)
		{
			res+=factores[i]*valores[i];
		}
		res*=factor*(1/(Math.pow(h,orden)));
		return res;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass()!=Derivada.class) return -1;
		Derivada t=(Derivada)o;
		return this.numero-t.numero;
	}
}