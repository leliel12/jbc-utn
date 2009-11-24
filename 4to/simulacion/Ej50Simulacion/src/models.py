###############################################################################
# IMPORTS
###############################################################################

import random


###############################################################################
# CLASE LUGAR
###############################################################################

class AbstractLugar(object):
    """Comportamiento Comun de un Lugar"""
    def __init__(self, nombre_lugar, max_aviones):
        self._nombre = nombre_lugar
        self._max = max_aviones
        self._aviones = []
        self._total_aviones = 0
        self._sin_espera = 0
        self._total_time_espera = 0.0
        self.auto_charge()
        
    def tiempo_espera(self):
        raise NotImplementedError()
    
    def auto_charge(self):
        raise NotImplementedError()
    
    def reset(self):
        self._aviones = []
        self._total_aviones = 0
        self._sin_espera = 0
        self._total_time_espera = 0.0
        self.auto_charge()
    
    def add_avion(self, entrada, salida=None):
        if self._max is not None and len(self._aviones) == self._max:
            msg = "%s soporta %i aviones" % (self._nombre , self._max)
            raise IndexError(msg)
        self._total_aviones += 1
        if not salida:
            salida = float(entrada + self.tiempo_espera())
        self._aviones.append((salida, entrada))
        self._aviones.sort()
        
    def first_avion(self, actual_time):
        avion = self._aviones.pop(0)
        espera = actual_time - avion[0]
        if not espera:
            self._sin_espera += 1
        self._total_time_espera += espera
        
    def last_avion(self):
        return self._aviones.pop()
        
    def next_time_event(self):
        if len(self._aviones):
            return self._aviones[0][0]
    
    @property
    def actual_aviones(self):
        return len(self._aviones)
    
    @property
    def total_aviones(self):
        return self._total_aviones
    
    @property
    def perc_sin_espera(self):
        return (self._sin_espera * 100.0) / self._total_aviones
        
    @property
    def promedio_espera(self):
        return self._total_time_espera / self._total_aviones
        
        
###############################################################################
# VUELO
###############################################################################

class Vuelo(AbstractLugar):
    """Representa las aeronaves esperando en vuelo.
    
    Siguiendo el enunciado:
        - Tardan en recibir una autorizacion para aterizar
          con (a, b) = (5 ,7)
    """
    def __init__(self):
        super(Vuelo, self).__init__("Vuelo", None)
        self.a = 5
        self.b = 7
    
    def auto_charge(self):
        pass
    
    def tiempo_espera(self):
        return random.uniform(self.a, self.b)


###############################################################################
# PISTA
###############################################################################

class Pista(AbstractLugar):
    """Representa la pista.
    
    Siguiendo el enunciado:
        - Soporta solo 1 avion por vez
        - Y el tiempo que tardan en salir de la pista es uniforme
          con (a, b) = (4 ,7)
    """
    def __init__(self):
        super(Pista, self).__init__("Pista", 1)
        self.a = 4
        self.b = 7
        
    def auto_charge(self):
        pass
    
    def tiempo_espera(self):
        return random.uniform(self.a, self.b)
    
    
###############################################################################
# ESTACIONADA
###############################################################################

class Estacion(AbstractLugar):
    """Representa estacionamiento de los aviones
    
    Siguiendo el enunciado:
        - Y el tiempo que tardan en salir de la estacion un tiempo normal con
        (mu, sigma) = (90, 20)
    """
    def __init__(self):
        super(Estacion, self).__init__("Estacion", None)
        self.mu = 90
        self.sigma = 20
    
    def auto_charge(self):
        self.add_avion(0.0, 15.0)
        self.add_avion(0.0, 17.0)
        self.add_avion(0.0, 20.0)
        self.add_avion(0.0, 22.0)
        self.add_avion(0.0, 24.0)
        self.add_avion(0.0, 38.0)
        
    
    def tiempo_espera(self):
        return random.normalvariate(self.mu, self.sigma)
    
    
###############################################################################
# MAIN
###############################################################################

if __name__ == '__main__':
    pass
