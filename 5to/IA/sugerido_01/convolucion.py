__author__ = 'Juan B Cabral 40.42 - 5k4'

def _get_matrix_size(mask):
    rows = len(mask)
    cols = None
    for row in mask:
        if cols is None:
            cols = len(row)
        if cols != len(row):
            raise ValueError("la cantidad de columnas de una imagen o mascara" + 
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
    pass
   
def aplicate_mask(img, mask):
    img_size = _get_matrix_size(img)
    mask_size = _get_matrix_size(mask)
    if not _is_valid_mask(mask_size):
        raise ValueError('''Mascara invalida.
        Mascara debe ser nxn siendo n entero impar''')
    if not _is_aplicable_mask(img_size, mask_size):
        raise ValueError("Mascara debe ser mas pequeña que la imagen")
    new_img = None
    
