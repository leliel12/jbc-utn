#!/usr/bin/env python

__author__ = 'Juan B Cabral 40.42 - 5k4'
__licence__ = 'GPL'

def _get_matrix_size(mask):
    rows = len(mask)
    cols = None
    for row in mask:
        if cols is None:
            cols = len(row)
        if cols != len(row):
            raise ValueError("la cantidad de columnas de una imagen " + 
            "debe ser igual en cada fila")            
    return (rows, cols)

    
def _is_aplicable_mask(img_size, mask_size):
    return (mask_size[0] <= img_size[0] and
            mask_size[1] <= img_size[1])
        
        
def _is_valid_mask(mask_size):
    valid = ((mask_size[0] % 2) != 0)
    valid = (mask_size[0] == mask_size[1])
    return valid
    
    
def _conv(mask, img_part):
    value = 0.0
    rows = zip(mask, img_part)
    for mask_row,img_row in rows:
        cells = zip(mask_row, img_row)
        for i,j in cells:
            value += i * j
    return value   


def _split_img(img, part_size):
    i,j = part_size
    
   
   
def aplicate_mask(img, mask):
    
    #Tamano de la mascara en formato (i,j)
    img_size = _get_matrix_size(img)
    
    #Tamano de la imagen en formato (i,j)
    mask_size = _get_matrix_size(mask)
    
    #Validamos que todo este bien para empezar a procesar 
    if not _is_valid_mask(mask_size):
        raise ValueError('''Mascara invalida.
        Mascara debe ser nxn siendo n entero impar''')
    if not _is_aplicable_mask(img_size, mask_size):
        raise ValueError("Mascara debe ser mas pequena que la imagen")
        
    #encontramos el pixel a reemplazar en cada fragmento de imagen
    center = (mask_size[0]/2, mask_size[1]/2)
    
    #nueva imagen con el valor de la anterior
    new_img = [list(row) for row in img]
    
    img_parts = _split_img(img, mask)
    
    
def mid_filter(img, mask_size):
    i,j = mask_size
    mask = []
    for idxi in range(i):
        row = [(1/mask_size) for idxj in range(j)]
        mask.append(row)
    return aplicate_mask(img, mask)



############################
#           TEST           #
############################

if __name__ == '__main__':
    
    def print_matrix(matrix, tabs=0):
        ttp = [ "\t" for c in range(tabs)]
        ttp = "".join(ttp)
        for row in matrix:
            print(ttp + str(row))
        
        
    print('TEST CASE')
    img = [
            [1, 2, 3, 4],
            [5, 6, 7, 8],
            [9, 10, 11, 12],
            [13, 14, 15, 16]
         ]
    print("\t img matrix:")
    print_matrix(img, 2)
    img_size = _get_matrix_size(img)
    print("\t img size: " + str(img_size))
    
    mask = [
            [2,1,3],
            [4,5,6],
            [7,8,9]
           ]
    print("\n\t mask:")
    print_matrix(mask, 2)
    mask_size = _get_matrix_size(mask)
    print("\t mask size: " + str(mask_size))
    print("\t Mascara valida? " + str(_is_valid_mask(mask_size)))
    print("\t Es aplicable a la imagen? " + 
            str(_is_aplicable_mask(img_size, mask_size)))
    print("\n\tSeparando imagen...")
    _split_img(img, mask_size)
     
    
            
    
    
    
    
