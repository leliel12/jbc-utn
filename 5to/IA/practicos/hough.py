#!/usr/bin/env python


__author__ = 'Juan B Cabral 40.42 - 5k4'
__licence__ = 'GPL 3'

import math
	
	
def _increment_cord(cord_dict, rt_cord):
	'''_increment_cord({}, () -> none
	Incrementa en '1' en el diccionario el valor correspondiente la llave 
	a la llave que se obtiene al transformar en string a rt_cord.'''
	k = str(rt_cord)
	if not cord_dict.has_key(k):
		cord_dict[k] = 0
	cord_dict[k] +=1

	
def _key_to_cord(key):
	'''_key_to_cord(str) -> ()
	Convierte el valor de una llave en una tupla con dos componente enteros que
	representa una coordenada.'''
	v1,v2 = key.split(',')
	v1 = v1.replace('(', '')
	v2 = v2.replace(')', '')
	v1 = float(v1)
	v2 = float(v2)
	return (v1,v2)


def _get_most_used_cords(cord_dict):
	'''_get_most_used_cords({}) -> [()]
	Extrae del diccionario el valor de las llaves que mayor valor posea.
	Retorna una lista  con estos valores en forma de tuplas de dos componentes
	enteros que representan una coordenada.'''
	most_used = []
	max = 0
	for k,v in cord_dict.items():
		if v > max:
			max = v
			cord = _key_to_cord(k)
			most_used = [cord]
		elif v == max:
			cord = _key_to_cord(k)
			most_used.append(cord)
	return most_used
			

def do_hough(points, theta_increment, round_factor):
	'''rect_hub([()]) -> (())
	Dada una serie de puntos utiliza "La Transformada de Hough" para encontrar
	los valores (r,t) que representan a las constantes de la recta en su
	forma polar(r = x * cos(t) + y * sen(t)).'''
	cord_dict = {}
	for cord in points:
		x,y = cord # Separo la cordenada en x e i
		theta = 0 #pongo el primer valor del angulo = 0
		while theta < 180:
			rho = round(x * math.cos(theta) + y * math.sin(theta), round_factor)
			rt_cord = (rho, theta)
			_increment_cord(cord_dict, rt_cord)
			theta += round(theta + theta_increment, round_factor)
	solution = _get_most_used_cords(cord_dict)
	return tuple(solution)
			
			
		


################################################################################
# main
################################################################################

if __name__ == '__main__':
	points = [(0, 0), (1, 1), (2, 2), (3, 0)]
	print do_hough(points,0.5,0)
