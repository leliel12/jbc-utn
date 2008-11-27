

import java.util.*;
import java.text.*;

/**
 * Una gestion de date (fecha y hora)
 * @version Diciembre de 2005
 */
public class GestionDate
{
	/**
	* Obtencion de un string con los datos horarios de un date pasado por parametro
	* Efectua el procesamiento con Locate.US
    * Ejemplo: 09:43:01
	* @param date Date del cual se quieren conocer sus datos horarios
	* @return String con los datos horarios de date
	*/
	public static String getStrHora(Date date)
	{
		if (date==null) return null;
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
			return calendario.get(Calendar.HOUR)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND);
		}	
		catch (Exception eq) {}		
		return "";
	}

	/**
	* Obtencion de un string con los datos de calendario de un date pasado por parametro
	* Utiliza como separacion de elementos la preposicion "de"
	* Efectua el procesamiento con Locate.US
    * Ejemplo: Wed 7 de Dec de 2005
	* @param date Date del cual se quieren conocer sus datos de calendario
	* @return String con los datos de calendario de date
	*/	
	public static String getStrDiaTexto(Date date)
	{
		if (date==null) return null;
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);			
			return getDia(date)+" "+calendario.get(Calendar.DATE)+" de "+getMes(date)+" de "+calendario.get(Calendar.YEAR);
		}	
		catch (Exception eq){}			
		return "";
	}

	/**
	* Obtencion de un string con los datos de calendario de un date pasado por parametro
	* Utiliza como separacion de elementos el caracter ""
	* Efectua el procesamiento con Locate.US
	* Ejemplo: Wed 7 Dec 2005
	* @param date Date del cual se quieren conocer sus datos de calendario
	* @return String con los datos de calendario de date
	*/		
	public static String getStrDia(Date date)
	{
		if (date==null) return null;
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);						
			return getDia(date)+" "+calendario.get(Calendar.DATE)+" "+getMes(date)+" "+calendario.get(Calendar.YEAR);
		}	
		catch (Exception eq){}			
		return "";
	}	
	
    /**
    * Procesamiento de los string pasados por parámetros que representan string de fecha y hora para obtener el date correspondiente
    * Retorna null si se ha producido una excepcion
    * Ejemplo: strDia-> Wed 7 Dec 2005
    * Ejemplo: strHora-> 09:43:01
    * @param strDia string fecha a procesar
    * @param strHora string hora a procesar
    * @return Date resultante del procesamiento, null si se ha producido una excepcion
    */	
	public static Date desprocesarStr(String strDia,String strHora)
	{
		try
		{
			return desprocesar(strDia,strHora);
		}
		catch (Exception qq)
		{
			return null;
		}
	}
	
    /**
    * Procesamiento de los string pasados por parámetros que representan string de fecha y hora para obtener el date correspondiente
    * Ejemplo: strDia-> Wed 7 Dec 2005
    * Ejemplo: strHora-> 09:43:01
    * @param strDia string fecha a procesar
    * @param strHora string hora a procesar
    * @return Date resultante del procesamiento
    */		
	private static Date desprocesar(String strDia,String strHora) throws Exception
	{
		String dia,numero,mes,anio,horas,minutos,segundos;
		dia=numero=mes=anio=horas=minutos=segundos="";
		int p=0;
		String aux="";
		for (int i=0;i<strDia.length();i++)
		{
			if (strDia.charAt(i)!=' ') aux+=""+strDia.charAt(i);
			else
			{
				switch(p)
				{
					case 0:dia=aux;break;
					case 1:numero=aux;break;
					case 2:mes=aux;break;
				}
				p++;aux="";
			}
		}
		anio=aux;
		aux="";
		p=0;
		for (int i=0;i<strHora.length();i++)
		{
			if (strHora.charAt(i)!=':') aux+=""+strHora.charAt(i);
			else
			{
				switch(p)
				{
					case 0:horas=aux;break;
					case 1:minutos=aux;break;
				}
				p++;aux="";
			}			
		}
		segundos=aux;
		GregorianCalendar calendario=new GregorianCalendar(Integer.parseInt(anio)-1900,getMes(mes),Integer.parseInt(numero),Integer.parseInt(horas),Integer.parseInt(minutos),Integer.parseInt(segundos));
		return calendario.getTime();
	}
	
    /**
    * Obtencion del numero de mes asociado a un string de mes
    * @param mes String que representa el mes del cual se quiere obtener su correspondencia numerica
    * @return int numero de mes asociado a un string de mes
    */		
	private static int getMes(String mes)
	{
		if (mes.equals("Jan")) return 0;
		if (mes.equals("Feb")) return 1;
		if (mes.equals("Mar")) return 2;
		if (mes.equals("Apr")) return 3;
		if (mes.equals("May")) return 4;
		if (mes.equals("Jun")) return 5;
		if (mes.equals("Jul")) return 6;
		if (mes.equals("Aug")) return 7;
		if (mes.equals("Sep")) return 8;
		if (mes.equals("Oct")) return 9;
		if (mes.equals("Nov")) return 10;
		if (mes.equals("Dec")) return 11;
		return -1;
	}
	
	/**
	 * Indica si un anio pasado por parametro es bisiesto
	 * @param anio int que es el anio del cual se quiere saber si es bisiesto
	 * @return boolean que indica la condicion de bisiesto
	 */
	public static boolean esAnioBisiesto(int anio)
	{
		if (anio%4!=0) return false;
		return !(anio%1000==0);
	}
	
	/**
	 * Indica si el anio de un date pasado por parametro es bisiesto
	 * @param date Date que es el date del cuyo anio se quiere saber si es bisiesto
	 * @return boolean que indica la condicion de bisiesto
	 */	
	public static boolean esAnioBisiesto(Date date)
	{
		return esAnioBisiesto(getAnio(date));
	}
	
	/**
	 * Indica la cantidad de dias de un mes de un anio pasados por parametro
	 * @param mes int que es el mes cuya cantidad de dias quiere ser conocida
	 * @param anio int que es el anio al que pertenece el mes
	 * @return cantidad de dias de un mes de un anio
	 */
	public static int getCantidadDiasMes(int mes,int anio)
	{
		if (mes<0||mes>11) return -1;
		if (mes==0||mes==2||mes==4||mes==6||mes==7||mes==9||mes==11) return 31;
		if (mes!=1) return 30;
		if (esAnioBisiesto(anio)) return 29;
		return 28;
	}
	
	/**
	 * Indica la cantidad de dias de un mes (expresado como String) de un anio pasados por parametro
	 * @param mes String que es el mes cuya cantidad de dias quiere ser conocida
	 * @param anio int que es el anio al que pertenece el mes
	 * @return int cantidad de dias de un mes de un anio
	 */
	public static int getCantidadDiasMes(String mes,int anio)
	{
		return getCantidadDiasMes(getMes(mes),anio);
	}
	
	/**
	 * Indica la cantidad de dias en el mes correspondiente a un date pasado por parametro
	 * @param date Date de cuyo mes actual se quiere conocer la cantidad de dias
	 * @return int cantidad de dias del mes actual de date
	 */
	public static int getCantidadDiasMes(Date date)
	{
		return getCantidadDiasMes(getMes(date),getAnio(date));
	}
	
	/**
	 * Retorna un date con los milis pasados por parametro
	 * @param milis milis que se convertiran en un date
	 * @return date con los milis pasados por parametro
	 */
	public static Date getDate(long milis)
	{
		try
		{
			Date date=new Date();
			date.setTime(milis);
			return date;
		} catch (Exception qq)
		{
			return null;
		}
	}
	
	/**
	 * Acceso al mes de un date
	 * @param date date a procesar
	 * @return mes de un date
	 */	
	public static String getMes(Date date)
	{
		if (date==null) return "";
		SimpleDateFormat formato=new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy",Locale.US);
                                          //Locale.getDefault());
		try
		{
			formato.applyPattern("MMM");
			return formato.format(date);
		}	
		catch (Exception eq) 
		{return "";}
	}		
	
	/**
	 * Acceso al anio de un date
	 * @param date date a procesar
	 * @return anio de un date
	 */
	public static int getAnio(Date date)
	{
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
        	return calendario.get(Calendar.YEAR);
		}	
		catch (Exception eq) 
		{return -1;}
	}	
	
	/**
	 * Acceso al numero de un date
	 * @param date date a procesar
	 * @return numero de un date
	 */
	public static int getNumero(Date date)
	{
		try
		{
        	GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
        	return calendario.get(Calendar.DATE);
		}	
		catch (Exception eq) 
		{return -1;}
	}	
	
	/**
	 * Acceso al dia de un date
	 * @param date date a procesar
	 * @return dia de un date
	 */
	public static String getDia(Date date)
	{
		if (date==null) return "";
		SimpleDateFormat formato=new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy",Locale.US);
                                          //Locale.getDefault());
		try
		{
			formato.applyPattern("EEE");
			return formato.format(date);
		}	
		catch (Exception eq) 
		{return "";}
	}		
	
	/**
	 * Acceso a las horas de un date
	 * @param date date a procesar
	 * @return horas de un date
	 */
	public static int getHoras(Date date)
	{
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
        	return calendario.get(Calendar.HOUR);
		}	
		catch (Exception eq) 
		{return -1;}
	}		

	/**
	 * Acceso a los minutos de un date
	 * @param date date a procesar
	 * @return minutos de un date
	 */	
	public static int getMinutos(Date date)
	{
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
        	return calendario.get(Calendar.MINUTE);
		}	
		catch (Exception eq) 
		{return -1;}
	}		
	
	/**
	 * Acceso a los segundos de un date
	 * @param date date a procesar
	 * @return segundos de un date
	 */
	public static int getSegundos(Date date)
	{
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
        	return calendario.get(Calendar.SECOND);
		}	
		catch (Exception eq) 
		{return -1;}
	}									

	/**
	 * Acceso al date mas antiguo de los dates de un vector
	 * @param date vector de Date
	 * @return date mas antiguo de los dates de un vector
	 */	
	public static Date getAnterior(Date date[])
	{
		if (date==null||date.length==0) return null;
		Date dateAnterior=date[0];
		for (int i=1;i<date.length;i++)
			if (date[i].before(dateAnterior)) dateAnterior=date[i];
		return dateAnterior;
	}
	
	/**
	 * Acceso al date mas reciente de los dates de un vector
	 * @param date vector de Date
	 * @return date mas reciente de los dates de un vector
	 */
	public static Date getPosterior(Date date[])
	{
		if (date==null||date.length==0) return null;
		Date dateAnterior=date[0];
		for (int i=1;i<date.length;i++)
			if (date[i].after(dateAnterior)) dateAnterior=date[i];
		return dateAnterior;
	}	
	
	/**
	 * Retorna un date en base a un date de SQL
	 * @param date date a ser convertido
	 * @return date en base a un date de SQL
	 */
	public static Date getDate(java.sql.Date date)
	{
		if (date==null) return null;
		return new Date(date.getTime());
	}
	
	/**
	 * Accer a un date a partir de un dia, mes y anio
	 * @param dia dia del date
	 * @paramme smes del date
	 * @param anio anio del date
	 * @return Date date creado apartir de los parametros
	 */
	public static Date getDate(int dia,int mes,int anio)
	{
		GregorianCalendar c=new GregorianCalendar();
		c.set(Calendar.YEAR,anio);
		c.set(Calendar.MONTH,mes);
		c.set(Calendar.DATE,dia);
		return c.getTime();
	}
	
	/**
	 * Crea un date a partir de una cadena con los millis
	 * @param date string a partir del cual se crea el date
	 * @return date a partir de una cadena con los millis
	 */
	public static Date getDate(String date)
	{
		if (date==null) return null;
		try
		{
			return new Date(Long.parseLong(date));
		} catch(Exception qq)
		{
			return null;
		}
	}
	
	/**
	* Obtencion de un string con los datos horarios de un date pasado por parametro
	* Efectua el procesamiento con Locate.US
    * Ejemplo: 09:43:01
	* @param date Date del cual se quieren conocer sus datos horarios
	* @return String con los datos horarios de date
	*/
	public static String getStrHoraTruncada(Date date)
	{
		if (date==null) return null;
		try
		{
			GregorianCalendar calendario=new GregorianCalendar();                                  
        	calendario.setTime(date);
			return calendario.get(Calendar.HOUR)+":"+"00"+":"+"00";
		}	
		catch (Exception eq) {}		
		return "";
	}	
}