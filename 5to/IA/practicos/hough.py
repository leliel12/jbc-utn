#!/usr/bin/env python


__author__ = 'Juan B Cabral 40.42 - 5k4'
__licence__ = 'GPL 3'


from math import atan2


def _increment_cord(cord_dict, cord):
	'''_increment_cord({}, () -> none
	Agrega al diccionario cord_dict una entrada con valor de llave igual al
	string que representa a cord y con un valor 1. si la llave ya existia
	anteriormente incrementa su valor en 1.'''
	k = str(cord)
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
	v1 = int(v1)
	v2 = int(v2)
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
			

def polar_hough(points):
	'''rect_hub([()]) -> (())
	Dada una serie de puntos utiliza "La Transformada de Hough" para encontrar
	los valores (r,t) que representan a las constantes de la recta en su
	forma polar(r = x * cos(t) + y * sen(t)).'''
	cord_dict = {}
	for x,y in points:
		r = x # r = x * sen(0) + y * cos(0) -> r = x * 1 + 0 -> r = x
		t =  int(atan2(-x,-y))
		cord = (r,t)
		_increment_cord(cord_dict, cord)
	result = _get_most_used_cords(cord_dict)
	return tuple(result)


################################################################################
# main
################################################################################

if __name__ == '__main__':
	points = [(0, 0), (1, 1), (2, 2), (3, 0)]
	print polar_hough(points)
