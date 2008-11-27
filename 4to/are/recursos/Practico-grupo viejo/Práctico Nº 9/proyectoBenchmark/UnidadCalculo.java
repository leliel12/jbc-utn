

public class UnidadCalculo implements Comparable,Valores
{
	private int codigo,inicio,fin,incluidoEn;
	private Lista inclusiones;
	
	public UnidadCalculo(int codigo,int inicio,int fin)
	{
		setCodigo(codigo);
		setInicio(inicio);
		setFin(fin);
		setIncluidoEn(-1);
		inclusiones=new Lista();
	}
	
	public UnidadCalculo(UnidadCalculo unidad)
	{
		setCodigo(unidad.getCodigo());
		setInicio(unidad.getInicio());
		setFin(unidad.getFin());
		setIncluidoEn(unidad.getIncluidoEn());
		setInclusiones(unidad.getInclusiones());		
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	
	public int getInicio()
	{
		return inicio;
	}
	
	public int getFin()
	{
		return fin;
	}
	
	public int getIncluidoEn()
	{
		return incluidoEn;
	}
	
	public Lista getInclusiones()
	{
		return inclusiones;
	}
	
	public void setCodigo(int codigo)
	{
		this.codigo=codigo;
	}
	
	public void setInicio(int inicio)
	{
		this.inicio=inicio;
	}
	
	public void setFin(int fin)
	{
		this.fin=fin;
	}
	
	public void setIncluidoEn(int incluidoEn)
	{
		this.incluidoEn=incluidoEn;
	}
	
	public void setInclusiones(Lista inclusiones)
	{
		this.inclusiones=inclusiones;
	}
	
	public void setearLista(UnidadCalculo unidades[]) throws Exception
	{
		Nodo r=inclusiones.getFrente();
		UnidadCalculo aux=null;
		while (r!=null)
		{
			int i;
			for (i=0;i<unidades.length&&unidades[i].compareTo((UnidadCalculo)r.getInfo())!=0;i++);
			if (i==unidades.length)
			{
				throw new Exception ("Excepcion de de seteo de copia de unidades");
			}
			r.setInfo(unidades[i]);
			r=r.getNext();
		}
	}
	
	public boolean incluye(UnidadCalculo pos)
	{
		return (pos.getInicio()>inicio&&pos.getFin()<fin);
	}
	
	public boolean insertar(UnidadCalculo pos)
	{
		return inclusiones.insertarOrdenado(pos);
	}
	
	public boolean eliminar(UnidadCalculo pos)
	{
		return inclusiones.borrar(pos);
	}
	
	public String mostrarInclusiones()
	{
		Comparable aux[]=inclusiones.getInfos();
		String str=getDescripcion()+"\n"+"incluye: "+"\n";
		for (int i=0;i<aux.length;i++)
		str+=((UnidadCalculo)aux[i]).getDescripcion()+"\n";
		return str;
	}
	
	public String getDescripcion()
	{
		return "codigo: "+codigo+" inicio: "+inicio+" fin: "+fin;
	}
	
	public static String getStringUnidadCalculos(UnidadCalculo posiciones[])
	{
		String aux="";
		for (int i=0;i<posiciones.length;i++)
		{
		aux+=posiciones[i].getDescripcion()+"\n";
		}
		return aux;
	}
	
	public static String getStringInclusionesUnidadCalculos(UnidadCalculo posiciones[])
	{
		String aux="";
		for (int i=0;i<posiciones.length;i++)
		{
			aux+=posiciones[i].mostrarInclusiones()+"\n";	
		}
		return aux;		
	}
	
	public static UnidadCalculo[] getUnidadCalculosLista(Lista lista)
	{
		UnidadCalculo posiciones[]=new UnidadCalculo[lista.getCantidadNodos()];
		Comparable aux[]=lista.getInfos();
		for (int i=0;i<aux.length;i++)
		posiciones[i]=(UnidadCalculo)aux[i];
		return posiciones;			
	}
	
	public void calcular(UnidadSintactica unidades[]) throws Exception
	{
		UnidadCalculo unidadCalculo=null;
		Comparable aux[]=inclusiones.getInfos();
		GestionVector.invertirVector(aux);
		for (int i=0;i<aux.length;i++)
		{
			unidadCalculo=(UnidadCalculo)aux[i];
			unidadCalculo.calcular(unidades);
		}
		UnidadSintactica calculos[]=UnidadSintactica.getUnidades(unidades,inicio,fin);		
		resolverUnidadCalculo(calculos);
	}
	
	private void resolverUnidadCalculo(UnidadSintactica calculos[]) throws Exception
	{
		if (estructuraFuncion(calculos)) return;
		if (estructuraSinCalculo(calculos)) return;
		if (estructuraOperacion(calculos)) return;
	}
	
	private boolean estructuraFuncion(UnidadSintactica calculos[]) throws Exception
	{
		if (calculos[0].getTipo()!=FUNCION) return false;
		double valor=marcarSimple(1,calculos);
		calculos[0].setTipo(NUMERICO);
		calculos[0].setExpresion(""+Matematica.calcular(calculos[0].getExpresion(),valor));
		return true;
	}
	
	private boolean estructuraSinCalculo(UnidadSintactica calculos[]) throws Exception
	{
		for (int i=0;i<calculos.length;i++)
			if (calculos[i].getTipo()==OPERACION) return false;
		double valor=marcarSimple(0,calculos);
		calculos[0].setTipo(NUMERICO);
		calculos[0].setExpresion(""+valor);	
		return true;
	}
	
	private boolean estructuraOperacion(UnidadSintactica calculos[]) throws Exception
	{
		double valor1,valor2;
		valor1=valor2=0;
		String operacion="";
		boolean valor1Encontrado=false;
		boolean valor2Encontrado=false;
		int cant=0;
		for (int i=1;i<calculos.length;i++)		
		{
			if (calculos[i].getTipo()==OPERACION)
				cant++;
		}
		if (cant==0)
		{
			throw new Exception("Excepcion de operaciones de calculo");
		}
		int cantParcial=0;
		while (cant!=cantParcial)
		{
			valor1Encontrado=false;
			valor2Encontrado=false;
			operacion="";
			for (int i=1;i<calculos.length;i++)		
			{
				if (calculos[i].getTipo()==NUMERICO)
				{
					if (valor1Encontrado)
					{
						valor2=Double.parseDouble(calculos[i].getExpresion());
						valor2Encontrado=true;
						calculos[i].setTipo(MARCA);
						calculos[i].setExpresion("");						
						break;
					}
					else
					{
						valor1=Double.parseDouble(calculos[i].getExpresion());					
						valor1Encontrado=true;
					}
				} else
				if (calculos[i].getTipo()==OPERACION)
				{
					if (valor1Encontrado&&!valor2Encontrado)
					{
						operacion=calculos[i].getExpresion();
					} else
					{
						throw new Exception("Excepcion de valores de calculo de operacion");
					}
				} 
				calculos[i].setTipo(MARCA);
				calculos[i].setExpresion("");
			}
			if (valor1Encontrado&&valor2Encontrado&&!operacion.equals(""))
			{
				calculos[1].setExpresion(""+Matematica.calcular(operacion,valor1,valor2));
				calculos[1].setTipo(NUMERICO);
				cantParcial++;
			} else
			{
				throw new Exception("Excepcion de valores de calculo de operacion final");
			}
		}
		return true;			
	}
	
	public static double marcarSimple(int pos,UnidadSintactica calculos[]) throws Exception
	{
		int c=0;
		int i;
		double valor=0;
		for (i=pos;i<calculos.length;i++)
		{
			if (calculos[i].getTipo()==NUMERICO)
			{
				valor=Double.parseDouble(calculos[i].getExpresion());
				c++;
			}
			if (!calculos[i].getExpresion().equals(""))
			calculos[i].setTipo(MARCA);
			calculos[i].setExpresion("");
		}
		if (c!=1)
		{
			throw new Exception("error marcacion");
		}
		return valor;
	}
	
	public int compareTo(Object o)
	{
		if (o.getClass()!=UnidadCalculo.class) return -1;
		UnidadCalculo u=(UnidadCalculo)o;
		return this.codigo-u.codigo;
	}	
}