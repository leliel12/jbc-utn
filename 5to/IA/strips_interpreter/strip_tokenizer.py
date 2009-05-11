#!/usr/bin/env python
#
#       strip_tokenizer.py
#       
#       Copyright 2009 Juan B Cabral <juan@Brother-Eye>
#       
#       This program is free software; you can redistribute it and/or modify
#       it under the terms of the GNU General Public License as published by
#       the Free Software Foundation; either version 3 of the License, or
#       (at your option) any later version.
#       
#       This program is distributed in the hope that it will be useful,
#       but WITHOUT ANY WARRANTY; without even the implied warranty of
#       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#       GNU General Public License for more details.
#       
#       You should have received a copy of the GNU General Public License
#       along with this program; if not, write to the Free Software
#       Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#       MA 02110-1301, USA.

'''Splits strip source doce in lines ingnoring the comments an empty lines.'''

import constants

def _is_empty(line):
    return not line.strip()


def _adjust(line):
    return line.rstrip()

    
def _del_comments(line):
    if constants.RW_COMMENT in line:
        idx =  line.index(constants.RW_COMMENT)
        line = line[0:idx]
    return line


def _append(line, line_list):
    if not _is_empty(line):
        line_list.append(line)
    return line_list


def strip_tokenizer(strip_src):
    line_list = []
    for line in strip_src.splitlines():
        if not _is_empty(line):
            line = _del_comments(line)
            line = _adjust(line)
            line_list = _append(line, line_list)
    return line_list
 
    
if __name__ == '__main__':
    print __doc__
    plf = open("/home/juan/UTN/5to/IA/planif/strip_self_tutorial.strip").read()
    
    ln = 1
    for line in strip_tokenizer(plf):
        print "%s" % (line)
        ln +=1
