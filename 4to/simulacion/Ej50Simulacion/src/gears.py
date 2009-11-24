###############################################################################
# DOCS
###############################################################################

"""La logica del ejercicio"""

###############################################################################
# IMPORTS
###############################################################################

import random
import models


###############################################################################
# CONSTANTS
###############################################################################

LLEGADA = "Nueva LLegada"
ATERRISAJE = "Nuevo Aterrisaje"
DESPEGUE = "Nuevo Despegue"
SALIDA_PISTA = "Nueva Salida a Pista"
DESVIO = "Desvio"


###############################################################################
# CLASE EVENTO
###############################################################################

class Evento(object):
    
    def __init__(self, iteration, names, time,
                 t_llegada, cant_vuelo,
                 cant_pista, cant_estacion):
        self.iteration = iteration
        self.names = names
        self.time = time
        self.t_llegada = t_llegada
        self.cant_vuelo = cant_vuelo
        self.cant_pista = cant_pista
        self.cant_estacion = cant_estacion
      
    def __str__(self):
        msg = ""
        msg += "i: " + str(self.iteration) + " - "
        msg += "evts: " + str(self.names) + " - "
        msg += "time: " + str(self.time) + " - "
        msg += "sig_lleg: " + str(self.t_llegada) + " - "
        msg += "en_vuelo: " + str(self.cant_vuelo) + " - "
        msg += "en_pista: " + str(self.cant_pista) + " - "
        msg += "estacionado: " + str(self.cant_estacion)
        return msg
      
###############################################################################
# MOTOR DE SIMULACION
###############################################################################  

class Gears(object):    
    
    def __init__(self):
        self._siguiente_llegada = 0.0
        self._en_vuelo = models.Vuelo()
        self._pista = models.Pista()
        self._estacion = models.Estacion()
        self._eventos = []
    
    def iter_events(self):
        return iter(self._eventos)
    
    def _next_time(self, actual_time):
        times = []
        if self._siguiente_llegada > actual_time:
            times.append(self._siguiente_llegada)
        if self._en_vuelo.next_time_event() > actual_time:
            times.append(self._en_vuelo.next_time_event())
        if  self._pista.next_time_event() > actual_time:
            times.append(self._pista.next_time_event())
        if  self._estacion.next_time_event() > actual_time:
            times.append(self._estacion.next_time_event())
        return min(times)
        
    
    def _tratar_llegada(self, actual_time):
        if self._siguiente_llegada <= actual_time:
            self._en_vuelo.add_avion(actual_time)
            self._siguiente_llegada = actual_time + random.expovariate(1 / 8.0)
            return True
    
    def _tratar_desvio(self, actual_time):
        if self._estacion.actual_aviones > 35:
            self._en_vuelo.last_avion()
            return True
        
    def _tratar_despegue(self, actual_time):
        if self._pista.next_time_event() is not None \
            and self._pista.next_time_event() <= actual_time:
                self._pista.first_avion(actual_time)
                return True
    
    def _tratar_aterrisaje(self, actual_time):
        if not self._pista.actual_aviones \
            and self._en_vuelo.next_time_event() is not None\
            and self._en_vuelo.next_time_event() <= actual_time:
                self._en_vuelo.first_avion(actual_time)
                self._estacion.add_avion(actual_time)
                return True
    
    def _tratar_salida(self, actual_time):
        if not self._pista.actual_aviones \
            and self._estacion.next_time_event() is not None\
            and self._estacion.next_time_event() <= actual_time:
                self._estacion.first_avion(actual_time)
                self._pista.add_avion(actual_time)
                return True
            
    def _generar_estadisticas(self, iteracion, events, actual_time):
        actual_time = dhm(actual_time)
        t_llegada = dhm(self._siguiente_llegada)
        cant_vuelo = self._en_vuelo.actual_aviones
        cant_pista = self._pista.actual_aviones
        cant_estacion = self._estacion.actual_aviones
        e = Evento(iteracion, events, actual_time,
                   t_llegada, cant_vuelo,
                   cant_pista, cant_estacion)
        self._eventos.append(e)
    
    def run(self, iterations):
        actual_time = 0.0
        self._siguiente_llegada = 2.0
        self._en_vuelo.reset()
        self._pista.reset()
        self._estacion.reset()
        self._eventos = []
        i = 0
        while i < iterations:
            eventos = []
            if self._tratar_llegada(actual_time):
                eventos.append(LLEGADA)
            if self._tratar_desvio(actual_time):
                eventos.append(DESVIO)
            if self._tratar_despegue(actual_time):
                eventos.append(DESPEGUE)
            if self._tratar_aterrisaje(actual_time):
                eventos.append(ATERRISAJE)
            if self._tratar_salida(actual_time):
                eventos.append(SALIDA_PISTA)
            if eventos:
                self._generar_estadisticas(i, eventos, actual_time)
                i += 1
            actual_time = self._next_time(actual_time)
        

###############################################################################
# FUNCTIONS
############################################################################### 

def dhm(minutes):
    h, m = divmod(minutes, 60)
    d, h = divmod(h, 24)
    return "Dia: %d %d:%02d" % (d, h, m)
    

###############################################################################
# MAIN
###############################################################################      
        
if __name__ == "__main__":
    print __doc__
