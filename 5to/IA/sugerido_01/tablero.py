def _get_number_of_moves(tab):
    rows = len(tab)
    cols = None
    for row in tab:
        if cols is None:
            cols = len(row)
        if cols != len(row):
            raise ValueError("la cantidad de columnas de la matriz " + 
            "debe ser igual en cada fila")            
    return (rows,cols)
        

def _move_ij(tab, move, empty):
    tab = [list(r) for r in tab]
    if move != empty:
        i,j = move
        nonei, nonej = empty
        if i+1 == nonei or i-1 == nonei or j+1 == nonej or j-1 == nonej:
            aux = tab[i][j]
            tab[i][j] = None
            tab[nonei][nonej] = aux
    return tab
        

def _get_empty_cord(tab, empty):
    emptys=[]
    for idx_i in range(len(tab)):
        for idx_j in range(len(tab[idx_i])):
            if tab[idx_i][idx_j] == empty:
                cord = (idx_i, idx_j)
                emptys.append(cord)
    return emptys

def _get_all_child(tab, empty = None):
    tab = [list(l) for l in tab]
    movesi, movesj = _get_number_of_moves(tab)
    childs = []
    emptys = _get_empty_cord(tab, empty)
    for empty in emptys:
        for idxi in range(movesi):
            for idxj in range(movesj):
                move = (idxi, idxj)
                new_tab = _move_ij(tab, move, empty)
                childs.append(new_tab)
    return childs


if __name__ == '__main__':
    def print_matrix(matrix, tabs=0):
        ttp = [ "\t" for c in range(tabs)]
        ttp = "".join(ttp)
        for row in matrix:
            print(ttp + str(row))
    
    tab = [
            [1,2,3],
            [8, None, 4],
            [7,6,5]
          ]
          
    tabs = _get_all_child(tab, None)
    for t in tabs:
        print_matrix(t, 2)
        print ""
