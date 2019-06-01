import networkx as nx

graph = nx.Graph()

FILE_NAME = "guategrafo.txt"

# ********************************** METODOS **************************

# imprime menu para usuario
def imprimir_menu():
    print("Menu")
    print("1. Agregar ciudad")
    print("2. Agregar coneccion entre ciudades")
    print("3. Eliminar coneccion entre ciudades")
    print("4. Algoritmo de Floyd")
    print("5. Centro del grafo")
    print("6. Mostrar matriz de adyacencia")
    print("7. Salir del programa")
    print("Ingrese opcion: ")

# lee el archivo guategrafo.txt y agrega nodos y aristas al grafo
def iniciar_grafo():
    file = open(FILE_NAME, "r")
    for line in file:
        parts = line.split(":")
        # si ya existe una ciudad, no se agrega de nuevo
        graph.add_node(parts[0])
        graph.add_node(parts[1])
        graph.add_edge(parts[0], parts[1], distancia=float(parts[2]))

# **************************** PROGRAMA PRINCIPAL *********************
opcion = ""
iniciar_grafo()
predMap, distMap = nx.floyd_warshall_predecessor_and_distance(graph, weight='distancia')

while (opcion != "7"):
    imprimir_menu()
    opcion = input()
    if(opcion == "1"):
      print("Ingresar nombre de ciudad:")
      ciudad = input()
      graph.add_node(ciudad)
      print("Ciudad agregada al grafo...")
    elif(opcion == "2"):
        print("Ingrese ciudad origen: ")
        ciudad1 = input()
        print("Ingrese ciudad destino: ")
        ciudad2 = input()
        print("Ingrese distancia: ")
        dist = float(input())
        graph.add_edge(ciudad1, ciudad2, distancia=dist)
        # ejecutar el algoritmo de floyd de nuevo
        predMap, distMap = nx.floyd_warshall_predecessor_and_distance(graph, weight='distancia')
        print("Coneccion agregada al grafo...")
    elif(opcion == "3"):
        print("Ingrese ciudad origen: ")
        ciudad1 = input()
        print("Ingrese ciudad destino: ")
        ciudad2 = input()
        graph.remove_edge(ciudad1, ciudad2)
        # ejecutar el algoritmo de floyd de nuevo
        predMap, distMap = nx.floyd_warshall_predecessor_and_distance(graph, weight='distancia')
        print("Coneccion eliminada del grafo...")
    elif(opcion == "4"):
        print("Ingrese ciudad origen: ")
        ciudad1 = input()
        print("Ingrese ciudad destino: ")
        ciudad2 = input()
        print("Distancia: ", distMap[ciudad1][ciudad2])
        camino = []
        camino.append(ciudad2)
        while(ciudad2 != ciudad1):
            ciudad2 = predMap[ciudad1][ciudad2]
            camino.append(ciudad2)
        print(list(reversed(camino)))
    elif(opcion == "5"):
        # mostrar centros del grafo
        print("Las mejores ciudades para colocar sucursales son:")
        centros = nx.center(graph)
        print(centros)
    elif(opcion == "6"):
        # imprimir matriz de adyacencia
        print(nx.adjacency_matrix(graph).todense())
    elif(opcion == "7"):
        print("Saliendo del programa...")
        break

