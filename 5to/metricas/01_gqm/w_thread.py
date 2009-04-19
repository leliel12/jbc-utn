#!/usr/bin/env python

from threading import Thread



#Super Clase de Estrategia
class Estrategia():
    def foo(self):
        raise Exception("Metodo sin implementar")


        
#Estrategia Implementacion 1       
class Estrategia1(Estrategia):
    def foo(self, thread_id):
        print("Estrategia 1: Thread %i" % thread_id)


 
#Estrategia Implementacion 2
class Estrategia2(Estrategia):
    
    def foo(self, thread_id):
        print("Estrategia 2: Thread %i" % thread_id)
    
    
        
# Usuario de Estrategia
class StrategyUser():
    def __init__(self, stg):
        self.stg = stg
        
    def do_strategy(self, thread_id):
        self.stg.foo(thread_id)



# Hilo
class WorkingThread(Thread):
    def __init__(self, stg_user, thread_id):
        Thread.__init__(self) # Constructor de SuperClass
        self.stg_user = stg_user #Asignacion a variable de instancia.
        self.thread_id = thread_id
        
    def run(self):
        self.stg_user.do_strategy(self.thread_id) #Corre la Estrategia


if __name__ == '__main__':
    print("Init Test...\n")
    
    #200 Hilos
    for i in range(1000):
        #Creamos las estrategias
        stg_1 = Estrategia1()
        stg_2 = Estrategia2()
    
        #Creamos los usuarios de estrategia
        stg_user_1 = StrategyUser(stg_1)
        stg_user_2 = StrategyUser(stg_2)
    
        #Creamos los thread
        wt_1 = WorkingThread(stg_user_1, i)
        wt_2 = WorkingThread(stg_user_2, i)
        
        # Lanzamos los hilos
        wt_1.start()
        wt_2.start()
    
    print("\nEnd Test.")
    

