import java.util.*;

public class AnalizadorFuncion implements Valores
{
	public static TipoUnidadSintactica funciones=new TipoUnidadSintactica("funciones",new String[] {"ln","sin","cos","tan"/*,"!"*/},true);
	public static TipoUnidadSintactica variables=new TipoUnidadSintactica("variables",new String[] {"x","y","z"},false);
	public static TipoUnidadSintactica constantes=new TipoUnidadSintactica("constantes",new String[] {""+NUMERO_NEPERIANO,""+PI},false);
	public static TipoUnidadSintactica operaciones=new TipoUnidadSintactica("operaciones",new String[] {SUMA,RESTA,"*","/",""+POTENCIA,""+RAIZ,""+LOGARITMO},false);
	public static TipoUnidadSintactica numeros=new TipoUnidadSintactica("numeros",new String[] {"0","1","2","3","4","5","6","7","8","9",".",NEGATIVO},true);
	public static TipoUnidadSintactica delimitadores=new TipoUnidadSintactica("delimitadores",new String[] {DELIMITADOR_INICIO,DELIMITADOR_FINAL},false);
	
	public static UnidadSintactica[] procesarUnidadSintactica(String expresion) throws Exception
	{
		UnidadSintactica unidades[]=procesarExpresion(expresion);
		Resultado resultado=analizarSintaxisUnidadSintacticas(unidades);
		if (!resultado.getResultado()) 
		{
			throw new Exception ("Error procesamiento"+"\n"+resultado.getDescripcion());
		}		
		convertirNumeros(unidades);
		unidades=rearmarExpresion(unidades);		
		return unidades;
	}
	
	public static UnidadCalculo[] procesarUnidadCalculo(String expresion) throws Exception
	{
		UnidadSintactica unidades[]=procesarUnidadSintactica(expresion);
		return procesarUnidadCalculo(unidades);
	}
	
	public static UnidadCalculo[] procesarUnidadCalculo(UnidadSintactica unidades[]) throws Exception
	{
		UnidadCalculo posiciones[]=getUnidadCalculosPila(unidades);
		organizarArbolCalculo(posiciones,unidades);		
		return posiciones;
	}
	
