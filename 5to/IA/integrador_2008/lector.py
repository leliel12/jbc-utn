#!/usr/bin/env python

import os
from struct import IFile

SEP_CHAR = os.path.sep


def get_data(path):
    path = path.strip()
    if len(path) == 0:
        raise ValueError("Invalid Path: %s" % path)
    files = []
    try:
        i = 0
        while True:
            i += 1
            file_path = path + SEP_CHAR + str(i) + ".txt"
            i_file = IFile(file_path)
            files.append(i_file)    
    except IOError, ex:
        if ex.errno == 2:
            pass
        else:
            raise ex
    return files
    
    
if __name__ == "__main__":
    d = get_data(".")
    for coso in d:
        print coso
