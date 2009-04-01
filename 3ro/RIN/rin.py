#!/usr/bin/env python
import random

u_1 = ["Concepto de Red de telecomunicacion, Ventajas y desventajas",
"Clasificacion de las redes",
"Componentes de una red LAN y WAN",
"Arquitectura de protocolos TCP/IP. Historia. Evolucion",
"Modelo de referencia TCP/IP: funcion de cada nivel",
"Conjunto de Protocolos TCP/IP",
"Ejemplos. Internet: Origenes. Caracteristicas. Servicios basicos",
"Organismos Internacionales de Normalizacion: ITU, ISO, Estandares IETF: RFC."
"Alternativas de conexion a Internet",
"Proveedores de servicios de Internet (ISP)"]

u_2 = [ "Red Telefonica: estructura jerarquica, plan de numeracion", 
"ISD: caracteristicas principales.",
"Frame Relay: caracteristicas, terminologia (DLCI, CIR, PVC, SVC, FECN, BECN), control de congestion en frame relay.",
"ATM: caracteristicas, capas ATM, canales virtuales, celda ATM, clases de servicios.",
"Analisis comparativo entre la conmutacion de paquetes y de celdas."]

u_3 = [ "Protocolo IPv4. Formato del datagrama",
"Direccionamiento IPv4. Clases de direcciones. Mascara de red.",
"Agotamiento de las direcciones IPv4. VLSM. CIDR",
"Protocolo IPv6: caracteristicas, ventajas con respecto a IPv4, formato del datagrama de IPv6.",
"ICMP (Protocolo de Control de Mensajes en Internet): funcionamiento,  tipos de mensajes.",
"ARP (Protocolo de resolucion de direcciones): funcionamiento, tablas ARP.",
"Direccionamiento estatico. Direccionamiento dinamico.",
"BOOTP: caracteristicas.",
"DHCP (Protocolo de Configuracion Dinamica de Host): caracteristicas, funcionamiento, agente relay de DHCP.",
"NAT (Traduccion de Direcciones de Red): caracteristicas, NAT estatica, NAT dinamica, PAT (Traduccion de Direcciones de Puerto)."]

u_4 = [ "Encaminamiento. Concepto. Algoritmos de encaminamiento. La ruta mas corta. Inundacion. ",
"Vector de distancia: caracteristicas, problema de conteo al infinito, horizonte dividido, actualizaciones por eventos. ",
"Estado de enlace: caracteristicas.",
"Analisis comparativo entre protocolos de vector de distancia y de estado de enlace.",
"Algoritmo Jerarquico. Difusion. Multi-transmision.",
"Encaminamiento en la Internet. Sistemas autonomos. Protocolos de gateway interior.",
"RIP: caracteristicas y funcionamiento.",
"OSPF: funcionamiento. Protocolo de gateway fronterizo. ",
"BGP: caracteristicas, funcionamiento. ",
"Routers: componentes, puertos, principio de funcionamiento, configuracion basica.",
"Congestion. Algoritmos de control de congestion. Diferencia entre control de congestion y control de flujo.",
"Principios generales del control de congestion. Politicas de prevencion de congestion.",
"Calidad de Servicio. Requerimientos. Tecnicas para alcanzar buena calidad de servicio",
"Herramientas de Administracion de Red. Comandos: arp, ping, tracert, ifconfig (linux), ipconfig, route print, netstat, nslookup, telnet"]

u_5 = [  "Capa de Transporte: Servicios de la capa de transporte: orientado a conexion y sin conexion.",
"Protocolo TCP: caracteristicas, funcionamiento, formato del segmento. Establecimiento y liberacion de una conexion.",
"Protocolo UDP: caracteristicas, formato del encabezado. Puertos. ",
"Aplicaciones de TCP y UDP. Socket: concepto, primitivas.",
"DNS (Sistema de Nombres de dominio). Funcionamiento. Espacio de nombres de DNS. Registros de recursos. Servidores de nombres. Configuracion de servidores DNS.",
"FTP (Protocolo de Transferencia de Archivos): Caracteristicas, comandos FTP, aplicaciones. TFTP",
"(Protocolo de Transferencia de Archivos Trivial): caracteristicas, configuracion, aplicaciones.",
"SNMP (Protocolo Simple de Administracion de redes).",
"Modelo SNMP. Funcionamiento. MIB. Protocolo SNMP. Aplicaciones.",
"Correo Electronico: Arquitectura y servicios, agente de usuario, formatos de mensaje, transferencia de mensajes.",
"Protocolo SMTP (Protocolo simple de transferencia de correo),",
"Protocolo MIME (Extensiones Multiproposito de Correo de Internet).",
"Confidencialidad en el correo electronico. Configuracion de servidores de correo.",
"WWW (World Wide Web): arquitectura, funcionamiento del cliente, funcionamiento del servidor.",
"URL (Localizador Uniforme de Recursos). Localizacion de informacion en la Web.",
"Documentos Web estaticos y dinamicos.",
"http (Protocolo de Transferencia de Hipertexto).",
"Servidor Proxy.",
"Configuracion de servidores Web.",
"Voz sobre IP: estandar H323, estandares de codificacion de voz,",
"protocolo RTTP (Protocolo de transferencia en tiempo real)",
"SIP (Protocolo de inicio de sesion)."]

u_6 = [ "Concepto de seguridad. Confidencialidad. Autenticacion. Integridad. Disponibilidad.",
"Firmas digitales. Concepto. Implementacion. Firmas de clave simetrica. Firmas de clave publica.",
"Firewalls. Caracteristicas. Tipos. Servidor Proxy. Filtrado de paquetes. Aplicaciones.",
"Seguridad en la capa de red: IPSec. Seguridad en la Web: Protocolo de seguridad SSL y TLS.",
"VPN (Redes Privadas Virtuales). Caracteristicas. Aplicaciones. Implementacion.",
"Seguridad en comunicaciones inalambricas."]


print("")
i=0;
while(True):
    i+=1
    preguntas=[]
    selected=random.randint(0,len(u_1)-1)
    preguntas.append(u_1[selected])
    
    selected=random.randint(0,len(u_2)-1)
    preguntas.append(u_2[selected])
    
    selected=random.randint(0,len(u_3)-1)
    preguntas.append(u_3[selected])
    
    selected=random.randint(0,len(u_4)-1)
    preguntas.append(u_4[selected])
    
    selected=random.randint(0,len(u_5)-1)
    preguntas.append(u_5[selected])
    
    selected=random.randint(0,len(u_6)-1)
    preguntas.append(u_6[selected])
    
    random.shuffle(preguntas)
    
    print ">>>>>>>> " + str(i)
    for preg in preguntas:
        print("# - "+preg)
    
    print("\n\"q\" for quit!")
    option=raw_input()
    if option is "q" or option is "Q":
	break