	public static double calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double x) throws Exception
	{
		return calcular(posiciones,unidades,new double[]{x,0,0});
	}
	
	public static double calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double x,double y) throws Exception
	{
		return calcular(posiciones,unidades,new double[]{x,y,0});
	}	
	
	public static double calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double x,double y,double z) throws Exception
	{
		return calcular(posiciones,unidades,new double[]{x,y,z});
	}	
	

	public static double procesarYCalcular(String expresion,double x) throws Exception
	{
		return procesarYCalcular(expresion,new double[]{x,0,0});
	}	
	
	public static double procesarYCalcular(String expresion,double x,double y) throws Exception
	{
		return procesarYCalcular(expresion,new double[]{x,y,0});
	}	
	
	public static double procesarYCalcular(String expresion,double x,double y,double z) throws Exception
	{
		return procesarYCalcular(expresion,new double[]{x,y,z});
	}		

	public static double procesarYCalcular(String expresion,double valores[]) throws Exception
	{
		UnidadSintactica unidades[]=procesarUnidadSintactica(expresion);
		UnidadCalculo posiciones[]=procesarUnidadCalculo(unidades);
		return calcular(posiciones,unidades,valores);
	}
	
	public static double calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double valores[]) throws Exception
	{
		UnidadSintactica unidSintacticaAux[]=copiarUnidadSintactica(unidades);
		UnidadCalculo unidCalculoAux[]=copiarUnidadCalculo(posiciones);
		setearUnidadesSintacticasCalculo(posiciones);		
		reemplazarVariables(unidades,valores);
		reemplazarConstantes(unidades);
		posiciones[0].calcular(unidades);
		double res=valorFinal(unidades);
		unidades=unidSintacticaAux;
		posiciones=unidCalculoAux;		
		return res;		
	}
	
	public static double valorFinal(UnidadSintactica unidades[])  throws Exception
	{
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==NUMERICO)
				return Double.parseDouble(unidades[i].getExpresion());
		}
		return 0;
	}
	
	public static double[] calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double valores[],boolean marca) throws Exception
	{
		double val[][]=new double[valores.length][3];
		for (int i=0;i<val.length;i++)
			val[i][0]=valores[i];
		return calcular(posiciones,unidades,val);
	}
	
	public static double[] calcular(UnidadCalculo posiciones[],UnidadSintactica unidades[],double valores[][]) throws Exception
	{
		double res[]=new double[valores.length];
		for (int i=0;i<valores.length;i++)
		{
			res[i]=calcular(posiciones,unidades,valores[i]);
		}
		return res;
	}
	
	
	private static UnidadSintactica[] copiarUnidadSintactica(UnidadSintactica unidades[])  throws Exception
	{
		UnidadSintactica nuevo[]=new UnidadSintactica[unidades.length];
		for (int i=0;i<unidades.length;i++)
			nuevo[i]=new UnidadSintactica(unidades[i]);
		return nuevo;
	}
	
	private static UnidadCalculo[] copiarUnidadCalculo(UnidadCalculo unidades[])  throws Exception
	{
		UnidadCalculo nuevo[]=new UnidadCalculo[unidades.length];
		for (int i=0;i<unidades.length;i++)
			nuevo[i]=new UnidadCalculo(unidades[i]);
		return nuevo;
	}	
	
	private static void setearUnidadesSintacticasCalculo(UnidadCalculo posiciones[]) throws Exception
	{
		for (int i=0;i<posiciones.length;i++)
		{
			posiciones[i].setearLista(posiciones);
		}
	}
	
	private static UnidadSintactica[] procesarExpresion(String expresion) throws Exception
	{
		if (expresion==null) return null;
		expresion=eliminarEspacios(expresion);
		if (expresion.length()==0) return null;
		String ultimo=""+expresion.charAt(0);
		int tipo=tipo(expresion.charAt(0));
		Lista lista=new Lista();
		for (int i=1;i<expresion.length();i++)
		{
			if (tipo==tipo(expresion.charAt(i)))
			{
				ultimo+=""+expresion.charAt(i);
			} else
			{
				lista.insertar(new UnidadSintactica(ultimo,tipo));
				tipo=tipo(expresion.charAt(i));
				ultimo=""+expresion.charAt(i);
			}
		}
		lista.insertar(new UnidadSintactica(ultimo,tipo));
		return refinarProcesoExpresion(getUnidadSintacticasLista(lista));
	}
	
	private static UnidadSintactica[] refinarProcesoExpresion(UnidadSintactica unidades[])  throws Exception
	{
		Lista lista=new Lista();
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==NUMERICO)
			{
				lista.insertar(unidades[i]);
			} else
			if (unidades[i].getTipo()==FUNCION)
			{
				Lista lista1=new Lista();
				procesarFuncion(unidades[i].getExpresion(),lista1);
				String f[]=GestionString.getStringsLista(lista1);
				int c=0;
				for (int k=0;k<f.length;k++)
				{
					c+=f[k].length();
				}
				String s=unidades[i].getExpresion().substring(c,unidades[i].getExpresion().length());
				if (!s.equals("")) 
					lista.insertar(new UnidadSintactica(s,FUNCION));
				for (int k=0;k<f.length;k++)
				{
					lista.insertar(new UnidadSintactica(f[k],FUNCION));
				}					
			} else
			{
				String aux=unidades[i].getExpresion();
				for (int j=aux.length()-1;j>=0;j--)
				{
					lista.insertar(new UnidadSintactica(""+aux.charAt(j),unidades[i].getTipo()));
				}
			}
		}
		return getUnidadSintacticasLista(lista);
	}
	
	private static void procesarFuncion(String expresion,Lista lista)  throws Exception
	{
		String f[]=funciones.getValores();
		boolean fin=false;
		for (int i=0;i<f.length&&!fin;i++)
		{
			if (f[i].length()<=expresion.length())
			{
				if (expresion.substring(0,f[i].length()).equals(f[i]))
				{
					lista.insertar(f[i]);
					procesarFuncion(expresion.substring(f[i].length(),expresion.length()),lista);
					fin=true;
				}
			}
		}
	}
	
	public static boolean getResultadoAnalisis(UnidadSintactica unidades[])  throws Exception
	{
		return analizarSintaxisUnidadSintacticas(unidades).getResultado();
	}
	
	public static Resultado analizarSintaxisUnidadSintacticas(UnidadSintactica unidades[])  throws Exception
	{
		Resultado resultado=esUnidadSintacticasValidas(unidades);
		if (resultado.getResultado())		
		{
			resultado=esOperacionesNoSecuenciales(unidades);
			if (resultado.getResultado())
			{
				resultado=esFuncionesValidas(unidades);
				if (resultado.getResultado())
				{
					resultado=esNumerosValidos(unidades);
					if (resultado.getResultado())
					{
						resultado=esConsistenteDelimitadores(unidades);
						if (resultado.getResultado())
						{
							resultado=esFormatoOperacionConsistente(unidades);
							if (resultado.getResultado())
							{
								resultado=esValoresNumericos(unidades);
								if (resultado.getResultado())
								{
									resultado=esFormatoFuncionConsistente(unidades);
									if (resultado.getResultado())
									{
										resultado=new Resultado(true,"analisis de expresion finalizado",SIN_ERROR);
									}
								}
							}
						}	
					}
				}
			}
		}
		return resultado;		
	}
	
	private static void convertirNumeros(UnidadSintactica unidades[])  throws Exception
	{
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==NUMERICO)
			{
				unidades[i].setExpresion(convertirNumero(unidades[i].getExpresion()));
			}
		}
	}
	 
	private static Resultado esValoresNumericos(UnidadSintactica unidades[])  throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=0;i<unidades.length-1;i++)
		{
			if (unidades[i].getTipo()==VARIABLE||unidades[i].getTipo()==CONSTANTE||unidades[i].getTipo()==NUMERICO)
			{
				if ((i+1)<unidades.length&&(unidades[i+1].getTipo()==VARIABLE||unidades[i+1].getTipo()==CONSTANTE||unidades[i+1].getTipo()==FUNCION||unidades[i+1].getTipo()==NUMERICO))
				{
					throw new Exception("valores numericos secuenciales: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion()+" posicion: "+i);
				}
				if ((i+1)<unidades.length&&unidades[i+1].getTipo()==DELIMITADOR&&unidades[i+1].getExpresion().equals(DELIMITADOR_INICIO))
				{
					throw new Exception("valor numerico mal acompaniado1: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion()+" posicion: "+i);					
				}
			}
		}
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==VARIABLE||unidades[i].getTipo()==CONSTANTE||unidades[i].getTipo()==NUMERICO)			
			{
				if (unidades[i-1].getTipo()==DELIMITADOR&&unidades[i-1].getExpresion().equals(DELIMITADOR_FINAL))
				{
					throw new Exception("valor numerico mal acompaniado2: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion()+" posicion: "+i);
				}
			}
		}
		return new Resultado(true,"valores numericos no secuenciales",SIN_ERROR);
	}
	
	private static Resultado esUnidadSintacticasValidas(UnidadSintactica unidades[])  throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=0;i<unidades.length;i++)
		{
			if(unidades[i].getTipo()==OTRO)
			{
				throw new Exception("UnidadSintactica sintactica invalida: "+unidades[i].getExpresion()+" posicion: "+i);
			}
		}
		return new Resultado(true,"UnidadSintacticas sintacticas validas",SIN_ERROR);
	}
	
	private static Resultado esConsistenteDelimitadores(UnidadSintactica unidades[])  throws Exception
	{
		Pila pila=new Pila();
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==DELIMITADOR)
			{
				String aux=unidades[i].getExpresion();
				if (aux.equals(DELIMITADOR_INICIO))
				{
					pila.insertar(aux);
				} 
				if (aux.equals(DELIMITADOR_FINAL))
				{
					Comparable t=pila.tomar();
					if (t==null) throw new Exception("delimitador final sobrante"+" posicion: "+i);
					String tomar=(String)t;
					if (tomar.equals(DELIMITADOR_FINAL)) throw new Exception("delimitador final repetido"+" posicion: "+i);
				}
			}
		}
		Comparable tom=pila.tomar();
		if (tom!=null) throw new Exception("delimitador sobrante "+(String)tom);
		for (int i=0;i<unidades.length-1;i++)
		{
			if (unidades[i].getTipo()==DELIMITADOR)
			{
				if (unidades[i].getExpresion().equals(DELIMITADOR_FINAL)&&(unidades[i+1].getTipo()==FUNCION||unidades[i+1].getTipo()==CONSTANTE||unidades[i+1].getTipo()==VARIABLE||unidades[i+1].getTipo()==NUMERICO))
				{
					throw new Exception ("delimitador mal acompaniado: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion());
				}
				if (unidades[i].getExpresion().equals(DELIMITADOR_INICIO)&&unidades[i+1].getTipo()==OPERACION)
				{
					throw new Exception ("delimitador mal acompaniado: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion());
				}
			}
		}
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==DELIMITADOR&&unidades[i-1].getTipo()==DELIMITADOR&&!unidades[i].getExpresion().equals(unidades[i-1].getExpresion()))
				throw new Exception ("delimitador mal acompaniado: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion());
		}
		return new Resultado(true,"consistencia delimitadores correcta",SIN_ERROR);
	}
	
	private static Resultado esOperacionesNoSecuenciales(UnidadSintactica unidades[])  throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==OPERACION&&unidades[i-1].getTipo()==OPERACION)
			{
				throw new Exception("operaciones sucesivas: "+unidades[i].getExpresion()+" y "+unidades[i-1].getExpresion()+" posicion: "+i);
			}
		}
		return new Resultado(true,"no hay operaciones sucesivas",SIN_ERROR);
	}
	
	private static Resultado esFuncionesValidas(UnidadSintactica unidades[]) throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==FUNCION&&!funciones.pertenece(unidades[i].getExpresion()))
			{
				throw new Exception("funcion invalida: "+unidades[i].getExpresion()+" posicion: "+i);
			}
		}
		return new Resultado(true,"funciones validas",SIN_ERROR);
	}
	
	private static Resultado esNumerosValidos(UnidadSintactica unidades[]) throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==NUMERICO)
			{
				try
				{
					Double.parseDouble(convertirNumero(unidades[i].getExpresion()));
				} catch (Exception qq)
				{
					throw new Exception("numero invalido: "+unidades[i].getExpresion()+" posicion: "+i);
				}
			}
		}
		return new Resultado(true,"numeros validos",SIN_ERROR);
	}
	
	private static String convertirNumero(String expresion) throws Exception
	{
		String aux="";
		for (int i=0;i<expresion.length();i++)
		{
			if ((""+expresion.charAt(i)).equals(NEGATIVO))
				aux+="-";
			else
				aux+=""+expresion.charAt(i);
		}		
		return aux;
	}
	
	private static Resultado esFormatoFuncionConsistente(UnidadSintactica unidades[]) throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");		
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==FUNCION)
			{
				if (unidades[i-1].getTipo()!=OPERACION&&unidades[i-1].getTipo()!=FUNCION)
				{
					if (unidades[i-1].getTipo()!=DELIMITADOR)
					{
						if (!unidades[i-1].getExpresion().equals(DELIMITADOR_FINAL))
						{
							throw new Exception("formato funcion inconsistente: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion()+" posicion: "+i);
						}						
					} else
					throw new Exception("formato funcion inconsistente: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion()+" posicion: "+i);
				}
			}
		}
		return new Resultado(true,"formato funcion consistente",SIN_ERROR);
	}
	
	private static Resultado esFormatoOperacionConsistente(UnidadSintactica unidades[]) throws Exception
	{
		if (unidades==null||unidades.length==0) throw new Exception("unidades invalidas");
		if (unidades[0].getTipo()==OPERACION) throw new Exception("operacion mal expresada1: "+unidades[0].getExpresion()+" posicion: "+0);
		for (int i=1;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==OPERACION)		
			{
				if (unidades[i-1].getTipo()!=NUMERICO&&unidades[i-1].getTipo()!=VARIABLE&&unidades[i-1].getTipo()!=CONSTANTE)
				{
					if (unidades[i-1].getTipo()!=DELIMITADOR)
					{
						throw new Exception("operacion mal expresada2: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion()+" posicion: "+i);		
					} else
					{
						if (unidades[i-1].getExpresion().equals(DELIMITADOR_INICIO))
							throw new Exception("operacion mal expresada3: "+unidades[i-1].getExpresion()+" "+unidades[i].getExpresion()+" posicion: "+i);		
					}
				}
				if ((i+1)==unidades.length)
					throw new Exception("operacion mal expresada4: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion()+" posicion: "+i);		
				else
				{
					if (unidades[i+1].getTipo()!=NUMERICO&&unidades[i+1].getTipo()!=VARIABLE&&unidades[i+1].getTipo()!=CONSTANTE&&unidades[i+1].getTipo()!=FUNCION)
					{
						if (unidades[i+1].getTipo()!=DELIMITADOR)
						{
							throw new Exception("operacion mal expresada5: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion()+" posicion: "+i);		
						} else
						{
							if (!unidades[i+1].getExpresion().equals(DELIMITADOR_INICIO))
								throw new Exception("operacion mal expresada6: "+unidades[i].getExpresion()+" "+unidades[i+1].getExpresion()+" posicion: "+i);		
						}
					}					
				}
			}
		}
		return new Resultado(true,"formato expresion consistente",SIN_ERROR);
	}
	
	private static UnidadSintactica[] getUnidadSintacticasLista(Lista lista) throws Exception
	{
		if (lista==null) return null;
		UnidadSintactica unidades[]=new UnidadSintactica[lista.getCantidadNodos()];
		Comparable aux[]=lista.getInfos();
		for (int i=0;i<aux.length;i++)
			unidades[i]=(UnidadSintactica)aux[i];
		return unidades;
	}
	
	private static String eliminarEspacios(String expresion) throws Exception
	{
		String aux="";
		for (int i=0;i<expresion.length();i++)
		{
			if (expresion.charAt(i)!=' ') aux+=""+expresion.charAt(i);
		}
		return aux;
	}
	
	private static int tipo(char car) throws Exception
	{
		String aux=""+car;
		int i;
		if (variables.pertenece(aux)) return VARIABLE;				
		if (car==FACTORIAL.charAt(0)) return FUNCION;
		if (Character.isLetter(car))
		{
			if (car==NUMERO_NEPERIANO||car==PI) return CONSTANTE;
			if (car==LOGARITMO.charAt(0)||car==POTENCIA.charAt(0)||car==RAIZ.charAt(0)) return OPERACION;
			return FUNCION;
		}
		if (operaciones.pertenece(aux)) return OPERACION;		
		if (numeros.pertenece(aux)) return NUMERICO;
		if (delimitadores.pertenece(aux)) return DELIMITADOR;		
		return OTRO;
	}
	
	private static UnidadSintactica[] rearmarExpresion(UnidadSintactica unidades[]) throws Exception
	{
		Lista lista=new Lista();
		int pendientes=0;
		lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));
		lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));
		lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==OPERACION)
			{
				lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));
				if (unidades[i].getExpresion().equals(SUMA)||unidades[i].getExpresion().equals(RESTA))
					lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));
				lista.insertar(unidades[i]);
				lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));
				if (unidades[i].getExpresion().equals(SUMA)||unidades[i].getExpresion().equals(RESTA))
					lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));
			} else
			if (unidades[i].getTipo()==FUNCION)
			{
				lista.insertar(unidades[i]);
				lista.insertar(new UnidadSintactica(DELIMITADOR_INICIO,DELIMITADOR));

			} else
			{
				lista.insertar(unidades[i]);
			}
		}
		lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));		
		lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));		
		lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));		
		unidades=getUnidadSintacticasLista(lista);
		GestionVector.invertirVector(unidades);
		lista=new Lista();
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==FUNCION)//||unidades[i].getTipo()==OPERACION)
			{
				lista.insertar(unidades[i]);
				pendientes++;
			}
			else
			if (unidades[i].getTipo()==DELIMITADOR&&unidades[i].getExpresion().equals(DELIMITADOR_FINAL))
			{
				lista.insertar(unidades[i]);
				for (int k=0;k<pendientes;k++)
				{
					lista.insertar(new UnidadSintactica(DELIMITADOR_FINAL,DELIMITADOR));
				}
				pendientes=0;
			} else
			{
				lista.insertar(unidades[i]);
			}			
		}
		unidades=getUnidadSintacticasLista(lista);
		GestionVector.invertirVector(unidades);		
		return unidades;
	}
	
	private static UnidadCalculo[] getUnidadCalculosPila(UnidadSintactica unidades[]) throws Exception
	{
		Pila pila=new Pila();
		Lista lista=new Lista();
		UnidadCalculo posicion=null;
		int codigo=0;
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==DELIMITADOR)
			{
				if (unidades[i].getExpresion().equals(DELIMITADOR_INICIO))
				{
					posicion=new UnidadCalculo(codigo++,i,-1);
					pila.insertar(posicion);
					lista.insertar(posicion);
				} 
				if (unidades[i].getExpresion().equals(DELIMITADOR_FINAL))
				{
					Comparable pos=pila.tomar();
					((UnidadCalculo)pos).setFin(i);
				}
			}
		}
		UnidadCalculo posiciones[]=UnidadCalculo.getUnidadCalculosLista(lista);
		GestionVector.invertirVector(posiciones);
		return posiciones;
		
	}
	
	private static void organizarArbolCalculo(UnidadCalculo posiciones[],UnidadSintactica unidades[]) throws Exception
	{
		for (int i=0;i<posiciones.length;i++)
		{
			for (int k=0;k<posiciones.length;k++)
			{
				if (k!=i&&posiciones[i].incluye(posiciones[k]))
				{
					if (i>posiciones[k].getIncluidoEn())
					{
						if (posiciones[k].getIncluidoEn()>=0)
						{
							posiciones[posiciones[k].getIncluidoEn()].eliminar(posiciones[k]);
						}
						posiciones[i].insertar(posiciones[k]);
						posiciones[k].setIncluidoEn(i);
					}
				}
			}
		}
	}
	
	private static void reemplazarVariables(UnidadSintactica unidades[],double valores[]) throws Exception
	{
		if (valores.length!=variables.getValores().length)
		{
			throw new Exception("Excepcion de paso de variables");		
		}
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==VARIABLE)
			{
				int j;
				for (j=0;j<variables.getValores().length&&!unidades[i].getExpresion().equals(variables.getValores()[j]);j++);
				if (j==variables.getValores().length)
				{
					throw new Exception("Excepcion de asignacion de valor");		
				}
				unidades[i].setExpresion(""+valores[j]);
				unidades[i].setTipo(NUMERICO);
			}
		}
	}
	
	private static void reemplazarConstantes(UnidadSintactica unidades[]) throws Exception
	{
		for (int i=0;i<unidades.length;i++)
		{
			if (unidades[i].getTipo()==CONSTANTE)
			{
				switch (unidades[i].getExpresion().charAt(0))
				{
					case NUMERO_NEPERIANO: unidades[i].setExpresion(""+Math.E);break;
					case PI: unidades[i].setExpresion(""+Math.PI);break;
					default: throw new Exception("Excepcion de reemplazo de constante");
				}
				unidades[i].setTipo(NUMERICO);
			}
		}		
	}
}